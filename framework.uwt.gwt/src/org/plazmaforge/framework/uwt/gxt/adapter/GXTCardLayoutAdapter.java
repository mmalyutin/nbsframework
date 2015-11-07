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

import java.util.List;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.layout.CardLayout;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Layout;

public class GXTCardLayoutAdapter extends GXTLayoutAdapter {

    
    public Object createDelegate(UIObject parent, UIObject element) {
	CardLayout layout = (CardLayout) element;
	com.extjs.gxt.ui.client.widget.layout.CardLayout xLayout = new com.extjs.gxt.ui.client.widget.layout.CardLayout();
	
	// Margin
	//xLayout.setMarginLeft(layout.getMarginLeft());
	//xLayout.setMarginTop(layout.getMarginTop());
	//xLayout.setMarginRight(layout.getMarginRight());
	//xLayout.setMarginBottom(layout.getMarginBottom());
	
	return xLayout;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	//TODO
	super.setProperty(element, name, value);
	
    }
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	Layout layout = (Layout) element;
	com.extjs.gxt.ui.client.widget.layout.CardLayout xLayout = (com.extjs.gxt.ui.client.widget.layout.CardLayout) element.getDelegate();
	if (isNavigation(methodName)) {
	    doNavigation(xLayout, layout, methodName);
	    return null;
	}
	return super.invoke(element, methodName, args);
    }
    
    
    /**
     * Return true if method uses to navigation
     * @param methodName
     * @return
     */
    private boolean isNavigation(String methodName) {
	return in(methodName, CardLayout.METHODS_NAVIGATION);
    }
    
    private void doNavigation(com.extjs.gxt.ui.client.widget.layout.CardLayout xLayout, Layout layout, String methodName) {
	Composite owner = layout.getOwner();
	com.extjs.gxt.ui.client.widget.LayoutContainer xOwner = (com.extjs.gxt.ui.client.widget.LayoutContainer) owner.getDelegate();
	
	List<com.extjs.gxt.ui.client.widget.Component> items = xOwner.getItems();
	int count = items == null ? 0: items.size();
	if (count == 0) {
	    return;
	}
	
	if (CardLayout.METHOD_FIRST.equals(methodName)) {
	    xLayout.setActiveItem(xOwner.getItem(0));
	    return;
	} else if (CardLayout.METHOD_PREV.equals(methodName)) {
	    if (xLayout.getActiveItem() == null) {
		xLayout.setActiveItem(xOwner.getItem(0));
	    } else {
		int index = indexOf(items, xLayout.getActiveItem());
		if (index <= 0) {
		    return;
		}
		index--;
		xLayout.setActiveItem(xOwner.getItem(index));
	    }
	    return;
	} else if (CardLayout.METHOD_NEXT.equals(methodName)) {
	    if (xLayout.getActiveItem() == null) {
		xLayout.setActiveItem(xOwner.getItem(0));
	    } else {
		int index = indexOf(items, xLayout.getActiveItem());
		if (index >= count - 1) {
		    return;
		}
		index++;
		xLayout.setActiveItem(xOwner.getItem(index));
	    }
	    return;
	} else if (CardLayout.METHOD_LAST.equals(methodName)) {
	    xLayout.setActiveItem(xOwner.getItem(count - 1));
	}
	
    }
    
    private int indexOf(List<com.extjs.gxt.ui.client.widget.Component> items, com.extjs.gxt.ui.client.widget.Component item) {
	if (items == null || items.isEmpty() || item == null) {
	    return -1;
	}
	int count = items.size();
	for (int i = 0; i < count; i++) {
	    if (item == items.get(i)) {
		return i;
	    }
	}
	return -1;
    }
    

}
