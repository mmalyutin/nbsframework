package org.plazmaforge.framework.sql.dialect;

public class MySQLDialect extends Dialect {

    public String getName() {
	return "mysql";
    }

    public String getDisplayName() {
	return "MySQL";
    }

    protected boolean supportsProductName(String productName) {
	return productName.toLowerCase().indexOf("mysql") > -1;
    }
    
    public String getCountString(String sql) {
	return super.getCountString(sql) + " ca"; // Add is special alias for MySQL
    }
    
    public String getLimitString(String sql, int offset, int limit) {
	boolean hasOffset = offset > 0;
	StringBuffer buf = new StringBuffer(sql);
	buf.append(hasOffset ? (" LIMIT " + offset + ", " + limit) : (" LIMIT " + limit));
	return buf.toString();
    }
    
    public String getSequenceNextValString(String sequenceName) {
	return "UPDATE " + sequenceName + " SET ID = LAST_INSERT_ID(ID + 1); SELECT LAST_INSERT_ID()";
    }

}
