package org.plazmaforge.framework.ext.service;

import java.io.Serializable;



public interface GenericEntityService<E, PK extends Serializable> extends EntityService<E, PK> {

    void setEntityClass(Class<E> entityClass);
    
    Class<E> getEntityClass();
}
