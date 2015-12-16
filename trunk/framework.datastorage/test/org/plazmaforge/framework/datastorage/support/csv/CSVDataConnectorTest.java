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
public class CSVDataConnectorTest extends AbstractDSTestCase {

    public void testConnector() throws Exception {

	// Data Producer
	DataProducer producer = new CSVDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	CSVDataConnector dataConnector = new CSVDataConnector();
	
	String fileName = getResourcesFileName("test.csv");
	dataConnector.setFileName(fileName);
	
	System.out.println("Create CSVDataConnector: fileName=" + fileName);

	// Session
	DSSession session = producer.openSession(dataConnector);
	assertNotNull(session);

	// 1.
	CSVResultSet csvResultSet = (CSVResultSet) producer.openResultSet(session);
	assertNotNull(csvResultSet);
	System.out.println("\nOpen CSVResultSet by session: fileName=" + fileName);
	printResultSet(csvResultSet);
	
	session.close();
	
	// 2.
	String connectionString = fileName;
	csvResultSet = (CSVResultSet) producer.openResultSet(connectionString);
	assertNotNull(csvResultSet);
	System.out.println("\nOpen CSVResultSet by internal connection string: '" + connectionString + "'");
	printResultSet(csvResultSet);
    }
    
    public void testDatamMnager() throws Exception {
	DataManager.registerDataProducerFactory(CSVDataProducerFactory.TYPE, new CSVDataProducerFactory());
	
	String fileName = getResourcesFileName("test.csv");
	String connectionString = "csv::" + fileName;
	CSVResultSet csvResultSet = (CSVResultSet) DataManager.openResultSet(connectionString);
	System.out.println("\nOpen CSVResultSet by general connection string: '" + connectionString + "'");
	printResultSet(csvResultSet);
    }
    
    private void printResultSet(CSVResultSet csvResultSet) throws DSException {
	int row = 0;
	System.out.println("Load CSV data:");
	while (csvResultSet.next()) {
	    row++;
	    System.out.println(" Row[" + row + "] : " + csvResultSet.getValue(0) + ", " + csvResultSet.getValue(1) + ", " + csvResultSet.getValue(2));
	}
    }
    
}
