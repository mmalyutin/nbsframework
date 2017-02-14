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

import org.plazmaforge.framework.core.data.access.AccessUtils;
import org.plazmaforge.framework.core.data.access.PropertyAccessor;

public class ClassPropertyProvider<T> implements ValidatePropertyProvider<T> {

    private Class<T> targetType;
    
    private Map<String, PropertyAccessor> propertyAccessors = new HashMap<String, PropertyAccessor>(); 

    public ClassPropertyProvider(Class<T> targetType) {
	this.targetType = targetType;
    }

    public boolean isValid() {
   	return targetType != null;
    }
    
    
    @Override
    public Object getValue(T element, String property) {
	if (element == null || property == null) {
	    return null;
	}
	return doGetValue(element, property);
    }

    @Override
    public void setValue(T element, String property, Object value) {
	if (element == null || property == null) {
	    return;
	}
	doSetValue(element, property, value);
    }
    
    protected Object doGetValue(T element, String property) {
	if (element == null || property == null) {
	    return null;
	}
	return getPropertyAccessor(property).getValue(element);
    }
    
    protected void doSetValue(T element, String property, Object value) {
	if (element == null || property == null) {
	    return;
	}
	getPropertyAccessor(property).setValue(element, value);
    }
    
    protected PropertyAccessor getPropertyAccessor(String property) {
	PropertyAccessor propertyAccessor = propertyAccessors.get(property);
	if (propertyAccessor != null) {
	    return propertyAccessor;
	}
	propertyAccessor = AccessUtils.getAccessor(targetType, property);
	if (propertyAccessor != null) {
	    propertyAccessors.put(property, propertyAccessor);
	    return propertyAccessor;
	}
	propertyAccessor = new PropertyAccessor();
	propertyAccessors.put(property, propertyAccessor);
	return propertyAccessor;
    }
    
    protected Class<?> getType(String property) {
	if (property == null) {
	    return null;
	}
	PropertyAccessor proprtyAccessor = getPropertyAccessor(property);
	if (proprtyAccessor == null) {
	    return null;
	}
	return proprtyAccessor.getType();
    }
    
    
}
