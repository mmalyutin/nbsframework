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

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
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
import org.plazmaforge.framework.report.export.AbstractWorkbookExporter;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * 
 * @author ohapon
 *
 */
public class XLSExporter extends AbstractWorkbookExporter {

    protected HSSFWorkbook xWorkbook;

    protected HSSFSheet xSheet;

    protected HSSFRow xRow;

    protected HSSFCell xCell;

    protected HSSFCellStyle emptyCellStyle;

    protected HSSFCellStyle cellStyle;
    
    protected Map<String, HSSFColor> colorMap;
    
    protected Map<String, HSSFFont> fontMap;
    
    
  
   
    
    @Override
    protected void initExport() {
	
	super.initExport();
	
	xWorkbook = null;
	xSheet = null;
	xRow = null;
	xCell = null;
	emptyCellStyle = null;
	cellStyle = null;
	
	colorMap = new HashMap<String, HSSFColor>();
	fontMap = new HashMap<String, HSSFFont>();
    }
   
    @Override
    protected void createXWorkbook() {
	xWorkbook = new HSSFWorkbook();
	
	HSSFCellStyle emptyCellStyle = xWorkbook.createCellStyle();
	emptyCellStyle.setFillForegroundColor((new HSSFColor.WHITE()).getIndex());
	
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
	    HSSFColor xColor = getXColor(background);
	    if (xColor != null) {
		cellStyle.setFillForegroundColor(xColor.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    }
	}

	// cell: foreground, font
	if (foreground != null || font != null) {
	    HSSFFont xFont = getXFont(font, foreground);
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

    protected HSSFSheet createXSheet(HSSFWorkbook workbook, String name) {
	return workbook.createSheet(name);
    }

    protected HSSFColor getXColor(Color color) {
	if (color == null) {
	    return null;
	}
	HSSFColor xColor = colorMap.get(color.getKey());
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
    
    protected HSSFColor createXColor(Color color) {
	if (color == null) {
	    return null;
	}
	return createXColor((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
    }
    
    protected HSSFColor createXColor(byte r, byte g, byte b) {
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
    
    protected HSSFFont getXFont(Font font, Color color) {
	String key = getFontColorKey(font, color);
	if (key == null) {
	    return null;
	}
	HSSFFont xFont = fontMap.get(key);
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
    
    protected HSSFFont createXFont(Font font, Color color) {
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
	    HSSFColor xColor = getXColor(color);
	    if (xColor != null) {
		xFont.setColor(xColor.getIndex());
	    }
	}
	return xFont;
    }
    
    
}
