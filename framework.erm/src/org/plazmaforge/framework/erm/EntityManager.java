package org.plazmaforge.framework.erm;

import java.io.Serializable;
import java.util.List;

import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.sql.dialect.Dialect;

/**
 * EntityManager - General interface to manage entities
 * 
 * - loadById/load methods to load single entity by ID or criteria/parameters.
 * - findById/find methods to load single entity by ID or criteria/parameters and throws EntityNotFound exception if not found.
 * - findAll methods to load  entities by criteria/parameters.
 * - insert method to insert entity.
 * - update method to update entity.
 * - save method to insert or update entity.
 * - delete method to delete entity.
 * - deleteById method to delete entity by ID.
 * - deleteAllByIds method to delete entities by IDs.
 * 
 * @author ohapon
 *
 */

public interface EntityManager {

    
    /**
     * Return SQL dialect
     * 
     * @return
     */
    Dialect getDialect();
    
    /**
     * Set SQL dialect
     * @param dialect
     */
    void setDialect(Dialect dialect);
    
    
    
    /**
     * Return Configuration
     */
    Configuration getConfiguration();

    /**
     * Set Configuration
     * 
     * @param configuration
     */
    void setConfiguration(Configuration configuration);
    
    
    
    /**
     * Load entity by ID
     * @param entityClass
     * @param id
     * @return
     */
    <T> T loadById(Class<T> entityClass, Serializable id);

    /**
     * Load entity by criteria
     * @param entityClass
     * @param criteria
     * @return
     */
    <T> T load(Class<T> entityClass, Criteria criteria);

    /**
     * Load entity by parameter
     * @param entityClass
     * @param parameter
     * @param value
     * @return
     */
    <T> T load(Class<T> entityClass, String parameter, Object value);
    
    /**
     * Load entity by parameters
     * @param entityClass
     * @param parameters
     * @param values
     * @return
     */
    <T> T load(Class<T> entityClass, String[] parameters, Object[] values);

    
    ////
    

    /**
     * Find entity by ID
     * @param entityClass
     * @param id
     * @return
     */
    <T> T findById(Class<T> entityClass, Serializable id);

    /**
     * Find entity by criteria
     * @param entityClass
     * @param criteria
     * @return
     */
    <T> T find(Class<T> entityClass, Criteria criteria);

    /**
     * Find entity by parameter
     * @param entityClass
     * @param parameter
     * @param value
     * @return
     */
    <T> T find(Class<T> entityClass, String parameter, Object value);
    
    /**
     * Find entity by parameters
     * @param entityClass
     * @param parameters
     * @param values
     * @return
     */
    <T> T find(Class<T> entityClass, String[] parameters, Object[] values);    
    
    ////
    
    
    /**
     * Find all entities
     * @param entityClass
     * @return
     */
    <T> List<T> findAll(Class<T> entityClass);
    
    /**
     * Find all entities by criteria
     * @param entityClass
     * @param criteria
     * @return
     */
    <T> List<T> findAll(Class<T> entityClass, Criteria criteria);

    
    /**
     * Find all entities by criteria
     * @param entityClass
     * @param criteria
     * @param loadMode
     * @return
     */
    <T> List<T> findAll(Class<T> entityClass, Criteria criteria, LoadMode loadMode);
    
    
    /**
     * Find all entities by criteria and hint
     * @param entityClass
     * @param criteria
     * @param loadMode
     * @param hint
     * @return
     */
    <T> List<T> findAll(Class<T> entityClass, Criteria criteria, LoadMode loadMode, String hint);
    
    /**
     * Find all entities by parameter
     * @param entityClass
     * @param parameter
     * @param value
     * @return
     */
    <T> List<T> findAll(Class<T> entityClass, String parameter, Object value);
    
    /**
     * Find all entities by parameters
     * @param entityClass
     * @param parameters
     * @param values
     * @return
     */
    <T> List<T> findAll(Class<T> entityClass, String[] parameters, Object[] values);

    
    
    /**
     * Insert new entity
     * @param obj
     * @return
     */
    Serializable insert(Object obj);

    /**
     * Update entity
     * @param obj
     */
    void update(Object obj);

    /**
     * Save (insert or update) entity
     * @param obj
     */
    void save(Object obj);

    /**
     * Delete entity
     * @param obj
     */
    void delete(Object obj);

    /**
     * Delete entity by ID
     * @param entityClass
     * @param id
     */
    void deleteById(Class<?> entityClass, Serializable id);
    
    
    /**
     * Delete entities by IDs
     * @param entityClass
     * @param id
     */
    void deleteAllByIds(Class<?> entityClass, List<Serializable> ids);    

}
