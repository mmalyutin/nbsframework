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

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;

import com.google.gwt.user.client.ui.HasWidgets;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;

import org.plazmaforge.framework.uwt.gxt.layout.XBoxLayout;
import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.widget.XVerticalLayoutContainer;
import org.plazmaforge.framework.uwt.layout.BoxLayout;

/**
 * 
 * @author ohapon
 *
 */
public class GXTBoxLayoutAdapter extends GXTLayoutAdapter {

    
    public Object createDelegate(UIElement parent, UIElement element) {
	BoxLayout layout = (BoxLayout) element;  
	XBoxLayout xLayout = createLayout(layout);
	return xLayout;
    }
    
    protected XBoxLayout createLayout(BoxLayout layout) {
	return new XBoxLayout(layout.getOrientation().equals(Orientation.HORIZONTAL) ? com.sencha.gxt.core.client.Style.Orientation.HORIZONTAL : com.sencha.gxt.core.client.Style.Orientation.VERTICAL);
    }

    protected XBoxLayout getXBoxLayout(Object delegate) {
	return (XBoxLayout) delegate;
    }

    @Override
    public HasWidgets createContainer(XLayout xLayout) {
	com.sencha.gxt.core.client.Style.Orientation xOrientation = xLayout == null ? null : ((XBoxLayout) xLayout).getOrientation();
	if (xOrientation == com.sencha.gxt.core.client.Style.Orientation.VERTICAL) {
	    //return new VBoxLayoutContainer();
	    return new XVerticalLayoutContainer();
	}
	// by default
	return new HBoxLayoutContainer();
    }
    
    
}
