package xxl.content.binary;

import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;

import xxl.content.Content;

public class AddFunction extends BinaryFunction {

    public AddFunction(Content left, Content right,  String specs) {
        super(left, right, specs, "ADD");
    }

    public AddFunction(String value, Content left, Content right, String specs) {
        super(value, left, right, specs, "ADD");
    }

    public AddFunction shallowCopy() {
        AddFunction c = new AddFunction(getValue(),getLeft(), getRight(), getSpecs());
        return c;
    }

    @Override
    public String accept(Visitor v){
        return v.visitAddFunction(this);
    }

}