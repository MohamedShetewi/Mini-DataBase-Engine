import java.io.*;
import java.util.Vector;

public class Table implements Serializable {

    private String tableName;
    private Vector<Page> pages;
    private Vector<Index> indices;
    private int pagesCounter;
    private int indexCounter;




    public Table(String tableName) {
        this.tableName = tableName;
        this.pages = new Vector<>();
        this.indices = new Vector<>();

    }

    public int getIndexCounter() {
        return indexCounter;
    }

    public void setIndexCounter(int indexCounter) {
        this.indexCounter = indexCounter;
    }

    public Vector<Index> getIndices() {
        return indices;
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
