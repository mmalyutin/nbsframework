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

import junit.framework.TestCase;

/**
 * 
 * @author ohapon
 *
 */
public class ByteConverterTest extends TestCase {
    
    
    private static final Byte MIN_VALUE = Byte.MIN_VALUE;
    
    private static final Byte MAX_VALUE = Byte.MAX_VALUE;
    
    private static final Byte ZERO_VALUE = 0;
    
    private static final Byte NEGATIVE_VALUE = -100;
    
    private static final Byte POSITIVE_VALUE = 100;
    

    // Short
    public void testByte2ShortConverter() throws Exception {
	Byte2ShortConverter converter = new Byte2ShortConverter();
	
	Byte source = null;
	Short target = converter.convert(source);
	assertNull(target);
	
	source = ZERO_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short((short) ZERO_VALUE));

	source = NEGATIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short((short) NEGATIVE_VALUE));

	source = POSITIVE_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short((short) POSITIVE_VALUE));

	source = MIN_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short((short) MIN_VALUE));

	source = MAX_VALUE;
	target = converter.convert(source);
	assertEquals(target, new Short((short) MAX_VALUE));
	
    }
    
    // Integer
    public void testByte2IntegerConverter() throws Exception {
	Byte2IntegerConverter converter = new Byte2IntegerConverter();
	
	Byte source = null;
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
    public void testByte2LongConverter() throws Exception {
	Byte2LongConverter converter = new Byte2LongConverter();
	
	Byte source = null;
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
    public void testByte2FloatConverter() throws Exception {
   	Byte2FloatConverter converter = new Byte2FloatConverter();
   	
   	Byte source = null;
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
    public void testByte2DoubleConverter() throws Exception {
   	Byte2DoubleConverter converter = new Byte2DoubleConverter();
   	
   	Byte source = null;
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
    public void testByte2BigIntegerConverter() throws Exception {
   	Byte2BigIntegerConverter converter = new Byte2BigIntegerConverter();
   	
   	Byte source = null;
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
    public void testByte2BigDecimalConverter() throws Exception {
   	Byte2BigDecimalConverter converter = new Byte2BigDecimalConverter();
   	
   	Byte source = null;
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
    public void testByte2StringConverter() throws Exception {
   	Byte2StringConverter converter = new Byte2StringConverter();
   	
   	Byte source = null;
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
   	converter = new Byte2StringConverter("#.00");
   	target = converter.convert(source);
   	
   	assertEquals(target, "123.00");
   	
    }        
    
}
