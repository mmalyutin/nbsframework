package plazma.ast.functions;


import java.io.PrintStream;

import plazma.ast.LNode;
import plazma.lang.LValue;

public class PrintNode implements LNode {

    private LNode expression;
    private PrintStream out;

    public PrintNode(LNode expression) {
        this(expression, System.out);
    }

    public PrintNode(LNode expression, PrintStream out) {
        this.expression = expression;
        this.out = out;
    }

    @Override
    public LValue evaluate() {
        LValue value = expression.evaluate();
        out.print(value == LValue.NULL ? "" : value);
        return LValue.VOID;
    }
}
