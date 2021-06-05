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

    public Page getPageByPath(String path) {
        for (Page p : pages)
            if (p.getPath().equals(path))
                return p;
        return null;
    }

    public String getTableName() {
        return tableName;
    }

    public Vector<Page> getPages() {
        return pages;
    }

    public int getPagesCounter() {
        return pagesCounter;
    }

    public void setPagesCounter(int pagesCounter) {
        this.pagesCounter = pagesCounter;
    }

}
