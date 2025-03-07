package xxl.app.search;

//import java.text.Normalizer.Form;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import xxl.Spreadsheet;

/**
 * Command for searching content values.
 */
class DoShowValues extends Command<Spreadsheet> {

    DoShowValues(Spreadsheet receiver) {
        super(Label.SEARCH_VALUES, receiver);
    }

    @Override
    protected final void execute() {
         String value = Form.requestString(Prompt.searchValue());
         _display.popup(_receiver.searchValue(value));
    }

}
