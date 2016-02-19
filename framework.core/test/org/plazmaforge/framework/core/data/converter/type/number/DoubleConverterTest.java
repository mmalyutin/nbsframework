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
public class DoubleConverterTest extends TestCase {
    
    
    private static final Double MIN_VALUE = -1 * Double.MAX_VALUE;
    
    private static final Double MAX_VALUE = Double.MAX_VALUE;
    
    private static final Double SMALL_VALUE = Double.MIN_VALUE;
    
    private static final Double ZERO_VALUE = 0.0D;
    
    private static final Double NEGATIVE_VALUE = -123.45D;
    
    private static final Double NEGATIVE_VALUE_UP = -123.56D;
    
    private static final Double POSITIVE_VALUE = 123.45D;
    
    private static final Double POSITIVE_VALUE_UP = 123.56D;
    

    // Byte
    public void testDouble2ByteConverter() throws Exception {
	Double2ByteConverter converter = new Double2ByteConverter();
	
	Double source = null;
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
	    fail("Byte overflow is not implemented: Double.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Double.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }

    // Short
    public void testDouble2ShortConverter() throws Exception {
	Double2ShortConverter converter = new Double2ShortConverter();
	
	Double source = null;
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
	    fail("Short overflow is not implemented: Double.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Short overflow is not implemented: Double.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }
    
    // Integer
    public void testDouble2IntegerConverter() throws Exception {
	Double2IntegerConverter converter = new Double2IntegerConverter();
	
	Double source = null;
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
	    fail("Integer overflow is not implemented: Double.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Integer overflow is not implemented: Double.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }

    // Long
    public void testDouble2LongConverter() throws Exception {
	Double2LongConverter converter = new Double2LongConverter();
   	
	Double source = null;
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
	    fail("Long overflow is not implemented: Double.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Long overflow is not implemented: Double.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}   	
   	
    }
    
    // Float
    public void testDouble2FloatConverter() throws Exception {
	Double2FloatConverter converter = new Double2FloatConverter();
   	
	Double source = null;
   	Float target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Float(ZERO_VALUE));

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Float(NEGATIVE_VALUE));

   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Float(POSITIVE_VALUE));

   	source = MIN_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Float(MIN_VALUE));

   	source = MAX_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Float(MAX_VALUE));
   	
    }
    
    // BigInteger
    public void testDouble2BigIntegerConverter() throws Exception {
	Double2BigIntegerConverter converter = new Double2BigIntegerConverter();
   	
	Double source = null;
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
    public void testDouble2BigDecimalConverter() throws Exception {
	Double2BigDecimalConverter converter = new Double2BigDecimalConverter();
   	
	Double source = null;
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
    public void testDoubleStringConverter() throws Exception {
	Double2StringConverter converter = new Double2StringConverter();
   	
	Double source = null;
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

   	source = 123.45D;
   	converter = new Double2StringConverter("#.00");
   	target = converter.convert(source);
   	
   	assertEquals(target, "123.45");
   	
    }
       
    
}
