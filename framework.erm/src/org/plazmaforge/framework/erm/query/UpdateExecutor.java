package org.plazmaforge.framework.erm.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.erm.EntityAccessor;
import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.event.EventType;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.mapping.EntityType;
import org.plazmaforge.framework.erm.mapping.Key;
import org.plazmaforge.framework.sql.dialect.Dialect;

public class UpdateExecutor extends ModifyExecutor {

    public UpdateExecutor(Dialect dialect, EntityManager entityManager) {
	super(dialect, entityManager);
    }
    
    protected QueryType getType() {
	return QueryType.UPDATE;
    }


    public List<Query> createUpdateQueries(Entity entity) {
 	Entity[] entities = entity.getHierarchy();
 	Attribute[] attributes = null;
 	Key key = null;
 	Entity currEntity = null;
 	Entity nextEntity = null;
 	
 	List<Query> queries = null;
 	int count = entities.length;
 	Query query = null;
 	queries = new ArrayList<Query>();

 	// Create queries
 	for (int i = 0; i < count; i++) {
 	    currEntity = entities[i];
 	    if (!currEntity.hasOwnTable()){
 		throw new RuntimeException("Invalid hierarchy");
 	    }
 	    key = currEntity.getGlobalKey();
 	    // 2012-07-14
 	    attributes = QueryTemplate.getAttributes(currEntity, QueryType.UPDATE);
 	    // if (!keys.isAutoKey()) {
 	    // TODO: find key in attributes
 	    // If not found then add global key to attributes
 	    // }
 	    int next = i + 1;
 	    if (next < count) {
 		nextEntity = entities[next];
 		if (EntityType.SubclassEntity.equals(nextEntity.getEntityType())) {
 		   // 2012-07-14
 		   attributes = add(attributes, QueryTemplate.getAttributes(nextEntity, QueryType.UPDATE));
 		   i++;
 		   currEntity = nextEntity;
 		}
 	    }
 	    query = createUpdateQuery(currEntity, attributes, key);
 	    queries.add(query);
 	}

 	return queries;
     }
     
   
	    
     public void executeUpdateQueries(Connection cn, Entity entity, List<Query> queries, Object obj) throws SQLException {
 	int count = queries.size();
 	Query query = null;
 	
 	// PRE EVENTS
 	doValidators(entity, obj);
 	doFireEvents(cn, entity, obj, EventType.BeforeUpdate);
 	doExecuteTriggers(cn, entity, obj, EventType.BeforeUpdate);

 	for (int i = 0; i < count; i++) {
 	    query = queries.get(i);
 	    executeUpdateQuery(cn, query, obj);
 	}

 	// POST EVENTS
 	doFireEvents(cn, entity, obj, EventType.AfterUpdate);
 	doExecuteTriggers(cn, entity, obj, EventType.AfterUpdate);
 	
     }
     
     public Query createUpdateQuery(Entity entity, Attribute[] attributes, Key key) {
 	UpdateTemplate template = createUpdateTemplate(entity, attributes, key);
 	Query query = new Query(template);
 	return query;
     }
     
     public void executeUpdateQuery(Connection cn, Query query, Object obj) throws SQLException {
 	
 	checkConnection(cn);
 	if (obj == null) {
 	    throw new RuntimeException("Can not insert object. Oject is null");
 	}
 	
 	UpdateTemplate template = (UpdateTemplate) query.getTemplate();
 	Entity entity = template.getEntity();
 	
 	// PRE PROCESS
 	
 	//doValidators(entity, obj);
 	//doFireEvents(cn, entity, obj, EventType.BeforeUpdate);
 	//doExecuteTriggers(cn, entity, obj, EventType.BeforeUpdate);
 	
 	
 	EntityAccessor accessor = getEntityAccessor(entity);
 	Key key = template.getQueryInput().getKey();
 	Attribute[] keys = key.getDetailAttributes();
 	TypeValue[] keyTypeValues = EntityHelper.getKeyTypeValues(accessor, keys, obj);
 	if (keyTypeValues == null || keyTypeValues.length == 0) {
 	    throw new RuntimeException("Key values are empty");
 	}
 	List<ColumnDef> columns = template.getQueryColumns(); // GET COLUMNS
 	TypeValue[] typeValues = query.getTypeValues(entity, accessor, columns, obj); // GET TYPES AND VALUES FROM OBJECT
 	
 	int position = query.setParameters(typeValues); // SET VALUES
 	query.setParameters(keyTypeValues, position);   // SET KEYS
 	
 	int count = query.executeUpdate();
 	
 	// TODO: STUB: RECYCLE DELETE ERROR
 	if (count < 1) {
 	    throw new RuntimeException("Can not update object");
 	}
 	
 	// POST PROCESS
 	doPostProcess(cn, template.getEntityContext(), obj);
 	
 	//doFireEvents(cn, entity, obj, EventType.AfterUpdate);
 	//doExecuteTriggers(cn, entity, obj, EventType.AfterUpdate);

     }
     
     protected void doPostProcess(Connection cn, EntityContext entityContext, Object obj) throws SQLException {
 	super.doPostProcess(cn, entityContext, obj);
     }
     
     ////////////////////////////////////////////////////////////////////////////////
     
     protected UpdateTemplate createUpdateTemplate(Entity entity, Attribute[] attributes, Key key) {
 	QueryInput queryInput = createQueryInput();
 	queryInput.setAttributes(attributes);
 	queryInput.setKey(key);
 	return createUpdateTemplate(entity, queryInput);
     }

     protected UpdateTemplate createUpdateTemplate(Entity entity, QueryInput queryInput) {
 	UpdateTemplate template = getBuilder().createUpdateTemplate(entity, queryInput);
 	checkTemplate(template);
 	checkColumns(template);
 	StringBuffer buf = new StringBuffer();
 	getGenerator().generateUpdateSQL(template, buf);
 	String sql = buf.toString();
 	checkSQL(sql);
 	template.setText(sql);
 	return template;
     }     
}
