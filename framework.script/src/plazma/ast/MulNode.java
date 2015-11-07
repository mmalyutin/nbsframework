package plazma.ast;


import java.util.ArrayList;
import java.util.List;

import plazma.lang.LValue;

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

        // number * number
        if (a.isNumber() && b.isNumber()) {
            return new LValue(a.asDouble() * b.asDouble());
        }

        // string * number
        if (a.isString() && b.isNumber()) {
            StringBuilder str = new StringBuilder();
            int stop = b.asDouble().intValue();
            for(int i = 0; i < stop; i++) {
                str.append(a.asString());
            }
            return new LValue(str.toString());
        }

        // list * number
        if (a.isList() && b.isNumber()) {
            List<LValue> total = new ArrayList<LValue>();
            int stop = b.asDouble().intValue();
            for(int i = 0; i < stop; i++) {
                total.addAll(a.asList());
            }
            return new LValue(total);
        }

        throw new RuntimeException("illegal expression: " + this);
    }

    @Override
    public String toString() {
        return String.format("(%s * %s)", lhs, rhs);
    }
}
