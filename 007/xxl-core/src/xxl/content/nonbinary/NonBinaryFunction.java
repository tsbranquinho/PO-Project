package xxl.content.nonbinary;

import xxl.content.Functions;
import xxl.content.ReferenceContent;
import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;

public abstract class NonBinaryFunction extends Functions {

    private ReferenceContent[] _references;

    public NonBinaryFunction(ReferenceContent [] references, String ref, String type) {
        super(ref, type);
        _references = references;
    }
    
    public NonBinaryFunction(String value, ReferenceContent [] references, String ref, String type) {
        super(value, ref, type);
        _references = references;
    }
    public abstract NonBinaryFunction shallowCopy();
    public abstract String accept(Visitor visitor);

    public ReferenceContent[] getAllReferences() { 
        return _references; 
    }

    public ReferenceContent getThatReference(int pos) { 
        return _references[pos]; 
    }
    
}