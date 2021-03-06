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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.GridLayout;
import org.plazmaforge.framework.report.model.base.grid.GridUtils;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * @author ohapon
 *
 */
public abstract class AbstractWorkbookExporter extends AbstractBaseExporter {

    
    
    protected int absoluteRowIndex;
    
    protected int absoluteColumnIndex;
    
    
    ////
    
    protected abstract void createXWorkbook();

    protected abstract void writeXWorkbook(OutputStream os) throws IOException;

    protected abstract void createXSheet(String name);

    protected abstract void createXRow(int rowIndex);

    protected abstract void createXCell(int columnIndex);

    protected abstract void setXCellStyle(Cell cell, Border border);

    protected abstract void setXCellSize(Cell cell, int rowIndex, int columnIndex, int rowspan, int colspan);
    
    protected abstract void setXCellValue(Cell cell);

    protected abstract void setXColumnWidth(int columnIndex, int columnWidth);

    protected abstract void setXRowHeight(int height);
      
    ////
    
    
  
    
    @Override
    public void exportDocument(Document document) throws RTException {
	String outputType = getOutputType();
	if (!isValidOutputType(outputType)) {
	    throw new RTException("Invalid output type: '" + outputType + "'");
	}
	if (isFileNameOutputType(outputType)) {
	    String fileName = (String) getData(PROPERTY_OUTPUT_FILE_NAME);
	    if (fileName == null) {
		throw new RTException("Output file name is empty");
	    }
	    exportDocument(document, fileName);
	    return;
	}
	throw new RTException("Invalid output type: '" + outputType + "'");
	
    }
    
    @Override
    public void exportPage(Page page) throws RTException {
	// TODO Auto-generated method stub
	
    }
  
    protected void initExport() {
	absoluteRowIndex = 0;
	absoluteColumnIndex = 0;
    }
    
    protected void exportDocument(Document document, String fileName) throws RTException {
 	initExport();
 	OutputStream os = null;
 	try {
 	
 	    createXWorkbook();
 	    createXSheet("XLSExport"); // TODO
 	    
 	    exportPages(document);
 	    
 	    os = new FileOutputStream(fileName);
 	    writeXWorkbook(os);
 	    os.flush();
 	    
 	} catch (Exception ex) {
 	    throw new RTException(ex);
 	}
     }  
     
     protected void exportPages(Document document) throws RTException {
 	if (!document.hasPages()) {
 	    return;
 	}
 	int pageCount = document.getPageCount();
 	for (int i = 0; i < pageCount; i++) {
 	    exportPage(document.getPage(i), i == 0);
 	}
     }
     
     protected void exportPage(Page page, boolean firstPage) throws RTException {
 	if (!page.hasChildren()) {
 	    return;
 	}
 	List<Element> children = page.getChildren();
 	int childrenCount = page.getChildrenCount();
 	for (int i = 0; i < childrenCount; i++) {
 	    Element child = children.get(i);
 	    if (child instanceof Grid) {
 		exportGrid((Grid) child, firstPage);
 	    }
 	}
     }

