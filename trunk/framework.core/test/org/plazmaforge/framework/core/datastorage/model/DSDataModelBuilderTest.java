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
package org.plazmaforge.framework.core.datastorage.model;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.DataTestCase;
import org.plazmaforge.framework.core.datastorage.DSBaseDataSource;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSExpressionParameter;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.data.Scope;
import org.plazmaforge.framework.core.exception.DSEvaluateException;
import org.plazmaforge.framework.util.StringUtils;

/**
 * @author ohapon
 *
 */
public class DSDataModelBuilderTest  extends DataTestCase {

    private DecimalFormat numberFormatter = new DecimalFormat("#.00");

    public void testGenerateQueryparameters() throws Exception  {
	
	// Test single DataSource 
	DSDataBuilder builder = new DSDataBuilder();
	

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
	
	builder.generateQueryParameters(dataSource, "${", "}", false);
	
	assertEquals(1, dataSource.getParameterCount());
	
	assertEquals(1, dataSource.getParameterCount());
	List<DSParameter> parameters = dataSource.getParameters();
	assertNotNull(parameters);
	
	DSParameter parameter = parameters.get(0);
	
	assertEquals("PRODUCT_ID", parameter.getName());
	assertEquals("${PRODUCT_ID}", ((DSExpressionParameter) parameter).getExpressionText());
	
	System.out.println();
	
	
    }
    
    public void testBuildModelEmpty() throws Exception  {
	DSDataBuilder builder = new DSDataBuilder();
	
	DSDataModel model = builder.buildDataModel((DSDataSource) null);
	assertNotNull(model);

	model = builder.buildDataModel((List<DSDataSource>) null);
	assertNotNull(model);
	
    }

    public void testBuildModel_1() throws Exception  {
	
	// Test single DataSource 
	DSDataBuilder builder = new DSDataBuilder();
	

	String query = "SELECT PRODUCT_ID, PRODUCT_NAME, PRICE FROM PRODUCT  WHERE PRODUCT_ID >= 150";
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
	
	DSDataModel model = builder.buildDataModel(dataSource);
	assertNotNull(model);

	
	Connection connection = getConnection();
	assertNotNull(connection);
	
	DSSession session = DataManager.openSession(connection);
	assertNotNull(session);
	
	Scope scope = new Scope();
	
	
	DSMainDataSet mainDataSet = new DSMainDataSet(model, session, scope);
	
	int i = 0;
	System.out.println("\nOutput 1 data source:");
	System.out.println("--------------------------------------------------------------------------------------------");	
	System.out.println("Row\tProductId\tProductName\tPrice");
	System.out.println("--------------------------------------------------------------------------------------------");
	
	while (mainDataSet.next()) {
	    i++;
	    System.out.println("" + i 
		    + "\t" + mainDataSet.getValue("PRODUCT_ID") 
		    + "\t\t" + mainDataSet.getValue("PRODUCT_NAME")
		    + "\t" + formatNumber((Number) mainDataSet.getValue("PRICE"))
		    );
	}
	
    }

