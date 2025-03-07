package xxl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import xxl.exceptions.ImportFileException;
import xxl.exceptions.MissingFileAssociationException;
import xxl.exceptions.UnavailableFileException;
import xxl.exceptions.UnrecognizedEntryException;
import xxl.exceptions.UnrecognizedRangeException;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {

    /** The current spreadsheet. */
    private Spreadsheet _spreadsheet;
    /** The name of the file associated to the current spreadsheet. */
    private String _filename;
    /** The current user. */
    private User _user = new User();

    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, 
                                        MissingFileAssociationException, 
                                                                IOException {

        if (_filename == null || _filename.equals("")) {
			throw new MissingFileAssociationException();
		}

        if (!_spreadsheet.getStatus()) {
            return;
        }

		try (ObjectOutputStream oos = new ObjectOutputStream(
				  new BufferedOutputStream(new FileOutputStream(_filename))) ) {
			oos.writeObject(_spreadsheet);
            _spreadsheet.setStatus(false);
            addSpreadsheetToUser();
		}
    }

    /**
     * Saves the serialized application's state into the specified file. The current network is
     * associated to this file.
     *
     * @param filename the name of the file.
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, 
                                                MissingFileAssociationException, 
                                                                IOException {
        if (filename == null || filename.equals("")) {
			throw new MissingFileAssociationException();
		}
        _filename = filename;
		save();
    }

    /**
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException {
        try (ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(filename))) ) {
			_spreadsheet = (Spreadsheet) ois.readObject();
			_spreadsheet.setStatus(false);
			_filename = filename;
		} catch (IOException | ClassNotFoundException e) {
			throw new UnavailableFileException(filename);
		}
    }


    /**
     * Read text input file and create domain entities..
     *
     * @param filename name of the text input file
     * @throws ImportFileException 
     */

    public void importFile(String filename) throws ImportFileException  {

        String line;

        try (BufferedReader reader = new BufferedReader(
										    new FileReader(filename))){

            String firstLine = reader.readLine();
            String[] parts = firstLine.split("=",2);
            int maxRows = Integer.parseInt(parts[1]);
            String secondLine = reader.readLine();
            parts = secondLine.split("=",2);
            int maxColumns = Integer.parseInt(parts[1]);
            
            _spreadsheet = new Spreadsheet(maxRows,maxColumns);
            
            while ((line = reader.readLine()) != null) {
                
                parts = line.split("\\|",2);
                String rangeCell = parts[0];
                String contentNow = parts[1];

                try {
                    _spreadsheet.insertContents(rangeCell, contentNow);
                } catch (UnrecognizedRangeException e) {
                    //Do nothing, just pass and not insert in this cell
                } catch (UnrecognizedEntryException e) {
                    //Do nothing, just pass and not insert in this cell
                }
            }
        
            reader.close();
        } catch (IOException e) {
            throw new ImportFileException(filename, e);
        }

        
    }

    /**
     * Sees if there is a spreadsheet.
     */
    public Boolean hasSpreadsheetChanged() {
        return _spreadsheet != null && _spreadsheet.getStatus();
    }
    /**
     * Gets the current spreadsheet.
     */

    public Spreadsheet getSpreadsheet(){
        return _spreadsheet;
    }

    /**
    * Sets the current spreadsheet.
    * @param maxRows
    * @param maxCols
    */

    public void setSpreadsheet(int maxRows, int maxCols){
        _spreadsheet = new Spreadsheet(maxRows, maxCols);
        _filename = null;
        addSpreadsheetToUser();
    }

    public void changeUser(String username) {
        User user = new User(username);
        _user = user;
    }

    public void addSpreadsheetToUser() {
        if (_filename != null) {
            _user.addSpreadsheet(_filename, _spreadsheet);
            _spreadsheet.addUser(_user.getName(), _user);
        }
        else { 
            _user.addSpreadsheet(_spreadsheet);
            _spreadsheet.addUser(_user.getName(), _user);
        }
    }
}
