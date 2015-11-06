package org.plazmaforge.framework.erm.trigger;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.sql.type.ObjectType;
import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.erm.Accessor;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.EntityAccessor;
import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.ErrorHandler;
import org.plazmaforge.framework.erm.event.Event;
import org.plazmaforge.framework.erm.event.EventType;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.query.TypeValue;
import org.plazmaforge.framework.erm.script.Script;
import org.plazmaforge.framework.util.StringUtils;

/**
 * Abstract trigger
 * 
 * @author ohapon
 *
 */
public abstract class AbstractTrigger implements Trigger {

    private Configuration configuration;
    
    private Entity entity;
    
    private EventType type;
    
    private String name;
    
    private Script script;

    public Configuration getConfiguration() {
	if (configuration != null) {
	    return configuration;
	}
	if (entity == null) {
	    return null;
	}
	configuration = entity.getConfiguration();
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    protected String getScriptText() {
        return script == null ? null : script.getText();
    }

    protected String[] getScriptLines() {
        return script == null ? null : script.getLines();
    }


    /**
     * Return commands from script lines by <code>separator</code>
     * @param separator
     * @return
     */
    protected String[] getCommands(String separator) {
	String[] scriptLines = getScriptLines();
	if (scriptLines == null) {
	    return null;
	}
	if (separator == null) {
	    return scriptLines;
	}
	List<String> output = new ArrayList<String>();
	for (String line: scriptLines) {
	    String[] cmds = StringUtils.split(line, separator);
	    for (String cmd: cmds) {
		//output.add(cmd.trim());
		output.add(cmd);
	    }
	}
	return output.toArray(new String[0]);
    }
    
    public abstract void execute(Connection cn, Map<String, Object> parameters) throws SQLException;
    
    protected boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }

    protected Entity getEntity(Map<String, Object> parameters) {
	return (Entity) parameters.get(Event.ENTITY_PARAMETER);
    }

    protected EntityManager getEntityManager(Map<String, Object> parameters) {
	return (EntityManager) parameters.get(Event.ENTITY_MANAGER);
    }

    protected Object getData(Map<String, Object> parameters) {
	return parameters.get(Event.DATA_PARAMETER);
    }
    
    
    protected Object getParameterValue(String cmd, Map<String, Object> parameters, Entity entity, EntityAccessor entityAccessor, Object obj, String parameterName) {
	TypeValue typeValue = getParameterTypeValue(cmd, parameters, entity, entityAccessor, obj, parameterName, false);
	return typeValue == null ? null : typeValue.getValue();
    }
    
    protected TypeValue getParameterTypeValue(String cmd, Map<String, Object> parameters, Entity entity, EntityAccessor entityAccessor, Object obj, String parameterName, boolean forceType) {
	
	TypeValue typeValue = new TypeValue();
	
	// Priority 1: parameters
	Object parameterValue = parameters.get(parameterName);
	if (parameterValue != null) {
	    typeValue.setValue(parameterValue);
	    if (forceType) {
		typeValue.setType(getTypeByClass(parameterValue.getClass()));
	    }
	    return typeValue;
	}
	
	// Priority 2: object
	if (obj == null ) {
	    return null;
	}
	
	// Priority 2.1: find in entity attributes 
	String token = null;
	Attribute attribute = entity.getAttributeByName(parameterName);
	if (attribute != null) {
	    parameterValue = entityAccessor.getValue(obj, attribute);
	    typeValue.setValue(parameterValue);
	    if (forceType) {
		Type type = attribute.getType();
		if (type == null) {
		    Class<?> parameterClass = parameterValue == null ? null : parameterValue.getClass();
		    type = getTypeByClass(parameterClass);
		}
		typeValue.setType(type);
	    }
	    return typeValue;
	}
	
	// Priority 2.2: find in class fields
	Accessor accessor = getAccessor(obj.getClass(), parameterName);
	if (accessor == null || accessor.getGetter() == null) {
	    throw new RuntimeException("Parameter '" + parameterName + "' not found, command='" + cmd + "', token='" + token + "'");
	}
	Method getter = accessor.getGetter();
	try {
	    parameterValue = getter.invoke(obj);
	    typeValue.setValue(parameterValue);
	    if (forceType) {
		Class<?> parameterClass = accessor.getAttributeClass();
		if (parameterClass == null && parameterValue != null) {
		    parameterClass = parameterValue.getClass();
		}
		Type type = getTypeByClass(parameterClass);
		typeValue.setType(type);
	    }
	    return typeValue;
	} catch (Exception ex) {
	    throw new RuntimeException("Call method error: " + ex.getMessage() + ", method = '" + getter.getName() + "',  parameterName = '" + parameterName + "', command='" + cmd + "', token='" + token + "'");
	}
    }
    
    protected Type getTypeByClass(Class<?> klass) {
	Type type = EntityHelper.getTypeByClass(getConfiguration(), klass);
	return type == null ? new ObjectType(): type;
    }
    
    protected Accessor getAccessor(Entity entity, Object obj, String property) {
	Attribute attribute = entity.getAttributeByName(property); // Set Attribute
	if (attribute != null) {
	    return attribute.getAccessor();  
	}
	return getAccessor(obj.getClass(), property);
    }
    
    protected Accessor getAccessor(Class<?> klass, String property) {
	return Accessor.getAccessor(klass, property);
    }
 
    protected void checkAccessor(Accessor accessor, Class<?> klass, String property) {
	if (accessor == null) {
	    throw new RuntimeException("Acessor is null, class='" + klass + "', property='" + property + "'");
	}
    }
    
    protected void checkGetter(Accessor accessor, Class<?> klass, String property) {
	checkAccessor(accessor, klass, property);
	Method getter = accessor.getGetter();
	if (getter == null) {
	    throw new RuntimeException("Getter is null, class='" + klass + "', property='" + property + "'");
	}
    }
    
    protected void checkSetter(Accessor accessor, Class<?> klass, String property) {
	checkAccessor(accessor, klass, property);
	Method getter = accessor.getSetter();
	if (getter == null) {
	    throw new RuntimeException("Setter is null, class='" + klass + "', property='" + property + "'");
	}
    }
    
    protected void handleError(Entity entity, String message) {
	ErrorHandler.handleEntityError(entity, message);
    }
    
    
}
