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

/**
 * 
 * @author ohapon
 *
 */
public class GXTCheckBoxAdapter extends GXTControlAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	com.sencha.gxt.widget.core.client.form.CheckBox xCheckBox = new com.sencha.gxt.widget.core.client.form.CheckBox();
	CheckBox checkBox = (CheckBox) element;
	String text = asSafeString(checkBox.getText());
	xCheckBox.setBoxLabel(text); // Set empty box label because we have NullPointerException if set box label late.
	addChild(getContent(parent.getDelegate()), xCheckBox, element);  // Add to parent
	return xCheckBox;
    }

    protected com.sencha.gxt.widget.core.client.form.CheckBox getCheckBox(Object delegate) {
	return (com.sencha.gxt.widget.core.client.form.CheckBox) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	com.sencha.gxt.widget.core.client.form.CheckBox xCheckBox = getCheckBox(element.getDelegate());
	if (xCheckBox == null) {
	    return;
	}
	if (CheckBox.PROPERTY_TEXT.equals(name)) {
	    xCheckBox.setBoxLabel(asSafeString(value));
	    return;
	} else if (CheckBox.PROPERTY_VALUE.equals(name)) {
	    xCheckBox.setValue(booleanValue(value));
	    return;
	}
	super.setProperty(element, name, value);
    }


    @Override
    public Object getProperty(UIObject element, String name) {
	com.sencha.gxt.widget.core.client.form.CheckBox xCheckBox = getCheckBox(element.getDelegate());
	if (xCheckBox == null) {
	    return null;
	}
	if (CheckBox.PROPERTY_VALUE.equals(name)) {
	    return xCheckBox.getValue();
	}
	return super.getProperty(element, name);
    }

    
    @Override
    protected void addSelectionListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	//TODO: Not implemented
	//component.addListener(com.sencha.gxt.ui.client.event.Events.Select, createListener(listener));
    }

    @Override
    protected void removeSelectionListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
	//TODO: Not implemented
	//component.removeListener(com.sencha.gxt.ui.client.event.Events.Select, getListener(listener));
    }
    
    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	com.sencha.gxt.widget.core.client.form.CheckBox xCheckBox = getCheckBox(element.getDelegate());
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
	com.sencha.gxt.widget.core.client.form.CheckBox xCheckBox = getCheckBox(element.getDelegate());
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
