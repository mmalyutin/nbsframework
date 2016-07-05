package org.plazmaforge.framework.script.ast;


import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.script.Function;
import org.plazmaforge.framework.script.Scope;
import org.plazmaforge.framework.script.ScriptUtils;
import org.plazmaforge.framework.script.lang.LValue;


public class MethodCallNode implements LNode {

    private String identifier;
    private List<LNode> params;
    private Map<String, Function> functions;
    private Scope globalScope;

    public MethodCallNode(String identifier, List<LNode> params, Map<String, Function> functions, Scope globalScope) {
	this.identifier = identifier;
        this.params = ScriptUtils.getSafeList(params);
        this.functions = functions;
        this.globalScope = globalScope;
    }

    @Override
    public LValue evaluate() {

        Function f = functions.get(identifier + params.size());

        if (f == null) {
            throw new RuntimeException("no function `" + identifier + "` with " + params.size() + " parameter(s)");
        }

        Function function = new Function(f, globalScope);

        return function.invoke(params, functions);
    }

    public List<LNode> getParams() {
        return params;
    }

    public String getIdentifier() {
        return identifier;
    }
    
    
    
}
