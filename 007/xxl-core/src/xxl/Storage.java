package xxl;
import java.io.Serializable;
import java.util.ArrayList;

import xxl.exceptions.UnrecognizedEntryException;


public abstract class Storage implements Serializable {

    public abstract Cell getCell(int row, int column);
    public abstract void setCell(String key, String content) throws UnrecognizedEntryException;
    public abstract void setTypeContent(int row, int column, String value) throws UnrecognizedEntryException;
    public abstract void addValue(String value, String coords);
    public abstract void deleteValue(String key, String value);
    public abstract ArrayList<String> getValues(String value);
    public abstract Cell getCell(String key);
}