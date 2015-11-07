package org.plazmaforge.framework.sql.dialect;

public class OracleDialect extends Dialect {

    public String getName() {
	return "oracle";
    }
    
    public String getDisplayName() {
	return "Oracle";
    }
    
    protected boolean supportsProductName(String productName) {
	return productName.toLowerCase().indexOf("oracle") > -1;
    }

    public String getLimitString(String sql, int offset, int limit) {
	boolean hasOffset = offset > 0;
	int startRow = offset;
	int endRow = startRow + limit;
	sql = sql.trim();
	boolean isForUpdate = false;
	if (sql.toLowerCase().endsWith(" FOR UPDATE")) {
	    sql = sql.substring(0, sql.length() - 11);
	    isForUpdate = true;
	}
	StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
	if (hasOffset) {
	    pagingSelect.append("SELECT * FROM (SELECT row_.*, rownum rownum_ FROM ( ");
	} else {
	    pagingSelect.append("SELECT * FROM ( ");
	}
	
	pagingSelect.append(sql); // BODY
	
	if (hasOffset) {
	    pagingSelect.append(" ) row_ WHERE rownum <= " + endRow + ") WHERE rownum_ > " + startRow);
	} else {
	    pagingSelect.append(" ) WHERE rownum <= " + endRow);
	}
	
	if (isForUpdate) {
	    pagingSelect.append(" FOR UPDATE");
	}

	return pagingSelect.toString();
    }
    
    
    public String getSequenceNextValString(String sequenceName) {
	return "select " + getSelectSequenceNextValString(sequenceName)	+ " from dual";
    }

    public String getSelectSequenceNextValString(String sequenceName) {
	return sequenceName + ".nextval";
    }
}
