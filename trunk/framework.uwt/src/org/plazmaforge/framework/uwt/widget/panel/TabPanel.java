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

package org.plazmaforge.framework.uwt.widget.panel;

import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.Widget;

/**
 * Tabbed Panel.
 * The TabPanel is container of TabItem's.
 * 
 * @author ohapon
 *
 */
public class TabPanel extends Panel {
    
    public static final String PROPERTY_TAB_ITEMS = "tabItems";
    
    //////////////////////////////////////////////////////////////////////
    // METHODS
    //////////////////////////////////////////////////////////////////////

    public static final String METHOD_SET_ACTIVE_ITEM = "setActiveItem";
    
    public static final String METHOD_GET_ACTIVE_ITEM = "getActiveItem";
    
    
    public TabPanel() {
	super();
    }

    @Override
    public void setLayout(Layout layout) {
	// ignore
    }
    
    @Override
    protected void checkChild(Widget element) {
	if (!(element instanceof TabItem)) {
	    throw new IllegalArgumentException("Child must be TabItem");
	}
    }
    
    /**
     * Add <code>TabItem</code> to <code>TabPanel</code> 
     * @param item
     */
    public void addItem(TabItem item) {
	super.add(item);
    }
    
    /**
     * Remove <code>TabItem</code> from <code>TabPanel</code>
     * @param item
     */
    public void removeItem(TabItem item) {
   	super.remove(item);
    }
    
    /**
     * Return true if <code>TabPanel</code> has <code>TabItem</code>'s
     * @return
     */
    public boolean hasItems() {
	return super.hasChildren();
    }
    
    /**
     * Set active item
     * @param item
     */
    public void setActiveItem(TabItem item) {
	int index = indexOf(item);
	doSetActiveItem(index);
    }

    /**
     * Set active item by name
     * @param name
     */
    public void setActiveItemById(String name) {
	TabItem item = (TabItem) getElementById(name);
	if (item == null) {
	    return;
	}
	setActiveItem(item);
    }

    /**
     * Set active item by name
     * @param name
     */
    public void setActiveItemByName(String name) {
	TabItem item = (TabItem) getElementByName(name);
	if (item == null) {
	    return;
	}
	setActiveItem(item);
    }

    /**
     * Set active item by index
     * @param index
     */
    public void setActiveItem(int index) {
	doSetActiveItem(index);
    }
    
    protected void doSetActiveItem(int index) {
	if (!isValidChildIndex(index)) {
	    return;
	}
	getAdapter().invoke(this, METHOD_SET_ACTIVE_ITEM, new Object[] {index});
    }
    

}
