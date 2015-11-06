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
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

public class ConvertFunctions {
    
    private static DateFormat DATE_FORMAT =  DateFormat.getDateInstance();
    
    private static DateFormat TIME_FORMAT =  DateFormat.getTimeInstance();
    
    private static DateFormat DATE_TIME_FORMAT =  DateFormat.getDateTimeInstance();
    
    private static NumberFormat NUMBER_FORMAT =  NumberFormat.getNumberInstance();
    
    private static NumberFormat INTEGER_FORMAT =  NumberFormat.getIntegerInstance();
    
    private static NumberFormat PERCENT_FORMAT =  NumberFormat.getPercentInstance();
    
    private static NumberFormat CURRENCY_FORMAT =  NumberFormat.getCurrencyInstance();
    
    

    private ConvertFunctions() {
    }

    // BYTE

    public static Byte TOBYTE(Number number) {
	if (number == null) {
	    return null;
	}
	return number.byteValue();
    }

    public static Byte TOBYTE(String str) {
	if (str == null) {
	    return null;
	}
	return Byte.valueOf(str);
    }


    // INTEGER
    
    public static Integer TOINTEGER(Number number) {
	if (number == null) {
	    return null;
	}
	return number.intValue();
    }
    
    public static Integer TOINTEGER(String str) {
	if (str == null) {
	    return null;
	}
	return Integer.valueOf(str);
    }

    
    // LONG

    public static Long TOLONG(Number number) {
	if (number == null) {
	    return null;
	}
	return number.longValue();
    }

    public static Long TOLONG(String str) {
	if (str == null) {
	    return null;
	}
	return Long.valueOf(str);
    }    
    
    // FLOAT
    
    public static Float TOFLOAT(Number number) {
	if (number == null) {
	    return null;
	}
	return number.floatValue();
    }

    public static Float TOFLOAT(String str) {
	if (str == null) {
	    return null;
	}
	return Float.valueOf(str);
    }
    
    
    // DOUBLE
    
    public static Double TODOUBLE(Number number) {
	if (number == null) {
	    return null;
	}
	return number.doubleValue();
    }
    
    public static Double TODOUBLE(String str) {
	if (str == null) {
	    return null;
	}
	return Double.valueOf(str);
    }    
    
    
    
    // STRING
    
    public static String TOSTRING(Number number) {
	if (number == null) {
	    return null;
	}
	// TODO: USE NUMBER FORMAT
	return number.toString();
    }
    
    public static String TOSTRING(Date date) {
	if (date == null) {
	    return null;
	}
	// TODO: USE NUMBER FORMAT
	return date.toString();
    }

    
    // DATE
    
    public static Date TODATE(String str) {
	if (str == null) {
	    return null;
	}
	try {
	    return DATE_FORMAT.parse(str);
	} catch (ParseException e) {
	    //TODO: May be error
	    return null;
	}
    }    

}
