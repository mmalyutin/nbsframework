package plazma.ast;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import plazma.ScriptUtils;
import plazma.lang.LValue;


public class MapNode implements LNode {

    private List<PairNode> expressionNodes;

    public MapNode(List<PairNode> nodes) {
        expressionNodes = ScriptUtils.getSafeList(nodes);
    }

    @Override
    public LValue evaluate() {
	Map<LValue, LValue> evaluated = new LinkedHashMap<LValue, LValue>();
	LValue key = null;
	LValue value = null;
	for (PairNode node : expressionNodes) {
	    key = node.getKey() == null ? LValue.NULL : node.getKey().evaluate();
	    value = node.getValue() == null ? LValue.NULL : node.getValue().evaluate();
	    evaluated.put(key, value);
	}
	return new LValue(evaluated);
    }

    @Override
    public String toString() {
        return expressionNodes.toString();
    }
    
   
}
