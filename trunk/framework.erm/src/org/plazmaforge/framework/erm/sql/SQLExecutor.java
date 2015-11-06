package org.plazmaforge.framework.erm.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.ErrorHandler;
import org.plazmaforge.framework.erm.FetchMode;
import org.plazmaforge.framework.erm.LoadMode;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.erm.query.DeleteExecutor;
import org.plazmaforge.framework.erm.query.InsertExecutor;
import org.plazmaforge.framework.erm.query.Query;
import org.plazmaforge.framework.erm.query.QueryInput;
import org.plazmaforge.framework.erm.query.SelectExecutor;
import org.plazmaforge.framework.erm.query.UpdateExecutor;
import org.plazmaforge.framework.sql.dialect.Dialect;
import org.plazmaforge.framework.util.StringUtils;


public class SQLExecutor  {

    private Dialect dialect;
    
    private Configuration configuration;
    
    // Optional
    private EntityManager entityManager;
    
    public SQLExecutor(Dialect dialect) {
	this.dialect = dialect;
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
    
    public Dialect getDialect() {
	return dialect;
    }
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
	if (configuration != null) {
	    return configuration;
	}
	return entityManager == null ? null : entityManager.getConfiguration();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////
    //// LOAD BY ID
    ////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public <T> T loadById(Connection cn, Class<T> entityClass, Serializable id) throws SQLException {
	return loadById(cn, entityClass, id, null/*getConfigLoadMode()*/);
    }
    
    public <T> T loadById(Connection cn, Class<T> entityClass, Serializable id, LoadMode loadMode) throws SQLException {
	checkConnection(cn);
	if (id == null) {
	    return null;
	}
	Entity entity = getEntityByClass(entityClass);
	return loadById(cn, entity, id, loadMode);
    }

    
    protected <T> T loadById(Connection cn, Entity entity, Serializable id) throws SQLException {
	return loadById(cn, entity, id, null/*getConfigLoadMode()*/);
    }
    
    protected <T> T loadById(Connection cn, Entity entity, Serializable id, LoadMode loadMode) throws SQLException {
	checkConnection(cn);
	if (id == null) {
	    return null;
	}
	SelectExecutor executor = new SelectExecutor(getDialect(), getEntityManager());
	Query query = executor.createLoadQuery(entity, createQueryInput(null, null, loadMode));
	return executor.executeLoadQuery(cn, query, id);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////
    //// LOAD BY CRITERIA
    ////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public <T> T load(Connection cn, Class<T> entityClass, Criteria criteria) throws SQLException {
	return load(cn, entityClass, criteria, null/*getConfigLoadMode()*/);
    }
    
    public <T> T load(Connection cn, Class<T> entityClass, Criteria criteria, LoadMode loadMode) throws SQLException {
	checkConnection(cn);
	Entity entity = getEntityByClass(entityClass);
	return load(cn, entity, criteria, loadMode);
    }
    
