package org.plazmaforge.framework.script.ast.functions;

import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LNumber;
import org.plazmaforge.framework.script.lang.LValue;


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
