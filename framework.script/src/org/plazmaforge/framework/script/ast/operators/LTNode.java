package org.plazmaforge.framework.script.ast.operators;

import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;


public class LTNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public LTNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        return a._lt(a, b);
        
        /*
        if (a.isNumber() && b.isNumber()) {
            return new LBoolean(a.asDouble() < b.asDouble());
        }

        if (a.isString() && b.isString()) {
            return new LBoolean(a.asString().compareTo(b.asString()) < 0);
        }

        throw new RuntimeException("illegal expression: " + this);
        */
    }

    @Override
    public String toString() {
        return String.format("(%s < %s)", lhs, rhs);
    }
}
