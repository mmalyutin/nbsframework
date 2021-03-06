package org.plazmaforge.framework.script.ast.operators;

import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;


public class NENode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public NENode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        return a._ne(a, b);
        
        //return new LBoolean(!a.equals(b));
    }

    @Override
    public String toString() {
        return String.format("(%s != %s)", lhs, rhs);
    }
}
