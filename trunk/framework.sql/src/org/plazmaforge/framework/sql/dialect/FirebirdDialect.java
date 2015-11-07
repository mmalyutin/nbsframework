package org.plazmaforge.framework.sql.dialect;

public class FirebirdDialect extends Dialect {

    public String getName() {
	return "firebird";
    }
    
    public String getDisplayName() {
	return "Firebird";
    }
    
    protected boolean supportsProductName(String productName) {
	return productName.toLowerCase().indexOf("firebird") > -1;
    }
    
    public String getLimitString(String sql, int offset, int limit) {
	boolean hasOffset = offset > 0;
	StringBuffer buf = new StringBuffer(sql);
	buf.insert(sql.toUpperCase().indexOf("SELECT") + 6, hasOffset ? (" FIRST " + limit + " SKIP " + offset) : (" FIRST " + limit));
	return buf.toString();
    }
    
    public String getSequenceNextValString(String sequenceName) {
	return "select " + getSelectSequenceNextValString( sequenceName ) + " from RDB$DATABASE";
    }

    public String getSelectSequenceNextValString(String sequenceName) {
	return "gen_id( " + sequenceName + ", 1 )";
    }    
}
