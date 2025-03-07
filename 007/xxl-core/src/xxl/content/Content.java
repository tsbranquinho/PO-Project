package xxl.content;

import java.io.Serializable;

import xxl.visitor.Visitor;
import xxl.visitor.VisitorAddObserver;
import xxl.visitor.VisitorRemoveObserver;
import xxl.visitor.VisitorEvaluate;
import xxl.visitor.VisitorSearchFunction;
import xxl.visitor.VisitorSearchValue;

public class Content implements Serializable {
    
    private String _value;

    public Content() {
        _value = null;
    }

    public Content(String value) {
        _value = value;
    }

    public Content shallowCopy() {
        Content c = new Content();
        return c;
    }


    public String getValue() {
        return _value;
    }

    public void setValue(String value) {
        _value = value;
    }

    public String accept(Visitor v) { 
        // Visitor pattern will be overriden in subclasses except if there's content null in cell
        return v.visitContent(this);
    }
    
    @Override
    public String toString() {

        if (_value != null) {
            return _value;
        }
        return "";
    }
}
    
    
    
    