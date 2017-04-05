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

package org.plazmaforge.framework.datastorage.support.xlsx;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.plazmaforge.framework.core.datastorage.DSBaseDataSource;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.datastorage.AbstractTestCase;

public class XLSXDataPathTest extends AbstractTestCase {

    
    public void testXLSXDataSet() throws Exception {

	// TODO: Temp solution: Use 'locale' attribute to configure locale in DataConnector/DataSet/DataResultSet 
	Locale.setDefault(Locale.ENGLISH);
	
	// Data Producer
	DataProducer producer = new XLSXDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	XLSXDataConnector dataConnector = new XLSXDataConnector();
	
	String fileName = getResourcesFileName("xlsx/test_data_type.xlsx");
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
	field.setName("FIELD_1");
	field.setPath("F_FLOAT");	// By Path
	field.setDataType("Float");
	dataSource.addField(field);

	field = new DSField();
	field.setName("FIELD_2");
	field.setPath("F_INTEGER");	// By Path
	field.setDataType("Integer");
	dataSource.addField(field);

	field = new DSField();
	field.setName("FIELD_3");
	field.setPath("F_STRING");	// By Path
	field.setDataType("String");
	dataSource.addField(field);
	
	field = new DSField();
	field.setName("F_DATE");
	field.setDataType("Date");
	dataSource.addField(field);
	
	field = new DSField();
	field.setName("F_TIME");
	field.setDataType("Time");
	dataSource.addField(field);

	field = new DSField();
	field.setName("F_DATE_TIME");
	field.setDataType("DateTime");
	dataSource.addField(field);

	field = new DSField();
	field.setName("F_DATE_FMT");
	field.setDataType("Date");
	field.setFormat("dd/MMM/yyyy"); // DateFormat (Locale)
	dataSource.addField(field);

	field = new DSField();
	field.setName("F_TIME_FMT");
	field.setDataType("Time");
	field.setFormat("hh:mm a"); // TimeFormat
	dataSource.addField(field);

	DSDataSet dataSet = producer.openDataSet(session, dataSource);
	
	int row = 0;
   	System.out.println("Load XLSX DataSet:");
   	
   	SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
   	SimpleDateFormat timeFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
   	SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
   	
   	Float value1 = null;
   	Integer value2 = null;
   	String value3 = null;
   	Date valueDate = null;
   	Date valueTime = null;
   	Date valueDateTime = null;
   	Date valueDateFmt = null;
   	Date valueTimeFmt = null;
   	
   	while (dataSet.next()) {
   	    
   	    value1 = (Float) dataSet.getValue("FIELD_1");
   	    value2 = (Integer) dataSet.getValue("FIELD_2");
   	    value3 = (String) dataSet.getValue("FIELD_3");
   	    valueDate = (Date) dataSet.getValue("F_DATE");
   	    valueTime = (Date) dataSet.getValue("F_TIME");
   	    valueDateTime = (Date) dataSet.getValue("F_DATE_TIME");
   	    valueDateFmt = (Date) dataSet.getValue("F_DATE_FMT");
   	    valueTimeFmt = (Date) dataSet.getValue("F_TIME_FMT");
   	    
   	    if (row == 0) {
   		assertEquals(value1, new Float(123.45));
   		assertEquals(value2, new Integer(100));
   		assertEquals(value3, "String 1");
   		assertEquals(valueDate, getDate(2001, 1, 11));
   		assertEquals(valueTime, getTime(11, 1, 21));
   		assertEquals(valueDateTime, getDateTime(2011, 1, 11, 21, 1, 11));
   		assertEquals(valueDateFmt, getDate(1951, 1, 21));
   		assertEquals(valueTimeFmt, getTime(1, 21, 00));
   	    } else if (row == 1) {
   		assertEquals(value1, new Float(234.56));
   		assertEquals(value2, new Integer(200));
   		assertEquals(value3, "String 2");
   		assertEquals(valueDate, getDate(2002, 2, 12));
   		assertEquals(valueTime, getTime(12, 2, 22));
   		assertEquals(valueDateTime, getDateTime(2012, 2, 12, 22, 2, 12));
   		assertEquals(valueDateFmt, getDate(1952, 2, 22));
   		assertEquals(valueTimeFmt, getTime(14, 22, 00));
   	    } else if (row == 2) {
   		assertEquals(value1, new Float(345.67));
   		assertEquals(value2, new Integer(300));   		
   		assertEquals(value3, "String 3");
   		assertEquals(valueDate, getDate(2003, 3, 13));
   		assertEquals(valueTime, getTime(13, 3, 23));
   		assertEquals(valueDateTime, getDateTime(2013, 3, 13, 23, 3, 13));
   		assertEquals(valueDateFmt, getDate(1953, 3, 23));
   		assertEquals(valueTimeFmt, getTime(3, 23, 00));
   	    }

   	    System.out.println(" Row[" + row + "] : "
            + value1 
            + ", " + value2 
            + ", " + value3 
   	    + ", " + formatDate(valueDate, dateFormat) 
   	    + ", " + formatDate(valueTime, timeFormat)
   	    + ", " + formatDate(valueDateTime, dateTimeFormat)
   	    + ", " + formatDate(valueDateFmt, dateFormat)
   	    + ", " + formatDate(valueTimeFmt, timeFormat)
   	    );
   	    
   	    row++;
   	}
	assertEquals(row, 3);
    }


}
