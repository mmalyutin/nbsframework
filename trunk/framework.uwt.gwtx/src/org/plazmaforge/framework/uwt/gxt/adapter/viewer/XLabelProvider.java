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

import org.plazmaforge.framework.uwt.gxt.adapter.GXTHelper;
import org.plazmaforge.framework.uwt.gxt.data.ModelData;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;
/**
 * 
 * @author ohapon
 *
 */
public class XLabelProvider implements com.sencha.gxt.data.shared.LabelProvider<ModelData> {

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
    

    
    public XLabelProvider() {
	super();
    }

    public XLabelProvider(String property) {
	super();
	this.property = property;
    }
    
    public XLabelProvider(String property, PropertyProvider propertyProvider) {
 	super();
 	this.property = property;
 	this.propertyProvider = propertyProvider;
    }

    public XLabelProvider(String property, ValueProvider valueProvider) {
  	super();
  	this.property = property;
  	this.valueProvider = valueProvider;
    }
    
    public XLabelProvider(ValueProvider valueProvider) {
  	super();
  	this.valueProvider = valueProvider;
    }
    
    public XLabelProvider(String property, PropertyProvider propertyProvider, ValueProvider valueProvider) {
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
    public String getLabel(ModelData item) {
	if (item == null) {
	    return null;
	}
	Object bean = null;
	Object value = null;
	
	// Check ValueProvider
	if (valueProvider != null) {
	    bean = getBean(item);
	    value = valueProvider.getValue(bean);
	    return toString(value);
	}
	
	// Check PropertyProvider
	if (propertyProvider != null) {
	    bean = getBean(item);
	    value = propertyProvider.getValue(bean, property);
	    toString(value);
	}
	
	value = item.get(property == null ? "toString" : property);
	return toString(value);
    }
    
    protected String toString(Object value) {
	return value == null ? null : value.toString();
    }
    
    protected Object getBean(ModelData item) {
	return GXTHelper.getBean(item);
    }
}
