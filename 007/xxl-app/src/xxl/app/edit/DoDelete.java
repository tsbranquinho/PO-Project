package xxl.app.edit;

//import java.text.Normalizer.Form;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedRangeException;
import pt.tecnico.uilib.forms.Form;

/**
 * Delete command.
 */
class DoDelete extends Command<Spreadsheet> {

    DoDelete(Spreadsheet receiver) {
        super(Label.DELETE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        String range = Form.requestString(Prompt.address());
        try {
            _receiver.deleteContents(range);
        }
        catch (UnrecognizedRangeException e) {
            throw new InvalidCellRangeException(range);
        }
    }

}
