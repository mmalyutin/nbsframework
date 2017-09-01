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


import org.plazmaforge.framework.uwt.UIAdapter;
import org.plazmaforge.framework.uwt.UIAdapterFactory;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Layout;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * 
 * @author ohapon
 *
 */
public class GXTCompositeAdapter extends GXTControlAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	Composite composite = (Composite) element;
	Layout layout = composite.getLayout();
	
	XLayoutContainer xComposite = createLayoutContainer(layout);
	
	//TODO: STUB
	if (parent == null) {
	    return xComposite;
	}
	
	addToParent(getContent(parent.getDelegate()), xComposite, element);
	return xComposite;
    }
    
    protected XLayoutContainer createLayoutContainer(Layout layout) {
	HasWidgets container = createContainer(layout);
	return new XLayoutContainer(container) ;
    }
    
    protected HasWidgets createContainer(Layout layout) {
	XLayout xLayout = getXLayout(layout);
	if (xLayout == null) {
	    return null;
	}
	
	// Get UIAdapter for Layout
	UIAdapter adapter = UIAdapterFactory.getAdapter(layout.getClass());
	if (adapter == null) {
	    return null;
	}
	
	if (!(adapter instanceof GXTLayoutAdapter)) {
	    //TODO: warning
	    return null;
	}
		
	// Create container by GXTLayoutAdapter
	HasWidgets container = ((GXTLayoutAdapter) adapter).createContainer(xLayout);
	
	
	//HasWidgets container = getXContainer(xLayout);
	return container;
    }
    
    protected XLayout getXLayout(Layout layout) {
	if (layout == null) {
	    return null;
	}
	layout.activateUI();
	return (XLayout) layout.getDelegate();
    }

    //DISABLE:MIGRATION
  

//    
//    @Override
//    public void setProperty(UIObject element, String name, Object value) {
//	
//	Object delegate = element.getDelegate();
//	if (delegate == null) {
//	    return;
//	}
//	
//	// Only for LayoutContainer we can set layout 
//	if (delegate instanceof LayoutContainer) {
//	    LayoutContainer xComposite = (LayoutContainer) delegate;
//	    if (Composite.PROPERTY_LAYOUT.equals(name)) {
//		Layout layout = (Layout) value;
//		layout.activateUI();
//		xComposite.setLayout((com.sencha.gxt.widget.core.client.Layout) layout.getDelegate());
//		
//		//setMargin(container, "Left", layout.getMarginLeft());
//		//setMargin(container, "Top", layout.getMarginTop());
//		//setMargin(container, "Right", layout.getMarginRight());
//		//setMargin(container, "Bottom", layout.getMarginBottom());
//		
//		return;
//	    }
//	}
//	
//	
//	super.setProperty(element, name, value);
//    }
//    
//    private void setMargin(LayoutContainer container, String name, int value) {
//	if (value <= 0) {
//	    return;
//	}
//	container.setStyleAttribute("margin" + name, "" + value + "px");
//    }

}
