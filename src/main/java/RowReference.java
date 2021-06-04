import java.io.Serializable;

public class RowReference implements Serializable {


    String Path;
    Object clusteringValue;

    public String getPath() {
        return Path;
    }

    public Object getClusteringValue() {
        return clusteringValue;
    }

    public RowReference(String path, Object clusteringValue) {
        this.Path = path;
        this.clusteringValue = clusteringValue;
    }


}
