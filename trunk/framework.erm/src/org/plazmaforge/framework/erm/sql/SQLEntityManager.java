package org.plazmaforge.framework.erm.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.erm.ConfigurationRegister;
import org.plazmaforge.framework.erm.LoadMode;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.sql.ConnectionHolder;
import org.plazmaforge.framework.sql.dialect.Dialect;
import org.plazmaforge.framework.sql.dialect.DialectFactory;

public class SQLEntityManager implements EntityManager {

    private Configuration configuration;
    
    private ConnectionHolder connectionHolder;
    
    private Dialect dialect;
    
    private SQLExecutor executor;

    
    public SQLEntityManager() {
    }

    
    public SQLEntityManager(ConnectionHolder connectionHolder) {
	super();
	this.connectionHolder = connectionHolder;
    }


    public Configuration getConfiguration() {
	if (configuration == null) {
	    configuration = ConfigurationRegister.getConfiguration();
	}
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
	if (this.configuration != null) {
	    throw new RuntimeException("Configuration already initialize");
	}
        this.configuration = configuration;
    }

    public ConnectionHolder getConnectionHolder() {
        return connectionHolder;
    }

    public void setConnectionHolder(ConnectionHolder connectionHolder) {
	if (this.connectionHolder != null) {
	    throw new RuntimeException("connectionHolder already initialize");
	}
        this.connectionHolder = connectionHolder;
    }

    public SQLExecutor getExecutor() {
	if (executor == null) {
	    executor = new SQLExecutor(getDialect());
	    executor.setEntityManager(this);
	}
        return executor;
    }
    
    public Dialect getDialect() {
	if (dialect == null) {
	    try {
		dialect = DialectFactory.getDialect(getConnection());
	    } catch (SQLException ex) {
		throw new RuntimeException("Can't get Dialect from Connection", ex);
	    }
	}
	return dialect;
    }
    
    public void setDialect(Dialect dialect) {
	if (this.dialect != null) {
	    throw new RuntimeException("Dialect already initialize");
	}
        this.dialect = dialect;
    }

    protected Connection getConnection() throws SQLException {
	if (connectionHolder == null) {
	    throw new RuntimeException("ConnectionHolder is not set");
	}
	return connectionHolder.getConnection();
    }
    
    //////////////////////////////////////////////////////////////////////////
    

    /**
     * Load entity by ID
     * @param entityClass
     * @param id
     * @return
     */
    public <T> T loadById(Class<T> entityClass, Serializable id) {
	try {
	    return getExecutor().loadById(getConnection(), entityClass, id);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity load error by class '" + entityClass + "'", ex);
	}
    }

    /**
     * Load entity by criteria
     * @param entityClass
     * @param criteria
     * @return
     */
    public <T> T load(Class<T> entityClass, Criteria criteria) {
	try {
	    return getExecutor().load(getConnection(), entityClass, criteria);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity load error by class '" + entityClass + "'", ex);
	}
    }

    /**
     * Load entity by parameter
     * @param entityClass
     * @param parameter
     * @param value
     * @return
     */
    public <T> T load(Class<T> entityClass, String parameter, Object value) {
	Criteria criteria = Criteria.createFilterCriteria(parameter, toSerializable(value));
	return load(entityClass, criteria);
    }
    
    /**
     * Load entity by parameters
     * @param entityClass
     * @param parameters
     * @param values
     * @return
     */
    public <T> T load(Class<T> entityClass, String[] parameters, Object[] values) {
	Criteria criteria = Criteria.createFilterCriteria(parameters, toSerializable(values));
	return load(entityClass, criteria);
    }

    
    ////
    
    /**
     * Find entity by ID
     * @param entityClass
     * @param id
     * @return
     */
    public <T> T findById(Class<T> entityClass, Serializable id) {
	try {
	    return getExecutor().findById(getConnection(), entityClass, id);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity find error by class '" + entityClass + "'", ex);
	}
    }

    /**
     * Find entity by criteria
     * @param entityClass
     * @param criteria
     * @return
     */
    public <T> T find(Class<T> entityClass, Criteria criteria) {
	try {
	    return getExecutor().find(getConnection(), entityClass, criteria);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity find error by class '" + entityClass + "'", ex);
	}
    }

    /**
     * Find entity by parameter
     * @param entityClass
     * @param parameter
     * @param value
     * @return
     */
    public <T> T find(Class<T> entityClass, String parameter, Object value) {
	Criteria criteria = Criteria.createFilterCriteria(parameter, toSerializable(value));
	return find(entityClass, criteria);
    }
    
