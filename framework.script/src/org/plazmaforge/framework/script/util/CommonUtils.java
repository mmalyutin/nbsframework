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
	return getDate(year, month, day, 0, 0, 0, 0);
    }

    public static Date getDate(int year, int month, int day, int h, int m, int s, int ms) {
	Calendar calendar = getCalendar(year, month, day, h, m, s, ms);
	return calendar.getTime();
    }

    public static Date getDate(long time) {
	Calendar calendar = getCalendar(time);
	return calendar.getTime();
    }
    
    public static Calendar getCalendar(int year, int month, int day, int h, int m, int s, int ms) {

	// WARNING: All date in GMT time zone
	Calendar calendar = getGMTCalendar();
	
	calendar.set(Calendar.YEAR, year);
	calendar.set(Calendar.MONTH, month);
	calendar.set(Calendar.DAY_OF_MONTH, day);

	calendar.clear(Calendar.HOUR_OF_DAY);
	calendar.clear(Calendar.MINUTE);
	calendar.clear(Calendar.SECOND);
	calendar.clear(Calendar.MILLISECOND);

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
    
    public static long truncateTime(long time) {
	return (time / DAY_TIME) * (DAY_TIME);
    }
    
}
