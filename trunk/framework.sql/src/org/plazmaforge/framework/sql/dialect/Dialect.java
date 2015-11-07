package org.plazmaforge.framework.sql.dialect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

public abstract class Dialect {
    
    
    public abstract String getName();
    
    public String getDisplayName() {
	return getName();
    }

    
    public boolean supportsProduct(Connection connection) {
	
	// Get an product name 
	DatabaseMetaData metaData = null;
	String productName = null;
	try {
	    metaData = connection.getMetaData();
	    productName = metaData.getDatabaseProductName();
	} catch (Throwable ex) {
	    return false;
	}
	
	// Get an major version
	int majorVersion = -1;
	try {
	    majorVersion = metaData.getDatabaseMajorVersion();
	} catch (Throwable ex) {
	    majorVersion = -1;
	}
	
	// Get an product version
	String productVersion = null;
	try {
	    productVersion = metaData.getDatabaseProductVersion();
	} catch (Throwable ex) {
	    productVersion = "nonSupported";
	}

	return supportsProduct(productName, majorVersion, productVersion);
    }
    
    public boolean supportsProduct(String productName, int majorVersion, String productVersion) {
	return supportsProductName(productName) && supportsMajorVersion(majorVersion) && supportsProductVersion(productVersion);
    }

    protected boolean supportsProductName(String productName) {
	return false;
    }
    
    protected boolean supportsMajorVersion(int majorVersion) {
	return true;
    }
    
    protected boolean supportsProductVersion(String productVersion) {
	return true;
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public String getCountString(String sql) {
	return "SELECT COUNT(*) FROM (" + sql + ")";
    }

    public String getLimitString(String sql, int offset, int limit) {
	return sql;
    }

    public String getSequenceNextValString(String sequenceName) {
	return null;
    }
    
}
