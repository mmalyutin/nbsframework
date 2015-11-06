package org.plazmaforge.framework.ext.service.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.plazmaforge.framework.erm.EntityManager;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.erm.sql.SQLEntityManager;
import org.plazmaforge.framework.sql.ConnectionHolder;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public abstract class AbstractDao<E, PK extends Serializable> extends JdbcDaoSupport {

    protected abstract Class<E> getEntityClass();
    
    private EntityManager entityManager;
    
    public EntityManager getEntityManager() {
	if (entityManager == null) {
	    entityManager = new SQLEntityManager(new ERMConnectionHolder());
	}
        return entityManager;
    }
    
    class ERMConnectionHolder implements ConnectionHolder {
	public Connection getConnection() throws SQLException {
	    return getConnection();
	}
    }


    public PK create(E entity) {
	return (PK) getEntityManager().insert(entity);
    }

    public void store(E entity) {
	getEntityManager().update(entity);
	
    }

    public void remove(E entity) {
	getEntityManager().delete(entity);
	
    }

    public E findById(PK id) {
	return getEntityManager().findById(getEntityClass(), id);
    }

    public List<E> findAll() {
	return getEntityManager().findAll(getEntityClass());
    }
    
    public List<E> findByCriteria(Criteria criteria) {
   	return getEntityManager().findAll(getEntityClass(), criteria);
    }
    
   

}

