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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ohapon
 *
 */
public class LDate extends LValue {

    /**
     * @param value
     */
    public LDate(Date value) {
	super(Type.DATE, value);
    }

    public LDate(long time) {
	//TODO: truncate time
	super(Type.DATE, new Date(time));
    }
    
    @Override
    public String _toString() {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime((Date) getValue());
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	return "" + year + "-" + (month < 9 ? ("0" + month) : month) + "-" + ((day < 9 ? ("0" + day) : day));
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
	
	if (!a.isDate() || !b.isDate()) {
	    return super._lt(a, b);
	}
	
	return new LBoolean(a.asDate().getTime() < b.asDate().getTime()); 
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
	
	if (!a.isDate() || !b.isDate()) {
	    return super._lte(a, b);
	}
	return new LBoolean(a.asDate().getTime() <= b.asDate().getTime()); 
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
	
	if (!a.isDate() || !b.isDate()) {
	    return super._gt(a, b);
	}
	return new LBoolean(a.asDate().getTime() > b.asDate().getTime()); 
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
	
	if (!a.isDate() || !b.isDate()) {
	    return super._gte(a, b);
	}
	return new LBoolean(a.asDate().getTime() >= b.asDate().getTime()); 
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
	
	if (!a.isDate()) {
	    return super._add(a, b);
	}

	// NULL
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	// NUMBER
	if (b.isNumber()) {
	    // Date + Long (ms) = Date
	    return new LDate(a.asDate().getTime() + b.asLong());
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
	
	if (!a.isDate()) {
	    return super._sub(a, b);
	}

	// NULL
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	// NUMBER
	if (b.isNumber()) {
	    // Date - Long (ms) = Date
	    return new LDate(a.asDate().getTime() - b.asLong());
	}
	
	// DATE
	if (b.isDate()) {
	    // Date - Date = Long (ms)
	    return new LNumber(a.asDate().getTime() - b.asDate().getTime());
	}
	return super._sub(a, b);
    }
    
    ////
    
    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	
	// TIME
	if ("getTime".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getTime());
	    
	} else if ("setTime".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    setTime(parameter.asLong());
	    return LValue.VOID;
	    
	// YEAR    
	} else if ("getYear".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getYear());
	    
	}  else if ("setYear".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    setYear(parameter.asInteger());
	    return LValue.VOID;

	// MONTH
	} else if ("getMonth".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getMonth());

	} else if ("setMonth".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    setMonth(parameter.asInteger());
	    return LValue.VOID;
	    
	// DAY
	} else if ("getDay".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getDay());

	} else if ("setDay".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    setDay(parameter.asInteger());
	    return LValue.VOID;
	    
	}
	
	return super._invoke(method, parameters);
    }
    
    ////
    
    public Calendar getCalendar() {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime((Date) getValue());
	return calendar;
    }

    public long getTime() {
	return asDate().getTime();
    }

    public void setTime(long time) {
	// TODO: truncate time
	asDate().setTime(time);
    }
    
    public int getYear() {
	return  getCalendar().get(Calendar.YEAR);
    }
    
    public void setYear(int year) {
	Calendar calendar = getCalendar();
	calendar.set(Calendar.YEAR, year);
	setTime(calendar.getTimeInMillis());
    }
    
    public int getMonth() {
	return getCalendar().get(Calendar.MONTH) + 1;
    }
    
    public void setMonth(int month) {
	Calendar calendar = getCalendar();
	calendar.set(Calendar.MONTH, month - 1);
	setTime(calendar.getTimeInMillis());
    }
    
    public int getDay() {
	return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    public void setDay(int day) {
	Calendar calendar = getCalendar();
	calendar.set(Calendar.DAY_OF_MONTH, day);
	setTime(calendar.getTimeInMillis());
    }
    
    
}
