package plazma;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import plazma.ast.LNode;
import plazma.lang.LValue;
import plazma.parser.PlazmaScriptWalker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Function {

    private String id;
    private List<String> identifiers;
    private CommonTree block;
    private Scope scope;
    private Scope globalScope;

    public Function(String id, CommonTree ids, CommonTree block) {
        this.id = id;
        this.identifiers = toList(ids);
        this.block = block;
        this.scope = new Scope();
    }

    public Function(Function original, Scope globalScope) {
        this.id = original.id;
        this.identifiers = original.identifiers;
        this.block = original.block;
        this.scope = original.scope.copy();
        this.globalScope = globalScope;
    }

    public LValue invoke(List<LNode> params, Map<String, Function> functions) {

        if(params.size() != identifiers.size()) {
            throw new RuntimeException("illegal function call: " + identifiers.size() +
                    " parameters expected for function `" + id + "`");
        }

        // Assign all expression parameters to this function's identifiers
        for(int i = 0; i < identifiers.size(); i++) {
            scope.assign(identifiers.get(i), params.get(i).evaluate());
        }

        try {
            // Create a tree walker to evaluate this function's code block
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(block);
            PlazmaScriptWalker walker = new PlazmaScriptWalker(nodes, functions, scope, globalScope);
            return walker.walk().evaluate();
        } catch (RecognitionException e) {
            // do not recover from this
            throw new RuntimeException("something went wrong, terminating", e);
        }
    }

    private List<String> toList(CommonTree tree) {
        List<String> ids = new ArrayList<String>();
        for(int i = 0; i < tree.getChildCount(); i++) {
            CommonTree child = (CommonTree)tree.getChild(i);
            ids.add(child.getText());
        }
        return ids;
    }
}
