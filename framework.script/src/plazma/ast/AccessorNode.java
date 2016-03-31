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

package plazma.ast;

import plazma.lang.LString;
import plazma.lang.LValue;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AccessorNode implements LNode {

    protected LValue getValue(LValue object, LValue attribute) {
	if (object.isMap()) {
	    return getMapValue(object, attribute);
	} else if (object.isList()) {
	    return getListValue(object, attribute);
	} else if (object.isString()) {
	    return getStringValue(object, attribute);
	}
	throw new RuntimeException("Can't get value: object=" + object + ", attribute=" + attribute);
    }
    
    protected void setValue(LValue object, LValue attribute, LValue value) {
	if (object.isMap()) {
	    setMapValue(object, attribute, value);
	    return;
	} else if (object.isList()) {
	    setListValue(object, attribute, value);
	    return;
	}
	//    else if (object.isString()) {
	//    setStringValue(object, index, value);
	//}
	throw new RuntimeException("Can't set value: object=" + object + ", attribute=" + attribute);
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected LValue getMapValue(LValue object, LValue index) {
    	return object.asMap().get(index);
    }

    protected void setMapValue(LValue object, LValue index, LValue value) {
    	object.asMap().put(index, value);
    }
    
    
    protected LValue getListValue(LValue object, LValue index) {
        int idx = getIndexValue(index);
        // TODO: Check index range
        return object.asList().get(idx);
    }
    
    protected void setListValue(LValue object, LValue index, LValue value) {
        int idx = getIndexValue(index);
        // TODO: Check index range
        object.asList().set(idx, value);
    }
    

    protected LValue getStringValue(LValue value, LValue index) {
        int idx = getIndexValue(index);
        // TODO: Check index range
        return new LString(String.valueOf(value.asString().charAt(idx)));
    }


    protected int getIndexValue(LValue index) {
        if (!index.isNumber()) {
            throw new RuntimeException("Illegal expression: " + /*+ expression +*/ "[" + index + "]");
        }
        return index.asLong().intValue();
    }

}
