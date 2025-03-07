package xxl.content.nonbinary;

import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;
import xxl.content.ReferenceContent;

public class AverageFunction extends NonBinaryFunction {

    public AverageFunction(ReferenceContent [] references, String specs) {
        super(references, specs, "AVERAGE");
    }

    public AverageFunction(String value, ReferenceContent [] references, String specs) {
        super(value, references, specs, "AVERAGE");
    }
    
    public AverageFunction shallowCopy() {
        AverageFunction c = new AverageFunction(getValue(), getAllReferences(), getSpecs());
        return c;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitAverageFunction(this);
    }

} 