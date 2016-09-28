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

import java.util.List;

import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;

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
}
