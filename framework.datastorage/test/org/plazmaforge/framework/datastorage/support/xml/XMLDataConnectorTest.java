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
package org.plazmaforge.framework.datastorage.support.xml;

import org.plazmaforge.framework.core.datastorage.DSBaseDataSource;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.datastorage.AbstractDSTestCase;

/**
 * @author ohapon
 *
 */
public class XMLDataConnectorTest extends AbstractDSTestCase {

    public void testXMLResultSet() throws Exception {

	// Data Producer
	DataProducer producer = new XMLDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	XMLDataConnector dataConnector = new XMLDataConnector();
	
	String fileName = getResourcesFileName("test.xml");
	dataConnector.setFileName(fileName);
	
	System.out.println("Create XMLDataConnector: fileName=" + fileName);

	// Session
	DSSession session = producer.openSession(dataConnector);
	assertNotNull(session);

	// 1. by Session
	String query = "/data-set/record";
	DSResultSet resultSet = producer.openResultSet(session, query);
	assertNotNull(resultSet);
	assertNotNull(resultSet instanceof XMLResultSet);
	XMLResultSet xmlResultSet = (XMLResultSet) resultSet;
	
	System.out.println("\nOpen XMLResultSet by session: fileName=" + fileName);
	printXMLResultSet(xmlResultSet);
	
	session.close();
	
	// 2.
	String connectionString = fileName + "->(query=/data-set/record)";
	resultSet = producer.openResultSet(connectionString);
	assertNotNull(resultSet);
	assertNotNull(resultSet instanceof XMLResultSet);
	xmlResultSet = (XMLResultSet) resultSet;

	
	System.out.println("\nOpen XMLResultSet by internal connection string: '" + connectionString + "'");
	printXMLResultSet(xmlResultSet);
	
    }
    
    public void testXMLDataSet() throws Exception {

	// Data Producer
	DataProducer producer = new XMLDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	XMLDataConnector dataConnector = new XMLDataConnector();
	
	String fileName = getResourcesFileName("test.xml");
	dataConnector.setFileName(fileName);
	
	System.out.println("\nCreate XMLDataConnector: fileName=" + fileName);

	// Session
	DSSession session = producer.openSession(dataConnector);
	assertNotNull(session);
	
	// Session
	session = producer.openSession(dataConnector);
	assertNotNull(session);
	
	String query = "/data-set/record";
	DSDataSource dataSource = new DSBaseDataSource();
	dataSource.setQueryText(query);
	dataSource.setType("xml");
	
	DSField field = new DSField();
	field.setName("A");
	field.setDataType("Integer");
	dataSource.addField(field);

	field = new DSField();
	field.setName("B");
	field.setDataType("Integer");
	dataSource.addField(field);

	field = new DSField();
	field.setName("C");
	field.setDataType("Integer");
	dataSource.addField(field);
	
	
	DSDataSet dataSet = producer.openDataSet(session, dataSource);
	
	int row = 0;
   	System.out.println("Load XML DataSet:");
   	Integer valueA = null;
   	Integer valueB = null;
   	Integer valueC = null;
   	while (dataSet.next()) {
   	    valueA = (Integer) dataSet.getValue("A");
   	    valueB = (Integer) dataSet.getValue("B");
   	    valueC = (Integer) dataSet.getValue("C");
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
    
    
    public void testDataManager() throws Exception {
	DataManager.registerDataProducerFactory(XMLDataConnector.TYPE, new XMLDataProducerFactory());
	
	String fileName = getResourcesFileName("test.xml");
	String connectionString = "xml::" + fileName + "->(query=/data-set/record)";
	XMLResultSet xmlResultSet = (XMLResultSet) DataManager.openResultSet(connectionString);
	System.out.println("\nOpen XMLResultSet by general connection string: '" + connectionString + "'");
	printXMLResultSet(xmlResultSet);
    }
    
    private int printXMLResultSet(XMLResultSet csvResultSet) throws DSException {
	int row = 0;
	System.out.println("Load XML data:");
	while (csvResultSet.next()) {
	    //System.out.println(" Row[" + row + "] : " + csvResultSet.getValue(0) + ", " + csvResultSet.getValue(1) + ", " + csvResultSet.getValue(2));
	    System.out.println(" Row[" + row + "] : ");
	    row++;
	}
	return row;
    }
    
}
