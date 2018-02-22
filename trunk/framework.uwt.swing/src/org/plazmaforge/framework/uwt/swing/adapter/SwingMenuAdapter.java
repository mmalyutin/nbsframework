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

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.menu.Menu;

/**
 * 
 * @author ohapon
 *
 */
public class SwingMenuAdapter extends SwingWidgetAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	
	
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

	menu.resetInitProperty(Menu.PROPERTY_TEXT);
	menu.resetInitProperty(Menu.PROPERTY_ICON);
	
	return xMenu;
    }
    
    
    protected javax.swing.MenuElement getMenu(Object delegate) {
	return (javax.swing.MenuElement) delegate;
    }
    


    @Override
    public void setProperty(UIElement element, String name, Object value) {
	Object xElement = element.getDelegate();
	if (xElement == null) {
	    return;
	}	
	if (!(xElement instanceof javax.swing.JMenu)) {
	    // Maybe it is JPopupMenu: see createDelegate()
	    // We have 2 variants: JMenu, JPopupMenu
	    return;
	}
	javax.swing.JMenu xMenu = (javax.swing.JMenu) xElement;

	if (eq(name, Button.PROPERTY_TEXT)) {
	    xMenu.setText(asSafeString(value));
	    return;
	} else if (eq(name, Button.PROPERTY_ICON)) {
	    javax.swing.Icon xIcon = createImageIcon(element, asImage(value));
	    if (xIcon != null) {
		xMenu.setIcon(xIcon);
	    }
	    return;
	} else if (eq(name, Button.PROPERTY_ICON_PATH)) {
	    javax.swing.Icon xIcon = createImageIcon(element, asString(value));
	    if (xIcon != null) {
		xMenu.setIcon(xIcon);
	    }
	    return;
	} 
	
	super.setProperty(element, name, value);
	
    }
    
    @Override
    public void addListener(UIElement element, String eventType, final Listener listener) {
	
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
    public void removeListener(UIElement element, String eventType, final Listener listener) {
	
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
