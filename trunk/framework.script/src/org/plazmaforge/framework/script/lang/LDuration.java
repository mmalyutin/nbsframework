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

package org.plazmaforge.framework.script.lang;

import java.util.List;

import org.plazmaforge.framework.script.util.CalendarConstants;


public class LDuration extends LInterval implements CalendarConstants {

    
    /**
     * @param value
     */
    public LDuration(long value) {
	super(Type.DURATION, new DurationValue(value));
    }

    @Override
    public String _toString() {
	return "" + getInstant();
    }
    
    
    // <
    @Override
    public LValue _lt(LValue a, LValue b) {
	LValue result = nullResult(a, "<", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL || b == LValue.NULL) {
	    return new LBoolean(ltNull(a, b));
	}	
	
	if (!equalsType(a) || !equalsType(b)) {
	    return super._lt(a, b);
	}
	
	return new LBoolean(a.asLong() < b.asLong()); 
    }
    
    // <=
    @Override
    public LValue _lte(LValue a, LValue b) {
	LValue result = nullResult(a, "<=", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL || b == LValue.NULL) {
	    return new LBoolean(lteNull(a, b));
	}	
	
	if (!equalsType(a) || !equalsType(b)) {
	    return super._lte(a, b);
	}
	return new LBoolean(a.asLong() <= b.asLong()); 
    }

    // >
    @Override
    public LValue _gt(LValue a, LValue b) {
	LValue result = nullResult(a, ">", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL || b == LValue.NULL) {
	    return new LBoolean(gtNull(a, b));
	}	
	
	if (!equalsType(a) || !equalsType(b)) {
	    return super._gt(a, b);
	}
	return new LBoolean(a.asLong() > b.asLong()); 
    }
    
    // >=
    @Override
    public LValue _gte(LValue a, LValue b) {
	LValue result = nullResult(a, ">=", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL || b == LValue.NULL) {
	    return new LBoolean(gteNull(a, b));
	}	
	
	if (!equalsType(a) || !equalsType(b)) {
	    return super._gte(a, b);
	}
	return new LBoolean(a.asLong() >= b.asLong()); 
    }
 
    ////    
    
 // +
    public LValue _add(LValue a, LValue b) {
	
	LValue result = nullResult(a, "+", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL) {
	    // Illegal operation
	    super._add(a, b);
	    //return LValue.NULL;
	}
	
	if (!equalsType(a)) {
	    return super._add(a, b);
	}

	// NULL
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	// NUMBER, DURATION
	if (b.isNumber() || b.isDuration()) {
	    // Duration + Long (ms) = Duration
	    return new LDuration(a.asLong() + b.asLong());
	}
	return super._add(a, b);
    }
    
    // -
    public LValue _sub(LValue a, LValue b) {
	
	LValue result = nullResult(a, "-", b);
	if (result != null) {
	    return result; 
	}

	if (a == LValue.NULL) {
	    // Illegal operation
	    return super._sub(a, b);
	    //return LValue.NULL;
	}
	
	if (!equalsType(a)) {
	    return super._sub(a, b);
	}

	// NULL
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	// NUMBER, DURATION
	if (b.isNumber() || b.isDuration()) {
	    // Duration - Long (ms) = Duration
	    return new LDuration(a.asLong() - b.asLong());
	}
	
	return super._sub(a, b);
    }
    
    ////    
    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	
	// TIME IN MILLISECONDS
	if ("setInstant".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    setInstant(parameter.asLong());
	    return LValue.VOID;
	}
	
	return super._invoke(method, parameters);
    }
    
    protected DurationValue getDurationValue() {
	 return (DurationValue) getValue();
    }

    public long getInstant() {
        return getDurationValue().getInstant();
    }

    public void setInstant(long instant) {
	getDurationValue().setInstant(instant);
    }    
}
