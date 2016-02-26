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

import org.plazmaforge.framework.core.exception.ConvertException;
import org.plazmaforge.framework.core.exception.OverflowException;

/**
 * 
 * @author ohapon
 *
 */
public class NumberUtils {

   
    private static final BigInteger BIG_BYTE_MIN = BigInteger.valueOf(Byte.MIN_VALUE);
    
    private static final BigInteger BIG_BYTE_MAX = BigInteger.valueOf(Byte.MAX_VALUE);

    private static final BigInteger BIG_SHORT_MIN = BigInteger.valueOf(Short.MIN_VALUE);

    private static final BigInteger BIG_SHORT_MAX = BigInteger.valueOf(Short.MAX_VALUE);

    private static final BigInteger BIG_INTEGER_MIN = BigInteger.valueOf(Integer.MIN_VALUE);

    private static final BigInteger BIG_INTEGER_MAX = BigInteger.valueOf(Integer.MAX_VALUE);
    
    private static final BigInteger BIG_LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);

    private static final BigInteger BIG_LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);


	
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
		throw new ConvertException("Could not parse number: " + ex.getMessage());
	    }
	}
	
	Number number = null;
	try {
	    if (isLikeInteger(type)) {
		try {
		    number = parseBigInteger(value);
		} catch (NumberFormatException ex) {
		    number = parseBigDecimal(value);
		}
		return convertNumber(number, type, checkOverflow);
	    } else if (isLikeDecimal(type)) {
		number = parseBigDecimal(value);
		return convertNumber(number, type, checkOverflow);
	    }
	} catch (NumberFormatException ex) {
	    throw new ConvertException("Could not parse number: " + ex.getMessage());
	}	
	
	throw new ConvertException("Could not parse number [" + source + "]: "  + ( type == null ? "type is null" : ("unknown type [" + type.getName() + "]")));
	
