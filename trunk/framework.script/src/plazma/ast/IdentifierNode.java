package plazma.ast;

import plazma.Scope;
import plazma.ScriptUtils;
import plazma.lang.LValue;

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
            throw new RuntimeException("no such variable: " + this);
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
