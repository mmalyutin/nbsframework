package org.plazmaforge.framework.erm.mapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.EntityAccessor;
import org.plazmaforge.framework.erm.EntityCreator;
import org.plazmaforge.framework.erm.ConfigurationRegister;
import org.plazmaforge.framework.erm.event.Event;
import org.plazmaforge.framework.erm.query.EntityContext;
import org.plazmaforge.framework.erm.query.TypeValue;
import org.plazmaforge.framework.erm.trigger.Trigger;
import org.plazmaforge.framework.util.ClassUtils;
import org.plazmaforge.framework.util.StringUtils;

public class EntityHelper {

    private EntityHelper() {
	
    }
    
    /**
     * Return list of hierarchy of entity.
     * Top entity (index = 0) is root entity.
     * @param entity
     * @return
     */
    public static Entity[] getEntityHierarchy(Entity entity) {
	Entity[] entities = entity.getConfiguration().getEntityHierarchy(entity);
	checkHierarchy(entities);
	return entities;
    }

    /**
     * Return root (top) entity from hierarchy
     * @param entity
     * @return
     */
    public static Entity getRootEntity(Entity entity) {
	Entity[] entities = getEntityHierarchy(entity);
	return entities[0]; // First element is root
    }
    
    /**
     * Return parent (next to up) entity from hierarchy
     * @param entity
     * @return
     */
    public static Entity getParentEntity(Entity entity) {
	return entity.getConfiguration().getParentEntity(entity);
    }
    

    /**
     * Return global key of entity.
     * @param entity
     * @return
     */
    public static Key getGlobalKey(Entity entity) {
	if (entity == null) {
	    return null;
	}
	Key key = entity.getKey();
	if (key != null) {
	    return key;
	}
	Entity[] entities = getEntityHierarchy(entity);
	return getGlobalKey(entities);
    }
    
    /**
     * Return global key of hierarchy of entity.
     * Find first not null key in hierarchy of entity.
     * @param entities
     * @return
     */
    public static Key getGlobalKey(Entity[] entities) {
	if (entities == null) {
	    return null;
	}
	Entity e = null;
	Key key = null;
	for (int i = entities.length - 1; i >= 0; i--) {
	    e = entities[i];
	    key = e.getKey();
	    if (key != null) {
		return key;
	    }
	}
	return null;
    }

    /**
     * Return global attributes of entity.
     * @param entity
     * @return
     */
    public static Attribute[] getGlobalAttributes(Entity entity) {
	Entity[] entities = getEntityHierarchy(entity);
	return getGlobalAttributes(entities);
    }
    
    /**
     * Return global attributes of hierarchy of entity.
     * @param entities
     * @return
     */
    public static Attribute[] getGlobalAttributes(Entity[] entities) {
	if (entities == null) {
	    return new Attribute[0];
	}
	List<Attribute> list = new ArrayList<Attribute>();
	for (Entity e: entities) {
	    for (Attribute a: e.getAttributes()) {
		list.add(a);
	    }
	}
	return list.toArray(new Attribute[0]);
    }

    public static Attribute getAttributeByName(Attribute[] attributes, String name) {
	if (name == null) {
	    return null;
	}
	if (attributes == null || attributes.length == 0) {
	    return null;
	}
	for (Attribute a: attributes) {
	    if (name.equals(a.getName())) {
		return a;
	    }
	}
	return null;
    }
    
    public static Attribute getGlobalAttributeByName(Entity[] entities, String name) {
	if (entities == null || entities.length == 0 || name == null) {
	    return null;
	}
	Attribute[] attributes = getGlobalAttributes(entities);
	return getAttributeByName(attributes, name);
    }
    
    /**
     * Return details of attribute
     * @param attribute
     * @return
     */
    public static Attribute[] getDetailAttributes(Attribute attribute) {
	if (attribute == null) {
	    return new Attribute[0];
	}
	List<Attribute> list = new ArrayList<Attribute>();
	if (attribute instanceof IComposite ){
	    populateCompositeAttributes((IComposite) attribute, list, false);
	} else {
	    list.add(attribute);
	}
	return list.toArray(new Attribute[0]);
    }

    /**
     * Return simple attribute tree of the entity.
     * The tree have only own attributes of the entity without depth.
     * 
     * @param entity
     * @return
     */
    public static Attribute[] getAttributeTree(Entity entity) {
	Attribute[] attributes = entity.getAttributes();
	List<Attribute> list = new ArrayList<Attribute>();
	for (Attribute a : attributes) {
	    if (a instanceof IComposite ){
		populateCompositeAttributes((IComposite) a, list, true);
	    } else {
		list.add(a);
	    }
	}
	return list.toArray(new Attribute[0]);
    }
    
    
    private static void populateCompositeAttributes(IComposite attribute, List<Attribute> list, boolean useNode) {
	if (useNode) {
	    list.add((Attribute) attribute);
	}
	Attribute[] attributes = attribute.getAttributes();
	if (attributes == null || attributes.length == 0) {
	    return;
	}
	for (Attribute a : attributes) {
	    //list.add(a);
	    if (a instanceof IComposite ){
		populateCompositeAttributes((IComposite) a, list, useNode);
	    } else {
		list.add(a);
	    }
	}
    }

