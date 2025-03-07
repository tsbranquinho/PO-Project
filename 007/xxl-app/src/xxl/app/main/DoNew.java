package xxl.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import xxl.Calculator;
import xxl.exceptions.MissingFileAssociationException;

/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

    DoNew(Calculator receiver) {
        super(Label.NEW, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        if (_receiver.hasSpreadsheetChanged()) {
            Boolean answer = Form.confirm(Prompt.saveBeforeExit());
            if (answer) {
                DoSave save = new DoSave(_receiver);
                save.execute();
            }
        }
        int rows = Form.requestInteger(Prompt.lines());
        int columns = Form.requestInteger(Prompt.columns());
        _receiver.setSpreadsheet(rows, columns);
    }
}
