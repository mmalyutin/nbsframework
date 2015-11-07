package plazma.ast.functions;

import plazma.ast.LNode;
import plazma.lang.LValue;

public class AssertNode implements LNode {

    private LNode expression;

    public AssertNode(LNode expression) {
        this.expression = expression;
    }

    @Override
    public LValue evaluate() {

        LValue value = expression.evaluate();

        if (!value.isBoolean()) {
            throw new RuntimeException("assert(...) only takes boolean expressions");
        }

        if (!value.asBoolean()) {
            throw new AssertionError(expression.toString());
        }

        return LValue.VOID;
    }
}
