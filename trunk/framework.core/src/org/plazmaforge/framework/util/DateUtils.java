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

package org.plazmaforge.framework.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author ohapon
 *
 */
public class DateUtils {

    /**
     * Return <code>Date</code> by year, month, day
     * @param year
     * @param month: 1-12
     * @param day
     * @return
     */
    public static Date getDate(int year, int month, int day) {
	return getDateTime(year, month, day, 0, 0, 0);
    }
    
    /**
     * Return <code>Date</code> by hour, minute, second
     * @param h
     * @param m
     * @param s
     * @return
     */
    public static Date getTime(int h, int m, int s) {
	return getTime(h, m, s, 0);
    }
    
    /**
     * Return <code>Date</code> by hour, minute, second, millisecond
     * @param h
     * @param m
     * @param s
     * @param ms
     * @return
     */
    public static Date getTime(int h, int m, int s, int ms) {
	return getDateTime(1970, 1, 1, h, m, s, ms);
    }
    
    /**
     * Return <code>Date</code> by year, month, day, hour, minute, second
     * @param year
     * @param month: 1-12
     * @param day
     * @param h
     * @param m
     * @param s
     * @return
     */
    public static Date getDateTime(int year, int month, int day, int h, int m, int s) {
	return getDateTime(year, month, day, h, m, s, 0);
    }
    
    /**
     * Return <code>Date</code> by year, month, day, hour, minute, second, millisecond
     * @param year
     * @param month: 1-12
     * @param day
     * @param h
     * @param m
     * @param s
     * @param ms
     * @return
     */
    public static Date getDateTime(int year, int month, int day, int h, int m, int s, int ms) {
	Calendar calendar = Calendar.getInstance();
	
	calendar.set(Calendar.YEAR, year);
	calendar.set(Calendar.MONTH, month - 1);
	calendar.set(Calendar.DAY_OF_MONTH, day);
	
	calendar.set(Calendar.HOUR_OF_DAY, h);
	calendar.set(Calendar.MINUTE, m);
	calendar.set(Calendar.SECOND, s);
	calendar.set(Calendar.MILLISECOND, ms);	
	
	return calendar.getTime();
    }

}
