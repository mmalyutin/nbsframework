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

/**
 * 
 */
package plazma;

import java.util.Date;
import java.util.List;
import java.util.Map;

import plazma.lang.LBoolean;
import plazma.lang.LDate;
import plazma.lang.LExtObject;
import plazma.lang.LList;
import plazma.lang.LMap;
import plazma.lang.LNumber;
import plazma.lang.LString;
import plazma.lang.LValue;

/**
 * @author ohapon
 *
 */
public class ValueAdapter {

    public static LValue fromNativeValue(Object value) {
	//TODO
	if (value == null) {
	    return LValue.NULL;
	}
	if (value instanceof String) {
	    return new LString((String) value);
	}
	if (value instanceof Boolean) {
	    return new LBoolean((Boolean) value);
	}
	if (value instanceof Number) {
	    return new LNumber((Number) value);
	}
	if (value instanceof Date) {
	    return new LDate((Date) value);
	}

	//TODO
	if (value instanceof List) {
	    return new LList((List) value);
	}
	//TODO
	if (value instanceof Map) {
	    return new LMap((Map) value);
	}
	
	return new LExtObject(value); 

	//return value == null ? LValue.NULL : new LValue(value);
    }
    
    public static Object toNativeValue(LValue value) {
	return value == null ? null : value.getValue();
    }
}
