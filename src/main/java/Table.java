import java.io.*;
import java.util.Vector;

public class Table implements Serializable {
    private String tableName;
    private Vector<Page> pages;
    private int pagesCounter;

    public Table(String tableName) {
        this.tableName = tableName;
        pages = new Vector<>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Vector<Page> getPages() {
        return pages;
    }

    public void setPages(Vector<Page> pages) {
        this.pages = pages;
    }

    public int getPagesCounter() {
        return pagesCounter;
    }

    public void setPagesCounter(int pagesCounter) {
        this.pagesCounter = pagesCounter;
    }

}
