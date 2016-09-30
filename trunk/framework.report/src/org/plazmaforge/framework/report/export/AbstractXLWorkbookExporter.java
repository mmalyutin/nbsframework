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

package org.plazmaforge.framework.report.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.util.CellRangeAddress;
import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;



/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractXLWorkbookExporter extends AbstractWorkbookExporter {

    
    public static final int CELL_TYPE_NUMERIC = org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
    public static final int CELL_TYPE_STRING = org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;
    public static final int CELL_TYPE_FORMULA = org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA;
    public static final int CELL_TYPE_BLANK = org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;
    public static final int CELL_TYPE_BOOLEAN = org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN;
    public static final int CELL_TYPE_ERROR = org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR;
    
    
    protected org.apache.poi.ss.usermodel.Workbook xWorkbook;

    protected org.apache.poi.ss.usermodel.Sheet xSheet;

    protected org.apache.poi.ss.usermodel.Row xRow;

    protected org.apache.poi.ss.usermodel.Cell xCell;
    
    protected org.apache.poi.ss.usermodel.CellStyle emptyCellStyle;

    protected org.apache.poi.ss.usermodel.CellStyle cellStyle;
    
    protected Map<String, org.apache.poi.ss.usermodel.Color> colorMap;
    
    protected Map<String, org.apache.poi.ss.usermodel.Font> fontMap;
    
    
    @Override
    protected void initExport() {
	
	super.initExport();
	
	xWorkbook = null;
	xSheet = null;
	xRow = null;
	xCell = null;
	emptyCellStyle = null;
	cellStyle = null;
	
	colorMap = new HashMap<String, org.apache.poi.ss.usermodel.Color>();
	fontMap = new HashMap<String, org.apache.poi.ss.usermodel.Font>();
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
    protected void setXCellSize(Cell cell) {
	if (colspan > 1 || rowspan > 1) {
	    xSheet.addMergedRegion(new CellRangeAddress(rowIndex, (rowIndex + rowspan - 1), columnIndex, (columnIndex + colspan - 1)));
	}
    }

    @Override
    protected void setXCellValue(Cell cell) {
	int cellType = getCellType(cell);
	if (cellType >= 0) {
	    xCell.setCellType(cellType);
	}
	Object value = cell.getValue();
	if (value == null) {
	    return;
	}
	boolean error = false;
	if (cellType == CELL_TYPE_NUMERIC) {
	    if (value instanceof Number) {
		xCell.setCellValue(((Number) value).doubleValue());
	    } else {
		error = true;
	    }
	} else if (cellType == CELL_TYPE_STRING) {
	    xCell.setCellValue(value.toString());
	} else if (cellType == CELL_TYPE_BOOLEAN) {
	    if (value instanceof Boolean) {
		xCell.setCellValue(((Boolean) value));
	    } else {
		error = true;
	    }
	}
	if (error) {
	    xCell.setCellType(CELL_TYPE_ERROR);
	}
	
    }

    protected int getCellType(Cell cell) {
	String dataType = cell.getDataType();
	if (dataType == null){
	    return -1;
	}
	if (TypeUtils.isLikeNumberType(dataType)) {
	    return CELL_TYPE_NUMERIC;
	} else if (TypeUtils.isStringType(dataType)) {
	    return CELL_TYPE_STRING;
	} else if (TypeUtils.isBooleanType(dataType)) {
	    return CELL_TYPE_BOOLEAN;
	}
	//else if (TypeUtils.isLikeCalendarType(dataType)) {
	//    return CELL_TYPE_NUMERIC;
	//}
	return -1;
    }
    
    @Override
    protected void setXColumnWidth(int columnIndex, int columnWidth) {
	xSheet.setColumnWidth(columnIndex, columnWidth * 43); // Math.min(43 * width, 256*255));
    }

    @Override
    protected void setXRowHeight(int height) {
	xRow.setHeightInPoints(height);
    }       
    
    @Override
    protected void setXCellStyle(Cell cell) {
	if (cell == null) {
	    return;
	}
	String format = normalizeString(cell.getFormat());
	if (background == null && foreground == null && font == null && format == null) {
	    return;
	}
	
	cellStyle = xWorkbook.createCellStyle();

	// cell: background
	if (background != null) {
	    org.apache.poi.ss.usermodel.Color xColor = getXColor(background);
	    if (xColor != null) {
		setXCellStyleFillForeground(cellStyle, xColor);
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    }
	}

	// cell: foreground, font
	if (foreground != null || font != null) {
	    org.apache.poi.ss.usermodel.Font xFont = getXFont(font, foreground);
	    if (xFont != null) {
		cellStyle.setFont(xFont);
	    }
	}

	if (format != null) {
	    DataFormat df = xWorkbook.createDataFormat();
	    short f = df.getFormat(format);
	    cellStyle.setDataFormat(f);
	}

	xCell.setCellStyle(cellStyle);
    }
    
    ////
    
    protected org.apache.poi.ss.usermodel.Color getXColor(Color color) {
	if (color == null) {
	    return null;
	}
	org.apache.poi.ss.usermodel.Color xColor = colorMap.get(color.getKey());
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

    protected org.apache.poi.ss.usermodel.Color createXColor(Color color) {
	if (color == null) {
	    return null;
	}
	return createXColor((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
    }
      
    protected org.apache.poi.ss.usermodel.Font getXFont(Font font, Color color) {
	String key = getFontColorKey(font, color);
	if (key == null) {
	    return null;
	}
	org.apache.poi.ss.usermodel.Font xFont = fontMap.get(key);
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

    protected org.apache.poi.ss.usermodel.Font createXFont(Font font, Color color) {
	if (font == null && color == null) {
	    return null;
	}
	org.apache.poi.ss.usermodel.Font xFont = xWorkbook.createFont();
	if (font != null) {
	    if (font.getName() != null) {
		xFont.setFontName(font.getName());
	    }
	    if (font.getSize() > 0) {
		xFont.setFontHeightInPoints((short) font.getSize());
	    }
	    if (font.isBold()) {
		xFont.setBoldweight(org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD);
	    }
	    if (font.isItalic()) {
		xFont.setItalic(true);
	    }
	    /*
	     * if (font.isUnderline()) {
	     * cellFont.setUnderline(HSSFFont.U_SINGLE); } if
	     * (font.isStrikeThrough()) { cellFont.setStrikeout(true); }
	     */
	}
	if (color != null) {
	    org.apache.poi.ss.usermodel.Color xColor = getXColor(color);
	    if (xColor != null) {
		setXFontColor(xFont, xColor);
	    }
	}
	return xFont;
    }
           
    ////      
    
    protected abstract org.apache.poi.ss.usermodel.Color createXColor(byte r, byte g, byte b);
    
    protected abstract void setXFontColor(org.apache.poi.ss.usermodel.Font font, org.apache.poi.ss.usermodel.Color color);

    protected abstract void setXCellStyleFillForeground(org.apache.poi.ss.usermodel.CellStyle cellStyle, org.apache.poi.ss.usermodel.Color color);

    protected abstract void setXCellStyleFillBackground(org.apache.poi.ss.usermodel.CellStyle cellStyle, org.apache.poi.ss.usermodel.Color color);
    
}
