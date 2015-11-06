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

import java.io.Serializable;


public class TimeSheet implements Serializable {

    private static final long serialVersionUID = -960529169512566202L;
    

    private int days;
    
    private int hours;
    
    private int minutes;

    public TimeSheet() {
	super();
    }

    public TimeSheet(int days, int hours, int minutes) {
	super();
	this.days = days;
	this.hours = hours;
	this.minutes = minutes;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    
    public void setTime(int days, int hours, int minutes) {
	setDays(days);
	setHours(hours);
	setMinutes(minutes);
    }
    
    public String getFormatString() {
	//return FormatUtils.formatTimeSheet(days, hours, minutes);
	return formatTimeSheet(days, hours, minutes);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////
    //
    // FormatUtils
    //
    ////////////////////////////////////////////////////////////////////////////////////
    
    private static String formatTimeSheet(int days, int hours, int minutes) {
	return formatTimeSheet(days, hours, minutes, true);
    }
    
    private static String formatTimeSheet(int days, int hours, int minutes, boolean useEmpty) {
	days = pozitiveInt(days);
	hours = pozitiveInt(hours);
	minutes = pozitiveInt(minutes);
	
	if (useEmpty && days == 0 && hours == 0 && minutes == 0) {
	    return "";
	}
	StringBuffer buf = new StringBuffer();
	boolean flag = false;
	if (!useEmpty || (useEmpty && days > 0)) {
	    buf.append(days + "d");
	    flag = true;
	}
	if (!useEmpty || (useEmpty && (hours > 0 || flag))) {
	    if (flag) {
		buf.append(" ");
	    }
	    buf.append(hours + "h");
	    flag = true;
	}
	if (!useEmpty || (useEmpty && (minutes > 0 || flag))) {
	    if (flag) {
		buf.append(" ");
	    }
	    buf.append(minutes + "m");
	    flag = true;
	}
	return buf.toString();
    }
    
    private static int pozitiveInt(int value) {
	return value < 0 ? 0 : value;
    }
}
