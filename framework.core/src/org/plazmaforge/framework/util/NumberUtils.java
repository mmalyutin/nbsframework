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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 
 * @author ohapon
 *
 */
public class NumberUtils {

    public static <T extends Number> T parseNumber(String source, Class<T> type, NumberFormat formatter, boolean checkOverflow) {
	String value = StringUtils.normalizeString(source);
	if (value == null) {
	    return null;
	}
	if (formatter != null) {
	    try {
		Number number = formatter.parse(source);
		return convertNumber(number, type, checkOverflow);
	    } catch (ParseException ex) {
		throw new IllegalArgumentException("Could not parse number: " + ex.getMessage());
	    }
	}
	
	// TODO: Must checkOverflow
	
	if (Byte.class == type) {
	    return (T) Byte.valueOf(source);
	}
	if (Short.class == type) {
	    return (T) Short.valueOf(source);
	}   
	if (Integer.class == type) {
	    return (T) Integer.valueOf(source);
	}   
	if (Long.class == type) {
	    return (T) Long.valueOf(source);
	}   
	if (Float.class == type) {
	    return (T) Float.valueOf(source);
	}   
	if (Double.class == type) {
	    return (T) Double.valueOf(source);
	}   
	if (BigInteger.class == type) {
	    return (T) new BigInteger(source);
	}   
	if (BigDecimal.class == type) {
	    return (T) new BigDecimal(source);
	}   	
	return null;
    }
    
    public static <T extends Number> T parseNumber(String source, Class<T> type, NumberFormat formatter) {
	return parseNumber(source, type, formatter, false);
    }
    
    public static <T extends Number> T parseNumber(String source, Class<T> type, boolean checkNumber) {
	return parseNumber(source, type, null, false);
    }
    
    
    public static <T extends Number> T convertNumber(Number number, Class<T> type) {
	return convertNumber(number, type, false);
    }
    
    public static <T extends Number> T convertNumber(Number number, Class<T> type, boolean checkOverflow) {
	if (number == null) {
	    return null;
	}
	if (type == null) {
	    throw new IllegalArgumentException("Could not convert number [" + number + "] to unknown type");
	}
	// TODO: Must checkOverflow
	
	if (type.isInstance(number)) {
	    return (T) number;
	}
	if (Byte.class == type) {
	    return (T) ConverterUtils.toByte(number);
	}
	if (Short.class == type) {
	    return (T) ConverterUtils.toShort(number);
	}   
	if (Integer.class == type) {
	    return (T) ConverterUtils.toInteger(number);
	}   
	if (Long.class == type) {
	    return (T) ConverterUtils.toLong(number);
	}   
	if (Float.class == type) {
	    return (T) ConverterUtils.toFloat(number);
	}   
	if (Double.class == type) {
	    return (T) ConverterUtils.toDouble(number);
	}   
	if (BigInteger.class == type) {
	    return (T) ConverterUtils.toBigInteger(number);
	}   
	if (BigDecimal.class == type) {
	    return (T) ConverterUtils.toBigDecimal(number);
	}   
	throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" + number.getClass().getName() + "] to unknown type [" + type.getName() + "]");
    }
    
}
