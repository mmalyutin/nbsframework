/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.core.data.provider;

import java.io.Serializable;

import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.data.Callback;

public interface ModelProviderAsync<T> extends CriteriaProviderAsync<T> {

    /**
     * Create data
     * @param data
     * @return ID of data
     */
     void create(T data, Callback<Serializable> callback);
    
    /**
     * Remove data
     * @param data
     */
    void remove(T data, Callback<?> callback);
    
    
    /**
     * Remove data by ID
     * @param id
     */
    void removeById(Serializable id, Callback<?> callback);
    
    
    /**
     * Update data
     * @param data
     */
    void update(T data, Callback<?> callback);
    
    
    /**
     * Load data by ID 
     * Return data
     */
    void getById(Serializable id, Callback<T> callback);
    
    /**
     * Load data by criteria 
     * @param criteria
     * Return data
     */
    void get(Criteria criteria, Callback<T> callback);
    
    
    /**
     * Load new instance of data
     * @return new instance
     */
    void instance(Callback<T> callback);

}
