package xxl.content.nonbinary;

import xxl.content.ReferenceContent;
import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;

public class ConcatFunction extends NonBinaryFunction {

    public ConcatFunction(ReferenceContent [] references, String specs) {
        super(references, specs, "CONCAT");
    }

    public ConcatFunction(String value, ReferenceContent [] references, String specs) {
        super(value, references, specs, "CONCAT");
    }
    
    public ConcatFunction shallowCopy() {
        ConcatFunction c = new ConcatFunction(getValue(), getAllReferences(), getSpecs());
        return c;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitConcatFunction(this);
    }
} 