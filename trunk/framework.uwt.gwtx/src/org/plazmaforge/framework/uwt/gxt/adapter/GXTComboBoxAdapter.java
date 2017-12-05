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
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.XLabelProvider;
import org.plazmaforge.framework.uwt.gxt.data.ModelData;
import org.plazmaforge.framework.uwt.gxt.widget.XComboBox;
import org.plazmaforge.framework.uwt.widget.ComboBox;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.ListStore;

/**
 * 
 * @author ohapon
 *
 */
public class GXTComboBoxAdapter extends GXTViewerAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	ComboBox<?> comboBox = (ComboBox<?>) element; 
	
	ListStore<ModelData> store = createXDefaultListStore();
	XComboBox xComboBox = new XComboBox(store, createXLabelProvider(comboBox.getDisplayProperty(), comboBox.getPropertyProvider()));

	xComboBox.setTypeAhead(true);
	xComboBox.setTriggerAction(TriggerAction.ALL); // Important to correct selection a item by click DOWN button
	
	addChild(getContent(parent.getDelegate()), xComboBox, element);  // Add to parent
	return xComboBox;
    }

    protected XComboBox asCheckBox(Object delegate) {
	return (XComboBox) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	ComboBox<?> comboBox = (ComboBox<?>) element; 
	XComboBox xComboBox = asCheckBox(element.getDelegate());
	if (xComboBox == null) {
	    return;
	}
	if (ComboBox.PROPERTY_TEXT.equals(name)) {
	    
	    //DISABLE:MIGRATION
	    //xComboBox.setRawValue(getSafeString(value));
	    
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
	    List<?> dataList = (List<?>) value;
	    
	    // Populate ListStore by flat DataList
	    ListStore<ModelData> store = xComboBox.getStore();
	    store.clear();
	    
	    populateListStore2(comboBox, dataList, store);
	    
	    return;
	} else if (ComboBox.PROPERTY_DISPLAY_PROPERTY.equals(name)) {
	    XLabelProvider xLabelProvider = (XLabelProvider) xComboBox.getLabelProvider();
	    xLabelProvider.setProperty((String) value);
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }
    
    
    @Override
    public Object getProperty(UIObject element, String name) {
	
	XComboBox xComboBox = asCheckBox(element.getDelegate());
	if (xComboBox == null) {
	    return null;
	}
	if (ComboBox.PROPERTY_TEXT.equals(name)) {
	    
	    //DISABLE:MIGRATION
	    //return xComboBox.getRawValue();
	    return null;
	    
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
    protected void addSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// GWT Selection (item)
	xWidget.addHandler(createModelSelectionListener(widget, listener), 
		com.google.gwt.event.logical.shared.SelectionEvent.getType());
    }

    @Override
    protected void removeSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.Select, getListener(widget, listener)); //TODO
    }    
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	XComboBox xComboBox = asCheckBox(element.getDelegate());
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
	XComboBox xComboBox = asCheckBox(element.getDelegate());
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
