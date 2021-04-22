import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.DateFormat;


public class DBApp implements DBAppInterface {
    @Override
    public void init() {

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


        File tableDirectory = new File("src/main/resources/Tables/" + tableName);

        if (tableDirectory.exists())
            throw new DBAppException();
        else
            tableDirectory.mkdir();

        Table tableInstance = new Table(tableName);

        try {
            FileOutputStream serializedFile = new FileOutputStream("src/main/resources/Tables/" + tableName + ".ser");
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
                tableMetaData.append(curLine);

            tableMetaData.append("\n");

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
    public void createIndex(String tableName, String[] columnNames) throws DBAppException {

    }

    @Override
    public void insertIntoTable(String tableName, Hashtable<String, Object> colNameValue) throws DBAppException, IOException, ParseException {
        //1- Check the table exists and the input record is valid.

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
            e.getMessage();
        }
        if (!found) {
            throw new DBAppException("There is no such table in the Database.");
        }

        validateRecord(tableCols, colNameValue);
    }

    @Override
    public void updateTable(String tableName, String clusteringKeyValue,
                            Hashtable<String, Object> columnNameValue)
            throws DBAppException, IOException, ClassNotFoundException, ParseException {
        Object[] clusteringInfo = validateUpdateInput(tableName, clusteringKeyValue, columnNameValue);
        String clusteringCol = (String) clusteringInfo[0];
        Object clusteringObject = clusteringInfo[1];
        FileInputStream fileIn = new FileInputStream("src/main/resources/Tables/" + tableName + ".ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Table t = (Table) in.readObject();
        in.close();
        fileIn.close();

        int pageIdx = searchForPage(t.getPages(), clusteringObject);
        if (pageIdx == -1) {
            throw new DBAppException("Invalid clustering key value");
        }
        FileInputStream pageFileIn = new FileInputStream(t.getPages().get(pageIdx).getPath());
        ObjectInputStream pageIn = new ObjectInputStream(pageFileIn);
        Vector<Hashtable<String, Object>> page = (Vector<Hashtable<String, Object>>) pageIn.readObject();
        pageIn.close();
        pageFileIn.close();

        int rowIdx = searchInsidePage(page, clusteringObject, clusteringCol);
        if (rowIdx == -1) {
            throw new DBAppException("Invalid clustering key value");
        }
        for (String key : columnNameValue.keySet()) {
            page.get(rowIdx).replace(key, columnNameValue.get(key));
        }

        FileOutputStream pageFileOut = new FileOutputStream(t.getPages().get(pageIdx).getPath());
        ObjectOutputStream pageOut = new ObjectOutputStream(pageFileOut);
        pageOut.writeObject(page);
        pageOut.close();
        pageFileOut.close();
    }

    private int searchForPage(Vector<Page> pages, Object clusteringObject) {
        int lo = 0;
        int hi = pages.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (compareClusteringValues(clusteringObject, pages.get(mid).getMinClusteringValue()) >= 0
                    && compareClusteringValues(clusteringObject, pages.get(mid).getMaxClusteringValue()) <= 0) {
                return mid;
            } else if (compareClusteringValues(clusteringObject, pages.get(mid).getMinClusteringValue()) <= 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    private int searchInsidePage(Vector<Hashtable<String, Object>> page, Object clusteringObject, String clusteringCol) {
        int lo = 0;
        int hi = page.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (compareClusteringValues(clusteringObject, page.get(mid).get(clusteringCol)) == 0) {
                return mid;
            } else if (compareClusteringValues(clusteringObject, page.get(mid).get(clusteringCol)) < 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    private int compareClusteringValues(Object clusteringObject1, Object clusteringObject2) {
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
     * saves the table to the disk
     *
     * @throws IOException If an I/O error occurred
     */
    private void save(String path) throws IOException {
        File f = new File(path);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(this);
        oos.close();
    }

    /**
     * deletes the table's file on the disk
     */
    public boolean delete(String path) {
        File f = new File(path);
        return f.delete();
    }

    @Override
    /**
     * deletes all rows that matches ALL of the specified entries (AND operator) from the table
     * @param tableName name of the table to delete the rows from
     * @param columnNameValue the entries to which rows will be compared with
     * @throws ClassNotFoundException If an error occurred in the stored table pages format
     * @throws IOException If an I/O error occurred
     * @throws DBAppException If an an error occurred in the table(No rows are deleted,table not found,types don't match,...)
     */
    public void deleteFromTable(String tableName, Hashtable<String, Object> columnNameValue) throws DBAppException, IOException, ClassNotFoundException {
        //validate table , get clusteringKey
        String clusteringKey = validateExistingTable(tableName);
        //Read the table from disk
        String tablePath = "src/main/resources/Tables/" + tableName + ".ser";
        FileInputStream serializedFile = new FileInputStream(tablePath);
        ObjectInputStream in = new ObjectInputStream(serializedFile);
        Table targetTable = (Table) in.readObject();
        in.close();
        serializedFile.close();
        //validate columns names and values??

        //check if the clustering key exists in the entries
        boolean clusteringKeyExists = columnNameValue.get(clusteringKey) != null;
        boolean deleted;
        if (clusteringKeyExists)
            deleted = deleteFromTableBinarySearch(targetTable, columnNameValue, clusteringKey);//binary search for the clustering key
        else
            deleted = deleteFromTableLinearSearch(targetTable, columnNameValue, clusteringKey);// linear deletion
        if (!deleted)
            throw new DBAppException("No Rows matching the entries were found");
        //update the table's file in disk
        delete(tablePath);
        save(tablePath);
    }

    /**
     * searches for the rows that match the entries using linear search and deletes them
     * returns true if some rows are deleted
     *
     * @param table           the table to delete the rows from
     * @param columnNameValue the entries to which rows will be compared with
     * @param clusteringKey   the clustering key of the table
     * @throws ClassNotFoundException If an error occurred in the stored table pages format
     * @throws IOException            If an I/O error occurred
     * @throws DBAppException         If an an error occurred in the table(table not found,types don't match,...)
     */
    private boolean deleteFromTableLinearSearch(Table table, Hashtable<String, Object> columnNameValue, String clusteringKey) throws IOException, ClassNotFoundException {
        Vector<Page> pages = table.getPages();
        Iterator<Page> pagesIterator = pages.iterator();
        boolean deletedRows = false;//is set to true if some rows are deleted
        while (pagesIterator.hasNext()) {
            Page p = pagesIterator.next();
            int state = p.delete(columnNameValue, clusteringKey);//returns -1 if the page becomes empty
            //update the page's file in disk
            delete(p.getPath());
            if (state == -1) {
                //if the page is empty , dont save the page to disk and remove it from the vector
                pagesIterator.remove();
                deletedRows = true;
            } else {
                deletedRows |= state == 1;
                save(p.getPath());
            }
        }
        return deletedRows;
    }

    /**
     * searches for the row that contains the specified clustering value and deletes it
     * returns true if the row is deleted
     *
     * @param table           name of the table to delete the rows from
     * @param columnNameValue the entries to which records will be compared with
     * @param clusteringKey   the clustering key of the table
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
        int state = page.delete(columnNameValue, clusteringKey);//returns -1 if the page is empty
        //update the page's file in disk
        delete(page.getPath());
        if (state == -1) {
            //if the page is empty , dont save the page to disk and remove it from the vector
            pages.remove(index);
            return true;
        }
        save(page.getPath());
        return state == 1;
    }

    @Override
    public Iterator selectFromTable(SQLTerm[] sqlTerms, String[] arrayOperators) throws IOException, DBAppException, ClassNotFoundException {
        if (sqlTerms.length != arrayOperators.length - 1)
            throw new DBAppException("Number of terms and operators does not match.");
        String targetTableName = sqlTerms[0].getTableName();
        validateArrayOperators(arrayOperators);
        String clusteringColumnName = validateExistingTable(targetTableName);
        validateTerms(sqlTerms);

        // getting the table Object we want to select from
        FileInputStream serializedFile = new FileInputStream("src/main/resources/Tables/" + targetTableName + ".ser");
        ObjectInputStream in = new ObjectInputStream(serializedFile);
        Table targetTable = (Table) in.readObject();
        in.close();
        serializedFile.close();
        //
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
            for (Hashtable<String, Object> rowB : b) {
                if (!rowA.get(clusteringColumnName).equals(rowB.get(clusteringColumnName))) {
                    resultOfDifference.add(rowA);
                }
            }
        }
        return resultOfDifference;
    }

    private Vector<Hashtable<String, Object>> rowsIntersection(Vector<Hashtable<String, Object>> a, Vector<Hashtable<String, Object>> b, String clusteringColumnName) {
        Vector<Hashtable<String, Object>> resultOfIntersection = new Vector<>();
        for (Hashtable<String, Object> rowA : a) {
            for (Hashtable<String, Object> rowB : b) {
                if (rowA.get(clusteringColumnName).equals(rowB.get(clusteringColumnName))) {
                    resultOfIntersection.add(rowA);
                }
            }
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
            FileInputStream serializedFile = new FileInputStream(page.getPath());
            ObjectInputStream in = new ObjectInputStream(serializedFile);
            Vector<Hashtable<String, Object>> rows = (Vector<Hashtable<String, Object>>) in.readObject();
            in.close();
            serializedFile.close();

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

        FileInputStream serializedFile = new FileInputStream(targetPage.getPath());
        ObjectInputStream in = new ObjectInputStream(serializedFile);
        Vector<Hashtable<String, Object>> targetPageRows = (Vector<Hashtable<String, Object>>) in.readObject();
        in.close();
        serializedFile.close();

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
                return (term.compareTo(row.get(term.getColumnName())) > 0);
            case ">=":
                return (term.compareTo(row.get(term.getColumnName())) >= 0);
            case "<":
                return (term.compareTo(row.get(term.getColumnName())) < 0);
            case "<=":
                return (term.compareTo(row.get(term.getColumnName())) <= 0);
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
            if (!term.getColumnName().equals(targetTableName))
                throw new DBAppException("The table name in all terms must be the same.");
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


    private static void validateRecord(ArrayList<String[]> tableCols, Hashtable<String, Object> colNameValue) throws DBAppException, ParseException {
        //complete.
        //The method checks for the following
        //The input record include (exactly) all the fields of the table.
        //The input record's values are of the right types as the table in the metadata.
        //The input record's values are in the range between their min and max values.

        if (colNameValue.size() != tableCols.size())
            throw new DBAppException("The record is not valid.\nAll fields must be included");

        for (String[] record : tableCols) {
            boolean valid = true;

            if (!colNameValue.containsKey(record[1]))
                throw new DBAppException("The field " + record[1] + "is not in the record" + ".\nAll fields must be included");

            Class c = colNameValue.get(record[1]).getClass();
            if ((c.getName()).equals(record[2])) {
                switch (c.getName()) {
                    case "java.lang.Integer": {
                        int min = Integer.parseInt(record[5]);
                        int max = Integer.parseInt(record[6]);
                        if (!((int) colNameValue.get(record[1]) >= min && (int) colNameValue.get(record[1]) <= max))
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
                    case "java.lang.String.Double": {
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
                            if (!(format.parse((String) colNameValue.get(record[1])).compareTo(min) >= 0 && format.parse((String) colNameValue.get(record[1])).compareTo(max) <= 0))
                                valid = false;
                        } catch (ParseException e) {
                            e.getMessage();
                        }
                        break;
                }
                if (!valid)
                    throw new DBAppException("The value of the field " + record[1] + " is not in the range.\nThe value should be between " + record[5] + " and " + record[6]);
            } else
                throw new DBAppException("The value of the field " + record[1] + " is incompatible.\nThe value should be an instance of " + record[2] + ".");
        }
    }

    private Object[] validateUpdateInput(String tableName, String clusteringKeyValue,
                                         Hashtable<String, Object> columnNameValue) throws IOException, ParseException, DBAppException, ClassNotFoundException {
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

        for (String key : columnNameValue.keySet()) {
            if (!colDataTypes.containsKey(key)) {
                throw new DBAppException("Column does not exist");
            }
            Class colClass = Class.forName(colDataTypes.get(key));
            if (!colClass.isInstance(columnNameValue.get(key))) {
                throw new DBAppException("Incompatible data types");
            }
        }

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
                        throw new DBAppException("Value for column " + key + " is below the minimum allowed");
                    }
                    if (((String) columnNameValue.get(key)).compareTo((String) colMax.get(key)) > 0) {
                        throw new DBAppException("Value for column " + key + " is above the maximum allowed");
                    }
                    break;
            }
        }
        return new Object[]{clusteringCol, clusteringObject};
    }


    public static void main(String[] args) throws DBAppException {

    }
}
