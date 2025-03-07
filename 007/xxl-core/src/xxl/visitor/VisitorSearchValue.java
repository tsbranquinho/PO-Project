package xxl.visitor;

import xxl.content.Content;
import xxl.content.LiteralContent;
import xxl.content.ReferenceContent;
import xxl.content.binary.AddFunction;
import xxl.content.binary.DivFunction;
import xxl.content.binary.MulFunction;
import xxl.content.binary.SubFunction;
import xxl.content.nonbinary.AverageFunction;
import xxl.content.nonbinary.CoalesceFunction;
import xxl.content.nonbinary.ConcatFunction;
import xxl.content.nonbinary.ProductFunction;

public class VisitorSearchValue implements Visitor {
    
    private String _value;

    public VisitorSearchValue(String value) {
        _value = value;
    }

    public String visitContent(Content content) { //in case it's null
        if (_value == null) {
            return "true";
        }
        return null;
    }

    public String visitLiteralContent(LiteralContent content) {
        String value = content.getValue();
        if (value == null) {
            return null;
        }
        if (value.equals(_value)) {
            return "true";
        }
        return null;
    }

    public String visitReferenceContent(ReferenceContent content) {
        String value = content.getValue();
        if (value == null) {
            return null;
        }
        if (value.equals(_value)) {
            return "true";
        }
        return null;
    }

    public String visitAddFunction(AddFunction content) {
        String value = content.getValue();
        if (value == null) {
            return null;
        }
        if (value.equals(_value)) {
            return "true";
        }
        return null;  
    }

    public String visitSubFunction(SubFunction content) {
        String value = content.getValue();
        if (value == null) {
            return null;
        }
        if (value.equals(_value)) {
            return "true";
        }
        return null;
    }

    public String visitMulFunction(MulFunction content) {
        String value = content.getValue();
        if (value == null) {
            return null;
        }
        if (value.equals(_value)) {
            return "true";
        }
        return null;
    }

    public String visitDivFunction(DivFunction content) {
        String value = content.getValue();
        if (value == null) {
            return null;
        }
        if (value.equals(_value)) {
            return "true";
        }
        return null;
    }

    public String visitAverageFunction(AverageFunction content) {
        String value = content.getValue();
        if (value == null) {
            return null;
        }
        if (value.equals(_value)) {
            return "true";
        }
        return null;
    }

    public String visitCoalesceFunction(CoalesceFunction content) {
        String value = content.getValue();
        if (value == null) {
            return null;
        }
        if (value.equals(_value)) {
            return "true";
        }
        return null;
    }

    public String visitConcatFunction(ConcatFunction content) {
        String value = content.getValue();
        if (value == null) {
            return null;
        }
        if (value.equals(_value)) {
            return "true";
        }
        return null;
    }

    public String visitProductFunction(ProductFunction content) {
        String value = content.getValue();
        if (value == null) {
            return null;
        }
        if (value.equals(_value)) {
            return "true";
        }
        return null;
    }
}