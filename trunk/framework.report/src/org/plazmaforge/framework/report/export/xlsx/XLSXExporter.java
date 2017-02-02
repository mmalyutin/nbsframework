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


import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.plazmaforge.framework.report.export.AbstractXLWorkbookExporter;

/**
 * 
 * @author ohapon
 *
 */
public class XLSXExporter extends AbstractXLWorkbookExporter {

    
    @Override
    protected void createXWorkbook() {
	xWorkbook = new XSSFWorkbook();
	
	emptyCellStyle = xWorkbook.createCellStyle();
	//emptyCellStyle.setFillForegroundColor((new XSSFColor.WHITE()).getIndex());
	//emptyCellStyle.setFillPattern(backgroundMode);
	//dataFormat = workbook.createDataFormat();
    }
    
    protected XSSFColor createXColor(byte r, byte g, byte b) {
	return new XSSFColor(new byte[] {r, g, b});
    }
    
    protected void setXFontColor(org.apache.poi.ss.usermodel.Font font, org.apache.poi.ss.usermodel.Color color) {
	((XSSFFont) font).setColor((XSSFColor) color);
    }

    protected void setXCellStyleFillForeground(org.apache.poi.ss.usermodel.CellStyle cellStyle, org.apache.poi.ss.usermodel.Color color) {
	((XSSFCellStyle) cellStyle).setFillForegroundColor((XSSFColor) color);
    }

    protected void setXCellStyleFillBackground(org.apache.poi.ss.usermodel.CellStyle cellStyle, org.apache.poi.ss.usermodel.Color color) {
	((XSSFCellStyle) cellStyle).setFillBackgroundColor((XSSFColor) color);
    }

    protected void setXCellBorderLeftColor(org.apache.poi.ss.usermodel.CellStyle cellStyle, org.apache.poi.ss.usermodel.Color color) {
	((XSSFCellStyle) cellStyle).setLeftBorderColor((XSSFColor) color);
    }
    
    protected void setXCellBorderRightColor(org.apache.poi.ss.usermodel.CellStyle cellStyle, org.apache.poi.ss.usermodel.Color color) {
	((XSSFCellStyle) cellStyle).setRightBorderColor((XSSFColor) color);
    }
    
}
