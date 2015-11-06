/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.function;

import java.util.Calendar;
import java.util.Date;

public class DateTimeFunctions {
    
    public DateTimeFunctions() {
    }

    public static Date NOW() {
	return new Date();
    }

    public static Date TODAY() {
	return NOW();
    }


    public static Integer YEAR(Date date) {
	return getFieldValue(date, Calendar.YEAR);
    }

    public static Integer MONTH(Date date) {
	Integer value = getFieldValue(date, Calendar.MONTH);
	return value == null ? null : value + 1;
    }

    public static Integer DAY(Date date) {
	return getFieldValue(date, Calendar.DAY_OF_MONTH);
    }

    public static Integer HOUR(Date date) {
	return getFieldValue(date, Calendar.HOUR);
    }

    public static Integer MINUTE(Date date) {
	return getFieldValue(date, Calendar.MINUTE);
    }

    public static Integer SECOND(Date date) {
	return getFieldValue(date, Calendar.SECOND);
    }

    
    ////
    
    
    
    public static Integer WEEKDAY(Date date) {
	return WEEKDAY(date, false);
    }

    public static Integer WEEKDAY(Date date, Boolean isSundayFirstDay) {
	Integer dayOfWeek = getFieldValue(date, Calendar.DAY_OF_WEEK);
	if (dayOfWeek == null)
	    return null;
	if (isSundayFirstDay.booleanValue())
	    return dayOfWeek;
	if (dayOfWeek.intValue() == 1)
	    return 7;
	else
	    return dayOfWeek - 1;
    }


    public static Date DATE(Integer year, Integer month, Integer dayOfMonth) {
	if (year == null || month == null || dayOfMonth == null) {
	    return null;
	}
	Calendar calendar = Calendar.getInstance();
	calendar.set(Calendar.YEAR, year);
	calendar.set(Calendar.MONTH, month);
	calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	return calendar.getTime();
    }

    public static Long DATEVALUE(Date date) {
	if (date == null) {
	    return null; 
	}
	return date.getTime();
    }
    
    
    ////
    

    /*
    //[TIMEFORMAT or FORMATTIME]
    public static String TIME(Integer hours, Integer minutes, Integer seconds) {
	return TIME(hours, minutes, seconds, null);
    }

    //[TIMEFORMAT or FORMATTIME]
    public static String TIME(Integer hours, Integer minutes, Integer seconds, String timePattern) {
	if (hours == null || minutes == null || seconds == null)
	    return null;
	LocalTime lt = new LocalTime(hours.intValue(), minutes.intValue(),
		seconds.intValue());
	if (timePattern == null)
	    return lt.toString(DateTimeFormat.longTime());
	try {
	    org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat
		    .forPattern(timePattern);
	    return lt.toString(dtf);
	} catch (IllegalArgumentException ex) {
	    return lt.toString(DateTimeFormat.longTime());
	}
    }

    public static Date EDATE(Object dateObject, Integer months) {
	Date convertedDate = convertDateObject(dateObject);
	if (convertedDate == null) {
	    return null;
	} else {
	    DateTime dt = new DateTime(convertedDate);
	    dt.plusMonths(months.intValue());
	    return dt.toDate();
	}
    }

    public static Date WORKDAY(Object dateObject, Integer workdays) {
	Date convertedDate = convertDateObject(dateObject);
	if (convertedDate == null)
	    return null;
	DateTime cursorDT = new DateTime(convertedDate);
	for (int remainingDays = workdays.intValue(); remainingDays > 0;) {
	    int dayOfWeek = cursorDT.getDayOfWeek();
	    if (dayOfWeek != 6 && dayOfWeek != 7)
		remainingDays--;
	    cursorDT.plusDays(1);
	}

	return cursorDT.toDate();
    }

    public static Integer NETWORKDAYS(Object startDate, Object endDate) {
	Date startDateObj = convertDateObject(startDate);
	Date endDateObj = convertDateObject(endDate);
	if (startDateObj == null || endDateObj == null)
	    return null;
	DateTime cursorDateTime = new DateTime(startDateObj);
	DateTime endDateTime = new DateTime(endDateObj);
	int workingDays = 0;
	if (cursorDateTime.isAfter(endDateTime)) {
	    DateTime tmp = cursorDateTime;
	    cursorDateTime = endDateTime;
	    endDateTime = tmp;
	}
	for (; Days.daysBetween(cursorDateTime, endDateTime).getDays() > 0; cursorDateTime
		.plusDays(1)) {
	    int dayOfWeek = cursorDateTime.getDayOfWeek();
	    if (dayOfWeek != 6 && dayOfWeek != 7)
		workingDays++;
	}

	return Integer.valueOf(workingDays);
    }

    public static Integer DAYS(Object startDate, Object endDate) {
	Date startDateObj = convertDateObject(startDate);
	Date endDateObj = convertDateObject(endDate);
	if (startDateObj == null || endDateObj == null) {
	    return null;
	} else {
	    DateTime dt1 = new DateTime(startDateObj);
	    DateTime dt2 = new DateTime(endDateObj);
	    return Integer.valueOf(Days.daysBetween(dt1, dt2).getDays());
	}
    }
    */

    public static Integer DAYSINMONTH(Date date) {
	if (date == null) {
	    return null;
	}
	Date lastDateOfMonth = LASTDATEOFMONTH(date);
	return getFieldValue(lastDateOfMonth, Calendar.DAY_OF_MONTH);
    }

