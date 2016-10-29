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
package org.plazmaforge.framework.report.export;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.BorderRegion;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.GridLayout;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.uwt.graphics.Color;

/**
 * @author ohapon
 *
 */
public class ExportHelper {

    private ExportHelper() {
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////
    // GRID
    //////////////////////////////////////////////////////////////////////////////////////////////
    
    public int calculateWidth(Grid grid) {
	return calculateWidth(grid == null ? null : grid.getColumns());
    }
    
    public static int calculateWidth(List<Column> columns) {
	if (columns == null) {
	    return 0;
	}
	int width = 0;
	for (Column column : columns) {
	    width += column.getWidth();
	}
	return width;
    }

    public static int calculateHeight(Grid grid) {
	return calculateHeight(grid == null ? null : grid.getRows());
    }
    
    public static int calculateHeight(List<Row> rows) {
	int height = 0;
	for (Row row : rows) {
	    height += row.getHeight();
	}
	return height;
    }
    
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
    
    
    public static GridLayout getGridLayout(Grid grid, Pen pen) {
	if (grid == null) {
	    return null;
	}
	
	if (pen == null) {
	    pen = new Pen(1, Pen.LINE_STYLE_SOLID, Color.BLACK); 
	}
	
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
		
		// Create cell border by border strategy
		// TODO: cell: 4 borders: _|
		Border cellBorder = new Border();
		
		cellBorder.setBottomPen(pen);
		cellBorder.setRightPen(pen);
		
		if (columnIndex == 0) {
		    cellBorder.setLeftPen(pen);
		}
		if (rowIndex == 0) {
		    cellBorder.setTopPen(pen);
		}
		
		
		if (cellBorder != null) {
		    
		    // Add cell border
		    String cellKey = layout.getCellKey(columnIndex, rowIndex);
		    cellBorders.put(cellKey, cellBorder);
		    
		    // Add column border (left, right)
		    Pen leftPen = normalizePen(cellBorder.hasLeftPen() ? cellBorder.getLeftPen() : null);
		    Pen rightPen = normalizePen(cellBorder.hasRightPen() ? cellBorder.getRightPen() : null);
		    if (leftPen != null) {
			mergeBorder(columnBorders, columnIndex, leftPen.getLineWidth(), false); // column: prev border 
		    }
		    if (rightPen != null) {
			mergeBorder(columnBorders, nextColumnIndex - 1, rightPen.getLineWidth(), true); // column: next border 
		    }

		    // Add row border (top, bottom)
		    Pen topPen = normalizePen(cellBorder.hasTopPen() ? cellBorder.getTopPen() : null);
		    Pen bottomPen = normalizePen(cellBorder.hasBottomPen() ? cellBorder.getBottomPen() : null);
		    if (topPen != null) {
			mergeBorder(rowBorders, rowIndex, topPen.getLineWidth(), false); // row: prev border 
		    }
		    if (bottomPen != null) {
			mergeBorder(rowBorders, nextRowIndex - 1, bottomPen.getLineWidth(), true); // row: next border 
		    }
		    
		}
		
		columnIndex = nextColumnIndex;
	    }
	}
	
	int gridWidthByColumns = calculateWidth(columns);
	int gridHeightByRows = calculateHeight(rows);
	
	int gridBorderWidth = layout.calculateValue(columnBorders);
	int gridBorderHeight = layout.calculateValue(rowBorders);

	int gridWidth = gridWidthByColumns + gridBorderWidth;
	int gridHeight = gridHeightByRows + gridBorderHeight;

	layout.setWidthByColumns(gridWidthByColumns);
	layout.setHeightByRows(gridHeightByRows);

	layout.setAreaWidth(gridWidth);
	layout.setAreaHeight(gridHeight);

	return layout;
    }
    
    
    private static Pen normalizePen(Pen pen) {
	if (pen == null) {
	    return null;
	}
	return pen.isEmpty() ? null : pen;
    }
    
    private static void mergeBorder(Map<Integer, BorderRegion> borders, int index, int width, boolean next) {
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
