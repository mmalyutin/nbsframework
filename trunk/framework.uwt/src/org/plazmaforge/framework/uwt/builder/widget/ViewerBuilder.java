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

package org.plazmaforge.framework.uwt.builder.widget;

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Column;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Viewer;

public abstract class ViewerBuilder extends CompositeBuilder {

    @Override
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	Viewer<?> composite = createViewer();
	populate(data, composite);
	return composite;
    }
    
    protected abstract Viewer<?> createViewer();
    
    protected void populateColumn(IData data, Column column) {
	
	String text = getRSString(data, Column.PROPERTY_TEXT);
	if (text != null) {
	    column.setText(text);
	}
	
	//TODO
	String iconPath = getImagePath(data, Column.PROPERTY_ICON);
	if (iconPath != null) {
	    column.setIcon(iconPath);
	}

	Integer width = getInteger(data, Column.PROPERTY_WIDTH);
	if (width != null) {
	    column.setWidth(width);
	}

	String property = getString(data, Column.PROPERTY_PROPERTY);
	if (property != null) {
	    column.setProperty(property);
	}

	String dataType = getString(data, Column.PROPERTY_DATA_TYPE);
	if (dataType != null) {
	    column.setDataType(dataType);
	}

	String format = getString(data, Column.PROPERTY_FORMAT);
	if (format != null) {
	    column.setFormat(format);
	}
	
	HorizontalAlign align = getHorizontalAlign(data, Column.PROPERTY_ALIGN);
	if (align != null) {
	    column.setAlign(align);
	}

	Boolean sortable = getBoolean(data, Column.PROPERTY_SORTABLE);
	if (sortable != null) {
	    column.setSortable(sortable);
	}
	
	Boolean filterable = getBoolean(data, Column.PROPERTY_FILTERABLE);
	if (filterable != null) {
	    column.setFilterable(filterable);
	}

	Boolean resizable = getBoolean(data, Column.PROPERTY_RESIZABLE);
	if (resizable != null) {
	    column.setResizable(resizable);
	}

	Boolean moveable = getBoolean(data, Column.PROPERTY_MOVEABLE);
	if (moveable != null) {
	    column.setMoveable(moveable);
	}

	Boolean editable = getBoolean(data, Column.PROPERTY_EDITABLE);
	if (editable != null) {
	    column.setEditable(editable);
	}
	
	
	//TODO:
	// - CellRenderer
	// - CellEditor
	// - ValuePresenter
	// - ValueProvider
	// - LabelProvider

    }

    protected void populateItems(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	Viewer viewer = (Viewer) element;
	List<String> items = getItemValues(String.class, data);
	if (items == null || items.isEmpty()) {
	    return;
	}
	viewer.setItems(items);
    }
    
    protected void populateBody(IData data, UIObject element) {
	//reset
    }
    
  
}
