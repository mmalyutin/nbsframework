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
import org.plazmaforge.framework.uwt.gxt.layout.XGridLayout;
import org.plazmaforge.framework.uwt.widget.Composite;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

public class GXTCoolBarAdapter extends GXTCompositeAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	LayoutContainer xCoolBar = new LayoutContainer();

	// TODO: STUB: Must use special HorizontalLayout
	XGridLayout xLayout = new XGridLayout(1);
	xLayout.setVerticalSpacing(0);
	xLayout.setHorizontalSpacing(0);

	xCoolBar.setLayout(xLayout);

	addToParent(getContent(parent.getDelegate()), xCoolBar, element);
	return xCoolBar;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	Object delegate = element.getDelegate();
	if (delegate == null) {
	    return;
	}
	
	if (Composite.PROPERTY_LAYOUT.equals(name) || Composite.PROPERTY_BACKGROUND.equals(name)) {
	    // ignore
	    return;
	}
	
	super.setProperty(element, name, value);
    }
}
