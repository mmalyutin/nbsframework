package org.plazmaforge.framework.erm.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.core.validation.Validator;
import org.plazmaforge.framework.erm.CascadeType;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.event.EventType;
import org.plazmaforge.framework.erm.event.EventWrapper;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.AttributeAlias;
import org.plazmaforge.framework.erm.mapping.Collection;
import org.plazmaforge.framework.erm.mapping.Composite;
import org.plazmaforge.framework.erm.mapping.CompositeKey;
import org.plazmaforge.framework.erm.mapping.Discriminator;
import org.plazmaforge.framework.erm.mapping.EmbeddedEntity;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.Entry;
import org.plazmaforge.framework.erm.mapping.JoinedEntity;
import org.plazmaforge.framework.erm.mapping.Key;
import org.plazmaforge.framework.erm.mapping.Mapping;
import org.plazmaforge.framework.erm.mapping.Reference;
import org.plazmaforge.framework.erm.mapping.SubclassEntity;
import org.plazmaforge.framework.erm.procedure.JavaProcedure;
import org.plazmaforge.framework.erm.script.Script;
import org.plazmaforge.framework.erm.trigger.AbstractTrigger;
import org.plazmaforge.framework.erm.trigger.TriggerUtils;
import org.plazmaforge.framework.util.ClassUtils;
import org.plazmaforge.framework.util.StringUtils;

/**
 * Reader data from XML
 * 
 * @author ohapon
 *
 */
public class XMLReader implements XMLInfo {

    private static final Logger logger = Logger.getLogger(XMLReader.class.getName()); 
    
    private boolean fileLog;
    private boolean entityLog;
    

    public boolean isFileLog() {
        return fileLog;
    }

    public void setFileLog(boolean fileLog) {
        this.fileLog = fileLog;
    }

    public boolean isEntityLog() {
        return entityLog;
    }

    public void setEntityLog(boolean entityLog) {
        this.entityLog = entityLog;
    }

    protected Document readDocument(String fileName) throws IOException {
	return readDocument(new File(fileName));
    }
    
    protected Document readDocument(File file) throws IOException {
	return readDocument(new FileInputStream(file));
    }
    
    protected Document readDocument(InputStream is) throws IOException {
	SAXBuilder builder = new SAXBuilder();
	//builder.setValidation(false);
	try {
	    return builder.build(is);
	} catch (JDOMException ex) {
	    throw new IOException(ex);
	}
    }
    
    ////

    public Mapping readMapping(String fileName) throws IOException {
	Document doc = readDocument(fileName);
	return readMapping(fileName, doc);
    }

    public Mapping readMapping(File file) throws IOException {
	Document doc = readDocument(file);
	return readMapping(file.getName(), doc);
    }

    public Mapping readMapping(String fileName, InputStream is) throws IOException {
	Document doc = readDocument(is);
	return readMapping(fileName, doc);
    }
    
    public Mapping readMapping(InputStream is) throws IOException {
	return readMapping(null, is);
    }
    
    /**
     * General read method
     * @param fileName
     * @param doc
     * @return
     */
    protected Mapping readMapping(String fileName, Document doc) {
	try {
	    Mapping mapping = doReadMapping(doc);
	    if (fileLog) {
		logger.log("Loaded ERM mapping" + (fileName != null ? (" from file '" + fileName + "'") : ""));
	    }
	    return mapping;
	} catch (Exception ex) {
	    handleError("Error load ERM mapping" + (fileName != null ? (" from file '" + fileName + "'") : ""), ex);
	    return null;
	}
    }
    
    protected Mapping doReadMapping(Document doc) {
	Element root = doc.getRootElement();
	
	String name = root.getName();
	Mapping mapping = new Mapping();
	if (XML_ENTITY_MAPPING.equals(name)) {
	    doReadEntities(mapping, root);
	    return mapping;
	}

	doReadEntity(mapping, root);
	return mapping;
    }
 
    protected void doReadEntities(Mapping mapping, Element root) {
	List children = root.getChildren();
	int count = children.size();
	if (count == 0) {
	    return;
	}

	// Read entities
	for (int i = 0; i < count; i++) {
	    Element element = (Element) children.get(i);
	    doReadEntity(mapping, element);
	}
	
    }
    
