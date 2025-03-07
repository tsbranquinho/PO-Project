package xxl;

import java.util.TreeMap;
import xxl.content.Content;
import java.util.Map;
import java.util.ArrayList;

import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;
import xxl.content.LiteralContent;
import xxl.content.ReferenceContent;
import xxl.content.Functions;
import xxl.content.binary.AddFunction;
import xxl.content.binary.DivFunction;
import xxl.content.binary.MulFunction;
import xxl.content.binary.SubFunction;
import xxl.content.nonbinary.AverageFunction;
import xxl.content.nonbinary.CoalesceFunction;
import xxl.content.nonbinary.ConcatFunction;
import xxl.content.nonbinary.ProductFunction;
import xxl.exceptions.UnrecognizedEntryException;

public class MapCells extends Storage {
    private Map<String, Cell> _cells;
    private Map<String, ArrayList<String>> _value; 
    private int _rowMax;
    private int _colMax;

    public MapCells(int rowMax, int colMax) {
        _cells = new TreeMap<>();
        _value = new TreeMap<>();
        _rowMax = rowMax;
        _colMax = colMax;
        for (int i = 1; i <= rowMax; i++) {
            for (int j = 1; j <= colMax; j++) {
                String cellKey = String.format("%d;%d", i, j);
                _cells.put(cellKey, new Cell());
            }
        }
    }

    public ArrayList<String> getValues(String value) {
        return _value.get(value);
    }
    
    
    public void deleteValue(String key, String value) {
        ArrayList<String> array = _value.get(key);
        if (array != null) {
            array.remove(value);
            if (array.isEmpty()) {
                _value.remove(key);
            }
        }
    }

    public void addValue(String value, String coords) {
        String valueKey = String.format(value);
        _value.computeIfAbsent(valueKey, k -> new ArrayList<>()).add(coords);
    }

    public Cell getCell(String key){
        return _cells.get(key);
    }

    public Cell getCell(int row, int column) {
        String cellKey = String.format("%d;%d", row, column);
        return _cells.get(cellKey);
    }

    public Map<String, Cell> getAllCells() {
        return _cells;
    }

    public void setCell(String key, String content) throws UnrecognizedEntryException{
        String[] parts = key.split(";");
        int row = Integer.parseInt(parts[0]);
        int column = Integer.parseInt(parts[1]);
        try {

            Cell cell = getCell(row, column);
            cell.getContent().accept(new VisitorRemoveObserver(cell));
            setTypeContent(row, column, content);
        } catch (UnrecognizedEntryException e) {
            throw new UnrecognizedEntryException(content);
        }
    }

    public void setCell(String key, Content content) { //for paste process
        Cell cell = getCell(key);
        cell.getContent().accept(new VisitorRemoveObserver(cell));
        cell.setContent(content);
        cell.getContent().accept(new VisitorAddObserver(cell));
        cell.evaluateCell();
    }


    public void deleteCell(int row, int column) {
        //for pure deletion process, so to not evaluation process
        Cell cell = getCell(row, column);
        cell.getContent().accept(new VisitorRemoveObserver(cell));
        cell.deleteContent();
    }

