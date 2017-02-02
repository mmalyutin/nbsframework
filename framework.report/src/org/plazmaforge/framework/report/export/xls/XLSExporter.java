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


import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.plazmaforge.framework.report.export.AbstractXLWorkbookExporter;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.grid.Cell;

/**
 * 
 * @author ohapon
 *
 */
public class XLSExporter extends AbstractXLWorkbookExporter {

    
    @Override
    protected void createXWorkbook() {
	xWorkbook = new HSSFWorkbook();
	
	emptyCellStyle = xWorkbook.createCellStyle();
	//emptyCellStyle.setFillForegroundColor((new HSSFColor.WHITE()).getIndex());
	//emptyCellStyle.setFillPattern(backgroundMode);
	//dataFormat = workbook.createDataFormat();
    }
 
    protected org.apache.poi.ss.usermodel.Color createXColor(byte r, byte g, byte b) {
	HSSFPalette palette = ((HSSFWorkbook) xWorkbook).getCustomPalette();
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
    
    protected void setXFontColor(org.apache.poi.ss.usermodel.Font font, org.apache.poi.ss.usermodel.Color color) {
	font.setColor(((HSSFColor) color).getIndex());
    }

    protected void setXCellStyleFillForeground(org.apache.poi.ss.usermodel.CellStyle cellStyle, org.apache.poi.ss.usermodel.Color color) {
	cellStyle.setFillForegroundColor(((HSSFColor) color).getIndex());
    }

    protected void setXCellStyleFillBackground(org.apache.poi.ss.usermodel.CellStyle cellStyle, org.apache.poi.ss.usermodel.Color color) {
	cellStyle.setFillBackgroundColor(((HSSFColor) color).getIndex());
    }

    protected void setXCellBorderLeftColor(org.apache.poi.ss.usermodel.CellStyle cellStyle, org.apache.poi.ss.usermodel.Color color) {
	cellStyle.setLeftBorderColor(((HSSFColor) color).getIndex());
    }

    protected void setXCellBorderRightColor(org.apache.poi.ss.usermodel.CellStyle cellStyle, org.apache.poi.ss.usermodel.Color color) {
	cellStyle.setRightBorderColor(((HSSFColor) color).getIndex());
    }
    
}
