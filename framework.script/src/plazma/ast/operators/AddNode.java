package plazma.ast.operators;

import java.util.List;

import plazma.ast.LNode;
import plazma.lang.LList;
import plazma.lang.LNumber;
import plazma.lang.LString;
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

        // TODO: LInteger
        /*
        // integer + integer
        if (a.isInteger() && b.isInteger()) {
            return new LNumber(a.asInteger() + b.asInteger());
        }
        */

        // number + number
        if (a.isNumber() && b.isNumber()) {
            return new LNumber(a.asDouble() + b.asDouble());
        }

        // list + any
        if (a.isList()) {
            List<LValue> list = a.asList();
            list.add(b);
            return new LList(list);
        }

        // string + any
        if (a.isString()) {
            return new LString(a.asString() + "" + b.toString());
        }

        // any + string
        if (b.isString()) {
            return new LString(a.toString() + "" + b.asString());
        }

        throw new RuntimeException("illegal expression: " + this);
    }

    @Override
    public String toString() {
        return String.format("(%s + %s)", lhs, rhs);
    }
}
