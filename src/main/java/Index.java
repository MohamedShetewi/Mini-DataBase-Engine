import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

public class Index implements Serializable {
    private int columnsCount;
    private String path;
    private String[] columnNames;
    private Range[][] columnRanges;

    public Index(String path, String[] columnNames, Hashtable<String, Object> minValPerCol, Hashtable<String, Object> maxValPerCol) {
        this.columnsCount = columnNames.length;
        this.path = path;
        this.columnNames = columnNames;
        columnRanges = new Range[columnsCount][10];
        for (int i = 0; i < columnsCount; i++)
            columnRanges[i] = buildRanges(minValPerCol.get(columnNames[i]), maxValPerCol.get(columnNames[i]));
    }

    private Range[] buildRanges(Object minVal, Object maxVal) {

        return null;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    static class Range implements Serializable {
        Object minVal, maxVal;

        public Range(Object minVal, Object maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }


}
