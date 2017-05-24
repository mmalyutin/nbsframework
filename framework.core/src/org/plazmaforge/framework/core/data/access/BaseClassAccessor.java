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

package org.plazmaforge.framework.core.data.access;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author ohapon
 *
 */
public class BaseClassAccessor implements ClassAccessor {

    private Class<?> targetType;
    
    private PropertyAccessor[] propertyAccessors;
    private Map<String, PropertyAccessor> propertyAccessorMap = new HashMap<String, PropertyAccessor>();
    

    public BaseClassAccessor(Class<?> targetType) {
	super();
	assert targetType != null;
	this.targetType = targetType;
	loadPropertyAccessors();
    }
    
    protected void loadPropertyAccessors() {
	propertyAccessors = AccessUtils.getPropertyAccessors(targetType);
	propertyAccessorMap = new HashMap<String, PropertyAccessor>();
	if (propertyAccessors == null || propertyAccessors.length == 0) {
	    return;
	}
	for (PropertyAccessor propertyAccessor: propertyAccessors) {
	    propertyAccessorMap.put(propertyAccessor.getName(), propertyAccessor);
	}
    }
    
    @Override
    public Class<?> getTargetType() {
        return targetType;
    } 
    
    @Override
    public Object getValue(Object object, String property) {
	PropertyAccessor propertyAccessor = getPropertyAccessor(property);
	if (propertyAccessor == null) {
	    // E: Property not found
	    return null;
	}
	return propertyAccessor.getValue(object);
    }

    @Override
    public void setValue(Object object, String property, Object value) {
	PropertyAccessor propertyAccessor = getPropertyAccessor(property);
	if (propertyAccessor == null) {
	    // E: Property not found
	    return;
	}
	propertyAccessor.setValue(object, value);
    }    

    @Override
    public PropertyAccessor getPropertyAccessor(String property) {
	if (property == null) {
	    return null;
	}
	return propertyAccessorMap.get(property);
	
	
//	PropertyAccessor propertyAccessor = propertyAccessorMap.get(property);
//	if (propertyAccessor != null) {
//	    return propertyAccessor;
//	}
//	propertyAccessor = AccessUtils.getPropertyAccessor(targetType, property);
//	if (propertyAccessor != null) {
//	    propertyAccessorMap.put(property, propertyAccessor);
//	    return propertyAccessor;
//	}
//	propertyAccessor = new BasePropertyAccessor();
//	propertyAccessorMap.put(property, propertyAccessor);
//	return propertyAccessor;
    }

    @Override
    public PropertyAccessor[] getPropertyAccessors() {
	return propertyAccessors;
    }
    
    public Class<?> getPropertyType(String property) {
//	if (property == null) {
//	    return null;
//	}
	PropertyAccessor proprtyAccessor = getPropertyAccessor(property);
	if (proprtyAccessor == null) {
	    // E: Property not found
	    return null;
	}
	return proprtyAccessor.getType();
    }
    
}
