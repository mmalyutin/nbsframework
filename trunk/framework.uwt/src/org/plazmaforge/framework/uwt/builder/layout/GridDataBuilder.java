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
package org.plazmaforge.framework.uwt.builder.layout;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.widget.LayoutData;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

/**
 * @author ohapon
 *
 */
public class GridDataBuilder extends AbstractLayoutDataBuilder {

    @Override
    public boolean accept(String type) {
	return UIBuilder.GRID_DATA_TYPE.equals(type) || "GridData".equals(type);
    }
    
    protected LayoutData createLayoutData(IData data) {
	return new GridData();
    }
    
    protected void populate(IData data, UIObject element) {
	if (data == null || element == null) {
	    return;
	}
	GridData layoutData = (GridData) element;
	
	// Span
	Integer columnSpan = getInteger(data, GridData.PROPERTY_COLUMN_SPAN);
	if  (columnSpan != null) {
	    layoutData.setColumnSpan(columnSpan);
	}
	Integer rowSpan = getInteger(data, GridData.PROPERTY_ROW_SPAN);
	if  (rowSpan != null) {
	    layoutData.setRowSpan(rowSpan);
	}
	
	// Align
	HorizontalAlign horizontalAlign = getHorizontalAlign(data, GridData.PROPERTY_HORIZONTAL_ALIGN);
	if  (horizontalAlign != null) {
	    layoutData.setHorizontalAlign(horizontalAlign);
	}
	VerticalAlign verticalAlign = getVerticalAlign(data, GridData.PROPERTY_VERTICAL_ALIGN);
	if  (verticalAlign != null) {
	    layoutData.setVerticalAlign(verticalAlign);
	}
	
	// Flex
	Boolean horizontalFlex = getBoolean(data, GridData.PROPERTY_HORIZONTAL_FLEX);
	if  (horizontalFlex != null) {
	    layoutData.setHorizontalFlex(horizontalFlex);
	}
	Boolean verticalFlex = getBoolean(data, GridData.PROPERTY_VERTICAL_FLEX);
	if  (verticalFlex != null) {
	    layoutData.setVerticalFlex(verticalFlex);
	}
	
	
    }
}
