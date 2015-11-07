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

package org.plazmaforge.framework.uwt.widget.table;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.core.data.provider.ArrayProvider;
import org.plazmaforge.framework.core.data.provider.DataProvider;
import org.plazmaforge.framework.uwt.data.store.DataSorter;
import org.plazmaforge.framework.uwt.data.store.PropertySorter;
import org.plazmaforge.framework.uwt.data.store.PropertySorter.PropertyInfo;
import org.plazmaforge.framework.uwt.event.EnterListener;
import org.plazmaforge.framework.uwt.widget.CellEditor;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.HasSelection;
import org.plazmaforge.framework.uwt.widget.Viewer;

public class Table<T> extends Viewer<T> implements HasSelection<T> {

    
    public static final int DEFAULT_WIDTH = 300;
    
    public static final int DEFAULT_HEIGHT = 300;
    
    
    /**
     * Columns of Table
     */
    private List<TableColumn> columns;
    
    
    /**
     * True if the lines are visible in the table
     */
    private boolean linesVisible;
    

    /**
     * True if the header is visible in the table
     */

    private boolean headerVisible;
    
    /**
     * True if the table can be sorted by column
     */
    private boolean sortable;

    ////
    
    private int selectionIndex = -1;
    
    
    /**
     * Selection mode (row, cell)
     */
    private SelectionMode selectionMode;
    
    
    /**
     * Check multi selection mode
     */
    private boolean checkSelection;
    
    /**
     * Auto resize columns mode (OFF, ALL, LAST)
     */
    private AutoResizeColumns autoResizeColumns;
    
    
    
    public Table() {
	super();
	columns = new ArrayList<TableColumn>();

	// Create default DataStore
	setDataStore(createDefaultDataStore());
	
	linesVisible = true;
	headerVisible = true;
	sortable = false;
    }
    
    
    protected DataProvider<T> createDefaultDataProvider() {
	return createDataListProvider(null);
    }
    
    protected DataProvider<T> createDataListProvider(List<T> dataList) {
	DataProvider<T> dataProvider = new ArrayProvider<T>(dataList);
	return dataProvider;
    }
    
    protected boolean isChildrenProperty(String name) {
	return PROPERTY_COLUMNS.equals(name) || PROPERTY_ITEMS.equals(name) || PROPERTY_CHILDREN.equals(name);
    }
    
    
    public void addColumn(TableColumn column) {
	checkTableColumn(column);
	columns.add(column);
	column.setParent(this);
	
	fireAddChild(getInitChildren(PROPERTY_COLUMNS), column);
    }


    public void removeColumn(TableColumn column) {
	checkTableColumn(column);
	columns.remove(column);
	column.setParent(null);
	
	fireRemoveChild(getInitChildren(PROPERTY_COLUMNS), column);
    }

    /**
     * Return column by index
     * 
     * @param index
     * @return
     */
    public TableColumn getColumn(int index) {
	return columns.get(index);
    }

    /**
     * Return column by property
     * 
     * @param property
     * @return
     */
    public TableColumn getColumnByProperty(String property) {
	if (property == null) {
	    return null;
	}
	for (TableColumn column : columns) {
	    if (property.equals(column.getProperty())) {
		return column;
	    }
	}
	return null;
    }

    
    ////
    
    /*
    public void addItem(TableItem item) {
	checkTableItemStructure();
	checkTableItem(item);
	getTableItemProvider().addItem(item);
	item.setParent(this);
	
	fireAddChild(getInitChildren(PROPERTY_ITEMS), item);
    }
    
    
    public void removeItem(TableItem item) {
	checkTableItemStructure();
	checkTableItem(item);
	getTableItemProvider().removeItem(item);
	item.setParent(null);
	
	fireRemoveChild(getInitChildren(PROPERTY_ITEMS), item);
    }
    */
    
    ////
    
    /**
     * Return count of columns
     * 
     * @return
     */
    public int getColumnCount() {
	return columns.size();
    }

