package org.plazmaforge.framework.script.ast.values;

import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LValue;


@Deprecated
public class AtomNode implements LNode {

    private LValue value;

//    public AtomNode(Object v) {
//        value = v == null ? LValue.NULL : new LValue(v);
//    }

    @Override
    public LValue evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
    
    
}
