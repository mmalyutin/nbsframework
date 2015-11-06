package org.plazmaforge.framework.erm.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.erm.EntityAccessor;
import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.FetchMode;
import org.plazmaforge.framework.erm.LoadMode;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.erm.event.EventType;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Collection;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.mapping.Entry;
import org.plazmaforge.framework.erm.mapping.Key;
import org.plazmaforge.framework.sql.dialect.Dialect;

public class DeleteExecutor extends ModifyExecutor {

    public DeleteExecutor(Dialect dialect, EntityManager entityManager) {
	super(dialect, entityManager);
    }

    protected QueryType getType() {
	return QueryType.DELETE;
    }
    
    public List<Query> createDeleteQueries(Entity entity) {
 	Entity[] entities = entity.getHierarchy();
 	Attribute[] attributes = null;
 	Key key = null;
 	Entity currEntity = null;

 	List<Query> queries = null;
 	int count = entities.length;
 	Query query = null;
 	queries = new ArrayList<Query>();

 	// Create queries
 	for (int i = 0; i < count; i++) {
 	    currEntity = entities[i];
 	    key = currEntity.getGlobalKey();
 	    // 2012-07-14
 	    attributes = QueryTemplate.getAttributes(currEntity, QueryType.DELETE); // Get attributes to analyze
 	    if (!currEntity.hasOwnTable()) {
 		continue;
 	    }
 	    query = createDeleteQuery(currEntity, attributes, key);
 	    queries.add(query);
 	}

 	return queries;
     }
     
     public void executeDeleteQueries(Connection cn, Entity entity, List<Query> queries, Object obj) throws SQLException {
 	int count = queries.size();
 	Query query = null;

 	// PRE EVENTS
 	doFireEvents(cn, entity, obj, EventType.BeforeDelete, true); // REVERSE
 	doExecuteTriggers(cn, entity, obj, EventType.BeforeDelete, true); // REVERSE
 	
 	// REVERSE
 	for (int i = count - 1; i >= 0; i--) {
 	    query = queries.get(i);
 	    executeDeleteQuery(cn, query, obj);
 	}

 	// POST EVENTS
 	doFireEvents(cn, entity, obj, EventType.AfterDelete, true); // REVERSE
 	doExecuteTriggers(cn, entity, obj, EventType.AfterDelete, true); // REVERSE
 	
     }
     
     public Query createDeleteQuery(Entity entity, Attribute[] attributes, Key key) {
 	DeleteTemplate template = createDeleteTemplate(entity, attributes, key);
 	Query query = new Query(template);
 	return query;
     }
     
     public void executeDeleteQuery(Connection cn, Query query, Object obj) throws SQLException {
 	
 	checkConnection(cn);
 	if (obj == null) {
 	    throw new RuntimeException("Can not delete object. Oject is null");
 	}
 	
 	DeleteTemplate template = (DeleteTemplate) query.getTemplate();
 	Entity entity = template.getEntity();
 	
 	// PRE PROCESS
 	doPreProcess(cn, template.getEntityContext(), obj);
 	
 	//doFireEvents(cn, entity, obj, EventType.BeforeDelete);
 	//doExecuteTriggers(cn, entity, obj, EventType.BeforeDelete);
 	
 	
 	
 	EntityAccessor accessor = getEntityAccessor(entity);
 	Key key = template.getQueryInput().getKey();
 	Attribute[] keys = key.getDetailAttributes();
 	TypeValue[] keyTypeValues = EntityHelper.getKeyTypeValues(accessor, keys, obj);
 	if (keyTypeValues == null || keyTypeValues.length == 0) {
 	    throw new RuntimeException("Key values are empty");
 	}
 	query.setParameters(keyTypeValues, 1);   // SET KEYS
 	
 	int count = query.executeUpdate();
 	if (count < 1) {
 	    throw new RuntimeException("Can not delete object");
 	}
 	
 	// POST PROCESS
 	doPostProcess(cn, template.getEntityContext(), obj);
 	
 	//doFireEvents(cn, entity, obj, EventType.AfterDelete);
 	//doExecuteTriggers(cn, entity, obj, EventType.AfterDelete);
     }
     
     
    protected DeleteTemplate createDeleteTemplate(Entity entity, Attribute[] attributes, Key key) {
	QueryInput queryInput = createQueryInput();
	queryInput.setAttributes(attributes);
	queryInput.setKey(key);
	return createDeleteTemplate(entity, queryInput);
    }

    protected DeleteTemplate createDeleteTemplate(Entity entity, QueryInput queryInput) {
	DeleteTemplate template = getBuilder().createDeleteTemplate(entity, queryInput);
	checkTemplate(template);
	StringBuffer buf = new StringBuffer();
	getGenerator().generateDeleteSQL(template, buf);
	String sql = buf.toString();
	checkSQL(sql);
	template.setText(sql);
	return template;
    }
    
    // PRE PROCESSING - DELETE CHILD ENTRY
    protected void doPreProcessEntryAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
 	
	Entry entry = (Entry) attribute;
 	boolean doit = entry.isDeleteCascadeType(); 
 	if (!doit) {
 	    return;
 	}
 	Entity entity = entityContext.getEntity();
 	Entity joinEntity = entry.getJoinEntity();
 	if (joinEntity == null) {
 	    // TODO: EXCEPTION
 	    return;
 	}
 	
