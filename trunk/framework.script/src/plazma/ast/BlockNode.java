package plazma.ast;


import java.util.ArrayList;
import java.util.List;

import plazma.Scope;
import plazma.lang.LValue;

public class BlockNode implements LNode {

    protected Scope scope;
    
    private List<LNode> statements;
    private LNode returnStatement;

    public BlockNode(Scope scope) {
	this.scope = scope;
        statements = new ArrayList<LNode>();
        returnStatement = null;
    }

    public void addReturn(LNode stat) {
        returnStatement = stat;
    }

    public void addStatement(LNode stat) {
        statements.add(stat);
    }

    @Override
    public LValue evaluate() {
        for(LNode stat : statements) {
            boolean external = false;
            
            //TODO: STUB. Maybe check BREAK, CONTINUE, EXIT statements
            if (stat instanceof FunctionCallNode || stat instanceof LookupNode) {
        	external = true;
            }
            
            LValue value = stat.evaluate();
            
            if(value != LValue.VOID && !external) {
                return value;
            }
            
            //if (value != null && (value == LValue.BREAK || value == LValue.CONTINUE)) {
            //    return value;
            //}

        }
        return returnStatement == null ? LValue.VOID : returnStatement.evaluate();
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for(LNode stat : statements) {
            b.append(stat).append("\n");
        }
        if(returnStatement != null) {
            b.append("return ").append(returnStatement).append("\n");
        }
        return b.toString();
    }

    public Scope getScope() {
        return scope;
    }
    
    
}
