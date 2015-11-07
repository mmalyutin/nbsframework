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

import java.util.List;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.gwt.GWTUtils;
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.GXTTableCellRenderer;
import org.plazmaforge.framework.uwt.gxt.widget.XColumnConfig;
import org.plazmaforge.framework.uwt.widget.CellEditor;
import org.plazmaforge.framework.uwt.widget.CellRenderer;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;


public class GXTTableColumnAdapter extends GXTWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	TableColumn column = (TableColumn) element;
	Table table = column.getTable();
	com.extjs.gxt.ui.client.widget.grid.Grid xGrid = (com.extjs.gxt.ui.client.widget.grid.Grid) parent.getDelegate();
	com.extjs.gxt.ui.client.widget.grid.ColumnModel cm = xGrid.getColumnModel();
	List<com.extjs.gxt.ui.client.widget.grid.ColumnConfig> columns = cm.getColumns();
	
	XColumnConfig xColumn = new XColumnConfig();
	xColumn.setGrid(xGrid);
	
	//TODO: Bad identifier
	// By default ID is index of column
	String columnIdentifier = "" + columns.size();
	xColumn.setId(columnIdentifier); 
	
	// Set sortable mode of column
	setSortable(xColumn, table == null ? false : table.isSortable(), column.isSortable()); 
	columns.add(xColumn);
	xGrid.reconfigure(xGrid.getStore(), new com.extjs.gxt.ui.client.widget.grid.ColumnModel(columns));
	return xColumn;
    }
    
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	TableColumn column = (TableColumn) element;
	XColumnConfig xColumn = (XColumnConfig) element.getDelegate();
	if (xColumn == null) {
	    return;
	}
	com.extjs.gxt.ui.client.widget.grid.Grid grid = (com.extjs.gxt.ui.client.widget.grid.Grid) xColumn.getGrid();
	
	if (TableColumn.PROPERTY_PROPERTY.equals(name)) {
	    xColumn.setId((String) value);
	    return;
	} else if (TableColumn.PROPERTY_TEXT.equals(name)) {
	    xColumn.setHeader((String) value);
	    return;
	} else if (TableColumn.PROPERTY_WIDTH.equals(name)) {
	    xColumn.setWidth((Integer) value);
	    return;
	} else if (TableColumn.PROPERTY_FORMAT.equals(name)) {
	    
	    // Get pattern
	    String pattern = (String) value;

	    
	    // Number format
	    NumberFormat numberFormat = GWTUtils.createNumberFormat(pattern);
	    if (numberFormat != null ) {
		xColumn.setNumberFormat(numberFormat);
		return;
	    }
	    
	    // Date format
	    DateTimeFormat dateTimeFormat = GWTUtils.createDateTimeFormat(pattern);
	    if (dateTimeFormat != null ) {
		xColumn.setDateTimeFormat(dateTimeFormat);
		return;
	    }
	    
	    return;
	    
	} else if (TableColumn.PROPERTY_ALIGN.equals(name)) {
	    HorizontalAlign align = (HorizontalAlign) value;
	    if (HorizontalAlign.LEFT.equals(align)) {
		xColumn.setAlignment(com.extjs.gxt.ui.client.Style.HorizontalAlignment.LEFT);
	    } else if (HorizontalAlign.RIGHT.equals(align)) {
		xColumn.setAlignment(com.extjs.gxt.ui.client.Style.HorizontalAlignment.RIGHT);
	    } else if (HorizontalAlign.CENTER.equals(align)) {
		xColumn.setAlignment(com.extjs.gxt.ui.client.Style.HorizontalAlignment.CENTER);
	    }
	    return;
	    
	} else if (TableColumn.PROPERTY_LABEL_PROVIDER.equals(name)) {
	    GXTTableCellRenderer xRenderer = (GXTTableCellRenderer)  xColumn.getRenderer();
	    if  (xRenderer == null) {
		xRenderer = new GXTTableCellRenderer(column.getTable(), column);
		xColumn.setRenderer(xRenderer);
	    }
	    xRenderer.setLabelProvider((LabelProvider) value);
	    return;
	} else if (TableColumn.PROPERTY_CELL_RENDERER.equals(name)) {
	    GXTTableCellRenderer xRenderer = (GXTTableCellRenderer)  xColumn.getRenderer();
	    if  (xRenderer == null) {
		xRenderer = new GXTTableCellRenderer(column.getTable(), column);
		xColumn.setRenderer(xRenderer);
	    }
	    xRenderer.setCellRenderer((CellRenderer) value);
	    
	    return;
	} else if (TableColumn.PROPERTY_CELL_EDITOR.equals(name)) {
	    CellEditor cellEditor = (CellEditor) value;
	    com.extjs.gxt.ui.client.widget.grid.CellEditor xCellEditor = createCellEditor(grid, column, cellEditor);
	    xColumn.setEditor(xCellEditor);
	    
	    return;    
	}
	
	
	//Return because TableColumn is not Widget.
	//We have error if use super.setProperty()
	//super.setProperty(element, name, value);
    }
    
    protected void reconfigure(com.extjs.gxt.ui.client.widget.grid.Grid grid) {
	if (grid == null) {
	    return;
	}
	grid.reconfigure(grid.getStore(), grid.getColumnModel());
    }
    
    protected void setSortable(XColumnConfig xColumn, boolean isTableSortable, boolean isColumnSortable) {
	boolean isSortable = isTableSortable && isColumnSortable;
	//xColumn.setMenuDisabled(!isSortable); // MENU IS DISABLED ALWAYS
	xColumn.setSortable(isSortable);
    }
    

    protected com.extjs.gxt.ui.client.widget.grid.CellEditor createCellEditor(com.extjs.gxt.ui.client.widget.grid.Grid xGrid, TableColumn column, CellEditor cellEditor) {
	if (cellEditor == null) {
	    return null;
	}
	return GXTHelper.createCellEditor(xGrid, column, cellEditor);
    }
}