//	
//	if (Byte.class == type) {
//	    return (T) Byte.valueOf(source);
//	}
//	if (Short.class == type) {
//	    return (T) Short.valueOf(source);
//	}   
//	if (Integer.class == type) {
//	    return (T) Integer.valueOf(source);
//	}   
//	if (Long.class == type) {
//	    return (T) Long.valueOf(source);
//	}   
//	if (Float.class == type) {
//	    return (T) Float.valueOf(source);
//	}   
//	if (Double.class == type) {
//	    return (T) Double.valueOf(source);
//	}   
//	if (BigInteger.class == type) {
//	    return (T) new BigInteger(source);
//	}   
//	if (BigDecimal.class == type) {
//	    return (T) new BigDecimal(source);
//	}   	
//	return null;
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
	    throw new ConvertException("Could not convert number [" + number + "]: type is null");
	}
	// TODO: Must checkOverflow
	
	if (type.isInstance(number)) {
	    return (T) number;
	}
	
	// Byte
	if (Byte.class == type) {
	    if (isOverflow(number, BIG_BYTE_MIN , BIG_BYTE_MAX)) {
		handleOverflowException(number, type);
	    }
	    
	    long value = number.longValue();
	    if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
		handleOverflowException(number, type);
	    }
	    return (T) Byte.valueOf(number.byteValue());
	}
	
	// Short
	if (Short.class == type) {
	    if (isOverflow(number, BIG_SHORT_MIN , BIG_SHORT_MAX)) {
		handleOverflowException(number, type);
	    }
	    long value = number.longValue();
	    if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
		handleOverflowException(number, type);
	    }
	    return (T) Short.valueOf(number.shortValue());
	}
	
	// Integer
	if (Integer.class == type) {
	    if (isOverflow(number, BIG_INTEGER_MIN , BIG_INTEGER_MAX)) {
		handleOverflowException(number, type);
	    }
	    long value = number.longValue();
	    if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
		handleOverflowException(number, type);
	    }
	    return (T) Integer.valueOf(number.intValue());
	}   

	// Long
	if (Long.class == type) {
	    if (isOverflow(number, BIG_LONG_MIN , BIG_LONG_MAX)) {
		handleOverflowException(number, type);
	    }
	    
	    /*
	    BigInteger bigInt = null;
	    if (number instanceof BigInteger) {
		bigInt = (BigInteger) number;
	    } else if (number instanceof BigDecimal) {
		bigInt = ((BigDecimal) number).toBigInteger();
	    }
	    // Effectively analogous to JDK 8's BigInteger.longValueExact()
	    if (bigInt != null && (bigInt.compareTo(BIG_LONG_MIN) < 0 || bigInt.compareTo(BIG_LONG_MAX) > 0)) {
		handleOverflowException(number, type);
	    }
	    */
	    
	    return (T) Long.valueOf(number.longValue());
	}
	
	if (Float.class == type) {
	    return (T) Float.valueOf(number.floatValue());
	    //return (T) ConverterUtils.toFloat(number);
	}   
	
	if (Double.class == type) {
	    return (T) Double.valueOf(number.doubleValue());
	    //return (T) ConverterUtils.toDouble(number);
	}
	
	// BigInteger
	if (BigInteger.class == type) {
	    if (number instanceof Float) {
		 return (T) new BigDecimal(number.toString()).toBigInteger();
	    } else if (number instanceof Double) {
		 return (T) new BigDecimal(number.toString()).toBigInteger();
	    } else if (number instanceof BigDecimal) {
		// do not lose precision - use BigDecimal's own conversion
		return (T) ((BigDecimal) number).toBigInteger();
	    } else {
		// original value is not a Big* number - use standard long
		// conversion
		return (T) BigInteger.valueOf(number.longValue());
	    }
	}
	
	if (BigDecimal.class == type) {
	    // always use BigDecimal(String) here to avoid unpredictability of
	    // BigDecimal(double)
	    // (see BigDecimal javadoc for details)
	    return (T) new BigDecimal(number.toString());
	    //return (T) ConverterUtils.toBigDecimal(number);
	}   
	throw new ConvertException("Could not convert number [" + number + "] of type [" + number.getClass().getName() + "] to unknown type [" + type.getName() + "]");
    }
    
    
    public static String formatNumber(Number value, NumberFormat formatter) {
	if (value == null) {
	    return null;
	}
	if (formatter == null) {
	    return value.toString();
	}
	return formatter.format(value);
    }
    
    public static boolean isUnknown(Number value) {
	if (value == null) {
	    return false;
	}
	if (value instanceof Float) {
	    return isUnknown((Float) value);
	}
	if (value instanceof Double) {
	    return isUnknown((Double) value);
	}
	return false;
    }
    
    public static boolean isUnknown(Float value) {
	if (value == null) {
	    return false;
	}
	return Float.isInfinite(value) || Float.isNaN(value);
    }
    
    public static boolean isUnknown(Double value) {
	if (value == null) {
	    return false;
	}
	return Double.isInfinite(value) || Double.isNaN(value);
    }
    
    private static void handleOverflowException(Number number, Class<?> type) {
	throw new OverflowException("Could not convert number [" + number + "] of type [" + number.getClass().getName() + "] to type [" + type.getName() + "]");
    }

    private static Number toBigNumber(Number number) {
	if (number == null) {
	    return null;
	}
	if (number instanceof Float) {
	    return new BigDecimal(number.toString());
	}
	if (number instanceof Double) {
	    return new BigDecimal(number.toString());
	}
	if (number instanceof BigInteger) {
	    return (BigInteger) number;
	}
	if (number instanceof BigDecimal) {
	    return (BigDecimal) number;
	}
	return null;
    }
    
//    private static BigDecimal toBigDecimal(Number number) {
//	if (number == null) {
//	    return null;
//	}
//	if (number instanceof Float) {
//	    return new BigDecimal(number.toString());
//	}
//	if (number instanceof Double) {
//	    return new BigDecimal(number.toString());
//	}
//	if (number instanceof BigInteger) {
//	    return new BigDecimal((BigInteger) number);
//	}
//	if (number instanceof BigDecimal) {
//	    return (BigDecimal) number;
//	}
//	return null;
//    }

    private static boolean isOverflow(Number value, BigInteger min, BigInteger max) {
	
	// Null value is not overflow
	if (value == null) {
	    return false;
	}
	
	// Unknown value is overflow
	if (isUnknown(value)) {
	    return true;
	}
	
	// Convert value to big number
	value = toBigNumber(value);
	
	if (value instanceof BigInteger) {
	    BigInteger v = (BigInteger) value;
	    return v.compareTo(min) < 0 || v.compareTo(max) > 0;
	}
	if (value instanceof BigDecimal) {
	    BigDecimal v = (BigDecimal) value;
	    BigDecimal v1 = new BigDecimal(min);
	    BigDecimal v2 = new BigDecimal(max);
	    return v.compareTo(v1) < 0 || v.compareTo(v2) > 0;
	}
	return false;
    }
    
