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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.BorderRegion;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Grid;
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
	int height = 0;
	int nextRowIndex = rowIndex + cell.getRowspan();
	for (int i = rowIndex; i < nextRowIndex; i++) {
	    height += rows.get(i).getHeight();
	}
	return height;
    }
    
    
    public static BorderLayout getBorderLayout(Grid grid, Pen pen) {
	if (grid == null) {
	    return null;
	}
	
	if (pen == null) {
	    pen = new Pen(1, Pen.LINE_STYLE_SOLID, Color.BLACK); 
	}
	
	BorderLayout borderLayout = new BorderLayout();
	Map<String, Border> cellBorders = new HashMap<String, Border>();
	List<BorderRegion> columnBorders = new ArrayList<BorderRegion>();
	List<BorderRegion> rowBorders = new ArrayList<BorderRegion>();
	
	borderLayout.setCellBorders(cellBorders);
	borderLayout.setColumnBorders(columnBorders);
	borderLayout.setRowBorders(rowBorders);
	
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
		
		String cellKey = borderLayout.getCellKey(columnIndex, rowIndex);
		cellBorders.put(cellKey, cellBorder);
		
		columnIndex = nextColumnIndex;
	    }
	}
	
	return borderLayout;
    }
    
    
    public static class BorderLayout {
	
	private Map<String, Border> cellBorders;
	
	private List<BorderRegion> columnBorders;
	
	private List<BorderRegion> rowBorders;

	public BorderLayout() {
	    super();
	}

	public Map<String, Border> getCellBorders() {
	    return cellBorders;
	}

	public void setCellBorders(Map<String, Border> cellBorders) {
	    this.cellBorders = cellBorders;
	}

	public List<BorderRegion> getColumnBorders() {
	    return columnBorders;
	}

	public void setColumnBorders(List<BorderRegion> columnBorders) {
	    this.columnBorders = columnBorders;
	}

	public List<BorderRegion> getRowBorders() {
	    return rowBorders;
	}

	public void setRowBorders(List<BorderRegion> rowBorders) {
	    this.rowBorders = rowBorders;
	}

	public String getCellKey(int columnIndex, int rowIndex) {
	    return "" + columnIndex + ":" + rowIndex;
	}
	
	
    }
}
