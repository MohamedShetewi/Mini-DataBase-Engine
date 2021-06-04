import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class Bucket implements Serializable{

    public String getPath() {
        return path;
    }

    private String path;
    private int numOfRecords;

    public Bucket(String path)
    {
        this.path = path;
    }

    public int getNumOfRecords() {
        return numOfRecords;
    }

    public void setNumOfRecords(int numOfRecords) {
        this.numOfRecords = numOfRecords;
    }


}
