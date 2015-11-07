package plazma.ast;

import plazma.lang.LValue;

public class TernaryNode implements LNode {

    private LNode condition;
    private LNode ifTrue;
    private LNode ifFalse;

    public TernaryNode(LNode c, LNode t, LNode f) {
        condition = c;
        ifTrue = t;
        ifFalse = f;
    }

    @Override
    public LValue evaluate() {

        LValue a = condition.evaluate();

        if (!a.isBoolean()) {
            throw new RuntimeException("not a boolean expression: " + condition + ", in: " + this);
        }

        return a.asBoolean() ? ifTrue.evaluate() : ifFalse.evaluate();
    }

    @Override
    public String toString() {
        return String.format("(%s ? %s : %s)", condition, ifTrue, ifFalse);
    }
}
