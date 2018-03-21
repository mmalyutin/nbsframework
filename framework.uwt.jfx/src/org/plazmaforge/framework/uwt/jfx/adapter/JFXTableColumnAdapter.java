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
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.jfx.util.JFXUtils;
import org.plazmaforge.framework.uwt.jfx.widget.cell.XCellFactory;
import org.plazmaforge.framework.uwt.jfx.widget.cell.XCellValueFactory;
import org.plazmaforge.framework.uwt.jfx.widget.cell.XPropertyValueFactory;
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
	javafx.scene.control.TableView<?> xTable = (javafx.scene.control.TableView) parent.getDelegate();
	javafx.scene.control.TableColumn xColumn = createColumn(table, column, false);
	
	xTable.getColumns().add(xColumn);
	return xColumn;
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
	Image icon = column.getIcon();
	int width = column.getWidth();
	HorizontalAlign align = column.getAlign();
	
	// Create column with text
	javafx.scene.control.TableColumn xColumn = new javafx.scene.control.TableColumn(text);
	
	// Set icon
	javafx.scene.image.ImageView xIcon = createImageView(column, icon);
	if (xIcon != null) {
	    xColumn.setGraphic(xIcon);
	}
	    
	// width
	xColumn.setPrefWidth(width);
	
	// Create CellValueFactory
	xColumn.setCellValueFactory(createCellValueFactory(property, propertyProvider, valueProvider));

	// Create CellFactory by data type
	XCellFactory cellFactory = createCellFactory(column.getDataType(), column.getFormat());
	if (cellFactory != null) {
	    xColumn.setCellFactory(cellFactory);
	}
		
	column.resetInitProperty(TableColumn.PROPERTY_TEXT);
	column.resetInitProperty(TableColumn.PROPERTY_ICON);
	column.resetInitProperty(TableColumn.PROPERTY_ICON_PATH);
	column.resetInitProperty(TableColumn.PROPERTY_PROPERTY);
	column.resetInitProperty(TableColumn.PROPERTY_WIDTH);
	//column.resetInitProperty(TableColumn.PROPERTY_ALIGN);
	column.resetInitProperty(TableColumn.PROPERTY_DATA_TYPE);
	column.resetInitProperty(TableColumn.PROPERTY_FORMAT);
	//column.resetInitProperty(TableColumn.PROPERTY_VALUE_PROVIDER);
	//column.resetInitProperty(TableColumn.PROPERTY_LABEL_PROVIDER);
	//column.resetInitProperty(TableColumn.PROPERTY_CELL_RENDERER);
	
	return xColumn;
    }
    

    @Override
    public void setProperty(UIElement element, String name, Object value) {
	TableColumn column = (TableColumn) element;
	javafx.scene.control.TableColumn xTableColumn = (javafx.scene.control.TableColumn) element.getDelegate();
	if (xTableColumn == null) {
	    return;
	}
	//javafx.scene.control.TableView xTable = (javafx.scene.control.TableView) parent.getDelegate();	
	//javafx.scene.control.TableView xTable = xTableColumn.getParent();
	
	if (TableColumn.PROPERTY_PROPERTY.equals(name)) {
	    PropertyProvider<?> propertyProvider = column.getTable() == null ? null : column.getTable().getPropertyProvider();
	    ValueProvider<?> valueProvider = column.getValueProvider();
	    xTableColumn.setCellValueFactory(createCellValueFactory(asString(value), propertyProvider, valueProvider));
	    return;
	} else if (TableColumn.PROPERTY_TEXT.equals(name)) {
	    xTableColumn.setText(asString(value));
	    return;
	} else if (TableColumn.PROPERTY_ICON.equals(name)) {
	    javafx.scene.image.ImageView xIcon = createImageView(element, asImage(value));
	    if (xIcon != null) {
		xTableColumn.setGraphic(xIcon);
	    }
	    return;
	} else if (TableColumn.PROPERTY_ICON_PATH.equals(name)) {
	    javafx.scene.image.ImageView xIcon = createImageView(element, asString(value));
	    if (xIcon != null) {
		xTableColumn.setGraphic(xIcon);
	    }
	    return;		    
	} else if (TableColumn.PROPERTY_WIDTH.equals(name)) {
	    xTableColumn.setPrefWidth(intValue(value));
	    return;
	} else if (TableColumn.PROPERTY_FORMAT.equals(name)) {
	    // Get pattern
	    String pattern = asString(value);
	    if (pattern == null) {
		return;
	    }
	    
	    //TODO: maybe type=dataType
	    XCellFactory cellFactory = createCellFactory(null, pattern);
	    if (cellFactory == null) {
		return;
	    }
	    xTableColumn.setCellFactory(cellFactory);
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
