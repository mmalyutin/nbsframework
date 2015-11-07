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

package org.plazmaforge.framework.uwt.widget.menu;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.util.CoreUtils;
import org.plazmaforge.framework.uwt.event.SelectionListener;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Widget;

public class Menu extends MenuItem {

    private List<MenuItem> items;
    
    
    public Menu() {
	super();
    }

    public Menu(Image icon) {
	super(icon);
    }

    public Menu(String text, Image icon) {
	super(text, icon);
    }

    public Menu(String text) {
	super(text);
    }


    public void addItem(MenuItem item) {
	doGetItems().add(item);
	item.setParent(this);
	fireAddChild(getInitChildren(PROPERTY_MENU_ITEMS), item);
    }

    public void removeItem(MenuItem item) {
	doGetItems().remove(item);
	item.setParent(null);
	fireRemoveChild(getInitChildren(PROPERTY_MENU_ITEMS), item);
    }
    
    public void addSeparator() {
	addItem(new MenuSeparator());
    }
    
    public List<MenuItem> getItems() {
	List<MenuItem> input = doGetItems();
	List<MenuItem> output = new ArrayList<MenuItem>();
	CoreUtils.transferList(input, output);
	return output;
	
    }

    protected List<MenuItem> doGetItems() {
	if (items == null) {
	    items = new ArrayList<MenuItem>();
	}
	return items;
    }
    
    
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Container block
    //
    // ///////////////////////////////////////////////////////////////////////////////////////////////////////
    protected List<Widget> getInitChildren(String key) {

	List<Widget> children = (List<Widget>) getInitProperty(key);
	if (children == null) {
	    children = new ArrayList<Widget>();
	    setInitProperty(key, children);
	}
	return children;
    }

    protected void fireAddChild(List<Widget> initChildren, Widget element) {
	if (!hasDelegate()) {
	    initChildren.add(element);
	    return;
	}
	addDelegateChild(element);
    }

    protected void fireRemoveChild(List<Widget> initChildren, Widget element) {
	if (!hasDelegate()) {
	    initChildren.remove(element);
	    return;
	}
	removeDelegateChild(element);
    }

    protected void initDelegateProperty(String name, Object value) {
	if (isChildrenProperty(name)) {
	    List<Widget> initChildren = (List<Widget>) getInitProperty(name);
	    initDelegateChildren(initChildren);
	    return;
	}
	super.initDelegateProperty(name, value); // 2012-11-23 MF
    }

    protected void initDelegateChildren(List<Widget> initChildren) {
	if (!hasDelegate() || initChildren.isEmpty()) {
	    return;
	}
	for (Widget element : initChildren) {
	    addDelegateChild(element);
	}
	initChildren.clear();
    }

    protected void addDelegateChild(Widget element) {
	if (element == null) {
	    return;
	}
	element.activateUI();
    }

    protected void removeDelegateChild(Widget element) {
	if (element == null) {
	    return;
	}
	element.deactivateUI();
    }

    protected boolean isChildrenProperty(String name) {
	return PROPERTY_MENU_ITEMS.equals(name);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
        
    public void addSelectionListener(SelectionListener listener) {
	// ignore
	throw new UnsupportedOperationException("SelectionListener doesn't support in Menu");
    }
    
}
