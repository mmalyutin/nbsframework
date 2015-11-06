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

package org.plazmaforge.framework.config;

import java.util.List;

import org.apache.log4j.Logger;
import org.plazmaforge.framework.config.configurer.ObjectConfigurer;
import org.plazmaforge.framework.config.object.IConfigIdentifier;
import org.plazmaforge.framework.config.object.IObjectConfig;
import org.plazmaforge.framework.util.StringUtils;

/**
 * Abstract config factory
 * 
 * @author ohapon
 *
 */
public abstract class AbstractConfigFactory {

    protected final Logger logger = Logger.getLogger(getClass());
	    
    private ObjectConfigurer configurer;

    /**
     * Return name of configurer
     * @return
     */
    protected abstract String getConfigurerName();
    
    /**
     * Return configurer
     * @return
     */
    public ObjectConfigurer getConfigurer() {
	if (configurer == null){
	    configurer = ConfigurerManager.getConfigurer(getConfigurerName()); 
	}
   	return configurer; 
    }
    
    /**
     * Return true if str is empty
     * @param str
     * @return
     */
    protected boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }

    /**
     * Return true if identifier is empty 
     * @param identifier
     * @return
     */
    protected boolean isEmpty(IConfigIdentifier identifier) {
	if (identifier == null) {
	    return true;
	}
	return isEmpty(identifier.getConfigId());
    }

    /**
     * Get object by id
     * @param id
     * @return
     */
    protected IObjectConfig getObjectConfigById(String id) {
	if (getConfigurer() == null) {
	    return null;
	}
	return getConfigurer().getObjectById(id);
    }

    /**
     * Get object by name
     * @param id
     * @return
     */
    protected IObjectConfig getObjectConfigByName(String name) {
	if (getConfigurer() == null) {
	    return null;
	}
	return getConfigurer().getObjectByName(name);
    }

    
    /**
     * Get object by <code>ConfigIdentifier</code>
     */
    protected IObjectConfig getObjectConfig(IConfigIdentifier identifier) {
	if (identifier == null || getConfigurer() == null) {
	    return null;
	}
	return getConfigurer().getObjectByName(identifier.getConfigName());
    }

    /**
     * Return all objects of configurer
     * @return
     */
    public List getObjects() {
	ObjectConfigurer configurer = getConfigurer();
	if (configurer == null) {
	    return null;
	}
	return configurer.getObjects();
    }
}
