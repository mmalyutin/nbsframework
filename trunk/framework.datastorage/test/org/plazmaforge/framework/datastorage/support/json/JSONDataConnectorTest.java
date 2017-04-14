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
package org.plazmaforge.framework.datastorage.support.json;

import org.plazmaforge.framework.core.datastorage.DSBaseDataSource;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.datastorage.AbstractTestCase;

/**
 * @author ohapon
 *
 */
public class JSONDataConnectorTest extends AbstractTestCase {

    
    public void testDataManager() throws Exception {
	DataManager.registerDataProducerFactory(JSONDataConnector.TYPE, new JSONDataProducerFactory());
	
	String file = getResourcesFileName("json/test.json");
	String connectionString = "json::" + file + "->(query=data-set.records)";
	JSONResultSet jsonResultSet = (JSONResultSet) DataManager.openResultSet(connectionString);
	System.out.println("\nOpen JSONResultSet by global connection string: '" + connectionString + "'");
	printJSONResultSet(jsonResultSet, new String[] {"a", "b", "c"});
    }

    public void testJSONResultSet() throws Exception {

	// Data Producer
	DataProducer producer = new JSONDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	JSONDataConnector dataConnector = new JSONDataConnector();
	
	String file = getResourcesFileName("json/test.json");
	dataConnector.setFile(file);
	
	System.out.println("Create JSONDataConnector: file=" + file);

	// Session
	DSSession session = producer.openSession(dataConnector);
	assertNotNull(session);

	// 1. by Session
	String query = "data-set.records";
	DSResultSet resultSet = producer.openResultSet(session, query);
	assertNotNull(resultSet);
	assertNotNull(resultSet instanceof JSONResultSet);
	JSONResultSet jsonResultSet = (JSONResultSet) resultSet;
	
	System.out.println("\nOpen JSONResultSet by session: file=" + file);
	printJSONResultSet(jsonResultSet, new String[] {"a", "b", "c"});
	
	session.close();
	
	// 2.
	String connectionString = file + "->(query=data-set.records)";
	resultSet = producer.openResultSet(connectionString);
	assertNotNull(resultSet);
	assertNotNull(resultSet instanceof JSONResultSet);
	jsonResultSet = (JSONResultSet) resultSet;

	
	System.out.println("\nOpen JSONResultSet by local connection string: '" + connectionString + "'");
	printJSONResultSet(jsonResultSet, new String[] {"a", "b", "c"});
	
    }
    
    public void testJSONLDataSet() throws Exception {

	// Data Producer
	DataProducer producer = new JSONDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	JSONDataConnector dataConnector = new JSONDataConnector();
	
	String file = getResourcesFileName("json/test.json");
	dataConnector.setFile(file);
	
	System.out.println("\nCreate JSONDataConnector: file=" + file);

	// Session
	DSSession session = producer.openSession(dataConnector);
	assertNotNull(session);
	
	// Session
	session = producer.openSession(dataConnector);
	assertNotNull(session);
	
	String query = "data-set.records";
	DSDataSource dataSource = new DSBaseDataSource();
	dataSource.setQueryText(query);
	dataSource.setType("json");
	
	DSField field = new DSField();
	field.setName("a");
	field.setDataType("Integer");
	dataSource.addField(field);

	field = new DSField();
	field.setName("b");
	field.setDataType("Integer");
	dataSource.addField(field);

	field = new DSField();
	field.setName("c");
	field.setDataType("Integer");
	dataSource.addField(field);
	
	
	DSDataSet dataSet = producer.openDataSet(session, dataSource);
	
	int row = 0;
   	System.out.println("Load JSON DataSet:");
   	Integer valueA = null;
   	Integer valueB = null;
   	Integer valueC = null;
   	while (dataSet.next()) {
   	    valueA = (Integer) dataSet.getValue("a");
   	    valueB = (Integer) dataSet.getValue("b");
   	    valueC = (Integer) dataSet.getValue("c");
   	    if (row == 0) {
   		assertEquals(valueA, new Integer(1));
   		assertEquals(valueB, new Integer(2));
   		assertEquals(valueC, new Integer(3));
   	    } else if (row == 1) {
   		assertEquals(valueA, new Integer(4));
   		assertEquals(valueB, new Integer(5));
   		assertEquals(valueC, new Integer(6));
   	    } else if (row == 2) {
   		assertEquals(valueA, new Integer(7));
   		assertEquals(valueB, new Integer(8));
   		assertEquals(valueC, new Integer(9));
   	    }

   	    System.out.println(" Row[" + row + "] : " + valueA + ", " + valueB + ", " + valueC);
   	    row++;
   	}
	assertEquals(row, 3);
    }
    
    private int printJSONResultSet(JSONResultSet resultSet, String[] fields) throws DSException {
	int row = 0;
	System.out.println("Load JSON data:");
	while (resultSet.next()) {
	    System.out.println(" Row[" + row + "] : " + resultSet.getValue(fields[0])  + ", " + resultSet.getValue(fields[1]) + ", " + resultSet.getValue(fields[2]));
	    row++;
	}
	return row;
    }
    
}
