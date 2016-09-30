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
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
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

    
    
    
    protected int rowIndex;
    
    protected int columnIndex;
    
    protected int cellIndex;
    
    protected int colspan;
    
    protected int rowspan;

    ////
    
    protected abstract void createXWorkbook();

    protected abstract void writeXWorkbook(OutputStream os) throws IOException;

    protected abstract void createXSheet(String name);

    protected abstract void createXRow(int rowIndex);

    protected abstract void createXCell(int columnIndex);

    protected abstract void setXCellStyle(Cell cell);

    protected abstract void setXCellSize(Cell cell);

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
  	rowIndex = 0;
  	columnIndex = 0;
  	cellIndex = 0;
  	colspan = 1;
  	rowspan = 1;
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
 	exportPage(document.getPage(0), true);
 	exportPage(document.getPage(1), false);
 	
 	/*
 	int pageCount = document.getPageCount();
 	for (int i = 0; i < pageCount; i++) {
 	    exportPage(document.getPage(i), i == 0);
 	}
 	*/
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
 	
 	for (int i = 0; i < rowCount; i++) {
 	    
 	    //rowIndex = i;
 	    Row row = rows.get(i);
 	    
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
 	    
 	    createXRow(rowIndex);
 	    setXRowHeight(row.getHeight());
 	    
 	    columnIndex = 0;
 	    cellIndex = 0;
 	    
 	    int cellWidth = 0;
 	    int cellHeight = 0;
 	    colspan = 0;
 	    rowspan = 0;


 	    int cellCount = row.getCellCount();
 	    List<Cell> cells = row.getCells();
 	
 	    
 	    
 	    Cell cell = null;
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
 		
 		cellWidth = ExportHelper.calculateCellWidth(cell, columns, columnIndex);
 		cellHeight = ExportHelper.calculateCellHeight(cell, rows, i);

 		// cell: parent gc
 		parentBackground = getColor(rowBackground, gridBackground != null ? gridBackground : contextBackground);
 		parentForeground = getColor(rowForeground, gridForeground != null ? gridForeground : contextForeground);
 		parentFont = getFont(rowFont, gridFont != null ? gridFont: contextFont);
 		
 		// cell: load gc
 		cellBackground = cell.getBackground();
 		cellForeground = cell.getForeground();
 		cellFont = cell.getFont();
 		
 		// cell: current gc
 		background = cellBackground;
 		foreground = cellForeground;
 		font = cellFont;
 		
 		
 		/////////////////////////////////////////////////
 		
 		createXCell(columnIndex);
 		
 		setXCellStyle(cell);
 		setXCellSize(cell);
 		
 		setXCellValue(cell);
 		
 		/////////////////////////////////////////////////
 		columnIndex = nextColumnIndex;
 		//cellX += cellWidth;

 	    }
 	    
 	   rowIndex++;
 	}
 	
     }
     
    
     ////
     
    protected String getFontColorKey(Font font, Color color) {
	if (font == null && color == null) {
	    return null;
	}
	return (font == null ? "." : font.getKey()) + "|" + (color == null ? "." : color.getKey());
    }
     
}
