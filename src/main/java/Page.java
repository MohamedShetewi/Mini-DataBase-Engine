import java.io.*;
import java.util.*;

public class Page implements Serializable{
    private String path;
    private Object minClusteringValue;
    private Object maxClusteringValue;
    private int numOfRecords;

    public Page(String path) {
        this.path = path;
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
