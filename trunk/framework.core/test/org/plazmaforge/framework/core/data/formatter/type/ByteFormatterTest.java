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

import java.util.Locale;

import junit.framework.TestCase;

public class ByteFormatterTest extends TestCase {
    
    
    // Byte
    public void testByteFormatter() throws Exception {
	
	// TODO: Temp solution: Use 'locale' attribute to configure locale in DataConnector/DataSet/DataResultSet 
	Locale.setDefault(Locale.ENGLISH);

	ByteFormatter formatter = new ByteFormatter();
	
	String str = null;
	Byte value = null;
	
	// Format value
	str = formatter.format(value);
	assertNull(str);
	
	value = 0;
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "0");

	value = -1;
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "-1");

	value = 1;
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "1");

	value = 123;
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "123");
	
	formatter = new ByteFormatter("#.00");

	value = 0;
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, ".00");

	value = -1;
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "-1.00");

	value = 1;
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "1.00");

	value = 123;
	str = formatter.format(value);
	assertNotNull(str);
	assertEquals(str, "123.00");
	

	// Parse string
	formatter = new ByteFormatter();
	
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
	assertEquals(value, new Byte((byte) 0));
	
	str = "123";
	value = formatter.parse(str);
	assertNotNull(value);
	assertEquals(value, new Byte((byte) 123));

	str = "-123";
	value = formatter.parse(str);
	assertNotNull(value);
	assertEquals(value, new Byte((byte) -123));
	
	
	formatter = new ByteFormatter("#.00");
	str = "123.5";
	value = formatter.parse(str);
	assertNotNull(value);
	assertEquals(value, new Byte((byte) 123));

    }

}
