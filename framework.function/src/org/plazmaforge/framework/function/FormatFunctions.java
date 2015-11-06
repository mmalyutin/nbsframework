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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatFunctions {

    private static DateFormat DATE_FORMAT =  DateFormat.getDateInstance();
    
    private static DateFormat TIME_FORMAT =  DateFormat.getTimeInstance();
    
    private static DateFormat DATE_TIME_FORMAT =  DateFormat.getDateTimeInstance();
    
    private static NumberFormat NUMBER_FORMAT =  NumberFormat.getNumberInstance();
    
    private static NumberFormat INTEGER_FORMAT =  NumberFormat.getIntegerInstance();
    
    private static NumberFormat PERCENT_FORMAT =  NumberFormat.getPercentInstance();
    
    private static NumberFormat CURRENCY_FORMAT =  NumberFormat.getCurrencyInstance();
    
    private FormatFunctions() {
    }
    
    
    // DATE
    
    public static String FORMATDATE(Date date) {
	if (date == null) {
	    return null;
	}
	return DATE_FORMAT.format(date);
    }

    public static String FORMATTIME(Date date) {
	if (date == null) {
	    return null;
	}
	return TIME_FORMAT.format(date);
    }

    public static String FORMATDATETIME(Date date) {
	if (date == null) {
	    return null;
	}
	return DATE_TIME_FORMAT.format(date);
    }

    public static String FORMAT(Date date) {
	return FORMAT(date, null);
    }

    public static String FORMAT(Date date, String pattern) {
	if (date == null) {
	    return null;
	}
	if (pattern == null) {
	    return FORMATDATE(date);
	}
	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	return dateFormat.format(date);
    }

    // NUMBER

    public static String FORMATNUMBER(Number number) {
	if (number == null) {
	    return null;
	}
	return NUMBER_FORMAT.format(number);
    }
    
    public static String FORMATINTEGER(Number number) {
	if (number == null) {
	    return null;
	}
	return INTEGER_FORMAT.format(number);
    }

    public static String FORMATPERCENT(Number number) {
	if (number == null) {
	    return null;
	}
	return PERCENT_FORMAT.format(number);
    }

    public static String FORMATCURRENCY(Number number) {
	if (number == null) {
	    return null;
	}
	return CURRENCY_FORMAT.format(number);
    }


    public static String FORMAT(Number number) {
	return FORMAT(number, null);
    }
    
    public static String FORMAT(Number number, String pattern) {
	if (number == null) {
	    return null;
	}
	if (pattern == null) {
	    return FORMATNUMBER(number);
	}
	DecimalFormat dateFormat = new DecimalFormat(pattern);
	return dateFormat.format(number);
    }

}
