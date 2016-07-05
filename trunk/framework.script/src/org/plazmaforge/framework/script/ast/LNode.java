package org.plazmaforge.framework.script.ast;

import org.plazmaforge.framework.script.lang.LValue;

public interface LNode {

    LValue evaluate();
}
