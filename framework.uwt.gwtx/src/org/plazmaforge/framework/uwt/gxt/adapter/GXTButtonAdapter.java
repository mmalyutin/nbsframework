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
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;

import com.google.gwt.resources.client.ImageResource;

/**
 * 
 * @author ohapon
 *
 */
public class GXTButtonAdapter extends GXTControlAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	com.sencha.gxt.widget.core.client.button.TextButton xButton = new com.sencha.gxt.widget.core.client.button.TextButton();
	
	Button button = (Button) element;
	initButton(xButton, button);
	
	addChild(getContent(parent.getDelegate()), xButton, element); // Add to parent
	return xButton;
    }

    protected void initButton(com.sencha.gxt.widget.core.client.button.CellButtonBase<?> xButton, Button button) {
	// Get text
	String text = button.getText();
	if (text != null) {
	    xButton.setText(text);
	}

	// Get icon
	ImageResource xIcon = createImage(button, button.getIcon());
	if (xIcon != null) {
	    xButton.setIcon(xIcon);
	}
    }
    

    ////
    
    protected com.sencha.gxt.widget.core.client.button.CellButtonBase<?> asButton(Object delegate) {
	return (com.sencha.gxt.widget.core.client.button.CellButtonBase<?>) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.sencha.gxt.widget.core.client.button.CellButtonBase<?> xButton = asButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}
	if (eq(name, Button.PROPERTY_TEXT)) {
	    xButton.setText(asSafeString(value));
	    return;
	} else if (eq(name, Button.PROPERTY_ICON)) {
	    ImageResource xIcon = createImage(element, asImage(value));
	    if (xIcon != null) {
		xButton.setIcon(xIcon);
	    }
	    return;
	} else if (eq(name, Button.PROPERTY_ICON_PATH)) {
	    ImageResource xIcon = createImage(element, asString(value));
	    if (xIcon != null) {
		xButton.setIcon(xIcon);
	    }
	    return;
	}

	
	super.setProperty(element, name, value);
	
    }
    

    @Override
    protected void addSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// GXT Selection (context)
	xWidget.addHandler(createSelectionListener(widget, listener), com.sencha.gxt.widget.core.client.event.SelectEvent.getType());
    }

    @Override
    protected void removeSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//xWidget.removeListener(com.sencha.gxt.ui.client.event.Events.Select, getListener(widget, listener)); //TODO
    }

    
    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	com.sencha.gxt.widget.core.client.button.CellButtonBase<?> xButton = asButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    addSelectionListener(xButton, control, listener);
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	com.sencha.gxt.widget.core.client.button.CellButtonBase<?> xButton = asButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    removeSelectionListener(xButton, control, listener);
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }
}
