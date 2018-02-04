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

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.util.CoreUtils;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.gwt.GWTUtils;
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.GXTTableCellRenderer;
import org.plazmaforge.framework.uwt.gxt.adapter.viewer.XValueProvider;
import org.plazmaforge.framework.uwt.gxt.data.Model;
import org.plazmaforge.framework.uwt.gxt.widget.XColumnConfig;
import org.plazmaforge.framework.uwt.gxt.widget.XGrid;
import org.plazmaforge.framework.uwt.gxt.widget.cell.XBaseCell;
import org.plazmaforge.framework.uwt.widget.CellEditor;
import org.plazmaforge.framework.uwt.widget.CellRenderer;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

/**
 * 
 * @author ohapon
 *
 */
public class GXTTableColumnAdapter extends GXTWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	TableColumn column = (TableColumn) element;
	Table<?> table = column.getTable();
	
	XGrid xGrid = (XGrid) parent.getDelegate();
	com.sencha.gxt.widget.core.client.grid.ColumnModel<Model> cm = xGrid.getColumnModel();
	List<com.sencha.gxt.widget.core.client.grid.ColumnConfig<Model, ?>> columns = CoreUtils.cloneList(cm.getColumns());
	
	// Create column
	XColumnConfig<?> xColumn = createColumn(table, column, false);
	xColumn.setGrid(xGrid);
	setSortable(xColumn, table == null ? false : table.isSortable(), column.isSortable()); 

	// Add column
	columns.add(xColumn);

	xGrid.reconfigure(xGrid.getStore(), new com.sencha.gxt.widget.core.client.grid.ColumnModel<Model>(columns));
	
	return xColumn;
    }
    
    public XColumnConfig<?> createColumn(Table<?> table, TableColumn column, boolean external) {
	
	if (external) {
	    // WARNING! Very important initialization
	    column.create();	    
	}
   
	// Get columns properties
	String property = column.getProperty();
	PropertyProvider<?> propertyProvider = table.getPropertyProvider();
	ValueProvider<?> valueProvider = column.getValueProvider();
	String text = column.getText();
	int width = column.getWidth();
	HorizontalAlign align = column.getAlign();
	
	// Create column
	XColumnConfig<?> xColumn = new XColumnConfig<Object>(createXValueProvider(property, propertyProvider, valueProvider), width, asSafeString(text));
	
	// Create cell by data type
	Cell cell = GWTUtils.createCell(column.getDataType(), column.getFormat());
	if (cell == null) {
	    cell = new XBaseCell();
	}
	//if (cell != null) {
	    xColumn.setCell(cell);
	//}

	if (column.getCellRenderer() != null) {
	    GXTTableCellRenderer xRenderer = new GXTTableCellRenderer(column.getTable(), column);
	    xColumn.setCellRenderer(xRenderer);
	    xRenderer.setCellRenderer(column.getCellRenderer());
	}
	
	setAlign(xColumn, align);
	
	column.resetInitProperty(TableColumn.PROPERTY_TEXT);
	column.resetInitProperty(TableColumn.PROPERTY_PROPERTY);
	column.resetInitProperty(TableColumn.PROPERTY_WIDTH);
	column.resetInitProperty(TableColumn.PROPERTY_ALIGN);
	column.resetInitProperty(TableColumn.PROPERTY_DATA_TYPE);
	column.resetInitProperty(TableColumn.PROPERTY_FORMAT);
	column.resetInitProperty(TableColumn.PROPERTY_VALUE_PROVIDER);
	column.resetInitProperty(TableColumn.PROPERTY_LABEL_PROVIDER);
	column.resetInitProperty(TableColumn.PROPERTY_CELL_RENDERER);
	
	
	return xColumn;
    }
    
    
    protected void setAlign(XColumnConfig<?> xColumn, HorizontalAlign align) {
	if (HorizontalAlign.LEFT.equals(align)) {
	    xColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	} else if (HorizontalAlign.RIGHT.equals(align)) {
	    xColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	} else if (HorizontalAlign.CENTER.equals(align)) {
	    xColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	}
    }
    
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	TableColumn column = (TableColumn) element;
	XColumnConfig<?> xColumn = (XColumnConfig<?>) element.getDelegate();
	if (xColumn == null) {
	    return;
	}
	XGrid grid = (XGrid) xColumn.getGrid();
	
	if (TableColumn.PROPERTY_PROPERTY.equals(name)) {
	    XValueProvider xValueProvider = (XValueProvider) xColumn.getValueProvider();
	    xValueProvider.setProperty(asString(value));
	    return;
	} else if (TableColumn.PROPERTY_TEXT.equals(name)) {
	    xColumn.setHeader(asString(value));
	    return;
	} else if (TableColumn.PROPERTY_WIDTH.equals(name)) {
	    xColumn.setWidth(asInteger(value));
	    return;
	} else if (TableColumn.PROPERTY_FORMAT.equals(name)) {
	    
	    // Get pattern
	    String pattern = asString(value);
	    if (pattern == null) {
		return;
	    }
	    
	    //TODO: maybe type=dataType
	    Cell cell = GWTUtils.createCell(null, pattern);
	    if (cell == null) {
		return;
	    }
	    xColumn.setCell(cell);
	    return;
	    
	} else if (TableColumn.PROPERTY_ALIGN.equals(name)) {
	    HorizontalAlign align = (HorizontalAlign) value;
	    setAlign(xColumn, align);
	    return;
	    
	} else if (TableColumn.PROPERTY_LABEL_PROVIDER.equals(name)) {
	    GXTTableCellRenderer xRenderer = (GXTTableCellRenderer)  xColumn.getCellRenderer();
	    if  (xRenderer == null) {
		xRenderer = new GXTTableCellRenderer(column.getTable(), column);
		xColumn.setCellRenderer(xRenderer);
	    }
	    xRenderer.setLabelProvider((LabelProvider) value);
	    return;
	} else if (TableColumn.PROPERTY_CELL_RENDERER.equals(name)) {
	    GXTTableCellRenderer xRenderer = (GXTTableCellRenderer)  xColumn.getCellRenderer();
	    if  (xRenderer == null) {
		xRenderer = new GXTTableCellRenderer(column.getTable(), column);
		xColumn.setCellRenderer(xRenderer);
	    }
	    xRenderer.setCellRenderer((CellRenderer) value);
	    return;
//	}   else if (TableColumn.PROPERTY_CELL_EDITOR.equals(name)) {
//	    CellEditor cellEditor = (CellEditor) value;
//	   com.sencha.gxt.widget.core.client.grid.CellEditor xCellEditor = createCellEditor(grid, column, cellEditor);
//	    xColumn.setEditor(xCellEditor);
//	    
//	    return;    
	}
	
	
	//Return because TableColumn is not Widget.
	//We have error if use super.setProperty()
	//super.setProperty(element, name, value);
    }
    
    public void setSortable(XColumnConfig<?> xColumn, boolean isTableSortable, boolean isColumnSortable) {
	boolean isSortable = isTableSortable && isColumnSortable;
	//xColumn.setMenuDisabled(!isSortable); // MENU IS DISABLED ALWAYS
	xColumn.setSortable(isSortable);
    }
    
  //DISABLE:MIGRATION
//    protected com.sencha.gxt.widget.core.client.grid.CellEditor createCellEditor(com.sencha.gxt.widget.core.client.grid.Grid xGrid, TableColumn column, CellEditor cellEditor) {
//	if (cellEditor == null) {
//	    return null;
//	}
//	return GXTHelper.createCellEditor(xGrid, column, cellEditor);
//    }
}
