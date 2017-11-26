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
import org.plazmaforge.framework.uwt.widget.panel.TabItem;

import com.google.gwt.resources.client.ImageResource;

/**
 * 
 * @author ohapon
 *
 */
public class GXTTabItemAdapter extends GXTCompositeAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	com.sencha.gxt.widget.core.client.TabPanel xParent = (com.sencha.gxt.widget.core.client.TabPanel) parent.getDelegate();
	com.sencha.gxt.widget.core.client.TabItemConfig xTabItem = new com.sencha.gxt.widget.core.client.TabItemConfig();
	TabItem tabItem = (TabItem) element;
	
	
	if (tabItem.getTitle() != null) {
	    xTabItem.setText(asSafeString(tabItem.getTitle()));
	}
	
	// Add default Layout
	//TODO:MIGRATION
	//xTabItem.setLayout(createDefaultCompositeLayout());

	XLayoutContainer content = new XLayoutContainer();
	
	// Special adding
	xParent.add(content, xTabItem);
	
	return xTabItem;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.sencha.gxt.widget.core.client.TabItemConfig xTabItem = (com.sencha.gxt.widget.core.client.TabItemConfig) element.getDelegate();
	if (xTabItem == null) {
	    return;
	}
	if (TabItem.PROPERTY_TITLE.equals(name)) {
	    xTabItem.setText(asSafeString(value));
	    return;
	} else if (TabItem.PROPERTY_ICON.equals(name)) {
	    ImageResource xImage = createImage(element, asImage(value));
	    if (xImage != null) {
		xTabItem.setIcon(xImage);
	    }
	    return;
	} else if (TabItem.PROPERTY_ICON_PATH.equals(name)) {
	    ImageResource xImage = createImage(element, asString(value));
	    if (xImage != null) {
		xTabItem.setIcon(xImage);
	    }
	    return;
	}

	
	super.setProperty(element, name, value);
    }
}