    protected <T> T load(Connection cn, Entity entity, Criteria criteria, LoadMode loadMode) throws SQLException {
	checkConnection(cn);
	SelectExecutor executor = new SelectExecutor(getDialect(), getEntityManager());
	QueryInput queryInput = createQueryInput(criteria, null, loadMode);
	queryInput.setLazyReference(false);
	queryInput.setLazyCollection(false);
	Query query = executor.createSelectQuery(entity, queryInput);
	List<T> entities = executor.executeSelectQuery(cn, query);
	if (entities == null || entities.isEmpty()) {
	    return null;
	}
	return entities.get(0);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////
    //// FINF BY ID
    ////    
    //////////////////////////////////////////////////////////////////////////////////////////////////////    

    public <T> T findById(Connection cn, Class<T> entityClass, Serializable id) throws SQLException {
	T obj = loadById(cn, entityClass, id);
	if (obj == null) {
	    throw new RuntimeException("Object not found");
	}
	return obj;
    }
    
    public <T> T findById(Connection cn, Class<T> entityClass, Serializable id, LoadMode loadMode) throws SQLException {
	T obj = loadById(cn, entityClass, id, loadMode);
	if (obj == null) {
	    throw new RuntimeException("Object not found");
	}
	return obj;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////
    //// FIND BY CRITERIA
    ////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public <T> T find(Connection cn, Class<T> entityClass, Criteria criteria) throws SQLException {
	T obj = load(cn, entityClass, criteria);
	if (obj == null) {
	    throw new RuntimeException("Object not found");
	}
	return obj;
    }
    
    public <T> T find(Connection cn, Class<T> entityClass, Criteria criteria, LoadMode loadMode) throws SQLException {
	T obj = load(cn, entityClass, criteria, loadMode);
	if (obj == null) {
	    throw new RuntimeException("Object not found");
	}
	return obj;
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////
    //// SELECT BY CRITERIA
    ////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public <T> List<T> findAll(Connection cn, Class<T> entityClass) throws SQLException {
	return findAll(cn, entityClass, null, null /*getConfigLoadMode()*/);
    }
    
    public <T> List<T> findAll(Connection cn, Class<T> entityClass, LoadMode loadMode) throws SQLException {
	return findAll(cn, entityClass, null, loadMode);
    }
    
    public <T> List<T> findAll(Connection cn, Class<T> entityClass, Criteria criteria) throws SQLException {
	return findAll(cn, entityClass, criteria, null/*getConfigLoadMode()*/);
    }
    
    public <T> List<T> findAll(Connection cn, Class<T> entityClass, Criteria criteria, LoadMode loadMode) throws SQLException {
	return findAll(cn, entityClass, criteria, loadMode, null);
    }
    
    public <T> List<T> findAll(Connection cn, Class<T> entityClass, Criteria criteria, LoadMode loadMode, String hint) throws SQLException {
	checkConnection(cn);
	boolean force = (LoadMode.FORCE == loadMode);
	Entity entity = force ? getEntityByClass(entityClass) : getHeaderEntityByClass(entityClass);
	SelectExecutor executor = new SelectExecutor(getDialect(), getEntityManager());
	QueryInput queryInput = createQueryInput(criteria, null, loadMode);
	if (force) {
	    queryInput.setLazyReference(false);
	    queryInput.setLazyCollection(false);
	}
	populateQueryInput(queryInput, hint);
	Query query = executor.createSelectQuery(entity, queryInput);
	return executor.executeSelectQuery(cn, query);
    }

    ////
    
    /**
     * Populate QueryInput by hint
     * @param queryInput
     * @param hint
     */
    protected void populateQueryInput(QueryInput queryInput, String hint) {
	if (hint == null) {
	    return;
	}

	String[] attributes = StringUtils.split(hint, ",");
	if (attributes == null || attributes.length == 0) {
	    return;
	}

	List<String> includes = new ArrayList<String>();
	List<String> excludes = new ArrayList<String>();

	for (String attribute : attributes) {
	    attribute = attribute.trim();
	    int len = attribute.length();
	    if (attribute.startsWith("-")) {
		if (len == 1) {
		    // IGNORE: May be error
		    continue;
		}
		excludes.add(attribute.substring(1));
	    } else if (attribute.startsWith("+")) {
		if (len == 1) {
		    // IGNORE: May be error
		    continue;
		}
		excludes.add(attribute.substring(1));
	    } else {
		includes.add(attribute);
	    }
	}

	if (!includes.isEmpty()) {
	    queryInput.setIncludeAttributes(includes.toArray(new String[0]));
	}
	if (!excludes.isEmpty()) {
	    queryInput.setExcludeAttributes(excludes.toArray(new String[0]));
	}
    }
    
    ////
    
    public Serializable insert(Connection cn, Object obj) throws SQLException {
	checkConnection(cn);
	if (obj == null) {
	    throw new RuntimeException("Can not insert object. Oject is null");
	}
	Entity entity = getEntityByObject(obj);
	return insert(cn, entity, obj);
    }
    
    protected Serializable insert(Connection cn, Entity entity, Object obj) throws SQLException {
	checkConnection(cn);
	if (obj == null) {
	    throw new RuntimeException("Can not insert object. Oject is null");
	}
	Serializable id = null;
	List<Query> queries = null;
	InsertExecutor executor = new InsertExecutor(getDialect(), getEntityManager()); 
	try {
	    
	    // Create queries
	    queries = executor.createInsertQueries(entity);

	    // Prepare statements
 	    executor.prepareStatements(cn, queries);
 	    
	    // Execute queries
	    id = executor.executeInsertQueries(cn, entity, queries, obj);
	    

	} finally {
	    executor.close(queries);
	}
	return id;
    }
 
   
    public void update(Connection cn, Object obj) throws SQLException {
 	checkConnection(cn);
 	if (obj == null) {
 	    throw new RuntimeException("Can not insert object. Oject is null");
 	}
 	Entity entity = getEntityByObject(obj);
 	update(cn, entity, obj);
    }

    protected void update(Connection cn, Entity entity, Object obj) throws SQLException {
 	checkConnection(cn);
 	if (obj == null) {
 	    throw new RuntimeException("Can not insert object. Oject is null");
 	}
 	List<Query> queries = null;
 	UpdateExecutor executor = new UpdateExecutor(getDialect(), getEntityManager()); 
 	try {
 	    // Create queries
 	    queries = executor.createUpdateQueries(entity);
 	    
 	    // Prepare statements
 	    executor.prepareStatements(cn, queries);
 	    
 	    // Execute queries
 	    executor.executeUpdateQueries(cn, entity, queries, obj);
 	} finally {
 	   executor.close(queries);
 	}
    }

    public void save(Connection cn, Object obj) throws SQLException {
 	checkConnection(cn);
 	if (obj == null) {
 	    throw new RuntimeException("Can not save object. Oject is null");
 	}
 	Entity entity = getEntityByObject(obj);
	boolean isUnsaved = EntityHelper.isUnsaved(entity, obj);
	if (isUnsaved) {
	    insert(cn, entity, obj); // INSERT
	} else {
	    update(cn, entity, obj); // UPDATE
	}
    }

    public void delete(Connection cn, Object obj) throws SQLException {
 	checkConnection(cn);
 	if (obj == null) {
 	    throw new RuntimeException("Can not delete object. Oject is null");
 	}
 	Entity entity = getEntityByObject(obj);
 	delete(cn, entity, obj);
    }
    
    protected void delete(Connection cn, Entity entity, Object obj) throws SQLException {
 	checkConnection(cn);
 	if (obj == null) {
 	    throw new RuntimeException("Can not delete object. Oject is null");
 	}
 	List<Query> queries = null;
 	DeleteExecutor executor = new DeleteExecutor(getDialect(), getEntityManager()); 
 	try {
 	    // Create queries
 	    queries = executor.createDeleteQueries(entity);
 	    
 	    // Prepare statements
 	    executor.prepareStatements(cn, queries);
 	    
 	    // Execute queries
 	    executor.executeDeleteQueries(cn, entity, queries, obj);
 	} finally {
 	   executor.close(queries);
 	}
    }

    public void deleteById(Connection cn, Class<?> entityClass, Serializable id) throws SQLException {
 	checkConnection(cn);
 	if (id == null) {
 	    throw new RuntimeException("Can not delete object. ID is null");
 	}
 	Entity entity = getEntityByClass(entityClass);
 	
 	//TODO: WARNING !!!
 	//TODO: Must optimize delete without load more attributes
 	//TODO: May be check references (other entities, events, triggers...) and use only simple delete (DELETE FROM table WHERE ID = ?)
 	
 	Object obj = loadById(cn, entity, id);
 	if (obj == null) {
 	   throw new RuntimeException("Can not delete object. Object '" + entityClass + "' not found by ID=" + id);
 	}
 	delete(cn, entity, obj);
 	
    }

    public void deleteAllByIds(Connection cn, Class<?> entityClass, List<Serializable> ids) throws SQLException {
 	checkConnection(cn);
 	if (ids == null) {
 	    throw new RuntimeException("Can not delete objects. IDs is null");
 	}
 	Entity entity = getEntityByClass(entityClass);

 	//TODO: WARNING !!!
 	//TODO: Must optimize delete without load more attributes
 	//TODO: May be check references (other entities, events, triggers...) and use only simple delete (DELETE FROM table WHERE ID = ?)

 	for (Serializable id: ids) {
 	    if (id == null) {
 		throw new RuntimeException("Can not delete object. ID is null");
 	    }
	    Object obj = loadById(cn, entity, id);
	    if (obj == null) {
		throw new RuntimeException("Can not delete object. Object '" + entityClass + "' not found by ID=" + id);
	    }
	    delete(cn, entity, obj);
 	}
    }
    
    
    
    
   
    protected Entity getEntityByIdentifier(String identifier) {
	Entity entity = getConfiguration().getEntityByIdentifier(identifier);
	if (entity == null) {
	    ErrorHandler.handleEntityNotMapped(identifier);
	}
	return entity;
    }

    protected Entity getEntityByClass(Class<?> entityClass) {
	return getEntityByClass(entityClass, true);
    }
	
    protected Entity getEntityByClass(Class<?> entityClass, boolean checkNotFound) {
	Entity entity = getConfiguration().getEntityByClass(entityClass);
	if (entity == null && checkNotFound) {
	    ErrorHandler.handleEntityNotMapped(entityClass);
	}
	return entity;
    }

    protected Entity getHeaderEntityByClass(Class<?> entityClass) {
	Entity entity = getEntityByClass(entityClass, true);
	String headerClassName = entity.getHeaderClassName();
	if (headerClassName == null) {
	    // Header class is not set 
	    return entity;
	}
	Entity headerEntity = getEntityByClass(entityClass, false);
	if (headerEntity == null) {
	    // Header entity not found. TODO: May be throw exception ?
	    return entity;
	}
	return headerEntity;
    }

    protected Entity getEntityByObject(Object obj) {
	Class<?> entityClass = obj.getClass();
	return getEntityByClass(entityClass);
    }

    protected void checkConnection(Connection cn) throws SQLException {
	if (cn == null) {
	    throw new RuntimeException("Connection is null");
	}
	if (cn.isClosed()) {
	    throw new RuntimeException("Connection is closed");
	}
	
    }
    protected void checkSQL(String sql) {
	if (sql == null || sql.length() == 0) {
	    throw new RuntimeException("Error generate SQL. Query is empty");
	}
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    
//    private FetchMode getConfigFetchMode() {
//	Configuration configuration = getConfiguration();
//	return configuration == null ? Configuration.DEFAULT_FETCH_MODE : configuration.getFetchMode(); 
//
//    }
    
//    private LoadMode getConfigLoadMode() {
//	Configuration configuration = getConfiguration();
//	return configuration == null ? Configuration.DEFAULT_LOAD_MODE : configuration.getLoadMode();
//    }
    
    ////

    
}
