package plazma.ast;

import java.util.ArrayList;
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

        if ( !(value.isList() || value.isMap() || value.isString())) {
            throw new RuntimeException("illegal expression: " + expression);
        }

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

        return value;
    }
    
    
}
