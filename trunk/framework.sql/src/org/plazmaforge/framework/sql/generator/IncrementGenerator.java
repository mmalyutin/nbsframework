package org.plazmaforge.framework.sql.generator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;


public class IncrementGenerator extends AbstractSQLGenerator {

    public static final String NAME = "increment"; 
    
    public Object generate(Connection cn, Map<String, Object> parameters) throws SQLException {
	String table = getTable(parameters);
	String column = getColumn(parameters);
	Statement stm = cn.createStatement();
	ResultSet rs = null;
	try {
	    rs = stm.executeQuery("SELECT MAX(" + column + ") FROM " + table);
	    if (!rs.next()) {
		return null;
	    }
	    Object value = rs.getObject(1);
	    
	    // Set 0 if null
	    if (value == null) {
		value = 0;
	    }
	    if (value instanceof Number) {
		value = ((Number) value).intValue() + 1;
		return value;
	    }
	} finally {
	    close(rs);
	    close(stm);
	}
	return null;
    }
  
    
}
