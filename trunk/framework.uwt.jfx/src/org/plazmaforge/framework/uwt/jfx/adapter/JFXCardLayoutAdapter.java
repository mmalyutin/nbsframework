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

package org.plazmaforge.framework.uwt.jfx.adapter;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.jfx.layout.XCardLayout;
import org.plazmaforge.framework.uwt.jfx.layout.XLayout;
import org.plazmaforge.framework.uwt.jfx.widget.XCardPanel;

import org.plazmaforge.framework.uwt.layout.CardLayout;
import org.plazmaforge.framework.uwt.widget.Layout;

/**
 * 
 * @author ohapon
 *
 */
public class JFXCardLayoutAdapter extends JFXLayoutAdapter {

    
    public Object createDelegate(UIElement parent, UIElement element) {
	CardLayout layout = (CardLayout) element;  
	XCardLayout xLayout = createLayout(layout);
	return xLayout;
    }
    
    protected XCardLayout createLayout(CardLayout layout) {
	XCardLayout xLayout = new XCardLayout();
	
	// Margin
	updateMargin(layout, xLayout);
	
	return xLayout;
    }

    @Override
    public javafx.scene.layout.Pane createContainer(XLayout xLayout) {
	XCardLayout xCardLayout = (XCardLayout) xLayout;
	XCardPanel xCardPanel = new XCardPanel();
	xLayout.assign(xCardPanel);
	xCardPanel.setPadding(createPadding(xCardLayout));
	return xCardPanel; 
    }
    
    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	Layout layout = (Layout) element;
	XCardLayout xLayout = (XCardLayout) element.getDelegate();
	if (isNavigation(methodName)) {
	    doNavigation(xLayout, methodName);
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
    
    protected void doNavigation(XCardLayout xLayout, String methodName) {

	XCardPanel xOwner = (XCardPanel) xLayout.getOwner();

	// first
	if (CardLayout.METHOD_FIRST.equals(methodName)) {
	    xOwner.first();
	}

	// prev
	if (CardLayout.METHOD_PREV.equals(methodName)) {
	    xOwner.prev();
	}

	// next
	if (CardLayout.METHOD_NEXT.equals(methodName)) {
	    xOwner.next();
	}

	// last
	if (CardLayout.METHOD_LAST.equals(methodName)) {
	    xOwner.last();
	}
    }
    
    
}
