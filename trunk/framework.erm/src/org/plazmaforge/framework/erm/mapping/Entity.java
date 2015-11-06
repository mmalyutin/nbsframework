package org.plazmaforge.framework.erm.mapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.validation.Validator;
import org.plazmaforge.framework.erm.Accessor;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.event.Event;
import org.plazmaforge.framework.erm.trigger.Trigger;
import org.plazmaforge.framework.util.ClassUtils;

/**
 * The Entity class
 * 
 *  - The Entity has only one KeyAttribute. But the KeyAttribute can be composite attribute. 
 *  
 * 
 * @author ohapon
 *
 */
public class Entity implements Serializable, INode {

    private static final long serialVersionUID = -7835471393997560786L;


    private Configuration configuration;
    
    private String name;
    
    private String className;
    
    private String headerClassName;
    
    private String tableName;
    
    private String validatorType;
    
    private String validatorClassName;
    
    private Validator validator;
    
    
    private boolean readonly;

    private boolean insert = true;

    private boolean update = true;


    private boolean lazyload;

    /**
     * Own key attribute
     */
    private Key key;
    
    /**
     * Own discriminator attribute
     */
    private Discriminator discriminator;
    
    /**
     * Own attributes
     */
    private List<Attribute> attributes;
    
    /**
     * True if entity is compiled
     */
    private boolean compiled;
    
    /**
     * True if entity in processing
     */
    private boolean processing;
    
    /**
     * Hierarchy of the entity
     */
    private Entity[] hierarchy;
    
    /**
     * Global (own and all hierarchy) attributes
     */
    private Attribute[] globalAttributes;
    
    /**
     * Global key (own or parent key)
     */
    private Key globalKey;
    
    ////
    
    /**
     * Attribute aliases
     */
    private List<AttributeAlias> attributeAliases;
    
    /**
     * Global attribute aliases (own and all hierarchy)
     */
    private AttributeAlias[] globalAttributeAliases;

    
    /**
     * Event list
     */
    private List<Event> events;
    
    /**
     * Global events (own and all hierarchy)
     */
    private Event[] globalEvents;

    /**
     * Trigger list
     */
    private List<Trigger> triggers;
    
    
    /**
     * Global triggers (own and all hierarchy)
     */
    private Trigger[] globalTriggers;
    
    /**
     * Config properties
     */
    private Map<String, String> configProperties;
    
    
    
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
	if (this.configuration != null) {
	    throw new RuntimeException("Configuration already initialize");
	}
	if (configuration == null) {
	    throw new RuntimeException("Configuration must be not null");
	}
        this.configuration = configuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityType getEntityType() {
        return EntityType.SingleEntity;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
	if (className == null) {
	    throw new IllegalArgumentException("Class must be not null");
	}
        this.className = className;
    }

    public String getHeaderClassName() {
        return headerClassName;
    }

    public void setHeaderClassName(String headerClassName) {
        this.headerClassName = headerClassName;
    }

    /**
     * Get entity identifier (class name)
     * @return
     */
    public String getIdentifier() {
	return className;
    }

    
    public String getExtendsClassName() {
        return null;
    }

    public boolean hasHierarchy() {
	return false; 
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
	if (tableName == null) {
	    throw new IllegalArgumentException("Table must be not null");
	}
        this.tableName = tableName;
    }

    /**
     * Return true if entity has own table.
     * Only SingleEntity and JoinedEntity have own table
     * @return
     */
    public boolean hasOwnTable() {
	return EntityType.SingleEntity.equals(getEntityType()) 
		|| EntityType.JoinedEntity.equals(getEntityType());
    }

    public String getValidatorType() {
        return validatorType;
    }

    public void setValidatorType(String validatorType) {
        this.validatorType = validatorType;
    }

    public String getValidatorClassName() {
        return validatorClassName;
    }

