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

package org.plazmaforge.framework.uwt.jfx.adapter;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.menu.MenuItem;

/**
 * 
 * @author ohapon
 *
 */
public class JFXMenuItemAdapter extends JFXWidgetAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {

	javafx.scene.control.Menu xMenu = (javafx.scene.control.Menu) parent.getDelegate();
	javafx.scene.control.MenuItem xMenuItem = new javafx.scene.control.MenuItem();
	
	MenuItem menuItem = (MenuItem) element;
	
	// Get text
	String text = menuItem.getText();
	if (text != null) {
	    xMenuItem.setText(text);
	}
	
	// Get icon
	javafx.scene.image.ImageView xIcon = createImageView(menuItem, menuItem.getIcon());
	if (xIcon != null) {
	    xMenuItem.setGraphic(xIcon);
	}

	xMenu.getItems().add(xMenuItem);
	return xMenuItem;
    }

    protected javafx.scene.control.MenuItem asMenuItem(Object delegate) {
	return (javafx.scene.control.MenuItem) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	javafx.scene.control.MenuItem xMenuItem = asMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}
	if (eq(name, MenuItem.PROPERTY_TEXT)) {
	    xMenuItem.setText(asSafeString(value));
	    return;
	} else if (eq(name, MenuItem.PROPERTY_ICON)) {
	    javafx.scene.image.ImageView xIcon = createImageView(element, asImage(value));
	    if (xIcon != null) {
		xMenuItem.setGraphic(xIcon);
	    }
	    return;
	} else if (eq(name, MenuItem.PROPERTY_ICON_PATH)) {
	    javafx.scene.image.ImageView xIcon = createImageView(element, asString(value));
	    if (xIcon != null) {
		xMenuItem.setGraphic(xIcon);
	    }
	    return;
	} else if (eq(name, MenuItem.PROPERTY_TOOL_TIP)) {
	    //TODO
	    //xMenuItem.setToolTip(asSafeString(value));
	    return;
	}
	super.setProperty(element, name, value);
	
    }    


    //@Override
    protected void addSelectionListener(javafx.scene.control.Control xWidget, Widget widget, Listener listener) {
	//TODO
    }

    //@Override
    protected void removeSelectionListener(javafx.scene.control.Control xWidget, Widget widget, Listener listener) {
	//TODO
    }

    
    @Override
    public void addListener(UIElement element, String eventType, final Listener listener) {
	Widget widget = (Widget) element;
	javafx.scene.control.MenuItem xMenuItem = asMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}
	
	if (eq(Events.Selection, eventType)) {
	    //TODO
	    //addSelectionListener(xMenuItem, widget, listener);
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }

    @Override
    public void removeListener(UIElement element, String eventType, final Listener listener) {
	Widget widget = (Widget) element;
	javafx.scene.control.MenuItem xMenuItem = asMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    //TODO
	    //removeSelectionListener(xMenuItem, widget, listener);
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }

}
