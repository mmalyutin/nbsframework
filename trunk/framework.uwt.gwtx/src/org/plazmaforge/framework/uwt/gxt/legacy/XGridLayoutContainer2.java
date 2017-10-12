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
package org.plazmaforge.framework.uwt.gxt.legacy;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.gxt.layout.XGridData;
import org.plazmaforge.framework.uwt.gxt.layout.XGridData.HorizontalAlignment;
import org.plazmaforge.framework.uwt.gxt.layout.XGridData.VerticalAlignment;
import org.plazmaforge.framework.uwt.gxt.layout.XGridLayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant;

/**
 * FlexTable grid layout implementation 
 * 
 * Problems:
 * - Horizontal/Vertical cell filling
 * - onResize
 * 
 * @author ohapon
 *
 */
public class XGridLayoutContainer2 extends FlexTable {

    private XGridLayout gridLayout;
    
    public XGridLayoutContainer2() {
	this(new XGridLayout());
    }

    public XGridLayoutContainer2(XGridLayout gridLayout) {
	super();
	this.gridLayout = gridLayout;
    }

    public XGridLayout getGridLayout() {
        return gridLayout;
    }
    
    @Override
    public void add(Widget child) {
	addChild(child);
    }
    
    protected void addChild(Widget widget) {
	
	
	// Get layout info
	int layoutColumnCount = gridLayout.getColumnCount();
	
	// Get layout data info
	Object layoutData = widget.getLayoutData();
	XGridData gridData = null;
	if (layoutData != null && layoutData instanceof XGridData) {
	    gridData = (XGridData) layoutData;
	}
	
	int rowCount = getRowCount();
	Cell freeCell = null;
	if (rowCount == 0) {
	    freeCell = new Cell();
	    freeCell.row = 0;
	    freeCell.column = 0;
	    
	    populateCellSpan(freeCell, gridData);
	    
	    // Normalize only for colSpan: collapse
	    normalizeCellSpan(freeCell, layoutColumnCount);
	    addWidget(freeCell, widget);
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
	    cellCount = getCellCount(row);
	    
	    // Set by default start column = 0
	    int column = 0;
	    for (int cellInex = 0; cellInex < cellCount; cellInex++) {

		if (getWidget(row, cellInex) == null) {
		    // No widget - no cell
		    continue;
		}
		
		colSpan = getFlexCellFormatter().getColSpan(row, cellInex);
		rowSpan = getFlexCellFormatter().getRowSpan(row, cellInex);
		
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
	
	populateCellSpan(freeCell, gridData);
	
	GWT.log("OUT: free" + freeCell + ", growColumn=" + growColumn + ", growRow=" + growRow);
	
	if (growRow || growColumn) {
	    if (growColumn) {
		// Normalize only for colSpan: collapse
		normalizeCellSpan(freeCell, layoutColumnCount);
	    }
	    addWidget(freeCell, widget);
	    return;
	}
	
	// Normalize: collapse rowSpan and colSpan if need
	normalizeCellSpan(freeCell, matrix, rowCount, columnCount, layoutColumnCount);
	addWidget(freeCell, widget);

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
	cell.verticalAlign = toVerticalAlign(xLayoutData.getVerticalAlign());
	cell.horizontalAlign = toHorizontalAlign(xLayoutData.getHorizontalAlign());
    }
    
    protected VerticalAlignmentConstant toVerticalAlign(VerticalAlignment vAlign) {
	if (vAlign == null) {
	    return null;
	}
	if (VerticalAlignment.TOP.equals(vAlign)) { 
   	    return HasVerticalAlignment.ALIGN_TOP;
   	} else if (VerticalAlignment.BOTTOM.equals(vAlign)) {
   	    return HasVerticalAlignment.ALIGN_BOTTOM;
   	} else if (VerticalAlignment.MIDDLE.equals(vAlign)) {
   	    return HasVerticalAlignment.ALIGN_MIDDLE;
   	}
	// VerticalAlignment.FILL ignore
	return null;
    }
    
    protected HorizontalAlignmentConstant toHorizontalAlign(HorizontalAlignment hAlign) {
	if (hAlign == null) {
	    return null;
	}
	if (HorizontalAlignment.LEFT.equals(hAlign)) {
	    return HasHorizontalAlignment.ALIGN_LEFT;
	} else if (HorizontalAlignment.RIGHT.equals(hAlign)) {
	    return HasHorizontalAlignment.ALIGN_RIGHT;
	} else if (HorizontalAlignment.CENTER.equals(hAlign)) {
	    return HasHorizontalAlignment.ALIGN_CENTER;
	}
	// HorizontalAlignment.FILL ignore
	return null;
    }
    
    protected void addWidget(Cell cell, Widget widget) {
  	if (widget == null) {
  	    return;
  	}
  	int row = 0;
  	int column = 0;
  	int rowSpan = 1;
  	int colSpan = 1;
  	HorizontalAlignmentConstant horizontalAlign = null;
	VerticalAlignmentConstant verticalAlign = null;
	
  	if (cell != null) {
  	    row = cell.row;
  	    column = cell.column;
  	    rowSpan = cell.rowSpan;
  	    colSpan = cell.colSpan;
  	    horizontalAlign = cell.horizontalAlign;
  	    verticalAlign = cell.verticalAlign;
  	}
  	
  	// add widget to cell (row, column)
  	int rowCount = getRowCount();
  	int cellCount = 0;
  	if (rowCount > 0 && row < rowCount) {
  	    cellCount = getCellCount(row);
  	}
  	
  	/*
	if (widget instanceof TextButton) {
	    XSimpleContainer c = new XSimpleContainer();
	    c.setWidget(widget);
	    c.setWidth("100%");
	    widget = c;
	}
	*/
  	
  	setWidget(row, cellCount, widget);
  	
  	if (colSpan > 0) {
  	    getFlexCellFormatter().setColSpan(row, column, colSpan);
  	}
  	if (rowSpan > 0) {
  	    getFlexCellFormatter().setRowSpan(row, column, rowSpan);
  	}
  	if (horizontalAlign != null) {
  	   getFlexCellFormatter().setHorizontalAlignment(row, column, horizontalAlign);
  	    
  	}
  	if (verticalAlign != null) {
  	    getFlexCellFormatter().setVerticalAlignment(row, column, verticalAlign);
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
	
	HorizontalAlignmentConstant horizontalAlign;
	VerticalAlignmentConstant verticalAlign;
	
	public String toString() {
	    return "Cell[row=" + row + ", column=" + column + ", rowSpan=" + rowSpan + ", colSpan=" + colSpan +  "]";
	}
    }    
}