    public static AttributeAlias[] getGlobalAttributeAliases(Entity[] entities) {
	if (entities == null) {
	    return new AttributeAlias[0];
	}
	Map<String, AttributeAlias> map = new LinkedHashMap<String, AttributeAlias>();
	
	// Create map of hierarchy
	// If child has own alias then we replace parent alias  
	for (Entity e: entities) {
	    for (AttributeAlias a: e.getAttributeAliases()) {
		map.put(a.getName(), a);
	    }
	}
	return map.values().toArray(new AttributeAlias[0]);
    }

    
    /**
     * Replace property name by alias of entity
     * @param entity
     * @param propertyName
     * @param type
     * @return
     */
    public static String replaceByAlias(Entity entity, String propertyName, int type) {
	if (entity == null || propertyName == null || entity.isEmptyGlobalAttributeAliases()) {
	    return null;
	}
	AttributeAlias[] aliases = entity.getGlobalAttributeAliases();
	for (AttributeAlias alias : aliases) {
	    if (propertyName.equals(alias.getName())) {
		return alias.getAttribute(type);
	    }
	}
	return propertyName;
    }
    
    
    
    
    /**
     * Return depth tree attributes of the entity.
     *  
     * @param entity
     * @return
     */
//    public static AttributeRef[] getDepthAttributeTree(Entity entity) {
//	Entity[] entities = getEntityHierarchy(entity);
//	return getDepthAttributeTree(entities);
//    }
    
    /**
     * Return depth tree attributes of the entity hierarchy.
     * @param entities
     * @return
     */
//    public static AttributeRef[] getDepthAttributeTree(Entity[] entities) {
//	Attribute[] globalAttributes = getGlobalAttributes(entities);
//	if (globalAttributes == null) {
//	    return new AttributeRef[0];
//	}
//	List<AttributeRef> list = new ArrayList<AttributeRef>();
//	populateDepthAttributes(globalAttributes, list, 0, null);
//	return list.toArray(new AttributeRef[0]);
//    }
    
//    private static void populateDepthAttributes(Attribute[] attributes, List<AttributeRef> list, int level, String path) {
//	if (attributes == null) {
//	    return;
//	}
//	
//	for (Attribute attribute: attributes) {
//	    if (attribute.isBasicType()) {
//		AttributeRef attributeRef = new AttributeRef(attribute);
//		attributeRef.setLevel(level);
//		attributeRef.setPath(getNormalizePath(path) + attribute.getName());
//		list.add(attributeRef);
//	    } else if (attribute.isCompositeType()) {
//		if (!((IComposite)attribute).isPseudoComposite()) {
//		    level++;
//		    path = getNormalizePath(path) + attribute.getName();
//		}
//		populateDepthCompositeAttributes((IComposite) attribute, list, level, path);
//	    } else if (attribute.isReferenceType()) {
//		level++;
//		path = getNormalizePath(path) + attribute.getName();
//		populateDepthReferenceAttributes((IReference) attribute, list, level, path);
//	    }
//	}
//    }
//    
//    private static void populateDepthCompositeAttributes(IComposite attribute, List<AttributeRef> list, int level, String path) {
//	Attribute[] attributes = attribute.getAttributes();
//	populateDepthAttributes(attributes, list, level, path);
//    }
//
//    private static void populateDepthReferenceAttributes(IReference attribute, List<AttributeRef> list, int level, String path) {
//	Entity joinEntity = attribute.getJoinEntity();
//	if (joinEntity == null) {
//	    throw new RuntimeException("JoinEntity not found");
//	}
//	Attribute[] attributes = getGlobalAttributes(joinEntity);
//	populateDepthAttributes(attributes, list, level, path);
//    }

    
    ////
    public static String getNormalizePath(String path) {
	return path == null ? "" : path + ".";
    }
    
//    public static AttributeRef findAttributeByPath(AttributeRef[] attributes, String path) {
//	if (attributes == null || path == null) {
//	    return null;
//	}
//	for (AttributeRef attribute: attributes) {
//	    if (path.equals(attribute.getPath())) {
//		return attribute;
//	    }
//	}
//	return null;
//    }

