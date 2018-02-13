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

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.gxt.layout.XCardLayout;
import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.widget.XCardLayoutContainer;
import org.plazmaforge.framework.uwt.layout.CardLayout;
import org.plazmaforge.framework.uwt.widget.Layout;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * 
 * @author ohapon
 *
 */
public class GXTCardLayoutAdapter extends GXTLayoutAdapter {

    
    public Object createDelegate(UIElement parent, UIElement element) {
	CardLayout layout = (CardLayout) element;
	XCardLayout xLayout = new XCardLayout();
	
	// Margin
	//xLayout.setMarginLeft(layout.getMarginLeft());
	//xLayout.setMarginTop(layout.getMarginTop());
	//xLayout.setMarginRight(layout.getMarginRight());
	//xLayout.setMarginBottom(layout.getMarginBottom());
	
	return xLayout;
    }
    

    @Override
    public HasWidgets createContainer(XLayout xLayout) {
	XCardLayoutContainer xContainer = new XCardLayoutContainer();
	xLayout.assign(xContainer);
	return xContainer;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	//TODO
	super.setProperty(element, name, value);
	
    }
    
    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	Layout layout = (Layout) element;
	XCardLayout xLayout = (XCardLayout) element.getDelegate();
	if (isNavigation(methodName)) {
	    doNavigation(xLayout, layout, methodName);
	    return null;
	}
	return super.invoke(element, methodName, args);
    }
     
    /**
     * Returns true if method uses to navigation
     * @param methodName
     * @return
     */
    protected boolean isNavigation(String methodName) {
	return in(methodName, CardLayout.METHODS_NAVIGATION);
    }
    
    /**
     * Navigates in CardLayoutContainer by navigation method (first, prev, nex, last)
     * @param xLayout
     * @param layout
     * @param methodName
     */
    protected void doNavigation(XCardLayout xLayout, Layout layout, String methodName) {
	XCardLayoutContainer xOwner = (XCardLayoutContainer) xLayout.getOwner();
	int count = xOwner.getWidgetCount();
	if (count == 0) {
	    return;
	}
	
	// Get old ActiveWidget
	com.google.gwt.user.client.ui.Widget oldWidget = xOwner.getActiveWidget();
	
	// Get new ActiveWidget by navigation method and old ActiveWidget
	com.google.gwt.user.client.ui.Widget newWidget = getNewWidget(xOwner, oldWidget, methodName);

	if (newWidget != null) {
	    xOwner.setActiveWidget(newWidget);
	}
    }
    
    protected com.google.gwt.user.client.ui.Widget getNewWidget(XCardLayoutContainer xOwner, com.google.gwt.user.client.ui.Widget oldWidget, String methodName) {

	// first
	if (CardLayout.METHOD_FIRST.equals(methodName)) {
	    return xOwner.getWidget(0);
	}

	// prev
	if (CardLayout.METHOD_PREV.equals(methodName)) {
	    if (oldWidget == null) {
		return xOwner.getWidget(0);
	    }
	    int index = xOwner.getWidgetIndex(oldWidget);
	    if (index <= 0) {
		return null;
	    }
	    index--;
	    return xOwner.getWidget(index);
	}

	// next
	if (CardLayout.METHOD_NEXT.equals(methodName)) {
	    if (oldWidget == null) {
		return xOwner.getWidget(0);
	    }
	    int index = xOwner.getWidgetIndex(oldWidget);
	    if (index >= xOwner.getWidgetCount() - 1) {
		return null;
	    }
	    index++;
	    return xOwner.getWidget(index);
	}

	// last
	if (CardLayout.METHOD_LAST.equals(methodName)) {
	    return xOwner.getWidget(xOwner.getWidgetCount() - 1);
	}

	return null;
    }

}
