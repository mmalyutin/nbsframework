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
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.menu.MenuItem;

public class SwingMenuItemAdapter extends SwingWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	
	Object xParent = parent.getDelegate();
	javax.swing.JMenuItem xMenuItem = null;
	
	// Only MenuBar, JMenu, JPopupMenu can be parent of menu
	if (xParent instanceof java.awt.MenuBar) {
	    javax.swing.JMenuBar xParentMenuBar = (javax.swing.JMenuBar) xParent;
	    
	    // Swing MenuBar can't have Swing MenuItem
	    // We create Swing Menu and add to Swing MenuBar
	    javax.swing.JMenu xMenu = new javax.swing.JMenu();
	    xParentMenuBar.add(xMenu);
	    
	    // Set Swing menu to Swing MenuItem 
	    xMenuItem = xMenu;
	} else if (xParent instanceof javax.swing.JMenu) {
	    javax.swing.JMenu xParentMenu = (javax.swing.JMenu) xParent;
	    xMenuItem = new javax.swing.JMenuItem();
	    xParentMenu.add(xMenuItem);
	} else if (xParent instanceof javax.swing.JPopupMenu) {
	    javax.swing.JPopupMenu xParentMenu = (javax.swing.JPopupMenu) xParent;
	    xMenuItem = new javax.swing.JMenuItem();
	    xParentMenu.add(xMenuItem);
	} else {
	    // ERROR
	}
	
	// Get text
	MenuItem menuItem = (MenuItem) element;
	String text = menuItem.getText();
	if (text != null) {
	    xMenuItem.setText(text);
	}
	
	// Get icon
	javax.swing.Icon xIcon = createImageIcon(element, menuItem.getIcon());
	if (xIcon != null) {
	    xMenuItem.setIcon(xIcon);
	}

	
	return xMenuItem;
    }
    
    
    protected javax.swing.JMenuItem getMenuItem(Object delegate) {
	return (javax.swing.JMenuItem) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	javax.swing.JMenuItem xMenuItem = getMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}
	if (eq(name, MenuItem.PROPERTY_TEXT)) {
	    xMenuItem.setText(getSafeString(value));
	    return;
	} else if (eq(name, MenuItem.PROPERTY_ICON)) {
	    javax.swing.Icon xIcon = createImageIcon(element, (Image) value);
	    if (xIcon != null) {
		xMenuItem.setIcon(xIcon);
	    }
	    return;
	} else if (eq(name, MenuItem.PROPERTY_ICON_PATH)) {
	    javax.swing.Icon xIcon = createImageIcon(element, (String) value);
	    if (xIcon != null) {
		xMenuItem.setIcon(xIcon);
	    }
	    return;
	} else if (eq(MenuItem.PROPERTY_ENABLED, name)) {
	    xMenuItem.setEnabled(getBoolean(value));
	    return;
	} else if (eq(MenuItem.PROPERTY_TOOL_TIP, name)) {
	    xMenuItem.setToolTipText(getSafeString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Widget widget = (Widget) element;
	javax.swing.JMenuItem xMenuItem = getMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xMenuItem.addActionListener(createActionListener(widget, listener));
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }

    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	Widget widget = (Widget) element;
	javax.swing.JMenuItem xMenuItem = getMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xMenuItem.removeActionListener(getActionListener(widget, listener));
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }

}
