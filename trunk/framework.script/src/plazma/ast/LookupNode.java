package plazma.ast;

import java.util.List;

import plazma.lang.LValue;

public class LookupNode extends AccessorNode {

    private LNode expression;
    private List<LNode> indexes;

    public LookupNode(LNode expression, List<LNode> indexes) {
        this.expression = expression;
        this.indexes = indexes;
    }

    @Override
    public LValue evaluate() {

        LValue value = expression.evaluate();

        //if ( !(value.isList() || value.isMap() || value.isString())) {
        //    throw new RuntimeException("illegal expression: " + expression);
        //}


        /*
        List<LValue> indexValues = new ArrayList<LValue>();

        for (LNode indexNode : indexes) {
            indexValues.add(indexNode.evaluate());
        }

        // map, list, string
	for (LValue index : indexValues) {

	    value = getValue(value, index);
	    
	    // Fixed null value (actual for Map)
	    if (value == null) {
		value = LValue.NULL;
	    }
	}
	*/

        
        // map, list, string
	for (int i = 0; i < indexes.size(); i++) {
	    LNode indexNode = indexes.get(i);
	    if (indexNode instanceof MethodCallNode) {
		MethodCallNode callNode = (MethodCallNode) indexNode;
		value = invoke(value, callNode.getIdentifier(), callNode.getParams());
	    } else {
		LValue index = indexNode.evaluate();
		value = get(value, index);
	    }
	    
	    // Fixed null value (actual for Map)
	    if (value == null) {
		value = LValue.NULL;
	    }
	}
	
        return value;
    }
    
}
