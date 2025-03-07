package xxl.content;

import xxl.Cell;
import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;

public class ReferenceContent extends Content {

    private Cell _cell;
    private String _reference;

    public ReferenceContent(String ref, Cell cell) {
        
        super();
        _cell = cell;
        _reference = ref.substring(1);
    }

    public ReferenceContent(String value, String ref, Cell cell) {
        
        super(value);
        _cell = cell;
        _reference = ref.substring(1);
    }

    public ReferenceContent shallowCopy() {
        ReferenceContent c = new ReferenceContent(getValue(), "=" + _reference, _cell);
        return c;
    }

    public Cell getCell() {
        return _cell;
    }


    public String accept(Visitor v) {
        return v.visitReferenceContent(this);
    }

    @Override
    public String toString() {
        if (getValue() == null) {
            return "#VALUE=" + _reference;
        }
        return getValue() + "=" + _reference;
    }
}   