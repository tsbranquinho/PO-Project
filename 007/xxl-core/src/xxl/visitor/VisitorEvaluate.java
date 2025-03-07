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

public class VisitorEvaluate implements Visitor {

    public String visitContent(Content content) { //in case it's null
        return content.getValue();
    }

    public String visitLiteralContent(LiteralContent content) {
        return content.getValue();
    }

    public String visitReferenceContent(ReferenceContent content) {
        //because of the observers, just need the value directly above
        return content.getCell().getContent().getValue();
    }

    public String visitAddFunction(AddFunction content) {
        try {
            int left = Integer.parseInt(content.getLeft().accept(this));
            int right = Integer.parseInt(content.getRight().accept(this));
            return Integer.toString(left + right);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public String visitSubFunction(SubFunction content) {
        try {
            int left = Integer.parseInt(content.getLeft().accept(this));
            int right = Integer.parseInt(content.getRight().accept(this));
            return Integer.toString(left - right);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public String visitMulFunction(MulFunction content) {
        try {
            int left = Integer.parseInt(content.getLeft().accept(this));
            int right = Integer.parseInt(content.getRight().accept(this));
            return Integer.toString(left * right);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public String visitDivFunction(DivFunction content) {
        try {
            int left = Integer.parseInt(content.getLeft().accept(this));
            int right = Integer.parseInt(content.getRight().accept(this));

            return Integer.toString(left / right);
        } catch (NumberFormatException e) {
            return null;
        } catch (ArithmeticException e) {
            return null;
        }
    }
    public String visitAverageFunction(AverageFunction content) {
        int average = 0;
        int length = content.getAllReferences().length;
        for (int i = 0; i < length; i++) {
            try {
                average += Integer.parseInt(content.getThatReference(i).accept(this));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return Integer.toString(average/length);
    }
    public String visitCoalesceFunction(CoalesceFunction content) {
        int length = content.getAllReferences().length;
        String coalesce = "'";
        for (int i = 0; i < length; i++) {
            String value = content.getThatReference(i).accept(this);
            if (value == null) {}
            else if (value.charAt(0) == '\'') {
                coalesce = value;
                break;
            }
        }
        
        return coalesce;
    }
    public String visitConcatFunction(ConcatFunction content) {
        int length = content.getAllReferences().length;
        String concat = "'";
        for (int i = 0; i < length; i++) {
            String value = content.getThatReference(i).accept(this);
            if (value == null) {}
            else {
                concat += value.substring(1);
            }
        }
        
        return concat;
    }
    public String visitProductFunction(ProductFunction content) {
        int length = content.getAllReferences().length;
        int product = 1;

        for (int i = 0; i < length; i++) {
            try {
                product *= Integer.parseInt(content.getThatReference(i).accept(this));
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return Integer.toString(product);
    }
}