import java.util.Hashtable;
import java.util.Vector;

public class Index {

    private String path;
    private String[] columnNames;
    private Range[] columnRanges;

    public String[] getColumnNames() {
        return columnNames;
    }

    public Index(String path, String[]columnNames, Hashtable<String,Object > minValPerCol, Hashtable<String, Object>maxValPerCol )
    {

        this.path = path;
        this.columnNames = columnNames;

        for(int i = 0; i < columnNames.length ; i++)
            columnRanges[i] = new Range(minValPerCol.get(columnNames[i]), maxValPerCol.get(columnNames[i]));
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    static class Range{
        Object minVal, maxVal;

        public Range(Object minVal, Object maxVal) {
            this.minVal = minVal;
            this.maxVal = maxVal;
        }
    }


}