    protected void doReadEntity(Mapping mapping, Element element) {
	    String elementName = element.getName();
	    Entity entity = readEntityByType(element, elementName);
	    if (entity != null) {
		mapping.addEntity(entity);
	    }
    }
    
    
    protected Entity readEntityByType(Element element, String elementName) {
	    if (XML_ENTITY.equals(elementName)) {
		return readSingleEntity(element);
	    } else if (XML_JOINED_ENTITY.equals(elementName)) {
		return readJoinedEntity(element);
	    } else if (XML_SUBCLASS_ENTITY.equals(elementName)) {
		return readSubclassEntity(element);
	    } else if (XML_EMBEDDED_ENTITY.equals(elementName)) {
		return readEmbeddedEntity(element);
	    } else  {
		handleError("Document is not valid");
		return null;
	    }
	
    }
    
    
    protected String getValue(Element element, String name) {
	if(element == null || name == null){
	    return null;
	}
	String value = element.getAttributeValue(name);
	if (value == null) {
	    return null;
	}
	value = value.trim();
	return value.isEmpty() ? null : value;
    }

    protected Boolean getBooleanValue(Element element, String name) {
	String value = getValue(element, name);
	if (value == null) {
	    return null;
	}
	return "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || "1".equalsIgnoreCase(value); 
    }

    protected int getIntValue(Element element, String name) {
	String value = getValue(element, name);
	if (value == null) {
	    return 0;
	}
	try {
	    return Integer.valueOf(value);
	} catch (NumberFormatException ex) {
	    return 0;
	}
    }

    /**
     * Read single entity (one table)
     * @param element
     * @return
     */
    protected Entity readSingleEntity(Element element) {
	
	Entity entity = new Entity();
	readBaseEntity(entity, element);
	entity.setClassName(getValue(element, XML_CLASS_ATTR));
	entity.setHeaderClassName(getValue(element, XML_HEADER_CLASS_ATTR));
	entity.setTableName(getValue(element, XML_TABLE_ATTR));
	
	return entity;
    }

    /**
     * Read joined entity (more tables)
     * @param element
     * @return
     */
    protected Entity readJoinedEntity(Element element) {
	
	JoinedEntity entity = new JoinedEntity();
	readBaseEntity(entity, element);
	entity.setClassName(getValue(element, XML_CLASS_ATTR));
	entity.setHeaderClassName(getValue(element, XML_HEADER_CLASS_ATTR));
	entity.setTableName(getValue(element, XML_TABLE_ATTR));
	entity.setExtendsClassName(getValue(element, XML_EXTENDS_CLASS_ATTR));
	
	return entity;
    }

    /**
     * Read subclass entity (one table with discriminator) 
     * @param element
     * @return
     */
    protected Entity readSubclassEntity(Element element) {
	
	SubclassEntity entity = new SubclassEntity();
	readBaseEntity(entity, element);
	entity.setClassName(getValue(element, XML_CLASS_ATTR));
	entity.setHeaderClassName(getValue(element, XML_HEADER_CLASS_ATTR));
	entity.setExtendsClassName(getValue(element, XML_EXTENDS_CLASS_ATTR));
	entity.setDiscriminatorValue(getValue(element, XML_DISCRIMINATOR_ATTR));
	
	return entity;
    }

    /**
     * Read embedded entity
     * @param element
     * @return
     */
    protected Entity readEmbeddedEntity(Element element) {
	
	EmbeddedEntity entity = new EmbeddedEntity();
	readBaseEntity(entity, element);
	entity.setClassName(getValue(element, XML_CLASS_ATTR));
	
	return entity;
    }    
    
