import java.util.Date;

public class SQLTerm implements Comparable<Object> {


    private String tableName;
    private String columnName;
    private String operator;
    private Object value;

    public SQLTerm(String tableName, String columnName, String operator, String value) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.operator = operator;
        this.value = value;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        if (this.getValue() instanceof java.util.Date)
            return ((Date) this.getValue()).compareTo((Date) o);
        else if (this.getValue() instanceof java.lang.Integer)
            return ((Integer) this.getValue()).compareTo((Integer) o);
        else if (this.getValue() instanceof java.lang.Double)
            return ((Double) this.getValue()).compareTo((Double) o);
        else if (this.getValue() instanceof java.lang.String)
            return ((String) this.getValue()).compareTo((String) o);
        return 0;

    }
}
