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

package org.plazmaforge.framework.core.datastorage;

import java.util.List;


import junit.framework.TestCase;

public class DSDataHelperTest extends TestCase {

    
    public void testGenerateQueryParameters() throws Exception  {
	
	// Test single DataSource 
	DSDataHelper helper = new DSDataHelper();
	

	String query = "SELECT PRODUCT_ID, PRODUCT_NAME, PRICE FROM PRODUCT  WHERE PRODUCT_ID = :PRODUCT_ID";
	DSDataSource dataSource = new DSBaseDataSource();
	dataSource.setType("sql");
	dataSource.setQueryText(query);
	
	DSField field = new DSField();
	field.setName("PRODUCT_ID");
	dataSource.addField(field);

	field = new DSField();
	field.setName("PRODUCT_NAME");
	dataSource.addField(field);

	field = new DSField();
	field.setName("PRICE");
	dataSource.addField(field);

	assertEquals(0, dataSource.getParameterCount());
	
	helper.generateQueryParameters(dataSource, "${", "}", false);
	
	assertEquals(1, dataSource.getParameterCount());
	
	assertEquals(1, dataSource.getParameterCount());
	List<DSParameter> parameters = dataSource.getParameters();
	assertNotNull(parameters);
	
	DSParameter parameter = parameters.get(0);
	
	assertEquals("PRODUCT_ID", parameter.getName());
	assertEquals("${PRODUCT_ID}", ((DSExpressionParameter) parameter).getExpressionText());
	
	
    }
    
}
