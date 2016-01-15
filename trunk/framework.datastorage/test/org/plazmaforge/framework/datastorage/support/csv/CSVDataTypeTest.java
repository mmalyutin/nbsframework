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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	field = new DSField();
	field.setName("F_DATE");
	field.setDataType("Date");
	dataSource.addField(field);
	
	field = new DSField();
	field.setName("F_TIME");
	field.setDataType("Time");
	dataSource.addField(field);
	
	DSDataSet dataSet = producer.openDataSet(session, dataSource);
	
	int row = 0;
   	System.out.println("Load CSV DataSet:");
   	
   	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
   	
   	String valueString = null;
   	Integer valueInteger = null;
   	Float valueFloat = null;
   	Date valueDate = null;
   	Date valueTime = null;
   	
   	while (dataSet.next()) {
   	    
   	    valueString = (String) dataSet.getValue(0);
   	    valueInteger = (Integer) dataSet.getValue("F_INTEGER");
   	    valueFloat = (Float) dataSet.getValue("F_FLOAT");
   	    valueDate = (Date) dataSet.getValue("F_DATE");
   	    valueTime = (Date) dataSet.getValue("F_TIME");
   	    
   	    if (row == 0) {
   		assertEquals(valueString, "String 1");
   		assertEquals(valueInteger, new Integer(100));
   		assertEquals(valueFloat, new Float(123.45));
   		assertEquals(valueDate, getDate(2001, 1, 11));
   		assertEquals(valueTime, getTime(11, 1, 21));
   	    } else if (row == 1) {
   		assertEquals(valueString, "String 2");
   		assertEquals(valueInteger, new Integer(200));
   		assertEquals(valueFloat, new Float(234.56));
   		assertEquals(valueDate, getDate(2002, 2, 12));
   		assertEquals(valueTime, getTime(12, 2, 22));
   	    } else if (row == 2) {
   		assertEquals(valueString, "String 3");
   		assertEquals(valueInteger, new Integer(300));
   		assertEquals(valueFloat, new Float(345.67));
   		assertEquals(valueDate, getDate(2003, 3, 13));
   		assertEquals(valueTime, getTime(13, 3, 23));
   	    }

   	    System.out.println(" Row[" + row + "] : " 
   	    + valueString 
   	    + ", " + valueInteger 
   	    + ", " + valueFloat 
   	    + ", " + formatDate(valueDate, dateFormat) 
   	    + ", " + formatDate(valueTime, timeFormat));
   	    
   	    row++;
   	}
	assertEquals(row, 3);
    }
    
    private String formatDate(Date date, DateFormat format) {
	return date == null ? null : format.format(date);
    }
    
    private Date getDate(int year, int month, int day) {
	Calendar calendar = Calendar.getInstance();
	
	calendar.set(Calendar.YEAR, year);
	calendar.set(Calendar.MONTH, month - 1);
	calendar.set(Calendar.DAY_OF_MONTH, day);
	
	calendar.set(Calendar.HOUR_OF_DAY, 0);
	calendar.set(Calendar.MINUTE, 0);
	calendar.set(Calendar.SECOND, 0);
	calendar.set(Calendar.MILLISECOND, 0);
	
	return calendar.getTime();
    }

    private Date getTime(int h, int m, int s) {
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(0);
	
	calendar.set(Calendar.HOUR_OF_DAY, h);
	calendar.set(Calendar.MINUTE, m);
	calendar.set(Calendar.SECOND, s);
	calendar.set(Calendar.MILLISECOND, 0);	
	return calendar.getTime();
    }

   
    
}
