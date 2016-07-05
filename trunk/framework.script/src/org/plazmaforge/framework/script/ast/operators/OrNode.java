package org.plazmaforge.framework.script.ast.operators;

import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;


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
        return String.format("(%s or %s)", lhs, rhs);
    }
}
