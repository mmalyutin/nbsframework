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

import java.util.Comparator;

import org.plazmaforge.framework.script.lang.LValue;

public class ValueComparator implements Comparator<LValue> {

    @Override
    public int compare(LValue v1, LValue v2) {
	if (v1 == null && v2 == null) {
	    return 0;
	}
	if (v1 == null) {
	    return -1;
	}
	if (v2 == null) {
	    return 1;
	}

	//
	if (v1.isNull() && v2.isNull()) {
	    return 0;
	}
	if (v1.isNull()) {
	    return -1;
	}
	if (v2.isNull()) {
	    return 1;
	}
	
	if (isSameType(v1, v2)) {
	    return v1.compareTo(v2);
	}
	
	return v1.toString().compareTo(v2.toString());
    }
    
    
    protected boolean isSameType(LValue v1, LValue v2) {
	return (v1.isNumber() && v2.isNumber())
		|| (v1.isString() && v2.isString())
		|| (v1.isBoolean() && v2.isBoolean())
		|| (v1.isDate() && v2.isDate())
		|| (v1.isList() && v2.isList())
		|| (v1.isMap() && v2.isMap());
		
    }

}
