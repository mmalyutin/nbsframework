package org.plazmaforge.framework.erm.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.erm.EntityAccessor;
import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.FetchMode;
import org.plazmaforge.framework.erm.LoadMode;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Collection;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.mapping.Entry;
import org.plazmaforge.framework.sql.dialect.Dialect;

public abstract class ModifyExecutor extends QueryExecutor {

    
    public ModifyExecutor(Dialect dialect, EntityManager entityManager) {
	super(dialect, entityManager);
    }
    
    // POPST PROCESSING - ENTRY
    protected void doPostProcessEntryAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
	
	Entity entity = entityContext.getEntity();
	// attrEntity = column.getEntity();

	Entry entry = (Entry) attribute;
	boolean doit = entry.isUpdateCascadeType(); 
 	if (!doit) {
 	    return;
 	}
	
	Entity joinEntity = entry.getJoinEntity();
	if (joinEntity == null) {
	    // TODO: EXCEPTION
	    return;
	}
	
	// ENTRY has join attribute or join column
	
	/*
	String joinAttributeName = entry.getJoinAttributeName();
	if (joinAttributeName == null) {
	    // TODO: EXCEPTION
	    return;
	}
	Attribute joinAttribute = joinEntity.getGlobalAttributeByName(joinAttributeName);
	if (joinAttribute == null) {
	    // TODO: EXCEPTION
	    return;
	}
	*/

	EntityAccessor accessor = getEntityAccessor(entity);
	EntityAccessor joinAccessor = getEntityAccessor(joinEntity);

	Attribute innerKey = joinEntity.getGlobalKey(); // Inner key

	InsertExecutor insertExecutor = new InsertExecutor(getDialect(), getEntityManager());
	UpdateExecutor updateExecutor = new UpdateExecutor(getDialect(), getEntityManager());

	List<Query> insertQueries = insertExecutor.createInsertQueries(joinEntity);
	List<Query> updateQueries = updateExecutor.createUpdateQueries(joinEntity);

	///////////////////////////////////////////////////////////////////////////////////

	boolean insertMode = QueryType.INSERT.equals(getType()); 
	try {
	    
	    // Prepare statements
	    prepareStatements(cn, insertQueries);
	    prepareStatements(cn, updateQueries);

	    int count = dataList.length;
	    for (int i = 0; i < count; i++) {
		Object obj = dataList[i];
		Object inner = accessor.getValue(obj, attribute); // Get inner from object
		if (inner == null) {
		    // TODO: Must check not-null of attribute
		    continue;
		}
		Object innerId = joinAccessor.getValue(inner, innerKey);
		boolean saved = (insertMode && innerKey.isAssignKey()) ? false : !EntityHelper.isUnsaved(innerKey, innerId);
		if (saved) {
		    updateExecutor.executeUpdateQueries(cn, joinEntity, updateQueries, inner);
		} else {
		    insertExecutor.executeInsertQueries(cn, joinEntity, insertQueries, inner);
		}
	    }

	} finally {
	    close(insertQueries);
	    close(updateQueries);
	}
    }     

    // POST PROCESSING
    protected void doPostProcessCollectionAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {
	
	Entity entity = entityContext.getEntity();
	// attrEntity = column.getEntity();

	Collection collection = (Collection) attribute;
	boolean doit = collection.isUpdateCascadeType(); 
 	if (!doit) {
 	    return;
 	}
 	
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
	EntityAccessor joinAccessor = getEntityAccessor(joinEntity);

	Attribute[] keys = entity.getGlobalDetailKeys(); // Parent keys
	TypeValue[] keyTypeValues = null;

	Attribute innerKey = joinEntity.getGlobalKey(); // Inner key

	InsertExecutor insertExecutor = new InsertExecutor(getDialect(), getEntityManager());
	UpdateExecutor updateExecutor = new UpdateExecutor(getDialect(), getEntityManager());
	DeleteExecutor deleteExecutor = new DeleteExecutor(getDialect(), getEntityManager());

	List<Query> insertQueries = insertExecutor.createInsertQueries(joinEntity);
	List<Query> updateQueries = updateExecutor.createUpdateQueries(joinEntity);
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
	SelectTemplate existsTemplate = createSelectTemplate(joinEntity, queryInput, false);

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
	    
	    // Prepare statements
	    prepareStatements(cn, insertQueries);
	    prepareStatements(cn, updateQueries);
	    prepareStatements(cn, deleteQueries);
	    
	    existsQuery.prepareStatement(cn);

	    int count = dataList.length;
	    for (int i = 0; i < count; i++) {

		Object obj = dataList[i];

		keyTypeValues = EntityHelper.getKeyTypeValues(accessor, keys, obj); // Get parent key values

		// Set parent key values parameters
		existsQuery.setParameters(keyTypeValues);

		// Get real innerList from DB
		List<Object> existsList = existsQuery.getListResult();
		boolean existsEmpty = existsList == null || existsList.isEmpty();

		// Get innerList from parent object
		java.util.Collection innerCollection = accessor.getCollectionValue(obj, collection); 
		boolean innerEmpty = innerCollection == null || innerCollection.isEmpty();

		if (existsEmpty && innerEmpty) {
		    // All empty
		    continue;
		}
		
		MarkerObject marker = null;
		Map<Object, MarkerObject> innerKeyMap = new HashMap<Object, MarkerObject>();
		for (Object inner : existsList) {
		    Object innerId = joinAccessor.getValue(inner, innerKey);
		    if (innerId == null) {
			// TODO: ERROR
		    }
		    marker = new MarkerObject(inner, false);
		    innerKeyMap.put(innerId, marker);
		}

		// SAVE: INSERT or UPDATE
		for (Object inner: innerCollection) {
		    Object innerId = joinAccessor.getValue(inner, innerKey);
		    boolean saved = !EntityHelper.isUnsaved(innerKey, innerId);
		    boolean found = saved || innerKeyMap.containsKey(innerId);
		    if (found) {
			innerKeyMap.get(innerId).setModify(true);
			updateExecutor.executeUpdateQueries(cn, joinEntity, updateQueries, inner);
		    } else {
			insertExecutor.executeInsertQueries(cn, joinEntity, insertQueries, inner);
		    }
		}

		for (Object innerId : innerKeyMap.keySet()) {
		    marker = innerKeyMap.get(innerId);
		    boolean modify = marker.isModify();
		    if (modify) {
			continue;
		    }
		    deleteExecutor.executeDeleteQueries(cn, joinEntity, deleteQueries, marker.getData()); // Delete inner
		}
	    }

	} finally {
	    close(insertQueries);
	    close(updateQueries);
	    close(deleteQueries);
	    existsQuery.close();
	}
    }     
    
  
     
}
