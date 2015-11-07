package plazma.ast;


import java.util.ArrayList;
import java.util.List;

import plazma.ScriptUtils;
import plazma.lang.LValue;

public class ListNode implements LNode {

    private List<LNode> expressionNodes;

    public ListNode(List<LNode> nodes) {
        expressionNodes = ScriptUtils.getSafeList(nodes);
    }

    @Override
    public LValue evaluate() {
        List<LValue> evaluated = new ArrayList<LValue>();
        for (LNode node : expressionNodes) {
            evaluated.add(node.evaluate());
        }
        return new LValue(evaluated);
    }

    @Override
    public String toString() {
        return expressionNodes.toString();
    }
}
