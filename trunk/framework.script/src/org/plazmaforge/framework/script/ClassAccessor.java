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

package org.plazmaforge.framework.script;

import java.util.HashMap;
import java.util.Map;

public class ClassAccessor {

    private Class<?> klass; 
    
    private Map<String, PropertyAccessor> propertyAccessors = new HashMap<String, PropertyAccessor>();
    
    
    public ClassAccessor(Class<?> klass) {
	this.klass = klass;
    }

    public Object get(Object object, String property) {
	return getAccessor(property).getValue(object);
    }

    public void set(Object object, String property, Object value) {
	getAccessor(property).setValue(object, value);
    }

    public Object invoke(Object object, String method, Object[] parameters) {
	//TODO
	return null;
    }

    ////
    
    protected PropertyAccessor getAccessor(String property) {
	if (property == null) {
	    throw new RuntimeException("Can not get accessor: property is null"); 
	}
	PropertyAccessor propertyAccessor = propertyAccessors.get(property);
	if (propertyAccessor == null) {
	    propertyAccessor = PropertyAccessor.getAccessor(klass, property);
	    
	}
	if (propertyAccessor == null) {
	    throw new RuntimeException("Can not get accessor: property is '" + property + "'");
	}
	return propertyAccessor;
    }
}
