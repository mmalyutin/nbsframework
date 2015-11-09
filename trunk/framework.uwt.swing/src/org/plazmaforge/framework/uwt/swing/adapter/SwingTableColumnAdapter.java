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


import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SwingTableCellRenderer;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SwingTableModel;
import org.plazmaforge.framework.uwt.widget.CellEditor;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;


public class SwingTableColumnAdapter extends SwingWidgetAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	
	javax.swing.JTable xTable = getJTable(parent.getDelegate());
	TableColumn tableColumn  = (TableColumn) element;
	
	// We use SwingTableModel only
	SwingTableModel xTableModel = (SwingTableModel) xTable.getModel();
	javax.swing.table.TableColumnModel xTableColumnModel = xTable.getColumnModel();
	
	int columnCount = xTableColumnModel.getColumnCount();
	int columnIndex = columnCount;
	String columnName = tableColumn.getText();
	
	//TODO: Bad identifier
	String columnIdentifier = columnName;
	
	if (columnIdentifier == null) {
	    columnIdentifier = "" + columnIndex;
	}
	
	javax.swing.table.TableColumn xTableColumn = new javax.swing.table.TableColumn();
	xTableColumn.setIdentifier(columnIdentifier);
	xTableColumn.setHeaderValue(columnName == null ? " " : columnName);
	xTableColumn.setModelIndex(columnIndex);
	xTableColumn.setCellRenderer(new SwingTableCellRenderer(tableColumn));
	
	
	xTableColumnModel.addColumn(xTableColumn);
	
	return xTableColumn;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	javax.swing.JTable xTable = getJTable(element.getUIParent().getDelegate());
	javax.swing.table.TableColumn xTableColumn = (javax.swing.table.TableColumn) element.getDelegate();
	if (xTableColumn == null) {
	    return;
	}
	if (TableColumn.PROPERTY_PROPERTY.equals(name)) {
	    //do nothing because 'property' of column doesn't use in Swing Table
	    return;
	} else if (TableColumn.PROPERTY_TEXT.equals(name)) {
	    xTableColumn.setHeaderValue(getSafeString(value));
	    return;
	} else if (TableColumn.PROPERTY_WIDTH.equals(name)) {
	    xTableColumn.setWidth(intValue(value));
	    return;
	} else if (TableColumn.PROPERTY_FORMAT.equals(name)) {
	    // do nothing because format resolve in CellRenerrer
	    return;
	} else if (TableColumn.PROPERTY_ALIGN.equals(name)) {
	    TableCellRenderer renderer = xTableColumn.getCellRenderer();
	    if (renderer == null || !(renderer instanceof JLabel)) {
		return;
	    }
	    JLabel label = (JLabel) renderer;
	    HorizontalAlign align = (HorizontalAlign) value;
	    if (HorizontalAlign.LEFT.equals(align)) {
		label.setHorizontalAlignment(JLabel.LEFT);
	    } else if (HorizontalAlign.RIGHT.equals(align)) {
		label.setHorizontalAlignment(JLabel.RIGHT);
	    } else if (HorizontalAlign.CENTER.equals(align)) {
		label.setHorizontalAlignment(JLabel.CENTER);
	    }
	    
	    return;
	} else if (TableColumn.PROPERTY_CELL_EDITOR.equals(name)) {
	    CellEditor cellEditor = (CellEditor) value;
	    TableColumn tableColumn = (TableColumn) element;
	    
	    javax.swing.table.TableCellEditor xCellEditor = createCellEditor(xTable, tableColumn, cellEditor);
	    xTableColumn.setCellEditor(xCellEditor);
	    
	    return;    
	}


	super.setProperty(element, name, value);
    }
    
    protected javax.swing.table.TableCellEditor createCellEditor(javax.swing.JTable xTable, TableColumn tableColumn, CellEditor cellEditor) {
	if (cellEditor == null) {
	    return null;
	}
	return SwingHelper.createCellEditor(xTable, tableColumn, cellEditor);
    }
    
}
