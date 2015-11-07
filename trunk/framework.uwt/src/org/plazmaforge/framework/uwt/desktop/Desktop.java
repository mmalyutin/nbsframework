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

package org.plazmaforge.framework.uwt.desktop;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.widget.Composite;

/**
 * The Desktop is the container of Desktop Items
 * 
 * @author ohapon
 *
 */
public class Desktop extends Composite {

    
    //////////////////////////////////////////////////////////////////////
    // METHODS
    //////////////////////////////////////////////////////////////////////

    public static final String METHOD_SET_ACTIVE_ITEM = "setActiveItem";
    
    public static final String METHOD_GET_ACTIVE_ITEM = "getActiveItem";
    
    
    
    /**
     * Desktop mode (SDI, MDI, TDI)
     * By default TDI (Tabbed document interface)
     */
    private DesktopMode mode = DesktopMode.TDI; 
    
    /**
     * List of vies
     */
    private List<DesktopItem> items = new ArrayList<DesktopItem>();
    
    /**
     * True if need check active item before setting
     */
    private boolean checkActiveItem = false; 
    
    
    /**
     * Add and open item
     * @return
     */
    public DesktopItem addItem() {
	DesktopItem item = new DesktopItem();
	items.add(item);
	//item.setContentVisible(true);
	item.setParent(this);
	fireAddChild(getInitChildren(PROPERTY_ITEMS), item);
	// WARNING!! Delegate must implemented auto activate new item
	return item;
    }
    
    /**
     * Close and remove item
     * @param item
     */
    public void removeItem(DesktopItem item) {
	checkItem(item);
	//item.setContentVisible(false);
	items.remove(item);
	item.setParent(null);
	fireRemoveChild(getInitChildren(PROPERTY_ITEMS), item);
    }

    /**
     * Activate/select item
     * @param item
     */
    public void setActiveItem(DesktopItem item) {
	checkItem(item);
	if (!existsActiveItem(item)) {
	    return;
	}
	doSetActiveItem(item);
    }

    /**
     * Activate/select item by id
     * @param id
     */
    public void setActiveItemById(String id) {
	DesktopItem item = findItemById(id);
	if (item == null) {
	    throw new IllegalArgumentException("Active item not found by id=" + id);
	}
	doSetActiveItem(item);
    }

    /**
     * Activate/select item by name
     * @param name
     */
    public void setActiveItemByName(String name) {
	DesktopItem item = findItemByName(name);
	if (item == null) {
	    throw new IllegalArgumentException("Active item not found by name=" + name);
	}
	doSetActiveItem(item);
    }
    
    protected void doSetActiveItem(DesktopItem item) {
	invokeDelegate(this, METHOD_SET_ACTIVE_ITEM, new Object[] {item});
    }
    
    

    /**
     * Find item by id.
     * If item not fount the return null.
     * @param id
     * @return
     */
    public DesktopItem findItemById(String id) {
	if (id == null) {
	    return null;
	}
	for (DesktopItem i: items) {
	    if (id.equals(i.getId())) {
		return i;
	    }
	}
	return null;
    }
    
    /**
     * Find item by name.
     * If item not fount the return null.
     * @param name
     * @return
     */
    public DesktopItem findItemByName(String name) {
	if (name == null) {
	    return null;
	}
	for (DesktopItem i: items) {
	    if (name.equals(i.getName())) {
		return i;
	    }
	}
	return null;
    }

    /**
     * Return true if item was found
     * @param item
     * @return
     */
    public boolean exists(DesktopItem item) {
	if (item == null) {
	    return false;
	}
	for (DesktopItem i: items) {
	    if (item == i) {
		return true;
	    }
	}
	return false;
    }

    protected boolean existsActiveItem(DesktopItem item) {
	if (exists(item)) {
	    return true;
	}
	if (!checkActiveItem) {
	    return false;
	}
	throw new IllegalArgumentException("Active item not found");
    }

    private void checkItem(DesktopItem item) {
	if (item == null) {
	    throw new IllegalArgumentException("Item must be not null");
	}
    }
    


    public DesktopMode getMode() {
        return mode;
    }

    public void setMode(DesktopMode mode) {
        this.mode = mode;
    }
    
    protected boolean isChildrenProperty(String name) {
   	return PROPERTY_ITEMS.equals(name);
    }
    

    
}