    /**
     * Return true if the table has columns
     * @return
     */
    public boolean hasColumns() {
	return !columns.isEmpty();
    }

   
    public int indexOfColumn(TableColumn column) {
	return column == null ? -1: columns.indexOf(column);
    }

    protected void checkTableColumn(TableColumn column) {
	if (column == null) {
	    throw new IllegalArgumentException("Column must be not null");
	}
    }

    ////
    
    public List<TableColumn> getColumns() {
	return new ArrayList<TableColumn>(columns);
    }
    
    public boolean isLinesVisible() {
        return linesVisible;
    }

    public void setLinesVisible(boolean linesVisible) {
        this.linesVisible = linesVisible;
        fireChangeProperty(PROPERTY_LINES_VISIBLE, linesVisible);
    }

    public boolean isHeaderVisible() {
        return headerVisible;
    }

    public void setHeaderVisible(boolean headerVisible) {
        this.headerVisible = headerVisible;
        fireChangeProperty(PROPERTY_HEADER_VISIBLE, headerVisible);
    }


    ////
    
    public boolean isSortable() {
        return sortable;
    }


    public void setSortable(boolean sortable) {
        this.sortable = sortable;
        fireChangeProperty(PROPERTY_ENABLED, sortable);
    }

    ////
    
    public int getSelectionIndex() {
	if (isReadyInput()) {
	    return (Integer) getDelegateProperty(PROPERTY_SELECTION_INDEX);
	}
	return selectionIndex;
    }

    
    public void setSelectionIndex(int selectionIndex) {
	this.selectionIndex = selectionIndex;
	 fireChangeProperty(PROPERTY_SELECTION_INDEX, selectionIndex);
	if (isReadyInput()) {
	    setDelegateProperty(PROPERTY_SELECTION_INDEX, selectionIndex);
	    return;
	}
    }

    public void setSelectionFirst() {
	setSelectionIndex(0);
    }
    
    ////
    

    /**
     * Get selection item
     * @return
     */
    public T getSelectionItem() {
	//TODO !!!
	int index = getSelectionIndex();
	if (!checkIndex(index)) {
	    return null;
	}
	return getItem(index);
    }
    
    /**
     * Set selection item
     * @param item
     */
    public void setSelectionItem(T item) {
	//TODO !!!
	if (item == null) {
	    resetSelection();
	    return;
	}
	int index = indexOfItem(item);
	if (index == -1) {
	    resetSelection();
	    return;
	}
	setSelectionIndex(index);
    }
    
    ////

    
    public void addEnterListener(EnterListener listener) {
	addEnterListener(this, listener);
    }
    
    public void removeEnterListener(EnterListener listener) {
	removeEnterListener(this, listener);
    }


    ////
    
    /**
     * Sort data by column
     * @param column
     */
    public void sortByColumn(TableColumn column) {
	sortByColumn(column, true);
    }
	
    /**
     * Sort data by column
     * @param column
     * @param asc
     */
    public void sortByColumn(TableColumn column, boolean asc) {
	if (column == null) {
	    return;
	}
	
	// Get property
	String property = column.getProperty();
	
	if (property == null) {
	    return;
	}
	
	// 1. Reset Sort
	resetSorter();
	
	// 2. Create new Sorter
	PropertySorter<T> sorter = new PropertySorter<T>();
	sorter.addProperty(property, asc);
	PropertyProvider<T> propertyProvider = getPropertyProvider();
	
	if (propertyProvider != null){
	    sorter.setPropertyProvider(propertyProvider);
	}
	
	// 3. Set new Sorter
	setSorter(sorter);
	
	fireInternalEvent(new Event("UpdateSorter"));
	
	//4. Refresh
	refresh();
	
    }
    
    /**
     * Return sort column
     * @return
     */
    public TableColumn getSortColumn() {
	if (!hasColumns()) {
	    return null;
	}
	
	PropertyInfo propertyInfo = getSortPropertyInfo();
	if (propertyInfo == null) {
	    return null;
	}
	String property = propertyInfo.getName();
	if (property == null) {
	    return null;
	}
	
	TableColumn column = getColumnByProperty(property);
	return column;
    }
    
