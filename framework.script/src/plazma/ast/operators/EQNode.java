package plazma.ast.operators;

import plazma.ast.LNode;
import plazma.lang.LValue;

public class EQNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public EQNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        return a._eq(b);
        
        //return new LBoolean(a.equals(b));
    }

    @Override
    public String toString() {
        return String.format("(%s == %s)", lhs, rhs);
    }
}
