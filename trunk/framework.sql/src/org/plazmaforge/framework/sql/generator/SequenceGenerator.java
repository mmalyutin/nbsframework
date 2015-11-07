package org.plazmaforge.framework.sql.generator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.plazmaforge.framework.sql.dialect.Dialect;

public class SequenceGenerator extends AbstractSQLGenerator {

    public static final String NAME = "sequence";
    
    public Object generate(Connection cn, Map<String, Object> parameters) throws SQLException {
	Dialect dialect = getDialect(parameters);
	String sequence = getSequence(parameters);
	Statement stm = cn.createStatement();
	ResultSet rs = null;
	String sql = dialect.getSequenceNextValString(sequence);
	try {
	    int lastPos = sql.lastIndexOf(";");
	    if (lastPos > 0) {
		String execSql = sql.substring(0, lastPos);
		sql = sql.substring(lastPos + 1);
		stm.execute(execSql);
	    }
	    rs = stm.executeQuery(sql);
	    if (!rs.next()) {
		return null;
	    }
	    Object value = rs.getObject(1);
	    return value;
	} finally {
	    close(rs);
	    close(stm);
	}
    }

}
