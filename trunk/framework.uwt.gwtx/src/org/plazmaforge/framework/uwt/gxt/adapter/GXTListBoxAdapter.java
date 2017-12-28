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
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.XValueProvider;
import org.plazmaforge.framework.uwt.gxt.data.Model;
import org.plazmaforge.framework.uwt.gxt.widget.XListBox;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.IField;
import org.plazmaforge.framework.uwt.widget.ListBox;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;

import com.sencha.gxt.data.shared.ListStore;

/**
 * 
 * @author ohapon
 *
 */
public class GXTListBoxAdapter extends GXTViewerAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	ListBox<?> listBox = (ListBox<?>) element;
	
	ListStore<Model> store = createXDefaultListStore();
	XValueProvider xValueProvider = createXValueProvider(listBox.getDisplayProperty(), listBox.getPropertyProvider());
	XListBox xListBox = new XListBox(store, xValueProvider);
	
	xListBox.setWidth(IField.DEFAULT_WIDTH); // TODO
	xListBox.setHeight(IField.DEFAULT_LIST_HEIGHT); // TODO
	
	//xListBox.setAutoWidth(true);
	//xListBox.setAutoHeight(true);
        //xListBox.getView().setForceFit(true);
        
	addChild(getContent(parent.getDelegate()), xListBox, element); // Add to parent
	
	return xListBox;
    }

    protected XListBox asListBox(Object delegate) {
	return (XListBox) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	ListBox<?> listBox = (ListBox<?>) element;
	
	XListBox xListBox = asListBox(element.getDelegate());
	if (xListBox == null) {
	    return;
	}
	if (ListBox.PROPERTY_VALUE.equals(name)) {
	    Model model = findModelByBean(listBox, xListBox.getStore(), value);
	    List<Model> selection = new ArrayList<Model>();
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
	    List<?> dataList = (List<?>) value;
	    
	    // Populate ListStore by flat DataList
	    com.sencha.gxt.data.shared.ListStore<Model> store = xListBox.getStore();
	    store.clear();
	    
	    populateListStore2(listBox, dataList, store);
	    
	    return;
	} else if (ListBox.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    XValueProvider xLabelProvider = (XValueProvider) xListBox.getValueProvider();
	    xLabelProvider.setProperty((String) value);
	    return;
	}

	super.setProperty(element, name, value);
    }
    
    @Override
    public Object getProperty(UIObject element, String name) {
	
	com.sencha.gxt.widget.core.client.ListView<Model, Object> xListBox = asListBox(element.getDelegate());
	if (xListBox == null) {
	    return null;
	}
	if (ListBox.PROPERTY_VALUE.equals(name)) {
	    Model model = xListBox.getSelectionModel().getSelectedItem();
	    Object value = getBean(model);
	    return value;
	} else if (ListBox.PROPERTY_SELECTION_INDEX.equals(name)) {
	    Model model = xListBox.getSelectionModel().getSelectedItem();
	    if (model == null) {
		return -1;
	    }
	    return xListBox.getStore().indexOf(model);
	}
	
	return super.getProperty(element, name);
	
    }
  
    @Override
    protected void addSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// GWT Selection (item)
	asListBox(xWidget).getSelectionModel().addSelectionHandler(createModelSelectionListener(widget, listener));
    }

    @Override
    protected void removeSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// ((com.sencha.gxt.widget.core.client.ListView<ModelData, Object>)
	// component).getSelectionModel().removeListener(com.sencha.gxt.ui.client.event.Events.SelectionChange,
	// getListener(widget, listener));
    }
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	com.sencha.gxt.widget.core.client.ListView<Model, Object> xListBox = asListBox(element.getDelegate());
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
	com.sencha.gxt.widget.core.client.ListView<Model, Object> xListBox = asListBox(element.getDelegate());
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
