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

package org.plazmaforge.framework.uwt.generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class ScopeContext {
    
    private Set<String> variables = new HashSet<String>();
    
    private Map<String, Integer> templateCount = new HashMap<String, Integer>();
    
    
    public String generateVariable(VariableScope scope, String template) {
	return generateVariable(template);
    }
    
    public String generateVariable(String template) {
	if (template == null) {
	    return null;
	}
	template = template.trim();
	if (template.isEmpty()) {
	    return null;
	}
	Integer count = templateCount.get(template);
	if (count == null) {
	    count = 0;
	}
	count++;
	String variable = template + count;
	
	//TODO: Must find new variable in set

	templateCount.put(template, count);
	variables.add(variable);
	
   	return variable;
    }
    
    public abstract ClassContext getClassContext();
    

}