    public void setValidatorClassName(String validatorClassName) {
        this.validatorClassName = validatorClassName;
    }

    
    public Validator getValidator() {
        return validator;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

   

    public boolean isLazyload() {
        return lazyload;
    }

    public void setLazyload(boolean lazyload) {
        this.lazyload = lazyload;
    }

    ////

    private List<Attribute> doGetAttributes() {
	if (attributes == null) {
	    attributes = new ArrayList<Attribute>(); 
	}
	return attributes;
    }
    
    public boolean hasAttributes() {
	return !isEmptyAttributes();
    }

    public boolean isEmptyAttributes() {
	return (attributes == null || attributes.isEmpty());
    }
    
    public Attribute[] getAttributes() {
        return doGetAttributes().toArray(new Attribute[0]);
    }

    public void addAttribute(Attribute attribute) {
	if (attribute == null) {
	    throw new IllegalArgumentException("Attribute must be not null");
	}
	if (attribute.getName() == null) {
	    if (!attribute.isAnonym() && !attribute.isDiscriminatorType()) {
		throw new IllegalArgumentException("Attribute Name must be not null");
	    }
	}
	if (attribute instanceof Key) {
	    if (key == null) {
		key = (Key) attribute;    
	    } else {
		throw new IllegalArgumentException("Duplicate key");
	    }
	}
	if (attribute instanceof Discriminator) {
	    if (discriminator == null) {
		discriminator = (Discriminator) attribute;    
	    } else {
		throw new IllegalArgumentException("Duplicate discriminator");
	    }
	}	
	attribute.setParent(this);
	doGetAttributes().add(attribute);
    }

    public void removeAttribute(Attribute attribute) {
	doGetAttributes().add(attribute);
    }

    public Attribute getAttributeByName(String name) {
	if (name == null) {
	    throw new IllegalArgumentException("Attribute Name must be not null");
	}
	if (isEmptyAttributes()) {
	    return null;
	}
	List<Attribute> attributes = doGetAttributes();
	for (Attribute a: attributes) {
	    if (name.equals(a.getName())) {
		return a;
	    }
	}
	return null;
    }
    
    ////
    
    public Attribute getGlobalAttributeByName(String name) {
	if (name == null) {
	    return null; // throw new IllegalArgumentException("Attribute Name must be not null");
	}
	Attribute[] globalAttributes = getGlobalAttributes();
	if (globalAttributes == null || globalAttributes.length == 0) {
	    return null;
	}
	for (Attribute a: globalAttributes) {
	    if (name.equals(a.getName())) {
		return a;
	    }
	}
	return null;
    }
    
    ////
    

   
    public Key getKey() {
	return key;
    }

    public Key getGlobalKey() {
	return globalKey;
    }

    public Attribute[] getGlobalDetailKeys() {
	Key key = getGlobalKey();
	return key == null ? null : EntityHelper.getDetailAttributes(key);
    }

    public Attribute[] getGlobalAttributes() {
	return globalAttributes;
    }
    
    public Attribute[] getDetailKeys() {
	return key == null ? null : key.getDetailKeys();
    }

    public Column[] getDetailKeyColumns() {
	return key == null ? null : key.getDetailColumns();
    }

    public Column[] getGlobalDetailKeyColumns() {
	Key key = getGlobalKey();
	return key == null ? null : key.getDetailColumns();
    }

    ////
    
    /**
     * Return hierarchy of entity
     * @return
     */
    public Entity[] getHierarchy() {
	return hierarchy;
    }
    
    /**
     * Return root (top) of hierarchy
     * @return
     */
    public Entity getRootEntity() {
	return hierarchy[0];
    }

    /**
     * Return parent entity
     * @return
     */
    public Entity getParentEntity() {
	return hierarchy.length < 2 ? null : hierarchy[hierarchy.length - 2];
    }

    ////
    
    public String toString() {
	StringBuffer buf = new StringBuffer("Entity: ");
	boolean more = false;
	if (name != null) {
	    more = true;
	    buf.append("name=" + name);
	}
	if (more) {
	    buf.append(", ");
	}
	buf.append("class=" + className);
	return buf.toString(); 
    }

    public Discriminator getDiscriminator() {
        return discriminator;
    }

    public boolean isCompiled() {
        return compiled;
    }

    public void compile() {
	if (processing) {
	    return;
	}
	if (compiled) {
	    throw new RuntimeException("Entity is compiled");
	}
	try {
	    
	    
	    // Mark process
	    processing = true;
	    
	    Configuration configuration = getConfiguration();
	    if (configuration == null) {
		throw new RuntimeException("Configuration must be not null");
	    }
	
	    // Get hierarchy
	    hierarchy = EntityHelper.getEntityHierarchy(this);
	    
	    // Get global attributes (own and all parents)
	    globalAttributes = EntityHelper.getGlobalAttributes(hierarchy); 
	    
	    globalAttributeAliases = EntityHelper.getGlobalAttributeAliases(hierarchy);
	    
	    globalKey = EntityHelper.getGlobalKey(hierarchy);
	    
	    globalEvents = EntityHelper.getGlobalEvents(hierarchy);
	    
	    globalTriggers = EntityHelper.getGlobalTriggers(hierarchy);
	    
	    
	    if (!hasOwnTable()) {
		Entity parentEntity = getParentEntity();
		if (parentEntity != null) {
		    setTableName(parentEntity.getTableName());
		}
	    }
	    
	    
	    // VALIDATOR
	    String validatorCalssName = getValidatorClassName();
	    String validatorType = getValidatorType();
	    if (validatorCalssName != null) {
		// CREATE NEW INSTANCE BY CLASS
		validator = (Validator) getInstance(validatorCalssName);
	    } else if (validatorType != null) {
		// GET INSTANCE FROM CONFIGURATION
		validator = configuration.getValidator(validatorType);
	    }
		
	    Attribute[] attributes = EntityHelper.getAttributeTree(this);
	    String entityClassName = getClassName();
	    Class<?> entityClass = getClass(entityClassName);
	    Class<?> attributeEntityClass = null;
	    
	    for (Attribute attribute : attributes) {
		
		INode node = attribute.getParent();
		String attributeName = attribute.getName();
		
		if (this == node) {
		    attributeEntityClass = entityClass;
		} else if (node instanceof IComposite) {
		    IComposite composite = (IComposite) node;
		    if (composite.isPseudoComposite()) {
			attributeEntityClass = entityClass;
		    } else {
			attributeEntityClass = getClass(composite.getClassName());
		    }
		}
		
		if (attributeEntityClass != null) {
		    Accessor accessor = Accessor.getAccessor(attributeEntityClass, attributeName);
		    if (accessor != null) {
			accessor.setEntityClass(attributeEntityClass);
			attribute.setAccessor(accessor);
		    }  else {
			//System.out.println("Accessor not found for " + attributeEntityClass);
		    }
		}
		
		
		
		// COMPILE ATTRIBUTE
		attribute.compile(configuration);
	    }
	    
	    compiled = true;
	    
	} finally {
	    processing = false;
	}
	
    }
    
    private List<AttributeAlias> doGetAttributeAliases() {
	if (attributeAliases == null) {
	    attributeAliases = new ArrayList<AttributeAlias>();
	}
        return attributeAliases;
    }
    
    public boolean hasAttributeAliases() {
	return !isEmptyAttributeAliases();
    }

    public boolean isEmptyAttributeAliases() {
	return (attributeAliases == null || attributeAliases.isEmpty());
    }
    
    public AttributeAlias[] getAttributeAliases() {
	return doGetAttributeAliases().toArray(new AttributeAlias[0]);
    }

    public void addAttributeAlias(AttributeAlias attributeAlias) {
        doGetAttributeAliases().add(attributeAlias);
    }

    public AttributeAlias[] getGlobalAttributeAliases() {
        return globalAttributeAliases;
    }

    public boolean hasGlobalAttributeAliases() {
	return !isEmptyGlobalAttributeAliases();
    }

    public boolean isEmptyGlobalAttributeAliases() {
	return (globalAttributeAliases == null || globalAttributeAliases.length == 0);
    }

    
    private List<Event> doGetEvents() {
	if (events == null) {
	    events = new ArrayList<Event>();
	}
	return events;
    }
    
    public boolean hasEvents() {
	return !isEmptyEvents();
    }

    public boolean isEmptyEvents() {
	return (events == null || events.isEmpty());
    }
    
    public Event[] getEvents() {
        return doGetEvents().toArray(new Event[0]);
    }

    public void addEvent(Event event) {
	doGetEvents().add(event);
    }

    public Event[] getGlobalEvents() {
        return globalEvents;
    }

    private List<Trigger> doGetTriggers() {
	if (triggers == null) {
	    triggers = new ArrayList<Trigger>();
	}
	return triggers;
    }

    public boolean hasTriggers() {
	return !isEmptyTriggers();
    }

    public boolean isEmptyTriggers() {
	return (triggers == null || triggers.isEmpty());
    }
    
    public Trigger[] getTriggers() {
        return doGetTriggers().toArray(new Trigger[0]);
    }

    public void addTrigger(Trigger trigger) {
	doGetTriggers().add(trigger);
	trigger.setEntity(this);
    }
    
    public Trigger[] getGlobalTriggers() {
        return globalTriggers;
    }

    private Map<String, String> doGetConfigProperties() {
	if (configProperties == null) {
	    configProperties = new HashMap<String, String>();
	}
	return configProperties;
    }
    
    public String getConfigProperty(String name) {
	return doGetConfigProperties().get(name);
    }
    
    public String getConfigProperty(String name, boolean global) {
	String value = doGetConfigProperties().get(name);
	if (!global || value != null) {
	    return value;
	}
	Entity parent = getParentEntity();
	return parent == null ? null : parent.getConfigProperty(name, true);
    }
    

    public void addConfigProperty(String name, String value) {
	doGetConfigProperties().put(name, value);
    }

    public void removeConfigProperty(String name) {
	doGetConfigProperties().remove(name);
    }

    protected Class<?> getClass(String className) {
	return ClassUtils.getSafeClass(className);
    }
    
    protected Object getInstance(String className) {
	return ClassUtils.newSafeInstance(className);
    }

}
