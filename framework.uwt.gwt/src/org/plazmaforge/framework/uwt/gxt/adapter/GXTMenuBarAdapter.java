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

public class GXTMenuBarAdapter extends GXTWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	com.extjs.gxt.ui.client.widget.LayoutContainer xParent = (com.extjs.gxt.ui.client.widget.LayoutContainer) parent.getDelegate();
	com.extjs.gxt.ui.client.widget.menu.MenuBar xMenuBar = new com.extjs.gxt.ui.client.widget.menu.MenuBar();
	xMenuBar.setHeight(25); // Fix height because we have big height if menubar has menus width submenu 
	xParent.add(xMenuBar);
	return xMenuBar;
    }
    
    
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.extjs.gxt.ui.client.widget.menu.MenuBar xMenuBar = (com.extjs.gxt.ui.client.widget.menu.MenuBar) element.getDelegate();
	if (xMenuBar == null) {
	    return;
	}
	
	/*
	if (startsWith(name, Control.PROPERTY_DATA_PREFIX)) {
	    
	    // HACK
	    // WARNING! We use '@' to separate 'data' and 'key' 
	    String key = name.substring(Control.PROPERTY_DATA_PREFIX.length());
	    if (Control.PROPERTY_LAYOUT_DATA.equals(key)) {
		UIObject layoutData = (UIObject) value;
		layoutData.activate();
		xMenuBar.setLayoutData(layoutData.getDelegate());
		return;
	    }
	    
	}
	*/
	
	super.setProperty(element, name, value);
    }
}
