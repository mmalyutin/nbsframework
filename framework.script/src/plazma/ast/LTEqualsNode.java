package plazma.ast;

import plazma.lang.LValue;

public class LTEqualsNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public LTEqualsNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        if (a.isNumber() && b.isNumber()) {
            return new LValue(a.asDouble() <= b.asDouble());
        }

        if (a.isString() && b.isString()) {
            return new LValue(a.asString().compareTo(b.asString()) <= 0);
        }

        throw new RuntimeException("illegal expression: " + this);
    }

    @Override
    public String toString() {
        return String.format("(%s <= %s)", lhs, rhs);
    }
}
