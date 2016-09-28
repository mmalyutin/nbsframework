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
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.export.AbstractBaseReportExporter;
import org.plazmaforge.framework.report.export.ExportHelper;
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
 * 
 * @author ohapon
 *
 */
public class XLSExporter extends AbstractBaseReportExporter {

    protected HSSFWorkbook xWorkbook;

    protected HSSFSheet xSheet;

    protected HSSFRow xRow;

    protected HSSFCell xCell;

    protected HSSFCellStyle emptyCellStyle;

    protected HSSFCellStyle cellStyle;
    
    protected Map<String, HSSFColor> colorMap;
    
    protected Map<String, HSSFFont> fontMap;
    
    
    
    protected int rowIndex;
    
    protected int columnIndex;
    
    protected int cellIndex;
    
    protected int colspan;
    
    protected int rowspan;

    
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
	
	rowIndex = -1;
	columnIndex = -1;
	cellIndex = -1;
	colspan = 1;
	rowspan = 1;
	    
	xWorkbook = null;
	xSheet = null;
	xRow = null;
	xCell = null;
	emptyCellStyle = null;
	cellStyle = null;
	
	colorMap = new HashMap<String, HSSFColor>();
	fontMap = new HashMap<String, HSSFFont>();
    }
    
    protected void createXWorkbook() {
	xWorkbook = createWorkbook();
    }
    
    protected void writeXWorkbook(OutputStream os) throws IOException {
	xWorkbook.write(os);
    }
    
    protected void createXSheet(String name) {
	xSheet = createSheet(xWorkbook, name);
    }
    
    protected void createXRow(int rowIndex) {
	xRow = xSheet.createRow(rowIndex);
    }
    
    protected void createXCell(int columnIndex){
	xCell = xRow.createCell((short) columnIndex);
    }
    
    protected void setXCellStyle(Cell cell) {
	if (background == null && foreground == null && font == null) {
	    return;
	}
	
	cellStyle = xWorkbook.createCellStyle();

	// cell: background
	if (background != null) {
	    HSSFColor xColor = getColor(background);
	    if (xColor != null) {
		cellStyle.setFillForegroundColor(xColor.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    }
	}

	// cell: foreground, font
	if (foreground != null || font != null) {
	    HSSFFont xFont = getFont(font, foreground);
	    if (xFont != null) {
		cellStyle.setFont(xFont);
	    }
	}

	xCell.setCellStyle(cellStyle);

    }
    
    protected void setXCellSize(Cell cell) {
	if (colspan > 1 || rowspan > 1) {
	    // TODO
	    xSheet.addMergedRegion(new CellRangeAddress(rowIndex, (rowIndex + rowspan - 1), columnIndex, (columnIndex + colspan - 1)));
	}
    }
    
    protected void setXCellValue(Cell cell) {
	Object value = cell.getValue();
	if (value != null) {
	    xCell.setCellValue(value.toString());
	}
    }
    
    protected void setColumnWidth(int columnIndex, int columnWidth) {
	xSheet.setColumnWidth(columnIndex, columnWidth * 43); // Math.min(43 * width, 256*255));
    }
    
    protected void setRowHeight(int height) {
	xRow.setHeightInPoints(height);
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
		setColumnWidth(i, column.getWidth());
	    }
	}
	
	for (int i = 0; i < rowCount; i++) {
	    
	    rowIndex = i;
	    Row row = rows.get(rowIndex);
	    
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
	    setRowHeight(row.getHeight());
	    
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
		int nextRowIndex = rowIndex + rowspan;
		
		if (nextColumnIndex > columnCount) {
		    // overflow columns
		    break;
		}
		if (nextRowIndex > rowCount) {
		    // overflow rows
		    break;
		}
		
		cellWidth = ExportHelper.calculateCellWidth(cell, columns, columnIndex);
		cellHeight = ExportHelper.calculateCellHeight(cell, rows, rowIndex);

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

    protected HSSFColor getColor(Color color) {
	if (color == null) {
	    return null;
	}
	HSSFColor xColor = colorMap.get(color.getKey());
	if (xColor != null) {
	    return xColor;
	}
	xColor = createColor(color);
	if (xColor == null) {
	    return null;
	}
	colorMap.put(color.getKey(), xColor);
	return xColor;
    }
    
    protected HSSFColor createColor(Color color) {
	if (color == null) {
	    return null;
	}
	return createColor((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
    }
    
    protected HSSFColor createColor(byte r, byte g, byte b) {
	HSSFPalette palette = xWorkbook.getCustomPalette();
	HSSFColor hssfColor = null;
	try {
	    hssfColor = palette.findColor(r, g, b);
	    if (hssfColor == null) {
		palette.setColorAtIndex(HSSFColor.LAVENDER.index, r, g, b);
		hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
	    }
	} catch (Exception e) {
	    //logger.error(e);
	}

	return hssfColor;
    }
    
    protected HSSFFont getFont(Font font, Color color) {
	if (font == null && color == null) {
	    return null;
	}
	String key = (font == null ? "." : font.getKey()) + "|" + (color == null ? "." : color.getKey()); 
	HSSFFont xFont = fontMap.get(key);
	if (xFont != null) {
	    return xFont;
	}
	xFont = createFont(font, color);
	if (xFont == null) {
	    return xFont;
	}
	fontMap.put(key, xFont);
	return xFont;
    }
    
    protected HSSFFont createFont(Font font, Color color) {
	if (font == null && color == null) {
	    return null;
	}
	HSSFFont xFont = xWorkbook.createFont();
	if (font != null) {
	    if (font.getName() != null) {
		xFont.setFontName(font.getName());
	    }
	    if (font.getSize() > 0) {
		xFont.setFontHeightInPoints((short) font.getSize());
	    }
	    if (font.isBold()) {
		xFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    }
	    if (font.isItalic()) {
		xFont.setItalic(true);
	    }
	    /*
	    if (font.isUnderline()) {
		cellFont.setUnderline(HSSFFont.U_SINGLE);
	    }
	    if (font.isStrikeThrough()) {
		cellFont.setStrikeout(true);
	    }
	    */
	}
	if (color != null) {
	    HSSFColor xColor = getColor(color);
	    if (xColor != null) {
		xFont.setColor(xColor.getIndex());
	    }
	}
	return xFont;
    }
    
    
}
