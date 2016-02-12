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
public class LongConverterTest extends TestCase {
    
    
    private static final Long MIN_VALUE = Long.MIN_VALUE;
    
    private static final Long MAX_VALUE = Long.MAX_VALUE;
    
    private static final Long ZERO_VALUE = 0L;
    
    private static final Long NEGATIVE_VALUE = -100L;
    
    private static final Long POSITIVE_VALUE = 100L;
    

    // Byte
    public void testLong2ByteConverter() throws Exception {
	Long2ByteConverter converter = new Long2ByteConverter();
	
	Long source = null;
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
	    fail("Byte overflow is not implemented: Long.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Long.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }

    // Short
    public void testLong2ShortConverter() throws Exception {
	Long2ShortConverter converter = new Long2ShortConverter();
	
	Long source = null;
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

	source = MIN_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Long.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Long.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }
    
    // Long
    public void testLong2IntegerConverter() throws Exception {
	Long2IntegerConverter converter = new Long2IntegerConverter();
	
	Long source = null;
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

	source = MIN_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Long.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Long.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }

    // Float
    public void testLong2FloatConverter() throws Exception {
	Long2FloatConverter converter = new Long2FloatConverter();
   	
	Long source = null;
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
    public void testLong2DoubleConverter() throws Exception {
	Long2DoubleConverter converter = new Long2DoubleConverter();
   	
	Long source = null;
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
    public void testLong2BigIntegerConverter() throws Exception {
	Long2BigIntegerConverter converter = new Long2BigIntegerConverter();
   	
	Long source = null;
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
    public void testLong2BigDecimalConverter() throws Exception {
	Long2BigDecimalConverter converter = new Long2BigDecimalConverter();
   	
	Long source = null;
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
    
    // String
    public void testLong2StringConverter() throws Exception {
	Long2StringConverter converter = new Long2StringConverter();
   	
	Long source = null;
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

   	source = 123L;
   	converter = new Long2StringConverter("#.00");
   	target = converter.convert(source);
   	
   	assertEquals(target, "123.00");
   	
    }
       
    
}