    /**
     * Find entity by parameters
     * @param entityClass
     * @param parameters
     * @param values
     * @return
     */
    public <T> T find(Class<T> entityClass, String[] parameters, Object[] values) {
	Criteria criteria = Criteria.createFilterCriteria(parameters, toSerializable(values));
	return find(entityClass, criteria);
    }
    
    ////
    
    /**
     * Select entities
     * @param entityClass
     * @return
     */
    public <T> List<T> findAll(Class<T> entityClass) {
	try {
	    return getExecutor().findAll(getConnection(), entityClass);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity select error by class '" + entityClass + "'", ex);
	}
    }

    /**
     * Select entities by criteria
     * @param entityClass
     * @param criteria
     * @return
     */
    public <T> List<T> findAll(Class<T> entityClass, Criteria criteria) {
	try {
	    return getExecutor().findAll(getConnection(), entityClass, criteria);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity select error by class '" + entityClass + "'", ex);
	}
    }
    
    /**
     * 
     * @param entityClass
     * @param criteria
     * @param loadMode
     * @return
     */
    public <T> List<T> findAll(Class<T> entityClass, Criteria criteria, LoadMode loadMode) {
	try {
	    return getExecutor().findAll(getConnection(), entityClass, criteria, loadMode);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity select error by class '" + entityClass + "'", ex);
	}
    }
    

    public <T> List<T> findAll(Class<T> entityClass, Criteria criteria, LoadMode loadMode, String hint) {
	try {
	    return getExecutor().findAll(getConnection(), entityClass, criteria, loadMode, hint);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity select error by class '" + entityClass + "'", ex);
	}
    }

    /**
     * Select entities by parameter
     * @param entityClass
     * @param parameter
     * @param value
     * @return
     */
    public <T> List<T> findAll(Class<T> entityClass, String parameter, Object value) {
	Criteria criteria = Criteria.createFilterCriteria(parameter, toSerializable(value));
	return findAll(entityClass, criteria);
    }

    /**
     * Select entities by parameters
     * @param entityClass
     * @param parameters
     * @param values
     * @return
     */
    public <T> List<T> findAll(Class<T> entityClass, String[] parameters, Object[] values) {
	Criteria criteria = Criteria.createFilterCriteria(parameters, toSerializable(values));
	return findAll(entityClass, criteria);
    }

    
    
    /**
     * Insert new entity
     * @param obj
     * @return
     */
    public Serializable insert(Object obj) {
	try {
	    return getExecutor().insert(getConnection(), obj);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity insert error", ex);
	}
    }

    /**
     * Update entity
     * @param obj
     */
    public void update(Object obj) {
	try {
	    getExecutor().update(getConnection(), obj);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity update error", ex);
	}
    }

    /**
     * Save (insert or update) entity
     * @param obj
     */
    public void save(Object obj) {
	try {
	    getExecutor().save(getConnection(), obj);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity save error", ex);
	}
    }

    /**
     * Delete entity
     * @param obj
     */
    public void delete(Object obj) {
	try {
	    getExecutor().delete(getConnection(), obj);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity delete error", ex);
	}
    }

    /**
     * Delete entity by ID
     * @param entityClass
     * @param id
     */
    public void deleteById(Class<?> entityClass, Serializable id) {
	try {
	    getExecutor().deleteById(getConnection(), entityClass, id);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entity delete error by class '" + entityClass + "'", ex);
	}
    }


    /**
     * Delete entities by IDs
     * @param entityClass
     * @param id
     */
    public void deleteAllByIds(Class<?> entityClass, List<Serializable> ids) {
	try {
	    getExecutor().deleteAllByIds(getConnection(), entityClass, ids);
	} catch (SQLException ex) {
	    throw new RuntimeException("Entities delete error by class '" + entityClass + "'", ex);
	}
    }

    
    
    
    
    
    private Serializable toSerializable(Object value) {
	return (Serializable) value;
    }
    
    private Serializable[] toSerializable(Object[] values) {
	Serializable[] serializableValues = null;
	if (values != null) {
	    serializableValues = new Serializable[values.length];
	    int i = 0;
	    for (Object value : values) {
		serializableValues[i] = (Serializable) value;
		i++;
	    }
	}
	return serializableValues;
    }

}
