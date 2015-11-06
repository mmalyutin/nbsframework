package org.plazmaforge.framework.erm.query;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.erm.EntityAccessor;
import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.event.EventType;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Column;
import org.plazmaforge.framework.erm.mapping.Discriminator;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.mapping.EntityType;
import org.plazmaforge.framework.erm.mapping.Key;
import org.plazmaforge.framework.erm.mapping.SubclassEntity;
import org.plazmaforge.framework.sql.dialect.Dialect;

public class InsertExecutor extends ModifyExecutor {

    public InsertExecutor(Dialect dialect, EntityManager entityManager) {
	super(dialect, entityManager);
    }

    protected QueryType getType() {
	return QueryType.INSERT;
    }
    
    public List<Query> createInsertQueries(Entity entity) {
	List<Query> queries = new ArrayList<Query>();
	Entity[] entities = entity.getHierarchy();
	Attribute[] attributes = null;
	Key key = null;
	Entity currEntity = null;
	Entity nextEntity = null;
	Query query = null;
	int count = entities.length;
	for (int i = 0; i < count; i++) {
	    currEntity = entities[i];
	    key = currEntity.getGlobalKey();
	    if (key == null) {
		throw new RuntimeException("Can not insert object. Key is null. Entity=" + currEntity.getClassName());
	    }
	    // 2012-07-14
	    attributes = QueryTemplate.getAttributes(currEntity, QueryType.INSERT);
	    if (!key.isAutoKey()) {
		// TODO: find key in attributes
		// If not found then add global key to attributes
	    }
	    DiscriminatorValue discriminatorValue = null;
	    int next = i + 1;
 	    if (next < count) {
 		nextEntity = entities[next];
 		if (EntityType.SubclassEntity.equals(nextEntity.getEntityType())) {
 		  SubclassEntity subclassEntity = (SubclassEntity) nextEntity;
 		  String stringValue = subclassEntity.getDiscriminatorValue();
 		  
 		  // TODO: Bad implementation !!! Must adding all attributes of hieararchy (subclass) and insert it.  
 		  
 		  if (stringValue != null) {
 		      Discriminator discriminator = currEntity.getDiscriminator();
 		      if (discriminator == null) {
 			 throw new RuntimeException("Discriminator not found for entity: '" + currEntity + "'");
 		      }
 		      Column column = discriminator.getColumn();
 		      discriminatorValue = new DiscriminatorValue();
 		      discriminatorValue.setEntity(subclassEntity);
 		      discriminatorValue.setColumn(column);
 		      discriminatorValue.setStringValue(stringValue); 		   
 		  
 		      // 2012-07-14
 		      attributes = add(attributes, QueryTemplate.getAttributes(nextEntity, QueryType.INSERT));
 		      //i++;
 		      currEntity = nextEntity;
 		  }
 		  i++;
 		  
 		  
 		  
 		}
 	    }
	    query = createInsertQuery(currEntity, attributes, key, discriminatorValue);
	    queries.add(query);
	}
	return queries;
    }

    public Serializable executeInsertQueries(Connection cn, Entity entity, List<Query> queries, Object obj) throws SQLException {
	Query query = null;
	int count = queries.size();
	Serializable id = null;

	// PRE EVENTS
	doValidators(entity, obj);
 	doFireEvents(cn, entity, obj, EventType.BeforeInsert);
 	doExecuteTriggers(cn, entity, obj, EventType.BeforeInsert);

	for (int i = 0; i < count; i++) {
	    query = queries.get(i);
	    Serializable curId = executeInsertQuery(cn, query, obj, id, i == 0);
	    if (i == 0) {
		id = curId;
	    }
	}

	// POST EVENTS
 	doFireEvents(cn, entity, obj, EventType.AfterInsert);
 	doExecuteTriggers(cn, entity, obj, EventType.AfterInsert);

	return id;
    }

    public Query createInsertQuery(Entity entity, Attribute[] attributes, Key key) {
	return createInsertQuery(entity, attributes, key, null);
    }
	    
    public Query createInsertQuery(Entity entity, Attribute[] attributes, Key key, DiscriminatorValue discriminatorValue) {
	InsertTemplate template = createInsertTemplate(entity, attributes, key, discriminatorValue);
	Query query = new Query(template);
	return query;
    }

    public Serializable executeInsertQuery(Connection cn, Query query, Object obj, Serializable id, boolean firstInsert) throws SQLException {

	checkConnection(cn);

	if (obj == null) {
	    throw new RuntimeException("Can not insert object. Oject is null");
	}
	if (!firstInsert && id == null) {
	    throw new RuntimeException("Can not insert object. Assign key must be not null");
	}

	InsertTemplate template = (InsertTemplate) query.getTemplate();
	Entity entity = template.getEntity();
	
	// PRE PROCESS
	// doPreProcess(???)
	
	//doValidators(entity, obj);
 	//doFireEvents(cn, entity, obj, EventType.BeforeInsert);
 	//doExecuteTriggers(cn, entity, obj, EventType.BeforeInsert);
	
	
	EntityAccessor accessor = getEntityAccessor(entity);
	Key key = template.getQueryInput().getKey();
	Object[] keyValues = null;
	if (firstInsert) {
	    if (!key.isAssignKey()) {
		keyValues = generateKeyValues(cn, key);
		id = EntityHelper.convertValuesToId(entity, key, keyValues);
		if (id == null) {
		    throw new RuntimeException("Error generate ID");
		}
		accessor.setValue(obj, key, id); // Assign ID to object
	    }
	}
	List<ColumnDef> columns = template.getQueryColumns(); // GET COLUMNS
	TypeValue[] typeValues = query.getTypeValues(entity, accessor, columns, obj); // GET TYPES AND VALUES FROM OBJECT
	query.setParameters(typeValues); // SET VALUES

	int count = query.executeUpdate();
	if (count < 1) {
	    throw new RuntimeException("Can not insert object");
	}
	
	// POST PROCESS
	doPostProcess(cn, template.getEntityContext(), obj);
	
 	//doFireEvents(cn, entity, obj, EventType.AfterInsert);
 	//doExecuteTriggers(cn, entity, obj, EventType.AfterInsert);

	return id;
    }

    protected void doPostProcess(Connection cn, EntityContext entityContext, Object obj) throws SQLException {
	super.doPostProcess(cn, entityContext, obj);
    }

    protected Object[] generateKeyValues(Connection cn, Key key)  throws SQLException {
	// TODO
	Object[] values = new Object[1];
	values[0] = GeneratorHelper.generateValue(getDialect(), cn, key);
	return values;
    }
     
    ///////////////////////////////////////////////////////////////////////////////////////
    
    protected InsertTemplate createInsertTemplate(Entity entity,  Attribute[] attributes, Key key, DiscriminatorValue discriminatorValue) {
	QueryInput queryInput = createQueryInput();
	queryInput.setAttributes(attributes);
	queryInput.setKey(key);
	queryInput.setDiscriminatorValue(discriminatorValue);
	return createInsertTemplate(entity, queryInput);
    }
    
    protected InsertTemplate createInsertTemplate(Entity entity, QueryInput queryInput) {
	InsertTemplate template = getBuilder().createInsertTemplate(entity, queryInput);
	checkTemplate(template);
	checkColumns(template);
	StringBuffer buf = new StringBuffer();
	getGenerator().generateInsertSQL(template, buf);
	String sql = buf.toString();
	checkSQL(sql);
	template.setText(sql);
	return template;
    }
}
