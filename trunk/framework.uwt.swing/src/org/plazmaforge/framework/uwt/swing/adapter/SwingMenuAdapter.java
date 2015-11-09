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

package org.plazmaforge.framework.uwt.swing.adapter;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.menu.Menu;

public class SwingMenuAdapter extends SwingWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	
	Object parentDelegate = parent == null ? null : parent.getDelegate();
	javax.swing.MenuElement xMenu = null;
	
	// Only MenuBar or Menu can be parent of menu
	if (parentDelegate instanceof javax.swing.JMenuBar) {
	    javax.swing.JMenuBar xParentMenuBar = (javax.swing.JMenuBar) parentDelegate;
	    xMenu = new javax.swing.JMenu();
	    xParentMenuBar.add((javax.swing.JMenu) xMenu);
	} else if (parentDelegate instanceof javax.swing.JMenu) {
	    javax.swing.JMenu xParentMenu = (javax.swing.JMenu) parentDelegate;
	    xMenu = new javax.swing.JMenu();
	    xParentMenu.add((javax.swing.JMenu) xMenu);
	} else {
	    xMenu = new javax.swing.JPopupMenu();
	}
	
	Menu menu = (Menu) element;
	
	// Get text
	String text = menu.getText();
	if (text != null) {
	    if (xMenu instanceof javax.swing.JMenu) {
		((javax.swing.JMenu) xMenu).setText(text);
	    }
	    
	}
	
	// Get image
	javax.swing.Icon xImage = createImageIcon(element, menu.getIcon());
	if (xImage != null) {
	    if (xMenu instanceof javax.swing.JMenu) {
		((javax.swing.JMenu) xMenu).setIcon(xImage);
	    }
	    
	}

	return xMenu;
    }
    
    
    protected javax.swing.MenuElement getMenu(Object delegate) {
	return (javax.swing.MenuElement) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	javax.swing.MenuElement xMenu = getMenu(element.getDelegate());
	if (xMenu == null) {
	    return;
	}
	
	// do nothing
	
	super.setProperty(element, name, value);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	javax.swing.MenuElement xMenu = getMenu(element.getDelegate());
	if (xMenu == null) {
	    return;
	}

	// DISABLE: NOT SUPPORT IN SWING
	//if (equals(UWT.Selection, eventType)) {
	//    xMenu.addActionListener(createActionListener(listener));
	//    return;
	//} 
	
	super.addListener(element, eventType, listener);
    }

    
    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	
	javax.swing.MenuElement xMenu = getMenu(element.getDelegate());
	if (xMenu == null) {
	    return;
	}

	// DISABLE: NOT SUPPORT IN SWING
	//if (equals(UWT.Selection, eventType)) {
	//    xMenu.removeActionListener(getActionListener(listener));
	//    return;
	//} 
	
	super.removeListener(element, eventType, listener);
    }
}
