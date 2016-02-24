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
public class BigDecimalConverterTest extends TestCase {
    
    private static final String BIG_STR = (new Long(Long.MAX_VALUE)).toString() + "1234567890";
    
    private static final BigDecimal BIG_NEGATIVE_VALUE = new BigDecimal("-" + BIG_STR + "." + BIG_STR);
    
    private static final BigDecimal BIG_POSITIVE_VALUE = new BigDecimal(BIG_STR + "." + BIG_STR);
    
    private static final BigDecimal ZERO_VALUE = BigDecimal.valueOf(0.0);
    
    private static final BigDecimal NEGATIVE_VALUE = BigDecimal.valueOf(-123.45);
    
    private static final BigDecimal NEGATIVE_VALUE_UP = BigDecimal.valueOf(-123.56);
    
    private static final BigDecimal POSITIVE_VALUE = BigDecimal.valueOf(123.45);
    
    private static final BigDecimal POSITIVE_VALUE_UP = BigDecimal.valueOf(123.56);
    

    // Byte
    public void testBigDecimal2ByteConverter() throws Exception {
	BigDecimal2ByteConverter converter = new BigDecimal2ByteConverter();
	
	BigDecimal source = null;
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
	
	source = BIG_NEGATIVE_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: BIG_NEGATIVE_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = BIG_POSITIVE_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: BIG_POSITIVE_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }

    // Short
    public void testBigDecimal2ShortConverter() throws Exception {
	BigDecimal2ShortConverter converter = new BigDecimal2ShortConverter();
	
	BigDecimal source = null;
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
	
	source = BIG_NEGATIVE_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: BIG_NEGATIVE_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = BIG_POSITIVE_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: BIG_POSITIVE_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }
    
    // Integer
    public void testBigDecimal2IntegerConverter() throws Exception {
	BigDecimal2IntegerConverter converter = new BigDecimal2IntegerConverter();
	
	BigDecimal source = null;
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
	
	source = BIG_NEGATIVE_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: BIG_NEGATIVE_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = BIG_POSITIVE_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: BIG_POSITIVE_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }  
    
    // Long
    public void testBigDecimal2LongConverter() throws Exception {
	BigDecimal2LongConverter converter = new BigDecimal2LongConverter();
	
	BigDecimal source = null;
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
	
	source = BIG_NEGATIVE_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: BIG_NEGATIVE_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = BIG_POSITIVE_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: BIG_POSITIVE_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }

    // Float
    public void testBigDecimal2FloatConverter() throws Exception {
	BigDecimal2FloatConverter converter = new BigDecimal2FloatConverter();
   	
	BigDecimal source = null;
   	Float target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Float(ZERO_VALUE.floatValue()));

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Float(NEGATIVE_VALUE.floatValue()));

   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Float(POSITIVE_VALUE.floatValue()));

   	source = BIG_NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Float(BIG_NEGATIVE_VALUE.floatValue()));

   	source = BIG_POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Float(BIG_POSITIVE_VALUE.floatValue()));
   	
    }

    // Double
    public void testBigDecimal2DoubleConverter() throws Exception {
	BigDecimal2DoubleConverter converter = new BigDecimal2DoubleConverter();
   	
	BigDecimal source = null;
   	Double target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Double(ZERO_VALUE.doubleValue()));

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Double(NEGATIVE_VALUE.doubleValue()));

   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Double(POSITIVE_VALUE.doubleValue()));

   	source = BIG_NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Double(BIG_NEGATIVE_VALUE.doubleValue()));

   	source = BIG_POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new Double(BIG_POSITIVE_VALUE.doubleValue()));
   	
    }
    
    // BigDecimal
    public void testBigDecimal2BigIntegerConverter() throws Exception {
	BigDecimal2BigIntegerConverter converter = new BigDecimal2BigIntegerConverter();
   	
	BigDecimal source = null;
   	BigInteger target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, (new BigDecimal(ZERO_VALUE.toString())).toBigInteger());

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, (new BigDecimal(NEGATIVE_VALUE.toString())).toBigInteger());

   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, (new BigDecimal(POSITIVE_VALUE.toString())).toBigInteger());

   	source = BIG_NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, (new BigDecimal(BIG_NEGATIVE_VALUE.toString())).toBigInteger());

   	source = BIG_POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, (new BigDecimal(BIG_POSITIVE_VALUE.toString())).toBigInteger());
   	
    }    
    
    // String
    public void testBigDecimal2StringConverter() throws Exception {
	BigDecimal2StringConverter converter = new BigDecimal2StringConverter();
   	
	BigDecimal source = null;
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

   	source = BIG_NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BIG_NEGATIVE_VALUE.toString());

   	source = BIG_POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BIG_POSITIVE_VALUE.toString());
   	
	// TODO: Temp solution: Use 'locale' attribute to configure locale in DataConnector/DataSet/DataResultSet 
	Locale.setDefault(Locale.ENGLISH);

   	source = BigDecimal.valueOf(123.45);
   	converter = new BigDecimal2StringConverter("#.00");
   	target = converter.convert(source);
   	
   	assertEquals(target, "123.45");
   	
    }
       
    
}
