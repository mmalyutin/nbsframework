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

package org.plazmaforge.framework.script.lang;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.plazmaforge.framework.script.ScriptUtils;

public class LSet extends LCollection {

    
    /**
     * @param value
     */
    public LSet(Set<?> value) {
	super(Type.SET, value);
	
    }

    
    protected LCollection newInstance(Collection<LValue> values) {
	return new LSet((Set<LValue>) values);
    }

    protected <T> Collection<T> newCollection() {
	return ScriptUtils.newSet();
    }
    
    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	if ("clone".equals(method)) {
	    checkMethod(method, parameters, 0);
	    Set<LValue> set = ScriptUtils.cloneSet(asSet());
	    LSet result = new LSet(set);
	    return result;
	}
	return super._invoke(method, parameters);
    }
    
}
