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
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.menu.Menu;


/**
 * 
 * @author ohapon
 *
 */

public class JFXMenuAdapter extends JFXWidgetAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	Object xParent = parent.getDelegate();
	javafx.scene.control.Menu xMenu = new javafx.scene.control.Menu();
	
	Menu menu = (Menu) element;
	
	// Get text
	String text = menu.getText();
	if (text != null) {
	    xMenu.setText(text);
	}
	
	// Get icon
	javafx.scene.image.ImageView xIcon = createImageView(menu, menu.getIcon());
	if (xIcon != null) {
	    xMenu.setGraphic(xIcon);
	}
		
	if (xParent instanceof javafx.scene.control.MenuBar) {
	    ((javafx.scene.control.MenuBar) xParent).getMenus().addAll(xMenu);
	} else if (xParent instanceof javafx.scene.control.Menu) {
	    ((javafx.scene.control.Menu) xParent).getItems().addAll(xMenu);
	}
	
	menu.resetInitProperty(Menu.PROPERTY_TEXT);
	menu.resetInitProperty(Menu.PROPERTY_ICON);
	
	return xMenu;
    }

    protected javafx.scene.control.Menu asMenu(Object delegate) {
	return (javafx.scene.control.Menu) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	javafx.scene.control.Menu xMenu = asMenu(element.getDelegate());
	if (xMenu == null) {
	    return;
	}
	if (eq(name, Button.PROPERTY_TEXT)) {
	    xMenu.setText(asSafeString(value));
	    return;
	} else if (eq(name, Button.PROPERTY_ICON)) {
	    javafx.scene.image.ImageView xIcon = createImageView(element, asImage(value));
	    if (xIcon != null) {
		xMenu.setGraphic(xIcon);
	    }
	    return;
	} else if (eq(name, Button.PROPERTY_ICON_PATH)) {
	    javafx.scene.image.ImageView xIcon = createImageView(element, asString(value));
	    if (xIcon != null) {
		xMenu.setGraphic(xIcon);
	    }
	    return;
	}
	
	super.setProperty(element, name, value);
    }
    
}
