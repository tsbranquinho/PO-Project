package xxl.content.nonbinary;

import xxl.content.ReferenceContent;
import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;

public class CoalesceFunction extends NonBinaryFunction {

    public CoalesceFunction(ReferenceContent [] references, String specs) {
        super(references, specs, "COALESCE");
    }

    public CoalesceFunction(String value, ReferenceContent [] references, String specs) {
        super(value, references, specs, "COALESCE");
    }
    
    public CoalesceFunction shallowCopy() {
        CoalesceFunction c = new CoalesceFunction(getValue(), getAllReferences(), getSpecs());
        return c;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCoalesceFunction(this);
    }
}   