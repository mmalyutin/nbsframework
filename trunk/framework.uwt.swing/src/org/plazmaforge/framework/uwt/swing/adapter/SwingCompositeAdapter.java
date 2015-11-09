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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Layout;

public class SwingCompositeAdapter extends SwingControlAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = (java.awt.Container) getContent(parent.getDelegate());
	javax.swing.JPanel xComposite = new javax.swing.JPanel();
	addToParent(xParent, xComposite, element);
	return xComposite;
    }
    
    protected java.awt.LayoutManager createDefaultCompositeLayout() {
	return new FlowLayout(FlowLayout.LEFT);
    }
    

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	java.awt.Container xComposite = (java.awt.Container) element.getDelegate();
	if (xComposite == null) {
	    return;
	}
	if (Composite.PROPERTY_LAYOUT.equals(name)) {
	    // Get content (JFrame, JDialog)
	    xComposite = getContent(xComposite);
	    
	    Layout layout = (Layout) value;
	    layout.setData(SwingLayoutAdapter.SYS_PROPERTY_OWNER, xComposite); // Set owner (container). It use in Swing<Layout>Adapter.createDelegate(); 
	    layout.activateUI();
	    xComposite.setLayout((java.awt.LayoutManager) layout.getDelegate());
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
    public Object invoke(UIObject element, String methodName, Object[] args) {
	if (element == null) {
	    return null;
	}
	java.awt.Container composite = ((java.awt.Container) element.getDelegate());
	if (composite == null) {
	    return null;
	}
	if (Composite.METHOD_LAYOUT.equals(methodName)) {
	    composite.doLayout();
	    return null;
	}
	return super.invoke(element, methodName, args);
    }
    
    
    
}
