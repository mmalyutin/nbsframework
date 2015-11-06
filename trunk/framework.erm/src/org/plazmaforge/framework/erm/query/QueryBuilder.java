package org.plazmaforge.framework.erm.query;

import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.ErrorHandler;
import org.plazmaforge.framework.erm.FetchMode;
import org.plazmaforge.framework.erm.LoadMode;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.Key;

public class QueryBuilder {

    private Configuration configuration;
    
    public QueryBuilder(Configuration configuration) {
	if (configuration == null) {
	    throw new RuntimeException("Configuration must be not null");
	}
	this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }



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
    
    protected QueryInput createQueryInput() {
	return createQueryInput(null, null, null);
    }
    
    
    public SelectTemplate createLoadTemplate(Entity entity) {
	return createLoadTemplate(entity, createQueryInput());
    }
    
    public SelectTemplate createLoadTemplate(Entity entity, QueryInput queryInput) {
	
	// Check
	checkEntity(entity);
	checkQueryInput(queryInput);
	
	// Create QueryTemplate
	SelectTemplate template = new SelectTemplate(getConfiguration());
	template.setType(QueryType.LOAD);
	template.setQueryInput(queryInput);
	
	// Create EntityConfig
	EntityConfig entityConfig = new EntityConfig();
	
	//2012-7-16
	entityConfig.configure(queryInput);

	//entityConfig.setFetchMode(queryInput.getFetchMode());
	//entityConfig.setLoadMode(queryInput.getLoadMode());
	//entityConfig.setLazyReference(queryInput.isLazyReference() == null ? false : queryInput.isLazyReference());
	//entityConfig.setLazyCollection(queryInput.isLazyCollection() == null ? false : queryInput.isLazyCollection());
	
	
	template.populateTemplate(entity, entityConfig);
	EntityContext entityContext = template.getEntityContext();
	
	// Add keys. Use keys of the entity
	Attribute[] keys = entity.getGlobalDetailKeys();
	if (isEmpty(keys)) {
	    ErrorHandler.handleEntityError(entity, "Create 'LoadTemplate' error: Can't load entity without key");
	    //throw new RuntimeException("Create 'LoadTemplate' error: Can't load entity without key");
	}
	for (Attribute key: keys) {
	    template.addFilter(key, entityContext);
	}
	
	template.prepare();
	return template;
    }

    public SelectTemplate createSelectTemplate(Entity entity) {
	return createSelectTemplate(entity, createQueryInput());
    }

    public SelectTemplate createSelectTemplate(Entity entity, QueryInput queryInput) {
	return createSelectTemplate(entity, queryInput, QueryType.SELECT);
    }

    
    public SelectTemplate createSelectTemplate(Entity entity, QueryInput queryInput, QueryType queryType) {
	checkEntity(entity);
	
	SelectTemplate template = new SelectTemplate(getConfiguration());
	template.setType(queryType);
	template.setQueryInput(queryInput);
	
	// Create EntityConfig
	EntityConfig entityConfig = new EntityConfig();
	
	//2012-7-16
	if (queryInput.isLazyCollection() == null) {
	    queryInput.setLazyCollection(true);
	}
	entityConfig.configure(queryInput);

	//entityConfig.setFetchMode(queryInput.getFetchMode());
	//entityConfig.setLoadMode(queryInput.getLoadMode());
	//entityConfig.setLazyReference(queryInput.isLazyReference() == null ? false : queryInput.isLazyReference());
	//entityConfig.setLazyCollection(queryInput.isLazyCollection() == null ? true : queryInput.isLazyCollection());


	template.populateTemplate(entity, entityConfig);
	
	template.prepare();
	return template;
    }