    public void setTypeContent(int row, int column, String value) throws UnrecognizedEntryException {

        Cell cell = getCell(row,column);
        
        if (value.isEmpty()) {
            cell.deleteContent(); // Sets a empty cell
        } else if (value.charAt(0) == '=') {
            cell.setInicialize();
            if (Character.isDigit(value.charAt(1))) {
                // Sets a Reference
                String[] parts = value.substring(1).split(";");
                Cell cell_ref = getCell(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                cell.setContent(value, cell_ref);
            } else {
                // Sets a Function
                Functions content = new Functions(value); // to set the function
                try {
                    Content newContent = setFunctionContent(value, content);
                    cell.setContent(newContent);
                } catch (UnrecognizedEntryException e) {
                    throw new UnrecognizedEntryException(value);
                }
            }
        } else {
            cell.setInicialize();
            cell.setContent(value);
            
        }
        cell.getContent().accept(new VisitorAddObserver(cell));
        cell.evaluateCell();
    }

    public void setNonBinary(){
        
    }

    public void setBinary(){
        
    }
    public Content setFunctionContent(String value, Functions function) throws UnrecognizedEntryException{
        String[] specification = value.split("\\(");
        specification[1] = specification[1].substring(0, specification[1].length() - 1);
        Content content;
        Content left = null;
        Content right = null;
        ReferenceContent [] nonBinaryArg = null;
        
        String[] binaryParts = specification[1].split(",");
        if (binaryParts.length == 2) {
            String[] binaryArgLeft  = binaryParts[0].split(";");
            String[] binaryArgRight  = binaryParts[1].split(";");
            left = getArgBinary(binaryParts, binaryArgLeft);
            right = getArgBinary(binaryParts, binaryArgRight);
        }
        else {
            String nonBinaryParts = specification[1];
            nonBinaryArg = getArgNonBinary(nonBinaryParts);
        }
        switch (function.checkFunction(value)) {
            case 1:
                content = (AddFunction) new AddFunction(left, right, specification[1]);
                return content;
            case 2:
                content = (SubFunction) new SubFunction(left, right, specification[1]);
                return content;
            case 3:
                content = (MulFunction) new MulFunction(left, right, specification[1]);
                return content;
            case 4:
                content = (DivFunction) new DivFunction(left, right, specification[1]);
                return content;
            case 5:
                content = (AverageFunction) new AverageFunction(nonBinaryArg, specification[1]);
                return content;
            case 6:
                content = (ProductFunction) new ProductFunction(nonBinaryArg, specification[1]);
                return content;
            case 7:
                content = (ConcatFunction) new ConcatFunction(nonBinaryArg, specification[1]);
                return content;
            case 8:
                content = (CoalesceFunction) new CoalesceFunction(nonBinaryArg, specification[1]);
                return content;
            default:
                throw new UnrecognizedEntryException(value);
        }
    }

    public Content getArgBinary(String [] binaryParts, String [] binaryArgSide) {
        if (binaryArgSide.length == 1) {
            LiteralContent side = new LiteralContent(binaryArgSide[0]);
            return side;
        }
        else {
            Cell cellRef = getCell(Integer.parseInt(binaryArgSide[0]), Integer.parseInt(binaryArgSide[1]));
            ReferenceContent side = new ReferenceContent(binaryParts[0], cellRef);
            return side;
        }
    }

    public ReferenceContent[] getArgNonBinary(String nonBinaryParts) {
        Gamma gamma = new Gamma(nonBinaryParts);
        Boolean horizontal = gamma.isHorizontalInterval();
        Boolean vertical = gamma.isVerticalInterval();
        if ((!horizontal && !vertical) || gamma.getCol1() <= 0 || gamma.getRow1() <= 0
        || gamma.getCol2() > _colMax || gamma.getRow2() > _rowMax ) {
            //case where it's not a valid gamma (arguments invalid - #VALUE)
            ReferenceContent[] refs = new ReferenceContent[1];
            Cell cellRef = new Cell();
            refs[0] = new ReferenceContent(nonBinaryParts, cellRef);
            return refs;
        }
        int pos = 0;

        if (horizontal) {
            ReferenceContent[] refs = new ReferenceContent[gamma.getCol2() - gamma.getCol1() + 1];
            
            for (int i = gamma.getCol1(); i <= gamma.getCol2(); i++) {
                Cell cellRef = getCell(gamma.getRow1(), i);
                refs[pos] = new ReferenceContent(nonBinaryParts, cellRef);
                pos++;
            }
            return refs;
        }
        else {
            ReferenceContent[] refs = new ReferenceContent[gamma.getRow2() - gamma.getRow1() + 1];

            for (int i = gamma.getRow1(); i <= gamma.getRow2(); i++) {
                Cell cellRef = getCell(i, gamma.getCol1());
                refs[pos] = new ReferenceContent(nonBinaryParts, cellRef);
                pos++;
            }
            return refs;
        }

    }
}