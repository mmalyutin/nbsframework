package org.plazmaforge.framework.script.ast.values;


import java.util.List;

import org.plazmaforge.framework.script.ScriptUtils;
import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LList;
import org.plazmaforge.framework.script.lang.LValue;


public class ListNode implements LNode {

    private List<LNode> expressionNodes;

    public ListNode(List<LNode> nodes) {
        expressionNodes = ScriptUtils.getSafeList(nodes);
    }

    @Override
    public LValue evaluate() {
        List<LValue> evaluated = ScriptUtils.newList();
        for (LNode node : expressionNodes) {
            evaluated.add(node.evaluate());
        }
        return new LList(evaluated);
    }

    @Override
    public String toString() {
        return expressionNodes.toString();
    }
}
