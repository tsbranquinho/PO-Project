package xxl.search;

import java.util.Map;
import xxl.visitor.Visitor;
import xxl.visitor.VisitorSearchValue;
import xxl.MapCells;
import xxl.Cell;

public class SearchValue {
    
    private MapCells _mapCells;
    private String _valueToSearch;
    private Visitor _visitor;

    public SearchValue(MapCells mapCells, String valueToSearch) {
        _mapCells = mapCells;
        _valueToSearch = valueToSearch;
        _visitor = new VisitorSearchValue(_valueToSearch);
    }

    public String search() {
        String result = "";
        for (Map.Entry<String, Cell> entry : _mapCells.getAllCells().entrySet()) {
            Cell cell = entry.getValue();
            String compared = cell.getContent().accept(_visitor);
            if (compared != null) {
                result += entry.getKey() + entry.getValue() + "\n";
            }
        }
        if (result == "") {
            return result;
        }
        return result.substring(0, result.length() - 1);
    }

}