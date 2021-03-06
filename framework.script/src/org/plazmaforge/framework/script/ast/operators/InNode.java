package org.plazmaforge.framework.script.ast.operators;


import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;


public class InNode  implements LNode {

    private LNode lhs;
    private LNode rhs;

    public InNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        // INPORTANT: USE 'b'
        return b._in(a, b);
        
        /*
        if (!rhs.evaluate().isList()) {
            throw new RuntimeException("illegal expression: " + this);
        }

        List<LValue> list = b.asList();

        for (LValue val : list) {
            if (val.equals(a)) {
                return LBoolean.TRUE;
            }
        }

        return LBoolean.FALSE;
        */
    }

    @Override
    public String toString() {
        return String.format("(%s in %s)", lhs, rhs);
    }
}
