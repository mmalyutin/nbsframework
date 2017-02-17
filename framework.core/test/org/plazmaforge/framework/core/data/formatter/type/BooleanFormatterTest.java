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


import junit.framework.TestCase;

public class BooleanFormatterTest extends TestCase {
    
    
    public void testBooleanFormatter() throws Exception {
	
	BooleanFormatter formatter = new BooleanFormatter();
	
	String str = null;
	Boolean value = null;
	
	// Format value
	str = formatter.format(true);
	assertNotNull(str);
	assertEquals(str, "true");
	
	str = formatter.format(false);
	assertNotNull(str);
	assertEquals(str, "false");
	
	
	
	// TRUE: lower case
	value = formatter.parse("true");
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);

	value = formatter.parse("yes");
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);

	value = formatter.parse("y");
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);

	value = formatter.parse("t");
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);

	value = formatter.parse("1");
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);


	
	// TRUE: upper case
	value = formatter.parse("TRUE");
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);
	
	value = formatter.parse("YES");
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);
	
	value = formatter.parse("Y");
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);


	value = formatter.parse("T");
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);


	
	// TRUE: ignore case
	value = formatter.parse("true"); // 0000
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);
	
	value = formatter.parse("truE"); // 0001
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);
	
	value = formatter.parse("trUe"); // 0010
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);

	value = formatter.parse("trUE"); // 0011
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);
	
	value = formatter.parse("tRue"); // 0100
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);
	
	value = formatter.parse("tRuE"); // 0101
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);

	value = formatter.parse("tRUe"); // 0110
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);

	value = formatter.parse("tRUE"); // 01111
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);

	value = formatter.parse("True"); // 1000
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);

	
	
	// FALSE: lower case
	value = formatter.parse("n");
	assertNotNull(value);
	assertEquals(value, Boolean.FALSE);


	value = formatter.parse("no");
	assertNotNull(value);
	assertEquals(value, Boolean.FALSE);

	value = formatter.parse("f");
	assertNotNull(value);
	assertEquals(value, Boolean.FALSE);


	value = formatter.parse("0");
	assertNotNull(value);
	assertEquals(value, Boolean.FALSE);
	
	
	
	// FALSE: upper case
	value = formatter.parse("N");
	assertNotNull(value);
	assertEquals(value, Boolean.FALSE);


	value = formatter.parse("NO");
	assertNotNull(value);
	assertEquals(value, Boolean.FALSE);

	value = formatter.parse("F");
	assertNotNull(value);
	assertEquals(value, Boolean.FALSE);


	
	// NULL: unknown value
	value = formatter.parse("Nan");
	assertNull(value);


	// FALSE: unknown value
	formatter = new BooleanFormatter(null, true, true, true);
	value = formatter.parse("Nan");
	assertNotNull(value);
	assertEquals(value, Boolean.FALSE);

	// Own boolean format '.T.' -> true, '.F.' -> false
	formatter = new BooleanFormatter(".T.|.F.", true, false, false);
	value = formatter.parse(".T.");
	assertNotNull(value);
	assertEquals(value, Boolean.TRUE);

	value = formatter.parse(".F.");
	assertNotNull(value);
	assertEquals(value, Boolean.FALSE);

	

    }

}
