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
package org.plazmaforge.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import junit.framework.TestCase;

/**
 * @author ohapon
 *
 */
public abstract class DataTestCase extends TestCase {

public static final String LINE_SEPARATOR = "==================================================================";
    
    private boolean outmode = true; 
    
    private Connection connection;
    
    private static TestDB testDB;

    protected void print(Object x) {
	if (!outmode) {
	    return;
	}
	System.out.print(x);
    }
    
    protected void println(Object x) {
	if (!outmode) {
	    return;
	}
	System.out.println(x);
    }

    protected void print(String x) {
	if (!outmode) {
	    return;
	}
	System.out.print(x);
    }
    
    protected void println(String x) {
	if (!outmode) {
	    return;
	}
	System.out.println(x);
    }

    public boolean isOutmode() {
        return outmode;
    }

    public void setOutmode(boolean outmode) {
        this.outmode = outmode;
    }

    public Connection getConnection() throws SQLException {
	if (connection != null) {
	    return connection;
	}
	try {
	    connection = getJDBCConnection();
	    if (!getTestDB().isInit()) {
		getTestDB().init(connection);
	    }
	} catch (ClassNotFoundException ex) {
	    throw new SQLException(ex.getMessage());
	}
	return connection;
    }
    
    public Connection getJDBCConnection() throws ClassNotFoundException, SQLException {
	String driver = "org.hsqldb.jdbcDriver";
	//String url = "jdbc:hsqldb:hsql://localhost";
	String url = "jdbc:hsqldb:mem:mydb";
	String user = "sa";
	String password = "";
	Class.forName(driver);
	Connection connection = DriverManager.getConnection(url, user, password);
	connection.setAutoCommit(false);
	return connection;
    }
    
    public void close(Connection connection) {
	try {
	    if (connection == null || connection.isClosed()) {
		return;
	    }
	    connection.close();
	} catch (Exception ex) {
	    
	}
    }
    
    private static TestDB getTestDB() {
	if (testDB == null) {
	    testDB = new TestDB();
	}
	return testDB;
    }
    

}
