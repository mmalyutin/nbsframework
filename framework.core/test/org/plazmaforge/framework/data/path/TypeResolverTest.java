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

/**
 * 
 */
package org.plazmaforge.framework.data.path;

import org.junit.Test;
import org.plazmaforge.framework.core.data.path.TypeInfo;
import org.plazmaforge.framework.core.data.path.TypeResolver;

import junit.framework.TestCase;

/**
 * @author ohapon
 *
 */
public class TypeResolverTest extends TestCase  {

    @Test
    public void testParsePath() throws Exception {
	TypeResolver typeResolver  = new TypeResolver();

	// MyForm
	typeResolver.parsePath(null);
	TypeInfo typeInfo = typeResolver.parsePath("mypackage/MyForm");
	System.out.println(typeInfo);
	
	typeResolver  = new TypeResolver();
	typeResolver.setBasePackage("org.plazmaforge.app");
	typeInfo = typeResolver.parsePath("mypackage/MyForm");
	System.out.println(typeInfo);
	
	typeResolver  = new TypeResolver();
	typeResolver.setBasePackage("org.plazmaforge.app");
	typeResolver.registerTypePackage("Form", "client.forms");
	typeInfo = typeResolver.parsePath("mypackage/MyForm");
	System.out.println(typeInfo);

	// MyListForm
	typeResolver  = new TypeResolver();
	typeResolver.setBasePackage("org.plazmaforge.app");
	typeResolver.registerTypePackage("Form", "client.forms");
	typeResolver.registerTypePackage("ListForm", "client.forms");
	typeInfo = typeResolver.parsePath("mypackage/MyListForm");
	System.out.println(typeInfo);
	for (String type: typeResolver.getTypes()) {
	    System.out.println(type);
	}
	assertEquals("ListForm", typeInfo.getType());

	typeResolver  = new TypeResolver();
	typeResolver.setBasePackage("org.plazmaforge.app");
	typeResolver.registerTypePackage("ListForm", "client.forms");	
	typeResolver.registerTypePackage("Form", "client.forms");
	typeInfo = typeResolver.parsePath("mypackage/MyListForm");
	System.out.println(typeInfo);
	for (String type: typeResolver.getTypes()) {
	    System.out.println(type);
	}
	
	assertEquals("ListForm", typeInfo.getType());
	

    }
    
    
    @Test
    public void testParseClass() throws Exception {
	TypeResolver typeResolver  = new TypeResolver();
	typeResolver.parsePath(null);
	TypeInfo typeInfo = typeResolver.parseClass("org.plazmaforge.app.mypackage.client.forms.MyForm");
	System.out.println(typeInfo);
	
	typeResolver  = new TypeResolver();
	typeResolver.setBasePackage("org.plazmaforge.app");
	typeInfo = typeResolver.parseClass("org.plazmaforge.app.mypackage.client.forms.MyForm");
	System.out.println(typeInfo);
	
	typeResolver  = new TypeResolver();
	typeResolver.setBasePackage("org.plazmaforge.app");
	typeResolver.registerTypePackage("Form", "client.forms");
	typeInfo = typeResolver.parseClass("org.plazmaforge.app.mypackage.client.forms.MyForm");
	System.out.println(typeInfo);

	typeResolver  = new TypeResolver();
	typeResolver.setBasePackage("org.plazmaforge.app");
	typeResolver.registerTypePackage("Form", "client.forms");
	typeResolver.registerTypePackage("ListForm", "client.forms");
	typeInfo = typeResolver.parseClass("org.plazmaforge.app.mypackage.client.forms.MyListForm");
	System.out.println(typeInfo);
	for (String type: typeResolver.getTypes()) {
	    System.out.println(type);
	}
	assertEquals("ListForm", typeInfo.getType());

	typeResolver  = new TypeResolver();
	typeResolver.setBasePackage("org.plazmaforge.app");
	typeResolver.registerTypePackage("ListForm", "client.forms");	
	typeResolver.registerTypePackage("Form", "client.forms");
	typeInfo = typeResolver.parseClass("org.plazmaforge.app.mypackage.client.forms.MyListForm");
	System.out.println(typeInfo);
	for (String type: typeResolver.getTypes()) {
	    System.out.println(type);
	}
	
	assertEquals("ListForm", typeInfo.getType());
	

    }
}

