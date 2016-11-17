/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

/**
 * 
 */
package org.plazmaforge.framework.report.model.base.grid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.BorderRegion;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.uwt.graphics.Color;

/**
 * @author ohapon
 *
 */
public class GridUtils {

    private GridUtils() {
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // COLUMNS
    //////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Calculate with of columns
     * @param grid
     * @return
     */
    public int calculateColumnsWidth(Grid grid) {
	return calculateColumnsWidth(grid == null ? null : grid.getColumns());
    }
    
    /**
     * Calculate with of columns
     * @param columns
     * @return
     */
    public static int calculateColumnsWidth(List<Column> columns) {
	if (columns == null) {
	    return 0;
	}
	int width = 0;
	for (Column column : columns) {
	    width += column.getWidth();
	}
	return width;
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // ROWS
    //////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Calculate height of rows
     * @param grid
     * @return
     */
    public static int calculateRowsHeight(Grid grid) {
	return calculateRowsHeight(grid == null ? null : grid.getRows());
    }
    
    /**
     * Calculate height of rows
     * @param rows
     * @return
     */
    public static int calculateRowsHeight(List<Row> rows) {
	int height = 0;
	for (Row row : rows) {
	    height += row.getHeight();
	}
	return height;
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // CELLS
    //////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Calculate width of cell by column span (without border)
     * @param cell
     * @param columns
     * @param columnIndex
     * @return
     */
    public static int calculateCellWidth(Cell cell, List<Column> columns, int columnIndex) {
	if (cell == null || columns == null) {
	    return 0;
	}
	int width = 0;
	int nextColumnIndex = columnIndex + cell.getColspan();
	for (int i = columnIndex; i < nextColumnIndex; i++) {
	    width += columns.get(i).getWidth();
	}
	return width;
    }
    
    /**
     * Calculate height of cell by row span (without border)
     * @param cell
     * @param rows
     * @param rowIndex
     * @return
     */
    public static int calculateCellHeight(Cell cell, List<Row> rows, int rowIndex) {
	if (cell == null || rows == null) {
	    return 0;
	}	
	int height = 0;
	int nextRowIndex = rowIndex + cell.getRowspan();
	for (int i = rowIndex; i < nextRowIndex; i++) {
	    height += rows.get(i).getHeight();
	}
	return height;
    }
    
    /**
     * Calculate width of cell by column span with border
     * @param layout
     * @param cell
     * @param columns
     * @param columnIndex
     * @return
     */
    public static int calculateCellWidth(GridLayout layout, Cell cell, List<Column> columns, int columnIndex) {
	if (cell == null || columns == null) {
	    return 0;
	}
	int width = 0;
	int nextColumnIndex = columnIndex + cell.getColspan();
	for (int i = columnIndex; i < nextColumnIndex; i++) {
	    width += columns.get(i).getWidth();
	    if (layout != null && cell.getColspan() > 0) {
		BorderRegion border = layout.getColumnBorder(i);
		if (border != null) {
		    if (i > columnIndex) {
			width += border.getPrevWidth();
		    }
		    if (i < nextColumnIndex - 1) {
			width += border.getNextWidth();
		    }
		}
	    }
	}
	return width;
    }

    /**
     * Calculate height of cell by row span with border
     * @param layout
     * @param cell
     * @param rows
     * @param rowIndex
     * @return
     */
    public static int calculateCellHeight(GridLayout layout, Cell cell, List<Row> rows, int rowIndex) {
	if (cell == null || rows == null) {
	    return 0;
	}	
	int height = 0;
	int nextRowIndex = rowIndex + cell.getRowspan();
	for (int i = rowIndex; i < nextRowIndex; i++) {
	    height += rows.get(i).getHeight();
	    if (layout != null && cell.getRowspan() > 0) {
		BorderRegion border = layout.getRowBorder(i);
		if (border != null) {
		    if (i > rowIndex) {
			height += border.getPrevWidth();
		    }
		    if (i < nextRowIndex - 1) {
			height += border.getNextWidth();
		    }
		}
	    }
	}
	return height;
    }
    
    
    public static GridLayout getGridLayout(Grid grid) {
	if (grid == null) {
	    return null;
	}
	
	CellBorderRule cellBorderRule = grid.getCellBorderRule();
	
	Pen defCellBorder = grid.hasCellBorder() ? grid.getCellBorder() : null;
	Pen defColumnBorder = defCellBorder;
	Pen defRowBorder = defCellBorder;
	
	// Override column border
	if (!grid.isEmptyColumnBorder()) {
	    defColumnBorder  = grid.getColumnBorder();
	}
	
	// Override row border
	if (!grid.isEmptyRowBorder()) {
	    defRowBorder  = grid.getRowBorder();
	}
	
	return getGridLayout(grid, cellBorderRule, defColumnBorder, defRowBorder); 
    }
    
    public static GridLayout getGridLayout(Grid grid, CellBorderRule cellBorderRule, Pen defColumnBorder, Pen defRowBorder) {
	if (grid == null) {
	    return null;
	}
	
	// Default borders (pen)
	Pen defLeftPen = null;
	Pen defTopPen = null;
	Pen defRightPen = null;
	Pen defBottomPen = null;

	// Current borders (pen)
	Pen leftPen = null;
	Pen topPen = null;
	Pen rightPen = null;
	Pen bottomPen = null;

	// Default R (from row) borders (pen) 
	Pen defRLeftPen = null;
	Pen defRTopPen = null;
	Pen defRRightPen = null;
	Pen defRBottomPen = null;
	
	// Default R (from row) borders
	Pen defRCellBorder = null;
	Pen defRColumnBorder = null;
	Pen defRRowBorder = null;
	
	// Transfer default column border (left, right)
	if (defColumnBorder != null && !defColumnBorder.isEmpty()) {
	    defLeftPen = defColumnBorder.clone();
	    defRightPen = defColumnBorder.clone();
	}
	
	// Transfer default row border (top, bottom)
	if (defRowBorder != null && !defRowBorder.isEmpty()) {
	    defTopPen = defRowBorder.clone();
	    defBottomPen = defRowBorder.clone();
	}
	
	
	defLeftPen = defaultPen(defLeftPen);
	defTopPen = defaultPen(defTopPen);
	defRightPen = defaultPen(defRightPen);
	defBottomPen = defaultPen(defBottomPen);
	
	GridLayout layout = new GridLayout();
	Map<String, Border> cellBorders = new HashMap<String, Border>();
	Map<Integer, BorderRegion> columnBorders = new HashMap<Integer, BorderRegion>();
	Map<Integer, BorderRegion> rowBorders = new HashMap<Integer, BorderRegion>();
	
	layout.setCellBorders(cellBorders);
	layout.setColumnBorders(columnBorders);
	layout.setRowBorders(rowBorders);
	
	int columnCount = grid.getColumnCount();
	int rowCount = grid.getRowCount();
	
	List<Column> columns = grid.getColumns();
	List<Row> rows = grid.getRows();
	
	Column column = null;
	Row row = null;
	Cell cell = null;
	
	int columnIndex = 0;
	int colspan = 0;
	int rowspan = 0;
	
	
	for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
	    
	    row = rows.get(rowIndex);
	    
	    int cellCount = row.getCellCount();
 	    List<Cell> cells = row.getCells();
	    
 	    columnIndex = 0;
 	    
 	    defRLeftPen = null;
 	    defRTopPen = null;
 	    defRRightPen = null;
 	    defRBottomPen = null;
 		
 	    
 	    defRCellBorder = row.hasCellBorder() ? row.getCellBorder() : null;
 	    defRCellBorder = normalizeNonePen(defRCellBorder);
 	   
 	    defRColumnBorder = defRCellBorder;
 	    defRRowBorder = defRCellBorder;
 	
 	    // Override column (R) border
 	    if (row.hasColumnBorder()) {
 		defRColumnBorder = normalizeNonePen(row.getColumnBorder());
 	    }

 	    // Override row (R) border
 	    if (row.hasRowBorder()) {
 		defRowBorder = normalizeNonePen(row.getRowBorder());
 	    }

 	    // Transfer default column border (left, right)
 	    if (defRColumnBorder != null) {
 		defRLeftPen = defRColumnBorder.clone();
 		defRRightPen = defRColumnBorder.clone();
 	    }
		
 	    // Transfer default row border (top, bottom)
 	    if (defRRowBorder != null) {
 		defRTopPen = defRRowBorder.clone();
 		defRBottomPen = defRRowBorder.clone();
 	    }
		
 	   
	    for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
		
		cell = cells.get(cellIndex);
		
		colspan = cell.getColspan();
		rowspan = cell.getRowspan();
		int nextColumnIndex = columnIndex + colspan;
		int nextRowIndex = rowIndex + rowspan;
		
		if (nextColumnIndex > columnCount) {
		    // overflow columns
		    break;
		}
		if (nextRowIndex > rowCount) {
		    // overflow rows
		    break;
		}
		int lastColumnIndex = nextColumnIndex - 1;
		int lastRowIndex = nextRowIndex - 1;
		
		
		// Transfer default to current borders 
		leftPen = defLeftPen;
		topPen = defTopPen;
		rightPen = defRightPen;
		bottomPen = defBottomPen;
		
		// Transfer R (from row) to current borders 
		if (defRLeftPen != null ) {
		    leftPen = defRLeftPen;
		}
		if (defRTopPen != null ) {
		    topPen = defRTopPen;
		}
		if (defRRightPen != null ) {
		    rightPen = defRRightPen;
		}
		if (defRBottomPen != null ) {
		    bottomPen = defRBottomPen;
		}
		
		
		Border cellBorder = null;
		Border orgBorder = cell.hasBorder() ? cell.getBorder() : null;
		
		if (cellBorderRule == null) {
		    // Use original cell border
		    cellBorder = orgBorder;
		} else {

		    // Create cell border by border type
		    cellBorder = new Border();
	
		    //////////////////////////////////////////////////////////
		    // General cell border: _| (bottom, right)
		    //////////////////////////////////////////////////////////
		    
		    // bottom
		    if (CellBorderRule.isBottom(cellBorderRule)) {
			if ( lastRowIndex != rowCount - 1 
				|| (lastRowIndex == rowCount - 1 && CellBorderRule.isLastBottom(cellBorderRule))) {
			    cellBorder.setBottom(bottomPen);
			}
		    }

		    // right
		    if (CellBorderRule.isRight(cellBorderRule)) {
			
			if ((lastColumnIndex != columnCount - 1)
				|| (lastColumnIndex == columnCount - 1 && CellBorderRule.isLastRight(cellBorderRule))) {
			    cellBorder.setRight(rightPen);
			}
		    }

		    // top: first row
		    if (rowIndex == 0) {
			if (CellBorderRule.isFirstTop(cellBorderRule)) {
			    cellBorder.setTop(topPen);
			}			
		    }

		    // left: first column
		    if (columnIndex == 0) {
			if (CellBorderRule.isFirstLeft(cellBorderRule)) {
			    cellBorder.setLeft(leftPen);
			}
		    }
		    
		    if (orgBorder != null) {
			if (orgBorder == Border.NONE) {
			    cellBorder = null;
			} else {
			    //if (!orgBorder.isEmpty()) {
				Pen orgLeftPen = orgBorder.hasLeft() ? orgBorder.getLeft() : null;
				Pen orgTopPen = orgBorder.hasTop() ? orgBorder.getTop() : null;
				Pen orgRightPen = orgBorder.hasRight() ? orgBorder.getRight() : null;
				Pen orgBottomPen = orgBorder.hasBottom() ? orgBorder.getBottom() : null;
				
				orgLeftPen = normalizeNonePen(orgLeftPen);
				orgTopPen = normalizeNonePen(orgTopPen);
				orgRightPen = normalizeNonePen(orgRightPen);
				orgBottomPen = normalizeNonePen(orgBottomPen);
				
				if (orgLeftPen != null) {
				    cellBorder.setLeft(orgLeftPen == Pen.NONE ? null : orgLeftPen);
				}
				if (orgTopPen != null) {
				    cellBorder.setTop(orgTopPen == Pen.NONE ? null : orgTopPen);
				}
				if (orgRightPen != null) {
				    cellBorder.setRight(orgRightPen == Pen.NONE ? null : orgRightPen);
				}
				if (orgBottomPen != null) {
				    cellBorder.setBottom(orgBottomPen == Pen.NONE ? null : orgBottomPen);
				}
				
				//if (cellBorder.isEmpty()) {
				//    cellBorder = null;
				//}
				
			    //}
			}
		    }
		    
		}

		if (cellBorder != null && cellBorder.isEmpty()) {
		    cellBorder = null;
		}

		
		if (cellBorder != null) {
		    
		    // Add cell border
		    String cellKey = layout.getCellKey(columnIndex, rowIndex);
		    cellBorders.put(cellKey, cellBorder);
		    
		    // Add column border region (left, right)
		    leftPen = normalizePen(cellBorder.hasLeft() ? cellBorder.getLeft() : null);
		    rightPen = normalizePen(cellBorder.hasRight() ? cellBorder.getRight() : null);
		    if (leftPen != null) {
			mergeBorderRegion(columnBorders, columnIndex, leftPen.getLineWidth(), false); // column: prev border 
		    }
		    if (rightPen != null) {
			mergeBorderRegion(columnBorders, nextColumnIndex - 1, rightPen.getLineWidth(), true); // column: next border 
		    }

		    // Add row border region (top, bottom)
		    topPen = normalizePen(cellBorder.hasTop() ? cellBorder.getTop() : null);
		    bottomPen = normalizePen(cellBorder.hasBottom() ? cellBorder.getBottom() : null);
		    if (topPen != null) {
			mergeBorderRegion(rowBorders, rowIndex, topPen.getLineWidth(), false); // row: prev border 
		    }
		    if (bottomPen != null) {
			mergeBorderRegion(rowBorders, nextRowIndex - 1, bottomPen.getLineWidth(), true); // row: next border 
		    }
		    
		}
		
		columnIndex = nextColumnIndex;
	    }
	}
	
	int gridColumnsWidth = calculateColumnsWidth(columns);
	int gridRowsHeight = calculateRowsHeight(rows);
	
	int gridBorderWidth = layout.calculateValue(columnBorders);
	int gridBorderHeight = layout.calculateValue(rowBorders);

	int gridWidth = gridColumnsWidth + gridBorderWidth;
	int gridHeight = gridRowsHeight + gridBorderHeight;

	layout.setColumnsWidth(gridColumnsWidth);
	layout.setRowsHeight(gridRowsHeight);

	layout.setAreaWidth(gridWidth);
	layout.setAreaHeight(gridHeight);

	return layout;
    }
    
    
    public static Pen normalizeNonePen(Pen pen) {
	if (pen == Pen.NONE) {
	    return pen;
	}
	return normalizePen(pen);
    }
    
    public static Pen normalizePen(Pen pen) {
	if (pen == null) {
	    return null;
	}
	return pen.isEmpty() ? null : pen;
    }
    
    public static Pen createDefaultPen() {
	return new Pen(1, Pen.LINE_STYLE_SOLID, Color.BLACK);
    }

    public static Pen defaultPen(Pen pen) {
	pen = normalizePen(pen);
	return pen == null ? createDefaultPen() : pen;
    }
    
    private static void mergeBorderRegion(Map<Integer, BorderRegion> borders, int index, int width, boolean next) {
	if (width <= 0) {
	    return;
	}
	
	// get border
	BorderRegion border = borders.get(index);
	if (border == null) {
	    border = new BorderRegion();
	    borders.put(index, border);
	}
	
	// get old width
	int oldWidth = next ? border.getNextWidth() : border.getPrevWidth();
	
	// set new width
	if (width > oldWidth) {
	    if (next) {
		border.setNextWidth(width);
	    } else {
		border.setPrevWidth(width);
	    }
	}
    }
    
    
}
