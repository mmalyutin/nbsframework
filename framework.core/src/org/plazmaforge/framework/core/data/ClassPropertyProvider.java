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

package org.plazmaforge.framework.core.data;

import java.util.HashMap;
import java.util.Map;

public class ClassPropertyProvider<T> implements ValidatePropertyProvider<T> {

    private Class<T> targetClass;
    
    private Map<String, Accessor> accessors = new HashMap<String, Accessor>(); 

    public ClassPropertyProvider(Class<T> targetClass) {
	this.targetClass = targetClass;
    }

    public boolean isValid() {
   	return targetClass != null;
    }
    
    
    @Override
    public Object getValue(T element, String property) {
	if (element == null || property == null) {
	    return null;
	}
	return getAccessor(property).getValue(element);
    }

    @Override
    public void setValue(T element, String property, Object value) {
	if (element == null || property == null) {
	    return;
	}
	getAccessor(property).setValue(element, value);
    }

    
    private Accessor getAccessor(String property) {
	Accessor accessor = accessors.get(property);
	if (accessor != null) {
	    return accessor;
	}
	accessor = Accessor.getAccessor(targetClass, property);
	if (accessor != null) {
	    accessors.put(property, accessor);
	    return accessor;
	}
	accessor = new Accessor();
	accessors.put(property, accessor);
	return accessor;
    }
    
    
}