    public String getSortProperty() {
	PropertyInfo propertyInfo = getSortPropertyInfo();
	return propertyInfo == null ? null : propertyInfo.getName(); 
    }

    public boolean isAscSortColumn() {
	PropertyInfo propertyInfo = getSortPropertyInfo();
	return propertyInfo == null ? false : propertyInfo.isAsc(); 
    }

    protected PropertyInfo getSortPropertyInfo() {
	DataSorter<T> sorter = getSorter();
	if (sorter == null) {
	    return null;
	}
	// Support only PropertySorter
	if (!(sorter instanceof PropertySorter)) {
	    return null;
	}
	PropertySorter<T> propertySorter = (PropertySorter<T>) sorter;
	// Support only single mode
	if (!propertySorter.isSingle()){
	    return null;
	}
	// Get first (single) property
	PropertyInfo propertyInfo = propertySorter.getProperty();
	return propertyInfo;
    }
    
    
    public void refresh() {
	super.refresh();
	updateColumns();
    }

    protected void refreshData() {
	super.refresh();
    }

    protected void updateColumns() {
	invokeDelegate(this, METHOD_UPDATE_COLUMNS, null);
    }

    

    public SelectionMode getSelectionMode() {
        return selectionMode;
    }


    public void setSelectionMode(SelectionMode selectionMode) {
        this.selectionMode = selectionMode;
        fireChangeProperty(PROPERTY_SELECTION_MODE, selectionMode);
    }


    public boolean isCheckSelection() {
        return checkSelection;
    }


    public void setCheckSelection(boolean checkSelection) {
        this.checkSelection = checkSelection;
    }


    public AutoResizeColumns getAutoResizeColumns() {
        return autoResizeColumns;
    }


    public void setAutoResizeColumns(AutoResizeColumns autoResizeColumns) {
        this.autoResizeColumns = autoResizeColumns;
        fireChangeProperty(PROPERTY_AUTO_RESIZE_COLUMNS, autoResizeColumns);
    }
    
    /**
     * Return ValueProvider of column by property
     * @param property
     * @return
     */
    public ValueProvider getValueProvider(String property) {
	if (property == null) {
	    return null;
	}
	TableColumn column = getColumnByProperty(property);
	if (column == null) {
	   return null; 
	}
	return column.getValueProvider();
    }
    
    public boolean hasValueProvider(String property) {
	return getValueProvider(property) != null;
    }
    
    /**
     * Return CellEditor of column by property
     * @param property
     * @return
     */
    public CellEditor getCellEditor(String property) {
	if (property == null) {
	    return null;
	}
	TableColumn column = getColumnByProperty(property);
	if (column == null) {
	   return null; 
	}
	return column.getCellEditor();
    }

    public boolean hasCellEditor(String property) {
	return getCellEditor(property) != null;
    }

    /**
     * Return value of element by property
     * @param element
     * @param property
     * @return
     */
    public Object getValue(T element, String property) {
	if (element == null || property == null) {
	    return null;
	}
	ValueProvider<T> valueProvider = getValueProvider(property);
	if  (valueProvider != null) {
	    return valueProvider.getValue(element);
	}
	PropertyProvider<T> propertyProvider = getPropertyProvider();
	if (propertyProvider != null) {
	    return propertyProvider.getValue(element, property);
	}
	return null;
    }

    /**
     * Set value of element by property
     * @param element
     * @param property
     * @param value
     */
    public void setValue(T element, String property, Object value) {
	if (element == null || property == null) {
	    return;
	}
	ValueProvider<T> valueProvider = getValueProvider(property);
	if  (valueProvider != null) {
	    valueProvider.setValue(element, value);
	}
	PropertyProvider<T> propertyProvider = getPropertyProvider();
	if (propertyProvider != null) {
	    propertyProvider.setValue(element, property, value);
	}
    }

}
