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

package org.plazmaforge.framework.core.data.converter.type.number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;

import org.plazmaforge.framework.core.exception.OverflowException;

import junit.framework.TestCase;

/**
 * 
 * @author ohapon
 *
 */
public class FloatConverterTest extends TestCase {
    
    
    private static final Float MIN_VALUE = -1 * Float.MAX_VALUE;
    
    private static final Float MAX_VALUE = Float.MAX_VALUE;
    
    private static final Float SMALL_VALUE = Float.MIN_VALUE;
    
    private static final Float ZERO_VALUE = 0.0F;
    
    private static final Float NEGATIVE_VALUE = -123.45F;
    
    private static final Float NEGATIVE_VALUE_UP = -123.56F;
    
    private static final Float POSITIVE_VALUE = 123.45F;
    
    private static final Float POSITIVE_VALUE_UP = 123.56F;
    
    private static final Float NEGATIVE_INFINITY = Float.NEGATIVE_INFINITY;
    
    private static final Float POSITIVE_INFINITY = Float.POSITIVE_INFINITY;
    

    // Byte
    public void testFloat2ByteConverter() throws Exception {
	Float2ByteConverter converter = new Float2ByteConverter();
	
	Float source = null;
	Byte target = converter.convert(source);
	assertNull(target);
	
	source = ZERO_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Byte(ZERO_VALUE.byteValue()));

