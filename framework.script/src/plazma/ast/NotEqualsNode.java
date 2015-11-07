package plazma.ast;

import plazma.lang.LValue;

public class NotEqualsNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public NotEqualsNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        return new LValue(!a.equals(b));
    }

    @Override
    public String toString() {
        return String.format("(%s != %s)", lhs, rhs);
    }
}
