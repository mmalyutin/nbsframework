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

package org.plazmaforge.framework.uwt.gxt.adapter.viewer;

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTHelper;
import org.plazmaforge.framework.uwt.gxt.data.Model;

/**
 * 
 * @author ohapon
 *
 */
public class XValueProvider implements com.sencha.gxt.core.client.ValueProvider<Model, Object> {

    /**
     * Property of bean
     */
    private String property;
    
    /**
     * Real bean PropertyProvider
     */
    private PropertyProvider propertyProvider;
    
    /**
     * Real bean ValueProvider
     */
    private ValueProvider valueProvider;
    
    
    public XValueProvider() {
	super();
    }

    public XValueProvider(String property) {
	super();
	this.property = property;
    }
    
    public XValueProvider(String property, PropertyProvider propertyProvider) {
 	super();
 	this.property = property;
 	this.propertyProvider = propertyProvider;
    }

    public XValueProvider(String property, ValueProvider valueProvider) {
  	super();
  	this.property = property;
  	this.valueProvider = valueProvider;
    }
    
    public XValueProvider(ValueProvider valueProvider) {
  	super();
  	this.valueProvider = valueProvider;
    }
    
    public XValueProvider(String property, PropertyProvider propertyProvider, ValueProvider valueProvider) {
	super();
	this.property = property;
	this.propertyProvider = propertyProvider;
	this.valueProvider = valueProvider;
    }
    
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public PropertyProvider getPropertyProvider() {
        return propertyProvider;
    }

    public void setPropertyProvider(PropertyProvider propertyProvider) {
        this.propertyProvider = propertyProvider;
    }

    public ValueProvider getValueProvider() {
        return valueProvider;
    }

    public void setValueProvider(ValueProvider valueProvider) {
        this.valueProvider = valueProvider;
    }

    ////
        
    @Override
    public Object getValue(Model item) {
	
	Object bean = null;
	
	// Check ValueProvider
	if (valueProvider != null) {
	    bean = getBean(item);
	    return valueProvider.getValue(bean);
	}
	
	// Check PropertyProvider
	if (propertyProvider != null) {
	    bean = getBean(item);
	    return propertyProvider.getValue(bean, property);
	}
	
	return item.get(property == null ? "toString" : property);
    }

    @Override
    public void setValue(Model item, Object value) {
	
	Object bean = null;
	
	// Check ValueProvider
	if (valueProvider != null) {
	    bean = getBean(item);
	    valueProvider.setValue(bean, value);
	    return;
	}
	
	// Check PropertyProvider
	if (propertyProvider != null) {
	    bean = getBean(item);
	    propertyProvider.setValue(bean, property, value);
	    return;
	}
	
	item.set(property, value);
    }

    @Override
    public String getPath() {
	return null;
    }

    protected Object getBean(Model item) {
 	return GXTHelper.getBean(item);
    }    
}
