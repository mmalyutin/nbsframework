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
    
	} else if ("toCalendarMonths".equals(method)) {
	    checkMethod(method, parameters, 0);
	    
	    Calendar start = getCalendar(getStart());
	    Calendar end = getCalendar(getEnd());
	    int months = monthsBetween(start, end);
	    return new LNumber(months);
	} else if ("toCalendarYears".equals(method)) {

	    Calendar start = getCalendar(getStart());
	    Calendar end = getCalendar(getEnd());
	    int years = yearsBetween(start, end);
	    return new LNumber(years);
	}

	
	return super._invoke(method, parameters);
    }
    
    protected int yearsBetween(Calendar start, Calendar end) {
	int months = monthsBetween(start, end);
	int years = months / MONTHS_PER_YEAR;
	return years;
    }
    
    protected int monthsBetween(Calendar start, Calendar end) {
	if (end.compareTo(start) < 0) {
	    Calendar swap = start;
	    start = end;
	    end = swap;
	}

	int startYear = start.get(Calendar.YEAR);
	int endYear = end.get(Calendar.YEAR);

	int startMonth = start.get(Calendar.MONTH);
	int endMonth = end.get(Calendar.MONTH);

	int startDay = start.get(Calendar.DAY_OF_MONTH);
	int endDay = end.get(Calendar.DAY_OF_MONTH);

	// TODO
	// 29 FEB -> 01 MAR: startMonth == Calendar.FEBRUARY && endMonth == Calendar.MARCH && startDay == 29 && endDay == 1
	
	// shift endMonth
	if (endDay < startDay) {
	    if (startYear == endYear) {
		if (startMonth != endMonth) {
		    endMonth--;
		} else {
		    endMonth = startMonth;
		}
	    } else {
		if (endMonth == Calendar.JANUARY) {
		    endYear--;
		    endMonth = Calendar.DECEMBER;
		} else {
		    endMonth--;
		}
	    }
	}

	int years = endYear - startYear;
	int months = 0;
	if (years == 0) {
	    months = endMonth - startMonth;
	} else {
	    int beforeMonths = Calendar.DECEMBER - startMonth;
	    int afterMonths = endMonth + 1;
	    int middleMonths = years > 2 ? ((years - 2) * MONTHS_PER_YEAR) : 0;
	    months = beforeMonths + middleMonths + afterMonths;
	}
	return months;
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
