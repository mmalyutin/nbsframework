package org.plazmaforge.framework.erm.trigger;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.plazmaforge.framework.erm.Accessor;
import org.plazmaforge.framework.erm.EntityAccessor;
import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.event.Event;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.procedure.Procedure;
import org.plazmaforge.framework.erm.query.TypeValue;
import org.plazmaforge.framework.util.StringUtils;

/**
 * Script trigger implementation
 * 
 * @author ohapon
 *
 */
public class MacrosTrigger extends AbstractTrigger {

    public static String COMMAND_SEPARATOR = ";";
    
    public static String PROCEDURE_SEPARATOR = "->";
    
    public static String SET_COMMAND = "SET";
    
    public static String CALL_COMMAND = "CALL";
  
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
     * 
     * @param cn
     * @param cmd
     * @param parameters
     * @param entity
     * @param entityAccessor
     * @param obj
     * @throws SQLException
     */
    protected void executeCommand(Connection cn, String cmd, Map<String, Object> parameters, Entity entity, EntityAccessor entityAccessor, Object obj) throws SQLException {
	String trimCmd = cmd.trim();
	String startCmd = trimCmd.toUpperCase();
	if (startCmd.startsWith(SET_COMMAND + " ")) {
	    executeSetCommand(cn, trimCmd, parameters, entity, entityAccessor, obj);
	} else if (startCmd.startsWith(CALL_COMMAND + " ") ) { 
	    executeCallCommand(cn, trimCmd, parameters, entity, entityAccessor, obj);
	} else {
	    handleError(entity, "Unknown command '" + cmd + "'");
	}
	
    }    

    
    protected void executeSetCommand(Connection cn, String cmd, Map<String, Object> parameters, Entity entity, EntityAccessor entityAccessor, Object obj) throws SQLException {
	if (cmd.length() <= SET_COMMAND.length() + 1) {
	    handleError(entity, "Invalid command: " + cmd);
	}
	cmd = cmd.substring(SET_COMMAND.length() + 1); 
	String[] tokens = StringUtils.split(cmd, ",");
	String[] nameValue = null;
	String attributeName = null;
	String attributeExpression = null;
	Map<Accessor, Object> valueMap = new LinkedHashMap<Accessor, Object>();
	for (String token : tokens) {

	    nameValue = StringUtils.split(token, "=");
	    if (nameValue.length != 2) {
		handleError(entity, "Unknown command '" + cmd + "', token='" + token + "'");
	    }
	    attributeName = nameValue[0].trim();
	    attributeExpression = nameValue[1].trim();
	    
	    Accessor accessor = null;
	    
	    if (attributeExpression.startsWith(":")) {
		
		// TOKEN IS PARAMETER WITH ':'  
		
		// If the parameter is only ':' then it is invalid
		if (attributeExpression.length() < 2 ) {
		    handleError(entity, "Invalid parameter with ':', command='" + cmd + "', token='" + token + "'");
		}

		String parameterName = attributeExpression.substring(1);
		Object parameterValue = getParameterValue(cmd, parameters, entity, entityAccessor, obj, parameterName);
		
		accessor = getAccessor(entity, obj, attributeName); // Set Attribute
		checkSetter(accessor, obj.getClass(), attributeName);		
		valueMap.put(accessor, parameterValue);
		
	    } else if ("null".equalsIgnoreCase(attributeExpression)) {
		
		// TOKEN IS 'null' expression

		accessor = getAccessor(entity, obj, attributeName); // Set Attribute
		checkSetter(accessor, obj.getClass(), attributeName);
		valueMap.put(accessor, new TypeValue());

	   } else if (attributeExpression.startsWith("findById(")) {
		
		// TOKEN IS 'findById()' expression

	        String expression = attributeExpression.substring(8 + 1, attributeExpression.length() - 1);
	        String prms[] = StringUtils.split(expression, "|");
		if (prms.length < 2 ) {
		    handleError(entity, "Invalid arguments of findById(), command='" + cmd + "', token='" + token + "'");
		}
		String prmName1 = prms[0].trim().substring(1); // without ':'
		String prmName2 = prms[1].trim().substring(1); // without ':'

		Object prmValue1 = getParameterValue(cmd, parameters, entity, entityAccessor, obj, prmName1);
		Object prmValue2 = getParameterValue(cmd, parameters, entity, entityAccessor, obj, prmName2);
		
		Collection<?> c = (Collection<?>) prmValue1;
		Object id = prmValue2;
		
		Object item = findById(c, "id", id);
	        	
		accessor = getAccessor(entity, obj, attributeName);
		checkSetter(accessor, obj.getClass(), attributeName);
		valueMap.put(accessor, item);		
	    } else {
		
		// TODO
		handleError(entity, "Not implemented expression, command='" + cmd + "', token='" + token + "'");
		
	    }
	    
	}
	Set<Entry<Accessor, Object>> valueSet = valueMap.entrySet();
	for (Entry<Accessor, Object> entry : valueSet ) {
	    Accessor accessor = entry.getKey();
	    Object value = entry.getValue();
	    if (value != null && value instanceof TypeValue) {
		value = ((TypeValue) value).getValue();
	    }
	    Method setter = accessor.getSetter();
	    try {
		setter.invoke(obj, value);
	    } catch (Exception ex) {
		handleError(entity, "Error execute method '"  + setter.getName() + "': " + ex.getMessage());
	    }
	}
    }    

