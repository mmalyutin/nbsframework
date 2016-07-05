package org.plazmaforge.framework.script.ast;

import org.plazmaforge.framework.script.Scope;
import org.plazmaforge.framework.script.ScriptUtils;
import org.plazmaforge.framework.script.lang.LValue;


public class IdentifierNode implements LNode {

    private String identifier;
    private Scope scope;
    private Scope globalScope;

    public IdentifierNode(String identifier, Scope scope, Scope globalScope) {
	this.identifier = identifier;
        this.scope = scope;
        this.globalScope = globalScope;
    }


    @Override
    public LValue evaluate() {
	Scope currentScope = getCurrentScope(identifier);
        LValue value = currentScope.resolve(identifier);
        if (value == null) {
            //throw new RuntimeException("no such variable: " + this);
            throw new RuntimeException("Variable '" + identifier + "' not found");
        }
        return value;
    }

    @Override
    public String toString() {
        return identifier;
    }
    
    protected Scope getCurrentScope(String identifier) {
	return ScriptUtils.isGlobalVariable(identifier) ? globalScope : scope;	
    }
    
}
