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

/**
 * 
 */
package org.plazmaforge.framework.script.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 
 * Common Utils
 * 
 * @author ohapon
 *
 */
public class CommonUtils {

    public static long DAY_TIME = 24 * 60 * 60 * 1000; 
	    
    public static Date getDate(int year, int month, int day) {
	return getDateTime(year, month, day, 0, 0, 0, 0);
    }

    public static Date getDateTime(int year, int month, int day, int h, int m, int s, int ms) {
	Calendar calendar = getCalendar(year, month, day, h, m, s, ms);
	return calendar.getTime();
    }
    
    public static Date getTime(int h, int m, int s, int ms) {
	// Set start date 1970 Jan 1
	Calendar calendar = getCalendar(1970, 0, 1, h, m, s, ms);
	return calendar.getTime();
    }
    

    public static Date getDate(long time) {
	time = truncateTime(time);
	Calendar calendar = getCalendar(time);
	return calendar.getTime();
    }

    public static Date getDateTime(long time) {
	Calendar calendar = getCalendar(time);
	return calendar.getTime();
    }
    
    public static Date getTime(long time) {
	time = shiftTime(time);
	Calendar calendar = getCalendar(time);
	return calendar.getTime();
    }
    
    
    public static Calendar getCalendar(int year, int month, int day, int h, int m, int s, int ms) {

	// WARNING: All date in GMT time zone
	Calendar calendar = getGMTCalendar();

	// Date
	setDate(calendar, year, month, day);
	
	// Time
	setTime(calendar, h, m, s, ms);
	
	return calendar;

    }
    
    public static Calendar getCalendar(long time) {
	Calendar calendar = getGMTCalendar();
	calendar.setTimeInMillis(time);
	return calendar;
    }

    public static Calendar getGMTCalendar() {
	return Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    }
    
    
    public static Calendar getZCalendar() {
	Calendar calendar1 = getGMTCalendar();
	Calendar calendar2 = Calendar.getInstance();
	int offset = calendar2.get(Calendar.ZONE_OFFSET);
	calendar1.setTimeInMillis(calendar1.getTimeInMillis() + offset);
	return calendar1;
    }    
    
    public static void setDate(Calendar calendar, int year, int month, int day) {
	calendar.set(Calendar.YEAR, year);
	calendar.set(Calendar.MONTH, month);
	calendar.set(Calendar.DAY_OF_MONTH, day);
    }
    
    public static void setTime(Calendar calendar, int h, int m, int s, int ms) {
	//calendar.clear(Calendar.HOUR_OF_DAY);
	//calendar.clear(Calendar.MINUTE);
	//calendar.clear(Calendar.SECOND);
	//calendar.clear(Calendar.MILLISECOND);

	calendar.set(Calendar.HOUR_OF_DAY, h);
	calendar.set(Calendar.MINUTE, m);
	calendar.set(Calendar.SECOND, s);
	calendar.set(Calendar.MILLISECOND, ms);
    }
    
    
    public static long truncateTime(long time) {
	return (time / DAY_TIME) * (DAY_TIME);
    }

    public static long shiftTime(long time) {
	return time <= DAY_TIME ? time: (time - truncateTime(time));
    }
    
    public static Date newDate() {
	// WARNING: All date in GMT time zone
	Calendar calendar = getZCalendar();

	// Reset (truncate) time
	setTime(calendar, 0, 0, 0, 0);
	
	return calendar.getTime();
    }

    public static Date newDateTime() {
	// WARNING: All date in GMT time zone
	Calendar calendar = getZCalendar();
	
	return calendar.getTime();
    }

    public static Date newTime() {
	// WARNING: All date in GMT time zone
	Calendar calendar = getZCalendar();

	// Set start date 1970 Jan 1
	setDate(calendar, 1970, 0, 1);

	// Reset
	//setTime(calendar, 0, 0, 0, 0);
	
	return calendar.getTime();
    }
    
    
    
    
}
