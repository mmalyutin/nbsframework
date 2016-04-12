package plazma.ast.operators;

import plazma.ast.LNode;
import plazma.lang.LValue;

public class NotNode implements LNode {

    private LNode exp;

    public NotNode(LNode e) {
        exp = e;
    }

    @Override
    public LValue evaluate() {

        LValue v = exp.evaluate();

        return v._not(v);
        
//        if (!v.isBoolean()) {
//            throw new RuntimeException("illegal expression: " + this);
//        }
//
//        return new LBoolean(!v.asBoolean());
    }

    @Override
    public String toString() {
        return String.format("(!%s)", exp);
    }
}
