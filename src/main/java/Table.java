import java.io.Serializable;
import java.util.Vector;

public class Table implements Serializable {
   private String tableName;
   private Vector<Integer> minPrimaryKey;
   private Vector<Integer> maxPrimaryKey;

    public Table(String tableName)
    {
        this.tableName = tableName;
        minPrimaryKey = new Vector<>();
        maxPrimaryKey = new Vector<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Vector<Integer> getMinPrimaryKey() {
        return minPrimaryKey;
    }

    public void setMinPrimaryKey(Vector<Integer> minPrimaryKey) {
        this.minPrimaryKey = minPrimaryKey;
    }

    public Vector<Integer> getMaxPrimaryKey() {
        return maxPrimaryKey;
    }

    public void setMaxPrimaryKey(Vector<Integer> maxPrimaryKey) {
        this.maxPrimaryKey = maxPrimaryKey;
    }
}
