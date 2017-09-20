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
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.menu.MenuItem;

import com.google.gwt.resources.client.ImageResource;

/**
 * 
 * @author ohapon
 *
 */
public class GXTMenuItemAdapter extends GXTWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {

	com.sencha.gxt.widget.core.client.menu.Menu xMenu = (com.sencha.gxt.widget.core.client.menu.Menu) parent.getDelegate();
	com.sencha.gxt.widget.core.client.menu.MenuItem xMenuItem = new com.sencha.gxt.widget.core.client.menu.MenuItem();
	
	MenuItem menuItem = (MenuItem) element;
	
	// Get text
	String text = menuItem.getText();
	if (text != null) {
	    xMenuItem.setText(text);
	}
	
	// Get image
	ImageResource xIcon = createImage(element, menuItem.getIcon());
	if (xIcon != null) {
	    xMenuItem.setIcon(xIcon);
	}
	
	xMenu.add(xMenuItem);
	return xMenuItem;
    }

    ////
    
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	//TODO: ToolItem can be not only Button
	
	com.sencha.gxt.widget.core.client.menu.MenuItem xMenuItem = getMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}
	if (eq(name, MenuItem.PROPERTY_TEXT)) {
	    xMenuItem.setText(asSafeString(value));
	    return;
	} else if (eq(name, MenuItem.PROPERTY_ICON)) {
	    ImageResource xIcon = createImage(element, asImage(value));
	    if (xIcon != null) {
		xMenuItem.setIcon(xIcon);
	    }
	    return;
	} else if (eq(name, MenuItem.PROPERTY_ICON_PATH)) {
	    ImageResource xIcon = createImage(element, asString(value));
	    if (xIcon != null) {
		xMenuItem.setIcon(xIcon);
	    }
	    return;
	} else if (eq(name, MenuItem.PROPERTY_TOOL_TIP)) {
	    xMenuItem.setToolTip(asSafeString(value));
	    return;
	}

	
	super.setProperty(element, name, value);
	
    }    
    protected com.sencha.gxt.widget.core.client.menu.MenuItem getMenuItem(Object delegate) {
	return (com.sencha.gxt.widget.core.client.menu.MenuItem) delegate;
    }

    //DISABLE:MIGRATION
//    @Override
//    protected void addSelectionListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.addListener(com.sencha.gxt.ui.client.event.Events.Select, createListener(widget, listener));
//    }
//
//    @Override
//    protected void removeSelectionListener(com.sencha.gxt.widget.core.client.Component component, Widget widget, Listener listener) {
//	component.removeListener(com.sencha.gxt.ui.client.event.Events.Select, getListener(widget, listener));
//    }

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Widget widget = (Widget) element;
	com.sencha.gxt.widget.core.client.menu.MenuItem xMenuItem = getMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    addSelectionListener(xMenuItem, widget, listener);
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }

    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	Widget widget = (Widget) element;
	com.sencha.gxt.widget.core.client.menu.MenuItem xMenuItem = getMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    removeSelectionListener(xMenuItem, widget, listener);
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }

}
