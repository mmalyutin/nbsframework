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
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.datastorage.AbstractDSTestCase;

/**
 * @author ohapon
 *
 */
public class XMLDataPathTest extends AbstractDSTestCase {

    public void testXMLDataSet() throws Exception {

   	// Data Producer
   	DataProducer producer = new XMLDataProducerFactory().getDataProducer();
   	assertNotNull(producer);
   	
   	// Data Connector
   	XMLDataConnector dataConnector = new XMLDataConnector();
   	
   	// Encoding
   	String fileName = getResourcesFileName("xml/country_list.xml");
   	dataConnector.setFileName(fileName);
   	dataConnector.setCharset("utf-8");
   	
   	System.out.println("\nCreate XMLDataConnector: fileName=" + fileName);

   	// Session
   	DSSession session = producer.openSession(dataConnector);
   	assertNotNull(session);
   	
   	String query = "/div/table/tbody/tr";
   	DSDataSource dataSource = new DSBaseDataSource();
   	dataSource.setQueryText(query);
   	dataSource.setType("xml");
   	
   	DSField field = new DSField();
   	field.setName("Name");
   	field.setPath("td[2]/a");
   	field.setDataType("String");
   	dataSource.addField(field);

   	field = new DSField();
   	field.setName("Capital");
   	field.setPath("td[4]/a");
   	field.setDataType("String");
   	dataSource.addField(field);
   	
   	field = new DSField();
   	field.setName("ISOCode");
   	field.setPath("td[5]");
   	field.setDataType("String");
   	dataSource.addField(field);
   	
   	field = new DSField();
   	field.setName("Ref");
   	field.setPath("td[2]/a/@href");
   	field.setDataType("String");
   	dataSource.addField(field);
   	
   	DSDataSet dataSet = producer.openDataSet(session, dataSource);
   	
   	int row = 0;
      	System.out.println("Load XML DataSet:");
      	String valueName = null;
      	String valueCapital = null;
      	String valueISOCode = null;
      	String valueRef = null;
      	while (dataSet.next()) {
      	    
      	    valueName = (String) dataSet.getValue("Name");
      	    valueCapital = (String) dataSet.getValue("Capital");
      	    valueISOCode = (String) dataSet.getValue("ISOCode");
      	    valueRef = (String) dataSet.getValue("Ref");
      	    
      	    System.out.println(" Row[" + row + "] : " + valueName + ", " + valueCapital + ", " + valueISOCode + ", " + valueRef);
      	    row++;
      	}
   	//assertEquals(row, 3);
   }
    
}
