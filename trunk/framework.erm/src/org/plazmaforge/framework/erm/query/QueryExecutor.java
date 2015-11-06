package org.plazmaforge.framework.erm.query;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.EntityAccessor;
import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.FetchMode;
import org.plazmaforge.framework.erm.LoadMode;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.validation.ValidationUtils;
import org.plazmaforge.framework.core.validation.Validator;
import org.plazmaforge.framework.erm.event.Event;
import org.plazmaforge.framework.erm.event.EventType;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.mapping.ICollection;
import org.plazmaforge.framework.erm.mapping.IComposite;
import org.plazmaforge.framework.erm.mapping.IEntry;
import org.plazmaforge.framework.erm.mapping.IReference;
import org.plazmaforge.framework.erm.sql.SQLGenerator;
import org.plazmaforge.framework.erm.trigger.Trigger;
import org.plazmaforge.framework.sql.dialect.Dialect;

public abstract class QueryExecutor {

 
    private Dialect dialect;
    
    private QueryBuilder builder;
    
    private SQLGenerator generator;


    //Optional
    private EntityManager entityManager;
    
    
    public QueryExecutor(Dialect dialect, EntityManager entityManager) {
	super();
	this.dialect = dialect;
	this.entityManager = entityManager;
    }
    
    protected abstract QueryType getType();
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    public Dialect getDialect() {
        return dialect;
    }

    public SQLGenerator getGenerator() {
	if (generator== null) {
	    generator = new SQLGenerator(getDialect());
	}
        return generator;
    }

    public QueryBuilder getBuilder() {
	if (builder == null) {
	    builder = new QueryBuilder(getConfiguration());
	}
        return builder;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public Configuration getConfiguration() {
	return entityManager == null ? null : entityManager.getConfiguration();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 
    // PRE PROCESS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////


    protected void doPreProcess(Connection cn, EntityContext entityContext, Object obj) throws SQLException {
	 doPreProcess(cn, entityContext, toArray(obj));
    }

    protected void doPreProcess(Connection cn, EntityContext entityContext, Object[] dataList) throws SQLException {
	if (entityContext == null) {
	    return;
	}
	
	// Processing own attributes
	boolean needProcessing = entityContext.hasProcessingAttributes();
	if (needProcessing) {
	    Attribute[] attributes = entityContext.getProcessingArttributes();
	    for (Attribute attribute : attributes) {
		doPreProcess(cn, entityContext, attribute, dataList);
	    }
	}
	
	// Processing children
	if (!entityContext.hasChildren()) {
	    return;
	}
	Entity entity = entityContext.getEntity();
	EntityAccessor accessor = getEntityAccessor(entity);
	List<EntityContext> childrenContexts = entityContext.getChildren();
	for (EntityContext childContext : childrenContexts) {
	    String attributeName = childContext.getAttributeName();
	    Attribute attribute = entity.getGlobalAttributeByName(attributeName);
	     
	    Object[] childList = new Object[dataList.length];
	    for (int i = 0; i < dataList.length; i++) {
		Object data = dataList[i];
		childList[i] = data == null ? null : accessor.getValue(data, attribute); // Get child object 
	    }
	    doPreProcess(cn, childContext, childList);
	}
    }
    
    protected void doPreProcess(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
	if (attribute instanceof IComposite) {
	    doPreProcessCompositeAttribute(cn, entityContext, attribute, dataList);
	} else if (attribute instanceof IReference) {
	    doPreProcessReferenceAttribute(cn, entityContext, attribute, dataList);
	} else if (attribute instanceof IEntry) {
	    doPreProcessEntryAttribute(cn, entityContext, attribute, dataList);
	} else if (attribute instanceof ICollection) {
	    doPreProcessCollectionAttribute(cn, entityContext, attribute, dataList);
	} else if (attribute.isBasicType()) {
	    doPreProcessBasicAttribute(cn, entityContext, attribute, dataList);
	}
    }
    
    protected void doPreProcessCompositeAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
    }
    
    protected void doPreProcessReferenceAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
    }
    