    public static Attribute[] getAttributePath(Entity entity, String path) {
	if (entity == null) {
	    throw new IllegalArgumentException("Entity must be not null");
	}
	if (path == null) {
	    throw new IllegalArgumentException("Path must be not null");
	}
	Attribute[] attributes = entity.getGlobalAttributes();
	String[] names = StringUtils.split(path, ".");
	List<Attribute> list = new ArrayList<Attribute>();
	int count = names.length;
	for (int i = 0; i < count; i++) {
	    String name = names[i];
	    Attribute attribute = findAttributeByName(attributes, name);
	    if (attribute == null) {
		return null;
	    }
	    list.add(attribute);
	    boolean last = i == count - 1;
	    if (!last) {
		if (attribute.isCompositeType()) {
		    attributes = ((IComposite) attribute).getAttributes();
		} else if (attribute.isReferenceType()) {
		    Entity joinEntity = ((IReference) attribute).getJoinEntity();
		    if (joinEntity == null) {
			throw new RuntimeException("JoinEntity of attribute '" + attribute.getName() + "' not found");
		    }
		    attributes = joinEntity.getGlobalAttributes();
		} else if (attribute.isEntryType()) {
		    Entity joinEntity = ((IEntry) attribute).getJoinEntity();
		    if (joinEntity == null) {
			throw new RuntimeException("JoinEntity of attribute '" + attribute.getName() + "' not found");
		    }
		    attributes = joinEntity.getGlobalAttributes();
		} else {    
		    // Ops...basic attribute is not container of attribute, but
		    // current path has .aaa.bbb.ccc
		    return null;
		}
	    }
	}
	return list.toArray(new Attribute[0]);
    }
    
    public static Attribute findAttributeByName(Attribute[] attributes, String name) {
	if (attributes == null || name == null) {
	    return null;
	}
	for (Attribute attribute: attributes) {
	    if (equalsName(attribute, name)) {
		return attribute;
	    }
	}
	return null;
    }

    public static boolean equalsName(Attribute attribute, String name) {
	if (attribute == null || name == null) {
	    return false;
	}
	return name.equals(attribute.getName());
    }
    
    /**
     * Return all columns by attributes
     * @param attributes
     * @return
     */
    public static Column[] getColumns(Attribute[] attributes) {
	if (attributes.length == 0) {
	    return new Column[0];
	}
	List<Column> list = new ArrayList<Column>();
	for (Attribute attribute: attributes){
	    if (attribute.isMultiColumn()) {
		Column[] columns = attribute.getColumns();
		for (Column column: columns) {
		    list.add(column);
		}
	    } else {
		list.add(attribute.getColumn());
	    }
	}
	return list.toArray(new Column[0]);
    }
    
    ////
    
    private static void checkHierarchy(Entity[] entities) {
	if (entities == null || entities.length < 1 ) {
	    throw new RuntimeException("Invalid get entity hierarchy");
	}	
    }
    
    
 
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static Object[] convertIdToValues(Entity entity, Key key, Serializable id) {
	if (id == null) {
	    return null;
	}
	Object[] values = null;
	if (id.getClass().isArray()) {
	    values = (Object[]) id;
	} else {
	    // TODO: Must use Key structure to parse ID
	    values = new Object[1];
	    values[0] = id;
	}
	return values;
    }

    public static TypeValue[] convertIdToTypeValues(Entity entity, Key key, Serializable id) {
	// TODO: Must use Key structure to parse ID
	if (id == null) {
	    return null;
	}
	Object[] values = null;
	TypeValue[] typeValues = null;
	if (id.getClass().isArray()) {
	    values = (Object[]) id;
	    int size = values.length;
	    typeValues = new TypeValue[size];
	    for (int i = 0; i < size; i++ ) {
		typeValues[i] = new TypeValue(key.getType(), values[i]); // TODO: Only for simple key
	    }
	} else {
	    values = convertIdToValues(entity, key, id);
	    typeValues = new TypeValue[1];
	    typeValues[0] = new TypeValue(key.getType(), values[0]); // TODO: Only for simple key
	}
	
	return typeValues;
    }

    public static Serializable convertValuesToId(Entity entity, Key key, Object[] values) {
	if (values == null || values.length == 0) {
	    return null;
	}
	//TODO: Must use Key structure to create ID
	Serializable id = (Serializable) values[0];
	return id;
    }

    public static Object[] getKeyValues(Entity entity, Object obj) {
	Attribute[] keys = entity.getGlobalDetailKeys();
	return getKeyValues(entity, keys, obj);
    }

    public static Object[] getKeyValues(Entity entity, Attribute[] keys, Object obj) {
	return getKeyValues(getEntityAccessor(entity), keys, obj);
    }

    public static Object[] getKeyValues(EntityAccessor accessor, Attribute[] keys, Object obj) {
	int count = keys.length;
	Object[] values = new Object[count];
	for (int i = 0; i < count; i++) {
	    values[i] = accessor.getValue(obj, keys[i]); // getPathValue ? 
	}
	return values;
    }

