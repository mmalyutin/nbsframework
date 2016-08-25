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

import org.plazmaforge.framework.script.util.CommonUtils;


/**
 * Base class for LDate/LDateTime/LTime with java.util.Date value. 
 * 
 * @author ohapon
 *
 */
public abstract class LInstant extends LValue {

    protected LInstant(Type type, Date value) {
	super(type, value);
    }
    
    protected abstract LInstant newInstance(long time);
    
    @Override
    public String _toString() {
	return toDateString(); 
    }

    public String toDateString() {
	Calendar calendar = getCalendar();
	
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	return "" + year + "-" + formatDigit2(month) + "-" + formatDigit2(day);
    }
    
    public String toTimeString() {
	Calendar calendar = getCalendar();
	
	int h = calendar.get(Calendar.HOUR_OF_DAY);
	int m = calendar.get(Calendar.MINUTE);
	int s = calendar.get(Calendar.SECOND);
	int ms = calendar.get(Calendar.MILLISECOND);
	
	return "" + formatDigit2(h) + ":" + formatDigit2(m) + ":" + formatDigit2(s) + "." + formatDigit3(ms);
    }
    
    public String toDateTimeString() {
	return toDateString() + " " + toTimeString();
    }
    
    protected String formatDigit2(int value) {
	return "" + (value < 10 ? ("0" + value) : value);
    }

    protected String formatDigit3(int value) {
	return "" + (value < 10 ? ("00" + value) : (value < 100 ? ("0" + value) : value));
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
	
	if (!equalsType(a) || !equalsType(b)) {
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
	
	if (!equalsType(a) || !equalsType(b)) {
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
	
	if (!equalsType(a) || !equalsType(b)) {
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
	
	if (!equalsType(a)) {
	    return super._add(a, b);
	}

	// NULL
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	// NUMBER
	if (b.isNumber()) {
	    // Date/DateTime/Time + Long (ms) = Date
	    return newInstance(a.asDate().getTime() + b.asLong());
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
	
	// NUMBER
	if (b.isNumber()) {
	    // Date - Long (ms) = Date
	    return newInstance(a.asDate().getTime() - b.asLong());
	}
	
	// DATE
	if (b.isInstant()) {
	    // Date/DateTime/Time - Date/DateTime/Time = Long (ms)
	    return new LNumber(a.asDate().getTime() - b.asDate().getTime());
	}
	return super._sub(a, b);
    }
    
    ////
    
    @Override
    public LValue _get(LValue index) {
	if (index.isString()) {
	    return _get(index.asString());
	}
	raiseIllegalMethodException("get");
	return null;
    }

    @Override
    public void _set(LValue index, LValue value) {
	if (index.isString()) {
	    _set(index.asString(), value);
	    return;
	}
	raiseIllegalMethodException("set");
    }

    ////
  
    
    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	
	// TIME IN MILLISECONDS
	if ("getInstant".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getTime());
	    
	} else if ("setInstant".equals(method)) {
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
	
	// HOUR
	} else if ("getHour".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getHour());

	} else if ("setHour".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    setHour(parameter.asInteger());
	    return LValue.VOID;
	    
	// MINUTE
	} else if ("getMinute".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getMinute());

	} else if ("setMinute".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    setMinute(parameter.asInteger());
	    return LValue.VOID;
	    
	// SECOND
	} else if ("getSecond".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getSecond());

	} else if ("setSecond".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    setSecond(parameter.asInteger());
	    return LValue.VOID;

	// MILLISECOND
	} else if ("getMillisecond".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getMillisecond());

	} else if ("setMillisecond".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    setMillisecond(parameter.asInteger());
	    return LValue.VOID;
	}
	
	return super._invoke(method, parameters);
    }
    
    ////
    
    public Calendar getCalendar() {
	Calendar calendar = CommonUtils.getGMTCalendar();
	calendar.setTime((Date) getValue());
	return calendar;
    }

    public long getTime() {
	return asDate().getTime();
    }

    public void setTime(long time) {
	//long truncTime = CommonUtils.truncateTime(time);
	doSetTime(time);
    }
    
    protected void doSetTime(long time) {
	asDate().setTime(time);
    }

    
    public int getYear() {
	return  getCalendar().get(Calendar.YEAR);
    }
    
    public void setYear(int year) {
	Calendar calendar = getCalendar();
	calendar.set(Calendar.YEAR, year);
	doSetTime(calendar.getTimeInMillis());
    }
    
    public int getMonth() {
	return getCalendar().get(Calendar.MONTH) + 1;
    }
    
    public void setMonth(int month) {
	Calendar calendar = getCalendar();
	calendar.set(Calendar.MONTH, month - 1);
	doSetTime(calendar.getTimeInMillis());
    }
    
    public int getDay() {
	return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    public void setDay(int day) {
	Calendar calendar = getCalendar();
	calendar.set(Calendar.DAY_OF_MONTH, day);
	doSetTime(calendar.getTimeInMillis());
    }
    
    public int getHour() {
	return getCalendar().get(Calendar.HOUR_OF_DAY);
    }

    public void setHour(int hour) {
	Calendar calendar = getCalendar();
	calendar.set(Calendar.HOUR_OF_DAY, hour);
	doSetTime(calendar.getTimeInMillis());
    }
    
    public int getMinute() {
	return getCalendar().get(Calendar.MINUTE);
    }

    public void setMinute(int minute) {
	Calendar calendar = getCalendar();
	calendar.set(Calendar.MINUTE, minute);
	doSetTime(calendar.getTimeInMillis());
    }
    
    public int getSecond() {
	return getCalendar().get(Calendar.SECOND);
    }

    public void setSecond(int second) {
	Calendar calendar = getCalendar();
	calendar.set(Calendar.SECOND, second);
	doSetTime(calendar.getTimeInMillis());
    }

    public int getMillisecond() {
	return getCalendar().get(Calendar.MILLISECOND);
    }

    public void setMillisecond(int second) {
	Calendar calendar = getCalendar();
	calendar.set(Calendar.MILLISECOND, second);
	doSetTime(calendar.getTimeInMillis());
    }
    
    
}
