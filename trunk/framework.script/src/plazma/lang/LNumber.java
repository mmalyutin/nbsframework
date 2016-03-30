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

    
    // +
    public LValue _add(LValue that) {
	if (!that.isNumber()) {
	    raiseIllegalOperationException(that);
	}
	return new LNumber(asDouble() + that.asDouble());
    }
    
    // -
    public LValue _sub(LValue that) {
	if (!that.isNumber()) {
	    raiseIllegalOperationException(that);
	}
	return new LNumber(asDouble() - that.asDouble());
    }
    
    // *
    public LValue _mul(LValue that) {
	if (!that.isNumber()) {
	    raiseIllegalOperationException(that);
	}
	return new LNumber(asDouble() * that.asDouble());
    }
    
    // /
    public LValue _div(LValue that) {
	if (!that.isNumber()) {
	    raiseIllegalOperationException(that);
	}
	return new LNumber(asDouble() / that.asDouble());
    }
    
    // ^
    public LValue _pow(LValue that) {
	if (!that.isNumber()) {
	    raiseIllegalOperationException(that);
	}
	return new LNumber(Math.pow(asDouble(), that.asDouble()));
    }
    
    // %
    public LValue _mod(LValue that) {
	if (!that.isNumber()) {
	    raiseIllegalOperationException(that);
	}
	return new LNumber(asDouble() % that.asDouble());
    }
    
    
}