    protected void executeCallCommand(Connection cn, String cmd, Map<String, Object> parameters, Entity entity, EntityAccessor entityAccessor, Object obj) throws SQLException {
	if (cmd.length() <= CALL_COMMAND.length() + 1) {
	    handleError(entity, "Invalid command: " + cmd);
	}
	cmd = cmd.substring(CALL_COMMAND.length() + 1);
	int index = cmd.indexOf("(");
	String procedureName = (index > -1) ? cmd.substring(0, index) : cmd;
	String entryPoint = null;
	String[] parts = StringUtils.split(procedureName, PROCEDURE_SEPARATOR);
	if (parts.length == 2) {
	    procedureName = parts[0].trim();
	    entryPoint = parts[1].trim();
	}
	Procedure procedure = getConfiguration().getProcedure(procedureName);
	if (procedure == null) {
	    handleError(entity, "Procedure '" + procedureName + "' not found");
	}
	if (entryPoint == null) {
	    entryPoint = procedure.getEntryPoint();
	}
	String prmsString = (index > -1) ? cmd.substring(index) : null;
	if (prmsString != null) {
	    if (prmsString.length() <= 2) {
		prmsString = null;
	    } else {
		prmsString = prmsString.substring(1, prmsString.length() - 1);
	    }
	}
	
	
	
	String[] tokens = prmsString == null ? new String[0] : StringUtils.split(prmsString, ",");
	String expression = null;
	
	Map<String, Object> procedurePrmsMap = new LinkedHashMap<String, Object>();
	
	// Add Entry Point to procedure parameters
	if (entryPoint != null) {
	    procedurePrmsMap.put(Event.ENTRY_POINT_PARAMETER, entryPoint);
	}
	EntityManager entityManager = getEntityManager(parameters); 
	procedurePrmsMap.put(Event.ENTITY_MANAGER, entityManager);
	
	int prmCounter = 0;
	
	for (String token : tokens) {

	    expression = token.trim();
	    
	    if (expression.startsWith(":")) {
		
		prmCounter++;
		
		// TOKEN IS PARAMETER WITH ':'  
		
		// If the parameter is only ':' then it is invalid
		if (expression.length() < 2 ) {
		    handleError(entity, "Invalid parameter with ':', command='" + cmd + "', token='" + token + "'");
		}

		String parameterName = expression.substring(1);
		Object parameterValue = getParameterValue(cmd, parameters, entity, entityAccessor, obj, parameterName);
		
		procedurePrmsMap.put("" + prmCounter, parameterValue);
		
	    } else if ("null".equalsIgnoreCase(expression)) {
		
		// TOKEN IS 'null' expression

		procedurePrmsMap.put("" + prmCounter, null);
		
	   } else if (expression.startsWith("'")) {
		
	        prmCounter++;
	       
		// TOKEN IS string expression
	        
	        String strValue = expression.substring(1, expression.length() - 1);
	        procedurePrmsMap.put("" + prmCounter, strValue);

	    } else {
		
		// TODO
		handleError(entity, "Not implemented expression, command='" + cmd + "', token='" + token + "'");
		
	    }
	    
	}
	
	procedure.execute(cn, procedurePrmsMap);
	
	
    }    

    
    /**
     * Find object from collection by id
     * @param c
     * @param idProperty
     * @param id
     * @return
     */
    protected Object findById(Collection<?> c, String idProperty, Object id) {
	if (id == null || c == null) {
	    return null;
	}
	boolean initAccessor = false;
	Method getter = null;
	Iterator<?> itr = c.iterator();
	Object item = null;
	try {
	    while (itr.hasNext()) {
		item = itr.next();
		if (!initAccessor) {
		    Class<?> itemClass = item.getClass();
		    Accessor accessor = getAccessor(itemClass, idProperty);
		    checkGetter(accessor, itemClass, idProperty);
		    getter = accessor.getGetter();
		    initAccessor = true;
		}
		Object itemId = getter.invoke(item);
		if (id.equals(itemId)) {
		    return item;
		}
	    }
	    return null;
	} catch (Exception e) {
	    throw new RuntimeException("Error execute findById(): " + e.getMessage());
	}
    }

    
}
