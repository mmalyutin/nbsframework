package plazma.ast;


import java.util.List;

import plazma.lang.LValue;

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

        if (!rhs.evaluate().isList()) {
            throw new RuntimeException("illegal expression: " + this);
        }

        List<LValue> list = b.asList();

        for (LValue val : list) {
            if (val.equals(a)) {
                return new LValue(true);
            }
        }

        return new LValue(false);
    }

    @Override
    public String toString() {
        return String.format("(%s in %s)", lhs, rhs);
    }
}
