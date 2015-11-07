package plazma.ast.functions;

import plazma.ast.LNode;
import plazma.lang.LValue;

public class SizeNode implements LNode {

    private LNode expression;

    public SizeNode(LNode expression) {
        this.expression = expression;
    }

    @Override
    public LValue evaluate() {
        LValue value = expression.evaluate();

        if (value.isString()) {
            return new LValue(value.asString().length());
        }

        if (value.isList()) {
            return new LValue(value.asList().size());
        }

        if (value.isMap()) {
            return new LValue(value.asMap().size());
        }

        throw new RuntimeException("Illegal function call: " + this);
    }

    @Override
    public String toString() {
        return String.format("size(%s)", expression);
    }
}
