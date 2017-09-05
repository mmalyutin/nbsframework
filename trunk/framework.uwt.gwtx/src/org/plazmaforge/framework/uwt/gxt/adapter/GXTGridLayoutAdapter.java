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
import org.plazmaforge.framework.uwt.gxt.layout.XGridLayout;
import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.layout.GridLayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
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
	HasWidgets container = parent.getContainer();
	if (!(container instanceof FlexTable)) {
	    throw new UWTException("Container is not GWT-FlexTable");
	}
	FlexTable table  = (FlexTable) container;
	
	//TODO: Simple implementation GridLayout: column = 2, rows = children
	int columnCount = 2;
	addChild(table, columnCount, widget);
	//parent.relayout();
    }
   
    private void buildTestWidgets(FlexTable table) {
	GWT.log("OUT: Start build test widgets...");
	
	//FlexCellFormatter cellFormatter = table.getFlexCellFormatter();

	table.setWidget(0, 0, new Label("Horizontal: blah, blah, blah, blah, blah, blah, blah, blah, blah, blah, blah"));
	table.getFlexCellFormatter().setColSpan(0, 0, 2);

	table.setWidget(0, 2, new Label("<div style='background-color: red'> Vertical: blah, </br> blah, blah, </br> blah, blah</div>"));
	table.getFlexCellFormatter().setRowSpan(0, 2, 3);
	
	table.setWidget(1, 0, new Label("GWT-1.1"));
	table.setWidget(1, 1, new Label("GWT-1.2"));
	
	table.setWidget(2, 0, new Label("GWT-2.1"));
	table.setWidget(2, 1, new Label("GWT-2.2"));
	
	
	int rowCount = table.getRowCount();
	int cellCount = 0;
	int colSpan = 0;
	int rowSpan = 0;
	Widget widget = null;
	for (int row = 0; row < rowCount; row++) {
	    cellCount = table.getCellCount(row);
	    for (int column = 0; column < 3; column++) {
		if (cellCount <= column) {
		    widget = null;
		    colSpan = 0;
		    rowSpan = 0;
		} else {
		    widget = table.getWidget(row, column);
		    colSpan = table.getFlexCellFormatter().getColSpan(row, column);
		    rowSpan = table.getFlexCellFormatter().getRowSpan(row, column);
		}
		GWT.log("OUT: [" + row + ", " + column + "]: " + (widget == null ? "" : ("[" + colSpan + ", " + rowSpan + "]")));
	    }
	    // GWT.log("OUT: row=" + row + ", cellCount=" + cellCount);

	}
	
    }
    
    private void addChild(FlexTable table, int layoutColumnCount, Widget widget) {
	

	int rowCount = table.getRowCount();
	if (rowCount == 0) {
	    //TODO: colSpan, rowSpan, alignment...
	    table.setWidget(rowCount, 0, widget);
	    return;
	}
	
	// Real column count in the table
	int columnCount = 0;
	
	// Cell count of a row 
	// It is small/virtual cell without colSpan and rowSpan than starts from the row
	int cellCount = 0;
	
	// Create array of column count of row 
	// Only for small/virtual cells without colSpan and rowSpan than start from the row
	//int[] rowColumns = new int[rowCount];
	
	int colSpan = 0;
	int rowSpan = 0;
	List<Cell> cells = new ArrayList<Cell>();
	
	// Analyze table structure: find real cells (with colSpan and rowSpan)
	for (int row = 0; row < rowCount; row++) {
	    cellCount = table.getCellCount(row);
	    
	    //rowColumns[row] = cellCount;
	    
	    // Calculate columnCount - max of cellCount
	    if (cellCount > columnCount) {
		columnCount = cellCount;
	    }
	    
	    for (int column = 0; column < cellCount; column++) {

		if (table.getWidget(row, column) == null) {
		    continue;
		}
		colSpan = table.getFlexCellFormatter().getColSpan(row, column);
		rowSpan = table.getFlexCellFormatter().getRowSpan(row, column);
		
		// Create new cell of widget
		Cell cell = new Cell();
		cell.column = column;		
		cell.row = row;
		cell.colSpan = colSpan;
		cell.rowSpan = rowSpan;	
		
		cells.add(cell);
		GWT.log("OUT: " + cell);
	    }
	}
	

	GWT.log("OUT: TableInfo [rowCount=" + rowCount + ", columnCount=" + columnCount + "]");
	
	// Fill cell matrix
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
	
	Cell freeCell = null;
	
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
	GWT.log("OUT: FreeCell: " + freeCell + ", growColumn=" + growColumn + ", growRow=" + growRow);
	
	normalizeCellSpan(freeCell, matrix, rowCount, columnCount, layoutColumnCount);
	
	//TODO: colSpan, rowSpan, alignment...
	table.setWidget(freeCell.row, freeCell.column, widget);
	
    }

	    
    protected void normalizeCellSpan(Cell cell, boolean[][] matrix, int rowCount, int columnCount, int layoutColumnCount) {
	if (cell == null) {
	    return;
	}

	int rowSpan = cell.rowSpan;
	int colSpan = cell.colSpan;
	if (rowSpan < 1) {
	    rowSpan = 1; // fixed rowSpan
	    cell.rowSpan = rowSpan;
	}
	if (colSpan < 1) {
	    colSpan = 1; // fixed colSpan
	    cell.colSpan = colSpan;
	}

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
	int column;
	int row;
	int colSpan = 1;
	int rowSpan = 1;
	
	public String toString() {
	    return "Cell[column=" + column + ", row=" + row + ", colSpan=" + colSpan + ", rowSpan=" + rowSpan + "]";
	}
    }
}
