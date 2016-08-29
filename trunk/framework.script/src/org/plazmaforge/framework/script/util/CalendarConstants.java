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

package org.plazmaforge.framework.script.util;

/**
 * 
 * @author ohapon
 *
 */
public interface CalendarConstants {

    int SECONDS_PER_MINUTE = 60;
    
    int MINUTES_PER_HOUR = 60;
    
    int MILLISECONDS_PER_SECOND = 1000;
    
    int HOURS_PER_DAY = 24;
    
    long SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    
    long SECONDS_PER_DAY = SECONDS_PER_HOUR * HOURS_PER_DAY;
    
    int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;
    
    long MILLISECONDS_PER_MINUTE = SECONDS_PER_MINUTE * MILLISECONDS_PER_SECOND;
    
    long MILLISECONDS_PER_HOUR = SECONDS_PER_HOUR * MILLISECONDS_PER_SECOND;
    
    long MILLISECONDS_PER_DAY = SECONDS_PER_DAY * MILLISECONDS_PER_SECOND;
    
   
    // Week/Month/Year
    
    int DAYS_PER_WEEK = 7;
    
    int DAYS_PER_MONTH = 30; // Only for standard month
    
    int MONTHS_PER_YEAR = 12;
    
    long SECONDS_PER_WEEK = DAYS_PER_WEEK * SECONDS_PER_DAY;
    
    long SECONDS_PER_MONTH = DAYS_PER_MONTH * SECONDS_PER_DAY; // Only for standard month
    
    long SECONDS_PER_YEAR = MONTHS_PER_YEAR * SECONDS_PER_MONTH; // Only for standard month and year
    
    long MILLISECONDS_PER_WEEK = SECONDS_PER_WEEK * MILLISECONDS_PER_SECOND;
    
    long MILLISECONDS_PER_MONTH = SECONDS_PER_MONTH * MILLISECONDS_PER_SECOND; // Only for standard month
    
    long MILLISECONDS_PER_YEAR = SECONDS_PER_YEAR * MILLISECONDS_PER_SECOND; // Only for standard month and year
    
}
