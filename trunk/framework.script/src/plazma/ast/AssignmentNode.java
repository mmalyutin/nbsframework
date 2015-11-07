package plazma.ast;


import java.util.List;

import plazma.Scope;
import plazma.ScriptUtils;
import plazma.lang.LValue;

public class AssignmentNode implements LNode {

    protected String identifier;
    protected List<LNode> indexNodes;
    protected LNode rhs;
    protected Scope scope;
    private Scope globalScope;

    public AssignmentNode(String identifier, List<LNode> indexNodes, LNode rhs, Scope scope, Scope globalScope) {
	this.identifier = identifier;
        this.indexNodes = ScriptUtils.getSafeList(indexNodes);
        this.rhs = rhs;
        this.scope = scope;
        this.globalScope = globalScope;
    }

    @Override
    public LValue evaluate() {

        LValue value = rhs.evaluate();

        if (value == LValue.VOID) {
            throw new RuntimeException("can't assign VOID to " + identifier);
        }

        Scope currentScope = getCurrentScope(identifier);
        
        if (indexNodes.isEmpty()) { // a simple assignment
            currentScope.assign(identifier, value);
        }  else { // a possible list-lookup and reassignment


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

            if (!idx.isNumber() || /*list == null ||*/ !list.isList()) { // sanity checks
                throw new RuntimeException("illegal statement: " + this);
            }

            // re-assign `foo[x][y][z]`
            List<LValue> existing = list.asList();
            existing.set(idx.asLong().intValue(), value);
        }

        return LValue.VOID;
    }

    @Override
    public String toString() {
        return String.format("(%s[%s] = %s)", identifier, indexNodes, rhs);
    }
    
    protected Scope getCurrentScope(String identifier) {
	return ScriptUtils.isGlobalVariable(identifier) ? globalScope : scope;
    }
}
