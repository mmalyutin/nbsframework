package plazma.ast;


import java.util.List;

import plazma.lang.LValue;

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

        // number - number
        if (a.isNumber() && b.isNumber()) {
            return new LValue(a.asDouble() - b.asDouble());
        }

        // list - any
        if (a.isList()) {
            List<LValue> list = a.asList();
            list.remove(b);
            return new LValue(list);
        }

        throw new RuntimeException("illegal expression: " + this);
    }

    @Override
    public String toString() {
        return String.format("(%s - %s)", lhs, rhs);
    }
}
