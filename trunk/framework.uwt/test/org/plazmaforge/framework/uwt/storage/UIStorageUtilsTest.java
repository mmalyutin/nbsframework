/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.uwt.storage;

import org.junit.Test;

import junit.framework.TestCase;

public class UIStorageUtilsTest extends TestCase {

    protected void setUp() {
	
    }

    
    @Test
    public void testUtils() throws Exception {
	
	String delim = null;
	String input = null;
	String output = null;
	output = UIStorageUtils.toCamelCase(input, delim);
	
	delim = "-";
	input = "test";
	
	output = UIStorageUtils.toCamelCase(input, delim);
	assertNotNull(output);
	assertEquals("test", output);

	input = "test-test";
	output = UIStorageUtils.toCamelCase(input, delim);
	assertNotNull(output);
	assertEquals("testTest", output);

	input = "test-test-test";
	output = UIStorageUtils.toCamelCase(input, delim);
	assertNotNull(output);
	assertEquals("testTestTest", output);

	
	input = "test-t";
	output = UIStorageUtils.toCamelCase(input, delim);
	assertNotNull(output);
	assertEquals("testT", output);

	
	input = "test-";
	output = UIStorageUtils.toCamelCase(input, delim);
	assertNotNull(output);
	assertEquals("test", output);

    }
}
