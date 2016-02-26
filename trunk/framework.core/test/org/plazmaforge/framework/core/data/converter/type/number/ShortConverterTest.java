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
public class ShortConverterTest extends TestCase {
    
    
    private static final Short MIN_VALUE = Short.MIN_VALUE;
    
    private static final Short MAX_VALUE = Short.MAX_VALUE;
    
    private static final Short ZERO_VALUE = 0;
    
    private static final Short NEGATIVE_VALUE = -100;
    
    private static final Short POSITIVE_VALUE = 100;
    

    // Byte
    public void testShort2ByteConverter() throws Exception {
	Short2ByteConverter converter = new Short2ByteConverter();
	
	Short source = null;
	Byte target = converter.convert(source);
	assertNull(target);
	
	source = ZERO_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Byte(ZERO_VALUE.byteValue()));

	source = NEGATIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Byte(NEGATIVE_VALUE.byteValue()));

	source = POSITIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Byte(POSITIVE_VALUE.byteValue()));

	source = MIN_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Short.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Short.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }
    
    // Integer
    public void testShort2IntegerConverter() throws Exception {
	Short2IntegerConverter converter = new Short2IntegerConverter();
	
	Short source = null;
	Integer target = converter.convert(source);
	assertNull(target);
	
	source = ZERO_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(ZERO_VALUE));

	source = NEGATIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(NEGATIVE_VALUE));

	source = POSITIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(POSITIVE_VALUE));

	source = MIN_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(MIN_VALUE));

	source = MAX_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(MAX_VALUE));
	
    }    

    // Long
    public void testShort2LongConverter() throws Exception {
	Short2LongConverter converter = new Short2LongConverter();
	
	Short source = null;
	Long target = converter.convert(source);
	assertNull(target);
	
	source = ZERO_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Long(ZERO_VALUE));

	source = NEGATIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Long(NEGATIVE_VALUE));

	source = POSITIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Long(POSITIVE_VALUE));

	source = MIN_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Long(MIN_VALUE));

	source = MAX_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Long(MAX_VALUE));
	
    }
    
    // Float
    public void testShort2FloatConverter() throws Exception {
	Short2FloatConverter converter = new Short2FloatConverter();
   	
	Short source = null;
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

    // Double
    public void testShort2DoubleConverter() throws Exception {
	Short2DoubleConverter converter = new Short2DoubleConverter();
   	
	Short source = null;
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
   	
    }
    

    // BigInteger
    public void testShort2BigIntegerConverter() throws Exception {
	Short2BigIntegerConverter converter = new Short2BigIntegerConverter();
   	
	Short source = null;
   	BigInteger target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigInteger.valueOf(ZERO_VALUE));

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigInteger.valueOf(NEGATIVE_VALUE));

   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigInteger.valueOf(POSITIVE_VALUE));

   	source = MIN_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigInteger.valueOf(MIN_VALUE));

   	source = MAX_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigInteger.valueOf(MAX_VALUE));
   	
    }
    

    // BigDecimal
    public void testShort2BigDecimalConverter() throws Exception {
	Short2BigDecimalConverter converter = new Short2BigDecimalConverter();
   	
	Short source = null;
   	BigDecimal target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigDecimal.valueOf(ZERO_VALUE));

   	source = NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigDecimal.valueOf(NEGATIVE_VALUE));

   	source = POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigDecimal.valueOf(POSITIVE_VALUE));

   	source = MIN_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigDecimal.valueOf(MIN_VALUE));

   	source = MAX_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, BigDecimal.valueOf(MAX_VALUE));
   	
    }    

    
    // String -> Short
    public void testShort2StringConverter() throws Exception {
	Short2StringConverter converter = new Short2StringConverter();
   	
	Short source = null;
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

   	source = 123;
   	converter = new Short2StringConverter("#.00");
   	target = converter.convert(source);
   	
   	assertEquals(target, "123.00");
   	
    }
       

    // String -> Short
    public void testString2ShortConverter() throws Exception {
   	String2ShortConverter converter = new String2ShortConverter();
   	
   	String source = null;
   	Short target = converter.convert(source);
   	assertNull(target);
   	
   	source = ZERO_VALUE.toString();
   	target = converter.convert(source);
   	assertEquals(target, ZERO_VALUE);

   	source = NEGATIVE_VALUE.toString();
   	target = converter.convert(source);
   	assertEquals(target, NEGATIVE_VALUE);

   	source = POSITIVE_VALUE.toString();
   	target = converter.convert(source);
   	assertEquals(target, POSITIVE_VALUE);

   	source = MIN_VALUE.toString();
   	target = converter.convert(source);
   	assertEquals(target, MIN_VALUE);

   	source = MAX_VALUE.toString();
   	target = converter.convert(source);
   	assertEquals(target, MAX_VALUE);

   	source = "123";
   	target = converter.convert(source);
   	assertEquals(target, new Short((short) 123));

   	source = "123.45"; // down
   	target = converter.convert(source);
   	assertEquals(target, new Short((short) 123));

   	source = "123.56"; // up
   	target = converter.convert(source);
   	assertEquals(target, new Short((short) 123));

   	Integer value = Short.MAX_VALUE + 1; // 32767 + 1 = 32768
   	source = value.toString();
   	try {
	    target = converter.convert(source);	    
	    fail("Short overflow is not implemented: " + source);
	} catch (OverflowException ex) {
	    
	}
   	
   	value = Short.MIN_VALUE - 1; // -32768 - 1 = -32769 
   	source = value.toString();
   	try {
	    target = converter.convert(source);	    
	    fail("Short overflow is not implemented: " + source);
	} catch (OverflowException ex) {
	    
	}
   	
   	source = "1234567890";
   	try {
	    target = converter.convert(source);	    
	    fail("Short overflow is not implemented: " + source);
	} catch (OverflowException ex) {
	    
	}

   	source = "-1234567890";
   	try {
	    target = converter.convert(source);	    
	    fail("Short overflow is not implemented: " + source);
	} catch (OverflowException ex) {
	    
	}
   	
    }                
}