    /*
    public static Integer DAYSINYEAR(Object dateObj) {
	Date date = convertDateObject(dateObj);
	if (date == null) {
	    return null;
	} else {
	    DateTime dt = new DateTime(date);
	    return Integer.valueOf(dt.dayOfYear().getMaximumValue());
	}
    }

    public static Integer WEEKS(Object startDate, Object endDate) {
	Date startDateObj = convertDateObject(startDate);
	Date endDateObj = convertDateObject(endDate);
	if (startDateObj == null || endDateObj == null) {
	    return null;
	} else {
	    DateTime dt1 = new DateTime(startDateObj);
	    DateTime dt2 = new DateTime(endDateObj);
	    return Integer.valueOf(Weeks.weeksBetween(dt1, dt2).getWeeks());
	}
    }

    public static Integer WEEKSINYEAR(Object dateObj) {
	Date date = convertDateObject(dateObj);
	if (date == null) {
	    return null;
	} else {
	    DateTime dt = new DateTime(date);
	    return Integer.valueOf(dt.weekOfWeekyear().getMaximumValue());
	}
    }

    public static Integer WEEKNUM(Object dateObj) {
	Date date = convertDateObject(dateObj);
	if (date == null) {
	    return null;
	} else {
	    DateTime dt = new DateTime(date);
	    return Integer.valueOf(dt.getWeekOfWeekyear());
	}
    }

    public static Integer MONTHS(Object startDate, Object endDate) {
	Date startDateObj = convertDateObject(startDate);
	Date endDateObj = convertDateObject(endDate);
	if (startDateObj == null || endDateObj == null) {
	    return null;
	} else {
	    DateTime dt1 = new DateTime(startDateObj);
	    DateTime dt2 = new DateTime(endDateObj);
	    return Integer.valueOf(Months.monthsBetween(dt1, dt2).getMonths());
	}
    }

    public static Integer YEARS(Object startDate, Object endDate) {
	Date startDateObj = convertDateObject(startDate);
	Date endDateObj = convertDateObject(endDate);
	if (startDateObj == null || endDateObj == null) {
	    return null;
	} else {
	    DateTime dt1 = new DateTime(startDateObj);
	    DateTime dt2 = new DateTime(endDateObj);
	    return Integer.valueOf(Years.yearsBetween(dt1, dt2).getYears());
	}
    }

    public static Boolean ISLEAPYEAR(Object dateObj) {
	Date date = convertDateObject(dateObj);
	if (date == null) {
	    return null;
	} else {
	    DateTime dt = new DateTime(date);
	    return Boolean.valueOf(dt.year().isLeap());
	}
    }

    public static String DATEFORMAT(Date dateObj, String formatPattern) {
	if (dateObj == null) {
	    return null;
	} else {
	    org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat
		    .forPattern(formatPattern);
	    DateTime dt = new DateTime(dateObj);
	    return dt.toString(formatter);
	}
    }

    private static Date convertDateObject(Object dateObject) {
	if (dateObject == null)
	    return null;
	if (dateObject instanceof String) {
	    SimpleDateFormat simpleFormat = new SimpleDateFormat();
	    try {
		return simpleFormat.parse((String) dateObject);
	    } catch (ParseException e) {
		return null;
	    }
	}
	if (dateObject instanceof Long)
	    return new Date(((Long) dateObject).longValue());
	if (dateObject instanceof Date)
	    return (Date) dateObject;
	else
	    return null;
    }

    private static Integer getCalendarFieldFromDate(Object dateObject, int field) {
	Date convertedDate = convertDateObject(dateObject);
	if (convertedDate == null) {
	    return null;
	} else {
	    Calendar cal = new GregorianCalendar();
	    cal.setTime(convertedDate);
	    return Integer.valueOf(cal.get(field));
	}
    }
    */

    ////
    
    public static Date PREVDATE(Date date) {
 	if (date == null) {
 	    return null;
 	}
 	Calendar calendar = getCalendar(date);
 	calendar.setTime(date);
 	calendar.add(Calendar.DAY_OF_MONTH, -1);
 	return calendar.getTime();
     }

     public static Date NEXTDATE(Date date) {
 	if (date == null) {
 	    return null;
 	}
 	Calendar calendar = getCalendar(date);
 	calendar.add(Calendar.DAY_OF_MONTH, 1);
 	return calendar.getTime();
     }    
    
     
    //
     
    public static Date FIRSTDATEOFMONTH(Integer year, Integer month) {
	if (year == null || month == null) {
	    return null;
	}
	return DATE(year, month, 1);
    }

    public static Date FIRSTDATEOFMONTH(Date date) {
	if (date == null) {
	    return null;
	}
	Calendar calendar = getCalendar(date);
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.DAY_OF_MONTH);
	return FIRSTDATEOFMONTH(year, month);
    }
	
    //
    
    public static Date LASTDATEOFMONTH(Integer year, Integer month) {
	if (year == null || month == null) {
	    return null;
	}
	if (month.intValue() == 12) {
	    year = year + 1;
	    month = 1;
	} else {
	    month++;
	}
	return PREVDATE(DATE(year, month, 1));
    }
       
    public static Date LASTDATEOFMONTH(Date date) {
	Calendar calendar = getCalendar(date);
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.DAY_OF_MONTH);
	return LASTDATEOFMONTH(year, month);
    }
       
    /////
    
    private static Calendar getCalendar(Date date) {
	if (date == null) {
	    return null;
	}
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
	return calendar;
    }
    
    private static Integer getFieldValue(Date date, int field) {
	Calendar calendar = getCalendar(date);
	return calendar == null ? null : calendar.get(field);
    }
}
