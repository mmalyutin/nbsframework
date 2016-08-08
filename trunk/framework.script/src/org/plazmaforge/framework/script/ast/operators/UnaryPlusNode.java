package org.plazmaforge.framework.script.ast.operators;

import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;


public class UnaryPlusNode implements LNode {

    private LNode exp;

    public UnaryPlusNode(LNode e) {
        exp = e;
    }

    @Override
    public LValue evaluate() {

        LValue v = exp.evaluate();

        return v._unaryPlus(v);
        
//        if (!v.isNumber()) {
//            throw new RuntimeException("illegal expression: " + this);
//        }
//        
//        if (v.isInteger()) {
//            return new LNumber(v.asInteger());
//        }
//
//        return new LNumber(v.asDouble());
    }

    @Override
    public String toString() {
        return String.format("(-%s)", exp);
    }
}
