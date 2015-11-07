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

package org.plazmaforge.framework.uwt.util;

import java.math.BigDecimal;
import java.math.BigInteger;

//[CORE]
public class NumberUtils {

    private static NumberUtils instance;
    
    
    public static NumberUtils getInstance() {
	if (instance == null) {
	    instance = new NumberUtils();  
	}
        return instance;
    }
    
    public static Number convertNumber(Number value, Class valueClass) {
	return getInstance().convertNumberValue(value, valueClass);
    }
    
    
    /**
     * Convert the number value to value of concrete number class
     * @param value
     * @param valueClass
     * @return convert value
     */
    protected Number convertNumberValue(Number value, Class valueClass) {
	
	if (value == null) {
	    return null;
	}
	
	if (valueClass == null) {
	    return value;
	}
	
	if (isByte(valueClass)) {
	    
	    return convertNumberToByte(value);
	    
	} else if (isShort(valueClass)) {
	    
	    return convertNumberToShort(value);
	    
	} else if (isInteger(valueClass)) {
	    
	    return convertNumberToInteger(value);
	    
	} else if (isLong(valueClass)) {
	    
	    return convertNumberToLong(value);
	    
	} else if (isFloat(valueClass)) {
	    
	    return convertNumberToFloat(value);
	    
	} else if (isDouble(valueClass)) {
	    
	    return convertNumberToDouble(value);
	    
	} else if (isBigInteger(valueClass)) {
	    
	    return convertNumberToBigInteger(value);
	    
	} else if (isBigDecimal(valueClass)) {
	    
	    return convertNumberToBigDecimal(value);
	    
	} else {
	    return value;
	}
    }   
  
    public static boolean isByte(Class klass) {
	return klass.isAssignableFrom(Byte.class) || klass.isAssignableFrom(Byte.TYPE);
    }
    
    public static boolean isShort(Class klass) {
	return klass.isAssignableFrom(Short.class) || klass.isAssignableFrom(Short.TYPE);
    }

    public static boolean isInteger(Class klass) {
	return klass.isAssignableFrom(Integer.class) || klass.isAssignableFrom(Integer.TYPE);
    }

    public static boolean isLong(Class klass) {
	return klass.isAssignableFrom(Long.class) || klass.isAssignableFrom(Long.TYPE);
    }

    public static boolean isFloat(Class klass) {
	return klass.isAssignableFrom(Float.class) || klass.isAssignableFrom(Float.TYPE);
    }

    public static boolean isDouble(Class klass) {
	return klass.isAssignableFrom(Double.class) || klass.isAssignableFrom(Double.TYPE);
    }
    
    public static boolean isBigInteger(Class klass) {
	return klass.isAssignableFrom(BigInteger.class);
    }

    public static boolean isBigDecimal(Class klass) {
	return klass.isAssignableFrom(BigDecimal.class);
    }
    
    
    
    
    protected Byte convertNumberToByte(Number value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Byte) {
	    return (Byte) value;
	}
	return new Byte(value.byteValue());
    }

    protected Short convertNumberToShort(Number value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Short) {
	    return (Short) value;
	}
	return new Short(value.shortValue());
    }
  
  
    protected Integer convertNumberToInteger(Number value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Integer) {
	    return (Integer) value;
	}
	return new Integer(value.intValue());
    }
  
    protected Long convertNumberToLong(Number value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Long) {
	    return (Long) value;
	}
	return new Long(value.longValue());
    }
  
    protected Double convertNumberToDouble(Number value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Double) {
	    return (Double) value;
	}
	return new Double(value.doubleValue());
    }    
  
    protected Float convertNumberToFloat(Number value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Float) {
	    return (Float) value;
	}
	return new Float(value.floatValue());
    }    
  
    protected BigInteger convertNumberToBigInteger(Number value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof BigInteger) {
	    return (BigInteger) value;
	}
	return BigInteger.valueOf(value.longValue());
    }   
  
    protected BigDecimal convertNumberToBigDecimal(Number value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof BigDecimal) {
	    return (BigDecimal) value;
	}
	return BigDecimal.valueOf(value.doubleValue());
    }       

}
