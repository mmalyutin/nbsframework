package plazma.ast;

import plazma.Scope;
import plazma.lang.LNumber;
import plazma.lang.LValue;

public class ForStatementNode implements LNode {

    private String identifier;
    private LNode startExpr;
    private String rangeSeparator;
    private LNode stopExpr;
    private LNode block;
    protected Scope scope;

    public ForStatementNode(String identifier, LNode startExpr, LNode stopExpr, LNode block, Scope scope) {
    	this(identifier, startExpr, null, stopExpr, block, scope);
    }
    
    public ForStatementNode(String identifier, LNode startExpr, String rangeSeparator, LNode stopExpr, LNode block, Scope scope) {
	this.identifier = identifier;
        this.startExpr = startExpr;
        this.rangeSeparator = rangeSeparator;
        this.stopExpr = stopExpr;
        this.block = block;
        this.scope = scope;
    }

    @Override
    public LValue evaluate() {

	// Get block node
	BlockNode blockNode  = (BlockNode) block;
	
	// Get block (local) scope
	Scope blockScope = blockNode.getScope();
	
        int start = startExpr.evaluate().asDouble().intValue();
        int stop = stopExpr.evaluate().asDouble().intValue();

        if ("..<".equals(rangeSeparator)) {
            for(int i = start; i < stop; i++) {
                
        	//scope.assign(identifier, new LValue(i)); // OLD CODE
        	blockScope.setVariableValue(identifier, new LNumber(i));
        	
                LValue returnValue = block.evaluate();
                if (returnValue == LValue.BREAK) {
                    break;
                }
                if (returnValue == LValue.CONTINUE) {
                    continue;
                }
                if(returnValue != LValue.VOID) {
                    return returnValue;
                }
            }
            
            return LValue.VOID;
        }
        
        for(int i = start; i <= stop; i++) {
            
            //scope.assign(identifier, new LValue(i)); OLD CODE
            blockScope.setVariableValue(identifier, new LNumber(i));
            
            LValue returnValue = block.evaluate();
            if (returnValue == LValue.BREAK) {
                break;
            }
            if (returnValue == LValue.CONTINUE) {
                continue;
            }
            if(returnValue != LValue.VOID) {
                return returnValue;
            }
        }

        return LValue.VOID;
    }
}
