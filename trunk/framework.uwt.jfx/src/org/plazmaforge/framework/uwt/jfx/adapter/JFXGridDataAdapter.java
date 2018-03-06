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

package org.plazmaforge.framework.uwt.jfx.adapter;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.jfx.layout.XGridData;
import org.plazmaforge.framework.uwt.jfx.layout.XLayoutData.HorizontalAlignment;
import org.plazmaforge.framework.uwt.jfx.layout.XLayoutData.VerticalAlignment;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

import org.plazmaforge.framework.uwt.layout.GridData;

/**
 * 
 * @author ohapon
 *
 */
public class JFXGridDataAdapter extends JFXLayoutDataAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	
   	GridData layoutData = (GridData) element;   
   	XGridData xLayoutData = new XGridData();
   	
   	xLayoutData.setColSpan(layoutData.getColumnSpan());
   	xLayoutData.setRowSpan(layoutData.getRowSpan());
   	
   	xLayoutData.setHorizontalFlex(layoutData.isHorizontalFlex());
   	xLayoutData.setVerticalFlex(layoutData.isVerticalFlex());
   	
   	populateHorizontalAlign(xLayoutData, layoutData);
   	populateVerticalAlign(xLayoutData, layoutData);
   	
   	return xLayoutData;
    }

    protected XGridData getXGridData(Object delegate) {
	return (XGridData) delegate;
    }

    @Override
    public void setProperty(UIElement element, String name, Object value) {

	XGridData xLayoutData = getXGridData(element.getDelegate());
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
	    xLayoutData.setHorizontalFlex(layoutData.isHorizontalFlex());
	    return;
	} else if (eq(GridData.PROPERTY_VERTICAL_FLEX, name)) {
	    xLayoutData.setVerticalFlex(layoutData.isVerticalFlex());
	    return;
	} else if (eq(GridData.PROPERTY_COLUMN_SPAN, name)) {
	    xLayoutData.setColSpan(layoutData.getColumnSpan());
	    return;
	} else if (eq(GridData.PROPERTY_ROW_SPAN, name)) {
	    xLayoutData.setRowSpan(layoutData.getRowSpan());
	    return;
	}
	
	super.setProperty(element, name, value);

    }

    protected void populateHorizontalAlign(XGridData xLayoutData, GridData layoutData) {
   	HorizontalAlign hAlign = layoutData.getHorizontalAlign();
   	if (hAlign == null) {
   	    xLayoutData.setHorizontalAlign(null);
   	    return;
   	}
   	
   	if (HorizontalAlign.LEFT.equals(hAlign)) { 
   	    xLayoutData.setHorizontalAlign(HorizontalAlignment.LEFT);
   	} else if (HorizontalAlign.RIGHT.equals(hAlign)) {
   	    xLayoutData.setHorizontalAlign(HorizontalAlignment.RIGHT);
   	} else if (HorizontalAlign.CENTER.equals(hAlign)) {
   	    xLayoutData.setHorizontalAlign(HorizontalAlignment.CENTER);
   	} else if (HorizontalAlign.FILL.equals(hAlign)) {
   	    xLayoutData.setHorizontalAlign(HorizontalAlignment.FILL);
   	} else {
   	    xLayoutData.setHorizontalAlign(null);
   	}
   	
    }
    
    protected void populateVerticalAlign(XGridData xLayoutData, GridData layoutData) {
   	VerticalAlign vAlign = layoutData.getVerticalAlign();
   	if (vAlign == null) {
   	    xLayoutData.setVerticalAlign(null);
   	    return;
   	}
   	
   	if (VerticalAlign.TOP.equals(vAlign)) { 
   	    xLayoutData.setVerticalAlign(VerticalAlignment.TOP);
   	} else if (VerticalAlign.BOTTOM.equals(vAlign)) {
   	    xLayoutData.setVerticalAlign(VerticalAlignment.BOTTOM);
   	} else if (VerticalAlign.MIDDLE.equals(vAlign)) {
   	    xLayoutData.setVerticalAlign(VerticalAlignment.MIDDLE);
   	} else if (VerticalAlign.FILL.equals(vAlign)) {
   	    xLayoutData.setVerticalAlign(VerticalAlignment.FILL);
   	} else {
   	    xLayoutData.setVerticalAlign(null); 
   	}
   	
    }

}
