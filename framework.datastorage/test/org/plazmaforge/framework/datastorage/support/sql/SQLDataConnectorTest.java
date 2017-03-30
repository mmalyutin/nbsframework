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
public class SQLDataConnectorTest extends AbstractDSTestCase {

    protected void setUp() throws Exception {
	super.setUp();
	openTestConnection();
    }

    public void testDataManager() throws Exception {
	
	String connectionString = "sql::jdbc:hsqldb:mem:mydb->(driver=org.hsqldb.jdbcDriver, username=sa)";
	DSSession session = DataManager.openSession(connectionString);
	assertNotNull(session);
	assertTrue(session instanceof SQLSession);
	
    }
    
    public void testSQLSession() throws Exception {

	// Data Producer
	DataProducer producer = new SQLDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// 1. by Data Connector
	SQLDataConnector dataConnector = new SQLDataConnector();
	
	String driver = "org.hsqldb.jdbcDriver";
	String url = "jdbc:hsqldb:mem:mydb";
	String username = "sa";
	String password = "";
	
	
	dataConnector.setDriverClassName(driver);
	dataConnector.setUrl(url);
	dataConnector.setUsername(username);
	dataConnector.setPassword(password);
	
	System.out.println("Create SQLDataConnector: driver=" + driver + ", url=" + url);

	// Session
	DSSession session = producer.openSession(dataConnector);
	assertNotNull(session);
	assertTrue(session instanceof SQLSession);
	
	SQLSession sqlSession = (SQLSession) session;
	
	Connection connection = sqlSession.getConnection();
	assertNotNull(connection);


	// 2. by Connection string
	String connectionString = "jdbc:hsqldb:mem:mydb->(driver=org.hsqldb.jdbcDriver, username=sa)";
	session = producer.openSession(connectionString);
	assertNotNull(session);
	assertTrue(session instanceof SQLSession);
	
	sqlSession = (SQLSession) session;
	
	connection = sqlSession.getConnection();
	assertNotNull(connection);
	
    }
    
    public void testSQLResultSet() throws Exception {

   	// Data Producer
   	DataProducer producer = new SQLDataProducerFactory().getDataProducer();
   	assertNotNull(producer);
   	
   	// Data Connector
	SQLDataConnector dataConnector = new SQLDataConnector();
	
	String driver = "org.hsqldb.jdbcDriver";
	String url = "jdbc:hsqldb:mem:mydb";
	String username = "sa";
	String password = "";
	
	
	dataConnector.setDriverClassName(driver);
	dataConnector.setUrl(url);
	dataConnector.setUsername(username);
	dataConnector.setPassword(password);
	
	System.out.println("Create SQLDataConnector: driver=" + driver + ", url=" + url);

   	// Session
   	DSSession session = producer.openSession(dataConnector);
   	assertNotNull(session);
   	assertTrue(session instanceof SQLSession);

   	// 1. by Session
   	String query = "SELECT * FROM TEST";
   	DSResultSet resultSet = producer.openResultSet(session, query);
   	assertNotNull(resultSet);
   	assertTrue(resultSet instanceof SQLResultSet);
   	SQLResultSet sqlResultSet = (SQLResultSet) resultSet;
   	
   	System.out.println("\nOpen SQLResultSet by session: query=" + query);
   	printSQLResultSet(sqlResultSet);
   	
   	session.close();
   	
   	/*
   	// 2. by Connection string
   	String connectionString = "jdbc:hsqldb:mem:mydb->(driver=org.hsqldb.jdbcDriver, username=sa)";
   	resultSet = producer.openResultSet(connectionString);
   	assertNotNull(resultSet);
   	assertTrue(resultSet instanceof SQLResultSet);
   	sqlResultSet = (SQLResultSet) resultSet;
   	
   	System.out.println("\nOpen SQLResultSet by internal connection string: '" + connectionString + "'");
   	printSQLResultSet(sqlResultSet);
   	*/
    }
    
    public void testSQLDataSet() throws Exception {

	// Data Producer
	DataProducer producer = new SQLDataProducerFactory().getDataProducer();
	assertNotNull(producer);
	
	// Data Connector
	SQLDataConnector dataConnector = new SQLDataConnector();
	
	String driver = "org.hsqldb.jdbcDriver";
	String url = "jdbc:hsqldb:mem:mydb";
	String username = "sa";
	String password = "";
	
	
	dataConnector.setDriverClassName(driver);
	dataConnector.setUrl(url);
	dataConnector.setUsername(username);
	dataConnector.setPassword(password);
	
	System.out.println("Create SQLDataConnector: driver=" + driver + ", url=" + url);

   	// Session
   	DSSession session = producer.openSession(dataConnector);
   	assertNotNull(session);
   	assertTrue(session instanceof SQLSession);
	

   	DSDataSource dataSource = new DSBaseDataSource();
	dataSource.setType("sql");
	dataSource.setQueryText("SELECT * FROM TEST");
	
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
   	System.out.println("Load SQL DataSet:");
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
    
    private int printSQLResultSet(SQLResultSet resultSet) throws DSException {
	int row = 0;
	System.out.println("Load SQL data:");
	while (resultSet.next()) {
	    System.out.println(" Row[" + row + "] : " + resultSet.getValue(0) + ", " + resultSet.getValue(1) + ", " + resultSet.getValue(2));
	    row++;
	}
	return row;
    }
    
}