//    private static boolean isOverflow(BigDecimal value, BigDecimal min, BigDecimal max) {
//	if (value == null) {
//	    return false;
//	}
//	return value.compareTo(min) < 0 || value.compareTo(max) > 0;
//    }


    private static BigInteger parseBigInteger(String value) {
	return decodeBigInteger(value);
    }

    private static BigDecimal parseBigDecimal(String value) {
	if (value == null) {
	    return null;
	}
	return new BigDecimal(value);
    }
    
    private static BigInteger decodeBigInteger(String value) {
	if (value == null) {
	    return null;
	}
	
	int radix = 10;
	int index = 0;
	boolean negative = false;

	// Handle minus sign, if present.
	if (value.startsWith("-")) {
	    negative = true;
	    index++;
	}

	// Handle radix specifier, if present.
	if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
	    index += 2;
	    radix = 16;
	} else if (value.startsWith("#", index)) {
	    index++;
	    radix = 16;
	} else if (value.startsWith("0", index) && value.length() > 1 + index) {
	    index++;
	    radix = 8;
	}

	BigInteger result = new BigInteger(value.substring(index), radix);
	return (negative ? result.negate() : result);
    }

    ////
    
    public static boolean isByte(Class<?> type) {
	if (type == null) {
	    return false;
	}
	return type.isAssignableFrom(Byte.class) || type.isAssignableFrom(Byte.TYPE);
    }

    public static boolean isShort(Class<?> type) {
	if (type == null) {
	    return false;
	}
	return type.isAssignableFrom(Short.class) || type.isAssignableFrom(Short.TYPE);
    }

    public static boolean isInteger(Class<?> type) {
	if (type == null) {
	    return false;
	}
	return type.isAssignableFrom(Integer.class) || type.isAssignableFrom(Integer.TYPE);
    }

    public static boolean isLong(Class<?> type) {
	if (type == null) {
	    return false;
	}
	return type.isAssignableFrom(Long.class) || type.isAssignableFrom(Long.TYPE);
    }

    public static boolean isFloat(Class<?> type) {
	if (type == null) {
	    return false;
	}
	return type.isAssignableFrom(Float.class) || type.isAssignableFrom(Float.TYPE);
    }

    public static boolean isDouble(Class<?> type) {
	if (type == null) {
	    return false;
	}
	return type.isAssignableFrom(Double.class) || type.isAssignableFrom(Double.TYPE);
    }

    public static boolean isBigInteger(Class<?> type) {
	if (type == null) {
	    return false;
	}
	return type.isAssignableFrom(BigInteger.class);
    }

    public static boolean isBigDecimal(Class<?> type) {
	if (type == null) {
	    return false;
	}
	return type.isAssignableFrom(BigDecimal.class);
    }

    public static boolean isLikeInteger(Class<?> type) {
	if (type == null) {
	    return false;
	}
	return isByte(type) || isShort(type) || isInteger(type) || isLong(type);
    }

    public static boolean isLikeDecimal(Class<?> type) {
	if (type == null) {
	    return false;
	}
	return isFloat(type) || isDouble(type) || isBigDecimal(type);
    }
    
    
    public static boolean isLikeInteger(Number value) {
	return (value instanceof Byte) 	|| (value instanceof Short) || (value instanceof Integer) || (value instanceof Long);
    }
    
    public static boolean isLikeDecimal(Number value) {
	return (value instanceof Float) || (value instanceof Double) || (value instanceof BigDecimal);
    }
    
}
