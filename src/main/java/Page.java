import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;

public class Page {
    private String path;
    private Object minClusteringValue;
    private Object maxClusteringValue;
    private int numOfRecords;

    public Page(String path, int numOfRecords) {
        this.path = path;
        this.numOfRecords = numOfRecords;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getMinClusteringValue() {
        return minClusteringValue;
    }

    public void setMinClusteringValue(Object minClusteringValue) {
        this.minClusteringValue = minClusteringValue;
    }

    public Object getMaxClusteringValue() {
        return maxClusteringValue;
    }

    public void setMaxClusteringValue(Object maxClusteringValue) {
        this.maxClusteringValue = maxClusteringValue;
    }

    public int getNumOfRecords() {
        return numOfRecords;
    }

    public void setNumOfRecords(int numOfRecords) {
        this.numOfRecords = numOfRecords;
    }

    /**
     * deletes all rows that matches ALL of the specified entries(AND operator) from the page
     * returns 0 if no rows are deleted,-1 if all rows are deleted and 1 if some (but not all) rows are deleted
     * @param columnNameValue the column key-value pairs to which records will be compared with
     * @param clusteringKey   the clustering key of the page's table
     * @throws ClassNotFoundException If an error occurred in the stored table pages format
     * @throws IOException            If an I/O error occurred
     * @throws DBAppException         If an an error occurred in the table(table not found,types don't match,...)
     */
    public int delete(Hashtable<String, Object> columnNameValue, String clusteringKey) throws IOException, ClassNotFoundException {
        //read the page from disk
        FileInputStream serializedFile = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(serializedFile);
        Vector<Hashtable<String, Object>> rows = (Vector<Hashtable<String, Object>>) in.readObject();
        in.close();
        serializedFile.close();
        int state = 0;//return state
        //iterate through the page to compare
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
        if (rows.isEmpty())
            return -1;
        //update the max and min clustering keys
        setMaxClusteringValue(rows.get(rows.size() - 1).get(clusteringKey));
        setMinClusteringValue(rows.get(0).get(clusteringKey));
        return state;
    }

    /**
     * compares two objects assuming they are an instance of the same class
     */
    public int compare(Object object1, Object object2) {
        if (object1 instanceof java.lang.Integer) {
            return ((Integer) object1).compareTo((Integer) object2);
        } else if (object1 instanceof java.lang.Double) {
            return ((Double) object1).compareTo((Double) object2);
        } else if (object1 instanceof java.util.Date) {
            return ((Date) object1).compareTo((Date) object2);
        } else {
            return ((String) object1).compareTo((String) object2);
        }
    }
}
