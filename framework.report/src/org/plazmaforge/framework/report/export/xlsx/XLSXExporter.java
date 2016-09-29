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

package org.plazmaforge.framework.report.export.xlsx;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.plazmaforge.framework.report.export.AbstractWorkbookExporter;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * 
 * @author ohapon
 *
 */
public class XLSXExporter extends AbstractWorkbookExporter {

    protected XSSFWorkbook xWorkbook;

    protected XSSFSheet xSheet;

    protected XSSFRow xRow;

    protected XSSFCell xCell;

    protected XSSFCellStyle emptyCellStyle;

    protected XSSFCellStyle cellStyle;
    
    protected Map<String, XSSFColor> colorMap;
    
    protected Map<String, XSSFFont> fontMap;
    
    
  
   
    
    @Override
    protected void initExport() {
	
	super.initExport();
	
	xWorkbook = null;
	xSheet = null;
	xRow = null;
	xCell = null;
	emptyCellStyle = null;
	cellStyle = null;
	
	colorMap = new HashMap<String, XSSFColor>();
	fontMap = new HashMap<String, XSSFFont>();
    }
   
    @Override
    protected void createXWorkbook() {
	xWorkbook = new XSSFWorkbook();
	
	XSSFCellStyle emptyCellStyle = xWorkbook.createCellStyle();
	//emptyCellStyle.setFillForegroundColor((new XSSFColor.WHITE()).getIndex());
	
	// emptyCellStyle.setFillPattern(backgroundMode);
	// dataFormat = workbook.createDataFormat();
    }
    
    @Override
    protected void writeXWorkbook(OutputStream os) throws IOException {
	xWorkbook.write(os);
    }
    
    @Override
    protected void createXSheet(String name) {
	xSheet = xWorkbook.createSheet(name);
    }
    
    @Override
    protected void createXRow(int rowIndex) {
	xRow = xSheet.createRow(rowIndex);
    }
    
    @Override
    protected void createXCell(int columnIndex){
	xCell = xRow.createCell((short) columnIndex);
    }
    
    @Override
    protected void setXCellStyle(Cell cell) {
	if (background == null && foreground == null && font == null) {
	    return;
	}
	
	cellStyle = xWorkbook.createCellStyle();

	// cell: background
	if (background != null) {
	    XSSFColor xColor = getXColor(background);
	    if (xColor != null) {
		cellStyle.setFillForegroundColor(xColor);
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    }
	}

	// cell: foreground, font
	if (foreground != null || font != null) {
	    XSSFFont xFont = getXFont(font, foreground);
	    if (xFont != null) {
		cellStyle.setFont(xFont);
	    }
	}

	xCell.setCellStyle(cellStyle);

    }
    
    @Override
    protected void setXCellSize(Cell cell) {
	if (colspan > 1 || rowspan > 1) {
	    // TODO
	    xSheet.addMergedRegion(new CellRangeAddress(rowIndex, (rowIndex + rowspan - 1), columnIndex, (columnIndex + colspan - 1)));
	}
    }
    
    @Override
    protected void setXCellValue(Cell cell) {
	Object value = cell.getValue();
	if (value != null) {
	    xCell.setCellValue(value.toString());
	}
    }
    
    @Override
    protected void setXColumnWidth(int columnIndex, int columnWidth) {
	xSheet.setColumnWidth(columnIndex, columnWidth * 43); // Math.min(43 * width, 256*255));
    }
    
    @Override
    protected void setXRowHeight(int height) {
	xRow.setHeightInPoints(height);
    }
    
 
    ////

    protected XSSFSheet createXSheet(XSSFWorkbook workbook, String name) {
	return workbook.createSheet(name);
    }

    protected XSSFColor getXColor(Color color) {
	if (color == null) {
	    return null;
	}
	XSSFColor xColor = colorMap.get(color.getKey());
	if (xColor != null) {
	    return xColor;
	}
	xColor = createXColor(color);
	if (xColor == null) {
	    return null;
	}
	colorMap.put(color.getKey(), xColor);
	return xColor;
    }
    
    protected XSSFColor createXColor(Color color) {
	if (color == null) {
	    return null;
	}
	return createXColor((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
    }
    
    protected XSSFColor createXColor(byte r, byte g, byte b) {
	return new XSSFColor(new byte[] {r, g, b});
    }
    
    protected XSSFFont getXFont(Font font, Color color) {
	if (font == null && color == null) {
	    return null;
	}
	String key = (font == null ? "." : font.getKey()) + "|" + (color == null ? "." : color.getKey()); 
	XSSFFont xFont = fontMap.get(key);
	if (xFont != null) {
	    return xFont;
	}
	xFont = createXFont(font, color);
	if (xFont == null) {
	    return xFont;
	}
	fontMap.put(key, xFont);
	return xFont;
    }
    
    protected XSSFFont createXFont(Font font, Color color) {
	if (font == null && color == null) {
	    return null;
	}
	XSSFFont xFont = xWorkbook.createFont();
	if (font != null) {
	    if (font.getName() != null) {
		xFont.setFontName(font.getName());
	    }
	    if (font.getSize() > 0) {
		xFont.setFontHeightInPoints((short) font.getSize());
	    }
	    if (font.isBold()) {
		xFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
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
	    XSSFColor xColor = getXColor(color);
	    if (xColor != null) {
		xFont.setColor(xColor);
	    }
	}
	return xFont;
    }
    
    
}
