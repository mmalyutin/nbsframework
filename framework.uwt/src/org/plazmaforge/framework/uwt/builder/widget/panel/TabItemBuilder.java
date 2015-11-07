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

/**
 * 
 */
package org.plazmaforge.framework.uwt.builder.widget.panel;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.builder.widget.CompositeBuilder;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;


/**
 * @author ohapon
 *
 */
public class TabItemBuilder extends CompositeBuilder {

    @Override
    public boolean accept(String type) {
	return eq(type, UIBuilder.TAB_ITEM_TYPE);
    }

    @Override
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	TabItem panel = new TabItem();
	populate(data, panel);
	return panel;
    }

    @Override
    protected void populateCommon(IData data, UIObject element) {
	super.populate(data, element);
	
	TabItem tabItem = (TabItem) element;
	
	String title = getRSString(data, TabItem.PROPERTY_TITLE);
  	if (title != null) {
  	    tabItem.setTitle(title);
  	}
  	
  	String iconPath = getImagePath(data, TabItem.PROPERTY_ICON); // image attribute as image path
	if (iconPath != null) {
	    tabItem.setIcon(iconPath);
	}
    }

}
