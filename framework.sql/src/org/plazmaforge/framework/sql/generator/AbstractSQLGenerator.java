package org.plazmaforge.framework.sql.generator;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.plazmaforge.framework.sql.dialect.Dialect;
import org.plazmaforge.framework.util.DBUtils;

public abstract class AbstractSQLGenerator extends AbstractGenerator {


    protected Dialect getDialect(Map<String, Object> parameters) {
	return (Dialect) parameters.get(PARAMETER_DIALECT);
    }
    
    protected String getTable(Map<String, Object> parameters) {
	return (String) parameters.get(PARAMETER_TABLE);
    }
    
    protected String getColumn(Map<String, Object> parameters) {
	return (String) parameters.get(PARAMETER_COLUMN);
    }

    protected String getSequence(Map<String, Object> parameters) {
	return (String) parameters.get(PARAMETER_SEQUENCE);
    }

    
    protected void close(Statement stm) {
	DBUtils.close(stm);
    }

    protected void close(ResultSet rs) {
	DBUtils.close(rs);
    }
    
}
