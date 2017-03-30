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
package org.plazmaforge.framework.datastorage.support.xlsx;

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
public class XLSXDataConnectorTest extends AbstractDSTestCase {

    public void testDataManager() throws Exception {
	DataManager.registerDataProducerFactory(XLSXDataConnector.TYPE, new XLSXDataProducerFactory());
	
	String fileName = getResourcesFileName("xlsx/test.xlsx");
	String connectionString = "xlsx::" + fileName;
	XLSXResultSet xlsResultSet = (XLSXResultSet) DataManager.openResultSet(connectionString);
	System.out.println("\nOpen XLSXResultSet by general connection string: '" + connectionString + "'");
	printXLSResultSet(xlsResultSet);
    }
    
    public void testXLSXResultSet() throws Exception {

	// Data Producer
	DataProducer producer = new XLSXDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	XLSXDataConnector dataConnector = new XLSXDataConnector();
	
	String fileName = getResourcesFileName("xlsx/test.xlsx");
	dataConnector.setFileName(fileName);
	
	System.out.println("Create XLSXDataConnector: fileName=" + fileName);

	// Session
	DSSession session = producer.openSession(dataConnector);
	assertNotNull(session);
	assertTrue(session instanceof XLSXSession);

	// 1. by Session
	DSResultSet resultSet = producer.openResultSet(session);
	assertNotNull(resultSet);
	assertTrue(resultSet instanceof XLSXResultSet);
	XLSXResultSet xlsResultSet = (XLSXResultSet) resultSet;
	
	System.out.println("\nOpen XLSXResultSet by session: fileName=" + fileName);
	printXLSResultSet(xlsResultSet);
	
	session.close();
	
	// 2. by Connection string
	String connectionString = fileName;
	resultSet = producer.openResultSet(connectionString);
	assertNotNull(resultSet);
	assertTrue(resultSet instanceof XLSXResultSet);
	xlsResultSet = (XLSXResultSet) resultSet;
	
	System.out.println("\nOpen XLSXResultSet by internal connection string: '" + connectionString + "'");
	printXLSResultSet(xlsResultSet);
	
    }
    
    public void testXLSXDataSet() throws Exception {

	// Data Producer
	DataProducer producer = new XLSXDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	XLSXDataConnector dataConnector = new XLSXDataConnector();
	
	String fileName = getResourcesFileName("xlsx/test.xlsx");
	dataConnector.setFileName(fileName);
	
	System.out.println("\nCreate XLSXDataConnector: fileName=" + fileName);

	// Session
	DSSession session = producer.openSession(dataConnector);
	assertNotNull(session);
	
	// 1. By Session
	dataConnector.setFirstRowHeader(true);
	session = producer.openSession(dataConnector);
	assertNotNull(session);
	
	DSDataSource dataSource = new DSBaseDataSource();
	dataSource.setType("xlsx");
	
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
   	System.out.println("Load XLSX DataSet:");
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
    
    private int printXLSResultSet(XLSXResultSet xlsResultSet) throws DSException {
	int row = 0;
	System.out.println("Load XLSX data:");
	while (xlsResultSet.next()) {
	    System.out.println(" Row[" + row + "] : " + xlsResultSet.getValue(0) + ", " + xlsResultSet.getValue(1) + ", " + xlsResultSet.getValue(2));
	    row++;
	}
	return row;
    }
    
}
