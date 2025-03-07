package xxl.content.binary;

import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;
import xxl.content.Content;

public class MulFunction extends BinaryFunction {

    public MulFunction(Content left, Content right,  String specs) {
        super(left, right, specs, "MUL");
    }
    
    public MulFunction(String value, Content left, Content right, String specs) {
        super(value, left, right, specs, "MUL");
    }
    
    public MulFunction shallowCopy() {
        MulFunction c = new MulFunction(getValue(), getLeft(), getRight(), getSpecs());
        return c;
    }

    @Override
    public String accept(Visitor v){
        return v.visitMulFunction(this);
    }

}