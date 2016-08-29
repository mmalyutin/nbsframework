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

package org.plazmaforge.framework.script.ast.values;

import java.util.List;

import org.plazmaforge.framework.script.ast.LNode;
import org.plazmaforge.framework.script.lang.LPeriod;
import org.plazmaforge.framework.script.lang.LValue;


public class PeriodNode extends ValueNode {

    protected List<LNode> nodes;
	    
    public PeriodNode(List<LNode> nodes) {
	this.nodes = nodes;
    }
    
    @Override
    public LValue evaluate() {
	if (value == null) {
	    int size = nodes == null ? 0 : nodes.size();
	    LNode startNode = size > 0 ? nodes.get(0) : null;
	    LNode endNode = size > 1 ? nodes.get(1) : null;
	    this.value = new LPeriod(getInstantValue(startNode), getInstantValue(endNode));
	}
        return value;
    }   
  
    protected Long getInstantValue(LNode node) {
	if (node == null) {
	    return 0L;
	}
	LValue v = node.evaluate();
	if (v.isNumber()) {
	    return v.asLong();
	    
	}
	if (v.isDate()) {
	    return v.asDate().getTime();
	}
	throw new RuntimeException("Parameter must be Number or Date/DateTime/Time");
    }
    
}
