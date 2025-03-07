package xxl;
import java.util.ArrayList;

public class Gamma {
    
    private int _row1;
    private int _col1;
    private int _row2;
    private int _col2;
    private boolean _oneCell = false;
    private ArrayList<String>  _coords = new ArrayList<>();

    public Gamma(String input) {
    
        String[] parts = input.split(":");

        if (parts.length == 1) {
            
            parseCell(parts[0]);
        } else if (parts.length == 2) {
            
            parseInterval(parts[0], parts[1]);
        }

        
        swapIntervals(_col1,_col2,_row1,_row2);

        if (isHorizontalInterval()) {
            for (int i = getCol1(); i <=getCol2(); i++) {
                _coords.add(getRow1() + ";" + i);
                
            }
        } else {
            for (int i = getRow1(); i <= getRow2(); i++) {
                _coords.add(i + ";" + getCol1());
            }
        }

    }

    private void parseCell(String cell) {
        String[] coordinates = cell.split(";");

        _row1 = _row2 = Integer.parseInt(coordinates[0]);
        _col1 = _col2 = Integer.parseInt(coordinates[1]);
        _oneCell = true;
    }

    private void parseInterval(String start, String end) {
        String[] startCoords = start.split(";");
        String[] endCoords = end.split(";");

        _row1 = Integer.parseInt(startCoords[0]);
        _col1 = Integer.parseInt(startCoords[1]);
        _row2 = Integer.parseInt(endCoords[0]);
        _col2 = Integer.parseInt(endCoords[1]);

    }

    public void swapIntervals(int col1,int col2,int row1,int row2){
        int temp;
        if (_col1 > _col2) {
            temp = _col1;
            _col1 = _col2;
            _col2 = temp;
        }

        if(_row1 > _row2){
            temp = _row1;
            _row1 = _row2;
            _row2 = temp;
        }
    }

    public int getRow1() {
        return _row1;
    }

    public int getCol1() {
        return _col1;
    }

    public int getRow2() {
        return _row2;
    }

    public int getCol2() {
        return _col2;
    }
    
    public boolean getOneCell(){
        return _oneCell;
    }
    
    public ArrayList<String> getCoords() {
        return _coords;
    }

    public boolean isHorizontalInterval() {
        if (_oneCell)
            return true; //handles case of a single cell
        return _row1 == _row2;
    }

    public boolean isVerticalInterval() {
        if (_oneCell)
            return false; //handles case of a single cell
        return _col1 == _col2;
    }

    public boolean sameDimension(Gamma g1, Gamma g2) {
        
        //The first number is the number of Collumn or Row the second is the number of the gama
        int col11 = g1.getCol1();
        int col21 = g1.getCol2();
        int row11 = g1.getRow1();
        int row21 = g1.getRow2();
    
        int col12 = g2.getCol1();
        int col22 = g2.getCol2();
        int row12 = g2.getRow1();
        int row22 = g2.getRow2();
        
        return col21 - col11 == col22 - col12 && row21 - row11 == row22-row12;
    }

    
}