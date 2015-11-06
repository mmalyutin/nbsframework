package org.plazmaforge.framework.erm;

import org.plazmaforge.framework.erm.exception.EntityException;
import org.plazmaforge.framework.erm.exception.MappingException;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;

public class ErrorHandler {

    public static void handleEntityNotMapped(Class<?> entityClass) {
	throw new MappingException("Entity not mapped for class '" + (entityClass == null ? "null" : entityClass.getName()) + "'");
    }

    public static void handleEntityNotMapped(String entityIdentifier) {
	throw new MappingException("Entity '" + entityIdentifier + "' not mapped");
    }

    public static void handleJoinEntityNotMapped(Attribute attribute, String joinEntityIdentifier) {
	Entity entity = attribute.getEntity();
	throw new MappingException("Join entity '" + joinEntityIdentifier + "' not mapped for attribute='" + attribute.getName() + "' of entity='" + getEntityIdentifier(entity) + "'");
    }

    public static void handleParentEntityNotMapped(Entity entity, String parentEntityIdentifier) {
	throw new MappingException("Parent entity '" + parentEntityIdentifier + "' not mapped for entity ='" + getEntityIdentifier(entity) + "'");
    }

    public static void handleEntityError(Entity entity, String message) {
	throw new EntityException("" + message + (entity == null ? "" : (": " + getEntityString(entity))));
    }
    
    public static String getEntityString(Entity entity) {
	return entity == null ? "" : "entity='" + entity.getIdentifier() + "'";
    }
    
    public static String getEntityIdentifier(Entity entity) {
	return entity == null ? "null" : entity.getIdentifier();
    }
    
}