    public static TypeValue[] getKeyTypeValues(EntityAccessor accessor, Attribute[] keys, Object obj) {
	int count = keys.length;
	TypeValue[] values = new TypeValue[count];
	Attribute key = null;
	for (int i = 0; i < count; i++) {
	    key = keys[i];
	    values[i] = new TypeValue();
	    values[i].setType(key.getType());
	    values[i].setValue(accessor.getValue(obj, key)); //getPathValue ?
	}
	return values;
    }

    public static EntityAccessor getEntityAccessor(Entity entity) {
	return ConfigurationRegister.getEntityAccessor(entity);
    }

    public static EntityCreator getEntityCreator(Entity entity) {
	return ConfigurationRegister.getEntityCreator(entity);
    }

    public static Attribute[] getFilterAttributes(Attribute[] attributes, EntityContext entityContext) {
	if (entityContext == null){
	    return attributes;
	}
	String[] include = entityContext.getIncludeAttributes();
	String[] exclude = entityContext.getExcludeAttributes();
	return EntityHelper.getAttributes(attributes, include, exclude);
    }
    
    public static Attribute[] getAttributes(Attribute[] input, String[] include, String[] exclude) {
	if (input == null) {
	    return null;
	}
	boolean useInclude = include != null && include.length > 0;
	boolean useExclude = exclude != null && exclude.length > 0;
	if (!useInclude && !useExclude) {
	    return input;
	}
	List<Attribute> output = new ArrayList<Attribute>();
	boolean findInclude = false;
	boolean findExclude = false;
	for (Attribute attribute : input) {
	    findInclude = false;
	    findExclude = false;
	    String name = attribute.getName();
	    
	    if (useInclude) {
		for (String s : include) {
		    if (name != null && name.equals(s)) { // TODO: Bad code
			findInclude = true;
			break;
		    }
		}
	    } else {
		findInclude = true;
	    }
	    
	    if (useExclude) {
		for (String s : exclude) {
		    if (name != null && name.equals(s)) {// TODO: Bad code
			findExclude = true;
			break;
		    }
		}
	    } else {
		findExclude = false;
	    }
	    
	    if (findInclude && !findExclude) {
		output.add(attribute);
	    }
	}
	return (Attribute[]) output.toArray(new Attribute[0]);
    }

    /**
     * Return true if object is  unsaved.
     * Check by id value of object
     * @param entity
     * @param obj
     * @return
     */
    public static boolean isUnsaved(Entity entity, Object obj) {
	if (entity == null) {
	    throw new IllegalArgumentException("Entity must be not null");
	}
	if (obj == null) {
	    throw new IllegalArgumentException("Object must be not null");
	}
	Key key = entity.getGlobalKey();
	if (key == null) {
	    throw new IllegalArgumentException("Key attribute not found in entity " + entity);
	}
	EntityAccessor accessor = EntityHelper.getEntityAccessor(entity);
	Object id = accessor.getValue(obj, key);
	// TODO: Must check unsaved value
	return isUnsaved(key, id);
    }
    
    /**
     * Return true if value of key is unsaved 
     * @param key
     * @param id
     * @return
     */
    public static boolean isUnsaved(Attribute key, Object id) {
	if (key == null) {
	    throw new IllegalArgumentException("Key must be not null");
	}
	// TODO: Must check unsaved value
	return id == null;
    }
    
    
    
    /**
     * Return global events of hierarchy of entity.
     * @param entities
     * @return
     */
    public static Event[] getGlobalEvents(Entity[] entities) {
	if (entities == null) {
	    return new Event[0];
	}
	List<Event> events = new ArrayList<Event>();
	for (Entity entity: entities) {
	    if (entity.isEmptyEvents() ) {
		continue;
	    }
	    for (Event e: entity.getEvents()) {
		events.add(e);
	    }
	}
	return events.toArray(new Event[0]);
    }

    /**
     * Return global triggers of hierarchy of entity.
     * @param entities
     * @return
     */
    public static Trigger[] getGlobalTriggers(Entity[] entities) {
	if (entities == null) {
	    return new Trigger[0];
	}
	Map<String, Trigger> triggers = new LinkedHashMap<String, Trigger>();
	int index = 0;
	for (Entity entity: entities) {
	    if (entity.isEmptyTriggers() ) {
		continue;
	    }
	    for (Trigger t: entity.getTriggers()) {
		String name = t.getName();
		if (name == null) {
		    index++;
		    name = "" + index; 
		}
		// Override trigger by name
		triggers.put(name, t);
	    }
	}
	return triggers.values().toArray(new Trigger[0]);
    }
    
    public static Type getTypeByClass(Configuration configuration, Class<?> klass) {
	if (klass == null) {
	    return null;
	}
	String baseClassName = ClassUtils.getBaseClassName(klass);
	return configuration.getType(baseClassName);
	//return EntityRegister.getType(baseClassName);
    }

}
