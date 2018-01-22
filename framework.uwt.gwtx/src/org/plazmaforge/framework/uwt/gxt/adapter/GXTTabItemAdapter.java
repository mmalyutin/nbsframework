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
import org.plazmaforge.framework.uwt.gxt.widget.XTabItem;
import org.plazmaforge.framework.uwt.gxt.widget.XTabPanel;
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;

import com.google.gwt.resources.client.ImageResource;

/**
 * 
 * @author ohapon
 *
 */
public class GXTTabItemAdapter extends GXTCompositeAdapter {

   
    public Object createDelegate(UIObject parent, UIObject element) {

	
	XTabPanel xParent = (XTabPanel) parent.getDelegate();
	XTabItem xTabItem = new XTabItem();
	TabItem tabItem = (TabItem) element;
	
	// Get text
	String text = tabItem.getTitle();
	if (text != null) {
	    xTabItem.setText(text);
	}
	
	// Get icon
	ImageResource xIcon = createImage(element, tabItem.getIcon());
	if (xIcon != null) {
	    xTabItem.setIcon(xIcon);
	}
	
	Layout layout = tabItem.getLayout();
	
	// Default implementation with special container wrapper
	// Create internal content by layout
	XLayoutContainer content = createLayoutContainer(layout);
	
	// Add magic shift by Y for content of TabItem for GridLayout only
	// We have problem with position of elements in GridLayout
	// content.setShiftY(XTabPanel.MAGIC_TAB_HEIGHT);
	
	// Set absolute position of content of TabItem
	content.setAbsolutePosition();

	// Special adding
	xParent.add(content, xTabItem);
	
	return xTabItem;
    }
    

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	XTabItem xTabItem = (XTabItem) element.getDelegate();
	if (xTabItem == null) {
	    return;
	}
	if (TabItem.PROPERTY_TITLE.equals(name)) {
	    xTabItem.setText(asSafeString(value));
	    return;
	} else if (TabItem.PROPERTY_ICON.equals(name)) {
	    ImageResource xIcon = createImage(element, asImage(value));
	    if (xIcon != null) {
		xTabItem.setIcon(xIcon);
	    }
	    return;
	} else if (TabItem.PROPERTY_ICON_PATH.equals(name)) {
	    ImageResource xIcon = createImage(element, asString(value));
	    if (xIcon != null) {
		xTabItem.setIcon(xIcon);
	    }
	    return;
	}
	super.setProperty(element, name, value);
    }
}
