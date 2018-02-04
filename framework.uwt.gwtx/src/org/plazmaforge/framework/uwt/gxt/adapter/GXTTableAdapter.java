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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.util.CoreUtils;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.gxt.data.Model;
import org.plazmaforge.framework.uwt.gxt.widget.XColumnConfig;
import org.plazmaforge.framework.uwt.gxt.widget.XGrid;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.Viewer.AutoResizeColumns;
import org.plazmaforge.framework.uwt.widget.Viewer.SelectionMode;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import com.sencha.gxt.widget.core.client.grid.CellSelectionModel;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;

/**
 * 
 * @author ohapon
 *
 */
public class GXTTableAdapter extends GXTViewerAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	Table<?> table = (Table<?>) element;
	List<ColumnConfig<Model, ?>> configs = new ArrayList<ColumnConfig<Model, ?>>(); 
	com.sencha.gxt.widget.core.client.grid.ColumnModel<Model> cm = new com.sencha.gxt.widget.core.client.grid.ColumnModel<Model>(configs);
	com.sencha.gxt.data.shared.ListStore<Model> store = createXDefaultListStore();
	XGrid xGrid = new  XGrid(store, cm);
	xGrid.setTable(table); // Assign UWT Table
	
	List<com.sencha.gxt.widget.core.client.grid.ColumnConfig<Model, ?>> columns = null;
	int columnCount = table.getColumnCount();
	if (table.isCheckSelection() || columnCount > 0) {
	    columns = CoreUtils.cloneList(cm.getColumns());
	}
	
	// Multi selection mode
	if (table.isCheckSelection()) {
	    CheckBoxSelectionModel<Model> selectionModel = new CheckBoxSelectionModel<Model>();
	    //selectionModel.setSelectionMode(Style.SelectionMode.SIMPLE);
	    xGrid.setSelectionModel(selectionModel);

	    ColumnConfig<Model, ?> xColumn = selectionModel.getColumn();
	    columns.add(xColumn);
	}

	// Add columns
	if (columnCount > 0) {
	    if (columns == null) {
		columns = CoreUtils.cloneList(cm.getColumns());
	    }
	    GXTTableColumnAdapter adapter = new GXTTableColumnAdapter();
	    for (int i = 0; i < columnCount; i++) {
		TableColumn column = table.getColumn(i);
		XColumnConfig<?> xColumn = adapter.createColumn(table, column, true);
		xColumn.setGrid(xGrid);
		adapter.setSortable(xColumn, table == null ? false : table.isSortable(), column.isSortable()); 
		columns.add(xColumn);
	    }
	}
	
	if  (columns != null) {
	    xGrid.reconfigure(xGrid.getStore(), new com.sencha.gxt.widget.core.client.grid.ColumnModel<Model>(columns));
	}
	
	table.resetInitProperty(Table.PROPERTY_COLUMNS);
	
	xGrid.setWidth(Table.DEFAULT_WIDTH); // TODO
	xGrid.setHeight(150 /*Table.DEFAULT_HEIGHT*/); // TODO
	
	//DISABLE:MIGRATION
	//xGrid.setAutoWidth(true);
	//xGrid.setAutoHeight(true);
	
	//xGrid.getView().setAutoFill(true);
	//Reset auto resize mode
	//xGrid.getView().setForceFit(true);
                
	addChild(getContent(parent.getDelegate()), xGrid, element); // Add to parent
	
	return xGrid;
    }

    protected XGrid asGrid(Object delegate) {
	return (XGrid) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	Table<?> table = (Table<?>) element;
	XGrid xGrid = asGrid(element.getDelegate());
	if (xGrid == null) {
	    return;
	}
	if (Table.PROPERTY_LAYOUT.equals(name)) {
	    // ignore layout
	    return;
	} else if (Table.PROPERTY_LINES_VISIBLE.equals(name)) {
	    // TODO: Only column lines. Must implement row lines
	    
	    //DISABLE:MIGRATION
	    //xGrid.setColumnLines(booleanValue(value));
	    
	    return;
	} else if (Table.PROPERTY_HEADER_VISIBLE.equals(name)) {
	    xGrid.setHideHeaders(!booleanValue(value)); // inverse value
	    return;
	} else if (Table.PROPERTY_DATA_LIST.equals(name)) {
	    
	    // Get DataList
	    List<?> dataList = (List<?>) value;
	    
	    // Populate ListStore by flat DataList
	    com.sencha.gxt.data.shared.ListStore<Model> store = xGrid.getStore();
	    store.clear();
	    populateListStore2(table, dataList, store);
	    
	    xGrid.reconfigure(store, xGrid.getColumnModel());
	    return;
	} else if (Table.PROPERTY_SELECTION_INDEX.equals(name)) {
	    com.sencha.gxt.data.shared.ListStore<Model> store = xGrid.getStore();
	    int index = (Integer) value;
	    
	    //DISABLE:MIGRATION
	    //Model data = store.getAt(index);
	    
	    
	    List<Model> list = new ArrayList<Model>();
	    xGrid.getSelectionModel().setSelection(list);
	    return;
	} else if (Table.PROPERTY_AUTO_RESIZE_COLUMNS.equals(name)) {
	    AutoResizeColumns autoResizeMode = (AutoResizeColumns) value;
	    
	    // - OFF	Yes
	    // - ALL	Yes
	    // - LAST	No
	    
	    if (autoResizeMode == null || AutoResizeColumns.OFF == autoResizeMode) {
		xGrid.getView().setForceFit(false);
	    } else if (AutoResizeColumns.ALL == autoResizeMode) {
		xGrid.getView().setForceFit(true);
	    } else if (AutoResizeColumns.LAST == autoResizeMode) {
		//TODO: Not implemented
	    }
	    
	    //boolean autoResize = Table.AUTO_RESIZE_ALL_COLUMNS == autoResizeMode;
	    //xGrid.getView().setForceFit(autoResize);
	    
	    return;
	} else if (Table.PROPERTY_SELECTION_MODE.equals(name)) {

	    //CheckBoxSelectionModel<ModelData> selectionModel = new CheckBoxSelectionModel<ModelData>();
	    //selectionModel.setSelectionMode(Style.SelectionMode.SIMPLE);
	    //xGrid.setSelectionModel(selectionModel);
	    
	    SelectionMode selectionMode = (SelectionMode) value;
	    
	    if (selectionMode == null || SelectionMode.ROW == selectionMode) {
		GridSelectionModel<Model> selectionModel = new GridSelectionModel<Model>();
		
		//DISABLE:MIGRATION
		//selectionModel.setSelectionMode(Style.SelectionMode.SINGLE);
		
		xGrid.setSelectionModel(selectionModel);
	    } else if (SelectionMode.CELL == selectionMode) {
		if (xGrid.getSelectionModel() != null && !((xGrid.getSelectionModel() instanceof CheckBoxSelectionModel))) {
		    xGrid.setSelectionModel(new CellSelectionModel<Model>());
		}
	    }
	    return;
	} else if (Table.PROPERTY_CHECK_SELECTION.equals(name)) {
	    //xGrid.setSelectionModel(new CheckBoxSelectionModel<ModelData>());
	}

	super.setProperty(element, name, value);
    }
    
    @Override
    public Object getProperty(UIObject element, String name) {
	XGrid xGrid = asGrid(element.getDelegate());
	if (xGrid == null) {
	    return null;
	}
	if (Table.PROPERTY_SELECTION_INDEX.equals(name)) {
	    Model data = xGrid.getSelectionModel().getSelectedItem();
	    com.sencha.gxt.data.shared.ListStore<Model> store = xGrid.getStore();
	    return store.indexOf(data); 
	}
	return super.getProperty(element, name);
    }


    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	if (Table.METHOD_GET_SELECTION_INDEX.equals(methodName)) {
	    XGrid xGrid = asGrid(element.getDelegate());
	    if (xGrid == null) {
		return -1;
	    }
	    Model data = xGrid.getSelectionModel().getSelectedItem();
	    com.sencha.gxt.data.shared.ListStore<Model> store = xGrid.getStore();
	    return store.indexOf(data); 
	}
	return super.invoke(element, methodName, args);
    }

    @Override
    protected void addSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	// GWT Selection (item)
	asGrid(xWidget).getSelectionModel().addSelectionHandler(createModelSelectionListener(widget, listener));
    }
    
    @Override
    protected void removeSelectionListener(com.google.gwt.user.client.ui.Widget xWidget, Widget widget, Listener listener) {
	//asGrid(xWidget).getSelectionModel().removeListener(com.sencha.gxt.ui.client.event.Events.SelectionChange,// getListener(widget, listener)); //TODO
    }
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	XGrid xGrid = asGrid(element.getDelegate());
	if (xGrid == null) {
	    return;
	}
	
	if (eq(Events.Selection, eventType)) {
	    addSelectionListener(xGrid, control, listener);
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    addEnterListener(xGrid, control, listener);
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
    
    
    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	XGrid xGrid = asGrid(element.getDelegate());
	if (xGrid == null) {
	    return;
	}
	
	if (eq(Events.Selection, eventType)) {
	    removeSelectionListener(xGrid, control, listener);
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    removeEnterListener(xGrid, control, listener);
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }
}
