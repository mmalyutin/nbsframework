package plazma.ast.operators;

import plazma.ast.LNode;
import plazma.lang.LValue;

public class OrNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public OrNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        return a._or(a, b);
        
        /*
        if (!a.isBoolean() || !b.isBoolean()) {
            throw new RuntimeException("illegal expression: " + this);
        }

        return new LBoolean(a.asBoolean() || b.asBoolean());
        */
    }

    @Override
    public String toString() {
        return String.format("(%s || %s)", lhs, rhs);
    }
}
