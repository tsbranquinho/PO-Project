package xxl.search;

import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;
import xxl.visitor.Visitor;
import xxl.visitor.VisitorSearchFunction;
import xxl.MapCells;
import xxl.Cell;

public class SearchFunction {
    
    private MapCells _mapCells;
    private String _valueToSearch;
    private Visitor _visitor;

    public SearchFunction(MapCells mapCells, String valueToSearch) {
        _mapCells = mapCells;
        _valueToSearch = valueToSearch;
        _visitor = new VisitorSearchFunction(_valueToSearch);
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
        return sortResult(result);
    }

    public String sortResult(String result) {
        String [] lines = result.split("\n");
        String sortedLines = "";

        //lambda function
        Comparator<String> customComparator = (line1, line2) -> {
            String[] parts1 = line1.split("=");
            String[] parts2 = line2.split("=");

            String function1 = parts1[1].split("\\(")[0];
            String function2 = parts2[1].split("\\(")[0];

            int functionComparison = function1.compareTo(function2);

            if (functionComparison != 0) {
                // If the functions are different, sort by function name
                return functionComparison;
            } else {
                // Parse line and column, it's already in order just to make sure
                String[] coords1 = parts1[0].split("[;|]");
                String[] coords2 = parts2[0].split("[;|]");
                int row1 = Integer.parseInt(coords1[0]);
                int col1 = Integer.parseInt(coords1[1]);
                int row2 = Integer.parseInt(coords2[0]);
                int col2 = Integer.parseInt(coords2[1]);

                if (row1 != row2) {
                    return Integer.compare(row1, row2);
                }   else {
                    return Integer.compare(col1, col2);
                }

            }
            
        };

        Arrays.sort(lines, customComparator);

        for (String line : lines) {
            sortedLines += line + "\n";
        }

        return sortedLines.substring(0, sortedLines.length() - 1);
    }
}