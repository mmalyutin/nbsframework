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
package org.plazmaforge.framework.script.lang;


/**
 * @author ohapon
 *
 */
public class LBoolean extends LValue {

    public static final LBoolean TRUE = new LBoolean(true);
    
    public static final LBoolean FALSE = new LBoolean(false);
    
    /**
     * @param value
     */
    public LBoolean(Boolean value) {
	super(Type.BOOLEAN, value);
    }

    // &&, and
    @Override
    public LValue _and(LValue a, LValue b) {
	LValue result = nullResult(a, "and", b);
	if (result != null) {
	    return result; 
	}

	if (a == LValue.NULL || b  == LValue.NULL) {
	    return andNullResult(a, b);
	}
	
	if (!a.isBoolean() || !b.isBoolean()) {
	    return super._and(a, b);	    
	}
	
	return new LBoolean(a.asBoolean() && b.asBoolean());
    }
    
    // &
    @Override
    public LValue _bitAnd(LValue a, LValue b) {
	LValue result = nullResult(a, "&", b);
	if (result != null) {
	    return result; 
	}

	if (a == LValue.NULL || b  == LValue.NULL) {
	    return bitAndNullResult(a, b);
	}
	
	if (!a.isBoolean() || !b.isBoolean()) {
	    return super._bitAnd(a, b);	    
	}
	
	return new LBoolean(a.asBoolean() & b.asBoolean());
    }
    

    // ||, or
    @Override
    public LValue _or(LValue a, LValue b) {
	LValue result = nullResult(a, "or", b);
	if (result != null) {
	    return result; 
	}

	if (a == LValue.NULL || b  == LValue.NULL) {
	    return orNullResult(a, b);
	}
	
	if (!a.isBoolean() || !b.isBoolean()) {
	    return super._or(a, b);	    
	}
	
	return new LBoolean(a.asBoolean() || b.asBoolean());
    }
    
    // |
    @Override
    public LValue _bitOr(LValue a, LValue b) {
	LValue result = nullResult(a, "|", b);
	if (result != null) {
	    return result; 
	}

	if (a == LValue.NULL || b  == LValue.NULL) {
	    return bitOrNullResult(a, b);
	}
	
	if (!a.isBoolean() || !b.isBoolean()) {
	    return super._bitOr(a, b);	    
	}
	
	return new LBoolean(a.asBoolean() | b.asBoolean());
    }
    
    
    // xor
    public LValue _xor(LValue a, LValue b) {
	LValue result = nullResult(a, "xor", b);
	if (result != null) {
	    return result; 
	}

	if (a == LValue.NULL || b  == LValue.NULL) {
	    return xorNullResult(a, b);
	}
	
	if (!a.isBoolean() || !b.isBoolean()) {
	    return super._xor(a, b);	    
	}
	
	return new LBoolean(a.asBoolean() ^ b.asBoolean());
    }
    
    // !, not
    @Override
    public LValue _not(LValue a) {
	LValue result = nullResult("not", a);
	if (result != null) {
	    return result; 
	}

	if (a == LValue.NULL) {
	    return LBoolean.FALSE;
	}
	if (!a.isBoolean()) {
	    raiseIllegalOperatorException("not", a);
	}
	return new LBoolean(!a.asBoolean());
	
    }    
    
    // ?
    public LValue _elvis(LValue exp, LValue a, LValue b) {
	LValue result = nullResult(exp, "?", a, b);
	if (result != null) {
	    return result; 
	}
	
	boolean flag;
	if (exp == LValue.NULL) {
	    flag = false;
	} else {
	    if (!exp.isBoolean()) {
		raiseIllegalOperatorException(exp, "?", a, b);
	    }
	    flag = exp.asBoolean().booleanValue();
	}
	
	return flag ? a: b;
    }
    
}
