package xxl;

import java.io.Serializable;
import java.util.TreeMap;

public class User implements Serializable {

    private String _name;
    private TreeMap<String, Spreadsheet> _spreadsheets = new TreeMap<>();

    public User() {
        _name = "root";
    }

    public User(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public void addSpreadsheet(String filename, Spreadsheet spreadsheet) {
        if (_spreadsheets.containsKey(filename) || _spreadsheets.containsKey("Unsaved")) {
            _spreadsheets.remove(filename);
        }
        _spreadsheets.put(filename, spreadsheet);
    }

    public void addSpreadsheet(Spreadsheet spreadsheet) {
        _spreadsheets.put("Unsaved", spreadsheet);
    }

    public void removeSpreadsheet(String filename) {
        _spreadsheets.remove(filename);
    }

    public Spreadsheet getSpreadsheet(String filename) {
        return _spreadsheets.get(filename);
    }
}