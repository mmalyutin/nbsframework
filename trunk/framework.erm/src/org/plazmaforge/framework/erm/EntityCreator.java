package org.plazmaforge.framework.erm;

import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.util.ClassUtils;

public class EntityCreator {

    public <T> T newObject(Entity entity) {
	try {
	    String className = entity.getClassName();
	    return (T) ClassUtils.newInstance(className);
	} catch (ClassNotFoundException ex) {
	    throw new RuntimeException("Class not found for entity " + entity);
	} catch (InstantiationException ex) {
	    throw new RuntimeException("Error create object: " + ex.getMessage());
	} catch (IllegalAccessException ex) {
	    throw new RuntimeException("Error create object: "+ ex.getMessage());
	}
    }
    
    public <T> T newObject(String className) {
	try {
	    return (T) ClassUtils.newInstance(className);
	} catch (ClassNotFoundException ex) {
	    throw new RuntimeException("Error create object: " + ex.getMessage());
	} catch (InstantiationException ex) {
	    throw new RuntimeException("Error create object: " + ex.getMessage());
	} catch (IllegalAccessException ex) {
	    throw new RuntimeException("Error create object: " + ex.getMessage());
	}
    }


    public <T> T nullObject(Entity entity) {
	return null;
    }
}
