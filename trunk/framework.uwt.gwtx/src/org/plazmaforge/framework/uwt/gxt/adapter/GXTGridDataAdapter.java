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
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

import org.plazmaforge.framework.uwt.gxt.layout.XGridData;
import org.plazmaforge.framework.uwt.layout.GridData;


public class GXTGridDataAdapter extends GXTLayoutDataAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
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
    public void setProperty(UIObject element, String name, Object value) {

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
   	if (HorizontalAlign.LEFT.equals(hAlign)) { 
   	    xLayoutData.setHorizontalAlign(HasHorizontalAlignment.ALIGN_LEFT);
   	} else if (HorizontalAlign.RIGHT.equals(hAlign)) {
   	    xLayoutData.setHorizontalAlign(HasHorizontalAlignment.ALIGN_RIGHT);
   	} else if (HorizontalAlign.CENTER.equals(hAlign)) {
   	    xLayoutData.setHorizontalAlign(HasHorizontalAlignment.ALIGN_CENTER);
   	} else if (HorizontalAlign.FILL.equals(hAlign)) {
   	    xLayoutData.setHorizontalAlign(HasHorizontalAlignment.ALIGN_JUSTIFY);
   	}
    }
    
    protected void populateVerticalAlign(XGridData xLayoutData, GridData layoutData) {
   	VerticalAlign vAlign = layoutData.getVerticalAlign();
   	if (VerticalAlign.TOP.equals(vAlign)) { 
   	    xLayoutData.setVerticalAlign(HasVerticalAlignment.ALIGN_TOP);
   	} else if (VerticalAlign.BOTTOM.equals(vAlign)) {
   	    xLayoutData.setVerticalAlign(HasVerticalAlignment.ALIGN_BOTTOM);
   	} else if (VerticalAlign.MIDDLE.equals(vAlign)) {
   	    xLayoutData.setVerticalAlign(HasVerticalAlignment.ALIGN_MIDDLE);
   	} else if (VerticalAlign.FILL.equals(vAlign)) {
   	    //xLayoutData.setVerticalAlign(HasVerticalAlignment.ALIGN_JJUSTIFY); // TODO: MIGRATE
   	}
    }

}
