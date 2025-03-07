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

public class VisitorSearchFunction implements Visitor {
    
    private String _value;

    public VisitorSearchFunction(String value) {
        _value = value;
    }

    public String visitContent(Content content) { //in case it's null
        return null;
    }

    public String visitLiteralContent(LiteralContent content) {
        return null;
    }

    public String visitReferenceContent(ReferenceContent content) {
        return null;
    }

    public String visitAddFunction(AddFunction content) {
        if (content.getType().contains(_value)) {
            return "true";
        }
        return null;
    }

    public String visitSubFunction(SubFunction content) {
        if (content.getType().contains(_value)) {
            return "true";
        }
        return null;
    }

    public String visitMulFunction(MulFunction content) {
        if (content.getType().contains(_value)) {
            return "true";
        }
        return null;
    }

    public String visitDivFunction(DivFunction content) {
        if (content.getType().contains(_value)) {
            return "true";
        }
        return null;
    }

    public String visitAverageFunction(AverageFunction content) {
        if (content.getType().contains(_value)) {
            return "true";
        }
        return null;
    }

    public String visitCoalesceFunction(CoalesceFunction content) {
        if (content.getType().contains(_value)) {
            return "true";
        }
        return null;
    }

    public String visitConcatFunction(ConcatFunction content) {
        if (content.getType().contains(_value)) {
            return "true";
        }
        return null;
    }

    public String visitProductFunction(ProductFunction content) {
        if (content.getType().contains(_value)) {
            return "true";
        }
        return null;
    }
}