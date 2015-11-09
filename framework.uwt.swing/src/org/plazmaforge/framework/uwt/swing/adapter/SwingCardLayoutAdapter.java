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
import org.plazmaforge.framework.uwt.layout.CardLayout;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Layout;

public class SwingCardLayoutAdapter extends SwingLayoutAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {

	org.plazmaforge.framework.uwt.swing.layout.XCardLayout xLayout = new org.plazmaforge.framework.uwt.swing.layout.XCardLayout();
	
	return xLayout;
    }
  
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	Layout layout = (Layout) element;
	org.plazmaforge.framework.uwt.swing.layout.XCardLayout xLayout = (org.plazmaforge.framework.uwt.swing.layout.XCardLayout) element.getDelegate();
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
    
    private void doNavigation(org.plazmaforge.framework.uwt.swing.layout.XCardLayout xLayout, Layout layout, String methodName) {
	Composite owner = layout.getOwner();
	java.awt.Container xOwner = (java.awt.Container) owner.getDelegate();
	int count = xOwner.getComponentCount();
	if (count == 0) {
	    return;
	}
	if (CardLayout.METHOD_FIRST.equals(methodName)) {
	    xLayout.first(xOwner);
	    xOwner.layout();
	    return;
	} else if (CardLayout.METHOD_PREV.equals(methodName)) {
	    int index = xLayout.getCurrentCard();
	    if (index <= 0) {
		return;
	    }
	    xLayout.previous(xOwner);
	    xOwner.layout();
	    return;
	} else if (CardLayout.METHOD_NEXT.equals(methodName)) {
	    int index = xLayout.getCurrentCard();
	    if (index >= count - 1) {
		return;
	    }
	    xLayout.next(xOwner);
	    xOwner.layout();
	    return;
	} else if (CardLayout.METHOD_LAST.equals(methodName)) {
	    xLayout.last(xOwner);
	    xOwner.layout();
	}
	
    }
    

}
