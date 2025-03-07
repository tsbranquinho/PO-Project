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
import xxl.Cell;

public class VisitorRemoveObserver implements Visitor {

    private Cell _dependent;

    public VisitorRemoveObserver(Cell dependent) {
        _dependent = dependent;
    }

    public String visitContent(Content content){ //in case it's null
        return null;
    }

    public String visitLiteralContent(LiteralContent content){
        return null;
    }

    public String visitReferenceContent(ReferenceContent content){
        Cell cell = content.getCell();
        cell.removeObserver(_dependent);
        return null;
    }

    public String visitAddFunction(AddFunction content){
        content.getLeft().accept(this);
        content.getRight().accept(this);
        return null;
        
    }
    public String visitSubFunction(SubFunction content){
        content.getLeft().accept(this);
        content.getRight().accept(this);
        return null;
    }
    public String visitMulFunction(MulFunction content){
        content.getLeft().accept(this);
        content.getRight().accept(this);
        return null;
    }
    public String visitDivFunction(DivFunction content){
        content.getLeft().accept(this);
        content.getRight().accept(this);
        return null;
    }
    public String visitAverageFunction(AverageFunction content){
        int length = content.getAllReferences().length;
        for (int i = 0; i < length; i++) {
            content.getThatReference(i).accept(this);
        }
        return null;
    }
    public String visitCoalesceFunction(CoalesceFunction content){
        int length = content.getAllReferences().length;
        for (int i = 0; i < length; i++) {
            content.getThatReference(i).accept(this);
        }
        return null;
    }
    public String visitConcatFunction(ConcatFunction content){
        int length = content.getAllReferences().length;
        for (int i = 0; i < length; i++) {
            content.getThatReference(i).accept(this);
        }
        return null;
    }
    public String visitProductFunction(ProductFunction content){
        int length = content.getAllReferences().length;
        for (int i = 0; i < length; i++) {
            content.getThatReference(i).accept(this);
        }
        return null;
    }
}