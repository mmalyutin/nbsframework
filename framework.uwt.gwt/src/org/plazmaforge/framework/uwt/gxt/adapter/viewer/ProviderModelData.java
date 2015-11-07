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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * ModelData width PropertyProvider
 * 
 * @author ohapon
 *
 */
public class ProviderModelData implements ModelData {

    private static final List<String> properties = new ArrayList<String>();
    
    static {
	// TODO Stub
	properties.add("text");
	properties.add("toString");
    }
    
    /**
     * Data bean
     */
    private Object bean;
    
    /**
     * Original model data
     */
    private ModelData modelData;
    
    
    /**
     * Property provider
     * getValue(T element, String property), setValue(T element, String property, Object value)
     */
    private PropertyProvider propertyProvider;

    /**
     * Map of value providers
     */
    private Map<String, ValueProvider> valueProviders;
    
    
    
    public ProviderModelData(Object bean, ModelData modelData, PropertyProvider propertyProvider, Map<String, ValueProvider> valueProviders) {
	super();
	this.bean = bean;
	this.modelData = modelData;
	this.propertyProvider = propertyProvider;
	this.valueProviders = valueProviders;
    }

    public ProviderModelData(Object bean, PropertyProvider propertyProvider) {
	this(bean, null, propertyProvider, null);
    }
    
    
    public PropertyProvider getPropertyProvider() {
        return propertyProvider;
    }

    public void setPropertyProvider(PropertyProvider propertyProvider) {
        this.propertyProvider = propertyProvider;
    }
    
    @Override
    public <X> X get(String property) {
	if (bean == null) {
	    return null;
	}
	ValueProvider valueProvider = getValueProvider(property);
	if (valueProvider != null) {
	    return toValue(valueProvider.getValue(bean));
	}
	if (propertyProvider == null) {
	    return toValue(modelData != null ? modelData.get(property) : null);
	}
	return toValue(propertyProvider.getValue(bean, property));
    }

    @Override
    public <X> X set(String property, X value) {
	if (bean == null) {
	    return null;
	}
	X oldValue = null;
	ValueProvider valueProvider = getValueProvider(property);
	if (valueProvider != null) {
	    oldValue = toValue(valueProvider.getValue(property));
	    valueProvider.setValue(bean, value);
	    return oldValue;
	}
	if (propertyProvider == null) {
	    return toValue(modelData != null ? modelData.set(property, value) : null);
	}
	oldValue = toValue(propertyProvider.getValue(bean, property));
	propertyProvider.setValue(bean, property, value);
	return oldValue;
    }

    
    @Override
    public Map<String, Object> getProperties() {
	return Collections.EMPTY_MAP;
    }

    @Override
    public Collection<String> getPropertyNames() {
	return properties;
    }

    @Override
    public <X> X remove(String property) {
	return null;
    }

    public Object getBean() {
        return bean;
    }
    
    ////
    
    protected ValueProvider getValueProvider(String property) {
	return valueProviders == null ? null : valueProviders.get(property);
    }

    protected <X> X toValue(Object value) {
	return (X) value;
    }

}