    protected void readBaseEntity(Entity entity, Element element) {

	String sValue = null;
	Boolean bValue = null;

	entity.setName(getValue(element, XML_NAME_ATTR));

	// Readonly
	bValue = getBooleanValue(element, XML_READONLY_ATTR);
	if (bValue != null) {
	    entity.setReadonly(bValue);
	}
	
	// Insert
	bValue = getBooleanValue(element, XML_INSERT_ATTR);
	if (bValue != null) {
	    entity.setInsert(bValue);
	}
	
	// Update 
	bValue = getBooleanValue(element, XML_UPDATE_ATTR);
	if (bValue != null) {
	    entity.setUpdate(bValue);
	}
	
	// Validator type
	sValue = getValue(element, XML_VALIDATOR_TYPE_ATTR);
	if (sValue != null) {
	    entity.setValidatorType(sValue);
	}

	// Validator class
	sValue = getValue(element, XML_VALIDATOR_CLASS_ATTR);
	if (sValue != null) {
	    entity.setValidatorClassName(sValue);
	}

	// Read config properties
	readConfig(entity, element);
	
	// Read aliases of entity attribute
	readAliases(entity, element);
	
	// Read attributes
	readAttributes(entity, element);
	
	// Read events
	readEvents(entity, element);
	
	// Read triggers
	readTriggers(entity, element);
    }
    
    ////
    
    protected void readConfig(Entity entity, Element element) {
	Element node = getChild(element, XML_CONFIG);
	if (node != null) {
	    List properties = node.getChildren();
	    readConfig(entity, properties);
	}
    }

    protected void readConfig(Entity entity, List properties) {
	int count = properties.size();
	if (count == 0) {
	    return;
	}
	for (int i = 0; i < count; i++) {
	    Element element = (Element) properties.get(i);
	    String name = getValue(element, XML_NAME_ATTR);
	    String value = element.getValue();
	    if (name != null) {
		entity.addConfigProperty(name, value);		
	    }
	}
    }
    
    ////
    
    protected void readAliases(Entity entity, Element element) {
	Element node = getChild(element, XML_ALIASES);
	if (node != null) {
	    List aliases = node.getChildren();
	    readAliases(entity, aliases);
	}
    }

    protected void readAliases(Entity entity, List aliases) {
	int count = aliases.size();
	if (count == 0) {
	    return;
	}
	for (int i = 0; i < count; i++) {
	    Element element = (Element) aliases.get(i);
	    AttributeAlias attribute = new AttributeAlias();
	    attribute.setName(getValue(element, XML_ALIAS_NAME_ATTR));
	    attribute.setAttribute(getValue(element, XML_ALIAS_ATTRIBUTE_ATTR));
	    attribute.setOrderAttribute(getValue(element, XML_ALIAS_ORDER_ATTRIBUTE_ATTR));
	    attribute.setFilterAttribute(getValue(element, XML_ALIAS_FILTER_ATTRIBUTE_ATTR));
	    if (attribute != null) {
		entity.addAttributeAlias(attribute);
	    }
	}
    }

    ////
    
    protected void readAttributes(Entity entity, Element element) {
	Element node = getChild(element, XML_ATTRIBUTES);
	if (node != null) {
	    List attributes = node.getChildren();
	    readAttributes(entity, attributes);
	}
    }
    
    protected void readAttributes(Entity entity, List attributes) {
	int count = attributes.size();
	if (count == 0) {
	    return;
	}
	for (int i = 0; i < count; i++) {
	    Element element = (Element) attributes.get(i);
	    Attribute attribute = readAttribute(entity, element);
	    if (attribute != null) {
		entity.addAttribute(attribute);
	    }
	}
    }
    
    protected Attribute readAttribute(Entity entity, Element element) {
	String elementName = element.getName();
	if (XML_KEY.equals(elementName)) {
	    return readKeyAttribute(element);
	} else if (XML_COMPOSITE_KEY.equals(elementName)) {
	    return readCompositeKeyAttribute(element);
	} else if (XML_ATTRIBUTE.equals(elementName)) {
	    return readBasicAttribute(element);
	} else if (XML_COMPOSITE.equals(elementName)) {
	    return readCompositeAttribute(element);
	} else if (XML_REFERENCE.equals(elementName)) {
	    return readReferenceAttribute(element);
	} else if (XML_ENTRY.equals(elementName)) {
	    return readEntryAttribute(element);
	} else if (XML_COLLECTION.equals(elementName)) {
	    return readCollectionAttribute(element);
	} else if (XML_DISCRIMINATOR.equals(elementName)) {
	    return readDiscriminatorAttribute(element);
	}    
	return null;
    }

