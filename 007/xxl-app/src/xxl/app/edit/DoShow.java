package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import xxl.exceptions.UnrecognizedRangeException;
import xxl.Spreadsheet;


/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

    DoShow(Spreadsheet receiver) {
        super(Label.SHOW, receiver);
    }

    @Override
    protected final void execute() throws CommandException  {

        String range = Form.requestString(Prompt.address());
        
        try {
                _display.popup(_receiver.showCell(range));
                
        }catch (UnrecognizedRangeException e) {
            throw new InvalidCellRangeException(range);
        }
    }
}
