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

package org.plazmaforge.framework.core.data.formatter.type;

import java.math.BigDecimal;
import java.util.Locale;

import junit.framework.TestCase;

public class BigDecimalFormatterTest extends TestCase {
    
    
    // BigDecimal
    public void testBigDecimalFormatter() throws Exception {
	
	// TODO: Temp solution: Use 'locale' attribute to configure locale in DataConnector/DataSet/DataResultSet 
	Locale.setDefault(Locale.ENGLISH);

	BigDecimalFormatter formatter = new BigDecimalFormatter();
	
	String str = null;
	BigDecimal value = null;
	
	// Format value
	str = formatter.format(value);
	assertNull(str);
	
	value = new BigDecimal("0");
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "0");

	value = new BigDecimal("-1");
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "-1");

	value = new BigDecimal("1");
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "1");

	value = new BigDecimal("123456.789");
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "123456.789");
	
	formatter = new BigDecimalFormatter("#.00");

	value = new BigDecimal("0");
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, ".00");

	value = new BigDecimal("-1");
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "-1.00");

	value = new BigDecimal("1");
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "1.00");

	value = new BigDecimal("123456.781");
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "123456.78"); // round down 
	
	value = new BigDecimal("123456.789");
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "123456.79"); // round up
	
	
	// Parse string
	formatter = new BigDecimalFormatter();
	
	str = null;
	value = formatter.parse(str);
	assertNull(value);

	str = "";
	value = formatter.parse(str);
	assertNull(value);

	str = " ";
	value = formatter.parse(str);
	assertNull(value);

	str = "0";
	value = formatter.parse(str);
	assertNotNull(value);
	assertEquals(value, new BigDecimal("0"));

	str = "123456.789";
	value = formatter.parse(str);
	assertNotNull(value);
	assertEquals(value, new BigDecimal("123456.789"));

	str = "-123456.789";
	value = formatter.parse(str);
	assertNotNull(value);
	assertEquals(value, new BigDecimal("-123456.789"));
	
	formatter = new BigDecimalFormatter("#.00");
	str = "123456.789";
	value = formatter.parse(str);
	assertNotNull(value);
	assertEquals(value, new BigDecimal("123456.789")); // ???		


    }

}
