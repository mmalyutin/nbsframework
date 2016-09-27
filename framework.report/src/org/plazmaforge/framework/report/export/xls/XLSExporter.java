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

package org.plazmaforge.framework.report.export.xls;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.export.AbstractReportExporter;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;

/**
 * 
 * @author ohapon
 *
 */
public class XLSExporter extends AbstractReportExporter {

    protected HSSFWorkbook workbook;

    protected HSSFSheet sheet;

    protected HSSFRow row;

    protected HSSFCell cell;

    protected HSSFCellStyle emptyCellStyle;

    
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
    
    protected void exportDocument(Document document, String fileName) throws RTException {
	HSSFWorkbook workbook = null;
	HSSFSheet sheet = null;
	OutputStream os = null;
	try {
	
	    workbook = createWorkbook();
	    sheet = createSheet(workbook, "XLSExport"); // TODO
	    
	    exportPages(document, sheet);
	    
	    os = new FileOutputStream(fileName);
	    workbook.write(os);
	    os.flush();
	    
	} catch (Exception ex) {
	    throw new RTException(ex);
	}
    }  
    
    protected void exportPages(Document document, HSSFSheet sheet) throws RTException {
	if (!document.hasPages()) {
	    return;
	}
	int pageCount = document.getPageCount();
	for (int i = 0; i < pageCount; i++) {
	    exportPage(document.getPage(i), sheet);
	}
    }
    
    protected void exportPage(Page page, HSSFSheet sheet) throws RTException {
	if (!page.hasChildren()) {
	    return;
	}
	List<Element> children = page.getChildren();
	int childrenCount = page.getChildrenCount();
	for (int i = 0; i < childrenCount; i++) {
	    Element child = children.get(i);
	    if (child instanceof Grid) {
		exportGrid((Grid) child, sheet);
	    }
	}
    }

    protected void exportGrid(Grid grid, HSSFSheet sheet) throws RTException {
	exportRows(grid, sheet);
    }
    
    protected void exportRows(Grid grid, HSSFSheet sheet) throws RTException {
	if (!grid.hasRows()) {
	    return;
	}
	
	int columnCount = grid.getColumnCount();
	List<Column> columns = grid.getColumns();
	
	List<Row> rows = grid.getRows();
	int rowCount = grid.getRowCount();
	
	// TODO
	//sheet.setColumnWidth((short) columnIndex, (short) (column.getDisplaySize() * 20));
	
	for (int i = 0; i < rowCount; i++) {
	    int rowIndex = i;
	    Row row = rows.get(rowIndex);
	    
	    HSSFRow  xRow = sheet.createRow(i);
	    short h = (short) row.getHeight();
	    //xRow.setHeight(h);
	    xRow.setHeightInPoints(h);
	    
	    int columnIndex = 0;
	    int cellIndex = 0;
	    
	    int cellWidth = 0;
	    int cellHeight = 0;
	    int colspan = 0;
	    int rowspan = 0;

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
		int nextRowIndex = rowIndex + rowspan;
		
		if (nextColumnIndex > columnCount) {
		    // overflow columns
		    break;
		}
		if (nextRowIndex > rowCount) {
		    // overflow rows
		    break;
		}
		
		for (int k = columnIndex; k < nextColumnIndex; k++) {
		    cellWidth += columns.get(k).getWidth();
		}
		for (int k = rowIndex; k < nextRowIndex; k++) {
		    cellHeight += rows.get(k).getHeight();
		}
		
		/////////////////////////////////////////////////
		HSSFCell xCell = xRow.createCell((short) columnIndex);
	
		
		Object value = cell.getValue();
		if (value != null) {
		    xCell.setCellValue(value.toString());
		}
		/////////////////////////////////////////////////
		columnIndex = nextColumnIndex;
		//cellX += cellWidth;

	    }
	}
	
    }
    

    
    protected HSSFWorkbook createWorkbook() {
	HSSFWorkbook workbook = new HSSFWorkbook();
	
	HSSFCellStyle emptyCellStyle = workbook.createCellStyle();
	emptyCellStyle.setFillForegroundColor((new HSSFColor.WHITE()).getIndex());
	
	// emptyCellStyle.setFillPattern(backgroundMode);
	// dataFormat = workbook.createDataFormat();
	return workbook;
    }

    protected HSSFSheet createSheet(HSSFWorkbook workbook, String name) {
	return workbook.createSheet(name);
    }

}
