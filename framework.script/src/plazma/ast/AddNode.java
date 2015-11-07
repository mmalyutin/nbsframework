package plazma.ast;

import java.util.List;

import plazma.lang.LValue;

public class AddNode implements LNode {

    private LNode lhs;
    private LNode rhs;

    public AddNode(LNode lhs, LNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public LValue evaluate() {

        LValue a = lhs.evaluate();
        LValue b = rhs.evaluate();

        // integer + integer
        if (a.isInteger() && b.isInteger()) {
            return new LValue(a.asInteger() + b.asInteger());
        }

        // number + number
        if (a.isNumber() && b.isNumber()) {
            return new LValue(a.asDouble() + b.asDouble());
        }

        // list + any
        if (a.isList()) {
            List<LValue> list = a.asList();
            list.add(b);
            return new LValue(list);
        }

        // string + any
        if (a.isString()) {
            return new LValue(a.asString() + "" + b.toString());
        }

        // any + string
        if (b.isString()) {
            return new LValue(a.toString() + "" + b.asString());
        }

        throw new RuntimeException("illegal expression: " + this);
    }

    @Override
    public String toString() {
        return String.format("(%s + %s)", lhs, rhs);
    }
}
