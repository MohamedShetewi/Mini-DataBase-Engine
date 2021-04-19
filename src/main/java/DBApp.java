import com.sun.tools.javac.Main;

import java.io.*;
import java.util.Hashtable;
import java.util.Iterator;

public class DBApp implements DBAppInterface{


    @Override
    public void init() {

    }

    @Override
    public void createTable(String tableName, String clusteringKey, Hashtable<String, String> colNameType, Hashtable<String, String> colNameMin, Hashtable<String, String> colNameMax) throws DBAppException {

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


        //writing table info in MetaData
        try {

            FileReader oldMetaDataFile = new FileReader("src/main/resources/metadata.csv");

            BufferedReader br = new BufferedReader(oldMetaDataFile);
            StringBuilder tableMetaData = new StringBuilder();

            String curLine = "";
            while ((curLine = br.readLine())!=null)
                tableMetaData.append(curLine);

            tableMetaData.append("\n");

            FileWriter metaDataFile = new FileWriter("src/main/resources/metadata.csv");
            for (String colName :  colNameType.keySet())
            {
                tableMetaData.append(tableName+",");
                tableMetaData.append(colName+",");
                tableMetaData.append(colNameType.get(colName)+",");
                tableMetaData.append((colName.equals(clusteringKey)?"True":"False") + ",");
                tableMetaData.append("False,");
                tableMetaData.append(colNameMin.get(colName)+",");
                tableMetaData.append(colNameMax.get(colName));
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


    public static void main(String[] args) throws DBAppException {
        String strTableName = "Student";
        DBApp dbApp = new DBApp( );
        Hashtable htblColNameType = new Hashtable( );
        htblColNameType.put("id", "java.lang.Integer");
        htblColNameType.put("name", "java.lang.String");
        htblColNameType.put("gpa", "java.lang.double");

        Hashtable htblColName = new Hashtable( );

        htblColName.put("id","0");
        htblColName.put("name", "0");
        htblColName.put("gpa", "0");

        Hashtable<String,String> ht = new Hashtable();
        ht.put("id","1000");
        ht.put("name", "1000");
        ht.put("gpa", "10000");



         //dbApp.createTable( strTableName, "id", htblColNameType,htblColName,ht);


        
    }
}
