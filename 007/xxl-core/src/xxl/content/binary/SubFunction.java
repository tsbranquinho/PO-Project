package xxl.content.binary;

import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;
import xxl.content.Content;

public class SubFunction extends BinaryFunction {

    public SubFunction(Content left, Content right,  String specs) {
        super(left, right, specs, "SUB");
    }

    public SubFunction(String value, Content left, Content right, String specs) {
        super(value, left, right, specs, "SUB");
    }
    
    public SubFunction shallowCopy() {
        SubFunction c = new SubFunction(getValue(), getLeft(), getRight(), getSpecs());
        return c;
    }

    @Override
    public String accept(Visitor v){
        return v.visitSubFunction(this);
    }
}