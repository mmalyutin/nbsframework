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


import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.gxt.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Layout;
//import com.sencha.gxt.ui.client.Style.Orientation;
import com.sencha.gxt.widget.core.client.container.Container;


public class GXTCompositeAdapter extends GXTControlAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	XLayoutContainer xComposite = new XLayoutContainer();
	//FlowLayoutContainer xComposite = new FlowLayoutContainer();
	//xComposite.setLayout(createDefaultCompositeLayout());
	
	//TODO: STUB
	if (parent == null) {
	    return xComposite;
	}
	
	addToParent(getContent(parent.getDelegate()), xComposite, element);
	return xComposite;
    }

    //DISABLE:MIGRATION
  
//    /**
//     * Create default layout of composite
//     * @return
//     */
//    protected com.sencha.gxt.widget.core.client.Layout createDefaultCompositeLayout() {
//	// By default set RowLayout with horizontal orientation
//	return new com.sencha.gxt.widget.core.client.layout.RowLayout(Orientation.HORIZONTAL);
//    }
//    
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
