import java.io.Serializable;

public class RowReference implements Serializable {


    int pageNum;
    Object clusteringValue;

    public RowReference(int pageNum, Object clusteringValue) {
        this.pageNum = pageNum;
        this.clusteringValue = clusteringValue;
    }


}
