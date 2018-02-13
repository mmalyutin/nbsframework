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

import java.awt.FlowLayout;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Container;
import org.plazmaforge.framework.uwt.widget.Layout;

public class SwingContainerAdapter extends SwingControlAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	java.awt.Container xParent = (java.awt.Container) getContent(parent.getDelegate());
	javax.swing.JPanel xContainer = new javax.swing.JPanel();
	addChild(xParent, xContainer, element);
	return xContainer;
    }
    
    protected java.awt.LayoutManager createDefaultCompositeLayout() {
	return new FlowLayout(FlowLayout.LEFT);
    }
    

    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	java.awt.Container xContainer = (java.awt.Container) element.getDelegate();
	if (xContainer == null) {
	    return;
	}
	if (Container.PROPERTY_LAYOUT.equals(name)) {
	    // Get content (JFrame, JDialog)
	    xContainer = getContent(xContainer);
	    
	    Layout layout = (Layout) value;
	    layout.setData(SwingLayoutAdapter.SYS_PROPERTY_OWNER, xContainer); // Set owner (container). It use in Swing<Layout>Adapter.createDelegate(); 
	    layout.activateUI();
	    xContainer.setLayout((java.awt.LayoutManager) layout.getDelegate());
	    return;
	}
	
	super.setProperty(element, name, value);
    }

    protected int getSplitOrientation(Orientation orientation) {
	if (orientation == null || orientation.equals(Orientation.HORIZONTAL)) {
	    return javax.swing.JSplitPane.HORIZONTAL_SPLIT;
	}
	return javax.swing.JSplitPane.VERTICAL_SPLIT;
    }
    
    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	if (element == null) {
	    return null;
	}
	java.awt.Container xContainer = ((java.awt.Container) element.getDelegate());
	if (xContainer == null) {
	    return null;
	}
	if (Container.METHOD_LAYOUT.equals(methodName)) {
	    xContainer.doLayout();
	    return null;
	}
	return super.invoke(element, methodName, args);
    }
    
    
    
}
