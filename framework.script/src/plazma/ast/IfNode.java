package plazma.ast;

import java.util.ArrayList;
import java.util.List;

import plazma.lang.LValue;

public class IfNode implements LNode {

    private List<Choice> choices;

    public IfNode() {
        choices = new ArrayList<Choice>();
    }

    public void addChoice(LNode e, LNode b) {
        choices.add(new Choice(e, b));
    }

    @Override
    public LValue evaluate() {

        for (Choice ch : choices) {
            LValue value = ch.expression.evaluate();

            if (!value.isBoolean()) {
                throw new RuntimeException("illegal boolean expression inside if-statement: " + ch.expression);
            }

            if (value.asBoolean()) {
                return ch.block.evaluate();
            }
        }

        return LValue.VOID;
    }

    private class Choice {

        LNode expression;
        LNode block;

        Choice(LNode e, LNode b) {
            expression = e;
            block = b;
        }
    }
}
