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

package org.plazmaforge.framework.core.data.object;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * General simple meta data 
 *  
 * - No inheritance
 * - No interfaces
 * - Methods only by name (without signature)
 *  
 * @author ohapon
 *
 *
 */
public class Meta implements IMeta {

    /**
     * Name of meta data
     */
    private String name;
    
    /**
     * Properties 
     */
    private List<Property> properties;
    
    /**
     * Methods
     */
    private List<Method> methods;

    /**
     * Method invoker
     */
    private Invoker invoker;
    
    /**
     * True if need check property by name before get/set
     */
    private boolean checkProperty;
    
    /**
     * True if need check method by name before invoke
     */
    private boolean checkMethod;
    
    
    
    public Meta(String name, List<Property> properties, List<Method> methods, Invoker invoker, boolean checkProperty, boolean checkMethod) {
	super();
	this.name = name;
	this.properties = properties;
	this.methods = methods;
	this.invoker = invoker;
	this.checkProperty = checkProperty;
	this.checkMethod = checkMethod;
    }

    public Meta(String name, List<Property> properties, List<Method> methods, Invoker invoker) {
	this(name, properties, methods, invoker, false, false);
    }

    public Meta(String name, List<Property> properties, List<Method> methods) {
	this(name, properties, methods, null, false, false);
    }

    public Meta(String name, List<Property> properties) {
	this(name, properties, null, null, false, false);
    }

    ////
    
    public String getName() {
        return name;
    }
    
    public List<Property> getProperties() {
        return properties;
    }

    public List<String> getPropertyNames() {
	if (properties == null) {
	    return null;
	}
	List<String> names = new ArrayList<String>();
	for (Property property: properties) {
	    names.add(property.getName());
	}
	return names;
    }
    
    @Override
    public List<Method> getMethods() {
        return methods;
    }

    @Override
    public List<String> getMethodNames() {
	if (methods == null) {
	    return null;
	}
	List<String> names = new ArrayList<String>();
	for (Method method: methods) {
	    names.add(method.getName());
	}
	return names;
    }

    @Override
    public Property getProperty(String name) {
	if (name == null || properties == null) {
	    return null;
	}
	for (Property property : properties) {
	    if (name.equals(property.getName())) {
		return property;
	    }
	}
	return null;
    }

    @Override
    public boolean hasProperty(String name) {
	return getProperty(name) != null;
    }

    @Override
    public Method getMethod(String name) {
	if (name == null || methods == null) {
	    return null;
	}
	for (Method method : methods) {
	    if (name.equals(method.getName())) {
		return method;
	    }
	}
	return null;
    }

    @Override
    public boolean hasMethod(String name) {
	return getMethod(name) != null;
    }

    public Invoker getInvoker() {
        return invoker;
    }


    public boolean isCheckProperty() {
        return checkProperty;
    }

    public boolean isCheckMethod() {
        return checkMethod;
    }

    
    
    
}
