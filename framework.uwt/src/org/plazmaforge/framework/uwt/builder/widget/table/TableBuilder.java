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

package org.plazmaforge.framework.uwt.builder.widget.table;

import java.util.List;

import org.plazmaforge.framework.core.data.ArrayPropertyProvider;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.widget.ViewerBuilder;
import org.plazmaforge.framework.uwt.widget.Viewer;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class TableBuilder extends ViewerBuilder {

    @Override
    protected Viewer<?> createViewer() {
	return new Table();
    }
    
    @Override
    protected void populate(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	super.populate(data, element);

	Table<?> table = (Table<?>) element;
	
	Boolean linesVisible = getBoolean(data, Table.PROPERTY_LINES_VISIBLE);
	if (linesVisible != null) {
	    table.setLinesVisible(linesVisible);
	}
	
	Boolean headerVisible = getBoolean(data, Table.PROPERTY_HEADER_VISIBLE);
	if (headerVisible != null) {
	    table.setHeaderVisible(headerVisible);
	}

	Boolean sortable = getBoolean(data, Table.PROPERTY_SORTABLE);
	if (sortable != null) {
	    table.setSortable(sortable);
	}

	Integer selectionIndex = getInteger(data, Table.PROPERTY_SELECTION_INDEX);
	if (selectionIndex != null) {
	    table.setSelectionIndex(selectionIndex);
	}

	
	//TODO:
	// - SelectionMode
	// - CheckSelection
	// - AutoResizeColumns

	// - LabelProvider
	
	
	populateColumns(data, element);
	populateItems(data, element);
    }
    
    protected void populateColumns(IData data, UIObject element) {
	Table<?> table = (Table<?>) element;
	
	List<IData> columns = getChildrenOfNode(data, Table.PROPERTY_COLUMNS);
	if (columns == null || columns.isEmpty()) {
	    return;
	}
	TableColumn column = null;
	for (IData columnData: columns ) {
	    column = createTableColumn(columnData);
	    table.addColumn(column);
	}
	
    }

    
    protected TableColumn createTableColumn(IData data) {
	if (data == null) {
	    return null;
	}
	
	TableColumn column = new TableColumn();
	populateColumn(data, column);
	return column;
    }
    
    
    protected void populateItems(IData data, UIObject element) {
	if (data == null) {
	    return;
	}
	Table table = (Table) element;
	int columnCount = table.getColumnCount();
	Class[] types = new Class[columnCount];
	
	for (int i = 0; i < columnCount; i++) {
	    types[i] = String.class; // TODO Use column data type
	}
	List<Object[]> items = getItemRecords(types, data);
	if (items == null || items.isEmpty()) {
	    return;
	}
	PropertyProvider propertyProvider = table.getPropertyProvider();
	if (propertyProvider == null) {
	    table.setPropertyProvider(new ArrayPropertyProvider());
	    
	    for (int i = 0; i < columnCount; i++) {
		TableColumn column = table.getColumn(i);
		if (column.getProperty() == null) {
		    column.setProperty("" + i);
		}
	    }
	}
	table.setItems(items);
    }
}
