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

/**
 * 
 */
package org.plazmaforge.framework.core.criteria;

import java.io.Serializable;

/**
 * @author ohapon
 *
 */
public class ValueFilter extends Filter {

    private static final long serialVersionUID = 5365162906021931712L;
    

    private String propertyName;
    
    private Serializable value;
    
    private String operation;
    
    // Only for string value
    private boolean ignoreCase;
    
    
    public ValueFilter() {
	super();
    }

    public ValueFilter(String propertyName, Serializable value) {
	super();
	if (propertyName == null) {
	    throw new IllegalArgumentException("Propery must be not null");
	}
	this.propertyName = propertyName;
	this.value = value;
    }
    
    public ValueFilter(String propertyName, Serializable value, String operation) {
	this(propertyName, value);
	this.operation = operation;
    }

    public ValueFilter(String propertyName, Serializable value, String operation, boolean ignoreCase) {
	this(propertyName, value, operation);
	this.ignoreCase = ignoreCase;
    }

    public ValueFilter(String propertyName, Serializable value, boolean ignoreCase) {
	this(propertyName, value);
	this.ignoreCase = ignoreCase;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Serializable getValue() {
        return value;
    }

    public String getOperation() {
        return operation;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public String toString() {
	return propertyName + " " + getOperation() + " " + value;
    }
    
    public boolean isParameter() {
	if (isNotParameterOperation(operation)) {
	    return false;
	}
	// TODO
	return value != null;
    }

}
