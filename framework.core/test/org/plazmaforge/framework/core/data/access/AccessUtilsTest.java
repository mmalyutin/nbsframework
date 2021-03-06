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

package org.plazmaforge.framework.core.data.access;


import org.plazmaforge.framework.core.data.path.TypeInfo;

import junit.framework.TestCase;

public class AccessUtilsTest extends TestCase {

    
    
    
    public void testGetAccessProperties() {
	
	PropertyAccessor[] propertyAccessors = AccessUtils.getPropertyAccessors((Class) null);
	assertNull(propertyAccessors);

	
	propertyAccessors = AccessUtils.getPropertyAccessors(TypeInfo.class);
	assertNotNull(propertyAccessors);

	propertyAccessors = AccessUtils.getPropertyAccessors(MyClass.class);
	assertNotNull(propertyAccessors);
	
	assertEquals(3, propertyAccessors.length);
	assertTrue(hasProperty(propertyAccessors, "name"));
	assertTrue(hasProperty(propertyAccessors, "type"));
	assertTrue(hasProperty(propertyAccessors, "description"));
	
	
	PropertyAccessor propertyAccessor = AccessUtils.getPropertyAccessor(MyClass.class, "type");
	assertNotNull(propertyAccessor);
	assertEquals("type", propertyAccessor.getName());
    }
    
    private boolean hasProperty(PropertyAccessor[] propertyAccessors, String property) {
	if (property == null || propertyAccessors == null || propertyAccessors.length == 0) {
	    return false;
	}
	for (PropertyAccessor propertyAccessor : propertyAccessors) {
	    if (property.equals(propertyAccessor.getName())) {
		return true;
	    }
	}
	return false;
    }
    
    
    public static class MyBaseClass {
	
	private String name;
	
	private int type;

	public MyBaseClass() {
	    super();
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public int getType() {
	    return type;
	}

	public void setType(int type) {
	    this.type = type;
	}
	
	
    }
    
    public static class MyClass extends MyBaseClass {
	
	private String description;

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}
	
	
    }
}