    /**
     * Return Query Template to insert one entity
     * @param entity
     * @return
     */
    public InsertTemplate createInsertTemplate(Entity entity) {
	
	checkEntity(entity);
	
	Attribute[] attributes = QueryTemplate.getAttributes(entity, QueryType.INSERT);
	QueryInput queryInput = createQueryInput();
	queryInput.setAttributes(attributes);
	
	return createInsertTemplate(entity, queryInput);
    }

    
    public InsertTemplate createInsertTemplate(Entity entity, QueryInput queryInput) {
	checkEntity(entity);
	checkQueryInput(queryInput);
	
	InsertTemplate template = new InsertTemplate(getConfiguration());
	template.setQueryInput(queryInput);
	
	TableDef table = template.addTable(entity);
	
	EntityContext context = new EntityContext(entity, table);
	
	// Create EntityConfig
	EntityConfig entityConfig = new EntityConfig();
	
	//2012-7-16
	entityConfig.configure(queryInput);

	//entityConfig.setFetchMode(queryInput.getFetchMode());
	//entityConfig.setLoadMode(queryInput.getLoadMode());
	//entityConfig.setLazyReference(false);
	//entityConfig.setLazyCollection(false);
	
	context.setEntityConfig(entityConfig);
	template.setEntityContext(context);
		
	Attribute[] attributes = queryInput.getAttributes();
	
	template.populateTemplate(context, attributes);
	
	return template;
    }

    /**
     * Return Query Template to update one entity
     * @param entity
     * @return
     */
    public UpdateTemplate createUpdateTemplate(Entity entity) {
	
	checkEntity(entity);
	
	Key key = entity.getGlobalKey();
	Attribute[] attributes = QueryTemplate.getAttributes(entity, QueryType.UPDATE);
	QueryInput queryInput = createQueryInput();
	queryInput.setAttributes(attributes);
	queryInput.setKey(key);
	
	return createUpdateTemplate(entity, queryInput);
    }

    
    public UpdateTemplate createUpdateTemplate(Entity entity, QueryInput queryInput) {
	
	checkEntity(entity);
	checkQueryInput(queryInput);
	Attribute[] attributes = queryInput.getAttributes();
	checkAttributes(attributes);
	Key key = queryInput.getKey();
	checkKey(key);
	Attribute[] detailKeys = key.getDetailAttributes();
	checkKeys(detailKeys);
	
	UpdateTemplate template = new UpdateTemplate(getConfiguration());
	template.setQueryInput(queryInput);
	
	TableDef table = template.addTable(entity);
	
	EntityContext entityContext = new EntityContext(entity, table);

	// Create EntityConfig
	EntityConfig entityConfig = new EntityConfig();
	
	//2012-7-16
	entityConfig.configure(queryInput);

	//entityConfig.setFetchMode(queryInput.getFetchMode());
	//entityConfig.setLoadMode(queryInput.getLoadMode());
	//entityConfig.setLazyReference(false);
	//entityConfig.setLazyCollection(false);
	
	entityContext.setEntityConfig(entityConfig);
	template.setEntityContext(entityContext);
	
	template.populateTemplate(entityContext, attributes);
	
	if (isEmpty(detailKeys) ){
	    return template;
	}
	for (Attribute detailKey: detailKeys) {
	    //template.addKey(detailKey);
	    template.addFilter(detailKey, entityContext);
	}
	return template;
    }
    

    /**
     * 
     * @param entity
     * @return
     */
    public DeleteTemplate createDeleteTemplate(Entity entity) {
	
	checkEntity(entity);
	
	Key key = entity.getGlobalKey();
	QueryInput queryInput = createQueryInput();
	queryInput.setKey(key);
	
	return createDeleteTemplate(entity, queryInput);
    }