     protected void exportGrid(Grid grid, boolean firstPage) throws RTException {
 	if (!grid.hasRows()) {
 	    return;
 	}
 	
 	int columnCount = grid.getColumnCount();
 	int rowCount = grid.getRowCount();
 	
 	List<Column> columns = grid.getColumns();
 	List<Row> rows = grid.getRows();
 	
 	GridLayout layout = GridUtils.getGridLayout(grid);
 	
 	Color contextBackground = null;
 	Color contextForeground = null;
 	Font contextFont = null;
 	
 	// Current parent
 	parentBackground = null;
 	parentForeground = null;
 	parentFont = null;
 	
 	// Current
 	background = null;
 	foreground = null;
 	font = null;	
 	
 	Color gridBackground = null;
 	Color gridForeground = null;
 	Font gridFont = null;
 	
 	Color rowBackground = null;
 	Color rowForeground = null;
 	Font rowFont = null;

 	Color cellBackground = null;
 	Color cellForeground = null;
 	Font cellFont = null;
 	
 	
 	// grid: parent gc
 	parentBackground = contextBackground;
 	parentForeground = contextForeground;
 	parentFont = contextFont;
 	
 	// grid: load gc
 	gridBackground = grid.getBackground();
 	gridForeground = grid.getForeground();
 	gridFont = grid.getFont();

 	// grid: current gc
 	background = gridBackground;
 	foreground = gridForeground;
 	font = gridFont;
 	
 	
 	// grid: background
 	// [?]

 	// grid: normalize current gc
 	normalizeCurrentStyle();

 	// grid: init gc
 	// [?]
 	
 	// grid: border
 	// [?]
 	
 	if (firstPage) {
 	    for (int i = 0; i < columnCount; i++) {
 		Column column = columns.get(i);
 		setXColumnWidth(i, column.getWidth());
 	    }
 	}
 	
 	int columnIndex = 0;
	int rowIndex = 0;
	int cellIndex = 0;
	
 	Row row = null;
	Cell cell = null;
	
 	for (int i = 0; i < rowCount; i++) {
 	    
 	    rowIndex = i;
 	    row = rows.get(i);
 	    
 	    // row: parent gc
 	    parentBackground = getColor(gridBackground, contextBackground);
 	    parentForeground = getColor(gridForeground, contextForeground);
 	    parentFont = getFont(gridFont, contextFont);
 		
 	    // row: load gc
 	    rowBackground = row.getBackground();
 	    rowForeground = row.getForeground();
 	    rowFont = row.getFont();

 	    // row: current gc
 	    background = rowBackground;
 	    foreground = rowForeground;
 	    font = rowFont;
 	    
 	    createXRow(absoluteRowIndex);
 	    setXRowHeight(row.getHeight());
 	    
 	    absoluteColumnIndex = 0;
 	    columnIndex = 0;
 	    cellIndex = 0;
 	    
 	    int cellWidth = 0;
 	    int cellHeight = 0;
 	    int colspan = 0;
 	    int rowspan = 0;

 	    int cellCount = row.getCellCount();
 	    List<Cell> cells = row.getCells();
 	
 	    for (int j = 0; j < cellCount; j++) {
 		
 		cellIndex = j;
 		cell = cells.get(cellIndex);
 		
 		cellWidth = 0;
 		cellHeight = 0;
 		colspan = cell.getColspan();
 		rowspan = cell.getRowspan();
 		int nextColumnIndex = columnIndex + colspan;
 		int nextRowIndex = i + rowspan;
 		
 		if (nextColumnIndex > columnCount) {
 		    // overflow columns
 		    break;
 		}
 		if (nextRowIndex > rowCount) {
 		    // overflow rows
 		    break;
 		}
 		
 		cellWidth = GridUtils.calculateCellWidth(cell, columns, columnIndex);
 		cellHeight = GridUtils.calculateCellHeight(cell, rows, i);

 		// cell: parent gc
 		parentBackground = getColor(rowBackground, gridBackground, contextBackground);
 		parentForeground = getColor(rowForeground, gridForeground, contextForeground);
 		parentFont = getFont(rowFont, gridFont, contextFont);
 		
 		// cell: load gc
 		cellBackground = cell.getBackground();
 		cellForeground = cell.getForeground();
 		cellFont = cell.getFont();
 		
 		// cell: current gc
 		background = cellBackground;
 		foreground = cellForeground;
 		font = cellFont;
 		
 		// cell: normalize current gc
 		normalizeCurrentStyle();
 		
 		Border border = layout.getCellBorder(columnIndex, rowIndex);
 		
 		/////////////////////////////////////////////////
 		
 		createXCell(columnIndex);
 		
 		setXCellStyle(cell, border);
 		//setXCellSize(cell);
 		setXCellSize(cell, absoluteRowIndex, absoluteColumnIndex, rowspan, colspan);
 		
 		setXCellValue(cell);
 		
 		/////////////////////////////////////////////////
 		columnIndex = nextColumnIndex;
 		absoluteColumnIndex = columnIndex; // ???
 		
 		//cellX += cellWidth;

 	    }
 	    
 	   absoluteRowIndex++;
 	}
 	
     }
     
    
     ////
     
     
}
