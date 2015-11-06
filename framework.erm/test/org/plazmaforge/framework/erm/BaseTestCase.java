package org.plazmaforge.framework.erm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import junit.framework.TestCase;

public class BaseTestCase extends TestCase {

    public static final String LINE_SEPARATOR = "==================================================================";
    
    private boolean outmode = true; 
    
    private Connection connection;
    

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
	} catch (ClassNotFoundException ex) {
	    throw new SQLException(ex.getMessage());
	}
	return connection;
    }
    
    public Connection getJDBCConnection() throws ClassNotFoundException, SQLException {
	String driver = "org.hsqldb.jdbcDriver";
	String url = "jdbc:hsqldb:hsql://localhost";
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

}
