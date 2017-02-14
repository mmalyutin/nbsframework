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

import java.lang.reflect.Method;

/**
 * 
 * @author ohapon
 *
 */
public class BasePropertyAccessor implements PropertyAccessor {

    private Class<?> targetType;
    
    /**
     * Property data type
     */
    private Class<?> type;
    
    /**
     * Getter method
     */
    private Method getter;

    /**
     * Setter method
     */
    private Method setter;


    public Class<?> getTargetType() {
        return targetType;
    }

    public void setTargetType(Class<?> targetType) {
        this.targetType = targetType;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Method getGetter() {
	return getter;
    }

    public void setGetter(Method getter) {
	this.getter = getter;
    }

    public Method getSetter() {
	return setter;
    }

    public void setSetter(Method setter) {
	this.setter = setter;
    }

    public String toString() {
	return "TargetType=" + targetType + ", Type=" + type + ", Getter=" + getter + ", Setter=" + setter;
    }
    
    //////
    

    public Object getValue(Object obj) {
	return AccessUtils.getValue(this, obj);
    }
    
    public void setValue(Object obj, Object value) {
	AccessUtils.setValue(this, obj, value);
    }

}
