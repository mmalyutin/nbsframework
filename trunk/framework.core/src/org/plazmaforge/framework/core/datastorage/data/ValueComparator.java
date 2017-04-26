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

package org.plazmaforge.framework.core.datastorage.data;

import java.util.Comparator;

/**
 * Universal value comparator.
 * If comparing is not correct then return <code>null<code> result.
 * 
 * @author ohapon
 *
 */
public class ValueComparator implements Comparator<Object> {

    
    
    @Override
    public int compare(Object o1, Object o2) {
	Integer result = compareValue(o1, o2);
	return result == null ? 0 : result;
    }
    
    public Integer compareValue(Object v1, Object v2) {
	if (v1 == null && v2 == null) {
	    return 0;
	}
	if (v1 == null) {
	    return -1;
	}
	if (v2 == null) {
	    return 1;
	}

	if (v1 instanceof Number && v2 instanceof Number) {
	    return compareNumberValue((Number) v1, (Number) v2);
	}
	
	if (v1 instanceof Comparable && v2 instanceof Comparable) {
	    return ((Comparable) v1).compareTo(v2);
	}
	
	return null;
    }
    
    
    protected Integer compareNumberValue(Number v1, Number v2) {
	if (v1 == null && v2 == null) {
	    return 0;
	}
	if (v1 == null) {
	    return -1;
	}
	if (v2 == null) {
	    return 1;
	}
	

	// Same type
	if (isEqualsValueType(v1, v2)) {
	    return ((Comparable) v1).compareTo(v2);
	}

	// Double
	Double d1 = v1.doubleValue();
	Double d2 = v2.doubleValue();
	if (isZero(d1) && isZero(d2)) { // -0.0, 0.0
	    return 0;
	}
	return d1.compareTo(d2);

    }
    
    public static boolean isZero(Double value) {
 	return value == null ? false : (value == 0.0 || value == -0.0); 
    }
    
    public static boolean isEqualsValueType(Object v1, Object v2) {
	if (v1 == null || v2 == null) {
	    return false;
	}
	return v1.getClass().equals(v2.getClass());
    }
    

}
