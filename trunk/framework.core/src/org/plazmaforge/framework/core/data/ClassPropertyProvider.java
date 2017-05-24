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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.access.BaseClassAccessor;
import org.plazmaforge.framework.core.data.access.PropertyAccessor;

/**
 * 
 * @author ohapon
 *
 * @param <T>
 */
public class ClassPropertyProvider<T> implements ValidatePropertyProvider<T>, MetaPropertyProvider<T> {

    private BaseClassAccessor classAccessor;
    
    public ClassPropertyProvider(Class<T> targetType) {
	this.classAccessor = new BaseClassAccessor(targetType);
    }

    public boolean isValid() {
   	return classAccessor != null && classAccessor.getTargetType() != null;
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
	PropertyAccessor propertyAccessor = getPropertyAccessor(property);
	if (propertyAccessor == null) {
	    // E: Property not found
	    return null;
	}
	return propertyAccessor.getValue(element);
    }
    
    protected void doSetValue(T element, String property, Object value) {
	if (element == null || property == null) {
	    return;
	}
	PropertyAccessor propertyAccessor = getPropertyAccessor(property);
	if (propertyAccessor == null) {
	    // E: Property not found
	    return;
	}
	
	propertyAccessor.setValue(element, value);
    }
    
    protected PropertyAccessor getPropertyAccessor(String property) {
	return classAccessor.getPropertyAccessor(property);
    }
    
    protected Class<?> getPropertyType(String property) {
	if (property == null) {
	    return null;
	}
	PropertyAccessor propertyAccessor = getPropertyAccessor(property);
	if (propertyAccessor == null) {
	    return null;
	}
	return propertyAccessor.getType();
    }

    @Override
    public List<String> getPropertyNames() {
	List<String> result = new ArrayList<String>();
	PropertyAccessor[] propertyAccessors = classAccessor.getPropertyAccessors(); 
	for (PropertyAccessor propertyAccessor : propertyAccessors) {
	    result.add(propertyAccessor.getName());
	}
	return result;
    }
    
    
}
