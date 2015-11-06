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

public class MathFunctions {

    private MathFunctions() {
    }

    public static Number ABS(Number number) {
	if (number == null) {
	    return null;
	}
	if (number instanceof Integer)
	    return Integer.valueOf(Math.abs(((Integer) number).intValue()));
	if (number instanceof Double)
	    return Double.valueOf(Math.abs(((Double) number).doubleValue()));
	if (number instanceof Float)
	    return Float.valueOf(Math.abs(((Float) number).floatValue()));
	if (number instanceof Long)
	    return Long.valueOf(Math.abs(((Long) number).longValue()));
	else
	    return Double.valueOf(Math.abs(number.doubleValue()));
    }
    
    //[*]
    public static Number ACOS(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.acos(number.doubleValue());
    }
    
    //[*]
    public static Number ASIN(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.asin(number.doubleValue());
    }

    //[*]
    public static Number ATAN(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.atan(number.doubleValue());
    }
    
    //[*]
    /*
    public static Number ATAN2(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.atan2(number.doubleValue());
    }
    */


    //[*]
    public static Number CEIL(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.ceil(number.doubleValue());
    }
    
    //[*]
    public static Number COS(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.cos(number.doubleValue());
    }

    //[*]
    /*
    public static Number COT(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.cot(number.doubleValue());
    }
    */

    
    //[*]
    public static Number DEGREES(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.toDegrees(number.doubleValue());
    }

    //[*]
    public static Number E() {
	return Math.E;
    }

    //[*]
    public static Number EXP(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.exp(number.doubleValue());
    }

    public static Long FACTORIAL(Integer number) {
	if (number == null) {
	    return null;
	}
	if (number.intValue() < 0) {
	    throw new RuntimeException("Unable to calculate the factorial number of a negative number.");
	}
	Long result = Long.valueOf(1L);
	for (int i = 1; i <= number.intValue(); i++) {
	    result = Long.valueOf(result.longValue() * (long) i);
	}
	return result;
    }

    //[*]
    public static Number FLOOR(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.floor(number.doubleValue());
    }

    //[*]
    /*
    public static Number LN(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.ln(number.doubleValue());
    }
    */

    //[*]
    public static Number LOG(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.log(number.doubleValue());
    }

    //[*]
    public static Number LOG10(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.log10(number.doubleValue());
    }


    public static Boolean ISEVEN(Number number) {
	if (number == null) {
	    return null;
	}
	return (number.intValue() % 2 == 0);
    }

    public static Boolean ISODD(Number number) {
	if (number == null) {
	    return null;
	}
	return (number.intValue() % 2 == 1);
    }

    //[*]
    public static Number PI() {
	return Math.PI;
    }

    //[*]
    public static Number POW(Number number1, Number number2) {
	if (number1 == null || number2 == null) {
	    return null;
	}
	return Math.pow(number1.doubleValue(), number2.doubleValue());
    }
    

    //[*]
    /*
    public static Number RADIANS(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.radians(number.doubleValue());
    }
    */


    //[*]
    public static Number MAX(Number numbers[]) {
	if (numbers.length == 0) {
	    return null;
	}
	double result = 0.0D;
	for (int i = 0; i < numbers.length; i++) {
	    double value = numbers[i].doubleValue(); // TODO: NullPointerException
	    if (value > result) {
		result = value; 
	    }
	}
	return Double.valueOf(result);
    }

    //[*]
    public static Number MIN(Number numbers[]) {
	if (numbers.length == 0) {
	    return null;
	}
	double result = 0.0D;
	for (int i = 0; i < numbers.length; i++) {
	    double value = numbers[i].doubleValue();  // TODO: NullPointerException
	    if (value < result) {
		result = value; 
	    }
	}
	return Double.valueOf(result);
    }
    
    //[*]
    public static Number MOD(Number number) {
	if (number == null) {
	    return null;
	}
	return (number.doubleValue() % 2);
    }
    

    public static Double RAND() {
	return Double.valueOf(Math.random());
    }

    
    //[*]
    public static Number ROUND(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.round(number.doubleValue());
    }
    
    public static Integer RANDBETWEEN(Integer bottomRange, Integer topRange) {
	int min = bottomRange.intValue();
	int max = topRange.intValue();
	return Integer.valueOf(min + (int) (Math.random() * (double) ((max - min) + 1)));
    }

    public static Integer SIGN(Number number) {
	if (number == null) {
	    return null;
	}
	return Integer.valueOf((int) Math.signum(number.doubleValue()));
    }

    public static Number SQRT(Number number) {
	if (number == null) {
	    return null;
	}
	return Double.valueOf(Math.sqrt(number.doubleValue()));
    }

    //[*]
    public static Number SIN(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.sin(number.doubleValue());
    }

    
    public static Number SUM(Number numbers[]) {
	if (numbers.length == 0) {
	    return null;
	}
	double result = 0.0D;
	for (int i = 0; i < numbers.length; i++)
	    result += numbers[i].doubleValue(); // TODO: NullPointerException

	return Double.valueOf(result);
    }
    
    
    //[*]
    public static Number TAN(Number number) {
	if (number == null) {
	    return null;
	}
	return Math.tan(number.doubleValue());
    }


}
