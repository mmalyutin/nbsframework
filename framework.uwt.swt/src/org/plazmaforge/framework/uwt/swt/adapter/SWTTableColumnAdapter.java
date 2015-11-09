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

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTTableLayout;
import org.plazmaforge.framework.uwt.swt.util.SWTUtils;
import org.plazmaforge.framework.uwt.widget.CellEditor;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class SWTTableColumnAdapter extends SWTWidgetAdapter {

    public static final String SYS_PROPETY_TABLE_COLUMN = "$tableColumn";
    
    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	TableColumn tableColumn = (TableColumn) element;
	org.eclipse.swt.widgets.Table xTable = (org.eclipse.swt.widgets.Table) parent.getDelegate();
	org.eclipse.swt.widgets.TableColumn xTableColumn = new org.eclipse.swt.widgets.TableColumn(xTable, SWT.NONE);
	xTableColumn.setData(SYS_PROPETY_TABLE_COLUMN, tableColumn); // Assign UWT column
	initColumn(xTableColumn);
	return xTableColumn;
    }

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	org.eclipse.swt.widgets.TableColumn xTableColumn = (org.eclipse.swt.widgets.TableColumn) element.getDelegate();
	if (xTableColumn == null) {
	    return;
	}
	org.eclipse.swt.widgets.Table xTable = xTableColumn.getParent();
	org.eclipse.jface.viewers.TableViewer xTableViewer = (org.eclipse.jface.viewers.TableViewer) xTable.getData(SWTTableAdapter.SYS_PROPETY_TABLE_VIEWER);
	
	if (TableColumn.PROPERTY_PROPERTY.equals(name)) {
	    String[] properties = getArray(xTableViewer.getColumnProperties(), xTable.getColumnCount());
	    int index = xTable.indexOf(xTableColumn);
	    properties[index] = (String) value;
	    xTableViewer.setColumnProperties(properties); // WHY ?
	    return;
	} else if (TableColumn.PROPERTY_TEXT.equals(name)) {
	    xTableColumn.setText((String) value);
	    return;
	} else if (TableColumn.PROPERTY_WIDTH.equals(name)) {
	    xTableColumn.setWidth((Integer) value);
	    return;
	} else if (TableColumn.PROPERTY_FORMAT.equals(name)) {
	    // do nothing because format resolve in label provider
	    return;
	} else if (TableColumn.PROPERTY_ALIGN.equals(name)) {
	    HorizontalAlign align = (HorizontalAlign) value;
	    if (HorizontalAlign.LEFT.equals(align)) {
		xTableColumn.setAlignment(SWT.LEFT);
	    } else if (HorizontalAlign.RIGHT.equals(align)) {
		xTableColumn.setAlignment(SWT.RIGHT);
	    } else if (HorizontalAlign.CENTER.equals(align)) {
		xTableColumn.setAlignment(SWT.CENTER);
	    }
	    return;
	} else if (TableColumn.PROPERTY_CELL_EDITOR.equals(name)) {
	    CellEditor cellEditor = (CellEditor) value;
	    TableColumn column = (TableColumn) element;
	    org.eclipse.jface.viewers.CellEditor[] xEditors = getArray2(xTableViewer.getCellEditors(), xTable.getColumnCount());
	    int index = xTable.indexOf(xTableColumn);
	    xEditors[index] = createCellEditor(xTable, column, cellEditor);
	    xTableViewer.setCellEditors(xEditors); // WHY ?
	    return;    
	}
	
	super.setProperty(element, name, value);
    }
    
   
    protected void initColumn(final org.eclipse.swt.widgets.TableColumn xTableColumn) {
	if (xTableColumn == null) {
	    return;
	}
	
	// Add sort action
	xTableColumn.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		doSortByColumn(xTableColumn);
	    }
	});
	
	xTableColumn.addListener(SWT.Resize, new org.eclipse.swt.widgets.Listener() {
	    public void handleEvent(org.eclipse.swt.widgets.Event event) {
		org.eclipse.swt.widgets.Table xTable = xTableColumn.getParent();
		SWTTableLayout tableLayout = (SWTTableLayout) xTable.getLayout();
		if (tableLayout == null) {
		    return;
		}
		ColumnWeightData d = (ColumnWeightData) tableLayout.getColumnData(xTableColumn);
		d.weight = xTableColumn.getWidth();
		tableLayout.doLayout();
	    }
	});
    }
    
    protected void doSortByColumn(final org.eclipse.swt.widgets.TableColumn xTableColumn) {
	org.eclipse.swt.widgets.Table xTable = xTableColumn.getParent();
	Table table = (Table) xTable.getData(SWTTableAdapter.SYS_PROPETY_TABLE); // Get UWT Table
	if (table == null || !table.isSortable()) {
	    // Table is not sortable
	    return;
	}
	TableColumn tableColumn = (TableColumn) xTableColumn.getData(SYS_PROPETY_TABLE_COLUMN); // Get UWT Column
	if (tableColumn == null || !tableColumn.isSortable()) {
	    // Column is not sortable
	    return;
	}
	
	// Inverse column direction
	SWTUtils.inverseSortColumn(xTableColumn);
	boolean asc = SWTUtils.isAscSortColumn(xTable); 
	table.sortByColumn(tableColumn, asc);
	
    }
    
    
    //[UTIL]
    /**
     * Return part of array
     * @param input
     * @param count
     * @return
     */
    
    protected String[] getArray(Object[] input, int count) {
	String[] output = new String[count];
	if (input == null || input.length == 0) {
	    return output;
	}
	int limit = Math.min(count, input.length);
	for (int i = 0; i <  limit; i++) {
	    output[i] = (String) input[i];
	}
	return output;
    }
    
    protected org.eclipse.jface.viewers.CellEditor[] getArray2(org.eclipse.jface.viewers.CellEditor[] input, int count) {
	org.eclipse.jface.viewers.CellEditor[] output = new org.eclipse.jface.viewers.CellEditor[count];
	if (input == null || input.length == 0) {
	    return output;
	}
	int limit = Math.min(count, input.length);
	for (int i = 0; i <  limit; i++) {
	    output[i] = input[i];
	}
	return output;
    }
    
    protected org.eclipse.jface.viewers.CellEditor createCellEditor(org.eclipse.swt.widgets.Table xTable, TableColumn tableColumn, CellEditor cellEditor) {
	if (cellEditor == null) {
	    return null;
	}
	org.eclipse.jface.viewers.CellEditor xCellEditor = SWTHelper.createCellEditor(xTable, tableColumn, cellEditor);
	return xCellEditor;
    }

}
