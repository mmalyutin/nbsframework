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

/**
 * 
 */
package org.plazmaforge.framework.uwt.swing.adapter;

import org.plazmaforge.framework.uwt.UIObject;

/**
 * @author ohapon
 *
 */
public class SwingMenuSeparatorAdapter extends SwingWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {

	Object xParent = parent.getDelegate();
	javax.swing.JSeparator xMenuSeparator = null;

	// Only MenuBar, JMenu, JPopupMenu can be parent of menu
	if (xParent instanceof java.awt.MenuBar) {
	    // NO SEPARATOR FOR MENU BAR
	    xMenuSeparator = new javax.swing.JSeparator();
	} else if (xParent instanceof javax.swing.JMenu) {
	    
	    javax.swing.JMenu xParentMenu = (javax.swing.JMenu) xParent;

	    // Add UI Separator
	    xParentMenu.addSeparator();
	    
	    // Get last component (separator)
	    javax.swing.JPopupMenu xPopupMenu = xParentMenu.getPopupMenu();
	    xMenuSeparator = (javax.swing.JSeparator) xPopupMenu.getComponent(xPopupMenu.getComponentCount() - 1);
	    
	} else if (xParent instanceof javax.swing.JPopupMenu) {
	    
	    javax.swing.JPopupMenu xParentMenu = (javax.swing.JPopupMenu) xParent;
	    
	    // Add UI Separator
	    xParentMenu.addSeparator();
	    
	    // Get last component (separator)
	    xMenuSeparator = (javax.swing.JSeparator) xParentMenu.getComponent(xParentMenu.getComponentCount() - 1);

	} else {
	    // ERROR
	}

	return xMenuSeparator;
    }
}
