import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowReference that = (RowReference) o;
        return Objects.equals(pagePath, that.pagePath) && Objects.equals(clusteringValue, that.clusteringValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pagePath, clusteringValue);
    }
}
