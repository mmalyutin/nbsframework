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

package org.plazmaforge.framework.uwt.swing.adapter.viewer;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.plazmaforge.framework.core.data.Accessor;
import org.plazmaforge.framework.uwt.util.UWTUtils;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class SwingTableModel extends DefaultTableModel {

    
    /**
     * List of data elements
     */
    private List dataList;
    
    /**
     * Editable flag
     */
    private boolean editable;
    
    /**
     *  Current sort column
     */
    private int sortColumn;


    /**
     *  Indicates ascending (true) or descending (false) order.
     */
    private boolean ascending;
    
    
    /**
     *  UWT Table
     */
    private Table table;
    
    /**
     * Swing TableColumnModel
     */
    private TableColumnModel tableColumnModel;
    
    

    public SwingTableModel(Table table, TableColumnModel tableColumnModel) {
	super();
	this.table = table;
	this.tableColumnModel = tableColumnModel;
	init();
    }
    
    private void init() {
	this.sortColumn = -1;
	this.ascending = true;
    }

    /**
     * UWT Table
     * @return
     */
    public Table getTable() {
        return table;
    }

    ////
    
    public boolean isCellEditable(int row, int column) {
	// TODO Use column configuration
	//return isEditable();
	
	TableColumn tableColumn = table.getColumn(column);
	return tableColumn.isEditable();
        
    }

    ////
    
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void setDataList(List dataList) {
	this.dataList = dataList;
	
        int count = getColumnCount();
        Vector<String> columns = new Vector<String>();
        for (int i = 0; i < count; i++ ){
            columns.add(getColumnName(i));
	}
	
        Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
        for (Object element : dataList) {
            Vector<Object> row = new Vector<Object>(count);
            for (int i = 0; i < count; i++ ) {
        	row.add(getValue(element, i));
            }
            rows.add(row);
        }
        setDataVector(rows, columns);
    }

    public List getDataList() {
        return dataList;
    }

    ////
    
    

    @Override
    public int getColumnCount() {
	return tableColumnModel.getColumnCount();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	return super.getValueAt(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	super.setValueAt(aValue, rowIndex, columnIndex);
	
	
	//TODO
	Object element = getElement(rowIndex);
	TableColumn column = table.getColumn(columnIndex);
	String property = column.getProperty();
	table.setValue(element, property, aValue);
	
	
    }

    ////
    
    protected Object getValue(Object element, int columnIndex) {
	if (element == null) {
	     return null;
	}
	
	// Get column
	TableColumn column = table.getColumn(columnIndex);
	if (column == null) {
	    return null;
	}
	
	return UWTUtils.getValue(element, table, column);
    }

    ////
    
    public Object getElement(int rowIndex) {
	if (dataList == null) {
	    return null;
	}
	return dataList.get(rowIndex);
    }

    public void setElement(Object element, int rowIndex) {
	if (dataList == null) {
	    return;
	}
	dataList.set(rowIndex, element);
	fireTableRowsUpdated(rowIndex, rowIndex);
    }

    ////
    
    protected Accessor getAccessor(Object obj, TableColumn column) {
	return UWTUtils.getAccessor(obj, column);
    }
    
    protected Accessor createAccessor(Class entityClass, String property) {
	return Accessor.getAccessor(entityClass, property);
    }
    
    protected Object getValue(Object obj, Accessor accessor) {
	return UWTUtils.getValue(obj, accessor);
    }
    
    protected String getTextValue(Object value, TableColumn column) {
	return UWTUtils.getTextValue(value, column);
    }
    
    ////
    
    /**
     * Returns the index of the sorting column, or -1 if the data is not sorted
     * on any column.
     *
     * @return the column used for sorting.
     */
    public int getSortColumn() {
        return this.sortColumn;
    }

    public void setSortColumn(int sortColumn) {
        this.sortColumn = sortColumn;
    }

    /**
     * Returns <code>true</code> if the data is sorted in ascending order, and
     * <code>false</code> otherwise.
     *
     * @return <code>true</code> if the data is sorted in ascending order, and
     *         <code>false</code> otherwise.
     */
    public boolean isAscending() {
        return this.ascending;
    }

    /**
     * Sets the flag that determines whether the sort order is ascending or
     * descending.
     *
     * @param flag  the flag.
     */
    public void setAscending(boolean flag) {
        this.ascending = flag;
    }

    /**
     * Sorts the table.
     *
     * @param column  the column to sort on (zero-based index).
     * @param ascending  a flag to indicate ascending order or descending order.
     */
    public void sortByColumn(int columnIndex, boolean ascending) {
        if (isSortable(columnIndex)) {
            this.sortColumn = columnIndex;
            TableColumn column = getUWTColumn(columnIndex);
            table.sortByColumn(column, ascending);
        }
    }

    /**
     * Returns a flag indicating whether or not a column is sortable.
     *
     * @param columnIndex  the column (zero-based index).
     *
     * @return boolean.
     */
    public boolean isSortable(int columnIndex) {
	TableColumn column = getUWTColumn(columnIndex);
        return column == null ? false : column.isSortable();
    }
    
    
    //// HELPER METHODS /////
    
    protected javax.swing.table.TableColumn getColumn(int columnIndex) {
	return tableColumnModel.getColumn(columnIndex);
    }
    
    protected TableColumn getUWTColumn(int columnIndex) {
	TableColumn column = table.getColumn(columnIndex);
	return column;
    }
    
}
