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
import org.plazmaforge.framework.uwt.widget.Composite;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * 
 * @author ohapon
 *
 */
public class GXTToolBarAdapter extends GXTCompositeAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	HasWidgets xParent = (HasWidgets) parent.getDelegate(); // = (com.sencha.gxt.widget.core.client.container.Container) parent.getDelegate();
	com.sencha.gxt.widget.core.client.toolbar.ToolBar xToolBar = new com.sencha.gxt.widget.core.client.toolbar.ToolBar();
	
	addToParent(getContent(parent.getDelegate()), xToolBar, element); // Add to parent. Use super method
	return xToolBar;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.sencha.gxt.widget.core.client.toolbar.ToolBar xToolBar = (com.sencha.gxt.widget.core.client.toolbar.ToolBar) element.getDelegate();
	if (xToolBar == null) {
	    return;
	}
	
	if (Composite.PROPERTY_LAYOUT.equals(name) /*|| Composite.PROPERTY_LAYOUT_DATA.equals(name)*/ || Composite.PROPERTY_BACKGROUND.equals(name)) {
	    // ignore
	    return;
	}
	
	super.setProperty(element, name, value);
    }

}
