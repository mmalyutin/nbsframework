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

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SortButtonRenderer;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SortTableHeader;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SortTableHeaderListener;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SwingTableModel;
import org.plazmaforge.framework.uwt.swing.widget.XScrollPane;
import org.plazmaforge.framework.uwt.swing.widget.XTable;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Viewer.AutoResizeColumns;
import org.plazmaforge.framework.uwt.widget.Viewer.SelectionMode;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;


public class SwingTableAdapter extends SwingCompositeAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	Table table = (Table) element;
	java.awt.Container xParent = getContent(parent.getDelegate());
	
	// Create default table model (column = 0, row = 0)
	DefaultTableColumnModel tableColumnModel = new DefaultTableColumnModel();
	SwingTableModel tableModel = new SwingTableModel((Table) element, tableColumnModel);
	tableModel.setEditable(false);
	
	XTable xTable = new XTable(tableModel, tableColumnModel);
	xTable.setAutoCreateColumnsFromModel(false);
	
	// Reset auto resize columns
	//xTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

	if (table.isSortable()) {
	    JTableHeader header = new SortTableHeader();
	    header.setColumnModel(xTable.getColumnModel());
	    xTable.setTableHeader(header);
	    
	    SortTableHeaderListener headerListener = new SortTableHeaderListener(tableModel, (SortButtonRenderer) header.getDefaultRenderer());
	    header.addMouseListener(headerListener);
	    header.addMouseMotionListener(headerListener);
	}
	
	//DefaultTableCellHeaderRenderer r = (DefaultTableCellHeaderRenderer) xTable.getTableHeader().getDefaultRenderer();
	
	// Add scroll wrapper because the JTable has not scrolling 
	JScrollPane scrollpane = new XScrollPane(xTable);
	
	addToParent(xParent, scrollpane, element);	
	return scrollpane; 
    }

    @Override
    protected java.awt.Component getViewComponent(Object delegate) {
   	return (java.awt.Component) getScrollComponent(delegate);
    }


    @Override
    public void setProperty(UIObject element, String name, Object value) {

	javax.swing.JTable xTable = getJTable(element.getDelegate());
	if (xTable == null) {
	    return;
	}
	if (eq(Table.PROPERTY_LAYOUT, name)) {
	    // ignore layout
	    return;
	} else if (eq(Table.PROPERTY_LINES_VISIBLE, name)) {
	    xTable.setShowGrid(booleanValue(value));
	    return;
	} else if (eq(Table.PROPERTY_HEADER_VISIBLE, name)) {
	    boolean visible = booleanValue(value);
	    xTable.getTableHeader().setVisible(visible);
	    xTable.getTableHeader().setPreferredSize(visible ? null : new java.awt.Dimension(-1, 0));
	    return;
	} else if (eq(Table.PROPERTY_DATA_LIST, name)) {
	    List<Object> dataList = (List<Object>) value;
	    SwingTableModel tableModel = (SwingTableModel) xTable.getModel();
	    tableModel.setDataList(dataList);
	    return;
	} else if (eq(Table.PROPERTY_SELECTION_INDEX, name)) {
	    //TODO
	    //xTable.setSelectedRow((Integer) value);
	} else if (eq(Table.PROPERTY_AUTO_RESIZE_COLUMNS, name)) {
	    
	    AutoResizeColumns autoResizeMode = (AutoResizeColumns) value;
	    
	    // - OFF	?
	    // - ALL	Yes
	    // - LAST	?

	    // TODO: DISABLE
	    /*
	    if (autoResizeMode == null || AutoResizeColumns.OFF == autoResizeMode) {
		xTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
	    } else if (AutoResizeColumns.ALL == autoResizeMode) {
		xTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
	    } else if (AutoResizeColumns.LAST == autoResizeMode) {
		xTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
	    }
	    */
	    
	    return;
	} else if (eq(Table.PROPERTY_SELECTION_MODE, name)) {
	    
	    SelectionMode selectionMode = (SelectionMode) value;
	    
	    if (selectionMode == null || SelectionMode.ROW == selectionMode) {
		xTable.setRowSelectionAllowed(true);
		xTable.setColumnSelectionAllowed(false);
	    } else if (SelectionMode.CELL == selectionMode) {
		xTable.setCellSelectionEnabled(true);
	    }
	    return;
	}
	
	super.setProperty(element, name, value);

    }
    
    @Override
    public Object getProperty(UIObject element, String name) {
	javax.swing.JTable xTable = getJTable(element.getDelegate());
	if (xTable == null) {
	    return null;
	}
	if (eq(Table.PROPERTY_SELECTION_INDEX, name)) {
	    return xTable.getSelectedRow();
	}
	return super.getProperty(element, name);
    }
    
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	Table table = (Table) element;
	javax.swing.JTable xTable = getJTable(element.getDelegate());
	if (eq(Table.METHOD_GET_SELECTION_INDEX, methodName)) {
	    if (xTable == null) {
		return -1;
	    }
	    return xTable.getSelectedRow();
	} else if (eq(Table.METHOD_UPDATE_COLUMNS, methodName)) {
	    SwingTableModel tableModel = (SwingTableModel) xTable.getModel();
	    // Reset sort columns mode
	    if (table.isSortable()) {
		TableColumn sortColumn = table.getSortColumn();
		int columnIndex = -1;
		if (sortColumn != null) {
		    columnIndex = table.indexOfColumn(sortColumn);
		}
		boolean asc = table.isAscSortColumn();
		tableModel.setSortColumn(columnIndex);
		tableModel.setAscending(asc);
		JTableHeader xTableHeader = xTable.getTableHeader();
		xTableHeader.repaint();
	    }
	}
	return super.invoke(element, methodName, args);
    }
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	javax.swing.JTable xTable = getJTable(element.getDelegate());
	if (xTable == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xTable.getSelectionModel().addListSelectionListener(createListSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    xTable.addKeyListener(createKEnterListener(control, listener));
	    xTable.addMouseListener(createMEnterListener(control, listener));
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
    
    
    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	Control control = (Control) element;
	javax.swing.JTable xTable = getJTable(element.getDelegate());
	if (xTable == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xTable.getSelectionModel().removeListSelectionListener(getListSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    xTable.removeKeyListener(getKeyListener(control, listener, 0));
	    xTable.removeMouseListener(getMouseListener(control, listener, 1));
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }

}
