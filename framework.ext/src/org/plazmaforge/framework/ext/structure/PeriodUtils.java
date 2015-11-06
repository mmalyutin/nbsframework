/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
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


package org.plazmaforge.framework.ext.structure;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author ohapon
 *
 */
public class PeriodUtils {

    public static void initDatePeriod(DatePeriod datePeriod) {
	if (datePeriod == null) {
	    return;
	}
	if (DatePeriod.TODAY.equals(datePeriod.getCode())) {
	    initToday(datePeriod);
	} if (DatePeriod.YESTERDAY.equals(datePeriod.getCode())) {
	    initYesterday(datePeriod);
	} if (DatePeriod.TOMORROW.equals(datePeriod.getCode())) {
	    initTomorrow(datePeriod);
	} if (DatePeriod.CURRENT_WEEK.equals(datePeriod.getCode())) {
	    initCurrentWeek(datePeriod);
	} if (DatePeriod.LAST_WEEK.equals(datePeriod.getCode())) {
	    initLastWeek(datePeriod);
	} if (DatePeriod.NEXT_WEEK.equals(datePeriod.getCode())) {
	    initNextWeek(datePeriod);
	} if (DatePeriod.CURRENT_MONTH.equals(datePeriod.getCode())) {
	    initCurrentMonth(datePeriod);
	} if (DatePeriod.LAST_MONTH.equals(datePeriod.getCode())) {
	    initLastMonth(datePeriod);
	} if (DatePeriod.NEXT_MONTH.equals(datePeriod.getCode())) {
	    initNextMonth(datePeriod);
	} if (DatePeriod.CURRENT_YEAR.equals(datePeriod.getCode())) {
	    initCurrentYear(datePeriod);
	} if (DatePeriod.LAST_YEAR.equals(datePeriod.getCode())) {
	    initLastYear(datePeriod);
	} if (DatePeriod.NEXT_YEAR.equals(datePeriod.getCode())) {
	    initNextYear(datePeriod);
	}
    }

    /**
     * Return period type in year (DAY, MONTH, YEAR)
     * @param start
     * @param end
     * @return
     */
    public static String getPeriodType(Date start, Date end) {
	
	if (start == null || end == null) {
	    return DatePeriod.UNKNOWN;
	}
	
	Calendar calendarStart = Calendar.getInstance();
	calendarStart.setTime(start);

	Calendar calendarEnd = Calendar.getInstance();
	calendarEnd.setTime(end);

	int startDay = calendarStart.get(Calendar.DAY_OF_MONTH);
	int startMonth = calendarStart.get(Calendar.MONTH);
	int startYear = calendarStart.get(Calendar.YEAR);

	int endDay = calendarEnd.get(Calendar.DAY_OF_MONTH);
	int endMonth = calendarEnd.get(Calendar.MONTH);
	int endYear = calendarEnd.get(Calendar.YEAR);

	// Custom
	if (startYear != endYear) {
	    return DatePeriod.CUSTOM;
	}
	
	int year = startYear;
	
	// Month or Day
	if (startMonth == endMonth) {
	    
	    // Day
	    if (startDay == endDay) {
		return DatePeriod.DAY;
	    }

	    // If not first Day of Month then custom period
	    if (startDay != 1) {
		return DatePeriod.CUSTOM;
	    }
	    
	    int month = startMonth;
	    
	    int lastDayOfMonth = getLastDayOfMonth(year, month);
	    if (endDay  == lastDayOfMonth) {
		return DatePeriod.MONTH;
	    }
	    
	    return DatePeriod.CUSTOM;
	}
	
	int quarter = getQuarter(startMonth, endMonth);
	if (quarter < 1) {
	    return DatePeriod.CUSTOM;
	}

	// If not first Day of Month then custom period
	if (startDay != 1) {
	    return DatePeriod.CUSTOM;
	}
	int lastDayOfMonth = getLastDayOfMonth(year, endMonth);
	if (endDay == lastDayOfMonth) {
	    return DatePeriod.QUARTER;
	}
	
	return DatePeriod.CUSTOM;
    }
    
    private static int getQuarter(int startMonth, int endMonth) {
	if (startMonth == Calendar.JANUARY && endMonth == Calendar.MARCH) {
	    return 1;
	} else if (startMonth == Calendar.APRIL && endMonth == Calendar.JUNE) {
	    return 2;
	} else if (startMonth == Calendar.JULY && endMonth == Calendar.SEPTEMBER) {
	    return 3;
	} else if (startMonth == Calendar.OCTOBER && endMonth == Calendar.DECEMBER) {
	    return 4;
	}
	return 0;
    }
    
