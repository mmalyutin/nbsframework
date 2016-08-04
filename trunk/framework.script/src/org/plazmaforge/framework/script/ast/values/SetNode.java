package org.plazmaforge.framework.script.ast.values;


import java.util.List;
import java.util.Set;

import org.plazmaforge.framework.script.ScriptUtils;
import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LSet;
import org.plazmaforge.framework.script.lang.LValue;


public class SetNode implements LNode {

    private List<LNode> expressionNodes;

    public SetNode(List<LNode> nodes) {
        expressionNodes = ScriptUtils.getSafeList(nodes);
    }

    @Override
    public LValue evaluate() {
        Set<LValue> evaluated = ScriptUtils.newSet();
        for (LNode node : expressionNodes) {
            evaluated.add(node.evaluate());
        }
        return new LSet(evaluated);
    }

    @Override
    public String toString() {
        return expressionNodes.toString();
    }
}
