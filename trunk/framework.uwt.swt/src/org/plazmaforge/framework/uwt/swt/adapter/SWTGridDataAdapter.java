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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.widget.Control;

public class SWTGridDataAdapter extends SWTLayoutDataAdapter {

    
    
    public Object createDelegate(UIObject parent, UIObject element) {
	
   	GridData layoutData = (GridData) element;   
   	org.eclipse.swt.layout.GridData xLayoutData = new org.eclipse.swt.layout.GridData();
   	
   	xLayoutData.horizontalSpan = layoutData.getColumnSpan();
   	xLayoutData.verticalSpan = layoutData.getRowSpan();

	xLayoutData.grabExcessHorizontalSpace = layoutData.isHorizontalFlex();
	xLayoutData.grabExcessVerticalSpace = layoutData.isVerticalFlex();

   	populateHorizontalAlign(xLayoutData, layoutData);
   	populateVerticalAlign(xLayoutData, layoutData);

	populateLayoutSize(layoutData, xLayoutData);
	
   	return xLayoutData;
    }

    protected org.eclipse.swt.layout.GridData getXGridData(Object delegate) {
	return (org.eclipse.swt.layout.GridData) delegate;
    }
    
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {

	org.eclipse.swt.layout.GridData xLayoutData = getXGridData(element.getDelegate());
	if (xLayoutData == null) {
	    return;
	}
	GridData layoutData = (GridData) element; 
	if (eq(GridData.PROPERTY_HORIZONTAL_ALIGN, name)) {
	    populateHorizontalAlign(xLayoutData, layoutData);
	    return;
	} else if (eq(GridData.PROPERTY_VERTICAL_ALIGN, name)) {
	    populateVerticalAlign(xLayoutData, layoutData);
	    return;
	} else if (eq(GridData.PROPERTY_HORIZONTAL_FLEX, name)) {
	    xLayoutData.grabExcessHorizontalSpace = layoutData.isHorizontalFlex();
	    return;
	} else if (eq(GridData.PROPERTY_VERTICAL_FLEX, name)) {
	    xLayoutData.grabExcessVerticalSpace = layoutData.isVerticalFlex();
	    return;
	} else if (eq(GridData.PROPERTY_COLUMN_SPAN, name)) {
	    xLayoutData.horizontalSpan = layoutData.getColumnSpan();
	    return;
	} else if (eq(GridData.PROPERTY_ROW_SPAN, name)) {
	    xLayoutData.verticalSpan = layoutData.getRowSpan();
	    return;
	}
	super.setProperty(element, name, value);
    }

    
    ////
    
    protected void populateHorizontalAlign(org.eclipse.swt.layout.GridData xLayoutData, GridData layoutData) {
	HorizontalAlign hAlign = layoutData.getHorizontalAlign();
	if (HorizontalAlign.LEFT.equals(hAlign)) {
	    xLayoutData.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
	} else if (HorizontalAlign.RIGHT.equals(hAlign)) {
	    xLayoutData.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
	} else if (HorizontalAlign.CENTER.equals(hAlign)) {
	    xLayoutData.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
	} else if (HorizontalAlign.FILL.equals(hAlign)) {
	    xLayoutData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
	}
    }
    
    protected void populateVerticalAlign(org.eclipse.swt.layout.GridData xLayoutData, GridData layoutData) {
	VerticalAlign vAlign = layoutData.getVerticalAlign();
	if (VerticalAlign.TOP.equals(vAlign)) {
	    xLayoutData.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
	} else if (VerticalAlign.BOTTOM.equals(vAlign)) {
	    xLayoutData.verticalAlignment = org.eclipse.swt.layout.GridData.END;
	} else if (VerticalAlign.MIDDLE.equals(vAlign)) {
	    xLayoutData.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
	} else if (VerticalAlign.FILL.equals(vAlign)) {
	    xLayoutData.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
	}

    }
    
    protected void populateLayoutSize(Object xLayoutData, int width, int height) {
	populateLayoutSize((org.eclipse.swt.layout.GridData) xLayoutData, width, height);
    }
    
    public static void populateLayoutSize(org.eclipse.swt.layout.GridData xLayoutData, int width, int height) {
	if (width != UWT.DEFAULT) {
	    xLayoutData.widthHint = width; 
	}
	if (height != UWT.DEFAULT) {
	    xLayoutData.heightHint = height; 
	}
    }

    public Object createDefaultLayoutData(Control control) {
	org.eclipse.swt.layout.GridData xLayoutData = new org.eclipse.swt.layout.GridData();
	xLayoutData.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
	xLayoutData.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
	populateLayoutSize(control, xLayoutData);
	return xLayoutData;
    }

    public boolean isCompatible(Object xLayoutData) {
	if (xLayoutData == null) {
	    return false;
	}
	return (xLayoutData instanceof org.eclipse.swt.layout.GridData);
    }
    ////

}