    public void testBuildModel_2() throws Exception  {
	
	// Test multi DataSource
	// 1.1, 1.2           - level 1
	// 2.1 (child of 1.2) - level 2
	
	DSDataBuilder builder = new DSDataBuilder();
	
	DSDataModel model = builder.buildDataModel((DSDataSource) null);
	assertNotNull(model);

	model = builder.buildDataModel((List<DSDataSource>) null);
	assertNotNull(model);


	List<DSDataSource> dataSources = new ArrayList<DSDataSource>();
	
	// 1.1 DataSource
	String query = "SELECT PRODUCT_ID, PRODUCT_NAME, PRICE FROM PRODUCT WHERE PRODUCT_ID >= 200";
	DSDataSource dataSource = new DSBaseDataSource();
	dataSource.setName("DS_1_1");
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
	
	dataSources.add(dataSource);


	// 1.2 DataSource	
	query = "SELECT PRODUCT_ID AS PRODUCT_ID, PRODUCT_NAME AS PRODUCT_NAME, PRICE AS PRICE_2 FROM PRODUCT  WHERE PRODUCT_ID >= 190";
	dataSource = new DSBaseDataSource();
	dataSource.setName("DS_1_2");
	dataSource.setType("sql");
	dataSource.setQueryText(query);
	
	field = new DSField();
	field.setName("PRODUCT_ID");
	dataSource.addField(field);

	field = new DSField();
	field.setName("PRODUCT_NAME");
	dataSource.addField(field);

	field = new DSField();
	field.setName("PRICE_2");
	dataSource.addField(field);
	
	dataSources.add(dataSource);

	// 2.1 DataSource	
	query = "SELECT PRODUCT_ID AS PRODUCT_ID_3, PRODUCT_NAME AS PRODUCT_NAME_3, PRICE AS PRICE_3 FROM PRODUCT  WHERE PRODUCT_ID >= :PRODUCT_ID";
	dataSource = new DSBaseDataSource();
	dataSource.setName("DS_2_1");
	dataSource.setParentName("DS_1_2");
	
	dataSource.setType("sql");
	dataSource.setQueryText(query);
	
	field = new DSField();
	field.setName("PRODUCT_ID_3");
	dataSource.addField(field);

	field = new DSField();
	field.setName("PRODUCT_NAME_3");
	dataSource.addField(field);

	field = new DSField();
	field.setName("PRICE_3");
	dataSource.addField(field);
	
	DSParameter parameter = new DSParameter();
	parameter.setName("PRODUCT_ID");
	parameter.setDataType("Integer");
	parameter.setDefaultValue(190);
	
	dataSource.addParameter(parameter);
	
	dataSources.add(dataSource);

	////
	
	model = builder.buildDataModel(dataSources);
	assertNotNull(model);

	
	Connection connection = getConnection();
	assertNotNull(connection);
	
	DSSession session = DataManager.openSession(connection);
	assertNotNull(session);
	
	Scope scope = new Scope();
	
	DSMainDataSet mainDataSet = new DSMainDataSet(model, session, scope);
	
	int i = 0;
	System.out.println("\nOutput 2 data sources:");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");	
	System.out.println("Row\t[1.1]ProductId\t[1.1]ProductName\t[1.1]Price\t[1.2]ProductId\t[1.2]ProductName\t[1.2]Price\t[2.1]ProductId\t[2.1]ProductName\t[2.1]Price");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

	while (mainDataSet.next()) {
	    i++;
	    System.out.println("" + i 
		    
		    // DataSource 1.1
		    + "\t" + mainDataSet.getValue("PRODUCT_ID") 
		    + "\t\t" + mainDataSet.getValue("PRODUCT_NAME")
		    + "\t\t" + formatNumber((Number) mainDataSet.getValue("PRICE"))
		    
		    // DataSource 1.2
		    + "\t\t" + mainDataSet.getValue("DS_1_2.PRODUCT_ID")				// Test complex name (<DataSource>.<Field>) 
		    + "\t\t" + formatString((String) mainDataSet.getValue("DS_1_2.PRODUCT_NAME"))	// Test complex name (<DataSource>.<Field>
		    + "\t\t" + formatNumber((Number) mainDataSet.getValue("PRICE_2"))			// Test simple name (<Field>

		    // DataSource 2.1 (Child of DataSource 1.2)
		    + "\t\t" + mainDataSet.getValue("PRODUCT_ID_3") 
		    + "\t\t" + formatString((String) mainDataSet.getValue("PRODUCT_NAME_3"))
		    + "\t\t" + formatNumber((Number) mainDataSet.getValue("PRICE_3"))

		    );
//	    if (i > 150){
//		break;
//	    }
	    
	}

	
    }
    
    private String formatNumber(Number value) {
	if (value == null) {
	    return null;
	}
	return numberFormatter.format(value.doubleValue());
    }
    
    private String formatString(String value) {
	int count = value == null ? 10 : (10 - value.length());
	if (count < 0) {
	    return value;
	}
	return value + StringUtils.replicate(" ", count);
    }

}
