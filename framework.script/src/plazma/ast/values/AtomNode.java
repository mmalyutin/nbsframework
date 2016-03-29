package plazma.ast.values;

import plazma.ast.LNode;
import plazma.lang.LValue;


public class AtomNode implements LNode {

    private LValue value;

    public AtomNode(Object v) {
        value = v == null ? LValue.NULL : new LValue(v);
    }

    @Override
    public LValue evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
    
    
}
