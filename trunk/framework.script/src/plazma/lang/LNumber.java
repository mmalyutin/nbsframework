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


public class LNumber extends LValue {

    /**
     * @param value
     */
    public LNumber(Number value) {
	super(Type.NUMBER, value);
    }


    // <
    @Override
    public LValue _lt(LValue that) {
	if (!that.isNumber()) {
	    return super._lt(that);
	}
	return new LBoolean(asDouble() < that.asDouble());
    }
    
    // <=
    @Override
    public LValue _lte(LValue that) {
	if (!that.isNumber()) {
	    return super._lte(that);
	}
	return new LBoolean(asDouble() <= that.asDouble());
    }

    // >
    @Override
    public LValue _gt(LValue that) {
	if (!that.isNumber()) {
	    return super._gt(that);
	}
	return new LBoolean(asDouble() > that.asDouble());
    }
    
    // >=
    @Override
    public LValue _gte(LValue that) {
	if (!that.isNumber()) {
	    return super._gte(that);
	}
	return new LBoolean(asDouble() >= that.asDouble());
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
	if (a == LValue.NULL) {
	    a = new LNumber(0);
	}
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	if (!a.isNumber() || !b.isNumber()) {
	    return super._add(a, b);
	}
	
	// Integer
	if (a.isInteger() && b.isInteger()) {
	    return new LNumber(a.asInteger() + b.asInteger());
	}
	return new LNumber(a.asDouble() + b.asDouble());
    }
    
    // -
    @Override
    public LValue _sub(LValue a, LValue b) {
	
	LValue result = nullResult(a, "-", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	if (a == LValue.NULL) {
	    a = new LNumber(0);
	}
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	if (!a.isNumber() || !b.isNumber()) {
	    return super._sub(a, b);
	}
	
	// Integer
	if (a.isInteger() && b.isInteger()) {
	    return new LNumber(a.asInteger() - b.asInteger());
	}
	return new LNumber(a.asDouble() - b.asDouble());
    }
    
    // *
    @Override
    public LValue _mul(LValue that) {
	if (!that.isNumber()) {
	    return super._mul(that);
	}
	// Integer
	if (that.isInteger()) {
	    return new LNumber(asInteger() * that.asInteger());
	}
	return new LNumber(asDouble() * that.asDouble());
    }
    
    // /
    @Override
    public LValue _div(LValue that) {
	if (!that.isNumber()) {
	    return super._div(that);
	}
	// Integer ???
	return new LNumber(asDouble() / that.asDouble());
    }
    
    // ^
    @Override
    public LValue _pow(LValue that) {
	if (!that.isNumber()) {
	    return super._pow(that);
	}
	// Integer ???
	return new LNumber(Math.pow(asDouble(), that.asDouble()));
    }
    
    // %
    @Override
    public LValue _mod(LValue that) {
	if (!that.isNumber()) {
	    return super._mod(that);
	}
	return new LNumber(asDouble() % that.asDouble());
    }
    
    
}
