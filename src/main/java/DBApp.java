import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DBApp implements DBAppInterface {
    @Override
    public void init() {

    }

    @Override
    public void createTable(String tableName, String clusteringKey, Hashtable<String, String> colNameType, Hashtable<String, String> colNameMin, Hashtable<String, String> colNameMax) throws DBAppException {

        File tableDirectory = new File("src/main/resources/Tables/" + tableName);

        if(tableDirectory.exists())
            throw new DBAppException();
        else
            tableDirectory.mkdir();

        Table tableInstance = new Table(tableName);

        try
        {
            FileOutputStream serializedFile = new FileOutputStream("src/main/resources/Tables/"+tableName+".ser");
            ObjectOutputStream out = new ObjectOutputStream(serializedFile);
            out.writeObject(tableInstance);
            out.close();
            serializedFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //validating input
        for(String col : colNameType.keySet())
            if(!colNameMax.containsKey(col) || !colNameMin.containsKey(col))
                throw new DBAppException();

        for(String col : colNameMax.keySet())
            if(!colNameType.containsKey(col) || !colNameMin.containsKey(col))
                throw new DBAppException();

        for(String col : colNameMin.keySet())
            if(!colNameType.containsKey(col) || !colNameMax.containsKey(col))
                throw new DBAppException();



        //writing table info in MetaData
        try {

            FileWriter metaDataFile = new FileWriter("src/main/resources/metadata.csv");

            StringBuilder tableMetaData = new StringBuilder();
            for (String colName :  colNameType.keySet())
            {
                tableMetaData.append(tableName+",");
                tableMetaData.append(colName+",");
                tableMetaData.append(colNameType.get(colName)+",");
                tableMetaData.append((colName.equals(clusteringKey)?"True":"False") + ",");
                tableMetaData.append("False,");
                tableMetaData.append(colNameMin.get(colName)+",");
                tableMetaData.append(colNameMax.get(colName)+",");
                tableMetaData.append("\n");
            }
            metaDataFile.write(tableMetaData.toString());
            metaDataFile.close();
        }
        catch (IOException e)
        {

        }
    }


    @Override
    public void createIndex(String tableName, String[] columnNames) throws DBAppException {

    }

    @Override
    public void insertIntoTable(String tableName, Hashtable<String, Object> colNameValue) throws DBAppException {

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

    public static void main(String[] args) throws DBAppException, IOException, ClassNotFoundException {
        DBApp dbApp = new DBApp();
        dbApp.updateTable("", "", new Hashtable<>());
    }
}
