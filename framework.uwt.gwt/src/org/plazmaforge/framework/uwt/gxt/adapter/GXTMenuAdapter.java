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

import com.google.gwt.user.client.ui.AbstractImagePrototype;


public class GXTMenuAdapter extends GXTWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	com.google.gwt.user.client.ui.Widget xWidget = getWidget(parent.getDelegate());

	Menu menu = (Menu) element;
	
	// Get text
	String text = menu.getText();
	if (text == null) {
	    text = "";
	}
	
	// Get image
	AbstractImagePrototype xImage = createImage(element, menu.getIcon());
	

	com.extjs.gxt.ui.client.widget.menu.Menu xMenu = new com.extjs.gxt.ui.client.widget.menu.Menu();

	if (xWidget instanceof com.extjs.gxt.ui.client.widget.menu.MenuBar) {
	    com.extjs.gxt.ui.client.widget.menu.MenuBar xMenuBarParent = (com.extjs.gxt.ui.client.widget.menu.MenuBar) parent.getDelegate();
	    com.extjs.gxt.ui.client.widget.menu.MenuBarItem xMenuBarItem = new com.extjs.gxt.ui.client.widget.menu.MenuBarItem(text, xMenu);
	    xMenuBarParent.add(xMenuBarItem);
	} else 	if (xWidget instanceof com.extjs.gxt.ui.client.widget.menu.Menu) {
	    com.extjs.gxt.ui.client.widget.menu.Menu xMenuParent = (com.extjs.gxt.ui.client.widget.menu.Menu) parent.getDelegate();
	    com.extjs.gxt.ui.client.widget.menu.MenuItem xMenuItem = new com.extjs.gxt.ui.client.widget.menu.MenuItem(text);
	    
	    if (xImage != null) {
		xMenuItem.setIcon(xImage);
	    }
	
	    xMenuItem.setSubMenu(xMenu);
	    xMenuParent.add(xMenuItem);
	}

	
	
	
	return xMenu;
    }

}
