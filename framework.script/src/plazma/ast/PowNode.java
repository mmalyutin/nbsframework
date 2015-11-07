package plazma.ast;

import plazma.lang.LValue;

public class PowNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public PowNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        // number ^ number
        if (a.isNumber() && b.isNumber()) {
            return new LValue(Math.pow(a.asDouble(), b.asDouble()));
        }

        throw new RuntimeException("illegal expression: " + this);
    }

    @Override
    public String toString() {
        return String.format("(%s^%s)", lhs, rhs);
    }
}
