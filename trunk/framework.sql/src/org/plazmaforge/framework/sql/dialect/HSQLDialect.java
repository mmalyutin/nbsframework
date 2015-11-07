package org.plazmaforge.framework.sql.dialect;

public class HSQLDialect extends Dialect {

    public String getName() {
	return "HSQL Database Engine";
    }
    
    protected boolean supportsProductName(String productName) {
	return productName.indexOf("HSQL Database Engine") > -1;
    }
    
    public String getLimitString(String sql, int offset, int limit) {
	boolean hasOffset = offset > 0;
	StringBuffer buf = new StringBuffer(sql);
	buf.insert(sql.toUpperCase().indexOf("SELECT") + 6, hasOffset ? (" LIMIT " + offset + " " + limit) : (" TOP " + limit));
	return buf.toString();
    }

    public String getSequenceNextValString(String sequenceName) {
	//return "select next value for " + sequenceName + " FROM system_sequences where sequence_name = '" + sequenceName.toUpperCase() + "'";
	return "call next value for " + sequenceName;
    }
    
    public String getSelectSequenceNextValString(String sequenceName) {
	return "next value for " + sequenceName;
    }
    
}
