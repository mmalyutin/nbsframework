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

import com.sun.javafx.property.PropertyReference;
import com.sun.javafx.scene.control.Logging;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import sun.util.logging.PlatformLogger;
import sun.util.logging.PlatformLogger.Level;

/**
 * 
 * @author ohapon
 *
 * @param <S>
 * @param <T>
 */
public abstract class XAbstractTreeCellFactory<T>  implements XTreeCellFactory<T> {

    private final String property;

    private Class<?> columnClass;
    private String previousProperty;
    private PropertyReference<T> propertyRef;
    
    //
    private PropertyProvider propertyProvider;
    
    private ValueProvider valueProvider;
    
    
    public XAbstractTreeCellFactory(String property) {
	this.property = property;
    }
    
    public XAbstractTreeCellFactory(String property, PropertyProvider propertyProvider, ValueProvider valueProvider) {
	this.property = property;
	this.propertyProvider = propertyProvider;
	this.valueProvider = valueProvider;
    }

    public String getProperty() {
        return property;
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
    
    @Override
    public TreeCell<T> call(TreeView<T> param) {
        return new TreeCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    //TODO
                    T value = getItemValue(item);
                    setText(formatValue(value));
                }
            }
        };
    }

 
    
    ////
    
    private ObservableValue<T> callValue(T rowData) {
        if (getProperty() == null || getProperty().isEmpty() || rowData == null) return null;

        try {
            if (columnClass == null || previousProperty == null ||
                    ! columnClass.equals(rowData.getClass()) ||
                    ! previousProperty.equals(getProperty())) {

                // create a new PropertyReference
                this.columnClass = rowData.getClass();
                this.previousProperty = getProperty();
                this.propertyRef = new PropertyReference<T>(rowData.getClass(), getProperty());
            }

            if (propertyRef.hasProperty()) {
                return propertyRef.getProperty(rowData);
            } else {
                T value = propertyRef.get(rowData);
                return new ReadOnlyObjectWrapper<T>(value);
            }
        } catch (IllegalStateException e) {
            // log the warning and move on
            final PlatformLogger logger = Logging.getControlsLogger();
            if (logger.isLoggable(Level.WARNING)) {
               logger.finest("Can not retrieve property '" + getProperty() +
                        "' in PropertyValueFactory: " + this +
                        " with provided class type: " + rowData.getClass(), e);
            }
        }

        return null;
    }  
    
    ////
 
   
    public T getItemValue(T bean) {
	
	// Check ValueProvider
	if (valueProvider != null) {
	    return (T) valueProvider.getValue(bean);
	}
	
	// Check PropertyProvider
	if (propertyProvider != null) {
	    return (T) propertyProvider.getValue(bean, getProperty());
	}
	ObservableValue<T> v = callValue(bean);
        return v == null ? null : v.getValue();
    }
    
    ////
    protected abstract String formatValue(T value);
}
