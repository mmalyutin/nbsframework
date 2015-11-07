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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.widget.CheckBox;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;


public class GXTCheckBoxAdapter extends GXTControlAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	com.extjs.gxt.ui.client.widget.form.CheckBox xCheckBox = new com.extjs.gxt.ui.client.widget.form.CheckBox();
	xCheckBox.setBoxLabel(""); // Set empty box label because we have NullPointerException if set box label late.
	addToParent(getContent(parent.getDelegate()), xCheckBox, element);  // Add to parent
	return xCheckBox;
    }

    protected com.extjs.gxt.ui.client.widget.form.CheckBox getCheckBox(Object delegate) {
	return (com.extjs.gxt.ui.client.widget.form.CheckBox) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	com.extjs.gxt.ui.client.widget.form.CheckBox xCheckBox = getCheckBox(element.getDelegate());
	if (xCheckBox == null) {
	    return;
	}
	if (CheckBox.PROPERTY_TEXT.equals(name)) {
	    xCheckBox.setBoxLabel(getSafeString(value));
	    return;
	} else if (CheckBox.PROPERTY_VALUE.equals(name)) {
	    xCheckBox.setValue(booleanValue(value));
	    return;
	}
	super.setProperty(element, name, value);
    }


    @Override
    public Object getProperty(UIObject element, String name) {
	com.extjs.gxt.ui.client.widget.form.CheckBox xCheckBox = getCheckBox(element.getDelegate());
	if (xCheckBox == null) {
	    return null;
	}
	if (CheckBox.PROPERTY_VALUE.equals(name)) {
	    return xCheckBox.getValue();
	}
	return super.getProperty(element, name);
    }

    
    @Override
    protected void addSelectionListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	//TODO: Not implemented
	//component.addListener(com.extjs.gxt.ui.client.event.Events.Select, createListener(listener));
    }

    @Override
    protected void removeSelectionListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	//TODO: Not implemented
	//component.removeListener(com.extjs.gxt.ui.client.event.Events.Select, getListener(listener));
    }
    
    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	com.extjs.gxt.ui.client.widget.form.CheckBox xCheckBox = getCheckBox(element.getDelegate());
	if (xCheckBox == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    addSelectionListener(xCheckBox, control, listener);
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	com.extjs.gxt.ui.client.widget.form.CheckBox xCheckBox = getCheckBox(element.getDelegate());
	if (xCheckBox == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    removeSelectionListener(xCheckBox, control, listener);
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }

}
