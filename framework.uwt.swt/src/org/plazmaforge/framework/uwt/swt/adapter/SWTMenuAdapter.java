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

package org.plazmaforge.framework.uwt.swt.adapter;

import org.eclipse.swt.SWT;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.menu.Menu;

public class SWTMenuAdapter extends SWTWidgetAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	
	org.eclipse.swt.widgets.Widget xParent = parent == null ? null : (org.eclipse.swt.widgets.Widget) getWidget(parent.getDelegate());
	Menu menu = (Menu) element;
	
	org.eclipse.swt.widgets.Menu xParentMenu = null;
	
	// If parent is Menu then create SubMenu
	if (xParent instanceof org.eclipse.swt.widgets.Menu) {
	    xParentMenu = (org.eclipse.swt.widgets.Menu) xParent;
	} else if (xParent instanceof org.eclipse.swt.widgets.MenuItem) {
	    org.eclipse.swt.widgets.MenuItem xParentMenuItem = (org.eclipse.swt.widgets.MenuItem) xParent;
	    xParentMenu = xParentMenuItem.getMenu();
	}
	
	if (xParentMenu != null) {
	    
	    // Create CASCADE Menu
	    org.eclipse.swt.widgets.MenuItem xMenuItem = new org.eclipse.swt.widgets.MenuItem(xParentMenu, SWT.CASCADE); // CASCADE/SUBMENU
	    
	    // Create special DROP_DOWN Menu for CASCADE Menu to display MenuItems
	    org.eclipse.swt.widgets.Menu dropDownMenu = new org.eclipse.swt.widgets.Menu(xParentMenu.getShell(), SWT.DROP_DOWN);
	    
	    // Set DROP_DOWN Menu for CASCADE Menu
	    xMenuItem.setMenu(dropDownMenu);
		
	    // Get text
	    String text = menu.getText();
	    if (text != null) {
		xMenuItem.setText(text);
	    }
	    
	    // Get image
	    org.eclipse.swt.graphics.Image xImage = createImage(element, menu.getIcon());
	    if (xImage != null) {
		xMenuItem.setImage(xImage);
	    }

	    return xMenuItem;	    
	}
	
	// If parent is Composite the create PopupMenu
	if (xParent instanceof org.eclipse.swt.widgets.Composite) {
	    org.eclipse.swt.widgets.Composite xParentComposite = (org.eclipse.swt.widgets.Composite) xParent;
	    org.eclipse.swt.widgets.Menu xMenu = new org.eclipse.swt.widgets.Menu(xParentComposite); // POPUP
	    return xMenu;
	    
	}

	// ERROR
	return null;
    }

    protected org.eclipse.swt.widgets.Menu getMenu(Object delegate) {
	return (org.eclipse.swt.widgets.Menu) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	// TODO check Menu and MenuItem
	
	//org.eclipse.swt.widgets.Menu xMenu = getMenu(element.getDelegate());
	//if (xMenu == null) {
	//    return;
	//}
	
	// do nothing
	
	super.setProperty(element, name, value);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	// TODO check Menu and MenuItem
	
	//org.eclipse.swt.widgets.Menu xMenu = getMenu(element.getDelegate());
	//if (xMenu == null) {
	//    return;
	//}
	
	Object delegate = element.getDelegate();
	if (delegate == null) {
	    return;
	}

	// DISBALE: NOT SUPPORT IN SWT
	/*
	if (delegate instanceof org.eclipse.swt.widgets.MenuItem) {
	    
	    // ITEM OF CASCADE MENU
	    org.eclipse.swt.widgets.MenuItem xMenuItem = (org.eclipse.swt.widgets.MenuItem) delegate;
	    if (eventType == UWT.Selection) {
		xMenuItem.addSelectionListener(createSelectionListener(listener));
		return;
	    }

	}
	*/
	
	super.addListener(element, eventType, listener);
    }


}
