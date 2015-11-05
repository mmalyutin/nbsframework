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

package org.plazmaforge.framework.core.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class TypeUtils {

    public static boolean isLikeStringType(String type) {
	return Types.StringType.equals(type) || Types.TextType.equals(type);
    }
    
    ////
    
    public static boolean isLikeNumberType(String type) {
	return Types.ByteType.equals(type) 
		|| Types.ShortType.equals(type)
		|| Types.IntegerType.equals(type)
		|| Types.LongType.equals(type)
		|| Types.FloatType.equals(type)
		|| Types.DoubleType.equals(type)
		|| Types.BigIntegerType.equals(type)
		|| Types.BigDecimalType.equals(type)
		|| Types.NumberType.equals(type);
    }
    
    public static boolean isLikeIntegerType(String type) {
	return Types.ByteType.equals(type) 
		|| Types.ShortType.equals(type)
		|| Types.IntegerType.equals(type)
		|| Types.LongType.equals(type)
		|| Types.BigIntegerType.equals(type);
    }
    
    public static boolean isLikeDecimalType(String type) {
	return Types.FloatType.equals(type)
		|| Types.DoubleType.equals(type)
		|| Types.BigDecimalType.equals(type);
    }    

    ////
    
    public static boolean isLikeDateType(String type) {
	return Types.DateType.equals(type) 
		|| Types.DateTimeType.equals(type);
    }

    public static boolean isLikeTimeType(String type) {
	return Types.TimeType.equals(type) 
		|| Types.DateTimeType.equals(type)
		|| Types.TimestampType.equals(type);
    }


    public static boolean isLikeCalendarType(String type) {
	return Types.DateType.equals(type) 
		|| Types.TimeType.equals(type)
		|| Types.DateTimeType.equals(type)
		|| Types.TimestampType.equals(type);
    }

    ////
    
    public static boolean isBooleanType(String type) {
	return Types.BooleanType.equals(type);
    }
    

    public static boolean isObjectType(String type) {
	return Types.ObjectType.equals(type);
    }

    public static boolean isStringType(String type) {
	return Types.StringType.equals(type);
    }
    
    public static boolean isTextType(String type) {
	return Types.StringType.equals(type);
    }
    

    public static boolean isByteType(String type) {
	return Types.ByteType.equals(type);
    }
    
    public static boolean isShortType(String type) {
	return Types.ShortType.equals(type);
    }
    

    public static boolean isIntegerType(String type) {
	return Types.IntegerType.equals(type);
    }

    public static boolean isLongType(String type) {
	return Types.LongType.equals(type);
    }

    public static boolean isFloatType(String type) {
	return Types.FloatType.equals(type);
    }

    public static boolean isDoubleType(String type) {
	return Types.DoubleType.equals(type);
    }

    public static boolean isBigIntegerType(String type) {
	return Types.BigIntegerType.equals(type);
    }

    public static boolean isBigDecimalType(String type) {
	return Types.BigDecimalType.equals(type);
    }

    public static boolean isNumberType(String type) {
	return Types.NumberType.equals(type);
    }

    public static boolean isDateType(String type) {
	return Types.DateType.equals(type);
    }

    
    public static boolean isTimeType(String type) {
	return Types.TimeType.equals(type);
    }

    public static boolean isDateTimeType(String type) {
	return Types.DateTimeType.equals(type);
    }

    public static boolean isTimestampType(String type) {
	return Types.TimestampType.equals(type);
    }

    
    /**
     * Return type by class
     * @param klass
     * @return
     */
    public static String getType(Class<?> klass) {
	if (klass == null) {
	    return null;
	}
	// We don't use Class.getSimpleName() because have problems with GWT/JS
	String className = klass.getName();
	return getType(className);
    }

    /**
     * Return type by class name
     * Type is simple name of class
     * @param className
     * @return
     */
    public static String getType(String className) {
	if (className == null){
	    return null;
	}
	int index = className.lastIndexOf('.') + 1;
	if (index > 0) {
	    className = className.substring(index);
	}
	return className;
    }
    
    
    /**
     * return real java class by type
     * @param type
     * @return
     */
    public static Class<?> getClass(String type) {
	if (type == null) {
	    return null;
	}
	if (Types.ObjectType.equals(type)) {
	    return Object.class;
	} else if (Types.StringType.equals(type)) {
	    return String.class;
	} else if (Types.TextType.equals(type)) {
	    return String.class;
	} else if (Types.ByteType.equals(type)) {
	    return Byte.class;
	} else if (Types.ShortType.equals(type)) {
	    return Short.class;
	} else if (Types.IntegerType.equals(type)) {
	    return Integer.class;
	} else if (Types.LongType.equals(type)) {
	    return Long.class;
	} else if (Types.FloatType.equals(type)) {
	    return Float.class;
	} else if (Types.DoubleType.equals(type)) {
	    return Double.class;
	} else if (Types.BigIntegerType.equals(type)) {
	    return BigInteger.class;
	} else if (Types.BigDecimalType.equals(type)) {
	    return BigDecimal.class;
	} else if (Types.NumberType.equals(type)) {
	    return Number.class;
	} else if (Types.DateType.equals(type)) {
	    return Date.class;
	} else if (Types.TimeType.equals(type)) {
	    return Date.class; // ?
	} else if (Types.DateTimeType.equals(type)) {
	    return Date.class; 
	} else if (Types.TimestampType.equals(type)) {
	    return Date.class; 
	}
	return null;
    }

}
