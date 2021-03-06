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

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;

import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class GXTToolItemAdapter extends GXTControlAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	
	
	com.extjs.gxt.ui.client.widget.toolbar.ToolBar xParent = (com.extjs.gxt.ui.client.widget.toolbar.ToolBar) parent.getDelegate();
	
	//TODO: ToolItem can be not only Button
	com.extjs.gxt.ui.client.widget.button.Button xButton = new com.extjs.gxt.ui.client.widget.button.Button();
	
	ToolItem toolItem = (ToolItem) element;
	
	// Get text
	String text = toolItem.getText();
	if (text != null) {
	    xButton.setText(text);
	}
	
	// Get icon
	AbstractImagePrototype xIcon = createImage(element, toolItem.getIcon());
	if (xIcon != null) {
	    xButton.setIcon(xIcon);
	}

	xParent.add(xButton); // Add to parent
	return xButton;
    }

    

    ////
    
    protected com.extjs.gxt.ui.client.widget.button.Button getButton(Object delegate) {
	return (com.extjs.gxt.ui.client.widget.button.Button) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	//TODO: ToolItem can be not only Button
	
	com.extjs.gxt.ui.client.widget.button.Button xButton = getButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}
	if (eq(name, ToolItem.PROPERTY_TEXT)) {
	    xButton.setText(asSafeString(value));
	    return;
	} else if (eq(name, ToolItem.PROPERTY_ICON)) {
	    AbstractImagePrototype xIcon = createImage(element, (Image) value);
	    if (xIcon != null) {
		xButton.setIcon(xIcon);
	    }
	    return;
	} else if (eq(name, ToolItem.PROPERTY_ICON_PATH)) {
	    AbstractImagePrototype xIcon = createImage(element, (String) value);
	    if (xIcon != null) {
		xButton.setIcon(xIcon);
	    }
	    return;
	} else if (eq(name, ToolItem.PROPERTY_TOOL_TIP)) {
	    xButton.setToolTip(asSafeString(value));
	    return;
	}

	
	super.setProperty(element, name, value);
	
    }

    @Override
    protected void addSelectionListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.addListener(com.extjs.gxt.ui.client.event.Events.Select, createListener(widget, listener));
    }

    @Override
    protected void removeSelectionListener(com.extjs.gxt.ui.client.widget.Component component, Widget widget, Listener listener) {
	component.removeListener(com.extjs.gxt.ui.client.event.Events.Select, getListener(widget, listener));
    }

    
    
    @Override
    public void addListener(UIElement element, String eventType, Listener listener) {
	Widget widget = (Widget) element;
	//TODO: ToolItem can be not only Button
	com.extjs.gxt.ui.client.widget.button.Button xButton = getButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    addSelectionListener(xButton, widget, listener);
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }

    @Override
    public void removeListener(UIElement element, String eventType, Listener listener) {
	Widget widget = (Widget) element;
	//TODO: ToolItem can be not only Button
	com.extjs.gxt.ui.client.widget.button.Button xButton = getButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    removeSelectionListener(xButton, widget, listener);
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }

}
