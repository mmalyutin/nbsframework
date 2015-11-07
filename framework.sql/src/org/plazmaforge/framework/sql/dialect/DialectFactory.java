package org.plazmaforge.framework.sql.dialect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class DialectFactory {

    private static Map<String, Dialect> dialects = new HashMap<String, Dialect>();
    
    private static Dialect genericDialect;
    
    static {
	registerDialects();
    }
    
    private static void registerDialects() {
	registerDialect(new MySQLDialect());
	registerDialect(new PostgreSQLDialect());
	registerDialect(new HSQLDialect());
	registerDialect(new FirebirdDialect());
	registerDialect(new OracleDialect());
    }
    
    
    public static void registerDialect(Dialect dialect) {
	String key = dialect.getName(); 
	dialects.put(key, dialect);
    }
    
   
    public static Map<String, Dialect> getDialects() {
        return dialects;
    }
    
    private static Dialect getGenericDialect() {
	if (genericDialect == null)
	    genericDialect = new GenericDialect();
	return genericDialect;
    }

    
    public static Dialect getDialect(Connection connection) {
	
	// If connection is not valid return an generic dialect
	try {
	    if (connection == null || connection.isClosed()) {
		return getGenericDialect();
	    }
	} catch (SQLException ex) {
	    return getGenericDialect();
	}
	
	//////////////////////////////////////////////////////////////
	
	// Get an product name 
	DatabaseMetaData metaData = null;
	String productName = null;
	try {
	    metaData = connection.getMetaData();
	    productName = metaData.getDatabaseProductName();
	} catch (Throwable ex) {
	    return getGenericDialect();
	}
	
	///////////////////////////////////////////////////////////////
	
	// Analyze SQL Dialects
	Map<String, Dialect> dialectMap = getDialects();
	Collection<Dialect> dialects = dialectMap.values();
	for (Dialect dialect : dialects) {
	    if (dialect.supportsProduct(connection)) {
		return dialect;
	    }
	}
	
	// DEFAULT DIALECT
	return getGenericDialect();

    }
}
