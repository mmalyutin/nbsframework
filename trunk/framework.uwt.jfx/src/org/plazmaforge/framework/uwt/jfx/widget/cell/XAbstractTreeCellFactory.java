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
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sun.util.logging.PlatformLogger;
import sun.util.logging.PlatformLogger.Level;

/**
 * Abstract TreeCellFactory:
 *  - DataBinding
 *  - CellRendering
 *  
 * @author ohapon
 *

 * @param <T>
 * @param <FV> - formated value 
 */
public abstract class XAbstractTreeCellFactory<T, FV>  implements XTreeCellFactory<T, FV> {

    // JFX Property
    private final String property;

    private Class<?> columnClass;
    
    private String previousProperty;
    
    private PropertyReference<FV> propertyRef;
    
    ////
    
    // UWT PropertyProvider
    private PropertyProvider propertyProvider;
    
    // UWT ValueProvider
    private ValueProvider valueProvider;
    
    ////
    
    private Image leafIcon;
    
    private Image nodeIcon;
    
    private Image openIcon;
    
    private Image closeIcon;
    
    
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
    
    public Image getLeafIcon() {
        return leafIcon;
    }

    public void setLeafIcon(Image leafIcon) {
        this.leafIcon = leafIcon;
    }

    public Image getNodeIcon() {
        return nodeIcon;
    }

    public void setNodeIcon(Image nodeIcon) {
        this.nodeIcon = nodeIcon;
    }

    public Image getOpenIcon() {
        return openIcon;
    }

    public void setOpenIcon(Image openIcon) {
        this.openIcon = openIcon;
    }

    public Image getCloseIcon() {
        return closeIcon;
    }

    public void setCloseIcon(Image closeIcon) {
        this.closeIcon = closeIcon;
    }

    @Override
    public TreeCell<T> call(TreeView<T> param) {
        return new TreeCell<T>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                
                if (item == null || empty) {
                    // Very important for root and virtual empty items 
                    setText(null);
                    setGraphic(null);
                    return;
                }
                
                // Get cell value
                TreeItem<T> treeItem = getTreeItem();
        	FV value = getValue(item);
        	
        	String text = getItemText(value);
        	Node graphic = getItemGraphic(treeItem, value);
        	
        	setText(text);
        	setGraphic(graphic);
      	

            }
        };
    }

 
    
    ////
    
    protected String getItemText(FV value) {
	return value == null ? null : formatValue(value);
    }
    
    protected Node getItemGraphic(TreeItem<T> item, FV value) {
	Image icon = getItemIcon(item);
	return icon == null ? null : new ImageView(icon);
    }

    protected Image getItemIcon(TreeItem<T> item) {
	if (item == null) {
	    return getItemIcon();
	}
	if (item.isLeaf()) {
	    return leafIcon;
	}
	Image icon = null;
	icon = item.isExpanded() ? openIcon : closeIcon;
	if (icon == null) {
	   icon = nodeIcon;  
	}
	return icon;
    }
    
    protected Image getItemIcon() {
 	return nodeIcon;
    }
    
    protected FV getValue(T bean) {
	
	// Check ValueProvider
	if (valueProvider != null) {
	    return (FV) valueProvider.getValue(bean);
	}
	
	// Check PropertyProvider
	if (propertyProvider != null) {
	    return (FV) propertyProvider.getValue(bean, getProperty());
	}
	ObservableValue<FV> v = callValue(bean);
        return v == null ? null : v.getValue();
    }
    
    protected ObservableValue<FV> callValue(T bean) {
        if (getProperty() == null || getProperty().isEmpty() || bean == null) return null;

        try {
            if (columnClass == null || previousProperty == null ||
                    ! columnClass.equals(bean.getClass()) ||
                    ! previousProperty.equals(getProperty())) {

                // create a new PropertyReference
                this.columnClass = bean.getClass();
                this.previousProperty = getProperty();
                this.propertyRef = new PropertyReference<FV>(bean.getClass(), getProperty());
            }

            if (propertyRef.hasProperty()) {
                return propertyRef.getProperty(bean);
            } else {
                FV value = propertyRef.get(bean);
                return new ReadOnlyObjectWrapper<FV>(value);
            }
        } catch (IllegalStateException e) {
            // log the warning and move on
            final PlatformLogger logger = Logging.getControlsLogger();
            if (logger.isLoggable(Level.WARNING)) {
               logger.finest("Can not retrieve property '" + getProperty() +
                        "' in PropertyValueFactory: " + this +
                        " with provided class type: " + bean.getClass(), e);
            }
        }

        return null;
    }  
    
    ////
    
    protected abstract String formatValue(FV value);
}
