import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //writing table info in MetaData
        try {

            FileReader oldMetaDataFile = new FileReader("src/main/resources/metadata.csv");

            BufferedReader br = new BufferedReader(oldMetaDataFile);
            StringBuilder tableMetaData = new StringBuilder();

            String curLine = "";
            while ((curLine = br.readLine()) != null)
                tableMetaData.append(curLine);

            tableMetaData.append("\n");

            FileWriter metaDataFile = new FileWriter("src/main/resources/metadata.csv");
            for (String colName : colNameType.keySet()) {
                tableMetaData.append(tableName + ",");
                tableMetaData.append(colName + ",");
                tableMetaData.append(colNameType.get(colName) + ",");
                tableMetaData.append((colName.equals(clusteringKey) ? "True" : "False") + ",");
                tableMetaData.append("False,");
                tableMetaData.append(colNameMin.get(colName) + ",");
                tableMetaData.append(colNameMax.get(colName));
                tableMetaData.append("\n");
            }
            metaDataFile.write(tableMetaData.toString());
            metaDataFile.close();
        } catch (IOException e) {

        }
    }

    @Override
    public void createIndex(String tableName, String[] columnNames) throws DBAppException {

    }

    @Override
    public void insertIntoTable(String tableName, Hashtable<String, Object> colNameValue) throws DBAppException, IOException, ParseException {
        //Check the table exists and the row is of the correct types (from metadata file)
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
            throw new DBAppException("There is no such table in the Data Base.");
        }

        validateRecord(tableCols, colNameValue);
    }

    @Override
    public void updateTable(String tableName, String clusteringKeyValue,
                            Hashtable<String, Object> columnNameValue)
            throws DBAppException, IOException, ClassNotFoundException {
        FileReader metadata = new FileReader("src/main/resources/metadata.csv");
        BufferedReader br = new BufferedReader(metadata);
        String curLine = "";
        Hashtable<String, String> colDataTypes = new Hashtable<>();
        String clusteringType = "";

        while((curLine = br.readLine()) != null){
            String[] res = curLine.split(",");
            if(res[0].equals(tableName)){
                colDataTypes.put(res[1], res[2]);
                if(res[3].equals("True")){
                    clusteringType = res[2];
                }
            }
        }

        Object clusteringObject;
        switch (clusteringType){
            case "java.lang.Integer":
                try {
                    clusteringObject = Integer.parseInt(clusteringKeyValue);
                }catch (NumberFormatException e){
                    throw new DBAppException("Incompatible clustering key data type");
                }
                break;
            case "java.lang.Double":
                try {
                    clusteringObject = Double.parseDouble(clusteringKeyValue);
                }catch (NumberFormatException e){
                    throw new DBAppException("Incompatible clustering key data type");
                }
                break;
            case "java.util.Date":
                try {
                    clusteringObject = new SimpleDateFormat("YYYY-MM-DD").parse(clusteringKeyValue);
                }catch (ParseException e){
                    throw new DBAppException("Incompatible clustering key data type");
                }
                break;
            default:
                clusteringObject = clusteringKeyValue;
                break;
        }

        for(String key: columnNameValue.keySet()){
            Class colClass = Class.forName(colDataTypes.get(key));
            if(!colClass.isInstance(columnNameValue.get(key))){
                throw new DBAppException("Incompatible data types");
            }
        }

    }

    @Override
    public void deleteFromTable(String tableName, Hashtable<String, Object> columnNameValue) throws DBAppException {

    }

    @Override
    public Iterator selectFromTable(SQLTerm[] sqlTerms, String[] arrayOperators) throws DBAppException {
        return null;
    }

    private static void validateRecord(ArrayList<String[]> tableCols, Hashtable<String, Object> colNameValue) throws DBAppException, ParseException {
        //still need to check primary key exists
        //still need to check the input include all the fields

        for (String[] record : tableCols) {
            boolean valid = true;
            Class c = colNameValue.get(record[1]).getClass();
            if ((c.getName()).equals(record[2])) {
                if (c.getName().equals("java.lang.Integer")) {
                    int min = Integer.parseInt(record[5]);
                    int max = Integer.parseInt(record[6]);
                    if (!((int) colNameValue.get(record[1]) >= min && (int) colNameValue.get(record[1]) <= max))
                        valid = false;
                } else if (c.getName().equals("java.lang.String")) {
                    String min = record[5];
                    String max = record[6];
                    if (!(((String) colNameValue.get(record[1])).compareTo(min) >= 0 && ((String) colNameValue.get(record[1])).compareTo(max) <= 0))
                        valid = false;
                } else if (c.getName().equals("java.lang.String.Double")) {
                    double min = Double.parseDouble(record[5]);
                    double max = Double.parseDouble(record[6]);
                    if (!((double) colNameValue.get(record[1]) >= min && (double) colNameValue.get(record[1]) <= max))
                        valid = false;
                } else {
                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                        Date min = format.parse(record[5]);
                        Date max = format.parse(record[6]);
                        if (!(format.parse((String) colNameValue.get(record[1])).compareTo(min) >= 0 && format.parse((String) colNameValue.get(record[1])).compareTo(max) <= 0))
                            valid = false;
                    } catch (ParseException e) {
                        e.getMessage();
                    }
                }
                if (!valid)
                    throw new DBAppException("The value of the field " + record[1] + " is not in the range.\nThe value should be between " + record[5] + " and " + record[6]);
            } else
                throw new DBAppException("The value of the field " + record[1] + " is incompatible.\nThe value should be an instance of " + record[2] + ".");
        }
    }


    public static void main(String[] args) throws DBAppException {
        String strTableName = "Student";
        DBApp dbApp = new DBApp();
        Hashtable htblColNameType = new Hashtable();
        htblColNameType.put("id", "java.lang.Integer");
        htblColNameType.put("name", "java.lang.String");
        htblColNameType.put("gpa", "java.lang.double");

        Hashtable htblColName = new Hashtable();

        htblColName.put("id", "0");
        htblColName.put("name", "0");
        htblColName.put("gpa", "0");

        Hashtable<String, String> ht = new Hashtable();
        ht.put("id", "1000");
        ht.put("name", "1000");
        ht.put("gpa", "10000");


        //dbApp.createTable( strTableName, "id", htblColNameType,htblColName,ht);


    }
}
