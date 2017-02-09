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


import org.plazmaforge.framework.core.data.converter.Converter;
import org.plazmaforge.framework.core.data.converter.ConverterManager;

public class ClassPropertyProvider2<T> extends ClassPropertyProvider<T> {

    private ConverterManager converterManager;
    
    private boolean checkDataType = true;// only input (setValue)
    
    public ClassPropertyProvider2(Class<T> targetClass) {
	super(targetClass);
    }

    public ClassPropertyProvider2(Class<T> targetClass, ConverterManager converterManager) {
	super(targetClass);
	this.converterManager = converterManager;
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
	value = convertValue(property, value);
	doSetValue(element, property, value);
    }

    protected Object convertValue(String property, Object value) {
	
	// no convert value: disable mode
	if (!checkDataType) {
	    return value;
	}
	if (value == null) {
	    return null;
	}
	
	// get data type of property
	Class<?> dataType = getDataType(property);
	if (dataType == null) {
	    //TODO
	    return value;
	}
	
	// get input type (real)
	Class<?> inputType = value.getClass();
	
	// no convert: type of value is correct
	if (dataType.isAssignableFrom(inputType)) {
	    return value;
	}
	
	// TODO: Why simple name. May be by class
	Converter converter = getConverter(inputType.getSimpleName(), dataType.getSimpleName());
	if (converter == null) {
	    //TODO
	    return value;
	}
	return converter.convert(value);
    }
    
    
    protected Converter<?, ?> getConverter(String sourceType, String targetType) {
	if (sourceType == null || targetType == null) {
	    return null;
	}
	String name = ConverterManager.getConverterSimpleName(sourceType, targetType);
	if (name == null) {
	    return null;
	}
	return getConverterByName(name);
    }

    protected Converter<?, ?> getConverterByName(String name) {
	return converterManager.getConverter(name);
    }      
    
}
