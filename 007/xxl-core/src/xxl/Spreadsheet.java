package xxl;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;


import xxl.exceptions.UnrecognizedEntryException;
import xxl.exceptions.UnrecognizedRangeException;

import xxl.content.Content;
import xxl.search.SearchValue;
import xxl.search.SearchFunction;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    /**
	 * Serial number for serialization.
	 */
    private static final long serialVersionUID = 202308312359L;

    /**
	 * Map of all the cells in the Spreadsheet.
	 */
    private MapCells _storeData;
    /**
	 * Max row of the Spreadsheet
	 */
    private int _rowMax;
    /**
	 * Max collumn of the Spreadsheet
	 */
    private int _colMax;
    /**
	 * Tells if changes have been made to the Spreadsheet
	 */
    private boolean _status = false;
    /**
     * CutBuffer is basically a mini-spreadsheet that stores a range of cells requested by the user.
     */
    private CutBuffer _cutBuffer = null;

    private TreeMap<String, User> _users = new TreeMap<>();
    
    /**
	 * Creates a Spreadsheet
	 * 
	 * @param rowMax   max row possible in this Spreadsheet
	 * @param colMax   max column possible in this Spreadsheet
	 */
    public Spreadsheet(int rowMax, int colMax) {

        _rowMax = rowMax;
        _colMax = colMax;
        _storeData = new MapCells(rowMax,colMax);
        _users.put("root", new User());
    }

    /**
     * Returns the max row of the Spreadsheet
     * 
     */
    public int getRowMax() {
        return _rowMax;
    }

    /**
     * Returns the max column of the Spreadsheet
     * 
     */
    public int getColMax() {
        return _colMax;
    }

    /**
     * Returns the MapCells of the Spreadsheet
     * 
     */
    public MapCells getStoreData() {
        return _storeData;
    }

    /**
     * Returns the CutBuffer of the Spreadsheet
     * 
     */
    public CutBuffer getCutBuffer() {
        return _cutBuffer;
    }

    /**
     * Returns the status of the Spreadsheet, if it was changed or not
     * 
     */
    public Boolean getStatus() {
        return _status;
    }

    /**
     * Sets the status of the Spreadsheet
     * 
     * @param status
     */
    public void setStatus(Boolean status) {
        _status = status;
    }

    /**
     * Insert specified content in specified range of the Spreadsheet
     *
     * @param rangeSpecification
     * @param contentSpecification
     */
    
    public void insertContents(String rangeSpecification, String contentSpecification) throws 
                        UnrecognizedRangeException, UnrecognizedEntryException {

        setStatus(true);
        Gamma gamma = new Gamma(rangeSpecification);
        ArrayList<String> coords= gamma.getCoords();

        if (!verifyCell(gamma)) {
            throw new UnrecognizedRangeException(rangeSpecification);
        }

        for (String key : coords) {
            try {
                    _storeData.setCell(key, contentSpecification);
                } catch (UnrecognizedEntryException e) {
                    throw new UnrecognizedEntryException(contentSpecification);
            }              
        }
    }
    /**
     * Turns two ints (row,col) to a key
     *
     * @param row
     * @param col
     */
    public String turnToKey(int row,int col){
        return row+";"+col;
    }

    /**
     * Turns a key to a row
     *
     * @param key
     */
    public int extractRowKey(String key){

        String[] parts = key.split(";");
        int row = Integer.parseInt(parts[0]);
        return row;

    }

    /**
     * Turns a key to a column
     *
     * @param column
     */
    public int extractColumnKey(String key){

        String[] parts = key.split(";");
        int column = Integer.parseInt(parts[1]);
        return column;

    }

    /**
     * Delete the content of a range of cells
     *
     * @param range
     */
    public void deleteContents(String range) throws UnrecognizedRangeException {
            
        setStatus(true);
        Gamma gamma = new Gamma(range);
        ArrayList<String> coords= gamma.getCoords();

        if (!verifyCell(gamma)) { 
            throw new UnrecognizedRangeException(range);
        }

        for (String key : coords) {

            int row = extractRowKey(key);
            int column = extractColumnKey(key);
            _storeData.deleteCell(row, column);
               
        }
        
    }

    /**
     * Gets the boolean value of if the cell is in the spreadsheet or if
     * the interval of cells is a line or column
     * @param coords
     * @param horizontal
     * @param vertical
     */
    public Boolean verifyCell(Gamma coords) {

        boolean horizontal = coords.isHorizontalInterval();
        boolean vertical = coords.isVerticalInterval();

        if ((coords.getRow1() > getRowMax() || coords.getRow1() < 1)
            || (coords.getCol1() > getColMax() || coords.getCol1() < 1)
            || (coords.getRow2() > getRowMax() || coords.getRow2() < 1)
            || (coords.getCol2() > getColMax() || coords.getCol2() < 1)
            || (!horizontal && !vertical) ) {
                return false;
        }
        return true;
    }
    
    /**
     * Gets all trueValues of a cell or a interval of cells and stores it in a
     * string.
     * @param range
     *
     * @throws UnrecognizedRangeException if some cell is out of the Spreadsheet
     */
    public String showCell(String range) throws UnrecognizedRangeException {

        Gamma gamma = new Gamma(range);
        String printCells = "";
        ArrayList<String> coords= gamma.getCoords();

        if (!verifyCell(gamma)) { 
            throw new UnrecognizedRangeException(range);
        }

        for (String key : coords) {

            Cell cell = _storeData.getCell(key);
            printCells += key + cell.toString() + "\n";
               
        }
        return printCells.substring(0,printCells.length()-1);
    }

    /**
     *Create a CutBuffer (array of content) with the range provided by the user.
     *
     * @param range
     *
     * @throws UnrecognizedRangeException if some cell is out of the Spreadsheet
     */
    public void createCutBuffer (String range) throws UnrecognizedRangeException{
        
        Gamma gamma = new Gamma(range); 

        if (!verifyCell(gamma)) { 
            throw new UnrecognizedRangeException(range);
        }
       
        _cutBuffer = new CutBuffer(gamma,_storeData);

    }

    /**
     * Show the CutBuffer
     * 
     */
    public String showCutBuffer(){
        if(_cutBuffer==null)return "";
        return _cutBuffer.toString();
    }

    /**
     * Copy the content of a range of cells from the CutBuffer to the Spreadsheet
     *
     * @param range
     *
     * @throws UnrecognizedRangeException if some gamma is out of the Spreadsheet
     */
    public void paste(String range)  throws UnrecognizedRangeException{

        if(_cutBuffer == null) return;

        Gamma gamma = new Gamma (range);
        Gamma cutBuffGamma = _cutBuffer.getGamma();
        Map<String, Content> contents= _cutBuffer.getContents();
        ArrayList<String> coords= gamma.getCoords();
        ArrayList<String> coordsCutBuff= _cutBuffer.getCoords();
        boolean horizontal = cutBuffGamma.isHorizontalInterval();
        int index = 0;
        
        if (!verifyCell(gamma)) {
            throw new UnrecognizedRangeException(range);
        }

        if(gamma.getOneCell() || gamma.sameDimension(gamma, cutBuffGamma)){
            for (String keyCutBuffer : coordsCutBuff) {
                
                int row = extractRowKey(coords.get(0));
                int col = extractColumnKey (coords.get(0));
                
                if(horizontal){
                    if(col  + index > _colMax) return;
                    String key = turnToKey(row,col+index);
                    _storeData.setCell(key, contents.get(keyCutBuffer)); 
                }
                else{

                    if(row + index> _rowMax ) return;
                    String key = turnToKey(row+index,col);
                    _storeData.setCell(key, contents.get(keyCutBuffer));
    
                }

                index++;
            }
        }
    }

    /**
     * Cut the content of a range of cells from the Spreadsheet to the CutBuffer
     *
     * @param range
     *
     * @throws UnrecognizedRangeException if some gamma is out of the Spreadsheet
     */
    public void cut(String range) throws UnrecognizedRangeException{

        createCutBuffer(range);
        deleteContents(range);
    }

    /**
     * Search for a value in the Spreadsheet
     * 
     * @param value
     * 
     */
    public String searchValue(String value) {
        SearchValue searchValue = new SearchValue(_storeData, value);
        return searchValue.search();
    }

    /**
     * Search for a function in the Spreadsheet
     * 
     * @param value
     * 
     */
    public String searchFunction(String value) {
        SearchFunction searchFunction = new SearchFunction(_storeData, value);
        return searchFunction.search();
    }

    /**
     * Add a User to the Spreadsheet
     * 
     * @param value
     * 
     */
    public void addUser(String username, User user){
        _users.put(username, user);
    }

    /**
     * Remove a User from the Spreadsheet
     * 
     * @param value
     * 
     */
    public void removeUser(String username){
        _users.remove(username);
    }
}
