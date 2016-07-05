package org.plazmaforge.framework.script.ast.operators;


import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;


public class MulNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public MulNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        return a._mul(a, b);
        
        /*
        // number * number
        if (a.isNumber() && b.isNumber()) {
            return new LNumber(a.asDouble() * b.asDouble());
        }

        // string * number
        if (a.isString() && b.isNumber()) {
            StringBuilder str = new StringBuilder();
            int stop = b.asDouble().intValue();
            for(int i = 0; i < stop; i++) {
                str.append(a.asString());
            }
            return new LString(str.toString());
        }

        // list * number
        if (a.isList() && b.isNumber()) {
            List<LValue> total = new ArrayList<LValue>();
            int stop = b.asDouble().intValue();
            for(int i = 0; i < stop; i++) {
                total.addAll(a.asList());
            }
            return new LList(total);
        }

        throw new RuntimeException("illegal expression: " + this);
        */
    }

    @Override
    public String toString() {
        return String.format("(%s * %s)", lhs, rhs);
    }
}
