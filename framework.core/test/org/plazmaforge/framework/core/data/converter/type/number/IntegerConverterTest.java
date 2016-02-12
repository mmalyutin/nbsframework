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
public class IntegerConverterTest extends TestCase {
    
    
    private static final Integer MIN_VALUE = Integer.MIN_VALUE;
    
    private static final Integer MAX_VALUE = Integer.MAX_VALUE;
    
    private static final Integer ZERO_VALUE = 0;
    
    private static final Integer NEGATIVE_VALUE = -100;
    
    private static final Integer POSITIVE_VALUE = 100;
    

    // Byte
    public void testInteger2ByteConverter() throws Exception {
	Integer2ByteConverter converter = new Integer2ByteConverter();
	
	Integer source = null;
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
	    fail("Byte overflow is not implemented: Integer.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Integer.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }

    // Short
    public void testInteger2ShortConverter() throws Exception {
	Integer2ShortConverter converter = new Integer2ShortConverter();
	
	Integer source = null;
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
	    fail("Byte overflow is not implemented: Integer.MIN_VALUE");
	} catch (OverflowException ex) {
	    
	}

	source = MAX_VALUE;
	try {
	    target = converter.convert(source);	    
	    fail("Byte overflow is not implemented: Integer.MAX_VALUE");
	} catch (OverflowException ex) {
	    
	}
	
    }
    
    // Long
    public void testInteger2LongConverter() throws Exception {
	Integer2LongConverter converter = new Integer2LongConverter();
	
	Integer source = null;
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
    public void testInteger2FloatConverter() throws Exception {
	Integer2FloatConverter converter = new Integer2FloatConverter();
   	
	Integer source = null;
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
    public void testInteger2DoubleConverter() throws Exception {
	Integer2DoubleConverter converter = new Integer2DoubleConverter();
   	
	Integer source = null;
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
    public void testInteger2BigIntegerConverter() throws Exception {
	Integer2BigIntegerConverter converter = new Integer2BigIntegerConverter();
   	
	Integer source = null;
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
    public void testInteger2BigDecimalConverter() throws Exception {
	Integer2BigDecimalConverter converter = new Integer2BigDecimalConverter();
   	
	Integer source = null;
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
    public void testInteger2StringConverter() throws Exception {
	Integer2StringConverter converter = new Integer2StringConverter();
   	
	Integer source = null;
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
   	converter = new Integer2StringConverter("#.00");
   	target = converter.convert(source);
   	
   	assertEquals(target, "123.00");
   	
    }
       
    
}
