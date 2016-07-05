/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.script.ast;

import org.plazmaforge.framework.script.lang.LValue;

public class VariableDefNode implements LNode {

    /**
     * Declare variable flag (var)
     */
    private String declare;
    
    /**
     * Variable type
     */
    private String type;
    
    
    public VariableDefNode(String declare, String type) {
	this.declare = declare;
	this.type = type;
    }

    public VariableDefNode(String declare) {
	this(declare, null);
    }

    @Override
    public LValue evaluate() {
	return LValue.VOID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isDeclare() {
	return declare != null && declare.trim().equals("var"); 
    }

}
