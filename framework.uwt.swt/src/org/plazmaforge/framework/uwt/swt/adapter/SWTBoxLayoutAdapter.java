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

package org.plazmaforge.framework.uwt.swt.adapter;

import org.eclipse.swt.SWT;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.layout.BoxLayout;
import org.plazmaforge.framework.uwt.widget.Layout;

public class SWTBoxLayoutAdapter extends SWTLayoutAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	BoxLayout layout = (BoxLayout) element;
	org.eclipse.swt.layout.RowLayout xLayout = createLayout(layout);
	populateLayout(layout, xLayout);
	return xLayout;
    }
    
    protected org.eclipse.swt.layout.RowLayout createLayout(BoxLayout layout) {
	return new org.eclipse.swt.layout.RowLayout(getOrientation(layout));
    }
    
    protected void populateLayout(BoxLayout layout, org.eclipse.swt.layout.RowLayout xLayout) {
	xLayout.marginLeft = layout.getMarginLeft();
	xLayout.marginTop = layout.getMarginTop();
	xLayout.marginRight = layout.getMarginRight();
	xLayout.marginBottom = layout.getMarginBottom();
    }
    

    
    protected int getOrientation(BoxLayout layout) {
	Orientation orientation = layout.getOrientation();  
	return Orientation.HORIZONTAL == orientation ? SWT.HORIZONTAL : SWT.VERTICAL;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.layout.RowLayout xLayout = (org.eclipse.swt.layout.RowLayout) element.getDelegate();
	if (xLayout == null) {
	    return;
	}
	
	if (Layout.PROPERTY_MARGIN_LEFT.equals(name)) {
	    xLayout.marginLeft = intValue(value);
	    return;
	} else if (Layout.PROPERTY_MARGIN_TOP.equals(name)) {
	    xLayout.marginTop = intValue(value);
	    return;
	} else if (Layout.PROPERTY_MARGIN_RIGHT.equals(name)) {
	    xLayout.marginRight = intValue(value);
	    return;
	} else if (Layout.PROPERTY_MARGIN_BOTTOM.equals(name)) {
	    xLayout.marginBottom = intValue(value);
	    return;
	} else if (Layout.PROPERTY_SPACING.equals(name)) {
	    xLayout.spacing = intValue(value);
	    return;
	}
	
	super.setProperty(element, name, value);
    }
}