    protected Attribute readKeyAttribute(Element element) {
	Key key = new Key();
	key.setName(getValue(element, XML_NAME_ATTR));
	key.setColumnName(getValue(element, XML_COLUMN_ATTR));
	key.setTypeName(getValue(element, XML_TYPE_ATTR));
	key.setClassName(getValue(element, XML_CLASS_ATTR));
	readGenerator(key, element);
	return key;
    }

    protected void readGenerator(Attribute attribute, Element element) {
	attribute.setGeneratorType(getValue(element, XML_GENERATOR_TYPE_ATTR));
	attribute.setGeneratorClassName(getValue(element, XML_GENERATOR_CLASS_ATTR));
	Element child = getChild(element, XML_GENERATOR);
	if (child == null) {
	    return;
	}
	
	// Override 'generator-type' and 'generator-class'
	String value = getValue(child, XML_TYPE_ATTR);
	if (value != null) {
	    attribute.setGeneratorType(value);
	}
	value = getValue(child, XML_CLASS_ATTR);
	if (value != null) {
	    attribute.setGeneratorClassName(value);
	}
	
	List children = getChildren(child, XML_PARAMETER);
	if (children == null || children.isEmpty()) {
	    return;
	}
	String name = null;
	int count = children.size();
	for (int i = 0; i < count; i++) {
	    Element e = (Element) children.get(i);
	    name = getValue(e, XML_NAME_ATTR);
	    value = e.getValue();
	    if (name != null && value != null) {
		// Add generator parameter
		attribute.addGeneratorParameter(name, value);
	    }
	}
    }
    
    protected Attribute readCompositeKeyAttribute(Element element) {
	CompositeKey compositeKey = new CompositeKey();
	compositeKey.setName(getValue(element, XML_NAME_ATTR));
	//compositeKey.setColumnName(getValue(element, XML_COLUMN_ATTR));
	compositeKey.setTypeName(getValue(element, XML_TYPE_ATTR));
	compositeKey.setClassName(getValue(element, XML_CLASS_ATTR));
	readGenerator(compositeKey, element);
	
	List children = element.getChildren();
	int count = children.size();
	if (count > 0) {
	    Element e = null;
	    String eName = null;
	    Attribute a = null;
	    for (int i = 0; i < count; i++) {
		e = (Element) children.get(i);
		eName = e.getName();
		if (XML_ATTRIBUTE.equals(eName)) { // TODO: Replace 'attribute' to 'key' 
		    a = readBasicAttribute(e);
		} else if (XML_COMPOSITE.equals(eName)) {
		    a = readCompositeKeyAttribute(e);
		}
		if (a != null) {
		    compositeKey.addAttribute(a);
		}
	    }
	}
	return compositeKey;
    }

    protected Attribute readBasicAttribute(Element element) {
	Attribute attribute = new Attribute();
	attribute.setName(getValue(element, XML_NAME_ATTR));
	attribute.setColumnName(getValue(element, XML_COLUMN_ATTR));
	attribute.setTypeName(getValue(element, XML_TYPE_ATTR));
	attribute.setClassName(getValue(element, XML_CLASS_ATTR));
	Boolean bValue = getBooleanValue(element, XML_REQUIRED_ATTR);
	if (bValue != null) {
	    attribute.setRequired(bValue);
	}
	attribute.setCheck(getValue(element, XML_CHECK_ATTR));
	readGenerator(attribute, element);
	return attribute;
    }
    
    protected Attribute readReferenceAttribute(Element element) {
	Reference reference = new Reference();
	reference.setName(getValue(element, XML_NAME_ATTR));
	reference.setColumnName(getValue(element, XML_COLUMN_ATTR));
	reference.setClassName(getValue(element, XML_CLASS_ATTR));
	Boolean bValue = getBooleanValue(element, XML_REQUIRED_ATTR);
	if (bValue != null) {
	    reference.setRequired(bValue);
	}
	reference.setCheck(getValue(element, XML_CHECK_ATTR));
	reference.setJoinDepth(getIntValue(element, XML_JOIN_DEPTH));
	return reference;
    }

