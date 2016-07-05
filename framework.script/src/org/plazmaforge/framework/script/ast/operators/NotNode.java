package org.plazmaforge.framework.script.ast.operators;

import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;


public class NotNode implements LNode {

    private LNode exp;

    public NotNode(LNode e) {
        exp = e;
    }

    @Override
    public LValue evaluate() {

        LValue v = exp.evaluate();

        return v._not(v);
        
//        if (!v.isBoolean()) {
//            throw new RuntimeException("illegal expression: " + this);
//        }
//
//        return new LBoolean(!v.asBoolean());
    }

    @Override
    public String toString() {
        return String.format("(not %s)", exp);
    }
}
