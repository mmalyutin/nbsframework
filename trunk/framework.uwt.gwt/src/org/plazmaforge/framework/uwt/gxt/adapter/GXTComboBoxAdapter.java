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

package org.plazmaforge.framework.uwt.gxt.adapter;

import java.util.List;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.gxt.widget.XComboBox;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;

import com.extjs.gxt.ui.client.data.ModelData;


public class GXTComboBoxAdapter extends GXTViewerAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	com.extjs.gxt.ui.client.store.ListStore<ModelData> store = new  com.extjs.gxt.ui.client.store.ListStore<ModelData>();
	XComboBox<ModelData> xComboBox = new XComboBox<ModelData>();
	xComboBox.setDisplayField("toString");
	xComboBox.setStore(store);
	
	addToParent(getContent(parent.getDelegate()), xComboBox, element);  // Add to parent
	return xComboBox;
    }

    protected com.extjs.gxt.ui.client.widget.form.ComboBox<ModelData> getCheckBox(Object delegate) {
	return (com.extjs.gxt.ui.client.widget.form.ComboBox<ModelData>) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	ComboBox comboBox = (ComboBox) element; 
	com.extjs.gxt.ui.client.widget.form.ComboBox<ModelData> xComboBox = getCheckBox(element.getDelegate());
	if (xComboBox == null) {
	    return;
	}
	if (ComboBox.PROPERTY_TEXT.equals(name)) {
	    xComboBox.setRawValue(getSafeString(value));
	    return;
	} else if (ComboBox.PROPERTY_VALUE.equals(name)) {
	    ModelData model = findModelByBean(comboBox, xComboBox.getStore(), value);
	    xComboBox.setValue(model);
	    return;
	}  else if (ComboBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    ModelData model = findModelByIndex(xComboBox.getStore(), intValue(value));
	    xComboBox.setValue(model);
	    return;	    
	} else if (ComboBox.PROPERTY_DATA_LIST.equals(name)) {
	    
	    // Get DataList
	    List dataList = (List) value;
	    
	    // Populate ListStore by flat DataList
	    com.extjs.gxt.ui.client.store.ListStore<ModelData> store = xComboBox.getStore();
	    store.removeAll();
	    
	    populateListStore(comboBox, dataList, store);
	    
	    return;
	} else if (ComboBox.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    xComboBox.setDisplayField(getSafeString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }
    
    
    @Override
    public Object getProperty(UIObject element, String name) {
	
	com.extjs.gxt.ui.client.widget.form.ComboBox<ModelData> xComboBox = getCheckBox(element.getDelegate());
	if (xComboBox == null) {
	    return null;
	}
	if (ComboBox.PROPERTY_TEXT.equals(name)) {
	    return xComboBox.getRawValue();
	} else if (ComboBox.PROPERTY_VALUE.equals(name)) {
	    ModelData model = xComboBox.getValue();
	    Object value = getBean(model);
	    return value;
	} else if (ComboBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    ModelData model = xComboBox.getValue();
	    if (model == null) {
		return -1;
	    }
	    return xComboBox.getStore().indexOf(model);
	}
	
	return super.getProperty(element, name);
	
    }
    
    @Override
    protected void addSelectionListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.SelectionChange, createListener(widget, listener));
    }

    @Override
    protected void removeSelectionListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.SelectionChange, getListener(widget, listener));
    }
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	com.extjs.gxt.ui.client.widget.form.ComboBox<ModelData> xComboBox = getCheckBox(element.getDelegate());
	if (xComboBox == null) {
	    return;
	}
	
	if (eq(Events.Selection, eventType)) {
	    addSelectionListener(xComboBox, control, listener);
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }

    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	com.extjs.gxt.ui.client.widget.form.ComboBox<ModelData> xComboBox = getCheckBox(element.getDelegate());
	if (xComboBox == null) {
	    return;
	}
	
	if (eq(Events.Selection, eventType)) {
	    removeSelectionListener(xComboBox, control, listener);
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }

}