    protected Attribute readEntryAttribute(Element element) {
	Entry entry = new Entry();
	entry.setName(getValue(element, XML_NAME_ATTR));
	entry.setClassName(getValue(element, XML_CLASS_ATTR));
	Boolean bValue = getBooleanValue(element, XML_REQUIRED_ATTR);
	if (bValue != null) {
	    entry.setRequired(bValue);
	}
	entry.setCheck(getValue(element, XML_CHECK_ATTR));
	entry.setJoinAttributeName(getValue(element, XML_JOIN_ATTRIBUTE_ATTR));
	entry.setJoinColumnName(getValue(element, XML_JOIN_COLUMN_ATTR));
	String cascade = getValue(element, XML_CASCADE_ATTR);
	if (cascade != null) {
	    cascade = cascade.toUpperCase(); // ALL, UPDATE, DELETE 
	    entry.setCascadeType(CascadeType.valueOf(cascade));
	}
	entry.setJoinDepth(getIntValue(element, XML_JOIN_DEPTH));
	return entry;
    }
    
    protected Attribute readCompositeAttribute(Element element) {
	Composite composite = new Composite();
	composite.setName(getValue(element, XML_NAME_ATTR));
	//compositeKey.setColumnName(getValue(element, XML_COLUMN_ATTR));
	composite.setTypeName(getValue(element, XML_TYPE_ATTR));
	composite.setClassName(getValue(element, XML_CLASS_ATTR));
	Boolean bValue = getBooleanValue(element, XML_REQUIRED_ATTR);
	if (bValue != null) {
	    composite.setRequired(bValue);
	}
	composite.setCheck(getValue(element, XML_CHECK_ATTR));
	composite.setJoinDepth(getIntValue(element, XML_JOIN_DEPTH));
	
	List children = element.getChildren();
	int count = children.size();
	if (count > 0) {
	    Element e = null;
	    String eName = null;
	    Attribute a = null;
	    for (int i = 0; i < count; i++) {
		e = (Element) children.get(i);
		eName = e.getName();
		if (XML_ATTRIBUTE.equals(eName)) {
		    a = readBasicAttribute(e);
		} else if (XML_COMPOSITE.equals(eName)) {
		    a = readCompositeAttribute(e);
		}
		if (a != null) {
		    composite.addAttribute(a);
		}
	    }
	}
	return composite;
    }
    
    protected Attribute readCollectionAttribute(Element element) {
	Collection collection = new Collection();
	collection.setName(getValue(element, XML_NAME_ATTR));
	collection.setImplementerClassName(getValue(element, XML_IMPLEMENTER_CLASS_ATTR));
	
	Boolean bValue = getBooleanValue(element, XML_REQUIRED_ATTR);
	if (bValue != null) {
	    collection.setRequired(bValue);
	}
	collection.setCheck(getValue(element, XML_CHECK_ATTR));
	collection.setJoinAttributeName(getValue(element, XML_JOIN_ATTRIBUTE_ATTR));
	collection.setJoinClassName(getValue(element, XML_JOIN_CLASS_ATTR));
	collection.setJoinDepth(getIntValue(element, XML_JOIN_DEPTH));
	
	String cascade = getValue(element, XML_CASCADE_ATTR);
	if (cascade != null) {
	    cascade = cascade.toUpperCase(); // ALL, UPDATE, DELETE 
	    collection.setCascadeType(CascadeType.valueOf(cascade));
	}
	return collection;
    }
    
