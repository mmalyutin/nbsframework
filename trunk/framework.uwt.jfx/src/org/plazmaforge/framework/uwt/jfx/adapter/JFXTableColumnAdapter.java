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


import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.jfx.util.JFXUtils;
import org.plazmaforge.framework.uwt.jfx.widget.cell.XCellFactory;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.CellEditor;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import javafx.scene.control.cell.PropertyValueFactory;

/**
 * 
 * @author ohapon
 *
 */
public class JFXTableColumnAdapter extends JFXWidgetAdapter {

    public static final String SYS_PROPETY_TABLE_COLUMN = "$tableColmn";
    
    @Override
    public Object createDelegate(UIElement parent, UIElement element) {
	
	TableColumn column = (TableColumn) element;
	Table<?> table = column.getTable();
	
	TableColumn tableColumn = (TableColumn) element;
	javafx.scene.control.TableView xTable = (javafx.scene.control.TableView) parent.getDelegate();
	javafx.scene.control.TableColumn xTableColumn = createColumn(table, column, false);
	
	xTable.getColumns().add(xTableColumn);
	return xTableColumn;
    }

    public javafx.scene.control.TableColumn createColumn(Table<?> table, TableColumn column, boolean external) {
	
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
	
	javafx.scene.control.TableColumn xTableColumn = new javafx.scene.control.TableColumn();
	
	
	// Create cell by data type
	XCellFactory cell = JFXUtils.createCell(column.getDataType(), column.getFormat());
	if (cell != null) {
	    xTableColumn.setCellFactory(cell);
	}

		
	//column.resetInitProperty(TableColumn.PROPERTY_TEXT);
	//column.resetInitProperty(TableColumn.PROPERTY_PROPERTY);
	//column.resetInitProperty(TableColumn.PROPERTY_WIDTH);
	//column.resetInitProperty(TableColumn.PROPERTY_ALIGN);
	//column.resetInitProperty(TableColumn.PROPERTY_DATA_TYPE);
	column.resetInitProperty(TableColumn.PROPERTY_FORMAT);
	//column.resetInitProperty(TableColumn.PROPERTY_VALUE_PROVIDER);
	//column.resetInitProperty(TableColumn.PROPERTY_LABEL_PROVIDER);
	//column.resetInitProperty(TableColumn.PROPERTY_CELL_RENDERER);
	
	return xTableColumn;
    }
    
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	javafx.scene.control.TableColumn xTableColumn = (javafx.scene.control.TableColumn) element.getDelegate();
	if (xTableColumn == null) {
	    return;
	}
	//javafx.scene.control.TableView xTable = (javafx.scene.control.TableView) parent.getDelegate();	
	//javafx.scene.control.TableView xTable = xTableColumn.getParent();
	
	if (TableColumn.PROPERTY_PROPERTY.equals(name)) {
	    xTableColumn.setCellValueFactory(new PropertyValueFactory<Object, Object>(asString(value)));
	    return;
	} else if (TableColumn.PROPERTY_TEXT.equals(name)) {
	    xTableColumn.setText(asString(value));
	    return;
	} else if (TableColumn.PROPERTY_WIDTH.equals(name)) {
	    xTableColumn.setPrefWidth(intValue(value));
	    return;
	} else if (TableColumn.PROPERTY_FORMAT.equals(name)) {
	    // TODO
	    // Get pattern
	    String pattern = asString(value);
	    if (pattern == null) {
		return;
	    }
	    
	    //TODO: maybe type=dataType
	    XCellFactory cell = JFXUtils.createCell(null, pattern);
	    if (cell == null) {
		return;
	    }
	    xTableColumn.setCellFactory(cell);
	    return;
	} else if (TableColumn.PROPERTY_ALIGN.equals(name)) {
	    /*
	    HorizontalAlign align = (HorizontalAlign) value;
	    if (HorizontalAlign.LEFT.equals(align)) {
		xTableColumn.setAlignment(SWT.LEFT);
	    } else if (HorizontalAlign.RIGHT.equals(align)) {
		xTableColumn.setAlignment(SWT.RIGHT);
	    } else if (HorizontalAlign.CENTER.equals(align)) {
		xTableColumn.setAlignment(SWT.CENTER);
	    }
	    */
	    // TODO
	    return;
	} else if (TableColumn.PROPERTY_CELL_EDITOR.equals(name)) {
	    // TODO
	    return;    
	}
	
	super.setProperty(element, name, value);
    }
    
   
 
}
