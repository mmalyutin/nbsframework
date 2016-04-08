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
package plazma.lang;

import java.util.List;

import plazma.ast.LNode;

/**
 * @author ohapon
 *
 */
public class LString extends LValue {

    /**
     * @param value
     */
    public LString(String value) {
	super(Type.STRING, value);
    } 
    
    
    // <
    @Override
    public LValue _lt(LValue that) {
	if (!that.isString()) {
	    return super._lt(that);
	}
	return new LBoolean(asString().compareTo(that.asString()) < 0);
    }
    
    // <=
    @Override
    public LValue _lte(LValue that) {
	if (!that.isString()) {
	    return super._lte(that);
	}
	return new LBoolean(asString().compareTo(that.asString()) <= 0);
    }

    // >
    @Override
    public LValue _gt(LValue that) {
	if (!that.isString()) {
	    return super._gt(that);
	}
	return new LBoolean(asString().compareTo(that.asString()) > 0);
    }
    
    // >=
    @Override
    public LValue _gte(LValue that) {
	if (!that.isString()) {
	    return super._gte(that);
	}
	return new LBoolean(asString().compareTo(that.asString()) > 0);
    }
    
    //////    

    // +
    @Override
    public LValue _add(LValue a, LValue b) {
	LValue result = nullResult(a, "+", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}

	if (!a.isString()) {
	    return super._add(a, b);
	}
	
	return new LString(a.asString() + "" + b.toString());
    }
    
    // *
    @Override
    public LValue _mul(LValue a, LValue b) {
	
	LValue result = nullResult(a, "*", b);
	if (result != null) {
	    return result; 
	}

	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	
	if (b == LValue.NULL) {
	    raiseIllegalMethodException("" + a + " * " + b + ". Right argument must be >= 1");
	}

	if (!b.isNumber()) {
	    return super._mul(a, b);
	}
	
	
	int stop = b.asInteger();
	if (stop < 1) {
	    raiseIllegalMethodException("" + a + " * " + b + ". Right argument must be >= 1");
	}
	String element = a.asString();
	StringBuilder str = new StringBuilder();
	for (int i = 0; i < stop; i++) {
	    str.append(element);
	}
	return new LString(str.toString());
    }
    
    ////
    
    @Override
    public LValue _get(LValue index) {
	return getStringValue(index);
    }

    @Override
    public void _set(LValue index, LValue value) {
	raiseIllegalMethodException("set");
    }
    
    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	if ("size".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asString().length());
	}
	return super._invoke(method, parameters);
    }
    
    ////
    
    protected LValue getStringValue(LValue index) {
        int idx = getIndexValue(index);
        // TODO: Check index range
        return new LString(String.valueOf(asString().charAt(idx)));
    }
    
}