    protected Attribute readDiscriminatorAttribute(Element element) {
	Discriminator attribute = new Discriminator();
	attribute.setName(getValue(element, XML_NAME_ATTR));
	attribute.setColumnName(getValue(element, XML_COLUMN_ATTR));
	attribute.setTypeName(getValue(element, XML_TYPE_ATTR));
	attribute.setClassName(getValue(element, XML_CLASS_ATTR));
	attribute.setCheck(getValue(element, XML_CHECK_ATTR));	
	return attribute;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Read events of entity
     * @param entity
     * @param element
     */
    protected void readEvents(Entity entity, Element element) {
	Element node = getChild(element, XML_EVENTS);
	if (node != null) {
	    List events = node.getChildren();
	    readEvents(entity, events);
	}
    }
    
    /**
     * Read events of entity from list
     * @param entity
     * @param events
     */
    protected void readEvents(Entity entity, List events) {
	int count = events.size();
	if (count == 0) {
	    return;
	}
	for (int i = 0; i < count; i++) {
	    Element element = (Element) events.get(i);
	    EventWrapper event = new EventWrapper();
	    String type = getValue(element, XML_TYPE_ATTR);
	    if (type != null) {
		type = normalizeEventType(type);
		event.setType(EventType.valueOf(type));
	    }
	    event.setEventClassName(getValue(element, XML_CLASS_ATTR));
	    entity.addEvent(event);
	}
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Read triggers of entity
     * @param entity
     * @param element
     */
    protected void readTriggers(Entity entity, Element element) {
	Element node = getChild(element, XML_TRIGGERS);
	if (node != null) {
	    List triggers = node.getChildren();
	    readTriggers(entity, triggers);
	}
    }
    
    /**
     * Read triggers of entity from list
     * @param entity
     * @param triggers
     */
    protected void readTriggers(Entity entity, List triggers) {
	int count = triggers.size();
	if (count == 0) {
	    return;
	}
	for (int i = 0; i < count; i++) {
	    Element element = (Element) triggers.get(i);
	    String type = getValue(element, XML_TYPE_ATTR);
	    if (type == null || type.isEmpty()) {
		continue;
	    }
	    String name = getValue(element, XML_NAME_ATTR);
	    Element scriptNode = getChild(element, XML_SCRIPT);
	    String scriptText =  scriptNode == null ? null : scriptNode.getValue();
	    
	    // Ignore trigger with empty script
	    if (scriptText == null || scriptText.isEmpty()) {
		continue;
	    }
	    
	    // Get script lines
	    String[] scriptLines = StringUtils.splitScriptLines(scriptText);
	    
	    String language = getValue(scriptNode, XML_LANGUAGE_ATTR);
	    if (language == null || language.isEmpty()) {
		language = TriggerUtils.getLanguageByScript(scriptLines);
	    }
	    if (language == null) {
		continue;
	    }
	    
	    // Create trigger by language
	    AbstractTrigger trigger = TriggerUtils.createTrigger(language);
	    if (trigger == null) {
		continue;
	    }
	    trigger.setName(name);
	    
	    Script script = new Script(language);
	    script.setText(scriptText);
	    script.setLines(scriptLines);
	    
	    trigger.setScript(script);
	    type = normalizeEventType(type);
	    trigger.setType(EventType.valueOf(type));
	    
	    entity.addTrigger(trigger);
	}
    }
    
    
    public Configuration readConfiguration(Configuration configuration, InputStream is) throws IOException {
	Document doc = readDocument(is);
	return readConfiguration(configuration, doc);
    }
    
    protected Configuration readConfiguration(Configuration configuration, Document doc) {
	
	
	Element root = doc.getRootElement();
	String rootName = root.getName();
	if (!XML_CONFIGURATION.equals(rootName)) {
	    handleError("Document is not valid");
	    return null;
	}
	
	List children = root.getChildren();

	int count = children.size();
	if (count == 0) {
	    return configuration;
	}

	// Read configuration
	for (int i = 0; i < count; i++) {
	    Element element = (Element) children.get(i);
	    String elementName = element.getName();
	    if (XML_PROPERTIES.equals(elementName)) {
		readConfigurationProperties(configuration, element);
	    } else if (XML_TYPES.equals(elementName)) {
		readConfigurationTypes(configuration, element);
	    } else if (XML_VALIDATORS.equals(elementName)) {
		readConfigurationValidators(configuration, element);
	    } else if (XML_PROCEDURES.equals(elementName)) {
		readConfigurationProcedures(configuration, element);
	    } else if (XML_MAPPING.equals(elementName)) {
		readConfigurationProcedures(configuration, element);
	    }
	}
	return configuration;
    }

    
    protected void readConfigurationProperties(Configuration configuration, Element element) {
	 List properties = getChildren(element, XML_PROPERTY);
	 readConfigurationProperties(configuration, properties);
    }

    protected void readConfigurationProperties(Configuration configuration, List properties) {
	int count = properties.size();
	if (count == 0) {
	    return;
	}
	for (int i = 0; i < count; i++) {
	    Element element = (Element) properties.get(i);
	    String name = getValue(element, XML_NAME_ATTR);
	    String value = element.getValue();
	    if (name != null) {
		configuration.setProperty(name, value);		
	    }
	}
    }
    
    
    protected void readConfigurationTypes(Configuration configuration, Element element) {
	List children = getChildren(element, XML_TYPE);
	if (children == null) {
	    return;
	}
	int count = children.size();
	if (count == 0) {
	    return;
	}
	    
	for (int i = 0; i < count; i++) {
	    Element child = (Element) children.get(i);
	    String name = getValue(child, XML_NAME_ATTR);
	    if (isEmpty(name)) {
		continue;
	    }
	    String className = getValue(child, XML_CLASS_ATTR);
	    if (isEmpty(className)) {
		continue;
	    }
	    Type type = (Type) getInstance(className);
	    if (type == null) {
		continue;
	    }
	    configuration.addType(name, type);
	}
    }

    protected void readConfigurationValidators(Configuration configuration, Element element) {
	List children = getChildren(element, XML_VALIDATOR);
	if (children == null) {
	    return;
	}
	int count = children.size();
	if (count == 0) {
	    return;
	}
	    
	for (int i = 0; i < count; i++) {
	    Element child = (Element) children.get(i);
	    String name = getValue(child, XML_NAME_ATTR);
	    if (isEmpty(name)) {
		continue;
	    }
	    String className = getValue(child, XML_CLASS_ATTR);
	    if (isEmpty(className)) {
		continue;
	    }
	    Validator validator = (Validator) getInstance(className);
	    if (validator == null) {
		continue;
	    }
	    configuration.addValidator(name, validator);
	}
    }

    protected void readConfigurationProcedures(Configuration configuration, Element element) {
	List children = getChildren(element, XML_PROCEDURE);
	if (children == null) {
	    return;
	}
	int count = children.size();
	if (count == 0) {
	    return;
	}
	    
	for (int i = 0; i < count; i++) {
	    Element child = (Element) children.get(i);
	    String name = getValue(child, XML_NAME_ATTR);
	    if (isEmpty(name)) {
		continue;
	    }

	    String className = getValue(child, XML_CLASS_ATTR);

	    // TODO: Stub only for JavaProcedure
	    if (isEmpty(className)) {
		continue;
	    }

	    String entryPoint = getValue(child, XML_ENTRY_POINT_ATTR);

	    JavaProcedure javaProcedure = new JavaProcedure();
	    javaProcedure.setName(name);
	    javaProcedure.setEntryPoint(entryPoint);
	    javaProcedure.setProcedureClassName(className);
	    configuration.addProcedure(name, javaProcedure);
	}
    }

    protected void readConfigurationMapping(Configuration configuration, Element element) {
	List children = getChildren(element, XML_PATH);
	if (children == null) {
	    return;
	}
	int count = children.size();
	if (count == 0) {
	    return;
	}
	    
	List<String> mapping = new ArrayList<String>();
	for (int i = 0; i < count; i++) {
	    Element child = (Element) children.get(i);
	    String value = child.getValue();
	    if (isEmpty(value)) {
		continue;
	    }
	    mapping.add(value);
	}
	if (mapping.isEmpty()) {
	    return;
	}
	String[] pathList = mapping.toArray(new String[0]);
	configuration.addMapping(pathList);
    }

    private boolean isEmpty(String str){
	return StringUtils.isEmpty(str);
    }
    
    private Object getInstance(String className) {
	return ClassUtils.newSafeInstance(className);
    }

    /**
     * Return child by name
     * @param parent
     * @param name
     * @return
     */
    protected Element getChild(Element parent, String name) {
	return parent.getChild(name, parent.getNamespace());	
    }

    protected List getChildren(Element parent, String name) {
	return parent.getChildren(name, parent.getNamespace());	
    }

    protected boolean useNamespace() {
	return true;
    }
    
    protected String normalizeEventType(String type) {
	//TODO: May be need convert to string with format '<Before|After><Insert|Update|Delete|Load|Select>'
	return type;
    }
    
    protected void handleError(String message, Throwable ex) {
	logger.error(message, ex);
	throw new RuntimeException(message, ex);
    }
    
    protected void handleError(String message) {
	logger.error(message);
	throw new RuntimeException(message);
    }
    
    protected void handleError(Throwable ex) {
	logger.error(ex);
	throw new RuntimeException(ex);
    }
    
}
