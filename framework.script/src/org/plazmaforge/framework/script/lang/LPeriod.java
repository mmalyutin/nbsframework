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

import java.util.Calendar;
import java.util.List;

import org.plazmaforge.framework.script.util.CalendarConstants;
import org.plazmaforge.framework.script.util.CommonUtils;


public class LPeriod extends LInterval implements CalendarConstants {

    
    /**
     * @param value
     */
    public LPeriod(long start, long end) {
	super(Type.PERIOD, new PeriodValue(start, end));
    }

    
    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	
	// START TIME IN MILLISECONDS
	if ("getStart".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getStart());
	} else if ("setStart".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number or Date/DateTime/Time");
	    }
	    setStart(parameter.asLong());
	    return LValue.VOID;
	

	// END TIME IN MILLISECONDS	    
	} else if ("getEnd".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(getEnd());
	} else if ("setEnd".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number or Date/DateTime/Time");
	    }
	    setEnd(parameter.asLong());
	    return LValue.VOID;
    
	    
	} else if ("toCalendardWeeks".equals(method)) {
	    
	    //TODO
	    checkMethod(method, parameters, 0);
	    return new LNumber(getInstant() / MILLISECONDS_PER_WEEK);   // milliseconds -> weeks (standard week)
	    
	    
	} else if ("toCalendarMonths".equals(method)) {
	    checkMethod(method, parameters, 0);
	    
	    Calendar startCalendar = getCalendar(getStart());
	    Calendar endCalendar = getCalendar(getEnd());

	    int startYear = startCalendar.get(Calendar.YEAR);
	    int endYear = endCalendar.get(Calendar.YEAR);
	    
	    int startMonth = startCalendar.get(Calendar.MONTH);
	    int endMonth = endCalendar.get(Calendar.MONTH);

	    int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
	    int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);
	    
	    int years = endYear - startYear;
	    
	    int months = 0;
	    if (years == 0) {
		months = getMonthsInYear(startMonth, startDay, endMonth, endDay);
	    } else {
		int beforeMonths = getMonthsInYear(startMonth, startDay, Calendar.DECEMBER, 31);
		int afterMonths = years > 0 ? getMonthsInYear(Calendar.JANUARY, 1, endMonth, endDay) : 0;
		int middleMonths = years > 1 ? (years - 2 * 12) : 0;
		months = beforeMonths + middleMonths + afterMonths;
		
	    }
	    
	    return new LNumber(months);
	} else if ("toCalendarYears".equals(method)) {
	    
	    //TODO
	    checkMethod(method, parameters, 0);
	    return new LNumber(getInstant() / MILLISECONDS_PER_YEAR);   // milliseconds -> years (standard years)
	}

	
	return super._invoke(method, parameters);
    }
    
    protected int getMonthsInYear(int startMonth, int startDay, int endMonth, int endDay) {
	
	if (startMonth == endMonth) {
	    if (startDay == endDay) {
		return 1;
	    }
	    return 0;
	}
	// 29 FEB -> 01 MAR
	if (startMonth == Calendar.FEBRUARY && endMonth == Calendar.MARCH && startDay == 29 && endDay == 1) {
	    return 0;
	}
	int months = endMonth - startMonth;
	if (endDay < startDay) {
	    months--;
	}
	return months;
    }
    
    protected int getMonthsInYear(int endMonth, int endDay) {
	return getMonthsInYear(0, 1, endMonth, endDay);
    }
    

    @Override
    public String _toString() {
	return "" + getStart() + ":" + getEnd();
    }    
    
    protected PeriodValue getPeriodValue() {
	return (PeriodValue) getValue();
    }

    public long getInstant() {
	return getPeriodValue().getInstant();
    }

    protected long getStart() {
	return getPeriodValue().getStart();
    }

    protected void setStart(long start) {
	getPeriodValue().setStart(start);
    }
    
    protected long getEnd() {
	return getPeriodValue().getEnd();
    }
    
    protected void setEnd(long end) {
	getPeriodValue().setEnd(end);
    }

    protected Calendar getCalendar(long time) {
	return CommonUtils.getCalendar(time); 
    }
    
}
