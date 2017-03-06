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
package org.plazmaforge.framework.datastorage.support.sql;

import java.sql.Connection;

import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.datastorage.AbstractDSTestCase;

/**
 * @author ohapon
 *
 */
public class SQLDataConnectorDriverTest extends AbstractDSTestCase {

    
    public void testSQLSession() throws Exception {

	// Data Producer
	DataProducer producer = new SQLDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	String driver = "org.hsqldb.jdbcDriver";
	String url = "jdbc:hsqldb:mem:mydb";
	String username = "sa";
	String password = "";
	
	// 1.1 by Data Connector: FAIL
	SQLDataConnector dataConnector = new SQLDataConnector();
	
	dataConnector.setDriverClassName(null); // WITHOUT DRIVER
	dataConnector.setUrl(url);
	dataConnector.setUsername(username);
	dataConnector.setPassword(password);
	
	DSSession session = null;
	SQLSession sqlSession = null;
	Connection connection = null;
	String connectionString = null;

	// Session	
	try {
	    session = producer.openSession(dataConnector);
	} catch (DSException ex) {
	    
	}

	// 2.1 by Connection string: FAIL
	try {
	    connectionString = "jdbc:hsqldb:mem:mydb->(username=sa)"; // WITHOUT DRIVER
	    session = producer.openSession(connectionString);
	} catch (DSException ex) {
	    
	}

	
	// 1.2 by Data Connector: SUCCESS
	dataConnector = new SQLDataConnector();
	
	dataConnector.setDriverClassName(driver);
	dataConnector.setUrl(url);
	dataConnector.setUsername(username);
	dataConnector.setPassword(password);
	
	session = producer.openSession(dataConnector);
	assertNotNull(session);
	assertTrue(session instanceof SQLSession);
	
	sqlSession = (SQLSession) session;
	
	connection = sqlSession.getConnection();
	assertNotNull(connection);


	// 2.1  by Connection string: SUCCESS
	connectionString = "jdbc:hsqldb:mem:mydb->(driver=org.hsqldb.jdbcDriver, username=sa)";
	session = producer.openSession(connectionString);
	assertNotNull(session);
	assertTrue(session instanceof SQLSession);
	
	sqlSession = (SQLSession) session;
	
	connection = sqlSession.getConnection();
	assertNotNull(connection);
	
    }
    
}
