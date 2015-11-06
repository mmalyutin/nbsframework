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

package org.plazmaforge.framework.function;

public class LogicalFunctions {

    private LogicalFunctions() {
    }

    
    public static Boolean AND(Boolean arguments[]) {
	if (arguments.length == 0) {
	    return null;
	}
	boolean result = true;
	Boolean aboolean[];
	int j = (aboolean = arguments).length;
	for (int i = 0; i < j; i++) {
	    Boolean arg = aboolean[i];
	    result = result && arg.booleanValue();
	    if (!result) {
		return Boolean.valueOf(false);
	    }
	}
	return Boolean.valueOf(result);
    }

    public static Boolean FALSE() {
	return Boolean.FALSE;
    }

    public static Boolean TRUE() {
	return Boolean.TRUE;
    }

    public static Boolean NOT(Boolean boolValue) {
	if (boolValue == null) {
	    return null;
	}
	return Boolean.valueOf(!boolValue.booleanValue());
    }

    public static Boolean OR(Boolean arguments[]) {
	if (arguments.length == 0) {
	    return null;
	}
	boolean result = false;
	Boolean aboolean[];
	int j = (aboolean = arguments).length;
	for (int i = 0; i < j; i++) {
	    Boolean arg = aboolean[i];
	    result = result || arg.booleanValue();
	    if (result) {
		return Boolean.valueOf(true);
	    }
	}
	return Boolean.valueOf(result);
    }

    public static Object IF(Boolean test, Object value1, Object value2) {
	if (test == null) {
	    return null;
	}
	return test.booleanValue() ? value1 : value2;
    }

    public static Boolean EQUALS(Object obj1, Object obj2) {
	if (obj1 != null) {
	    return Boolean.valueOf(obj1.equals(obj2));
	}
	if (obj2 != null) {
	    return Boolean.valueOf(obj2.equals(obj1));
	}
	return Boolean.valueOf(true);
    }

}