    private static int getLastDayOfMonth(int year, int month) {
	
	Calendar calendar = getNowCalendar();
	
	if (Calendar.DECEMBER == month) {
	    month = Calendar.JANUARY;
	    year++;
	} else {
	    month++;
	}
	
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	calendar.set(Calendar.MONTH, month);
	calendar.set(Calendar.YEAR, year);
	
	calendar.add(Calendar.DAY_OF_MONTH, -1);
	
	return calendar.get(Calendar.DAY_OF_MONTH); 

    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    private static void initToday(DatePeriod datePeriod) {
	Date date = getNowDate();
	truncDate(date);
	
	Date startDate = (Date) date.clone();
	Date endDate = (Date) date.clone();
	
	datePeriod.setPeriodDate(startDate, endDate);
    }

    private static void initYesterday(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();
	calendar.add(Calendar.DAY_OF_MONTH, -1);
	Date date = calendar.getTime();
	truncDate(date);
	
	Date startDate = (Date) date.clone();
	Date endDate = (Date) date.clone();
	
	datePeriod.setPeriodDate(startDate, endDate);
    }

    private static void initTomorrow(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();
	calendar.add(Calendar.DAY_OF_MONTH, 1);
	Date date = calendar.getTime();
	truncDate(date);
	
	Date startDate = (Date) date.clone();
	Date endDate = (Date) date.clone();
	
	datePeriod.setPeriodDate(startDate, endDate);
    }
    
    private static void initCurrentWeek(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();
	Calendar calendars[] = getWeekPeriod(calendar);
	
	Date startDate = calendars[0].getTime();
	truncDate(startDate);

	Date endDate = calendars[1].getTime();
	truncDate(endDate);

	datePeriod.setPeriodDate(startDate, endDate);
    }
    
    
    private static void initLastWeek(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();
	calendar.add(Calendar.DAY_OF_MONTH, -7);
	
	Calendar calendars[] = getWeekPeriod(calendar);
	
	Date startDate = calendars[0].getTime();
	truncDate(startDate);

	Date endDate = calendars[1].getTime();
	truncDate(endDate);

	datePeriod.setPeriodDate(startDate, endDate);
    }

    private static void initNextWeek(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();
	calendar.add(Calendar.DAY_OF_MONTH, 7);
	
	Calendar calendars[] = getWeekPeriod(calendar);
	
	Date startDate = calendars[0].getTime();
	truncDate(startDate);

	Date endDate = calendars[1].getTime();
	truncDate(endDate);

	datePeriod.setPeriodDate(startDate, endDate);
    }
    
    private static Calendar[] getWeekPeriod(Calendar calendar) {
	Calendar[] period = new Calendar[2];
	
	int firstDayOfWeek = calendar.getFirstDayOfWeek();
	Calendar calendar1 = (Calendar) calendar.clone();
	while (true) {
	    if (firstDayOfWeek == calendar1.get(Calendar.DAY_OF_WEEK)) {
		break;
	    }
	    calendar1.add(Calendar.DAY_OF_MONTH, -1);
	}
	Calendar calendar2 = (Calendar) calendar1.clone();
	calendar2.add(Calendar.DAY_OF_MONTH, 7 - 1);
	
	period[0] = calendar1;
	period[1] = calendar2;
	
	return period;
    }

    
    private static void initCurrentMonth(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();
	
	
	Calendar[] calendars = getMonthPeriod(calendar);
	Calendar calendar1 = calendars[0];
	Calendar calendar2 = calendars[1];
	
	Date startDate = calendar1.getTime();
	truncDate(startDate);

	Date endDate = calendar2.getTime();
	truncDate(endDate);

	datePeriod.setPeriodDate(startDate, endDate);

    }

    
    private static void initLastMonth(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();
	
	int month = calendar.get(Calendar.MONTH);
	int year = calendar.get(Calendar.YEAR);
	
	if (Calendar.JANUARY == month) {
	    month = Calendar.DECEMBER;
	    year = year - 1;
	} else {
	    month = month - 1;
	}
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	calendar.set(Calendar.MONTH, month);
	calendar.set(Calendar.YEAR, year);
	
	Calendar[] calendars = getMonthPeriod(calendar);
	Calendar calendar1 = calendars[0];
	Calendar calendar2 = calendars[1];
	
	Date startDate = calendar1.getTime();
	truncDate(startDate);

	Date endDate = calendar2.getTime();
	truncDate(endDate);

	datePeriod.setPeriodDate(startDate, endDate);

    }
    
    private static void initNextMonth(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();
	
	int month = calendar.get(Calendar.MONTH);
	int year = calendar.get(Calendar.YEAR);
	
	if (Calendar.DECEMBER == month) {
	    month = Calendar.JANUARY;
	    year = year + 1;
	} else {
	    month = month + 1;
	}
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	calendar.set(Calendar.MONTH, month);
	calendar.set(Calendar.YEAR, year);
	
	Calendar[] calendars = getMonthPeriod(calendar);
	Calendar calendar1 = calendars[0];
	Calendar calendar2 = calendars[1];
	
	Date startDate = calendar1.getTime();
	truncDate(startDate);

	Date endDate = calendar2.getTime();
	truncDate(endDate);

	datePeriod.setPeriodDate(startDate, endDate);

    }
    
    
    private static void initCurrentYear(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();

	Calendar calendar1 = (Calendar) calendar.clone();
	Calendar calendar2 = (Calendar) calendar.clone();
	
	calendar1.set(Calendar.DAY_OF_MONTH, 1);
	calendar1.set(Calendar.MONTH, Calendar.JANUARY);

	calendar2.set(Calendar.DAY_OF_MONTH, 31);
	calendar2.set(Calendar.MONTH, Calendar.DECEMBER);

	Date startDate = calendar1.getTime();
	truncDate(startDate);

	Date endDate = calendar2.getTime();
	truncDate(endDate);

	datePeriod.setPeriodDate(startDate, endDate);
    }
    
    
    private static void initLastYear(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();
	int year = calendar.get(Calendar.YEAR) - 1;
	Calendar calendar1 = (Calendar) calendar.clone();
	Calendar calendar2 = (Calendar) calendar.clone();
	
	calendar1.set(Calendar.DAY_OF_MONTH, 1);
	calendar1.set(Calendar.MONTH, Calendar.JANUARY);
	calendar1.set(Calendar.YEAR, year);

	calendar2.set(Calendar.DAY_OF_MONTH, 31);
	calendar2.set(Calendar.MONTH, Calendar.DECEMBER);
	calendar2.set(Calendar.YEAR, year);

	Date startDate = calendar1.getTime();
	truncDate(startDate);

	Date endDate = calendar2.getTime();
	truncDate(endDate);

	datePeriod.setPeriodDate(startDate, endDate);
    }

    
    private static void initNextYear(DatePeriod datePeriod) {
	Calendar calendar = getNowCalendar();
	int year = calendar.get(Calendar.YEAR) + 1;
	Calendar calendar1 = (Calendar) calendar.clone();
	Calendar calendar2 = (Calendar) calendar.clone();
	
	calendar1.set(Calendar.DAY_OF_MONTH, 1);
	calendar1.set(Calendar.MONTH, Calendar.JANUARY);
	calendar1.set(Calendar.YEAR, year);

	calendar2.set(Calendar.DAY_OF_MONTH, 31);
	calendar2.set(Calendar.MONTH, Calendar.DECEMBER);
	calendar2.set(Calendar.YEAR, year);

	Date startDate = calendar1.getTime();
	truncDate(startDate);

	Date endDate = calendar2.getTime();
	truncDate(endDate);

	datePeriod.setPeriodDate(startDate, endDate);
    }

    
  
    private static Calendar[] getMonthPeriod(Calendar calendar) {
	Calendar[] period = new Calendar[2];
	
	int month = calendar.get(Calendar.MONTH);
	int year = calendar.get(Calendar.YEAR);
	
	int month1 = month;
	int year1 = year;
	
	int month2 = month;
	int year2 = year;
	
	if (Calendar.DECEMBER == month) {
	    month2 = Calendar.JANUARY;
	    year2 = year2 + 1;
	} else {
	    month2 = month2 + 1;
	}
	
	Calendar calendar1 = getNowCalendar();
	Calendar calendar2 = getNowCalendar();
	
	calendar1.set(Calendar.DAY_OF_MONTH, 1);
	calendar1.set(Calendar.MONTH, month1);
	calendar1.set(Calendar.YEAR, year1);

	calendar2.set(Calendar.DAY_OF_MONTH, 1);
	calendar2.set(Calendar.MONTH, month2);
	calendar2.set(Calendar.YEAR, year2);
	
	calendar2.add(Calendar.DAY_OF_MONTH, -1);
	
	
	period[0] = calendar1;
	period[1] = calendar2;
	
	return period;
    }

    
    
    
    private static void truncDate(Date date) {
	date.setHours(0);
	date.setMinutes(0);
	date.setSeconds(0);
    }
    
    private static Date getNowDate() {
	return getNowCalendar().getTime();
    }
    
    private static Calendar getNowCalendar() {
	Calendar calendar = Calendar.getInstance();
	return calendar;
    }

    private static long getNowMillis() {
	return getNowCalendar().getTimeInMillis();
    }

}
