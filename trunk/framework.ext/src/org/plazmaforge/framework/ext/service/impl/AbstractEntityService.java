package org.plazmaforge.framework.ext.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.plazmaforge.framework.core.exception.ApplicationException;
import org.plazmaforge.framework.core.exception.DAOException;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.ext.model.IEntityObject;
import org.plazmaforge.framework.ext.service.EntityService;

public abstract class AbstractEntityService<E, PK extends Serializable> extends AbstractService implements EntityService<E, PK> {

    protected abstract Class<E> getEntityClass();
    
    protected Class<E> getEntityHeaderClass() {
	return getEntityClass();
    }
    

    @Override
    public void setInfo(String info)  {
    }
    
    @Override
    public String getInfo() {
	return null;
    }

    @Override
    public PK create(E entity) throws DAOException {
	try {
	    logCallMethod("create(E)");
	    
	    onPreSave(entity);
	    onPreInsert(entity);

	    PK id = (PK) getEntityManager().insert(entity);

	    onPostSave(entity);
	    onPostInsert(entity);
	    return id;
	} catch (Throwable e) {
	    logError("Service error", e);
	    throw new DAOException(e);
	}
    }

    @Override
    public void store(E entity) throws DAOException {
	try {
	    logCallMethod("store(E)");
	    
	    onPreSave(entity);
	    onPreUpdate(entity);

	    getEntityManager().update(entity);

	    onPostSave(entity);
	    onPostUpdate(entity);
	} catch (Throwable e) {
	    logError("Service error", e);
	    throw new DAOException(e);
	}
    }

    @Override
    public void remove(E entity) throws DAOException {

	try {
	    logCallMethod("remove(E)");

	    onPreDelete(entity);

	    getEntityManager().delete(entity);

	    onPostDelete(entity);
	} catch (Throwable e) {
	    logError("Service error", e);
	    throw new DAOException(e);
	}
    }
    
    @Override
    public void removeById(PK id) throws DAOException {
	try {
	    logCallMethod("removeById(PK)");

	    onPreDelete(id);

	    getEntityManager().deleteById(getEntityClass(), id);

	    onPostDelete(id);
	} catch (Throwable e) {
	    logError("Service error", e);
	    throw new DAOException(e);
	}
    }

    @Override
    public E findById(PK id) throws DAOException {
	try {
	    logCallMethod("findById(PK)");
	    
	    onPreLoad(null);

	    E entity = getEntityManager().findById(getEntityClass(), id);

	    onPostLoad(entity);
	    return entity;
	} catch (Throwable e) {
	    logError("Service error", e);
	    throw new DAOException(e);
	}
    }
    
    @Override
    public Object findHeaderById(PK id) throws DAOException {
	try {
	    logCallMethod("findHeaderById(PK)");
	    return getEntityManager().findById(getEntityHeaderClass(), id);
	} catch (Throwable e) {
	    logError("Service error", e);
	    throw new DAOException(e);
	}
    }

    @Override
    public List<E> findAll() throws DAOException {
	try {
	    logCallMethod("findAll()");
	    return getEntityManager().findAll(getEntityHeaderClass(), createERMCriteria());    
	} catch (Throwable e) {
	    logError("Service error", e);
	    throw new DAOException(e);
	}
    }
	
    @Override
    public List<E> findByCriteria(Criteria criteria) throws DAOException {
	try {
	    logCallMethod("findByCriteria(Criteria)");
	    prepareERMCriteria(criteria);
	    return getEntityManager().findAll(getEntityHeaderClass(), criteria);
	} catch (Throwable e) {
	    logError("Service error", e);
	    throw new DAOException(e);
	}
    }

    @Override
    public List<E> findAll(Criteria criteria) throws DAOException {
	try {
	    logCallMethod("findAll(Criteria)");
	    prepareERMCriteria(criteria);
	    return getEntityManager().findAll(getEntityHeaderClass(), criteria);
	} catch (Throwable e) {
	    logError("Service error", e);
	    throw new DAOException(e);
	}
    }

    protected String formatMethod(String method) {
	if (method == null) {
	    return "";
	}
	Class<?> entityClass = getEntityClass();
	if (entityClass == null) {
	    return method;
	}
	return entityClass.getSimpleName() + "::" + method;
    }
    
    protected String formatCallMethod(String method) {
	return "Call method '" + formatMethod(method) + "'";
    }
    
    protected void logCallMethod(String method) {
	log(formatCallMethod(method));
    }
	
    protected Criteria createERMCriteria() {
	Criteria criteria = new Criteria();
	prepareERMCriteria(criteria);
	return criteria;
    }
    
    protected void prepareERMCriteria(Criteria criteria) {
	
    }
    
    
    @Override
    public void prepareChildren(Collection children) {
	// TODO Auto-generated method stub
    }

    @Override
    public void updateCollection(Collection collection) throws ApplicationException {
   	if (collection == null) {
   	    return;
   	}
   	for (Object obj : collection) {
   	    if (!(obj instanceof IEntityObject)) {
   		continue;
   	    }
   	    IEntityObject entity = (IEntityObject) obj;
   	    if (entity.getId() == null || entity.isCreated()) {
   		create((E) entity);
   	    } else {
   		if (entity.isDeleted()) {
   		    remove((E) entity);
   		} else {
   		    store((E) entity);
   		}
   	    }
   	}
       }    

    @Override
    public void synchronizeCollection(Collection newCollection, List<PK> oldIds) throws ApplicationException {
	if (newCollection != null) {
	    // Update new collection
	    updateCollection(newCollection);
	}

	// Synchronize new and old collections
	if (oldIds == null) {
	    return;
	}

	for (Serializable oldId : oldIds) {
	    if (oldId == null) {
		continue;
	    }
	    boolean found = false;
	    if (newCollection != null) {
		for (Object newObject : newCollection) {
		    if (!(newObject instanceof IEntityObject)) {
			continue;
		    }
		    IEntityObject newEntity = (IEntityObject) newObject;
		    Serializable newId = newEntity.getId();
		    if (oldId.equals(newId)) {
			found = true;
		    }
		}
	    }

	    if (!found) {
		removeById((PK) oldId);
	    }
	}

    }
    
    protected E getCastEntity(Object entity) {
	return (E) entity;
    }
    
    ///////////////////////////////////////////////////////////////

    protected void onPreLoad(Object entity) {
	
    }

    protected void onPostLoad(Object entity) {
	
    }

    ////
    
    protected void onPreInsert(Object entity) {
	
    }

    protected void onPostInsert(Object entity) {
	
    }

    ////
    
    protected void onPreUpdate(Object entity) {
	
    }

    protected void onPostUpdate(Object entity) {
	
    }

    ////
    
    protected void onPreSave(Object entity) {
	
    }

    protected void onPostSave(Object entity) {
	
    }
    
    ////
    
    protected void onPreDelete(Object entity) {
	
    }
    
    protected void onPostDelete(Object entity) {
	
    }

}
