package xxl.content.binary;

import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;
import xxl.content.Content;

public class DivFunction extends BinaryFunction {

    public DivFunction(Content left, Content right,  String specs) {
        super(left, right, specs, "DIV");
    }

    public DivFunction(String value, Content left, Content right, String specs) {
        super(value, left, right, specs, "DIV");
    }

    public DivFunction shallowCopy() {
        DivFunction c = new DivFunction(getValue(), getLeft(), getRight(), getSpecs());
        return c;
    }

    @Override
    public String accept(Visitor v){
        return v.visitDivFunction(this);
    }
}