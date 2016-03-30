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
    public LValue _lt(LValue that) {
	if (!that.isNumber()) {
	    return super._lt(that);
	}
	return new LBoolean(asDouble() < that.asDouble());
    }
    
    // <=
    public LValue _lte(LValue that) {
	if (!that.isNumber()) {
	    return super._lte(that);
	}
	return new LBoolean(asDouble() <= that.asDouble());
    }

    // >
    public LValue _gt(LValue that) {
	if (!that.isNumber()) {
	    return super._gt(that);
	}
	return new LBoolean(asDouble() > that.asDouble());
    }
    
    // >=
    public LValue _gte(LValue that) {
	if (!that.isNumber()) {
	    return super._gte(that);
	}
	return new LBoolean(asDouble() >= that.asDouble());
    }
    
    //////
    
    // +
    public LValue _add(LValue that) {
	if (!that.isNumber()) {
	    return super._add(that);
	}
	return new LNumber(asDouble() + that.asDouble());
    }
    
    // -
    public LValue _sub(LValue that) {
	if (!that.isNumber()) {
	    return super._sub(that);
	}
	return new LNumber(asDouble() - that.asDouble());
    }
    
    // *
    public LValue _mul(LValue that) {
	if (!that.isNumber()) {
	    return super._mul(that);
	}
	return new LNumber(asDouble() * that.asDouble());
    }
    
    // /
    public LValue _div(LValue that) {
	if (!that.isNumber()) {
	    return super._div(that);
	}
	return new LNumber(asDouble() / that.asDouble());
    }
    
    // ^
    public LValue _pow(LValue that) {
	if (!that.isNumber()) {
	    return super._pow(that);
	}
	return new LNumber(Math.pow(asDouble(), that.asDouble()));
    }
    
    // %
    public LValue _mod(LValue that) {
	if (!that.isNumber()) {
	    return super._mod(that);
	}
	return new LNumber(asDouble() % that.asDouble());
    }
    
    
}
