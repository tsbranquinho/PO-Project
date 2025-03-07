package xxl.app.edit;

//import java.text.Normalizer.Form;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import xxl.Spreadsheet;
import xxl.exceptions.UnrecognizedRangeException;
import xxl.exceptions.UnrecognizedEntryException;

/**
 * Class for inserting data.
 */
class DoInsert extends Command<Spreadsheet> {

    DoInsert(Spreadsheet receiver) {
        super(Label.INSERT, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        String range = Form.requestString(Prompt.address());
        String contents = Form.requestString(Prompt.content());
        try {
            _receiver.insertContents(range, contents);
        }
        catch (UnrecognizedRangeException e) {
            throw new InvalidCellRangeException(range);
        }
        catch (UnrecognizedEntryException e) {
            throw new UnknownFunctionException(contents);
        }
    }

}
