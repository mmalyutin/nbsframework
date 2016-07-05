package org.plazmaforge.framework.script.ast;


import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.script.Scope;
import org.plazmaforge.framework.script.ScriptUtils;
import org.plazmaforge.framework.script.lang.LValue;


public class AssignmentNode extends AccessorNode {

    protected VariableDefNode def;
    protected String identifier;
    protected List<LNode> indexNodes;
    protected LNode rhs;
    protected Scope scope;
    private Scope globalScope;

    public AssignmentNode(LNode def, String identifier, List<LNode> indexNodes, LNode rhs, Scope scope, Scope globalScope) {
	this.def = (VariableDefNode) def;
	this.identifier = identifier;
        this.indexNodes = ScriptUtils.getSafeList(indexNodes);
        this.rhs = rhs;
        this.scope = scope;
        this.globalScope = globalScope;
    }

    @Override
    public LValue evaluate() {

	Scope currentScope = getCurrentScope(identifier);
	
	boolean isDeclare = isDeclare();
	
	LValue value = null;
	
	if (isDeclare) {
	    
	    // Declare new variable
	    
	    if (indexNodes != null && !indexNodes.isEmpty()) {
		throw new RuntimeException("Can't declare variable '" + identifier + "'. Index node doesn't support");
	    }
	    if (currentScope.exists(identifier)) {
		throw new RuntimeException("Can't declare variable '" + identifier + "' already");
	    }
	    
	    value = rhs == null ? LValue.NULL : rhs.evaluate(); 
	    currentScope.setVariableValue(identifier, value);
	    return LValue.VOID;
	}	

	if ((currentScope.resolve(identifier) == null)) {
	    throw new RuntimeException("Variable '" + identifier + "' not found");
	}
        
	value = rhs.evaluate();

        if (value == LValue.VOID) {
            throw new RuntimeException("can't assign VOID to " + identifier);
        }

        
        
        if (indexNodes.isEmpty()) { // a simple assignment
            currentScope.assign(identifier, value);
            return LValue.VOID;
        } 
        
        // a possible list-lookup and reassignment


        /*
	LValue list = currentScope.resolve(identifier);

	// iterate up to `foo[x][y]` in case of `foo[x][y][z] = 42;`
	for (int i = 0; i < indexNodes.size() - 1 && list != null; i++) {
	    LValue index = indexNodes.get(i).evaluate();

	    if (!index.isNumber() || !list.isList()) { // sanity checks
		throw new RuntimeException("illegal statement: " + this);
	    }

	    int idx = index.asLong().intValue();
	    list = list.asList().get(idx);
	}
	// list is now pointing to `foo[x][y]` in case of `foo[x][y][z] = 42;`

	// get the value `z`: the last index, in `foo[x][y][z] = 42;`
	LValue idx = indexNodes.get(indexNodes.size() - 1).evaluate();

	if (!idx.isNumber() || !list.isList()) { // sanity checks
	    throw new RuntimeException("illegal statement: " + this);
	}

	// re-assign `foo[x][y][z]`
	List<LValue> existing = list.asList();
	existing.set(idx.asLong().intValue(), value);
	*/
        
        List<LValue> indexValues = new ArrayList<LValue>();

        for (LNode indexNode : indexNodes) {
            indexValues.add(indexNode.evaluate());
        }

        LValue curValue = currentScope.resolve(identifier);

        LValue index = null;
        
        // map, list, string
       	for (int i = 0; i < indexValues.size() - 1; i++) {

       	    index = indexValues.get(i);
       	
       	    curValue = get(curValue, index);
       	    
       	    // Fixed null value (actual for Map)
       	    if (curValue == null) {
       		curValue = LValue.NULL;
       	    }
       	}
       	index = indexValues.get(indexValues.size() - 1);
       	set(curValue, index, value);

        return LValue.VOID;
    }

    protected boolean isDeclare() {
	return def == null ? false : def.isDeclare(); 
    }
    
    @Override
    public String toString() {
        return String.format("(%s[%s] = %s)", identifier, indexNodes, rhs);
    }
    
    protected Scope getCurrentScope(String identifier) {
	return ScriptUtils.isGlobalVariable(identifier) ? globalScope : scope;
    }
    
}
