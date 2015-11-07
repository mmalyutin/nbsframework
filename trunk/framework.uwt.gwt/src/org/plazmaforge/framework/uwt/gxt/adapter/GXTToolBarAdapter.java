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
import org.plazmaforge.framework.uwt.gxt.layout.XGridData;
import org.plazmaforge.framework.uwt.gxt.layout.XGridLayout;
import org.plazmaforge.framework.uwt.widget.Composite;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

public class GXTToolBarAdapter extends GXTCompositeAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	LayoutContainer xCoolBar = (LayoutContainer) parent.getDelegate();
	XGridLayout xLayout = (XGridLayout) xCoolBar.getLayout();
	int count = xCoolBar.getItemCount();
	
	// If CoolBar has ToolBars the reset HORIZONTAL FLEX flag
	if (count > 0) {
	    List<Component> items = xCoolBar.getItems();
	    for (Component c: items) {
		Object ld = c.getLayoutData();
		if (ld != null && ld instanceof XGridData) {
		    ((XGridData) ld).setHorizontalFlex(false);
		}
	    }
	    count++;
	    xLayout.setColumnCount(count);
	}
	
	
	com.extjs.gxt.ui.client.widget.toolbar.ToolBar xToolBar = new  com.extjs.gxt.ui.client.widget.toolbar.ToolBar();
	
	XGridData xLayoutData = new XGridData();
	xLayoutData.setHorizontalSpan(1);
	xLayoutData.setVerticalSpan(1);
	xLayoutData.setHorizontalAlign(XGridData.HorizontalAlignment.FILL);
	xLayoutData.setHorizontalFlex(true);
	xToolBar.setLayoutData(xLayoutData);
	
	xCoolBar.add(xToolBar);
	
	//addToParent(getParentWidget(parent.getDelegate()), xToolBar, element); // Add to parent. Use super method
	
	return xToolBar;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.extjs.gxt.ui.client.widget.toolbar.ToolBar xToolBar = (com.extjs.gxt.ui.client.widget.toolbar.ToolBar) element.getDelegate();
	if (xToolBar == null) {
	    return;
	}
	
	if (Composite.PROPERTY_LAYOUT.equals(name) || Composite.PROPERTY_LAYOUT_DATA.equals(name) || Composite.PROPERTY_BACKGROUND.equals(name)) {
	    // ignore
	    return;
	}
	
	super.setProperty(element, name, value);
    }

}
