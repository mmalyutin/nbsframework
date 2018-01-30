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


import java.util.List;

import org.plazmaforge.framework.uwt.gxt.data.Model;
import org.plazmaforge.framework.uwt.gxt.widget.cell.XCellRenderer;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safecss.shared.SafeStyles;
import com.google.gwt.safecss.shared.SafeStylesBuilder;
import com.google.gwt.safecss.shared.SafeStylesUtils;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnData;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;
import com.sencha.gxt.widget.core.client.grid.GridViewConfig;
import com.sencha.gxt.widget.core.client.grid.RowExpander;
import com.sencha.gxt.widget.core.client.menu.CheckMenuItem;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

/**
 * 
 * @author ohapon
 *
 * See ColumnHeader.updateSortIcon(int colIndex, SortDir dir)
 */
public class XGridView extends GridView<Model> {


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
	// to disable DropDown menu of column (sorting setting) 
	visibleColumnsMenu = false;
	
	setViewConfig(new GridViewConfig<Model>() {
	    
            @Override
            public String getColStyle(Model model, ValueProvider<? super Model, ?> valueProvider, int rowIndex, int colIndex) {
        	ColumnConfig<Model, ?> c = grid.getColumnModel().getColumn(colIndex);
        	if (c == null) {
        	    return null;
        	}
        	if (!(c instanceof XColumnConfig)) {
        	    return null;
        	}
        	XColumnConfig<?> column = (XColumnConfig<?>) c;
        	XCellRenderer cellRenderer = column.getCellRenderer();
        	if (cellRenderer == null) {
        	    return null;
        	}
	
        	String cellStyle = cellRenderer.getCellStyle(model, valueProvider, rowIndex, colIndex);
        	GWT.log("CELL-STYLE: " + cellStyle);
        	return cellStyle;
            }

            @Override
            public String getRowStyle(Model model, int rowIndex) {
                return null;
            }
        });

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

