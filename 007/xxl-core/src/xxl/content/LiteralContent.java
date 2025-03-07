package xxl.content;

import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;

public class LiteralContent extends Content {


    public LiteralContent(String value) {
        super(value);
    }

    public LiteralContent shallowCopy() {
        LiteralContent c = new LiteralContent(getValue());
        return c;
    }

    public String accept(Visitor v) {
        return v.visitLiteralContent(this);
    }
}