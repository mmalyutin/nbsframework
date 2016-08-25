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

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberUtils {

    private static final BigInteger MAX_LONG    = BigInteger.valueOf(Long.MAX_VALUE);
    private static final BigInteger MIN_LONG    = BigInteger.valueOf(Long.MIN_VALUE);

    private static final BigInteger MAX_INTEGER = BigInteger.valueOf(Integer.MAX_VALUE);
    private static final BigInteger MIN_INTEGER = BigInteger.valueOf(Integer.MIN_VALUE);

    private static final BigDecimal MAX_DOUBLE  = new BigDecimal(String.valueOf(Double.MAX_VALUE));
    private static final BigDecimal MIN_DOUBLE  = MAX_DOUBLE.negate();

    private static final BigDecimal MAX_FLOAT   = new BigDecimal(String.valueOf(Float.MAX_VALUE));
    private static final BigDecimal MIN_FLOAT   = MAX_FLOAT.negate();

    
    public static Number parseInteger(String str) {
	BigInteger value = new BigInteger(str);
	
	// of Integer, Long, and BigInteger.
	if (value.compareTo(MAX_INTEGER) <= 0 && value.compareTo(MIN_INTEGER) >= 0) {
	    return Integer.valueOf(value.intValue());
	} else if (value.compareTo(MAX_LONG) <= 0 && value.compareTo(MIN_LONG) >= 0) {
	    return new Long(value.longValue());
	}
	return value;
    }
}
