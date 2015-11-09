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

package org.plazmaforge.framework.uwt.swing.adapter;

import java.awt.GridBagConstraints;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.layout.GridData;

public class SwingGridDataAdapter extends SwingLayoutDataAdapter {

    
    
    public Object createDelegate(UIObject parent, UIObject element) {
	
   	GridData layoutData = (GridData) element;
   	GridBagConstraints xLayoutData = new GridBagConstraints();
   	
   	xLayoutData.gridwidth = layoutData.getColumnSpan();
   	xLayoutData.gridheight = layoutData.getRowSpan();
   	
	xLayoutData.weightx = layoutData.isHorizontalFlex() ? 1 : 0;
	xLayoutData.weighty = layoutData.isVerticalFlex() ? 1 : 0;

	populateAlign(xLayoutData, layoutData);
	
   	return xLayoutData;
    }

    protected GridBagConstraints getXGridData(Object delegate) {
	return (GridBagConstraints) delegate;
    }

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	GridData layoutData = (GridData) element;
	GridBagConstraints xLayoutData = getXGridData(element.getDelegate());
	if (xLayoutData == null) {
	    return;
	}
	if (eq(GridData.PROPERTY_HORIZONTAL_ALIGN, name) || (eq(GridData.PROPERTY_VERTICAL_ALIGN, name))) {
	    populateAlign(xLayoutData, layoutData);
	    return;
	} else if (eq(GridData.PROPERTY_HORIZONTAL_FLEX, name)) {
	    xLayoutData.weightx = layoutData.isHorizontalFlex() ? 1 : 0;
	    return;
	} else if (eq(GridData.PROPERTY_VERTICAL_FLEX, name)) {
	    xLayoutData.weighty = layoutData.isVerticalFlex() ? 1 : 0;
	    return;
	} else if (eq(GridData.PROPERTY_COLUMN_SPAN, name)) {
	    xLayoutData.gridwidth = layoutData.getColumnSpan();
	    return;
	} else if (eq(GridData.PROPERTY_ROW_SPAN, name)) {
	    xLayoutData.gridheight = layoutData.getRowSpan();
	    return;
	}
	
	super.setProperty(element, name, value);

    }

    protected void populateAlign(GridBagConstraints xLayoutData, GridData layoutData) {
	HorizontalAlign hAlign = layoutData.getHorizontalAlign();
	VerticalAlign vAlign = layoutData.getVerticalAlign();
	
	if (HorizontalAlign.LEFT.equals(hAlign) && VerticalAlign.TOP.equals(vAlign)) {
	    xLayoutData.anchor = GridBagConstraints.NORTHWEST;
	} else if (HorizontalAlign.CENTER.equals(hAlign) && VerticalAlign.TOP.equals(vAlign)) {
	    xLayoutData.anchor = GridBagConstraints.NORTH;
	} else if (HorizontalAlign.RIGHT.equals(hAlign) && VerticalAlign.TOP.equals(vAlign)) {
	    xLayoutData.anchor = GridBagConstraints.NORTHEAST;
	} else if (HorizontalAlign.CENTER.equals(hAlign) && VerticalAlign.MIDDLE.equals(vAlign)) {
	    xLayoutData.anchor = GridBagConstraints.CENTER;
	} else if (HorizontalAlign.RIGHT.equals(hAlign) && VerticalAlign.MIDDLE.equals(vAlign)) {
	    xLayoutData.anchor = GridBagConstraints.EAST;
	} else if (HorizontalAlign.RIGHT.equals(hAlign) && VerticalAlign.BOTTOM.equals(vAlign)) {
	    xLayoutData.anchor = GridBagConstraints.SOUTHEAST;
	} else if (HorizontalAlign.CENTER.equals(hAlign) && VerticalAlign.BOTTOM.equals(vAlign)) {
	    xLayoutData.anchor = GridBagConstraints.SOUTH;
	} else if (HorizontalAlign.LEFT.equals(hAlign) && VerticalAlign.BOTTOM.equals(vAlign)) {
	    xLayoutData.anchor = GridBagConstraints.SOUTHWEST;
	} else if (HorizontalAlign.LEFT.equals(hAlign) && VerticalAlign.MIDDLE.equals(vAlign)) {
	    xLayoutData.anchor = GridBagConstraints.WEST;
	    
	} else if (HorizontalAlign.FILL.equals(hAlign) && VerticalAlign.TOP.equals(vAlign)) {
	    xLayoutData.fill = GridBagConstraints.HORIZONTAL;
	    xLayoutData.anchor = GridBagConstraints.NORTH;
	} else if (HorizontalAlign.FILL.equals(hAlign) && VerticalAlign.MIDDLE.equals(vAlign)) {
	    xLayoutData.fill = GridBagConstraints.HORIZONTAL;
	    xLayoutData.anchor = GridBagConstraints.CENTER;
	} else if (HorizontalAlign.FILL.equals(hAlign) && VerticalAlign.BOTTOM.equals(vAlign)) {
	    xLayoutData.fill = GridBagConstraints.HORIZONTAL;
	    xLayoutData.anchor = GridBagConstraints.SOUTH;

	} else if (HorizontalAlign.FILL.equals(hAlign) && VerticalAlign.FILL.equals(vAlign)) {
	    xLayoutData.fill = GridBagConstraints.BOTH;
	    xLayoutData.anchor = GridBagConstraints.CENTER;

	
	} else if (HorizontalAlign.LEFT.equals(hAlign) && VerticalAlign.FILL.equals(vAlign)) {
	    xLayoutData.fill = GridBagConstraints.VERTICAL;
	    xLayoutData.anchor = GridBagConstraints.WEST;
	} else if (HorizontalAlign.CENTER.equals(hAlign) && VerticalAlign.FILL.equals(vAlign)) {
	    xLayoutData.fill = GridBagConstraints.VERTICAL;
	    xLayoutData.anchor = GridBagConstraints.CENTER;
	} else if (HorizontalAlign.RIGHT.equals(hAlign) && VerticalAlign.FILL.equals(vAlign)) {
	    xLayoutData.fill = GridBagConstraints.VERTICAL;
	    xLayoutData.anchor = GridBagConstraints.EAST;
	}
	
    }

}
