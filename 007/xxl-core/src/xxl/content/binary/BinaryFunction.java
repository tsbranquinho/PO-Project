package xxl.content.binary;

import xxl.content.Functions;
import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;
import xxl.content.Content;

public abstract class BinaryFunction extends Functions {

    private Content _left;
    private Content _right;

    public BinaryFunction(Content left, Content right, String specs, String type) {

        super(specs, type);
        _left = left;
        _right = right;
    }

    public BinaryFunction(String value, Content left, Content right, String specs, String type) {

        super(value, specs, type);
        _left = left;
        _right = right;
    }
    
    public abstract BinaryFunction shallowCopy();
    public abstract String accept(Visitor visitor);

    public Content getLeft() { 
        return _left; 
    }
    public Content getRight() { 
        return _right; 
    }

    public String getSpecs() {
        return super.getSpecs();
    }

}