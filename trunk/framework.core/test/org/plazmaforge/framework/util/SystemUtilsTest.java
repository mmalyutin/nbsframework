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

/**
 * 
 */
package org.plazmaforge.framework.util;

import java.util.Properties;

import junit.framework.TestCase;

/**
 * @author ohapon
 *
 */
public class SystemUtilsTest extends TestCase {
    
    
    public void testLoadArgs() {
	Properties propetrties = null;

	// Null arguments
	propetrties = SystemUtils.loadProperties(null);
	assertNotNull(propetrties);
	
	// Empty arguments
	propetrties = SystemUtils.loadProperties(new String[] {});
	assertNotNull(propetrties);
	assertTrue(propetrties.isEmpty());

	propetrties = SystemUtils.loadProperties(new String[] {""});
	assertNotNull(propetrties);
	assertTrue(propetrties.isEmpty());

	propetrties = SystemUtils.loadProperties(new String[] {" "});
	assertNotNull(propetrties);
	assertTrue(propetrties.isEmpty());

	propetrties = SystemUtils.loadProperties(new String[] {" ", " ", ""});
	assertNotNull(propetrties);
	assertTrue(propetrties.isEmpty());

	// Ignore arguments (without '-')
	propetrties = SystemUtils.loadProperties(new String[] {"property1", "value1", "property2", "value2"});
	assertNotNull(propetrties);
	assertTrue(propetrties.isEmpty());

	// Ignore arguments (without '-' and only '-')
	propetrties = SystemUtils.loadProperties(new String[] {"property1", "value1", "property2", "value2", "-", "", "-", "-"});
	assertNotNull(propetrties);
	assertTrue(propetrties.isEmpty());

	// Ignore arguments (without '-' and only '-' and last ' ')
	propetrties = SystemUtils.loadProperties(new String[] {"property1", "value1", "property2", "value2", "-", "", "-", "-", " "});
	assertNotNull(propetrties);
	assertTrue(propetrties.isEmpty());

	// Ignore arguments (without '-' and only '-' and last 'value5')
	propetrties = SystemUtils.loadProperties(new String[] {"property1", "value1", "property2", "value2", "-", "", "-", "-", "value5"});
	assertNotNull(propetrties);
	assertTrue(propetrties.isEmpty());
	
	propetrties = SystemUtils.loadProperties(new String[] {"property1", "value1", "property2", "value2", "-", "value3", "-", "-", "value5"});
	assertNotNull(propetrties);
	assertTrue(propetrties.isEmpty());
	
	
	// Load arguments (1 property)
	propetrties = SystemUtils.loadProperties(new String[] {"-property1", "value1"});
	assertNotNull(propetrties);
	assertTrue(!propetrties.isEmpty());
	assertTrue(propetrties.size() == 1);
	assertEquals(propetrties.getProperty("property1"), "value1");
	

	// Load arguments (2 properties)
	propetrties = SystemUtils.loadProperties(new String[] {"-property1", "value1", "-property2", "value2"});
	assertNotNull(propetrties);
	assertTrue(!propetrties.isEmpty());
	assertTrue(propetrties.size() == 2);
	assertEquals(propetrties.getProperty("property1"), "value1");
	assertEquals(propetrties.getProperty("property2"), "value2");

	// Load arguments (1 property and ignore properties)
	propetrties = SystemUtils.loadProperties(new String[] {"-property1", "value1", "property2"});
	assertNotNull(propetrties);
	assertTrue(!propetrties.isEmpty());
	assertTrue(propetrties.size() == 1);
	assertEquals(propetrties.getProperty("property1"), "value1");

	propetrties = SystemUtils.loadProperties(new String[] {"-property1", "value1", "property2", "value2"});
	assertNotNull(propetrties);
	assertTrue(!propetrties.isEmpty());
	assertTrue(propetrties.size() == 1);
	assertEquals(propetrties.getProperty("property1"), "value1");
	
	// Load arguments (2 properties and ignore properties)
	propetrties = SystemUtils.loadProperties(new String[] {"-property1", "value1", "property2", "value22", "-property2", "value2", "property2", "value222", "property3", "value333"});
	assertNotNull(propetrties);
	assertTrue(!propetrties.isEmpty());
	assertTrue(propetrties.size() == 2);
	assertEquals(propetrties.getProperty("property1"), "value1");
	assertEquals(propetrties.getProperty("property2"), "value2");	

	propetrties = SystemUtils.loadProperties(new String[] {"-property1", "value1", "-property2"});
	assertNotNull(propetrties);
	assertTrue(!propetrties.isEmpty());
	assertTrue(propetrties.size() == 2);
	assertEquals(propetrties.getProperty("property1"), "value1");
	assertEquals(propetrties.getProperty("property2"), "true");	

	propetrties = SystemUtils.loadProperties(new String[] {"-property1", "value1", "-property2", "-property3"});
	assertNotNull(propetrties);
	assertTrue(!propetrties.isEmpty());
	assertTrue(propetrties.size() == 3);
	assertEquals(propetrties.getProperty("property1"), "value1");
	assertEquals(propetrties.getProperty("property2"), "true");
	assertEquals(propetrties.getProperty("property3"), "true");	

	propetrties = SystemUtils.loadProperties(new String[] {"-property1", "value1", "-property2", "-property3", "-property4", "value4"});
	assertNotNull(propetrties);
	assertTrue(!propetrties.isEmpty());
	assertTrue(propetrties.size() == 4);
	assertEquals(propetrties.getProperty("property1"), "value1");
	assertEquals(propetrties.getProperty("property2"), "true");
	assertEquals(propetrties.getProperty("property3"), "true");
	assertEquals(propetrties.getProperty("property4"), "value4");	

	
	propetrties = SystemUtils.loadProperties(new String[] {"-property1", "-property2", "-property3", "value3", "-property4", "-property5", "value5"});
	assertNotNull(propetrties);
	assertTrue(!propetrties.isEmpty());
	assertTrue(propetrties.size() == 5);
	assertEquals(propetrties.getProperty("property1"), "true");
	assertEquals(propetrties.getProperty("property2"), "true");
	assertEquals(propetrties.getProperty("property3"), "value3");
	assertEquals(propetrties.getProperty("property4"), "true");
	assertEquals(propetrties.getProperty("property5"), "value5");	
	
    }

}
