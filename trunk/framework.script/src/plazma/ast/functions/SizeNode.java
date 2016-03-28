package plazma.ast.functions;

import plazma.ast.LNode;
import plazma.lang.LNumber;
import plazma.lang.LValue;

public class SizeNode implements LNode {

    private LNode expression;

    public SizeNode(LNode expression) {
        this.expression = expression;
    }

    // TODO: LInteger
    @Override
    public LValue evaluate() {
        LValue value = expression.evaluate();

        if (value.isString()) {
            return new LNumber(value.asString().length());
        }

        if (value.isList()) {
            return new LNumber(value.asList().size());
        }

        if (value.isMap()) {
            return new LNumber(value.asMap().size());
        }

        throw new RuntimeException("Illegal function call: " + this);
    }

    @Override
    public String toString() {
        return String.format("size(%s)", expression);
    }
}
