package xxl.app.search;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import xxl.Spreadsheet;

/**
 * Command for searching function names.
 */
class DoShowFunctions extends Command<Spreadsheet> {

    DoShowFunctions(Spreadsheet receiver) {
        super(Label.SEARCH_FUNCTIONS, receiver);
    }

    @Override
    protected final void execute() {
        String value = Form.requestString(Prompt.searchFunction());
        _display.popup(_receiver.searchFunction(value));
    }

}
