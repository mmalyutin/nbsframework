package org.plazmaforge.framework.ext.service.impl;

import java.io.Serializable;

import org.plazmaforge.framework.ext.service.GenericEntityService;


public class GenericEntityServiceImpl<E, PK extends Serializable> extends AbstractEntityService<E, Serializable> implements GenericEntityService<E, Serializable> {

    private Class<E> entityClass;

    public Class<E> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }
    
}
