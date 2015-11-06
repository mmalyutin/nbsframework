/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package org.plazmaforge.framework.ext.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.exception.ApplicationException;
import org.plazmaforge.framework.core.exception.DAOException;



/**
 * 
 * Entity Service
 * 
 * @author ohapon
 *
 */
public interface EntityService<E, PK extends Serializable> extends Service {
   
    
    /**
     * Create entity
     * 
     * @param entity
     * @return
     * @throws DAOException
     * @throws CreateException
     */
    PK create(E entity) throws DAOException;
    
    
    /**
     * Store entity
     * @param entity
     * @throws DAOException
     */
    void store(E entity) throws DAOException;
    
    
    /**
     * Remove entity
     * @param entity
     * @throws DAOException
     */
    void remove(E entity) throws DAOException;
    
    
    /**
     * Remove entity by id
     * @param id
     * @throws DAOException
     */
    void removeById(PK id) throws DAOException;
    
    
    /**
     * Find entity by id
     * @param id
     * @return
     * @throws DAOException
     */
    
    E findById(PK id) throws DAOException;
    
    
    /**
     * Find header of entity by id
     * @param id
     * @return
     * @throws DAOException
     */
    Object findHeaderById(PK id) throws DAOException;
    
    
    /**
     * Find all entity
     * 
     * @return
     * @throws DAOException
     */
    List<E> findAll() throws DAOException;
	
	
    /**
     * Find entities by criteria
     * @param criteria
     * @return
     * @throws DAOException
     */
    List<E> findByCriteria(Criteria criteria) throws DAOException;
    
    
    
    List<E> findAll(Criteria criteria) throws DAOException;
    
	
    /**
     * Prepare children before store
     * @param children
     */
    void prepareChildren(Collection children);
    
    /**
     * Update collection
     * @param collection
     * @throws ApplicationException
     */
    void updateCollection(Collection collection) throws ApplicationException;
    
    /**
     * Synchronize collection
     * @param newCollection
     * @param oldIds
     * @throws ApplicationException
     */
    void synchronizeCollection(Collection newCollection, List<PK> oldIds) throws ApplicationException;
    
}
