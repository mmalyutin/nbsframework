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
public class BigIntegerConverterTest extends TestCase {
    
    
    private static final BigInteger BIG_NEGATIVE_VALUE = new BigInteger((new Long(Long.MIN_VALUE)).toString() + "1234567890");
    
    private static final BigInteger BIG_POSITIVE_VALUE = new BigInteger((new Long(Long.MAX_VALUE)).toString() + "1234567890");
    
    private static final BigInteger ZERO_VALUE = BigInteger.valueOf(0);
    
    private static final BigInteger NEGATIVE_VALUE = BigInteger.valueOf(-100);
    
    private static final BigInteger POSITIVE_VALUE = BigInteger.valueOf(100);
    

    // Byte
    public void testBigInteger2ByteConverter() throws Exception {
	BigInteger2ByteConverter converter = new BigInteger2ByteConverter();
	
	BigInteger source = null;
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
    public void testBigInteger2ShortConverter() throws Exception {
	BigInteger2ShortConverter converter = new BigInteger2ShortConverter();
	
	BigInteger source = null;
	Short target = converter.convert(source);
	assertNull(target);
	
	source = ZERO_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short(ZERO_VALUE.shortValue()));

	source = NEGATIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short(NEGATIVE_VALUE.shortValue()));

	source = POSITIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short(POSITIVE_VALUE.shortValue()));

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
    public void testBigInteger2IntegerConverter() throws Exception {
	BigInteger2IntegerConverter converter = new BigInteger2IntegerConverter();
	
	BigInteger source = null;
	Integer target = converter.convert(source);
	assertNull(target);
	
	source = ZERO_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(ZERO_VALUE.intValue()));

	source = NEGATIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(NEGATIVE_VALUE.intValue()));

	source = POSITIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Integer(POSITIVE_VALUE.intValue()));

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
    public void testBigInteger2LongConverter() throws Exception {
	BigInteger2LongConverter converter = new BigInteger2LongConverter();
	
	BigInteger source = null;
	Long target = converter.convert(source);
	assertNull(target);
	
	source = ZERO_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Long(ZERO_VALUE.longValue()));

	source = NEGATIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Long(NEGATIVE_VALUE.longValue()));

	source = POSITIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Long(POSITIVE_VALUE.longValue()));

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
    public void testBigInteger2FloatConverter() throws Exception {
	BigInteger2FloatConverter converter = new BigInteger2FloatConverter();
   	
	BigInteger source = null;
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
    public void testBigInteger2DoubleConverter() throws Exception {
	BigInteger2DoubleConverter converter = new BigInteger2DoubleConverter();
   	
	BigInteger source = null;
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
    public void testBigInteger2BigDecimalConverter() throws Exception {
	BigInteger2BigDecimalConverter converter = new BigInteger2BigDecimalConverter();
   	
	BigInteger source = null;
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

   	source = BIG_NEGATIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new BigDecimal(BIG_NEGATIVE_VALUE.toString()));

   	source = BIG_POSITIVE_VALUE;
   	target = converter.convert(source);
   	assertEquals(target, new BigDecimal(BIG_POSITIVE_VALUE.toString()));
   	
    }    
    
    // String
    public void testBigInteger2StringConverter() throws Exception {
	BigInteger2StringConverter converter = new BigInteger2StringConverter();
   	
	BigInteger source = null;
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

   	source = BigInteger.valueOf(123);
   	converter = new BigInteger2StringConverter("#.00");
   	target = converter.convert(source);
   	
   	assertEquals(target, "123.00");
   	
    }
       
    
}
