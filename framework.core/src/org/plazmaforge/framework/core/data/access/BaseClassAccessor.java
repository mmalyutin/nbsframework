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
    
    private Map<String, PropertyAccessor> propertyAccessors = new HashMap<String, PropertyAccessor>();

    public BaseClassAccessor(Class<?> targetType) {
	super();
	this.targetType = targetType;
    }

    @Override
    public Class<?> getTargetType() {
        return targetType;
    } 
    
    @Override
    public Object getValue(Object object, String property) {
	return getPropertyAccessor(property).getValue(object);
    }

    @Override
    public void setValue(Object object, String property, Object value) {
	getPropertyAccessor(property).setValue(object, value);
    }    

    @Override
    public PropertyAccessor getPropertyAccessor(String property) {
	PropertyAccessor propertyAccessor = propertyAccessors.get(property);
	if (propertyAccessor != null) {
	    return propertyAccessor;
	}
	propertyAccessor = AccessUtils.getPropertyAccessor(targetType, property);
	if (propertyAccessor != null) {
	    propertyAccessors.put(property, propertyAccessor);
	    return propertyAccessor;
	}
	propertyAccessor = new BasePropertyAccessor();
	propertyAccessors.put(property, propertyAccessor);
	return propertyAccessor;
    }

    public Class<?> getPropertyType(String property) {
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
