import java.io.Serializable;
import java.util.Vector;

public class Cell implements Serializable {
    private int numOfBuckets;
    private Vector<Bucket> buckets;

    public Cell(){
        buckets = new Vector<>();
    }

    public int getNumOfBuckets() {
        return numOfBuckets;
    }

    public Vector<Bucket> getBuckets() {
        return buckets;
    }

    public void setNumOfBuckets(int numOfBuckets) {
        this.numOfBuckets = numOfBuckets;
    }
}
