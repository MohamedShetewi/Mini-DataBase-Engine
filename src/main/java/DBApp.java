import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.DateFormat;


public class DBApp implements DBAppInterface {
    @Override
    public void init() {
        File tablesDirectory = new File("src/main/resources/data/Tables");
        if (!tablesDirectory.exists())
            tablesDirectory.mkdirs();

        try {
            FileReader oldMetaDataFile = new FileReader("src/main/resources/metadata.csv");
            BufferedReader br = new BufferedReader(oldMetaDataFile);
            if (br.readLine() == null) {
                FileWriter metaDataFile = new FileWriter("src/main/resources/metadata.csv");
                StringBuilder tableMetaData = new StringBuilder();
                tableMetaData.append("Table Name,Column Name,Column Type,ClusteringKey,Indexed,min,max");
                metaDataFile.write(tableMetaData.toString());
                metaDataFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable(String tableName, String clusteringKey, Hashtable<String, String> colNameType, Hashtable<String, String> colNameMin, Hashtable<String, String> colNameMax) throws DBAppException {

        //validating input
        for (String col : colNameType.keySet())
            if (!colNameMax.containsKey(col) || !colNameMin.containsKey(col))
                throw new DBAppException();

        for (String col : colNameMax.keySet())
            if (!colNameType.containsKey(col) || !colNameMin.containsKey(col))
                throw new DBAppException();

        for (String col : colNameMin.keySet())
            if (!colNameType.containsKey(col) || !colNameMax.containsKey(col))
                throw new DBAppException();


        File tableDirectory = new File("src/main/resources/data/Tables/" + tableName);

        if (tableDirectory.exists())
            throw new DBAppException();
        else
            tableDirectory.mkdir();

        Table tableInstance = new Table(tableName);

        try {
            FileOutputStream serializedFile = new FileOutputStream("src/main/resources/data/Tables/" + tableName + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(serializedFile);
            out.writeObject(tableInstance);
            out.close();
            serializedFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //writing table info in MetaData
        try {

            FileReader oldMetaDataFile = new FileReader("src/main/resources/metadata.csv");

            BufferedReader br = new BufferedReader(oldMetaDataFile);
            StringBuilder tableMetaData = new StringBuilder();

            String curLine;
            while ((curLine = br.readLine()) != null)
                tableMetaData.append(curLine).append('\n');

            FileWriter metaDataFile = new FileWriter("src/main/resources/metadata.csv");
            for (String colName : colNameType.keySet()) {
                tableMetaData.append(tableName).append(",");
                tableMetaData.append(colName).append(",");
                tableMetaData.append(colNameType.get(colName)).append(",");
                tableMetaData.append(colName.equals(clusteringKey) ? "True" : "False").append(",");
                tableMetaData.append("False,");
                tableMetaData.append(colNameMin.get(colName)).append(",");
                tableMetaData.append(colNameMax.get(colName));
                tableMetaData.append("\n");
            }
            metaDataFile.write(tableMetaData.toString());
            metaDataFile.close();
        } catch (IOException ignored) {
        }
    }


    @Override
    public void insertIntoTable(String tableName, Hashtable<String, Object> colNameValue) throws DBAppException {
        //Check the table exists and the input record is valid.
        String primaryKey = validateRecord(tableName, colNameValue);
        try {
            //Deserialize table and get the index of the page to insert in
            Table t = (Table) deserializeObject("src/main/resources/data/Tables/" + tableName + ".ser");
            Vector<Page> pages = t.getPages();
            int idxOfPage = searchForPage(pages, colNameValue.get(primaryKey));
            if (pages.size() == 0) {
                //empty table
                createAndSerializePage(t, colNameValue, primaryKey, 0);
                delete("src/main/resources/data/Tables/" + tableName + ".ser");
                serializeObject(t, "src/main/resources/data/Tables/" + tableName + ".ser");
                return;
            }
            int maxCountInPage = readConfig()[0];

            if (idxOfPage < pages.size()) {
                //I found the exact page I should insert in
                Page curPage = pages.get(idxOfPage);
                Vector<Hashtable<String, Object>> pageRecords = (Vector<Hashtable<String, Object>>) deserializeObject(curPage.getPath());
                int idxOfRecord = searchInsidePage(pageRecords, colNameValue.get(primaryKey), primaryKey);
                if (idxOfRecord < pageRecords.size())
                    throw new DBAppException("The record already exists in the table.");
                if (pageRecords.size() == maxCountInPage) {
                    //Page is full. Check the following page.
                    if (idxOfPage + 1 < pages.size()) {
                        Page followingPage = pages.get(idxOfPage + 1);
                        if (followingPage.getNumOfRecords() == maxCountInPage) {
                            //following page is also full. Create new page in between and shift.
                            createNewPageAndShift(t, colNameValue, pageRecords, primaryKey, idxOfPage);
                        } else {
                            //following page is not full.
                            //shift last record in the current page to the following page
                            //and insert the record in place in the current page.
                            insertRecordInPlace(idxOfRecord, colNameValue, primaryKey, pageRecords, curPage);

                            Hashtable<String, Object> lastRecord = pageRecords.remove(pageRecords.size() - 1);
                            Vector<Hashtable<String, Object>> followingPageRecords = (Vector<Hashtable<String, Object>>) deserializeObject(followingPage.getPath());

                            followingPageRecords.add(0, lastRecord);
                            followingPage.setMinClusteringValue(lastRecord.get(primaryKey));
                            followingPage.setNumOfRecords(followingPageRecords.size());

                            curPage.setNumOfRecords(pageRecords.size());
                            curPage.setMaxClusteringValue(pageRecords.get(pageRecords.size() - 1).get(primaryKey));

                            delete(followingPage.getPath());
                            serializeObject(followingPageRecords, followingPage.getPath());
                        }
                    } else {
                        //current page was the last page in the table.
                        //need to create new page and shift some records
                        createNewPageAndShift(t, colNameValue, pageRecords, primaryKey, idxOfPage);
                    }
                } else {
                    insertRecordInPlace(idxOfRecord, colNameValue, primaryKey, pageRecords, curPage);
                }
            } else {
                //The key doesn't belong to a range in any page.

                idxOfPage -= pages.size();
                if (idxOfPage == 0) {
                    //check for place in page at index 0 else create new page
                    Page curPage = pages.get(0);
                    if (curPage.getNumOfRecords() < maxCountInPage) {
                        Vector<Hashtable<String, Object>> pageRecords = (Vector<Hashtable<String, Object>>) deserializeObject(curPage.getPath());
                        int idxOfRecord = searchInsidePage(pageRecords, colNameValue.get(primaryKey), primaryKey);
                        insertRecordInPlace(idxOfRecord, colNameValue, primaryKey, pageRecords, curPage);
                    } else {
                        createAndSerializePage(t, colNameValue, primaryKey, 0);
                    }
                } else if (idxOfPage == pages.size()) {
                    //check for place in last page else create new page
                    Page curPage = pages.get(pages.size() - 1);
                    if (curPage.getNumOfRecords() < maxCountInPage) {
                        Vector<Hashtable<String, Object>> pageRecords = (Vector<Hashtable<String, Object>>) deserializeObject(curPage.getPath());
                        int idxOfRecord = searchInsidePage(pageRecords, colNameValue.get(primaryKey), primaryKey);
                        insertRecordInPlace(idxOfRecord, colNameValue, primaryKey, pageRecords, curPage);
                    } else {
                        createAndSerializePage(t, colNameValue, primaryKey, pages.size());
                    }
                } else {
                    //check for the current page then the following else create a new page
                    Page curPage = pages.get(idxOfPage - 1);
                    if (curPage.getNumOfRecords() < maxCountInPage) {
                        Vector<Hashtable<String, Object>> pageRecords = (Vector<Hashtable<String, Object>>) deserializeObject(curPage.getPath());
                        int idxOfRecord = searchInsidePage(pageRecords, colNameValue.get(primaryKey), primaryKey);
                        insertRecordInPlace(idxOfRecord, colNameValue, primaryKey, pageRecords, curPage);
                    } else {
                        curPage = pages.get(idxOfPage); //this now is following page
                        if (curPage.getNumOfRecords() < maxCountInPage) {
                            Vector<Hashtable<String, Object>> pageRecords = (Vector<Hashtable<String, Object>>) deserializeObject(curPage.getPath());
                            int idxOfRecord = searchInsidePage(pageRecords, colNameValue.get(primaryKey), primaryKey);
                            insertRecordInPlace(idxOfRecord, colNameValue, primaryKey, pageRecords, curPage);
                        } else {
                            createAndSerializePage(t, colNameValue, primaryKey, idxOfPage);
                        }
                    }
                }
            }
            delete("src/main/resources/data/Tables/" + tableName + ".ser");
            serializeObject(t, "src/main/resources/data/Tables/" + tableName + ".ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createAndSerializePage(Table table, Hashtable<String, Object> colNameValue, String primaryKey, int index) throws IOException {

        String pagePath = "src/main/resources/data/Tables/" + table.getTableName() + "/page" + table.getPagesCounter() + ".ser";
        table.setPagesCounter(table.getPagesCounter() + 1);
        Page newPage = new Page(pagePath);

        Vector<Hashtable<String, Object>> pageRecords = new Vector<>();
        pageRecords.add(colNameValue);
        newPage.setMinClusteringValue(colNameValue.get(primaryKey));
        newPage.setMaxClusteringValue(colNameValue.get(primaryKey));
        newPage.setNumOfRecords(newPage.getNumOfRecords() + 1);

        table.getPages().add(index, newPage);
        serializeObject(pageRecords, pagePath);

    }

    private void insertRecordInPlace(int idxOfRecord, Hashtable<String, Object> colNameValue, String primaryKey, Vector<Hashtable<String, Object>> pageRecords, Page curPage) throws IOException {
        idxOfRecord -= curPage.getNumOfRecords();
        pageRecords.add(idxOfRecord, colNameValue);
        curPage.setMinClusteringValue(pageRecords.get(0).get(primaryKey));
        curPage.setMaxClusteringValue(pageRecords.get(pageRecords.size() - 1).get(primaryKey));
        curPage.setNumOfRecords(pageRecords.size());
        delete(curPage.getPath());
        serializeObject(pageRecords, curPage.getPath());
    }

    private void createNewPageAndShift(Table table, Hashtable<String, Object> colNameValue, Vector<Hashtable<String, Object>> pageRecords, String primaryKey, int idxOfPreviousPage) throws IOException {

        String newPagePath = "src/main/resources/data/Tables/" + table.getTableName() + "/page" + table.getPagesCounter() + ".ser";
        table.setPagesCounter(table.getPagesCounter() + 1);

        Vector<Hashtable<String, Object>> newPageRecords = new Vector<>();
        newPageRecords.add(colNameValue);
        for (int i = 0; i < pageRecords.size(); i++) {
            Hashtable<String, Object> record = pageRecords.get(i);
            if (compare(record.get(primaryKey), colNameValue.get(primaryKey)) > 0) {
                Hashtable<String, Object> newRecord = pageRecords.remove(i--);
                newPageRecords.add(newRecord);
            }
        }
        Page newPage = new Page(newPagePath);

        newPage.setMinClusteringValue(colNameValue.get(primaryKey));
        newPage.setMaxClusteringValue(newPageRecords.get(newPageRecords.size() - 1).get(primaryKey));
        newPage.setNumOfRecords(newPageRecords.size());

        table.getPages().get(idxOfPreviousPage).setNumOfRecords(pageRecords.size());
        table.getPages().get(idxOfPreviousPage).setMinClusteringValue(pageRecords.get(0).get(primaryKey));
        table.getPages().get(idxOfPreviousPage).setMaxClusteringValue(pageRecords.get(pageRecords.size() - 1).get(primaryKey));

        delete(table.getPages().get(idxOfPreviousPage).getPath());
        serializeObject(pageRecords, table.getPages().get(idxOfPreviousPage).getPath());

        serializeObject(newPageRecords, newPagePath);
        table.getPages().add(idxOfPreviousPage + 1, newPage);

    }

    private void serializeObject(Object o, String path) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(o);
        objectOut.close();
        fileOut.close();
    }

    private Object deserializeObject(String path) throws IOException, ClassNotFoundException {
        Object o = null;
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        o = objectIn.readObject();
        objectIn.close();
        fileIn.close();
        return o;
    }

    private String validateRecord(String tableName, Hashtable<String, Object> colNameValue) throws DBAppException {
        //complete.
        //The method checks for the following
        //The input record include the primary key.
        //The input record's values are of the right types as the table in the metadata.
        //The input record's values are in the range between their min and max values.

        boolean found = false;
        ArrayList<String[]> tableCols = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/metadata.csv"));
            String line;
            big:
            while ((line = br.readLine()) != null) {
                String[] record = line.split(",");
                if (record[0].equals(tableName)) {
                    found = true;
                    tableCols.add(record);
                    while ((line = br.readLine()) != null) {
                        record = line.split(",");
                        if (record[0].equals(tableName)) {
                            tableCols.add(record);
                        } else break big;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!found) {
            throw new DBAppException("There is no such table in the Database.");
        }

        boolean primaryKeyExist = false;
        String primaryKey = null;
        for (String[] record : tableCols) {
            boolean valid = true;
            if (record[3].equals("True")) {
                primaryKeyExist = colNameValue.get(record[1]) != null;
                primaryKey = record[1];
            }
            if (!colNameValue.containsKey(record[1]))
                continue;
            Class c = colNameValue.get(record[1]).getClass();
            if ((c.getName()).equals(record[2])) {
                switch (c.getName()) {
                    case "java.lang.Integer": {
                        Integer min = Integer.parseInt(record[5]);
                        Integer max = Integer.parseInt(record[6]);
                        if (!(((Integer) colNameValue.get(record[1])).compareTo(min) >= 0 && ((Integer) colNameValue.get(record[1])).compareTo(max) <= 0))
                            valid = false;
                        break;
                    }
                    case "java.lang.String": {
                        String min = record[5];
                        String max = record[6];
                        if (!(((String) colNameValue.get(record[1])).compareTo(min) >= 0 && ((String) colNameValue.get(record[1])).compareTo(max) <= 0))
                            valid = false;
                        break;
                    }
                    case "java.lang.Double": {
                        double min = Double.parseDouble(record[5]);
                        double max = Double.parseDouble(record[6]);
                        if (!((double) colNameValue.get(record[1]) >= min && (double) colNameValue.get(record[1]) <= max))
                            valid = false;
                        break;
                    }
                    default:
                        try {
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date min = format.parse(record[5]);
                            Date max = format.parse(record[6]);
                            if (((Date) colNameValue.get(record[1])).compareTo(min) < 0 || ((Date) colNameValue.get(record[1])).compareTo(max) > 0)
                                valid = false;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                if (!valid)
                    throw new DBAppException("The value of the field " + record[1] + " is not in the range.\nThe value should be between " + record[5] + " and " + record[6] + ". Found: " + colNameValue.get(record[1]));
            } else
                throw new DBAppException("The value of the field " + record[1] + " is incompatible.\nThe value should be an instance of " + record[2] + ".");
        }
        if (!primaryKeyExist)
            throw new DBAppException("The primary key must be included in the record.");
        return primaryKey;
    }

    private int[] readConfig() {
        Properties prop = new Properties();
        String filePath = "src/main/resources/DBApp.config";
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            prop.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int[] arr = new int[2];
        arr[0] = Integer.parseInt(prop.getProperty("MaximumRowsCountinPage"));
        arr[1] = Integer.parseInt(prop.getProperty("MaximumKeysCountinIndexBucket"));
        return arr;
    }

    @Override
    public void createIndex(String tableName, String[] columnNames) throws DBAppException, IOException, ParseException {


        String clusteringKey = validateExistingTable(tableName);
        Object[] tableInfo = getTableInfo(tableName);
        Hashtable<String,String> columnsInfo = (Hashtable<String, String>) tableInfo[0];

        for (String colName : columnNames)
            if(!columnsInfo.containsKey(colName))
                throw new DBAppException("No such column exits!");

        int[]dimensions = new int[columnNames.length];
        Arrays.fill(dimensions, 10);

        Object gridIndex = Array.newInstance(Vector.class, dimensions);
        


    }




    @Override
    public void updateTable(String tableName, String clusteringKeyValue,
                            Hashtable<String, Object> columnNameValue)
            throws DBAppException, IOException, ClassNotFoundException, ParseException {
        Object[] clusteringInfo = validateUpdateInput(tableName, clusteringKeyValue, columnNameValue);
        String clusteringCol = (String) clusteringInfo[0];
        Object clusteringObject = clusteringInfo[1];

        Table t = (Table) deserializeObject("src/main/resources/data/Tables/" + tableName + ".ser");

        int pageIdx = searchForPage(t.getPages(), clusteringObject);
        if (pageIdx >= t.getPages().size()) {
            System.out.println("Invalid clustering key value");
            return;
        }
        Vector<Hashtable<String, Object>> page = (Vector<Hashtable<String, Object>>) deserializeObject(t.getPages().get(pageIdx).getPath());

        int rowIdx = searchInsidePage(page, clusteringObject, clusteringCol);
        if (rowIdx >= page.size()) {
            System.out.println("Invalid clustering key value");
            return;
        }
        for (String key : columnNameValue.keySet()) {
            page.get(rowIdx).replace(key, columnNameValue.get(key));
        }
        delete(t.getPages().get(pageIdx).getPath());
        serializeObject(page, t.getPages().get(pageIdx).getPath());
    }

    private int searchForPage(Vector<Page> pages, Object clusteringObject) {
        int lo = 0;
        int hi = pages.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (compare(clusteringObject, pages.get(mid).getMinClusteringValue()) >= 0
                    && compare(clusteringObject, pages.get(mid).getMaxClusteringValue()) <= 0) {
                return mid;
            } else if (compare(clusteringObject, pages.get(mid).getMinClusteringValue()) <= 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return hi + 1 + pages.size();
    }

    private int searchInsidePage(Vector<Hashtable<String, Object>> page, Object clusteringObject, String clusteringCol) {
        int lo = 0;
        int hi = page.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (compare(clusteringObject, page.get(mid).get(clusteringCol)) == 0) {
                return mid;
            } else if (compare(clusteringObject, page.get(mid).get(clusteringCol)) < 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return hi + 1 + page.size();
    }

    private int compare(Object clusteringObject1, Object clusteringObject2) {
        if (clusteringObject1 instanceof java.lang.Integer) {
            return ((Integer) clusteringObject1).compareTo((Integer) clusteringObject2);
        } else if (clusteringObject1 instanceof java.lang.Double) {
            return ((Double) clusteringObject1).compareTo((Double) clusteringObject2);
        } else if (clusteringObject1 instanceof java.util.Date) {
            return ((Date) clusteringObject1).compareTo((Date) clusteringObject2);
        } else {
            return ((String) clusteringObject1).compareTo((String) clusteringObject2);
        }
    }


    /**
     * deletes the file from the disk
     *
     * @param path the path of the file to be deleted.
     */
    private boolean delete(String path) {
        File f = new File(path);
        return f.delete();
    }

    @Override
    /**
     * deletes all rows that matches ALL of the specified entries (AND operator) from the table.
     * @param tableName name of the table to delete the rows from
     * @param columnNameValue the entries to which rows will be compared with
     * @throws ClassNotFoundException If an error occurred in the stored table pages format
     * @throws IOException If an I/O error occurred
     * @throws DBAppException If an an error occurred in the table(No rows are deleted,table not found,types don't match,...)
     * @throws ParseException If an error occurred while parsing the input
     */
    public void deleteFromTable(String tableName, Hashtable<String, Object> columnNameValue) throws DBAppException, IOException, ClassNotFoundException, ParseException {
        //validate input, get clusteringKey
        String clusteringKey = validateDeleteFromTable(tableName, columnNameValue);
        //Read the table from disk
        String tablePath = "src/main/resources/data/Tables/" + tableName + ".ser";
        Table targetTable = (Table) deserializeObject(tablePath);
        //check if the clustering key exists in the entries
        boolean clusteringKeyExists = columnNameValue.get(clusteringKey) != null;
        boolean deleted;
        if (clusteringKeyExists)
            deleted = deleteFromTableBinarySearch(targetTable, columnNameValue, clusteringKey);//binary search for the clustering key
        else
            deleted = deleteFromTableLinearSearch(targetTable, columnNameValue, clusteringKey);// linear deletion
        if (!deleted) {
            System.out.println("No Rows matching the entries were found. Row: " + columnNameValue);
            return;
        }
        //update the table's file in disk
        delete(tablePath);
        serializeObject(targetTable, tablePath);
    }

    /**
     * validates the input values by checking that the entries' values are in the range and their types are compatible.
     *
     * @param tableName       the name of the table to get the max,min and data types from.
     * @param columnNameValue the entries to validate.
     * @return the name of the clustering key column
     * @throws ClassNotFoundException If an error occurred in the stored table pages format
     * @throws IOException            If an I/O error occurred
     * @throws DBAppException         If an an error occurred in the table(No rows are deleted,table not found,types don't match,...)
     * @throws ParseException         If an error occurred while parsing the input
     */
    private String validateDeleteFromTable(String tableName, Hashtable<String, Object> columnNameValue) throws IOException, ParseException, DBAppException, ClassNotFoundException {
        Object[] tableInfo = getTableInfo(tableName);
        Hashtable<String, String> colDataTypes = (Hashtable<String, String>) tableInfo[0];
        Hashtable<String, Object> colMin = (Hashtable<String, Object>) tableInfo[1];
        Hashtable<String, Object> colMax = (Hashtable<String, Object>) tableInfo[2];
        String clusteringCol = (String) tableInfo[4];
        checkColumnsCompatibility(columnNameValue, colDataTypes);
        checkValuesRanges(columnNameValue, colDataTypes, colMin, colMax);
        return clusteringCol;
    }

    /**
     * deletes all rows that matches ALL of the specified entries(AND operator) from the page.
     *
     * @param columnNameValue the column key-value pairs to which records will be compared with
     * @param clusteringKey   the clustering key of the page's table
     * @return 0 if no rows are deleted,-1 if all rows are deleted and 1 if some (but not all) rows are deleted.
     * @throws ClassNotFoundException If an error occurred in the stored table pages format
     * @throws IOException            If an I/O error occurred
     */
    private int deleteFromPage(Page p, Hashtable<String, Object> columnNameValue, String clusteringKey) throws IOException, ClassNotFoundException {
        //read the page from disk
        Vector<Hashtable<String, Object>> rows = (Vector<Hashtable<String, Object>>) deserializeObject(p.getPath());
        int state = 0;//return state
        //iterate through the page to compare
        if (columnNameValue.get(clusteringKey) != null) {
            int idxInsidePage = searchInsidePage(rows, columnNameValue.get(clusteringKey), clusteringKey);
            if (idxInsidePage < rows.size()) {
                boolean delete = true;
                Hashtable<String, Object> curRow = rows.get(idxInsidePage);
                for (String key : columnNameValue.keySet())
                    delete &= curRow.containsKey(key) && compare(curRow.get(key), columnNameValue.get(key)) == 0;
                if (delete) {
                    rows.remove(idxInsidePage);
                    state = 1;
                }
            }
        } else {
            Iterator<Hashtable<String, Object>> rowsIterator = rows.iterator();
            while (rowsIterator.hasNext()) {
                boolean delete = true;
                Hashtable<String, Object> curRow = rowsIterator.next();
                //compare all entries of the row to be deleted with the current row's entries
                for (String key : columnNameValue.keySet())
                    delete &= curRow.containsKey(key) && compare(curRow.get(key), columnNameValue.get(key)) == 0;//checks if the curRow contains the key and the values match

                if (delete) {
                    rowsIterator.remove();
                    state = 1;
                }
            }
        }
        delete(p.getPath());
        if (rows.isEmpty())
            return -1;
        //update the max, min clustering keys and the number of records
        p.setNumOfRecords(rows.size());
        p.setMaxClusteringValue(rows.get(rows.size() - 1).get(clusteringKey));
        p.setMinClusteringValue(rows.get(0).get(clusteringKey));
        serializeObject(rows, p.getPath());
        return state;
    }

    /**
     * searches for the rows that match the entries using linear search and deletes them.
     *
     * @param table           the table to delete the rows from
     * @param columnNameValue the entries to which rows will be compared with
     * @param clusteringKey   the clustering key of the table
     * @return true if any rows are deleted.
     * @throws ClassNotFoundException If an error occurred in the stored table pages format
     * @throws IOException            If an I/O error occurred
     */
    private boolean deleteFromTableLinearSearch(Table table, Hashtable<String, Object> columnNameValue, String clusteringKey) throws IOException, ClassNotFoundException {
        Vector<Page> pages = table.getPages();
        Iterator<Page> pagesIterator = pages.iterator();
        boolean deletedRows = false;//is set to true if any rows are deleted
        while (pagesIterator.hasNext()) {
            Page p = pagesIterator.next();
            int state = deleteFromPage(p, columnNameValue, clusteringKey);//returns -1 if the page becomes empty
            //update the page's file in disk
            if (state == -1) {
                //if the page is empty , dont save the page to disk and remove it from the vector
                pagesIterator.remove();
                deletedRows = true;
            } else {
                deletedRows |= state == 1;
            }
        }
        return deletedRows;
    }

    /**
     * searches for the row that contains the specified clustering value and deletes it.
     *
     * @param table           name of the table to delete the rows from
     * @param columnNameValue the entries to which records will be compared with
     * @param clusteringKey   the clustering key of the table
     * @return true if the row is deleted.
     * @throws ClassNotFoundException If an error occurred in the stored table pages format
     * @throws IOException            If an I/O error occurred
     * @throws DBAppException         If an an error occurred in the table(table not found,types don't match,...)
     */
    private boolean deleteFromTableBinarySearch(Table table, Hashtable<String, Object> columnNameValue, String clusteringKey) throws IOException, ClassNotFoundException {
        Vector<Page> pages = table.getPages();
        int index = searchForPage(pages, columnNameValue.get(clusteringKey));//binary search for the index of the page that contains the clustering value
        if (index >= pages.size())//row not found
            return false;
        Page page = pages.get(index);//page that contains the row to delete
        int state = deleteFromPage(page, columnNameValue, clusteringKey);//returns -1 if the page is empty
        if (state == -1) {
            //if the page is empty,remove it from the vector
            pages.remove(index);
            return true;
        }
        return state == 1;
    }

    @Override
    public Iterator selectFromTable(SQLTerm[] sqlTerms, String[] arrayOperators) throws IOException, DBAppException, ClassNotFoundException {
        if (sqlTerms.length - 1 != arrayOperators.length)
            throw new DBAppException("Number of terms and operators does not match.");
        String targetTableName = sqlTerms[0].getTableName();
        validateArrayOperators(arrayOperators);
        String clusteringColumnName = validateExistingTable(targetTableName);
        validateTerms(sqlTerms);

        // getting the table Object we want to select from
        Table targetTable = (Table) deserializeObject("src/main/resources/data/Tables/" + targetTableName + ".ser");

        Vector<Page> tablePages = targetTable.getPages();
        Stack<Vector<Hashtable<String, Object>>> termsSets = new Stack<>();
        for (int i = sqlTerms.length - 1; i >= 0; i--) {
            Vector<Hashtable<String, Object>> vector = isValidTerm(sqlTerms[i], tablePages, clusteringColumnName);
            termsSets.add(vector);
        }

        for (String arrayOperator : arrayOperators) {
            Vector<Hashtable<String, Object>> a = termsSets.pop();
            Vector<Hashtable<String, Object>> b = termsSets.pop();

            switch (arrayOperator) {
                case "AND":
                    Vector<Hashtable<String, Object>> aIntersectB = rowsIntersection(a, b, clusteringColumnName);
                    termsSets.push(aIntersectB);
                    break;
                case "OR":
                    Vector<Hashtable<String, Object>> aUnionB = rowsUnion(a, b, clusteringColumnName);
                    termsSets.push(aUnionB);
                    break;
                case "XOR":
                    Vector<Hashtable<String, Object>> aDiffB = rowsDifference(a, b, clusteringColumnName);
                    Vector<Hashtable<String, Object>> bDiffA = rowsDifference(b, a, clusteringColumnName);
                    Vector<Hashtable<String, Object>> aXorB = rowsUnion(aDiffB, bDiffA, clusteringColumnName);
                    termsSets.push(aXorB);
                    break;
            }
        }

        Vector<Hashtable<String, Object>> queryResult;
        queryResult = termsSets.pop();
        return queryResult.iterator();
    }

    private Vector<Hashtable<String, Object>> rowsUnion(Vector<Hashtable<String, Object>> a, Vector<Hashtable<String, Object>> b, String clusteringColumnName) {
        Vector<Hashtable<String, Object>> resultOfUnion = new Vector<>();
        resultOfUnion.addAll(a);
        resultOfUnion.addAll(rowsDifference(b, a, clusteringColumnName));
        return resultOfUnion;
    }

    private Vector<Hashtable<String, Object>> rowsDifference(Vector<Hashtable<String, Object>> a, Vector<Hashtable<String, Object>> b, String clusteringColumnName) {
        Vector<Hashtable<String, Object>> resultOfDifference = new Vector<>();
        for (Hashtable<String, Object> rowA : a) {
            boolean found = false;
            for (Hashtable<String, Object> rowB : b) {
                if (rowA.get(clusteringColumnName).equals(rowB.get(clusteringColumnName))) {
                    found = true;
                }
            }
            if (!found)
                resultOfDifference.add(rowA);
        }
        return resultOfDifference;
    }

    private Vector<Hashtable<String, Object>> rowsIntersection(Vector<Hashtable<String, Object>> a, Vector<Hashtable<String, Object>> b, String clusteringColumnName) {
        Vector<Hashtable<String, Object>> resultOfIntersection = new Vector<>();
        for (Hashtable<String, Object> rowA : a) {
            boolean found = false;
            for (Hashtable<String, Object> rowB : b) {
                if (rowA.get(clusteringColumnName).equals(rowB.get(clusteringColumnName))) {
                    found = true;
                    break;
                }
            }
            if (found)
                resultOfIntersection.add(rowA);
        }
        return resultOfIntersection;
    }


    private void validateArrayOperators(String[] arrayOperators) throws DBAppException {
        for (String operator : arrayOperators) {
            if (!(operator.equals("OR") || operator.equals("AND") || operator.equals("XOR")))
                throw new DBAppException("The operator is not correct");
        }
    }

    private Vector<Hashtable<String, Object>> isValidTerm(SQLTerm term, Vector<Page> tablePages, String clusteringColumnName) throws IOException, ClassNotFoundException {
        if (term.getColumnName().equals(clusteringColumnName))
            return searchOnClustering(term, tablePages);
        return searchLinearly(term, tablePages);

    }

    private Vector<Hashtable<String, Object>> searchLinearly(SQLTerm term, Vector<Page> tablePages) throws IOException, ClassNotFoundException {
        Vector<Hashtable<String, Object>> result = new Vector<>();
        for (Page page : tablePages) {
            Vector<Hashtable<String, Object>> rows = (Vector<Hashtable<String, Object>>) deserializeObject(page.getPath());
            for (Hashtable<String, Object> row : rows)
                if (booleanValueOfTerm(row, term))
                    result.add(row);
        }
        return result;
    }

    private Vector<Hashtable<String, Object>> searchOnClustering(SQLTerm term, Vector<Page> tablePages) throws IOException, ClassNotFoundException {
        Vector<Hashtable<String, Object>> result = new Vector<>();
        int lo = 0;
        int hi = tablePages.size() - 1;
        Page targetPage = tablePages.get(0);
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            Page curPage = tablePages.get(mid);
            if (term.compareTo(curPage.getMinClusteringValue()) >= 0 && term.compareTo(curPage.getMaxClusteringValue()) <= 0) {
                targetPage = curPage;
                break;
            }
            if (term.compareTo(curPage.getMinClusteringValue()) < 0)
                hi = mid - 1;
            else
                lo = mid + 1;
        }

        Vector<Hashtable<String, Object>> targetPageRows = (Vector<Hashtable<String, Object>>) deserializeObject(targetPage.getPath());
        if (term.getOperator().equals("=")) {
            lo = 0;
            hi = targetPageRows.size() - 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                Hashtable<String, Object> curRow = targetPageRows.get(mid);
                if (booleanValueOfTerm(curRow, term)) {
                    result.add(curRow);
                    break;
                }
                if (term.compareTo(curRow.get(term.getColumnName())) < 0)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }

            return result;
        }

        Vector<Page> pagesBeforeTarget = new Vector<>();
        Vector<Page> pagesAfterTarget = new Vector<>();
        for (Page page : tablePages) {
            if (page.equals(targetPage))
                break;
            pagesBeforeTarget.add(page);
        }
        for (int i = tablePages.indexOf(targetPage) + 1; i < tablePages.size(); i++)
            pagesAfterTarget.add(tablePages.get(i));

        for (Hashtable<String, Object> row : targetPageRows)
            if (booleanValueOfTerm(row, term))
                result.add(row);

        if (term.getOperator().equals("<") || term.getOperator().equals("<=")) {
            addRowsOfPage(result, pagesBeforeTarget);
        } else if (term.getOperator().equals(">") || term.getOperator().equals(">=")) {
            addRowsOfPage(result, pagesAfterTarget);
        } else {
            addRowsOfPage(result, pagesBeforeTarget);
            addRowsOfPage(result, pagesAfterTarget);
        }
        return result;

    }

    private void addRowsOfPage(Vector<Hashtable<String, Object>> result, Vector<Page> pages) throws IOException, ClassNotFoundException {
        for (Page page : pages) {
            FileInputStream fileInputStream = new FileInputStream(page.getPath());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            result.addAll((Vector<Hashtable<String, Object>>) objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
        }
    }


    private boolean booleanValueOfTerm(Hashtable<String, Object> row, SQLTerm term) {
        switch (term.getOperator()) {
            case "=":
                return (term.compareTo(row.get(term.getColumnName())) == 0);
            case "!=":
                return (term.compareTo(row.get(term.getColumnName())) != 0);
            case ">":
                return (term.compareTo(row.get(term.getColumnName())) < 0);
            case ">=":
                return (term.compareTo(row.get(term.getColumnName())) <= 0);
            case "<":
                return (term.compareTo(row.get(term.getColumnName())) > 0);
            case "<=":
                return (term.compareTo(row.get(term.getColumnName())) >= 0);
            default:
                return false;
        }
    }


    private String validateExistingTable(String targetTableName) throws IOException, DBAppException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/metadata.csv"));
        String curLine;
        while (br.ready()) {
            curLine = br.readLine();
            String[] metaData = curLine.split(",");
            if (metaData[0].equals(targetTableName)) {
                if (metaData[3].equals("True")) {
                    return metaData[1];
                }
            }
        }
        throw new DBAppException("There is no such table in the database.");
    }

    private void validateTerms(SQLTerm[] sqlTerms) throws IOException, DBAppException, ClassNotFoundException {
        String targetTableName = sqlTerms[0].getTableName();
        for (SQLTerm term : sqlTerms) {
            if (!term.getTableName().equals(targetTableName)) {
                throw new DBAppException("The table name in all terms must be the same.");
            }
        }

        FileReader metadata = new FileReader("src/main/resources/metadata.csv");
        BufferedReader br = new BufferedReader(metadata);
        String curLine;
        Hashtable<String, String> colDataTypes = new Hashtable<>();
        while ((curLine = br.readLine()) != null) {
            String[] res = curLine.split(",");
            if (res[0].equals(sqlTerms[0].getTableName())) {
                colDataTypes.put(res[1], res[2]);
            }
        }
        for (SQLTerm term : sqlTerms) {
            String columnName = term.getColumnName();
            Object columnValue = term.getValue();
            if (!colDataTypes.containsKey(columnName)) {
                throw new DBAppException("Column does not exist");
            }
            Class colClass = Class.forName(colDataTypes.get(columnName));
            if (!colClass.isInstance(columnValue)) {
                throw new DBAppException("Incompatible data types");
            }
        }
    }


    private Object[] validateUpdateInput(String tableName, String clusteringKeyValue,
                                         Hashtable<String, Object> columnNameValue) throws IOException, ParseException, DBAppException, ClassNotFoundException {
        Object[] tableInfo = getTableInfo(tableName);

        Hashtable<String, String> colDataTypes = (Hashtable<String, String>) tableInfo[0];
        Hashtable<String, Object> colMin = (Hashtable<String, Object>) tableInfo[1];
        Hashtable<String, Object> colMax = (Hashtable<String, Object>) tableInfo[2];
        String clusteringType = (String) tableInfo[3], clusteringCol = (String) tableInfo[4];

        Object clusteringObject;
        switch (clusteringType) {
            case "java.lang.Integer":
                try {
                    clusteringObject = Integer.parseInt(clusteringKeyValue);
                } catch (NumberFormatException e) {
                    throw new DBAppException("Incompatible clustering key data type");
                }
                break;
            case "java.lang.Double":
                try {
                    clusteringObject = Double.parseDouble(clusteringKeyValue);
                } catch (NumberFormatException e) {
                    throw new DBAppException("Incompatible clustering key data type");
                }
                break;
            case "java.util.Date":
                try {
                    clusteringObject = new SimpleDateFormat("yyyy-MM-dd").parse(clusteringKeyValue);
                } catch (ParseException e) {
                    throw new DBAppException("Incompatible clustering key data type");
                }
                break;
            default:
                clusteringObject = clusteringKeyValue;
                break;
        }

        checkColumnsCompatibility(columnNameValue, colDataTypes);
        checkValuesRanges(columnNameValue, colDataTypes, colMin, colMax);
        if (columnNameValue.get(clusteringCol) != null) {
            throw new DBAppException("Cannot update clustering cloumn.");
        }
        return new Object[]{clusteringCol, clusteringObject};
    }

    private void checkValuesRanges(Hashtable<String, Object> columnNameValue, Hashtable<String, String> colDataTypes, Hashtable<String, Object> colMin, Hashtable<String, Object> colMax) throws DBAppException {
        for (String key : columnNameValue.keySet()) {
            switch (colDataTypes.get(key)) {
                case "java.lang.Integer":
                    if (((Integer) columnNameValue.get(key)).compareTo((Integer) colMin.get(key)) < 0) {
                        throw new DBAppException("Value for column " + key + " is below the minimum allowed");
                    }
                    if (((Integer) columnNameValue.get(key)).compareTo((Integer) colMax.get(key)) > 0) {
                        throw new DBAppException("Value for column " + key + " is above the maximum allowed");
                    }
                    break;
                case "java.lang.Double":
                    if (((Double) columnNameValue.get(key)).compareTo((Double) colMin.get(key)) < 0) {
                        throw new DBAppException("Value for column " + key + " is below the minimum allowed");
                    }
                    if (((Double) columnNameValue.get(key)).compareTo((Double) colMax.get(key)) > 0) {
                        throw new DBAppException("Value for column " + key + " is above the maximum allowed");
                    }
                    break;
                case "java.util.Date":
                    if (((Date) columnNameValue.get(key)).compareTo((Date) colMin.get(key)) < 0) {
                        throw new DBAppException("Value for column " + key + " is below the minimum allowed");
                    }
                    if (((Date) columnNameValue.get(key)).compareTo((Date) colMax.get(key)) > 0) {
                        throw new DBAppException("Value for column " + key + " is above the maximum allowed");
                    }
                    break;
                default:
                    if (((String) columnNameValue.get(key)).compareTo((String) colMin.get(key)) < 0) {
                        throw new DBAppException("Value for column " + key + " is below the minimum allowed. Min: " + colMin.get(key) + ". Found: " + columnNameValue.get(key));
                    }
                    if (((String) columnNameValue.get(key)).compareTo((String) colMax.get(key)) > 0) {
                        throw new DBAppException("Value for column " + key + " is above the maximum allowed. Max: " + colMax.get(key) + ". Found: " + columnNameValue.get(key));
                    }
                    break;
            }
        }
    }

    private void checkColumnsCompatibility(Hashtable<String, Object> columnNameValue, Hashtable<String, String> colDataTypes) throws DBAppException, ClassNotFoundException {
        for (String key : columnNameValue.keySet()) {
            if (!colDataTypes.containsKey(key)) {
                throw new DBAppException("Column does not exist");
            }
            Class colClass = Class.forName(colDataTypes.get(key));
            if (!colClass.isInstance(columnNameValue.get(key))) {
                throw new DBAppException("Incompatible data types");
            }
        }
    }

    /**
     * Returns an array containing useful info about the passed table in the parameter.
     *
     * @param tableName name of the table that the method will use to retrieve data from metadata.csv
     * @return Array of objects.
     * <ul>
     *     <li>array[0] a Hashtable containing every column in the table and its data type.</li>
     *     <li>array[1] a Hashtable containing every column in the table and its allowed minimum value.</li>
     *     <li>array[2] a Hashtable containing every column in the table and its allowed maximum value.</li>
     *     <li>array[3] a String containing the data type of the clustering column</li>
     *     <li>array[4] a String containing the name of the clustering column</li>
     * </ul>
     * @throws IOException
     * @throws ParseException
     */

    private Object[] getTableInfo(String tableName) throws IOException, ParseException {
        FileReader metadata = new FileReader("src/main/resources/metadata.csv");
        BufferedReader br = new BufferedReader(metadata);
        String curLine;
        Hashtable<String, String> colDataTypes = new Hashtable<>();
        Hashtable<String, Object> colMin = new Hashtable<>();
        Hashtable<String, Object> colMax = new Hashtable<>();
        String clusteringType = "", clusteringCol = "";
        while ((curLine = br.readLine()) != null) {
            String[] res = curLine.split(",");
            if (res[0].equals(tableName)) {
                colDataTypes.put(res[1], res[2]);
                switch (res[2]) {
                    case "java.lang.Integer":
                        colMin.put(res[1], Integer.parseInt(res[5]));
                        colMax.put(res[1], Integer.parseInt(res[6]));
                        break;
                    case "java.lang.Double":
                        colMin.put(res[1], Double.parseDouble(res[5]));
                        colMax.put(res[1], Double.parseDouble(res[6]));
                        break;
                    case "java.util.Date":
                        colMin.put(res[1], new SimpleDateFormat("yyyy-MM-dd").parse(res[5]));
                        colMax.put(res[1], new SimpleDateFormat("yyyy-MM-dd").parse(res[6]));
                        break;
                    default:
                        colMin.put(res[1], res[5]);
                        colMax.put(res[1], res[6]);
                        break;
                }
                if (res[3].equals("True")) {
                    clusteringType = res[2];
                    clusteringCol = res[1];
                }
            }
        }
        return new Object[]{colDataTypes, colMin, colMax, clusteringType, clusteringCol};
    }

    public static void main(String[] args) throws DBAppException, IOException, ClassNotFoundException, ParseException {

    }
}
