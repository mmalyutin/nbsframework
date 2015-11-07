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

package org.plazmaforge.framework.uwt.gxt.widget;


import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.SortInfo;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridView;
import com.extjs.gxt.ui.client.widget.menu.CheckMenuItem;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;

/**
 * 
 * @author ohapon
 *
 * See ColumnHeader.updateSortIcon(int colIndex, SortDir dir)
 */
public class XGridView extends GridView {


    private boolean ownSortable;
    
    private boolean visibleColumnsMenu;
    
    
    //UWT Table
    private Table table;
    
    public XGridView() {
	super();
	init();
    }
    
    private void init() {
	ownSortable = false;
	// Use ColumnConfig.setMenuDisabled(true) 
	// to disable dropdown menu of column (sorting setting) 
	visibleColumnsMenu = false;
    }
    
    public void setTable(Table table) {
	this.table = table;
    }
    
    protected SortDir getSortDir(TableColumn column) {
	return (SortDir) column.getData("$sortDir");
    }

    protected void setSortDir(TableColumn column, SortDir sortDir) {
	column.setData("$sortDir", sortDir);
    }

    protected void onHeaderClick(Grid<ModelData> grid, int colIndex) {
	this.headerColumnIndex = colIndex;
	if (!headerDisabled && cm.isSortable(colIndex)) {
	    // Get column by index
	    TableColumn column = table.getColumn(colIndex);
	    // Get current SortDir of column
	    SortDir sortDir = getSortDir(column);
	    // Inverse SortDir
	    sortDir = (sortDir == null || sortDir == SortDir.DESC) ? SortDir.ASC: SortDir.DESC;
	    // Store new SortDir
	    setSortDir(column, sortDir);
	    // Sorting
	    doSort(colIndex, sortDir);
	}
    }

    protected void doSort(int colIndex, SortDir sortDir) {
	if (!ownSortable) {
	    doUWTSort(colIndex, sortDir);
	    return;
	}
	ds.sort(cm.getDataIndex(colIndex), sortDir);
    }
    
    
    protected void doUWTSort(int colIndex, SortDir sortDir) {
	if (table == null) {
	    return;
	}
	
	// Change SortInfo: Special for ColumnHeader.updateSortIcon
	String field = cm.getDataIndex(colIndex);
	SortInfo sortInfo = ds.getSortState();
	sortInfo.setSortField(field);
	sortInfo.setSortDir(sortDir);
	
	TableColumn column = table.getColumn(colIndex);
	boolean asc = !SortDir.DESC.equals(sortDir); // ASC = TRUE if sortDir = ASC or sortDit = NONE
	table.sortByColumn(column, asc);
    }
    
    protected Menu createContextMenu(final int colIndex) {
	    final Menu menu = new Menu();

	    if (cm.isSortable(colIndex)) {
	      MenuItem item = new MenuItem();
	      item.setText(GXT.MESSAGES.gridView_sortAscText());
	      item.setIcon(getImages().getSortAsc());
	      item.addSelectionListener(new SelectionListener<MenuEvent>() {
	        public void componentSelected(MenuEvent ce) {
	          doSort(colIndex, SortDir.ASC);
	        }

	      });
	      menu.add(item);

	      item = new MenuItem();
	      item.setText(GXT.MESSAGES.gridView_sortDescText());
	      item.setIcon(getImages().getSortDesc());
	      item.addSelectionListener(new SelectionListener<MenuEvent>() {
	        public void componentSelected(MenuEvent ce) {
	          doSort(colIndex, SortDir.DESC);
	        }
	      });
	      menu.add(item);
	    }

	    if (!visibleColumnsMenu) {
		return menu;
	    }
	    
	    MenuItem columns = new MenuItem();
	    columns.setText(GXT.MESSAGES.gridView_columnsText());
	    columns.setIcon(getImages().getColumns());
	    columns.setData("gxt-columns", "true");

	    final Menu columnMenu = new Menu();

	    int cols = cm.getColumnCount();
	    for (int i = 0; i < cols; i++) {
	      if (shouldNotCount(i, false)) {
	        continue;
	      }
	      final int fcol = i;
	      final CheckMenuItem check = new CheckMenuItem();
	      check.setHideOnClick(false);
	      check.setText(cm.getColumnHeader(i));
	      check.setChecked(!cm.isHidden(i));
	      check.addSelectionListener(new SelectionListener<MenuEvent>() {
	        public void componentSelected(MenuEvent ce) {
	          cm.setHidden(fcol, !cm.isHidden(fcol));
	          restrictMenu(columnMenu);
	        }
	      });
	      columnMenu.add(check);
	    }

	    restrictMenu(columnMenu);

	    columns.setSubMenu(columnMenu);
	    menu.add(columns);
	    return menu;
	  }
    
    
    
    
    protected void restrictMenu(Menu columns) {
	int count = 0;
	for (int i = 0, len = cm.getColumnCount(); i < len; i++) {
	    if (!shouldNotCount(i, true)) {
		count++;
	    }
	}

	if (count == 1) {
	    for (Component item : columns.getItems()) {
		CheckMenuItem ci = (CheckMenuItem) item;
		if (ci.isChecked()) {
		    ci.disable();
		}
	    }
	} else {
	    for (Component item : columns.getItems()) {
		item.enable();
	    }
	}
    }

    
    protected boolean shouldNotCount(int columnIndex, boolean includeHidden) {
	return cm.getColumnHeader(columnIndex) == null
		|| cm.getColumnHeader(columnIndex).equals("")
		|| (includeHidden && cm.isHidden(columnIndex))
		|| cm.isFixed(columnIndex);
    }

    

}
