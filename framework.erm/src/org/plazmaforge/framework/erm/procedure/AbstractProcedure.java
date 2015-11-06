package org.plazmaforge.framework.erm.procedure;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.event.Event;
import org.plazmaforge.framework.erm.script.Script;


public abstract class AbstractProcedure implements Procedure {

    private String name;
    
    private String entryPoint;
    
    private Script script;
    
    ////
    private EntityManager entityManager;
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntryPoint() {
        return entryPoint;
    }

    public void setEntryPoint(String entryPoint) {
        this.entryPoint = entryPoint;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public abstract void execute(Connection cn, Map<String, Object> parameters) throws SQLException;
    
    /**
     * Return values of parameters with check parameters
     * @param parameters
     * @param size of values array
     * @param check
     * @return values
     */
    protected Object[] getParameterValues(Map<String, Object> parameters, int size, boolean check) {
	if (size <= 0) {
	    throw new IllegalArgumentException("Size must be > 0");
	}
	Object[] values = new Object[size];
	if (parameters == null) {
	    return values;
	}
	for (int i = 0; i < size; i++ ) {
	    values[i] = parameters.get("" + (i + 1)); // We use index as key of parameter
	}
	if (check) {
	    checkRequiredParameters(values);
	}
	return values;
    }
    
    /**
     * Return values of parameters 
     * @param parameters
     * @param size of values array
     * @return values
     */
    protected Object[] getParameterValues(Map<String, Object> parameters, int size) {
	return getParameterValues(parameters, size, false);
    }
    
    protected void checkRequiredParameter(String parameter, Object value) {
	if (value == null) {
	    throw new RuntimeException("Parameter '" + parameter + "' is empty");
	}
    }
    
    protected void checkRequiredParameters(Object[] values) {
	if (values == null) {
	    return;
	}
	int size = values.length;
	for (int i = 0; i < size; i++ ) {
	    checkRequiredParameter("" + (i + 1), values[i]);
	}
    }
    
    protected String getEntryPoint(Map<String, Object> parameters) {
	return (String) parameters.get(Event.ENTRY_POINT_PARAMETER);
    }
    
    protected EntityManager getEntityManager(Map<String, Object> parameters) {
	return (EntityManager) parameters.get(Event.ENTITY_MANAGER);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    
}
