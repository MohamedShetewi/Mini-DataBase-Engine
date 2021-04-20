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
}
