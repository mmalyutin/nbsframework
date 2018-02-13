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
import org.plazmaforge.framework.uwt.gxt.layout.XGridLayout;
import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.widget.XGridLayoutContainer;
import org.plazmaforge.framework.uwt.layout.GridLayout;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * 
 * @author ohapon
 *
 */
public class GXTGridLayoutAdapter extends GXTLayoutAdapter {

    
    public Object createDelegate(UIElement parent, UIElement element) {
	GridLayout layout = (GridLayout) element;  
	XGridLayout xLayout = new XGridLayout(layout.getColumnCount());
	
	// Spacing
	xLayout.setVerticalSpacing(layout.getVerticalSpacing());
	xLayout.setHorizontalSpacing(layout.getHorizontalSpacing());
	
	xLayout.setFix(layout.isFix());
	
	// Margin
	xLayout.setMarginLeft(layout.getMarginLeft());
	xLayout.setMarginTop(layout.getMarginTop());
	xLayout.setMarginRight(layout.getMarginRight());
	xLayout.setMarginBottom(layout.getMarginBottom());
	
	return xLayout;
    }

    protected XGridLayout getXGridLayout(Object delegate) {
	return (XGridLayout) delegate;
    }

    @Override
    public HasWidgets createContainer(XLayout xLayout) {
	//XGridLayoutContainer2 gridLayoutContainer = new  XGridLayoutContainer2((XGridLayout) xLayout);
	XGridLayoutContainer gridLayoutContainer = new  XGridLayoutContainer((XGridLayout) xLayout);
	return gridLayoutContainer;
    }
    

}
