package plazma.ast;


import java.util.ArrayList;
import java.util.List;

import plazma.lang.LValue;

public class BlockNode implements LNode {

    private List<LNode> statements;
    private LNode returnStatement;

    public BlockNode() {
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
            LValue value = stat.evaluate();
            if(value != LValue.VOID) {
                return value;
            }
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
}
