import java.util.Date;

public class SQLTerm implements Comparable<Object> {


    public String _strTableName;
    public String _strColumnName;
    public String _strOperator;
    public Object _objValue;

    public SQLTerm(){

    }

    public String get_strTableName() {
        return _strTableName;
    }

    public String get_strColumnName() {
        return _strColumnName;
    }

    public String get_strOperator() {
        return _strOperator;
    }

    public Object get_objValue() {
        return _objValue;
    }

    @Override
    public int compareTo(Object o) {
        if (this.get_objValue() instanceof java.util.Date)
            return ((Date) this.get_objValue()).compareTo((Date) o);
        else if (this.get_objValue() instanceof java.lang.Integer)
            return ((Integer) this.get_objValue()).compareTo((Integer) o);
        else if (this.get_objValue() instanceof java.lang.Double)
            return ((Double) this.get_objValue()).compareTo((Double) o);
        else if (this.get_objValue() instanceof java.lang.String)
            return ((String) this.get_objValue()).toLowerCase().compareTo(((String) o).toLowerCase());
        return 0;

    }
}