    protected void doPreProcessEntryAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
    }
    
    protected void doPreProcessCollectionAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
    }
    
    protected void doPreProcessBasicAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
    }

    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 
    // POST PROCESS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void doPostProcess(Connection cn, EntityContext entityContext, Object obj) throws SQLException {
	 doPostProcess(cn, entityContext, toArray(obj));
    }

    protected void doPostProcess(Connection cn, EntityContext entityContext, Object[] dataList) throws SQLException {
	if (entityContext == null) {
	    return;
	}
	
	// Processing own attributes
	boolean needProcessing = entityContext.hasProcessingAttributes();
	if (needProcessing) {
	    Attribute[] attributes = entityContext.getProcessingArttributes();
	    for (Attribute attribute : attributes) {
		doPostProcess(cn, entityContext, attribute, dataList);
	    }
	}

	// Processing children
	if (!entityContext.hasChildren()) {
	    return;
	}
	Entity entity = entityContext.getEntity();
	EntityAccessor accessor = getEntityAccessor(entity);
	List<EntityContext> childrenContexts = entityContext.getChildren();
	for (EntityContext childContext : childrenContexts) {
	    String attributeName = childContext.getAttributeName();
	    Attribute attribute = entity.getGlobalAttributeByName(attributeName);
	     
	    Object[] childList = new Object[dataList.length];
	    for (int i = 0; i < dataList.length; i++) {
		Object data = dataList[i];
		childList[i] = data == null ? null : accessor.getValue(data, attribute); // Get child object 
	    }
	    doPostProcess(cn, childContext, childList);
	}
    }
    
    protected void doPostProcess(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
	if (attribute instanceof IComposite) {
	    doPostProcessCompositeAttribute(cn, entityContext, attribute, dataList);
	} else if (attribute instanceof IReference) {
	    doPostProcessReferenceAttribute(cn, entityContext, attribute, dataList);
	} else if (attribute instanceof IEntry) {
	    doPostProcessEntryAttribute(cn, entityContext, attribute, dataList);
	} else if (attribute instanceof ICollection) {
	    doPostProcessCollectionAttribute(cn, entityContext, attribute, dataList);
	} else if (attribute.isBasicType()) {
	    doPostProcessBasicAttribute(cn, entityContext, attribute, dataList);
	}
    }
    
    protected void doPostProcessCompositeAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
    }
    
    protected void doPostProcessReferenceAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
    }
    
    protected void doPostProcessEntryAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
    }
    
    protected void doPostProcessCollectionAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
    }
    
    protected void doPostProcessBasicAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    protected QueryInput createQueryInput(Criteria criteria, FetchMode fetchMode, LoadMode loadMode) {
	if (criteria == null) {
	    criteria = new Criteria();
	}
//	if (fetchMode == null) {
//	    fetchMode = getConfigFetchMode();
//	}
//	if (loadMode == null) {
//	    loadMode = getConfigLoadMode();
//	}
	QueryInput input = new QueryInput(criteria, fetchMode, loadMode);
	input.configure(getConfiguration());
	return input;
    }

    
    ////
    
    public void close(List<Query> queries) {
	if (queries == null || queries.isEmpty()) {
	    return;
	}
	for (Query query : queries) {
	    query.close();
	}
    }

    public void prepareStatements(Connection cn, List<Query> queries) throws SQLException {
	if (queries == null || queries.isEmpty()) {
	    return;
	}
	for (Query query : queries) {
	    query.prepareStatement(cn);
	}
    }     

    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Utils
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected boolean isEmpty(String str) {
	return str == null || str.trim().length() == 0;
    }

    protected Object[] toArray(Object obj) {
	if (obj == null) {
	    return new Object[0];
	}
	Object[] array =  new Object[1];
	array[0] = obj;
	return array;
    }
    
    protected Object[] toArray(List list) {
	if (list == null || list.isEmpty()) {
	    return new Object[0];
	}
	return list.toArray(new Object[0]);
    }

    protected Attribute[] add(Attribute[] a1,  Attribute[] a2) {
   	int len1 = a1 == null ? 0: a1.length;
   	int len2 = a2 == null ? 0: a2.length;
   	int len = len1 + len2;
   	Attribute[] attributes = new Attribute[len];
   	if (len == 0) {
   	    return attributes;
   	}
   	for (int i = 0; i < len1; i++) {
   	    attributes[i] = a1[i];
   	}
   	for (int i = 0; i < len2; i++) {
   	    attributes[i + len1] = a2[i];
   	}
   	return attributes;
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    protected QueryInput createQueryInput() {
	return createQueryInput(null, null, null);
    }
    
    public EntityAccessor getEntityAccessor(Entity entity) {
	return EntityHelper.getEntityAccessor(entity);
    }
    
    
	    
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    public SelectTemplate createLoadTemplate(Entity entity) {
	return createLoadTemplate(entity, createQueryInput());
    }
    
    public SelectTemplate createLoadTemplate(Entity entity, QueryInput queryInput) {
	SelectTemplate template = getBuilder().createLoadTemplate(entity, queryInput);
	checkTemplate(template);
	checkColumns(template);
	StringBuffer buf = new StringBuffer();
	getGenerator().generateLoadSQL(template, buf);
	String sql = buf.toString();
	checkSQL(sql);
	template.setText(sql);
	return template;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    
    public SelectTemplate createSelectTemplate(Entity entity, QueryInput queryInput) {
	return createSelectTemplate(entity, queryInput, QueryType.SELECT, true);
    }

    public SelectTemplate createSelectTemplate(Entity entity, QueryInput queryInput, boolean isGenerateSQL) {
	return createSelectTemplate(entity, queryInput, QueryType.SELECT, isGenerateSQL);
    }
    
    public SelectTemplate createSelectTemplate(Entity entity, QueryInput queryInput, QueryType queryType, boolean isGenerateSQL) {
	SelectTemplate template = getBuilder().createSelectTemplate(entity, queryInput, queryType);
	checkTemplate(template);
	checkColumns(template);
	if (!isGenerateSQL) {
	    return template;
	}
	StringBuffer buf = new StringBuffer();
	getGenerator().generateSelectSQL(template, buf);
	String sql = buf.toString();
	checkSQL(sql);
	template.setText(sql);
	return template;
    }
    
   
    ///////////////////////////////////////////////////////////////////////////////////////////

    protected void checkConnection(Connection cn) throws SQLException {
	if (cn == null) {
	    throw new RuntimeException("Connection is null");
	}
	if (cn.isClosed()) {
	    throw new RuntimeException("Connection is closed");
	}
    }

    protected void checkTemplate(QueryTemplate template) {
	if (template == null) {
	    throw new RuntimeException("Error generate Query. Template is null");
	}
    }

    protected void checkColumns(QueryTemplate template) {
	if (!template.hasColumns()) {
	    throw new RuntimeException("Error generate Query. Query has not columns");
	}
    }

    protected void checkSQL(String sql) {
	if (isEmpty(sql)) {
	    throw new RuntimeException("Error generate Query. SQL is empty");
	}
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////

    
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 
    // FIRE EVENTS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void doFireEvents(Connection cn, Entity entity, Object obj, EventType eventType) throws SQLException {
	doFireEvents(cn, entity, obj, eventType, false);
    }
	
    protected void doFireEvents(Connection cn, Entity entity, Object obj, EventType eventType, boolean reverse) throws SQLException {
	Event[] events  = entity.getGlobalEvents();
	if (events == null || events.length == 0) {
	    return;
	}
	if (reverse) {
	    events = reverse(events);
	}
	Map<String, Object> parameters = new HashMap<String, Object>();
	parameters.put(Event.ENTITY_PARAMETER, entity);
	parameters.put(Event.DATA_PARAMETER, obj);
	parameters.put(Event.ENTITY_MANAGER, getEntityManager());
	for (Event event : events) {
	    if (!acceptEvent(eventType, event.getType())) {
		continue;
	    }
	    doFireEvent(cn, event, parameters);
	}
    }

    protected void doFireEvent(Connection cn, Event event, Map<String, Object> parameters) throws SQLException {
	event.fire(cn, parameters);
    }
    
    

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 
    // EXECUTE TRIGGERS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void doExecuteTriggers(Connection cn, Entity entity, Object obj, EventType eventType) throws SQLException {
	doExecuteTriggers(cn, entity, obj, eventType, false);
    }
	
    protected void doExecuteTriggers(Connection cn, Entity entity, Object obj, EventType eventType, boolean reverse) throws SQLException {
	Trigger[] triggers  = entity.getGlobalTriggers();
	if (triggers == null || triggers.length == 0) {
	    return;
	}
	if (reverse) {
	    triggers = reverse(triggers);
	}
	Map<String, Object> parameters = new HashMap<String, Object>();
	parameters.put(Event.ENTITY_PARAMETER, entity);
	parameters.put(Event.DATA_PARAMETER, obj);
	parameters.put(Event.ENTITY_MANAGER, getEntityManager());
	for (Trigger trigger : triggers) {
	    if (!acceptEvent(eventType, trigger.getType())) {
		continue;
	    }
	    doExecuteTrigger(cn, trigger, parameters);
	}
    }

    protected void doExecuteTrigger(Connection cn, Trigger trigger, Map<String, Object> parameters) throws SQLException {
	trigger.execute(cn, parameters);
    }

    protected boolean acceptEvent(EventType eventType, EventType inputType) {
	if (eventType == null || inputType == null){
	    return false;
	}
	if (eventType.equals(inputType)) {
	    return true;
	}
	if ((eventType.equals(EventType.BeforeInsert) || eventType.equals(EventType.BeforeUpdate))
		&& inputType.equals(EventType.BeforeSave)) {
	    return true;
	}
	if ((eventType.equals(EventType.AfterInsert) || eventType.equals(EventType.AfterUpdate))
		&& inputType.equals(EventType.AfterSave)) {
	    return true;
	}
	return false;
    }
    

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 
    // VALIDATORS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void doValidators(Entity entity, Object obj) {
	Attribute[] attributes = entity.getGlobalAttributes();
	EntityAccessor accessor = getEntityAccessor(entity);
	
	String message = null;
	Object value = null;
	boolean getValue = false;
	Validator requiredValidator = null;
	Validator validator = null;
	for (Attribute attribute : attributes) {
	    getValue = false;
	    
	    // REQUIRED
	    if (attribute.isRequired()) {
		if (requiredValidator == null) {
		    requiredValidator = getConfiguration().getValidator(Validator.Required);
		    if (requiredValidator == null) {
			throw new RuntimeException("Required Validator not found");
		    }
		}
		value = accessor.getValue(obj, attribute);
		getValue = true;
		message = ValidationUtils.getErrorMessage(requiredValidator.validate(value));
		handleValidationError(entity, attribute, message);
	    }
	    
	    validator = attribute.getValidator();
	    if (validator == null) {
		continue;
	    }
	    if (!getValue) {
		value = accessor.getValue(obj, attribute);
	    }
	    message = ValidationUtils.getErrorMessage(validator.validate(value));
	    handleValidationError(entity, attribute, message);
	}
    }
    
    protected void handleValidationError(Entity entity, Attribute attribute, String message) {
	if (message == null) {
	    return;
	}
	throw new RuntimeException("Attribute '" + attribute.getName() + "' of entity '" + entity.getIdentifier() + "' is invalid: " + message);
    }
    
    
    /**
     * Return reverse order array
     * @param array
     * @return
     */
    protected <T> T[] reverse(T[] array) {
	if (array == null) {
	    return null;
	}
	Class type = array.getClass();
	int length = array.length;
	T[] result =  ((Object) type == (Object) Object[].class) ? (T[]) new Object[length] : (T[]) Array.newInstance(type.getComponentType(), length);
	for (int i = 0; i < length; i++) {
	    result[i] = array[length - i - 1];
	}
        return result;

    }
    
    ////
    
//  private FetchMode getConfigFetchMode() {
//	Configuration configuration = getConfiguration();
//	return configuration == null ? Configuration.DEFAULT_FETCH_MODE : configuration.getFetchMode(); 
//  }
//  
//  private LoadMode getConfigLoadMode() {
//	Configuration configuration = getConfiguration();
//	return configuration == null ? Configuration.DEFAULT_LOAD_MODE : configuration.getLoadMode();
//  }
    
}
