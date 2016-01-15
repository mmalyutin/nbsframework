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

import java.text.SimpleDateFormat;
import java.util.Date;


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
public class JSONDataTypeTest extends AbstractDSTestCase {

    
   
    
    public void testJSONDataSet() throws Exception {

	// Data Producer
	DataProducer producer = new JSONDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	JSONDataConnector dataConnector = new JSONDataConnector();
	
	String fileName = getResourcesFileName("json/test_data_type.json");
	dataConnector.setFileName(fileName);
	
	System.out.println("\nCreate JSONDataConnector: fileName=" + fileName);

	// Session
	DSSession session = producer.openSession(dataConnector);
	assertNotNull(session);
	
	// 1. By Session
	session = producer.openSession(dataConnector);
	assertNotNull(session);
	
	DSDataSource dataSource = new DSBaseDataSource();
	dataSource.setType("json");
	dataSource.setQueryText("data-set.records");
	
	DSField field = new DSField();
	field.setName("f_string");
	field.setDataType("String");
	dataSource.addField(field);

	field = new DSField();
	field.setName("f_integer");
	field.setDataType("Integer");
	dataSource.addField(field);

	field = new DSField();
	field.setName("f_float");
	field.setDataType("Float");
	dataSource.addField(field);
	
	field = new DSField();
	field.setName("f_date");
	field.setDataType("Date");
	dataSource.addField(field);
	
	field = new DSField();
	field.setName("f_time");
	field.setDataType("Time");
	dataSource.addField(field);

	field = new DSField();
	field.setName("f_date_time");
	field.setDataType("DateTime");
	dataSource.addField(field);
	
	DSDataSet dataSet = producer.openDataSet(session, dataSource);
	
	int row = 0;
   	System.out.println("Load JSON DataSet:");
   	
   	SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
   	SimpleDateFormat timeFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
   	SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
   	
   	String valueString = null;
   	Integer valueInteger = null;
   	Float valueFloat = null;
   	Date valueDate = null;
   	Date valueTime = null;
   	Date valueDateTime = null;
   	
   	while (dataSet.next()) {
   	    
   	    valueString = (String) dataSet.getValue("f_string");
   	    valueInteger = (Integer) dataSet.getValue("f_integer");
   	    valueFloat = (Float) dataSet.getValue("f_float");
   	    valueDate = (Date) dataSet.getValue("f_date");
   	    valueTime = (Date) dataSet.getValue("f_time");
   	    valueDateTime = (Date) dataSet.getValue("f_date_time");
   	    
   	    if (row == 0) {
   		assertEquals(valueString, "String 1");
   		assertEquals(valueInteger, new Integer(100));
   		assertEquals(valueFloat, new Float(123.45));
   		assertEquals(valueDate, getDate(2001, 1, 11));
   		assertEquals(valueTime, getTime(11, 1, 21));
   		assertEquals(valueDateTime, getDateTime(2011, 1, 11, 21, 1, 11));
   	    } else if (row == 1) {
   		assertEquals(valueString, "String 2");
   		assertEquals(valueInteger, new Integer(200));
   		assertEquals(valueFloat, new Float(234.56));
   		assertEquals(valueDate, getDate(2002, 2, 12));
   		assertEquals(valueTime, getTime(12, 2, 22));
   		assertEquals(valueDateTime, getDateTime(2012, 2, 12, 22, 2, 12));
   	    } else if (row == 2) {
   		assertEquals(valueString, "String 3");
   		assertEquals(valueInteger, new Integer(300));
   		assertEquals(valueFloat, new Float(345.67));
   		assertEquals(valueDate, getDate(2003, 3, 13));
   		assertEquals(valueTime, getTime(13, 3, 23));
   		assertEquals(valueDateTime, getDateTime(2013, 3, 13, 23, 3, 13));
   	    }

   	    System.out.println(" Row[" + row + "] : " 
   	    + valueString 
   	    + ", " + valueInteger 
   	    + ", " + valueFloat 
   	    + ", " + formatDate(valueDate, dateFormat) 
   	    + ", " + formatDate(valueTime, timeFormat)
   	    + ", " + formatDate(valueDateTime, dateTimeFormat)
   	    );
   	    
   	    row++;
   	}
	assertEquals(row, 3);
    }
    

    
}
