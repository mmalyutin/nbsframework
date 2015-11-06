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

import org.plazmaforge.framework.core.data.path.TypeResolver;
import org.plazmaforge.framework.core.exception.ApplicationException;

/**
 * <code>ServiceLocator</code>
 * 
 * @author ohapon
 * 
 */
public interface ServiceLocator {

    /**
     * Get service by class name
     * 
     * @param className
     * @return
     */
    Object getServiceByClass(String className);

    /**
     * Get service by interface class
     */
    Object getService(Class<?> serviceInterface);

    /**
     * Initialize locator
     * 
     * @throws ApplicationException
     */
    void init() throws ApplicationException;

    /**
     * Set property
     * 
     * @param key
     * @param value
     */
    void setProperty(String key, Object value);

    /**
     * Get property by key
     * 
     * @param key
     * @return
     */
    Object getProperty(String key);
    
    
    TypeResolver getTypeResolver();

    void setTypeResolver(TypeResolver typeResolver);

}
