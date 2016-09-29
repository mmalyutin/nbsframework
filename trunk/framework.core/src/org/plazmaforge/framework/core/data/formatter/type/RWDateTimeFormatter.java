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

package org.plazmaforge.framework.core.data.formatter.type;

import java.util.Date;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public class RWDateTimeFormatter extends RWDateFormatter {

    @Override
    public Date parse(String str) {
	if (str == null) {
	    return null;
	}
	if (str.length() < 10) {
	    return null;
	}
	// TODO: Simple parser
	String dateStr = str.substring(0, 10);
	String[] array = StringUtils.split(dateStr, DEFAULT_DATE_DELIM, true);
	if (array == null || array.length < 5) {
	    return null;
	}
	
	int year = intValue(array[0]) - 1900;
	int month = intValue(array[2]) - 1;
	int day = intValue(array[4]);
	
	int hours = 0;
	int minutes = 0;
	int seconds = 0;
	
	if (str.length() > 10 ) {
	    String timeStr = str.substring(10).trim();
	    array = StringUtils.split(timeStr, RWTimeFormatter.DEFAULT_TIME_DELIM, true);
	    if (array != null && array.length >=4) {
		hours = intValue(array[0]);
		minutes = intValue(array[2]);
		// Seconds is optional
		if (array.length >=5) {
		    seconds = intValue(array[4]);
		}
	    }
	}
	return new Date(year, month, day, hours, minutes, seconds);
	
	//return new GregorianCalendar(year, month, day, hours, minutes, seconds).getTime();
    }
    
    
    @Override
    public String format(Date value) {
	if (value == null) {
	    return null;
	}
	//TODO: Simple formatter
	
	int year = value.getYear() + 1900;
	int month = value.getMonth() + 1;
	int day = value.getDate(); // getDate() !!!

	int hours = value.getHours();
	int minutes = value.getMinutes();
	int seconds = value.getSeconds();	

	//Calendar calendar = Calendar.getInstance();
	//calendar.setTime(date);
	
	//int year = calendar.get(Calendar.YEAR);
	//int month = calendar.get(Calendar.MONTH) + 1;
	//int day = calendar.get(Calendar.DAY_OF_MONTH);
	
	//int hours = calendar.get(Calendar.HOUR_OF_DAY);
	//int minutes = calendar.get(Calendar.MINUTE);
	//int seconds = calendar.get(Calendar.SECOND);	
	
	// yyyy-MM-dd HH:mm:ss
	return "" + year + DEFAULT_DATE_DELIM + toString2(month) + DEFAULT_DATE_DELIM + toString2(day) 
		+ " " + toString2(hours) + RWTimeFormatter.DEFAULT_TIME_DELIM + toString2(minutes)+ RWTimeFormatter.DEFAULT_TIME_DELIM + toString2(seconds);
    }

}
