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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.layout.CardLayout;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Layout;

public class SWTCardLayoutAdapter extends SWTLayoutAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.custom.StackLayout xLayout = new  org.eclipse.swt.custom.StackLayout();
	return xLayout;
    }
    
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	Layout layout = (Layout) element;
	org.eclipse.swt.custom.StackLayout xLayout = (org.eclipse.swt.custom.StackLayout) element.getDelegate();
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
    
    private void doNavigation(org.eclipse.swt.custom.StackLayout xLayout, Layout layout, String methodName) {
	Composite owner = layout.getOwner();
	org.eclipse.swt.widgets.Composite xOwner = (org.eclipse.swt.widgets.Composite) owner.getDelegate();
	org.eclipse.swt.widgets.Control[] children = xOwner.getChildren();
	if (children.length == 0) {
	    return;
	}
	if (CardLayout.METHOD_FIRST.equals(methodName)) {
	    xLayout.topControl = children[0];
	    xOwner.layout();
	    return;
	} else if (CardLayout.METHOD_PREV.equals(methodName)) {
	    if (xLayout.topControl == null) {
		xLayout.topControl = children[0];
		xOwner.layout();
	    } else {
		int index = indexOf(children, xLayout.topControl);
		if (index <= 0) {
		    return;
		}
		index--;
		xLayout.topControl = children[index];
		xOwner.layout();
	    }
	    return;
	} else if (CardLayout.METHOD_NEXT.equals(methodName)) {
	    if (xLayout.topControl == null) {
		xLayout.topControl = children[0];
		xOwner.layout();
	    } else {
		int index = indexOf(children, xLayout.topControl);
		if (index >= children.length - 1) {
		    return;
		}
		index++;
		xLayout.topControl = children[index];
		xOwner.layout();
	    }
	    return;
	} else if (CardLayout.METHOD_LAST.equals(methodName)) {
	    xLayout.topControl = children[children.length - 1];
	    xOwner.layout();
	}
	
    }
    
    private int indexOf(org.eclipse.swt.widgets.Control[] children, org.eclipse.swt.widgets.Control child) {
	if (children == null || children.length == 0 || child == null ) {
	    return -1;
	}
	for (int i=0; i < children.length; i++) {
	    if (child == children[i]) {
		return i;
	    }
	}
	return -1;
    }
    

}
