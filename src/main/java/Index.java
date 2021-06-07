import java.io.Serializable;
import java.util.*;

public class Index implements Serializable {
    private String path;
    private final String[] columnNames;
    private final Range[][] columnRanges;
    private final int columnsCount;
    private int numOfBuckets;

    public int getNumOfBuckets() {
        return numOfBuckets;
    }

    public void setNumOfBuckets(int numOfBuckets) {
        this.numOfBuckets = numOfBuckets;
    }

    public Index(String path, String[] columnNames, Hashtable<String, Object> minValPerCol, Hashtable<String, Object> maxValPerCol) {
        this.columnsCount = columnNames.length;
        this.path = path;
        this.columnNames = columnNames;
        columnRanges = new Range[columnsCount][10];
        for (int i = 0; i < columnsCount; i++)
            columnRanges[i] = buildRanges(minValPerCol.get(columnNames[i]), maxValPerCol.get(columnNames[i]));
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    private Range[] buildRanges(Object minVal, Object maxVal) {
        if (minVal instanceof String)
            return buildStringRanges();
        return buildNumbersRanges(minVal, maxVal);
    }

    public static void main(String[] args) {
//        Hashtable<String, Object> max = new Hashtable<>();
//        Hashtable<String, Object> min = new Hashtable<>();
//        max.put("employer", "a");
//        min.put("employer", "aa");
//        min.put("ID", new Date());
//        max.put("ID", new Date(((Date) min.get("ID")).getTime() + 3000));
//        System.out.println(max.get("ID"));
//        Index index = new Index("", new String[]{"employer", "ID"}, min, max);
//        System.out.println(index.getPosition(new Date(new Date().getTime() + 2900), 1));
//        System.out.println(Arrays.toString(index.columnRanges[0]) + "\n" + Arrays.toString(index.columnRanges[1]));
    }

    private Range[] buildNumbersRanges(Object minVal, Object maxVal) {
        Object difference = getDifference(minVal, maxVal);
        Range[] ranges = new Range[10];
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                ranges[i] = new Range((Comparable) minVal, add(minVal, difference));
            } else {
                Comparable min = ranges[i - 1].getMaxVal();
                Comparable max = add(min, difference);
                ranges[i] = new Range(min, max);
            }
        }
        return ranges;
    }

    private Comparable add(Object value, Object toAdd) {
        if (value instanceof Integer)
            return (int) value + (int) toAdd;
        if (value instanceof Double)
            return (double) value + (double) toAdd;
        return new Date(((Date) value).getTime() + (long) toAdd);
    }

    private Object getDifference(Object minVal, Object maxVal) {
        if (minVal instanceof Integer)
            return (((Integer) maxVal - (Integer) minVal) + 9) / 10;
        if (minVal instanceof Double)
            return ((Double) maxVal - (Double) minVal + 1) / 10.0;
        return (((Date) maxVal).getTime() - ((Date) minVal).getTime() + 9) / 10;
    }

    private static Range[] buildStringRanges() {
        Range[] ranges = new Range[10];
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                ranges[i] = new Range("a", "c");
            } else {
                char previous = ((String) ranges[i - 1].getMaxVal()).charAt(0);
                String min = (char) (previous + 1) + "";
                String max = (char) (previous + (i < 6 ? 3 : 2)) + "";
                ranges[i] = new Range(min, max);
            }
        }
        return ranges;
    }

    public Vector<Integer> getPosition(Range r, int column) {
        Vector<Integer> ans = new Vector<>();
        if (r.getMinVal() instanceof String) {
            r.setMinVal(((String) r.getMinVal()).toLowerCase());
            r.setMaxVal(((String) r.getMaxVal()).toLowerCase());
        }
        for (int i = 0; i < 10; i++)
            if (i == 9) {
                if (isInRangeIncInc(columnRanges[column][i], r)) {
                    ans.add(i);
                }
            } else {
                if (isInRangeIncExc(columnRanges[column][i], r))
                    ans.add(i);
            }
        return ans;
    }

    private boolean isInRangeIncExc(Range r1, Range r2) {
        return !(r1.getMaxVal().compareTo(r2.getMinVal()) <= 0 || r1.getMinVal().compareTo(r2.getMaxVal()) > 0);
    }

    private boolean isInRangeIncInc(Range r1, Range r2) {
        return !(r1.getMaxVal().compareTo(r2.getMinVal()) < 0 || r1.getMinVal().compareTo(r2.getMaxVal()) > 0);
    }

    public String getPath() {
        return path;
    }

    public String[] getColumnNames() {
        return columnNames;
    }


    public boolean isSameIndex(Index index) {
        if (index.getColumnNames().length != this.columnNames.length)
            return false;

        for (int i = 0; i < index.getColumnNames().length; i++)
            if (!index.getColumnNames()[i].equals(this.columnNames[i]))
                return false;

        return true;
    }
}
