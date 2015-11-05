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

/**
 * 
 */
package org.plazmaforge.framework.core.data;


/**
 * @author ohapon
 *
 */

public class ArrayPropertyProvider<T> implements PropertyProvider<T> {
    
    /**
     * Reserved property 
     */
    public static final String PROPERTY_VALUE = "$value";
    

    @Override
    public Object getValue(T element, String property) {
	if (element == null || property == null) {
	    return null;
	}
	int index = index(property);
	if (index == -1) {
	    return null;
	}
	
	//TODO
	if (!element.getClass().isArray()) {
	    return index == 0 ? element : null;
	}
	Object[] array = (Object[]) element;
	
	return index < array.length ? array[index] : null;
    }

    @Override
    public void setValue(T element, String property, Object value) {
	if (element == null || property == null) {
	    return;
	}
	int index = index(property);
	if (index == -1) {
	    return;
	}
	
	//TODO
	if (!element.getClass().isArray()) {
	    return;
	}
	Object[] array = (Object[]) element;
	
	if (index < array.length) {
	    array[index] = value;
	}
	
    }

    
    private int index(String property) {
	if (property == null) {
	    return -1;
	}
	if (PROPERTY_VALUE.equals(property)) {
	    return 0;
	}
	try {
	    return Integer.valueOf(property);
	} catch (NumberFormatException e) {
	    return -1;
	}
    }
}
