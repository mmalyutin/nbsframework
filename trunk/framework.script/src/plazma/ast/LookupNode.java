package plazma.ast;

import java.util.ArrayList;
import java.util.List;

import plazma.lang.LValue;

public class LookupNode implements LNode {

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

	    if (value.isMap()) {
		value = getMapValue(value, index);
	    } else if (value.isList()) {
		value = getListValue(value, index);
	    } else if (value.isString()) {
		value = getStringValue(value, index);
	    }
	}

        return value;
    }
    
    protected LValue getMapValue(LValue value, LValue index) {
    	return value.asMap().get(index);
    }
    
    protected LValue getListValue(LValue value, LValue index) {
        int idx = getIndexValue(index);
        return value.asList().get(idx);
    }

    protected LValue getStringValue(LValue value, LValue index) {
        int idx = getIndexValue(index);
        return new LValue(String.valueOf(value.asString().charAt(idx)));
    }

    protected int getIndexValue(LValue index) {
        if (!index.isNumber()) {
            throw new RuntimeException("illegal expression: " + expression + "[" + index + "]");
        }
        return index.asLong().intValue();
    }
    
}
