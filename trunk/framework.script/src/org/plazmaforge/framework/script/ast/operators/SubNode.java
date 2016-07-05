package org.plazmaforge.framework.script.ast.operators;


import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;


public class SubNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public SubNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        return a._sub(a, b);
        
        /*
        // number - number
        if (a.isNumber() && b.isNumber()) {
            return new LNumber(a.asDouble() - b.asDouble());
        }

        // list - any
        if (a.isList()) {
            List<LValue> list = a.asList();
            list.remove(b);
            return new LList(list);
        }

        throw new RuntimeException("illegal expression: " + this);
        */
    }

    @Override
    public String toString() {
        return String.format("(%s - %s)", lhs, rhs);
    }
}
