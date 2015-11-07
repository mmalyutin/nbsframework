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

package org.plazmaforge.framework.uwt.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.criteria.Filter;
import org.plazmaforge.framework.core.criteria.Order;
import org.plazmaforge.framework.core.criteria.ValueFilter;
import org.plazmaforge.framework.core.data.DataList;
import org.plazmaforge.framework.core.data.Pager;
import org.plazmaforge.framework.uwt.widget.Column;
import org.plazmaforge.framework.uwt.widget.Viewer;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;
import org.plazmaforge.framework.uwt.widget.tree.Tree;
import org.plazmaforge.framework.uwt.widget.tree.TreeColumn;

public class ToolUtils {

    
    /**
     * Create display fields from Viewer 
     * @param viewer
     * @return
     */
    public static List<DisplayField> createDisplayFields(Viewer viewer) {
	if (viewer == null) {
	    return null;
	}
	if (viewer instanceof Table) {
	    Table table = (Table) viewer;
	    return createDisplayFields(table);
	} else if (viewer instanceof Tree) {
	    Tree tree = (Tree) viewer;
	    return createDisplayFields(tree);
	}
	//TODO: Must implement other type of viewer 
	return null;
    }
    
    /**
     * Create display fields from Table
     * @param table
     * @return
     */
    public static List<DisplayField> createDisplayFields(Table table) {
	    int columnCount = table.getColumnCount();
	    if (columnCount == 0) {
		return null;
	    }
	    List<DisplayField> fields = new ArrayList<DisplayField>();
	    List<TableColumn> columns = table.getColumns();
	    for (TableColumn column : columns) {

		// Create DisplayField by Column
		DisplayField field = createDisplayField(column);
		if (field == null) {
		    continue;
		}
		
		fields.add(field);
	    }
	    return fields.isEmpty() ? null : fields; 
    }

    /**
     * Create display fields from Tree
     * @param tree
     * @return
     */
    public static List<DisplayField> createDisplayFields(Tree tree) {
	int columnCount = tree.getColumnCount();
	if (columnCount == 0) {
	    return null;
	}
	List<DisplayField> fields = new ArrayList<DisplayField>();
	List<TreeColumn> columns = tree.getColumns();
	for (TreeColumn column : columns) {

	    // Create DisplayField by Column
	    DisplayField field = createDisplayField(column);
	    if (field == null) {
		continue;
	    }

	    fields.add(field);
	}
	return fields.isEmpty() ? null : fields;
    }

    /**
     * Create display fields from Column
     * @param column
     * @return
     */
    private static DisplayField createDisplayField(Column column) {
	if (!isDisplayColumn(column)) {
	    return null;
	}
	DisplayField field = new DisplayField();
	field.setProperty(column.getProperty());
	field.setLabel(column.getText());
	field.setType(column.getDataType());
	field.setSortable(column.isSortable());
	field.setFilterable(column.isFilterable());
	
	field.setValuePresenter(column.getValuePresenter());
	return field;
    }
    
    /**
     * Return true if column is displayed
     * @param column
     * @return
     */
    public static boolean isDisplayColumn(Column column) {
	if (column == null) {
	    return false;
	}
	String text = column.getText();
	if (text != null) {
	    text = text.trim();
	}
	if (text == null || text.isEmpty()) {
	    return false;
	}
	return true;
    }
    
    
   
    
    /**
     * Create list of filter field by active viewer
     * Use to FilterDialog
     * 
     * @param displayFields
     * @return
     */
    public static List<DisplayField> createFilterFields(
	    List<DisplayField> displayFields) {
	if (displayFields == null) {
	    return null;
	}
	List<DisplayField> filterFields = new ArrayList<DisplayField>();
	for (DisplayField field : displayFields) {
	    if (field.isFilterable()) {
		filterFields.add(field);
	    }
	}
	return filterFields;

    }

    /**
     * Create list of sort field by active viewer.
     * Use to FilterDialog
     * 
     * @param displayFields
     * @return
     */
    public static List<DisplayField> createSortFields(List<DisplayField> displayFields) {
	if (displayFields == null) {
	    return null;
	}
	List<DisplayField> sortFields = new ArrayList<DisplayField>();
	for (DisplayField field : displayFields) {
	    if (field.isSortable()) {
		sortFields.add(field);
	    }
	}
	return sortFields;
    }
    
    
    /**
     * Initialize Pager by Criteria
     * @param pager
     * @param criteria
     */
    public static void initPager(Pager pager, Criteria criteria) {
	ToolUtils.initPager(pager, criteria);
    }
    
    /**
     * Populate Pager by data list
     * @param pager
     * @param dataList
     */
    public static void populatePager(Pager pager, List dataList) {
	if (pager == null) {
	    return;
	}
	int total = 0;
	int count = 0;
	
	if (dataList != null) {
	    
	    count = dataList.size();
	    if (dataList instanceof DataList) {
		total = ((DataList) dataList).getTotal();
	    } else {
		total = count;
	    }
	    
	    
	}
	pager.setTotalRowCount(total); // If need get total row count
	pager.setRowCount(count);
	pager.recalculate();
    }
    
    /**
     * Populate Criteria by Pager
     * @param criteria
     * @param pager
     * @param isPaging
     */
    public static void populateCriteria(Criteria criteria, Pager pager, boolean isPaging) {
	if (criteria == null || pager == null) {
	    return;
	}
	criteria.setOffset(isPaging ? pager.getFromRow(): 0);
	criteria.setLimit(isPaging? pager.getRowLimit(): 0);
    }

    /**
     * Populate Criteria by filter fields
     * @param criteria
     * @param filterFields
     */
    public static void populateCriteriaFilters(Criteria criteria, List<FilterField> filterFields) {
	// Add new filters if need
	if (filterFields == null || filterFields.isEmpty()) {
	    return;
	}
	for (FilterField field : filterFields) {
	    criteria.addFilter(new ValueFilter(field.getProperty(), (Serializable) field.getValue(), field.getOperation(), field.isCaseInsensitive()));
	}
    }

   
    
    /**
     * Populate Criteria by order fields
     * @param criteria
     * @param orderFields
     */
    public static void populateCriteriaOrders(Criteria criteria, List<OrderField> orderFields) {
	// Add new orders (if need)
	if (orderFields == null || orderFields.isEmpty()) {
	    return;
	}
	for (OrderField field : orderFields) {
	    criteria.addOrder(new Order(field.getProperty(), field.isAsc()));
	}	
    }
 
    
    /**
     * Generate paging display string
     * 
     * @param pager
     * @return
     */
    public static String getPagingString(Pager pager) {
	if (pager == null || pager.isDisablePager()) {
	    return "No data";
	}
	int count = pager.getRowCount();
	int currPage = pager.getCurrentPageIndex();
	int pageCount = pager.getPageCount();
	int prevPage = currPage - 1;
	if (prevPage < 0) {
	    prevPage = 0;
	} else {
	    prevPage++;
	}
	// int totalIndex = prevPage * pager.getRowLimit() + index;
	int totalCount = pager.getTotalRowCount();

	int rowCount = pager.getRowCount();
	int fromRow = pager.getFromRow();

	currPage++; // because start with 1
	fromRow++; // because start with 1

	int toRow = fromRow + rowCount - 1;

	//return  STATUS_PAGE_MESSAGE + " " + currPage + " " + STATUS_OF_MESSAGE + " " + pageCount 
	//    + ", " + getFromToString(fromRow, toRow, totalCount);
	return "Page " + currPage + " of " + pageCount + ", Rows " + fromRow + "-" + toRow + " of " + totalCount;
	    
    }
}
