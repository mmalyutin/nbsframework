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

/*
 * Created on 20.09.2005
 *
 */
package org.plazmaforge.framework.ext.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.config.configurer.ConfigurerInfo;
import org.plazmaforge.framework.config.ConfigurerService;
import org.plazmaforge.framework.config.configurer.ObjectConfigurer;
import org.plazmaforge.framework.config.object.IObjectConfig;
import org.plazmaforge.framework.core.data.path.TypeResolver;
import org.plazmaforge.framework.core.exception.ApplicationException;
import org.plazmaforge.framework.util.ClassUtils;


/**
 * <code>AbstractServiceLocator</code>
 * 
 * @author ohapon
 * 
 */
public abstract class AbstractServiceLocator implements ServiceLocator {

    /** 
     * Properties of <code>ServiceLocator</code> 
     */
    private Properties properties = new Properties();

    
    private TypeResolver typeResolver;
    
    
    private Logger logger;
    
    
    /**
     * Get service name by class
     * 
     * Example: class name - com.company.project.service.MyService service
     * name - MyService
     * 
     * @param klass
     * @return
     */
    protected String getServiceNameByClass(Class<?> klass) {
	if (klass == null) {
	    return null;
	}
	String className = klass.getName();
	return getServiceNameByClass(className);
    }
    
    protected String getServiceNameByClass(String className) {
	if (className == null) {
	    return null;
	}
	int pos = className.lastIndexOf('.');
	if (pos < 0) {
	    return className;
	}
	return className.substring(pos + 1, className.length());
    }

    protected Class<?> getLazyClass(String className) {
	return ClassUtils.getSafeClass(className);
    }

    /**
     * Set property
     */
    public void setProperty(String key, Object value) {
	properties.put(key, value);
    }

    /**
     * Get property by key
     */
    public Object getProperty(String key) {
	return properties.get(key);
    }
    
    public Object getProperty(String key, String def) {
	return properties.getProperty(key, def);
    }

    public TypeResolver getTypeResolver() {
        return typeResolver;
    }

    public void setTypeResolver(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    
    public void init() throws ApplicationException {
	// Stub
    }

    protected void loadConfigurers() throws ApplicationException {
	ConfigurerService service = (ConfigurerService) ServiceFactory.getService(ConfigurerService.class);
	if (service == null) {
	    return;
	}
	List<ConfigurerInfo<?>> l = service.loadConfigurers();
	if (l == null) {
	    return;
	}
	Iterator<ConfigurerInfo<?>> c = l.iterator();
	while (c.hasNext()) {
	    log(c.next());

	}

	// ConfigurerManager.addConfigurers(l);
	List<ObjectConfigurer<?>> k = new ArrayList<ObjectConfigurer<?>>();
	for (int i = 0; i < l.size(); i++) {
	    ConfigurerInfo<?> inf = (ConfigurerInfo<?>) l.get(i);
	    ObjectConfigurer oc = (ObjectConfigurer) ClassUtils.newSafeInstance(inf.getClassName());
	    if (oc == null) {
		//TODO: WARN
		continue;
	    }
	    oc.setName(inf.getName());
	    k.add(oc);

	    List<?> objects = inf.getObjects();
	    if (objects == null) {
		continue;
	    }

	    for (int j = 0; j < objects.size(); j++) {
		oc.addObject((IObjectConfig) objects.get(j));
	    }
	}
	// Add and replace if found
	//ConfigurerManager.addConfigurers(k, true);
    }
 
    
    protected void log(Object message) {
	getLogger().log(toMessage(message));
    }
    
    protected void log(Throwable e) {
	getLogger().error(null, e);
    }
    
    protected void log(Object message, Throwable e) {
	getLogger().error(toMessage(message), e);
    }
    
    protected String toMessage(Object message) {
	return message == null ? null : message.toString();
    }
    
    protected String formatLine(int index) {
	if (index < 10) {
	    return "00" + index;
	}
	if (index < 100) {
	    return "0" + index;
	}
	return "" + index;
    }
    
    protected Logger getLogger() {
	if (logger == null) {
	    logger = Logger.getLogger(getClass().getName());
	}
	return logger;
    }
}
