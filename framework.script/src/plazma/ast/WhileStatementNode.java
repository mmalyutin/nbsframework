package plazma.ast;

import plazma.lang.LValue;

public class WhileStatementNode implements LNode {

    private LNode expression;
    private LNode block;

    public WhileStatementNode(LNode expression, LNode block) {
        this.expression = expression;
        this.block = block;
    }

    @Override
    public LValue evaluate() {

        while (expression.evaluate().asBoolean()) {

            LValue returnValue = block.evaluate();

            if (returnValue == LValue.BREAK) {
                break;
            }
            if (returnValue == LValue.CONTINUE) {
                continue;
            }
            
            if(returnValue != LValue.VOID) {
                return returnValue;
            }
        }

        return LValue.VOID;
    }
}
