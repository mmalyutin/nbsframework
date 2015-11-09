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

package org.plazmaforge.framework.uwt.swt.adapter;

import java.util.List;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTTableContentProvider;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTTableLabelProvider;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTTableLayout;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTTablePainter;
import org.plazmaforge.framework.uwt.swt.util.SWTUtils;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Viewer.AutoResizeColumns;
import org.plazmaforge.framework.uwt.widget.Viewer.SelectionMode;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class SWTTableAdapter extends SWTCompositeAdapter {

    public static final String SYS_PROPETY_TABLE = "$table";
    
    public static final String SYS_PROPETY_TABLE_VIEWER = "$tableViewer";
    
    public static final String SYS_PROPETY_TABLE_CURSOR = "$tableCursor";
    
    
    
    
    public Object createDelegate(UIObject parent, UIObject element) {
	final Table table = (Table) element;
	final org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	int style = SWT.BORDER | SWT.FULL_SELECTION;
	if (table.isCheckSelection()) {
	    style |= SWT.CHECK;
	}
	final org.eclipse.swt.widgets.Table xTable = new org.eclipse.swt.widgets.Table(xParent, style);
	xTable.setHeaderVisible(true); // Header visible by default
	xTable.setLinesVisible(true); // Lines visible by default
	final SWTTablePainter tablePainter = new SWTTablePainter();
	
	org.eclipse.swt.widgets.Listener paintListener = new org.eclipse.swt.widgets.Listener() {
	    public void handleEvent(org.eclipse.swt.widgets.Event event) {
		switch (event.type) {
		case SWT.MeasureItem: {
		    tablePainter.onMeasureItem(xTable, event);
		    break;
		}
		case SWT.EraseItem: {
		    tablePainter.onEraseItem(xTable, event);
		    break;
		}
		case SWT.PaintItem: {
		    tablePainter.onPaintItem(xTable, event);
		    break;
		}
		}
	    }
	};

	xTable.addListener(SWT.MeasureItem, paintListener);
	xTable.addListener(SWT.EraseItem, paintListener);
	xTable.addListener(SWT.PaintItem, paintListener);
	    
	TableViewer xTableViewer = new TableViewer(xTable) {
	    public void  editElement(Object element, int column) { 
		super.editElement(element, column);
	    }
	    
	    public org.eclipse.jface.viewers.CellEditor[]  getCellEditors() {
		return super.getCellEditors();
	    }
	};
	xTable.setData(SYS_PROPETY_TABLE_VIEWER, xTableViewer); // Assign SWT Viewer
	xTable.setData(SYS_PROPETY_TABLE, table); // Assign UWT Table
	
	xTableViewer.setCellModifier(createCellModifier(xTableViewer));
	addToParent(xParent, xTable, element);
	return xTable;
    }
    
    
    @Override
    public void checkDelegate(UIObject element) {
	// clear super method
    }

    
    
   
    
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.Table xTable = (org.eclipse.swt.widgets.Table) element.getDelegate();
	if (xTable == null) {
	    return;
	}
	org.eclipse.jface.viewers.TableViewer xTableViewer = (org.eclipse.jface.viewers.TableViewer) xTable.getData(SYS_PROPETY_TABLE_VIEWER);
	org.plazmaforge.framework.uwt.widget.table.Table table = (org.plazmaforge.framework.uwt.widget.table.Table) element;
	
	if (Table.PROPERTY_LAYOUT.equals(name)) {
	    // ignore layout
	    return;
	} else if (Table.PROPERTY_LINES_VISIBLE.equals(name)) {
	    xTable.setLinesVisible(booleanValue(value));
	    return;
	} else if (Table.PROPERTY_HEADER_VISIBLE.equals(name)) {
	    xTable.setHeaderVisible(booleanValue(value));
	    return;
	} else if (Table.PROPERTY_DATA_LIST.equals(name)) {
	    
	    List<Object> dataList = (List<Object>) value;
	    IContentProvider contentProvider = xTableViewer.getContentProvider();
	    if (contentProvider != null) {
		contentProvider.dispose();
	    }
	    
	    SWTTableContentProvider newContentProvider = new SWTTableContentProvider(table.getDataProvider());
	    newContentProvider.setDataList(dataList);
	    xTableViewer.setContentProvider(newContentProvider);
	    
	    xTableViewer.setLabelProvider(new SWTTableLabelProvider(table));
	    xTableViewer.setInput("");
	    return;
	} else if (Table.PROPERTY_SELECTION_INDEX.equals(name)) {
	    xTable.setSelection((Integer) value);
	    return;
	} else if (Table.PROPERTY_AUTO_RESIZE_COLUMNS.equals(name)) {
	    
	    AutoResizeColumns autoResizeMode = (AutoResizeColumns) value;
	    
	    // - OFF	Yes
	    // - ALL	Yes
	    // - LAST	No

	    if (autoResizeMode == null || AutoResizeColumns.OFF == autoResizeMode) {
		SWTTableLayout tableLayout = (SWTTableLayout) xTable.getLayout();
		if (tableLayout != null) {
		    tableLayout.dispose(); 
		}
		xTable.setLayout(null);
	    } else if (AutoResizeColumns.ALL == autoResizeMode) {
		SWTTableLayout tableLayout = (SWTTableLayout) xTable.getLayout();
		if (tableLayout == null) {
		    tableLayout = createTableLayout(xTable);
		    xTable.setLayout(tableLayout);
		}
		tableLayout.setAutoResize(SWTTableLayout.AutoResize.ALL);
	    } else if (AutoResizeColumns.LAST == autoResizeMode) {
		SWTTableLayout tableLayout = (SWTTableLayout) xTable.getLayout();
		if (tableLayout == null) {
		    tableLayout = createTableLayout(xTable);
		    xTable.setLayout(tableLayout);
		}
		tableLayout.setAutoResize(SWTTableLayout.AutoResize.LAST);
	    }
	    return;
	} else if (Table.PROPERTY_SELECTION_MODE.equals(name)) {
	    
	    SelectionMode selectionMode = (SelectionMode) value;
	    
	    if (selectionMode == null || SelectionMode.ROW == selectionMode) {
		//TODO
	    } else if (SelectionMode.CELL == selectionMode) {
		//TODO
		org.eclipse.swt.custom.TableCursor tableCursor = createTableCursor(xTable);
		xTable.setData(SYS_PROPETY_TABLE_CURSOR, tableCursor);
		
	    }
	    return;
	} else if (Table.PROPERTY_CHECK_SELECTION.equals(name)) {
	    //TODO: Nothing, because SWT.CHEK style we can set in SWT Table constructor only
	    return;
	}
	
	super.setProperty(element, name, value);
    }
    
    @Override
    public Object getProperty(UIObject element, String name) {
	org.eclipse.swt.widgets.Table xTable = (org.eclipse.swt.widgets.Table) element.getDelegate();
	if (xTable == null) {
	    return null;
	}
	if (Table.PROPERTY_SELECTION_INDEX.equals(name)) {
	    return xTable.getSelectionIndex();
	}
	return super.getProperty(element, name);
    }

    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	Table table = (Table) element;
	org.eclipse.swt.widgets.Table xTable = (org.eclipse.swt.widgets.Table) element.getDelegate();
	if (eq(Table.METHOD_GET_SELECTION_INDEX, methodName)) {
	    if (xTable == null) {
		return -1;
	    }
	    return xTable.getSelectionIndex();
	} else if (eq(Table.METHOD_UPDATE_COLUMNS, methodName)) {
	    // Reset sort columns mode
	    SWTUtils.resetSortColumn(xTable);
	    if (table.isSortable()) {
		TableColumn sortColumn = table.getSortColumn();
		if (sortColumn == null) {
		    return null;
		}
		org.eclipse.swt.widgets.TableColumn xColumn = (org.eclipse.swt.widgets.TableColumn) sortColumn.getDelegate();
		boolean asc = table.isAscSortColumn();
		SWTUtils.setSortColumn(xColumn, asc);
	    }
	    return null;
	}
	return super.invoke(element, methodName, args);
    }
    
    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	org.eclipse.swt.widgets.Table xTable = (org.eclipse.swt.widgets.Table) element.getDelegate();
	if (xTable == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xTable.addSelectionListener(createSelectionListener(control, listener));
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
	org.eclipse.swt.widgets.Table xTable = (org.eclipse.swt.widgets.Table) element.getDelegate();
	if (xTable == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xTable.removeSelectionListener(getSelectionListener(control, listener));
	    return;
	} else if (eq(Events.Enter, eventType)) {
	    xTable.removeKeyListener(getKeyListener(control, listener, 0));
	    xTable.removeMouseListener(getMouseListener(control, listener, 1));
	    return;
	} 

	super.removeListener(element, eventType, listener);
    }
    
    private SWTTableLayout createTableLayout(org.eclipse.swt.widgets.Table xTable) {
	SWTTableLayout tableLayout = new SWTTableLayout(xTable);
	return tableLayout;
    }
    
 
    protected org.eclipse.swt.custom.TableCursor createTableCursor(
	    final org.eclipse.swt.widgets.Table table) {

	// http://www.java2s.com/Tutorial/Java/0280__SWT/TableCursorKeyevent.htm

	// create a TableCursor to navigate around the table
	final TableViewer xTableViewer = (TableViewer) table.getData(SYS_PROPETY_TABLE_VIEWER);
	final org.eclipse.swt.custom.TableCursor cursor = new org.eclipse.swt.custom.TableCursor(table, SWT.NONE);

	cursor.addSelectionListener(new SelectionAdapter() {
	    
	    // when the TableEditor is over a cell, select the corresponding row in the table
	    public void widgetSelected(SelectionEvent e) {
		table.setSelection(new TableItem[] { cursor.getRow() });
	    }

	    // when the user hits "ENTER" in the TableCursor, pop up a text editor so that
	    // they can change the text of the cell
	    public void widgetDefaultSelected(SelectionEvent e) {

		TableItem row = cursor.getRow();
		int column = cursor.getColumn();
		Object element = row.getData();

		// Activate editor
		editElement(xTableViewer, cursor, element, column);

	    }
	});

	cursor.addMouseListener(new MouseAdapter() {
	    public void mouseDown(MouseEvent e) {

		TableItem row = cursor.getRow();
		int column = cursor.getColumn();
		Object element = row.getData();

		// Activate editor
		editElement(xTableViewer, cursor, element, column);

	    }
	});

	// Hide the TableCursor when the user hits the "CTRL" or "SHIFT" key.
	// This alows the user to select multiple items in the table.
	cursor.addKeyListener(new KeyAdapter() {
	    public void keyPressed(KeyEvent e) {
		if (e.keyCode == SWT.CTRL || e.keyCode == SWT.SHIFT
			|| (e.stateMask & SWT.CONTROL) != 0
			|| (e.stateMask & SWT.SHIFT) != 0) {
		    cursor.setVisible(false);
		}
	    }
	});
	
	// Show the TableCursor when the user releases the "SHIFT" or "CTRL" key.
	// This signals the end of the multiple selection task.
	table.addKeyListener(new KeyAdapter() {
	    public void keyReleased(KeyEvent e) {
		if (e.keyCode == SWT.CONTROL && (e.stateMask & SWT.SHIFT) != 0)
		    return;
		if (e.keyCode == SWT.SHIFT && (e.stateMask & SWT.CONTROL) != 0)
		    return;
		if (e.keyCode != SWT.CONTROL
			&& (e.stateMask & SWT.CONTROL) != 0)
		    return;
		if (e.keyCode != SWT.SHIFT && (e.stateMask & SWT.SHIFT) != 0)
		    return;

		TableItem[] selection = table.getSelection();
		TableItem row = (selection.length == 0) ? table.getItem(table
			.getTopIndex()) : selection[0];
		table.showItem(row);
		cursor.setSelection(row, 0);
		cursor.setVisible(true);
		cursor.setFocus();
	    }
	});

	// oha
	cursor.setBackground(table.getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION));
	cursor.setForeground(table.getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));

	return cursor;

    }
    
    protected void editElement(TableViewer xViewer, org.eclipse.swt.custom.TableCursor cursor, Object element, int column) {
	cursor.setVisible(false);
	xViewer.editElement(element, column);
    }
    
    protected org.eclipse.jface.viewers.ICellModifier createCellModifier(final TableViewer xViewer) {
	final org.eclipse.swt.widgets.Table xTable = xViewer.getTable();
	final Table table = (Table) xTable.getData(SYS_PROPETY_TABLE);

	org.eclipse.jface.viewers.ICellModifier cellModifier = new org.eclipse.jface.viewers.ICellModifier() {

	    public boolean canModify(Object element, String property) {
		return table.hasCellEditor(property);
	    }

	    public Object getValue(Object element, String property) {
		return table.getValue(element, property);
	    }

	    public void modify(Object element, String property, Object value) {
		if (element instanceof TableItem) {
		    element = ((TableItem) element).getData();
		}
		table.setValue(element, property, value);
		xViewer.refresh(element);
	    }
	};

	return cellModifier;
    }    
    
}
