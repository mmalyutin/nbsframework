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
package org.plazmaforge.framework.core.datastorage;

import java.sql.Connection;

import org.plazmaforge.framework.DataTestCase;
import org.plazmaforge.framework.core.exception.DSException;

/**
 * @author ohapon
 *
 */
public class DataManagerTest extends DataTestCase {

    public void testOpenSesion() throws Exception  {
	
	// Open session by NULL DataConnector
	try {
	    DataManager.openSession((DSDataConnector) null);
	} catch (DSException ex) {
	}

	// Open session by NULL Connection
	try {
	    DataManager.openSession((Connection) null);
	} catch (DSException ex) {
	}

	
	// Open session by Connection
	Connection connection = getConnection();
	assertNotNull(connection);
	
	DSSession session = DataManager.openSession(connection);
	assertNotNull(session);
	
	assertEquals(session.isClosed(), false);
	
	
	// Open session by NULL connection string
	try {
	    session = DataManager.openSession((String) null);
	} catch (DSException ex) {
	}	

	// Open session by connection string
	session = DataManager.openSession("sql::jdbc:hsqldb:mem:mydb");
	assertEquals(session.isClosed(), false);
    }
    
    public void testOpenResultSet() throws Exception  {

	Connection connection = getConnection();
	assertNotNull(connection);
	
	DSSession session = DataManager.openSession(connection);
	assertNotNull(session);
	
	assertEquals(session.isClosed(), false);
	
	// By DataSource
	String query = "SELECT PRODUCT_ID, PRODUCT_NAME, PRICE FROM PRODUCT";
	DSDataSource dataSource = new DSBaseDataSource();
	dataSource.setType("sql");
	dataSource.setQueryText(query);
	
	DSResultSet resultSet = DataManager.openResultSet(session, dataSource);
	assertNotNull(resultSet);
	
	assertEquals(resultSet.isEmpty(), false); // TODO
	
	int count = 0;
	while (resultSet.next()) {
	    count++;
	    //System.out.println("" + resultSet.getValue(0) + ", " + resultSet.getValue(1) + ", " + resultSet.getValue(2));
	}
	assertEquals(count, 100);
	System.out.println("Load records " + count);

	
	// By Query
	resultSet = DataManager.openResultSet(session, query);
	assertNotNull(resultSet);
	
	assertEquals(resultSet.isEmpty(), false); // TODO
	
	count = 0;
	while (resultSet.next()) {
	    count++;
	    //System.out.println("" + resultSet.getValue(0) + ", " + resultSet.getValue(1) + ", " + resultSet.getValue(2));
	}
	assertEquals(count, 100);
	System.out.println("Load records " + count);


	
    }
}
