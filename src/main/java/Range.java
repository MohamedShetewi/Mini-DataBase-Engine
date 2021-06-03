import java.io.Serializable;

public class Range implements Serializable {
    Comparable minVal, maxVal;



    public Comparable getMinVal() {
        return minVal;
    }

    public Comparable getMaxVal() {
        return maxVal;
    }

    public Range(Comparable minVal, Comparable maxVal) {
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    @Override
    public String toString() {
        return "Range{" +
                "minVal=" + minVal +
                ", maxVal=" + maxVal +
                '}';
    }
}