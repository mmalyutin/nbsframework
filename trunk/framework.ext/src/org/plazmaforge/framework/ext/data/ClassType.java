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

package org.plazmaforge.framework.ext.data;

import org.plazmaforge.framework.util.ClassUtils;


/**
 * Container of class and class name.
 * We can use class name without class
 * Lazy initialization of class (only in geType method)
 * 
 * @author ohapon
 *
 */
public class ClassType {

    
    /**
     * Class of type
     */
    private Class<?> type;
    
    
    /**
     * Name of class of type
     */
    private String name;

    
    /**
     * The special flag to indicate create class error
     */
    private boolean createClassError;
    
    
    
    public Class<?> getType() {

	// If class is null and 'CreateClassError' is false then recreate class 
	if (type == null && !createClassError) {
	    type = createClass();
	}
	
        return type;
    }
    
    
    public void setType(Class<?> type) {
        doSetClass(type);
        doSetClassName(null); // Reset ClassName
    }

    public String getName() {
	if (type == null) {
	    return name;
	}
	return type.getName();
        
    }

    public void setName(String name) {
	
	String normalizeClassName = normalizeClassName(name);
	if (normalizeClassName == null) {
	    doSetClass(null);
	    doSetClassName(null);
	    return;
	}
	
	if (this.type == null) {
	    doSetClassName(normalizeClassName);
	    return;
	}
	
	if (this.type.getName().equals(normalizeClassName)) {
	    doSetClassName(null);
	    return;
	}
	
	doSetClass(null);
	doSetClassName(normalizeClassName);
    }
    
    
    protected void doSetClass(Class<?> klass) {
        this.type = klass;
    }
    
    protected void doSetClassName(String className) {
	this.name = className;
	this.createClassError = false; // Clear CreateClassError flag
    }
    
    
    /**
     * Return normalize name of class
     * @param className
     * @return
     */
    protected String normalizeClassName(String className) {
	if (className == null) {
	    return null;
	}
	String normalizeClassName = className.trim();
	if (normalizeClassName.length() == 0) {
	    return null;
	}
	return normalizeClassName;
    }
    

    /**
     * Create class of type by <code>ClassName</code>
     * @return
     */
    protected Class<?> createClass() {

	if (name == null) {
	    return null;
	}

	try {
	    createClassError = false;
	    return ClassUtils.getClass(name);
	} catch (Exception ex) {
	    createClassError = true;
	}
	return null;
    }    
    
    
    public boolean isEmpty() {
	return type == null && name == null;
    }

    public boolean isCreateClassError() {
	return createClassError;
    }
}

