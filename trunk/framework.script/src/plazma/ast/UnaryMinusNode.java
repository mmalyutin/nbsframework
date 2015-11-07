package plazma.ast;

import plazma.lang.LValue;

public class UnaryMinusNode implements LNode {

    private LNode exp;

    public UnaryMinusNode(LNode e) {
        exp = e;
    }

    @Override
    public LValue evaluate() {

        LValue v = exp.evaluate();

        if (!v.isNumber()) {
            throw new RuntimeException("illegal expression: " + this);
        }

        return new LValue(-v.asDouble());
    }

    @Override
    public String toString() {
        return String.format("(-%s)", exp);
    }
}
