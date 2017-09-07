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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.UWTException;
import org.plazmaforge.framework.uwt.gxt.layout.XGridData;
import org.plazmaforge.framework.uwt.gxt.layout.XGridLayout;
import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.layout.GridLayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author ohapon
 *
 */
public class GXTGridLayoutAdapter extends GXTLayoutAdapter {

    
    public Object createDelegate(UIObject parent, UIObject element) {
	GridLayout layout = (GridLayout) element;  
	XGridLayout xLayout = new XGridLayout(layout.getColumnCount());
	
	// Spacing
	xLayout.setVerticalSpacing(layout.getVerticalSpacing());
	xLayout.setHorizontalSpacing(layout.getHorizontalSpacing());
	
	xLayout.setFix(layout.isFix());
	
	// Margin
	xLayout.setMarginLeft(layout.getMarginLeft());
	xLayout.setMarginTop(layout.getMarginTop());
	xLayout.setMarginRight(layout.getMarginRight());
	xLayout.setMarginBottom(layout.getMarginBottom());
	
	return xLayout;
    }

    protected XGridLayout getXGridLayout(Object delegate) {
	return (XGridLayout) delegate;
    }

    @Override
    public HasWidgets createContainer(XLayout xLayout) {
	XGridLayout xGridLayout = (XGridLayout) xLayout;
	FlexTable table  = new  FlexTable();
	return table;
    }
    
   
    @Override
    public void addChild(XLayoutContainer parent, com.google.gwt.user.client.ui.Widget widget, UIObject element) {

	// Get container
	HasWidgets container = parent.getContainer();
	if (!(container instanceof FlexTable)) {
	    throw new UWTException("Container is not GWT-FlexTable");
	}
	FlexTable table  = (FlexTable) container;
	
	// Get layout
	XLayout xLayout = parent.getLayout();
	if (!(xLayout instanceof XGridLayout)) {
	    throw new UWTException("XLayout is not XGridLayout");
	}
	XGridLayout xGridLayout = (XGridLayout) xLayout;
	
	addChild(table, xGridLayout, widget);
	//parent.relayout();
    }
   
 
    protected void addChild(FlexTable table, XGridLayout xGridLayout, Widget widget) {
	
	// Get layout info
	int layoutColumnCount = xGridLayout.getColumnCount();
	
	// Get layout data info
	Object xLayoutData = widget.getLayoutData();
	XGridData xGridData = null;
	if (xLayoutData != null && xLayoutData instanceof XGridData) {
	    xGridData = (XGridData) xLayoutData;
	}
	
	int rowCount = table.getRowCount();
	Cell freeCell = null;
	if (rowCount == 0) {
	    freeCell = new Cell();
	    freeCell.row = 0;
	    freeCell.column = 0;
	    
	    populateCellSpan(freeCell, xGridData);
	    
	    // Normalize only for colSpan: collapse
	    normalizeCellSpan(freeCell, layoutColumnCount);
	    addWidget(table, freeCell, widget);
	    return;
	}
	
	// Real column count in the table
	int columnCount = 0;
	
	// Cell count of a row 
	int cellCount = 0;
	int colSpan = 0;
	int rowSpan = 0;
	List<Cell> cells = new ArrayList<Cell>();
	
	// Analyze table structure: find real cells (with colSpan and rowSpan)
	for (int row = 0; row < rowCount; row++) {
	    cellCount = table.getCellCount(row);
	    
	    // Set by default start column = 0
	    int column = 0;
	    for (int cellInex = 0; cellInex < cellCount; cellInex++) {

		if (table.getWidget(row, cellInex) == null) {
		    // No widget - no cell
		    continue;
		}
		
		colSpan = table.getFlexCellFormatter().getColSpan(row, cellInex);
		rowSpan = table.getFlexCellFormatter().getRowSpan(row, cellInex);
		
		// Calculate shift for column by before rows with cells with rowSpan
		int shift = 0;
		do {
		    shift = getColumnShift(cells, row, column);
		    if (shift > 0) {
			column += shift;
		    }
		} while (shift > 0);
		
		
		// Create new cell of widget
		Cell cell = new Cell();
		cell.column = column;
		cell.row = row;
		cell.colSpan = colSpan;
		cell.rowSpan = rowSpan;

		cells.add(cell);
		GWT.log("OUT: out" + cell);	
		
		// Calculate new column (column + colSpan)
		column += colSpan;
		
		// Calculate new column count
		if (column > columnCount) {
		    columnCount = column;
		}
		
	    }
	}
	

	GWT.log("OUT: TableInfo [rowCount=" + rowCount + ", columnCount=" + columnCount + "]");
	
	// Fill cell matrix (true - cell is not free)
	boolean[][] matrix = new boolean[rowCount][columnCount];
	
	// Populate cell matrix
	for (Cell cell: cells) {
	    int startRow = cell.row;
	    int endRow = cell.row + cell.rowSpan; // exclude
	    int startColumn = cell.column;
	    int endColumn = cell.column + cell.colSpan; // exclude	 
	    
	    for (int row = startRow; row < endRow; row++) {
		for (int column = startColumn; column < endColumn; column++) {
		    
		    // Mark cell is not free
		    matrix[row][column] = true;
		}
	    }
	}
	GWT.log("OUT: Matrix=" + matrix);
	
	// Find free cell
	boolean growColumn = false;
	boolean growRow = false;
	
	
	// Find free cell in matrix to add new widget
	for (int row = 0; row < rowCount; row++) {
	    for (int column = 0; column < columnCount; column++) {
		if (!matrix[row][column]) {
		    freeCell = new Cell();
		    freeCell.row = row;
		    freeCell.column = column;
		    
		    // Cell found - break
		    break;
		}
	    }
	    
	    // Cell found - break
	    if (freeCell != null) {
		break;
	    }
	    
	    if (columnCount < layoutColumnCount) {
		growColumn = true;
		
		freeCell = new Cell();
		freeCell.row = row;
		freeCell.column = columnCount; // new column
		
		// Column is growing - break
		break;
	    }
	    
	}
	if (freeCell == null) {
	    growRow = true;

	    freeCell = new Cell();
	    freeCell.row = rowCount; // new row
	    freeCell.column = 0;
	    
	}
	
	populateCellSpan(freeCell, xGridData);
	
	GWT.log("OUT: free" + freeCell + ", growColumn=" + growColumn + ", growRow=" + growRow);
	
	if (growRow || growColumn) {
	    if (growColumn) {
		// Normalize only for colSpan: collapse
		normalizeCellSpan(freeCell, layoutColumnCount);
	    }
	    addWidget(table, freeCell, widget);
	    return;
	}
	
	// Normalize: collapse rowSpan and colSpan if need
	normalizeCellSpan(freeCell, matrix, rowCount, columnCount, layoutColumnCount);
	addWidget(table, freeCell, widget);

    }
    
