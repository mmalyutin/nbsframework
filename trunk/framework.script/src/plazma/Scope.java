package plazma;

import java.util.HashMap;
import java.util.Map;

import plazma.lang.LValue;

public class Scope {

    private Scope parent;
    private Map<String, LValue> variables;

    public Scope() {
        // only for the global scope, the parent is null
        this(null);
    }

    public Scope(Scope parent) {
        this.parent = parent;
        this.variables = new HashMap<String, LValue>();
    }

    public void assign(String var, LValue value) {
        if (resolve(var) != null) {
            // There is already such a variable, re-assign it
            this.reAssign(var, value);
        } else {
            // A newly declared variable
            setVariableValue(var, value);
            //variables.put(var, value);
        }
    }

    public Scope copy() {
        // Create a shallow copy of this scope. Used in case functions are
        // are recursively called. If we wouldn't create a copy in such cases,
        // changing the variables would result in changes ro the Maps from
        // other "recursive scopes".
        Scope scope = new Scope();
        scope.variables = new HashMap<String, LValue>(this.variables);
        return scope;
    }

    public boolean isGlobalScope() {
        return parent == null;
    }

    public Scope parent() {
        return parent;
    }

    private void reAssign(String identifier, LValue value) {
        if (exists(identifier)) {
            // The variable is declared in this scope
            setVariableValue(identifier, value);
            //variables.put(identifier, value);
        } else if(parent != null) {
            // The variable was not declared in this scope, so let
            // the parent scope re-assign it
            parent.reAssign(identifier, value);
        }
    }

    public LValue resolve(String var) {
        LValue value = getVariableValue(var);
        //LValue value = variables.get(var);
        if (value != null) {
            // The variable resides in this scope
            return value;
        } else if (!isGlobalScope()) {
            // Let the parent scope look for the variable
            return parent.resolve(var);
        }
        
        // Unknown variable
        return null;
    }
    
    
    protected boolean exists(String var) {
	return variables.containsKey(var);
    }
    
    protected LValue getVariableValue(String var) {
	return variables.get(var);
    }

    protected void setVariableValue(String var, LValue value) {
	variables.put(var, value);
    }

    public void reset() {
	// Clear all variables
	variables.clear();
    }
}
