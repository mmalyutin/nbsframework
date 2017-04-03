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

package org.plazmaforge.framework.report;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import junit.framework.TestCase;

import org.plazmaforge.framework.datastorage.TestDB;
import org.plazmaforge.framework.util.DateUtils;

public class AbstractTestCase extends TestCase  {

    //public static final String DEFAULT_RESOURCES_DIR = "test/resources";
    
    public static final String DEFAULT_RESOURCES_DIR = "test/org/plazmaforge/framework/report/resources";
    
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    
    
    private String resourcesDir; 
    
    
    private Connection connection;
    
    private static TestDB testDB;

    
    
    protected void setUp()  throws Exception {
	if (resourcesDir != null) {
	    return;
	}
	// Iniitialize base resource directory
	String rootDir = new File("").getAbsolutePath();
	resourcesDir = rootDir + "/" + DEFAULT_RESOURCES_DIR; 
    }

    /**
     * Return base resource directory
     * @return
     */
    protected String getResourcesDir() {
        return resourcesDir;
    }
    

    /**
     * Return full file name in base resource directory 
     * @param fileName
     * @return
     */
    protected String getResourcesFileName(String fileName) {
        return (fileName == null  || resourcesDir == null) ? null : (getResourcesDir() + "/" + fileName);
    }

    protected Date getDate(int year, int month, int day) {
	return DateUtils.getDate(year, month, day);
    }
    
    protected Date getTime(int h, int m, int s) {
	return DateUtils.getTime(h, m, s);
    }
    
    protected Date getTime(int h, int m, int s, int ms) {
	return DateUtils.getTime(h, m, s, ms);
    }
    
    protected Date getDateTime(int year, int month, int day, int h, int m, int s) {
	return DateUtils.getDateTime(year, month, day, h, m, s);
    }
    
    protected Date getDateTime(int year, int month, int day, int h, int m, int s, int ms) {
	return DateUtils.getDateTime(year, month, day, h, m, s, ms);
    }

    protected String formatDate(Date date, DateFormat format) {
	return date == null ? null : format.format(date);
    }

    ////
    
    protected Connection getConnection() throws SQLException {
	if (connection == null) {
	    connection = openTestConnection();
	}
	return connection;
    }
    
    protected Connection openTestConnection() throws SQLException {
	try {
	    Connection connection = getJDBCConnection();
	    if (!getTestDB().isInit()) {
		getTestDB().init(connection);
	    }
	    return connection;
	} catch (ClassNotFoundException ex) {
	    throw new SQLException(ex.getMessage());
	}
    }
    
    protected Connection getJDBCConnection() throws ClassNotFoundException, SQLException {
	String driver = "org.hsqldb.jdbcDriver";
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