    protected void onHeaderClick(Grid<Model> grid, int colIndex) {
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
    
    
    /**
     * Renders the grid view into safe HTML.
     *
     * @param cs the column attributes required for rendering
     * @param rows the data models for the rows to be rendered
     * @param startRow the index of the first row in <code>rows</code>
     */
    protected SafeHtml doRender(List<ColumnData> cs, List<Model> rows, int startRow) {
      final int colCount = cm.getColumnCount();
      final int last = colCount - 1;

      int[] columnWidths = getColumnWidths();

      // root builder
      SafeHtmlBuilder buf = new SafeHtmlBuilder();

      final SafeStyles rowStyles = SafeStylesUtils.fromTrustedString("width: " + getTotalWidth() + "px;");

      final String unselectableClass = unselectable;
      final String rowAltClass = styles.rowAlt();
      final String rowDirtyClass = styles.rowDirty();

      final String cellClass = styles.cell() + " " + states.cell();
      final String cellInnerClass = styles.cellInner() + " " + states.cellInner();
      final String cellFirstClass = "x-grid-cell-first";
      final String cellLastClass = "x-grid-cell-last";
      final String cellDirty = styles.cellDirty();

      final String rowWrap = styles.rowWrap() + " " + states.rowWrap();
      final String rowBody = styles.rowBody() + " " + states.rowBody();
      final String rowBodyRow = states.rowBodyRow();

      // loop over all rows
      for (int j = 0; j < rows.size(); j++) {
        Model model = rows.get(j);

        ListStore<Model>.Record r = ds.hasRecord(model) ? ds.getRecord(model) : null;

        int rowBodyColSpanCount = colCount;
        if (enableRowBody) {
          for (ColumnConfig<Model, ?> c : cm.getColumns()) {
            if (c instanceof RowExpander) {
              rowBodyColSpanCount--;
            }
          }
        }

        int rowIndex = (j + startRow);

        String rowClasses = styles.row() + " " + states.row();

        if (!selectable) {
          rowClasses += " " + unselectableClass;
        }
        if (isStripeRows() && ((rowIndex + 1) % 2 == 0)) {
          rowClasses += " " + rowAltClass;
        }

        if (isShowDirtyCells() && r != null && r.isDirty()) {
          rowClasses += " " + rowDirtyClass;
        }

        if (viewConfig != null) {
          rowClasses += " " + viewConfig.getRowStyle(model, rowIndex);
        }

        SafeHtmlBuilder trBuilder = new SafeHtmlBuilder();

        // loop each cell per row
        for (int i = 0; i < colCount; i++) {
          SafeHtml rv = getRenderedValue(rowIndex, i, model, r);
          ColumnConfig<Model, ?> columnConfig = cm.getColumn(i);
          ColumnData columnData = cs.get(i);

          String cellClasses = cellClass;
          if (i == 0) {
            cellClasses += " " + cellFirstClass;
          } else if (i == last) {
            cellClasses += " " + cellLastClass;
          }

          String cellInnerClasses = cellInnerClass;
          if (columnConfig.getColumnTextClassName() != null) {
            cellInnerClasses += " " + columnConfig.getColumnTextClassName();
          }
          if (!columnConfig.isCellPadding()) {
            cellInnerClasses += " " + styles.noPadding();
          }

          if (columnData.getClassNames() != null) {
            cellClasses += " " + columnData.getClassNames();
          }

          if (columnConfig.getCellClassName() != null) {
            cellClasses += " " + columnConfig.getCellClassName();
          }

          if (isShowDirtyCells() && r != null && r.getChange(columnConfig.getValueProvider()) != null) {
            cellClasses += " " + cellDirty;
          }

          //if (viewConfig != null) {
          //  cellClasses += " " + viewConfig.getColStyle(model, cm.getValueProvider(i), rowIndex, i);
          //}
          //final SafeStyles cellStyles = columnData.getStyles();
  
          // CHANGE-BEGIN
          SafeStyles cellStyles = null;
          if (viewConfig != null) {
              String colStyle = viewConfig.getColStyle(model, cm.getValueProvider(i), rowIndex, i);
              if (colStyle != null) {
        	  SafeStylesBuilder builder = new SafeStylesBuilder();
        	  builder.append(columnData.getStyles());
        	  builder.append(SafeStylesUtils.fromTrustedString(colStyle + ";"));
        	  cellStyles = builder.toSafeStyles();
              }
          }
          if (cellStyles == null) {
              cellStyles = columnData.getStyles();
          }
          // CHANGE-END

          final SafeHtml tdContent;
          if (enableRowBody && i == 0) {
            tdContent = tpls.tdRowSpan(i, cellClasses, cellStyles, getRowBodyRowSpan(), cellInnerClasses, rv);
          } else {
            if (!selectable && GXT.isIE()) {
              tdContent = tpls.tdUnselectable(i, cellClasses, cellStyles, cellInnerClasses,
                  columnConfig.getColumnTextStyle(), rv);
            } else {
              tdContent = tpls.td(i, cellClasses, cellStyles, cellInnerClasses, columnConfig.getColumnTextStyle(), rv);
            }

          }
          trBuilder.append(tdContent);
        }

        if (enableRowBody) {
          String cls = styles.dataTable() + " x-grid-resizer";

          SafeHtmlBuilder sb = new SafeHtmlBuilder();
          sb.append(tpls.tr("", trBuilder.toSafeHtml()));
          sb.appendHtmlConstant("<tr class='" + rowBodyRow + "'><td colspan=" + rowBodyColSpanCount + "><div class='"
              + rowBody + "'></div></td></tr>");

          SafeHtml tdWrap = null;
          if (!selectable && GXT.isIE()) {
            tdWrap = tpls.tdWrapUnselectable(colCount, "", rowWrap,
                tpls.table(cls, rowStyles, sb.toSafeHtml(), renderHiddenHeaders(columnWidths)));
          } else {
            tdWrap = tpls.tdWrap(colCount, "", rowWrap,
                tpls.table(cls, rowStyles, sb.toSafeHtml(), renderHiddenHeaders(columnWidths)));
          }
          buf.append(tpls.tr(rowClasses, tdWrap));

        } else {
          buf.append(tpls.tr(rowClasses, trBuilder.toSafeHtml()));
        }

      }
      // end row loop
      return buf.toSafeHtml();

    }
    
    
    //DISABLE:MIGRATION

    /*
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
*/
    

}
