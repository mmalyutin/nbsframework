package plazma.ast.functions;


import java.io.PrintStream;

import plazma.ast.LNode;
import plazma.lang.LValue;

public class PrintlnNode implements LNode {

    private LNode expression;
    private PrintStream out;

    public PrintlnNode(LNode expression) {
        this(expression, System.out);
    }

    public PrintlnNode(LNode expression, PrintStream out) {
        this.expression = expression;
        this.out = out;
    }

    @Override
    public LValue evaluate() {
	LValue value = expression == null ? null : expression.evaluate();
	if (expression == null) {
	    out.println();
	} else {
	    out.println(value);
	}
	return LValue.VOID;
    }
}
