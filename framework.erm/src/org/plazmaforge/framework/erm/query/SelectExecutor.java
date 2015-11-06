package org.plazmaforge.framework.erm.query;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.DataList;
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
import org.plazmaforge.framework.erm.mapping.Key;
import org.plazmaforge.framework.sql.dialect.Dialect;

public class SelectExecutor extends QueryExecutor {

    
    
    public SelectExecutor(Dialect dialect, EntityManager entityManager) {
	super(dialect, entityManager);
    }

    protected QueryType getType() {
	return QueryType.SELECT;
    }

    public Query createLoadQuery(Entity entity, QueryInput queryInput) {
	SelectTemplate template = createLoadTemplate(entity, queryInput);
	Query query = new Query(template);
	return query;
    }
    
    public <T> T executeLoadQuery(Connection cn, Query query, Serializable id) throws SQLException {
	checkConnection(cn);
	if (id == null) {
	    return null;
	}
	SelectTemplate template = (SelectTemplate) query.getTemplate();
	Entity entity = template.getEntity();
	
	
	// PRE PROCESS
 	doFireEvents(cn, entity, null, EventType.BeforeLoad); // Object is null because not loaded
 	doExecuteTriggers(cn, entity, null, EventType.BeforeLoad); // Object is null because not loaded
	
	
	
	Key key = entity.getGlobalKey();
	T obj = null;
	TypeValue[] keyTypeValues = EntityHelper.convertIdToTypeValues(entity, key, id);
	
	// LOAD OBJECT BY ID
	try {
	    query.prepareStatement(cn);
	    query.setParameters(keyTypeValues);
	    obj = query.getSingleResult();
	} finally {
	    query.close();
	}
	
	// POST PROCESS
	doPostProcess(cn, template.getEntityContext(), obj);
	initObject(obj, entity);
	
 	doFireEvents(cn, entity, obj, EventType.AfterLoad);
 	doExecuteTriggers(cn, entity, obj, EventType.AfterLoad);

	if (obj != null && template.getEntityContext().hasChildren()) {
	    EntityAccessor accessor = getEntityAccessor(entity);
	    List<EntityContext> childrenContexts = template.getEntityContext().getChildren();
	    for (EntityContext childContext : childrenContexts) {
		String attributeName = childContext.getAttributeName();
		Attribute attribute = entity.getGlobalAttributeByName(attributeName);
		Object childObj = accessor.getValue(obj, attribute); // Get child object
		if (childObj == null) {
		    // No trigger for null object
		    continue;
		}
		Entity childEntity = childContext.getEntity();
		doExecuteTriggers(cn, childEntity, childObj, EventType.AfterLoad);
	    }
	}
	return obj;
    }    
    
    protected void initObject(Object obj, Entity entity) {
	
    }
    
    ////
    
    public Query createSelectQuery(Entity entity, QueryInput queryInput) {
	Criteria criteria = queryInput.getCriteria();
	boolean isPaging = criteria == null ? false: criteria.isPaging();
	SelectTemplate template = createSelectTemplate(entity, queryInput, !isPaging);
	Query query = new Query(template);
	return query;
    }
    
    public <T> List<T> executeSelectQuery(Connection cn, Query query) throws SQLException {
	SelectTemplate template = (SelectTemplate) query.getTemplate();
	QueryInput queryInput = template.getQueryInput();
	Criteria criteria = queryInput.getCriteria();
	boolean isPaging = criteria == null ? false: criteria.isPaging();

	List<T> dataList = null;
	try {
	    if (isPaging) {
		String sql = getGenerator().getCountSelectSQL(template);
		template.setText(sql);
		query.prepareStatement(cn);
		query.setCriteriaParameters();
		int count = query.intValue();
		query.setTotalRowCount(count);
		query.close();
		if (count == 0) {
		    return new ArrayList<T>();
		}
		sql = getGenerator().getLimitSelectSQL(template, criteria.getOffset(), criteria.getLimit());
		template.setText(sql);
	    }
	    query.prepareStatement(cn);
	    query.setCriteriaParameters();
	    dataList = query.getListResult();
	} finally {
	    query.close();
	}
	doPostProcess(cn, template.getEntityContext(), toArray(dataList));
	//initObject(obj, entity);

	if (isPaging) {
	    return new DataList<T>(dataList, query.getTotalRowCount());
	}
	return dataList;
    }
    
    protected void doPostProcessCollectionAttribute(Connection cn, EntityContext entityContext, Attribute attribute, Object[] dataList) throws SQLException {

	Entity entity = entityContext.getEntity();
	//attrEntity = column.getEntity();
	Collection collection = (Collection) attribute;
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
	//String joinColumnName = collection.getJoinColumnName(); // TODO: Multi columns
	//if (joinColumnName == null) {
	    // TODO: EXCEPTION
	//    return;
	//}

	EntityAccessor accessor = getEntityAccessor(entity);
 	EntityAccessor joinAccessor = getEntityAccessor(joinEntity);
 	
	/////////////////////////////////////////////////////////////////////////////////
	String[] excludeAttributes = new String[] {joinAttributeName};
	
	Criteria criteria = new Criteria();
	EntityConfig entityConfig = entityContext.getEntityConfig();
	FetchMode fetchMode = entityConfig == null ? null : entityConfig.getFetchMode();
	LoadMode loadMode = entityConfig == null ? null : entityConfig.getLoadMode();
	QueryInput queryInput = createQueryInput(criteria, fetchMode, loadMode);
	queryInput.setExcludeAttributes(excludeAttributes);
	
	// Create query without generate SQL
	SelectTemplate template = createSelectTemplate(joinEntity, queryInput, false);
	
	template.addFilter(joinAttribute, template.getEntityContext()); // Add parent filter
	
	// Generate SQL after added parent filter
	StringBuffer buf = new StringBuffer();
	getGenerator().generateSelectSQL(template, buf);
	String sql = buf.toString();
	if (isEmpty(sql)) {
	    throw new RuntimeException("Error generate Query. SQL is empty");
	}
	template.setText(sql);
	///////////////////////////////////////////////////////////////////////////////////
	
	
	Query query = new Query(template);
	
	Attribute[] keys = entity.getGlobalDetailKeys(); // Parent keys
	TypeValue[] keyTypeValues = null;
	
	try {
	    // Prepare statement
	    query.prepareStatement(cn);
	    int count = dataList.length;
	    for (int i = 0; i < count; i++) {
		
		List<Object> innerList = null;
		Object obj = dataList[i];
		if (obj != null) {
		    keyTypeValues = EntityHelper.getKeyTypeValues(accessor, keys, obj); // Get parent key values
		    query.setParameters(keyTypeValues); // Set parent parameters

		    // Execute query
		    innerList = query.getListResult();
		}
		
		doPostProcess(cn, template.getEntityContext(), toArray(innerList));

		int innerCount = innerList == null ? 0 : innerList.size();
		if (innerCount == 0) {
		    continue;
		}
		for (int j = 0; j < innerCount; j++) {
		    Object inner = innerList.get(j);
		    
		    // Set parent to child
		    joinAccessor.setValue(inner, joinAttribute, obj);
		    
		}
		accessor.setCollectionValue(obj, collection, innerList);
	    }

	} finally {
	    query.close();
	}
	
    }
    
    
    
    
}