	source = NEGATIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Byte(NEGATIVE_VALUE.byteValue()));

	source = NEGATIVE_VALUE_UP;
	target = converter.convert(source);
	assertEquals(target, new Byte(NEGATIVE_VALUE_UP.byteValue()));
	
	source = POSITIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Byte(POSITIVE_VALUE.byteValue()));

	source = POSITIVE_VALUE_UP;
	target = converter.convert(source);
	assertEquals(target, new Byte(POSITIVE_VALUE_UP.byteValue()));

	source = SMALL_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Byte(SMALL_VALUE.byteValue()));
	
	source = MIN_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Float.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Float.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = NEGATIVE_INFINITY;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Float.NEGATIVE_INFINITY");
	} catch (OverflowException ex) {
	    
	}

	source = POSITIVE_INFINITY;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Float.POSITIVE_INFINITY");
	} catch (OverflowException ex) {
	    
	}
	
    }

    // Short
    public void testFloat2ShortConverter() throws Exception {
	Float2ShortConverter converter = new Float2ShortConverter();
	
	Float source = null;
	Short target = converter.convert(source);
	assertNull(target);
	
	source = ZERO_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short(ZERO_VALUE.shortValue()));

	source = NEGATIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short(NEGATIVE_VALUE.shortValue()));

	source = NEGATIVE_VALUE_UP;
	target = converter.convert(source);
	assertEquals(target, new Short(NEGATIVE_VALUE_UP.shortValue()));
	
	source = POSITIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short(POSITIVE_VALUE.shortValue()));

	source = POSITIVE_VALUE_UP;
	target = converter.convert(source);
	assertEquals(target, new Short(POSITIVE_VALUE_UP.shortValue()));

	source = SMALL_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short(SMALL_VALUE.shortValue()));
	
	source = MIN_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Short overflow is not implemented: Float.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Short overflow is not implemented: Float.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
	source = NEGATIVE_INFINITY;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Float.NEGATIVE_INFINITY");
	} catch (OverflowException ex) {
	    
	}

	source = POSITIVE_INFINITY;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Float.POSITIVE_INFINITY");
	} catch (OverflowException ex) {
	    
	}
	
    }
    
    // Integer
    public void testFloat2IntegerConverter() throws Exception {
	Float2IntegerConverter converter = new Float2IntegerConverter();
	
	Float source = null;
	Integer target = converter.convert(source);
	assertNull(target);
	
	source = ZERO_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(ZERO_VALUE.intValue()));

	source = NEGATIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(NEGATIVE_VALUE.intValue()));

	source = NEGATIVE_VALUE_UP;
	target = converter.convert(source);
	assertEquals(target, new Integer(NEGATIVE_VALUE_UP.intValue()));
	
	source = POSITIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(POSITIVE_VALUE.intValue()));

	source = POSITIVE_VALUE_UP;
	target = converter.convert(source);
	assertEquals(target, new Integer(POSITIVE_VALUE_UP.intValue()));

	source = SMALL_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(SMALL_VALUE.intValue()));
	
	source = MIN_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Integer overflow is not implemented: Float.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Integer overflow is not implemented: Float.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
	source = NEGATIVE_INFINITY;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Float.NEGATIVE_INFINITY");
	} catch (OverflowException ex) {
	    
	}

	source = POSITIVE_INFINITY;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Float.POSITIVE_INFINITY");
	} catch (OverflowException ex) {
	    
	}
	
    }

    // Long
    public void testFloat2LongConverter() throws Exception {
	Float2LongConverter converter = new Float2LongConverter();
   	
	Float source = null;
   	Long target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Long(ZERO_VALUE.longValue()));

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Long(NEGATIVE_VALUE.longValue()));

   	source = NEGATIVE_VALUE_UP;
   	target = converter.convert(source);
   	assertEquals(target, new Long(NEGATIVE_VALUE_UP.longValue()));
   	
   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Long(POSITIVE_VALUE.longValue()));

   	source = POSITIVE_VALUE_UP;
   	target = converter.convert(source);
   	assertEquals(target, new Long(POSITIVE_VALUE_UP.longValue()));

   	source = SMALL_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Long(SMALL_VALUE.longValue()));
   	
   	source = MIN_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Long overflow is not implemented: Float.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Long overflow is not implemented: Float.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}   	
   	
	source = NEGATIVE_INFINITY;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Float.NEGATIVE_INFINITY");
	} catch (OverflowException ex) {
	    
	}

	source = POSITIVE_INFINITY;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Float.POSITIVE_INFINITY");
	} catch (OverflowException ex) {
	    
	}
	
    }

    // Double
    public void testFloat2DoubleConverter() throws Exception {
	Float2DoubleConverter converter = new Float2DoubleConverter();
   	
	Float source = null;
   	Double target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Double(ZERO_VALUE));

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Double(NEGATIVE_VALUE));

   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Double(POSITIVE_VALUE));

   	source = MIN_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Double(MIN_VALUE));

   	source = MAX_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Double(MAX_VALUE));
   	
	source = NEGATIVE_INFINITY;
	target = converter.convert(source);
	assertEquals(target, new Double(NEGATIVE_INFINITY));

	source = POSITIVE_INFINITY;
	target = converter.convert(source);
	assertEquals(target, new Double(POSITIVE_INFINITY));
   	
    }
    
    // BigInteger
    public void testFloat2BigIntegerConverter() throws Exception {
	Float2BigIntegerConverter converter = new Float2BigIntegerConverter();
   	
	Float source = null;
   	BigInteger target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigInteger.valueOf(ZERO_VALUE.longValue()));

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigInteger.valueOf(NEGATIVE_VALUE.longValue()));

   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigInteger.valueOf(POSITIVE_VALUE.longValue()));

   	source = MIN_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new BigDecimal(MIN_VALUE.toString()).toBigInteger());

   	source = MAX_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new BigDecimal(MAX_VALUE.toString()).toBigInteger());
   	
    }
    
    // BigDecimal
    public void testFloat2BigDecimalConverter() throws Exception {
	Float2BigDecimalConverter converter = new Float2BigDecimalConverter();
   	
	Float source = null;
   	BigDecimal target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new BigDecimal(ZERO_VALUE.toString()));

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new BigDecimal(NEGATIVE_VALUE.toString()));

   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new BigDecimal(POSITIVE_VALUE.toString()));

   	source = MIN_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new BigDecimal(MIN_VALUE.toString()));

   	source = MAX_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new BigDecimal(MAX_VALUE.toString()));
   	
    }    
    
    // String
    public void testFloatStringConverter() throws Exception {
	Float2StringConverter converter = new Float2StringConverter();
   	
	Float source = null;
   	String target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, ZERO_VALUE.toString());

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, NEGATIVE_VALUE.toString());

   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, POSITIVE_VALUE.toString());

   	source = MIN_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, MIN_VALUE.toString());

   	source = MAX_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, MAX_VALUE.toString());
   	
	// TODO: Temp solution: Use 'locale' attribute to configure locale in DataConnector/DataSet/DataResultSet 
	Locale.setDefault(Locale.ENGLISH);

   	source = 123.45F;
   	converter = new Float2StringConverter("#.00");
   	target = converter.convert(source);
   	
   	assertEquals(target, "123.45");
   	
    }
       
    
}
