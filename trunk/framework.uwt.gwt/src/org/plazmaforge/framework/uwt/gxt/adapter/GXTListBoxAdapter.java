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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.IField;
import org.plazmaforge.framework.uwt.widget.ListBox;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;

import com.extjs.gxt.ui.client.data.ModelData;

public class GXTListBoxAdapter extends GXTViewerAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	com.extjs.gxt.ui.client.store.ListStore<ModelData> store = new  com.extjs.gxt.ui.client.store.ListStore<ModelData>();
	com.extjs.gxt.ui.client.widget.ListView<ModelData> xListBox = new  com.extjs.gxt.ui.client.widget.ListView<ModelData>(store);
	xListBox.setDisplayProperty("toString");
	xListBox.setWidth(IField.DEFAULT_WIDTH); // TODO
	xListBox.setHeight(IField.DEFAULT_LIST_HEIGHT); // TODO
	
	//xListBox.setAutoWidth(true);
	//xListBox.setAutoHeight(true);
        //xListBox.getView().setForceFit(true);
        
	addToParent(getContent(parent.getDelegate()), xListBox, element); // Add to parent
	
	return xListBox;
    }

    protected  com.extjs.gxt.ui.client.widget.ListView<ModelData> getListBox(Object delegate) {
	return (com.extjs.gxt.ui.client.widget.ListView<ModelData>) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	ListBox listBox = (ListBox) element;
	
	com.extjs.gxt.ui.client.widget.ListView<ModelData> xListBox = getListBox(element.getDelegate());
	if (xListBox == null) {
	    return;
	}
	if (ListBox.PROPERTY_VALUE.equals(name)) {
	    ModelData model = findModelByBean(listBox, xListBox.getStore(), value);
	    List<ModelData> selection = new ArrayList<ModelData>();
	    if (model != null) {
		selection.add(model);
	    }
	    xListBox.getSelectionModel().setSelection(selection);
	    return;
	}  else if (ListBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    
	    //ModelData model = findModelByIndex(xListBox.getStore(), intValue(value));
	    xListBox.getSelectionModel().select(intValue(value), true);
	    return;
	} if (ListBox.PROPERTY_LAYOUT.equals(name)) {
	    // ignore layout
	    return;
	} else if (ListBox.PROPERTY_LINES_VISIBLE.equals(name)) {
	    // TODO: Only column lines. Must implement row lines
	    return;
	} else if (ListBox.PROPERTY_HEADER_VISIBLE.equals(name)) {
	    // TODO:
	    return;
	} else if (ListBox.PROPERTY_DATA_LIST.equals(name)) {
	    
	    // Get DataList
	    List dataList = (List) value;
	    
	    // Populate ListStore by flat DataList
	    com.extjs.gxt.ui.client.store.ListStore<ModelData> store = xListBox.getStore();
	    store.removeAll();
	    
	    populateListStore(listBox, dataList, store);
	    
	    return;
	} else if (ListBox.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    xListBox.setDisplayProperty(getSafeString(value));
	    return;
	}

	super.setProperty(element, name, value);
    }
    
    @Override
    public Object getProperty(UIObject element, String name) {
	
	com.extjs.gxt.ui.client.widget.ListView<ModelData> xListBox = getListBox(element.getDelegate());
	if (xListBox == null) {
	    return null;
	}
	if (ListBox.PROPERTY_VALUE.equals(name)) {
	    ModelData model = xListBox.getSelectionModel().getSelectedItem();
	    Object value = getBean(model);
	    return value;
	} else if (ListBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    ModelData model = xListBox.getSelectionModel().getSelectedItem();
	    if (model == null) {
		return -1;
	    }
	    return xListBox.getStore().indexOf(model);
	}
	
	return super.getProperty(element, name);
	
    }
    
    
    @Override
    protected void addSelectionListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	((com.extjs.gxt.ui.client.widget.ListView<ModelData>) component).getSelectionModel().addListener(com.extjs.gxt.ui.client.event.Events.SelectionChange, createListener(widget, listener));
    }

    @Override
    protected void removeSelectionListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	((com.extjs.gxt.ui.client.widget.ListView<ModelData>) component).getSelectionModel().removeListener(com.extjs.gxt.ui.client.event.Events.SelectionChange, getListener(widget, listener));
    }
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	com.extjs.gxt.ui.client.widget.ListView<ModelData> xListBox = getListBox(element.getDelegate());
	if (xListBox == null) {
	    return;
	}
	
	if (eq(Events.Selection, eventType)) {
	    addSelectionListener(xListBox, control, listener);
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    addEnterListener(xListBox, control, listener);
	    return;
	}  
	
	super.addListener(element, eventType, listener);
    }

    
    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	com.extjs.gxt.ui.client.widget.ListView<ModelData> xListBox = getListBox(element.getDelegate());
	if (xListBox == null) {
	    return;
	}
	
	if (eq(Events.Selection, eventType)) {
	    removeSelectionListener(xListBox, control, listener);
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    removeEnterListener(xListBox, control, listener);
	    return;
	}  
	
	super.removeListener(element, eventType, listener);
    }

}