    /**
     * Find a cell in before rows with rowSpan than can cover 
     * current cell (row, column) and return shift of column
     * 
     * @param cells
     * @param row - current row
     * @param column - current column
     * @return shift of column (colSpan of found cell)
     */
    protected int getColumnShift(List<Cell> cells, int row, int column) {
	 if (cells == null || cells.isEmpty()) {
	     return 0;
	 }
	 for (Cell cell : cells) {
	     
	     // Only current column and before rows (without current row)
	     if (cell.column != column || cell.row >= row) {
		 continue;
	     }
	     
	     // Only cell with rowSpan than can cover current cell (row, column)
	     if (cell.row + cell.rowSpan > row) {
		 // shift
		 return cell.colSpan;
	     }
	 }
	 return 0;
    }
    
    protected void populateCellSpan(Cell cell, XGridData xLayoutData){
	if  (xLayoutData == null) {
	    return;
	}
	if (xLayoutData.getRowSpan() > 1) {
	    cell.rowSpan = xLayoutData.getRowSpan();
	}
	if (xLayoutData.getColSpan() > 1) {
	    cell.colSpan = xLayoutData.getColSpan();
	}
    }
    
    protected void addWidget(FlexTable table, Cell cell, Widget widget) {
  	if (widget == null) {
  	    return;
  	}
  	int row = 0;
  	int column = 0;
  	int rowSpan = 1;
  	int colSpan = 1;
  	if (cell != null) {
  	    row = cell.row;
  	    column = cell.column;
  	    rowSpan = cell.rowSpan;
  	    colSpan = cell.colSpan;
  	}
  	
  	// add widget to cell (row, column)
  	int rowCount = table.getRowCount();
  	int cellCount = 0;
  	if (rowCount > 0 && row < rowCount) {
  	    cellCount = table.getCellCount(row);
  	}
  	
  	
  	table.setWidget(row, cellCount, widget);
  	
  	FlexCellFormatter f = table.getFlexCellFormatter();
  	if (colSpan > 0) {
  	    table.getFlexCellFormatter().setColSpan(row, column, colSpan);
  	}
  	if (rowSpan > 0) {
  	    table.getFlexCellFormatter().setRowSpan(row, column, rowSpan);
  	}
  	GWT.log("OUT: addCell[row=" + row + ", cell=" + cellCount + ", rowSpan=" + rowSpan + ", colSpan=" + colSpan + "]");

  	/*
  	cellCount = table.getCellCount(row);
  	GWT.log("OUT: cellCount: " + cellCount + "");
  	
  	// Find magic empty cell
  	Widget w = null;
  	int k = -1;
  	for (int i= 0; i < cellCount; i++) {
  	    w = table.getWidget(row, i);
  	    GWT.log("OUT: cell:" + i + ", widget=" + w);
  	    if (w == null) {
  		k = i;
  	    }
  	}
  	
  	// Remove magic empty cell
  	if (k > -1) {
  	  GWT.log("OUT: removeCell[row=" + row + ", column="+ k + "]");
  	  table.removeCell(row, k);
  	    
  	}
  	*/
  	
  	//TODO: alignment...
    }    

