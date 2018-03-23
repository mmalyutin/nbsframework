/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the LicensS, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple PlacS, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */
package org.plazmaforge.framework.uwt.jfx.widget.cell;

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Table PropertyValueFactory:
 * - DataBinding only
 * 
 * @author ohapon
 *
 * @param <S>
 * @param <T>
 */
public class XPropertyValueFactory<S, T> extends PropertyValueFactory<S, T> implements XTableCellValueFactory<S, T> {

    // UWT PropertyProvider
    private PropertyProvider propertyProvider;
    
    // UWT ValueProvider
    private ValueProvider valueProvider;
    
    public XPropertyValueFactory(String property) {
	super(property);
    }
    
    public XPropertyValueFactory(String property, PropertyProvider propertyProvider, ValueProvider valueProvider) {
	super(property);
	this.propertyProvider = propertyProvider;
	this.valueProvider = valueProvider;
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
    
    protected Object getBean(CellDataFeatures<S,T> param) {
	return param.getValue();
    }

    protected ObservableValue<T> createWrapper(T value) {
	return new ReadOnlyObjectWrapper<T>(value);
    }
    
    @Override
    public ObservableValue<T> call(CellDataFeatures<S,T> param) {
	
	Object bean = null;
	
	// Check ValueProvider
	if (valueProvider != null) {
	    bean = getBean(param);
	    return createWrapper((T) valueProvider.getValue(bean));
	}
	
	// Check PropertyProvider
	if (propertyProvider != null) {
	    bean = getBean(param);
	    return createWrapper((T) propertyProvider.getValue(bean, getProperty()));
	}
	
        return super.call(param);
    }
    
}
