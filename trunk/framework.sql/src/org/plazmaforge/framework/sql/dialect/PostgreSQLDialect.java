package org.plazmaforge.framework.sql.dialect;

public class PostgreSQLDialect extends Dialect {

    public String getName() {
	return "postgresql";
    }
    
    public String getDisplayName() {
	return "PostgreSQL";
    }

    protected boolean supportsProductName(String productName) {
	return productName.toLowerCase().indexOf("postgresql") > -1;
    }
 
    public String getCountString(String sql) {
	return super.getCountString(sql) + " ca"; // Add is special alias for MySQL
    }

    public String getLimitString(String sql, int offset, int limit) {
	boolean hasOffset = offset > 0;
	StringBuffer buf = new StringBuffer(sql);
	buf.append(hasOffset ? (" LIMIT " + limit + " OFFSET " + offset) : (" LIMIT " + limit));
	return buf.toString();
    }
    
    public String getSequenceNextValString(String sequenceName) {
	return "select " + getSelectSequenceNextValString(sequenceName);
    }

    public String getSelectSequenceNextValString(String sequenceName) {
	return "nextval ('" + sequenceName + "')";
    }
}
