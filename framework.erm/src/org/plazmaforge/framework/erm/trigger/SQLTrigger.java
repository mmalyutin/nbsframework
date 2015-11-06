package org.plazmaforge.framework.erm.trigger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.erm.EntityAccessor;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.query.TypeValue;
import org.plazmaforge.framework.util.DBUtils;


/**
 * SQL trigger implementation
 * 
 * @author ohapon
 *
 */
public class SQLTrigger extends AbstractTrigger {

    public static String COMMAND_SEPARATOR = ";"; 
    
    public void execute(Connection cn, Map<String, Object> parameters) throws SQLException {
	String script = getScriptText();
	if (isEmpty(script)) {
	    return;
	}
	
	// Get value from parameter map
	Entity entity = getEntity(parameters);
	EntityAccessor entityAccessor = EntityHelper.getEntityAccessor(entity); 
	Object obj = getData(parameters);
	
	// Get commands
	String[] commands = getCommands(COMMAND_SEPARATOR);
	for (String cmd : commands) {
	    executeCommand(cn, cmd, parameters, entity, entityAccessor, obj);
	}
    }
    
    /**
     * Execute command
     * @param cn
     * @param cmd
     * @param parameters
     * @param entity
     * @param entityAccessor
     * @param obj
     * @throws SQLException
     */
    protected void executeCommand(Connection cn, String cmd, Map<String, Object> parameters, Entity entity, EntityAccessor entityAccessor, Object obj) throws SQLException {
	StringTokenizer tokens = new StringTokenizer(cmd, " \t\n\r\f,()", true);
	StringBuffer buf = new StringBuffer();
	String token = null;
	List<TypeValue> stmParameters = new ArrayList<TypeValue>();
	while (tokens.hasMoreTokens()) {
	    token = tokens.nextToken();
	    
	    // If token start with ':' then token is parameter. For example :id, :name
	    if (token.startsWith(":") ) {
		
		// If the parameter is only ':' then it is invalid
		if (token.length() < 2 ) {
		    throw new RuntimeException("Invalid parameter with ':', command='" + cmd + "', token='" + token + "'");
		}
		
		// Replace parameter to '?'. For example ':id' -> '?' 
		buf.append("?"); // add parameter
		String parameterName = token.substring(1);
		TypeValue parameterValue = getParameterTypeValue(cmd, parameters, entity, entityAccessor, obj, parameterName, true);
		stmParameters.add(parameterValue);
	    } else {
		buf.append(token);
	    }
	}
	String sql = buf.toString();
	PreparedStatement stm = null; 
	try {
	    stm = cn.prepareStatement(sql);
	    int count = stmParameters.size();
	    for (int i = 0; i < count; i++) {
		TypeValue prmValue = stmParameters.get(i);
		Type type = prmValue.getType();
		Object value = prmValue.getValue();
		if (value == null) {
		    stm.setNull(i + 1, type.getSqlType());
		} else {
		    type.set(stm, value, i + 1);
		}
	    }
	    stm.execute();
	} finally {
	    DBUtils.close(stm);
	}
    }

}
