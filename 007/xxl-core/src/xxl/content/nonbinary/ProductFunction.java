package xxl.content.nonbinary;

import xxl.content.ReferenceContent;
import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;

public class ProductFunction extends NonBinaryFunction {

    public ProductFunction(ReferenceContent [] references, String specs) {
        super(references, specs, "PRODUCT");
    }

    public ProductFunction(String value, ReferenceContent [] references, String specs) {
        super(value, references, specs, "PRODUCT");
    }
    
    public ProductFunction shallowCopy() {
        ProductFunction c = new ProductFunction(getValue(), getAllReferences(), getSpecs());
        return c;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitProductFunction(this);
    }

}    