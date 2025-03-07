package xxl.app.edit;

//import java.text.Normalizer.Form;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedRangeException;

/**
 * Copy command.
 */
class DoCopy extends Command<Spreadsheet> {

    DoCopy(Spreadsheet receiver) {
        super(Label.COPY, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        String range = Form.requestString(Prompt.address());

        try {
            _receiver.createCutBuffer(range);
        } catch (UnrecognizedRangeException e) {
            
            throw new InvalidCellRangeException(range);
        }
    }

}


