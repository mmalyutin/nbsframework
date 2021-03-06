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
import org.plazmaforge.framework.script.lang.LDate;
import org.plazmaforge.framework.script.lang.LValue;


public class DateNode extends InstantNode {

    
    public DateNode(List<LNode> nodes) {
	super(nodes);
    }

    @Override
    public LValue evaluate() {
	if (value == null) {
	    this.value = new LDate(createDate(expressionNodes));
	}
        return value;
    }    
    
    // TODO: temp solution
    /*
    protected Date splitDate(String value) {
	if (value == null) {
	    return null;
	}
	
	// TODO
	String[] values = value.split(",");
	return parseDate(values); 
	
    }
    
    public static Date parseDate(String str) {
    	if (str == null) {
    		return null;
    	}
    	int start = "#Date{".length();
    	if (str.length() <= start + 1){
    		return null;
    	}
    	
    	String val = str.substring(start, str.length() - 1);
    	String[] array = val.split("-");
    	return parseDate(array);
    }
    */
    
    
  
    
}

