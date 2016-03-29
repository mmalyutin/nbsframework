package plazma.ast.operators;

import plazma.ast.LNode;
import plazma.lang.LBoolean;
import plazma.lang.LValue;

public class NegateNode implements LNode {

    private LNode exp;

    public NegateNode(LNode e) {
        exp = e;
    }

    @Override
    public LValue evaluate() {

        LValue v = exp.evaluate();

        if (!v.isBoolean()) {
            throw new RuntimeException("illegal expression: " + this);
        }

        return new LBoolean(!v.asBoolean());
    }

    @Override
    public String toString() {
        return String.format("(!%s)", exp);
    }
}
