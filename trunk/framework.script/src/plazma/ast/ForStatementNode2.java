/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package plazma.ast;

import java.util.List;

import plazma.Scope;
import plazma.lang.LValue;

public class ForStatementNode2 implements LNode {

    private String identifier;
    private LNode startExpr;
    private LNode block;
    protected Scope scope;

    public ForStatementNode2(String identifier, LNode startExpr, LNode block, Scope scope) {
        this.identifier = identifier;
        this.startExpr = startExpr;
        this.block = block;
        this.scope = scope;
    }

    @Override
    public LValue evaluate() {

	LValue startValue = startExpr.evaluate();
	
	// Range
	if (startValue.isList()) {
	    List<LValue> values = startValue.asList();
	    LValue value = null;
	    int count = values.size();
	    for (int i = 0; i < count; i++) {
		value = values.get(i);
		scope.assign(identifier, value);
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
	    
	}
	
        return LValue.VOID;
    }


}
