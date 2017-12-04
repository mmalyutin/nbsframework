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
import org.plazmaforge.framework.uwt.widget.menu.Menu;

import com.google.gwt.resources.client.ImageResource;

/**
 * 
 * @author ohapon
 *
 */

public class GXTMenuAdapter extends GXTWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	com.google.gwt.user.client.ui.Widget xWidget = asWidget(parent.getDelegate());

	Menu menu = (Menu) element;
	
	// Get text
	String text = menu.getText();
	if (text == null) {
	    text = "";
	}
	
	// Get image
	ImageResource xImage = createImage(element, menu.getIcon());
	

	com.sencha.gxt.widget.core.client.menu.Menu xMenu = new com.sencha.gxt.widget.core.client.menu.Menu();

	if (xWidget instanceof com.sencha.gxt.widget.core.client.menu.MenuBar) {
	   com.sencha.gxt.widget.core.client.menu.MenuBar xMenuBarParent = (com.sencha.gxt.widget.core.client.menu.MenuBar) parent.getDelegate();
	   com.sencha.gxt.widget.core.client.menu.MenuBarItem xMenuBarItem = new com.sencha.gxt.widget.core.client.menu.MenuBarItem(text, xMenu);
	    xMenuBarParent.add(xMenuBarItem);
	} else 	if (xWidget instanceof com.sencha.gxt.widget.core.client.menu.Menu) {
	   com.sencha.gxt.widget.core.client.menu.Menu xMenuParent = (com.sencha.gxt.widget.core.client.menu.Menu) parent.getDelegate();
	   com.sencha.gxt.widget.core.client.menu.MenuItem xMenuItem = new com.sencha.gxt.widget.core.client.menu.MenuItem(text);
	    
	    if (xImage != null) {
		xMenuItem.setIcon(xImage);
	    }
	
	    xMenuItem.setSubMenu(xMenu);
	    xMenuParent.add(xMenuItem);
	}

	
	
	
	return xMenu;
    }

}
