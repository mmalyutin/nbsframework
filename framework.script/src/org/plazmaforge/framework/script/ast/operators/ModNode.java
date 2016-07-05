package org.plazmaforge.framework.script.ast.operators;

import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;


public class ModNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public ModNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        return a._mod(a, b);
        
        /*
        // number % number
        if (a.isNumber() && b.isNumber()) {
            return new LNumber(a.asDouble() % b.asDouble());
        }

        throw new RuntimeException("illegal expression: " + this);
        */
    }

    @Override
    public String toString() {
        return String.format("(%s % %s)", lhs, rhs);
    }
}
