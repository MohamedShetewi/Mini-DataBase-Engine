import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;

public class DBApp implements DBAppInterface{


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
    public void updateTable(String tableName, String clusteringKeyValue, Hashtable<String, Object> columnNameValue) throws DBAppException {

    }

    @Override
    public void deleteFromTable(String tableName, Hashtable<String, Object> columnNameValue) throws DBAppException {

    }

    @Override
    public Iterator selectFromTable(SQLTerm[] sqlTerms, String[] arrayOperators) throws DBAppException {
        return null;
    }

}
