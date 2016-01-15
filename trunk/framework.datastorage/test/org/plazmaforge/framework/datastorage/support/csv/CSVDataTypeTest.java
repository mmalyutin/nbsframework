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
package org.plazmaforge.framework.datastorage.support.csv;

import org.plazmaforge.framework.core.datastorage.DSBaseDataSource;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.datastorage.AbstractDSTestCase;

/**
 * @author ohapon
 *
 */
public class CSVDataTypeTest extends AbstractDSTestCase {

    
   
    
    public void testCSVDataSet() throws Exception {

	// Data Producer
	DataProducer producer = new CSVDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	CSVDataConnector dataConnector = new CSVDataConnector();
	
	String fileName = getResourcesFileName("csv/test_data_type.csv");
	dataConnector.setFileName(fileName);
	
	System.out.println("\nCreate CSVDataConnector: fileName=" + fileName);

	// Session
	DSSession session = producer.openSession(dataConnector);
	assertNotNull(session);
	
	// 1. By Session
	dataConnector.setFirstRowHeader(true);
	session = producer.openSession(dataConnector);
	assertNotNull(session);
	
	DSDataSource dataSource = new DSBaseDataSource();
	dataSource.setType("csv");
	
	DSField field = new DSField();
	field.setName("F_STRING");
	field.setDataType("String");
	dataSource.addField(field);

	field = new DSField();
	field.setName("F_INTEGER");
	field.setDataType("Integer");
	dataSource.addField(field);

	field = new DSField();
	field.setName("F_FLOAT");
	field.setDataType("Float");
	dataSource.addField(field);
	
	
	DSDataSet dataSet = producer.openDataSet(session, dataSource);
	
	int row = 0;
   	System.out.println("Load CSV DataSet:");
   	
   	String valueString = null;
   	Integer valueInteger = null;
   	Float valueFloat = null;
   	
   	while (dataSet.next()) {
   	    valueString = (String) dataSet.getValue(0);
   	    valueInteger = (Integer) dataSet.getValue("F_INTEGER");
   	    valueFloat = (Float) dataSet.getValue("F_FLOAT");
   	    
   	    if (row == 0) {
   		assertEquals(valueString, "String 1");
   		assertEquals(valueInteger, new Integer(100));
   		assertEquals(valueFloat, new Float(123.45));
   	    } else if (row == 1) {
   		assertEquals(valueString, "String 2");
   		assertEquals(valueInteger, new Integer(200));
   		assertEquals(valueFloat, new Float(234.56));
   	    } else if (row == 2) {
   		assertEquals(valueString, "String 3");
   		assertEquals(valueInteger, new Integer(300));
   		assertEquals(valueFloat, new Float(345.67));
   	    }

   	    System.out.println(" Row[" + row + "] : " + valueString + ", " + valueInteger + ", " + valueFloat);
   	    row++;
   	}
	assertEquals(row, 3);
    }
    
    
   
    
}
