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

public interface Visitor {
    public String visitContent(Content content); //in case it's null
    public String visitLiteralContent(LiteralContent content);
    public String visitReferenceContent(ReferenceContent content);
    public String visitAddFunction(AddFunction content);
    public String visitSubFunction(SubFunction content);
    public String visitMulFunction(MulFunction content);
    public String visitDivFunction(DivFunction content);
    public String visitAverageFunction(AverageFunction content);
    public String visitCoalesceFunction(CoalesceFunction content);
    public String visitConcatFunction(ConcatFunction content);
    public String visitProductFunction(ProductFunction content);
}