 	// ENTRY has join attribute or join column
 	
 	/*String joinAttributeName = entry.getJoinAttributeName();
 	if (joinAttributeName == null) {
 	    // TODO: EXCEPTION
 	    return;
 	}
 	Attribute joinAttribute = joinEntity.getGlobalAttributeByName(joinAttributeName);
 	if (joinAttribute == null) {
 	    // TODO: EXCEPTION
 	    return;
 	}*/
 	
 	
 	EntityAccessor accessor = getEntityAccessor(entity);

 	DeleteExecutor deleteExecutor = new DeleteExecutor(getDialect(), getEntityManager());
 	List<Query> deleteQueries = deleteExecutor.createDeleteQueries(joinEntity);

 	try {
 	    // Prepare statement
 	    prepareStatements(cn, deleteQueries);

 	    int count = dataList.length;
 	    for (int i = 0; i < count; i++) {
 		Object obj = dataList[i];
 		Object inner = accessor.getValue(obj, attribute);
 		deleteExecutor.executeDeleteQueries(cn, joinEntity, deleteQueries, inner);
 	    }

 	} finally {
 	    close(deleteQueries);
 	}
    }

    // PRE PROCESSING - DELETE CHILD COLLECTION
    protected void doPreProcessCollectionAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
	
 	Collection collection = (Collection) attribute;
 	//CascadeType cascadeType = collection.getCascadeType();
 	boolean doit = collection.isDeleteCascadeType(); //cascadeType != null && (CascadeType.ALL.equals(cascadeType) || CascadeType.DELETE.equals(cascadeType)); 
 	if (!doit) {
 	    return;
 	}
 	Entity entity = entityContext.getEntity();
 	Entity joinEntity = collection.getJoinEntity();
 	if (joinEntity == null) {
 	    // TODO: EXCEPTION
 	    return;
 	}
 	String joinAttributeName = collection.getJoinAttributeName();
 	if (joinAttributeName == null) {
 	    // TODO: EXCEPTION
 	    return;
 	}
 	Attribute joinAttribute = joinEntity.getGlobalAttributeByName(joinAttributeName);
 	if (joinAttribute == null) {
 	    // TODO: EXCEPTION
 	    return;
 	}

 	EntityAccessor accessor = getEntityAccessor(entity);
 	//EntityAccessor joinAccessor = getEntityAccessor(joinEntity);

 	Attribute[] keys = entity.getGlobalDetailKeys(); // Parent keys
 	TypeValue[] keyTypeValues = null;

 	Attribute innerKey = joinEntity.getGlobalKey(); // Inner key

 	DeleteExecutor deleteExecutor = new DeleteExecutor(getDialect(), getEntityManager());
 	List<Query> deleteQueries = deleteExecutor.createDeleteQueries(joinEntity);

	/////////////////////////////////////////////////////////////////////////////////
	String[] includeAttributes = new String[] {innerKey.getName()};

	Criteria criteria = new Criteria();
	EntityConfig entityConfig = entityContext.getEntityConfig();
	FetchMode fetchMode = entityConfig == null ? null : entityConfig.getFetchMode();
	LoadMode loadMode = entityConfig == null ? null : entityConfig.getLoadMode();
	QueryInput queryInput = createQueryInput(criteria, fetchMode, loadMode);
	queryInput.setIncludeAttributes(includeAttributes);

	// Create query without generate SQL
	SelectTemplate existsTemplate = createSelectTemplate(joinEntity, queryInput, QueryType.SELECT_FOR_DELETE, false);

	existsTemplate.addFilter(joinAttribute, existsTemplate.getEntityContext()); // Add parent filter

	// Generate SQL after added parent filter
	StringBuffer buf = new StringBuffer();
	getGenerator().generateSelectSQL(existsTemplate, buf);
	String sql = buf.toString();
	if (isEmpty(sql)) {
	    throw new RuntimeException("Error generate Query. SQL is empty");
	}
	existsTemplate.setText(sql);
	Query existsQuery = new Query(existsTemplate);
	///////////////////////////////////////////////////////////////////////////////////
 	
 	try {
 	    // Prepare statement
 	    prepareStatements(cn, deleteQueries);
 	    existsQuery.prepareStatement(cn);

 	    int count = dataList.length;
 	    for (int i = 0; i < count; i++) {

 		Object obj = dataList[i];

 		keyTypeValues = EntityHelper.getKeyTypeValues(accessor, keys, obj); // Get parent key values

		// Set parent key values parameters
		existsQuery.setParameters(keyTypeValues);

		// Get real children from DB
		List<Object> existsList = existsQuery.getListResult();
		boolean existsEmpty = existsList == null || existsList.isEmpty();
		
		if (existsEmpty) {
		    // All empty
		    continue;
		}

 		// DELETE
		// TODO: May be need optimize query, use SELECT by parent
 		for (Object inner : existsList) {
 		    deleteExecutor.executeDeleteQueries(cn, joinEntity, deleteQueries, inner);
 		}
 	    }

 	} finally {
 	    close(deleteQueries);
 	    existsQuery.close();
 	}
     }

    // POPST PROCESSING - ENTRY
    protected void doPostProcessEntryAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
	// Do nothing
    }
	
    // POST PROCESSING - NOTHING: OVERRIDE MODIFY
    protected void doPostProcessCollectionAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
	// Do nothing
    }

}