    protected void normalizeCellSpan(Cell cell) {
	if (cell == null) {
	    return;
	}
	if (cell.rowSpan < 1) {
	    cell.rowSpan = 1; // fixed rowSpan
	}
	if (cell.colSpan < 1) {
	    cell.colSpan = 1; // fixed colSpan
	}
    }
    
    protected void normalizeCellSpan(Cell cell, int layoutColumnCount) {
	if (cell == null) {
	    return;
	}
	normalizeCellSpan(cell);
	if (cell.column + cell.colSpan > layoutColumnCount) {
	    cell.colSpan = layoutColumnCount - cell.column;
	}
    }
	    
    protected void normalizeCellSpan(Cell cell, boolean[][] matrix, int rowCount, int columnCount, int layoutColumnCount) {
	if (cell == null) {
	    return;
	}
	normalizeCellSpan(cell);
	int rowSpan = cell.rowSpan;
	int colSpan = cell.colSpan;
	
	if (rowSpan == 1 && colSpan == 1) {
	    // No span
	    return;
	}

	int startRow = cell.row;
	int startColumn = cell.column;

	if (!matrix[startRow][startColumn]) {
	    // Start cell is not free
	    return;
	}

	int lastRow = rowCount - 1;
	int lastColumn = columnCount - 1;

	int endRow = startRow + rowSpan - 1; // include
	int endColumn = startColumn + colSpan - 1; // include

	int endRow2 = endRow;
	int endColumn2 = endColumn;

	// Collapse endRow2 if need
	if (endRow2 > lastRow) {
	    endRow2 = lastRow;
	}

	// Collapse endColumn2 if need
	if (endColumn2 > lastColumn) {
	    endColumn2 = lastColumn;
	}

	int stopRow = endRow2; // include
	int stopColumn = endColumn2; // include

	boolean stop = false;
	for (int row = startRow; row <= endRow2; row++) {
	    for (int column = startColumn; column <= endColumn2; column++) {

		// Check free cell
		// If cell is not free than processing stopColumn and stopRow
		// We calculate stopColumn only for first row because column
		// growing is priority!
		if (!matrix[row][column]) {

		    // Check before column
		    // If before column is left than:
		    // - first row: update stopColumn
		    // - other row: update stopRow and stop processing

		    if (column - 1 < stopColumn) {
			if (row == startRow) {
			    stopColumn = column - 1;
			} else {
			    stopRow = row - 1;
			    stop = true;
			    break;
			}
		    }

		}
	    }

	    if (stop) {
		break;
	    }
	}

	int stopRowSpan = stopRow - startRow + 1;
	int stopColSpan = stopColumn - startColumn + 1;

	if (stopRowSpan < rowSpan) {
	    // last row - grow
	    if (stopRow == rowCount - 1) {
		stopRowSpan = rowSpan;
	    }
	}

	if (stopColSpan < colSpan) {
	    // last column - grow
	    if (stopColumn == columnCount - 1) {
		stopColSpan = colSpan;
	    }
	}

	cell.rowSpan = stopRowSpan;
	cell.colSpan = stopColSpan;

    }
	    
	
    public static class Cell {
	
	int row;
	int column;
	int rowSpan = 1;
	int colSpan = 1;
	
	
	public String toString() {
	    return "Cell[row=" + row + ", column=" + column + ", rowSpan=" + rowSpan + ", colSpan=" + colSpan +  "]";
	}
    }
}
