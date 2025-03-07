package xxl.app.edit;

//import java.text.Normalizer.Form;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedRangeException;

/**
 * Paste command.
 */
class DoPaste extends Command<Spreadsheet> {

    DoPaste(Spreadsheet receiver) {
        super(Label.PASTE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        String range = Form.requestString(Prompt.address());
        
        try {
            _receiver.paste(range);
        } catch (UnrecognizedRangeException e) {
            
            throw new InvalidCellRangeException(range);
        }
    }

}
