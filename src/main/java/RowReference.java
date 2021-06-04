import java.io.Serializable;

public class RowReference implements Serializable {


    private String pagePath;
    private Object clusteringValue;

    public RowReference(String pagePath, Object clusteringValue) {
        this.pagePath = pagePath;
        this.clusteringValue = clusteringValue;
    }

    public String getPagePath() {
        return pagePath;
    }

    public Object getClusteringValue() {
        return clusteringValue;
    }
}