    /**
     * Return Query Template to delete one entity
     * @param entity
     * @return
     */
    public DeleteTemplate createDeleteTemplate(Entity entity, QueryInput queryInput) {
	checkEntity(entity);
	Attribute[] attributes = queryInput.getAttributes();
	
	DeleteTemplate template = new DeleteTemplate(getConfiguration());
	template.setQueryInput(queryInput);
	
	
	TableDef table = template.addTable(entity);
	EntityContext entityContext = new EntityContext(entity, table);
	
	// Create EntityConfig
	EntityConfig entityConfig = new EntityConfig();
	
	//2012-7-16
	entityConfig.configure(queryInput);
	
	//entityConfig.setFetchMode(queryInput.getFetchMode());
	//entityConfig.setLoadMode(queryInput.getLoadMode());
	//entityConfig.setLazyReference(false);
	//entityConfig.setLazyCollection(false);
	
	entityContext.setEntityConfig(entityConfig);
	
	template.setEntityContext(entityContext);
	template.populateTemplate(entityContext, attributes);
	
	// Add keys
	Attribute[] keys = entity.getDetailKeys();
	if (isEmpty(keys)) {
	    return template;
	}
	for (Attribute key : keys) {
	    template.addFilter(key, entityContext);
	}
	return template;
    }

    
    ////

    public SelectTemplate createExistsTemplate(Entity entity, Attribute filter) {
	
	checkEntity(entity);
	QueryInput queryInput = createQueryInput();
	
	//checkAttribute(attribute);
	
	Attribute[] detailFilters = filter.getDetailAttributes();
	checkKeys(detailFilters);
	
	SelectTemplate template = new SelectTemplate(getConfiguration());
	template.setQueryInput(queryInput);

	// TODO: Must use real table from TableDef[]
	TableDef table = template.addTable(entity);
	
	EntityContext context = new EntityContext(entity, table);

	// Create EntityConfig
	EntityConfig entityConfig = new EntityConfig();
	
	//2012-7-16
	entityConfig.configure(queryInput);

	//entityConfig.setFetchMode(queryInput.getFetchMode());
	//entityConfig.setLoadMode(queryInput.getLoadMode());
	//entityConfig.setLazyReference(false);
	//entityConfig.setLazyCollection(false);
	
	context.setEntityConfig(entityConfig);
	template.setEntityContext(context);
	
	if (isEmpty(detailFilters)) {
	    return template;
	}
	for (Attribute detailKey: detailFilters) {
	    template.addColumn(detailKey, context);
	    template.addFilter(filter, context);
	}
	return template;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    private void checkEntity(Entity entity) {
	if (entity == null) {
	    ErrorHandler.handleEntityError(entity, "Entity must be not null");
	}
	if (entity.getName() == null) {
	    ErrorHandler.handleEntityError(entity, "Entity Name must be not null");
	}
	if (entity.getTableName() == null && entity.hasOwnTable()) {
	    ErrorHandler.handleEntityError(entity, "Entity Table must be not null");
	}
    }
 
    private void checkKey(Attribute key) {
	if (key == null) {
	    throw new IllegalArgumentException("Key must be not null");
	}
    }

    private void checkKeys(Attribute[] keys) {
	if (keys == null) {
	    throw new IllegalArgumentException("Keys must be not null");
	}
	if (keys.length == 0) {
	    throw new IllegalArgumentException("keys must be not empty");
	}
	
    }

    private void checkAttributes(Attribute[] attributes) {
	if (attributes == null) {
	    throw new IllegalArgumentException("Attributes must be not null");
	}
	if (attributes.length == 0) {
	    throw new IllegalArgumentException("Attributes must be not empty");
	}
    }
    
    private boolean isEmpty(Attribute[] attributes) {
	return attributes == null || attributes.length == 0;
    }

    private void checkQueryInput(QueryInput queryInput) {
	if (queryInput == null) {
	    throw new IllegalArgumentException("QueryInput must be not null");
	}
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    private FetchMode getConfigFetchMode() {
//	Configuration configuration = getConfiguration();
//	return configuration == null ? Configuration.DEFAULT_FETCH_MODE : configuration.getFetchMode(); 
//    }
//    
//    private LoadMode getConfigLoadMode() {
//	//TODO: STUB
//	return Configuration.DEFAULT_LOAD_MODE;
//    }

    
}
