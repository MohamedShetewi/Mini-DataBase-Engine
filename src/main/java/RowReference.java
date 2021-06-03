import java.io.Serializable;

public class RowReference implements Serializable {


    String pagePath;
    Object clusteringValue;

    public RowReference(String pagePath, Object clusteringValue) {
        this.pagePath = pagePath;
        this.clusteringValue = clusteringValue;
    }


}
