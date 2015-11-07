package plazma.ast;


import java.util.List;
import java.util.Map;

import plazma.Function;
import plazma.Scope;
import plazma.ScriptUtils;
import plazma.lang.LValue;

public class FunctionCallNode implements LNode {

    private String identifier;
    private List<LNode> params;
    private Map<String, Function> functions;
    private Scope globalScope;

    public FunctionCallNode(String identifier, List<LNode> params, Map<String, Function> functions, Scope globalScope) {
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
}
