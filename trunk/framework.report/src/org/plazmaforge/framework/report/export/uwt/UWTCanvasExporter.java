/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.report.export.uwt;

import java.util.List;

import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.export.AbstractReportExporter;
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
import org.plazmaforge.framework.uwt.graphics.GC;

/**
 * @author ohapon
 *
 */
public class UWTCanvasExporter extends AbstractReportExporter {


    
    protected int offsetX;
    
    protected int offsetY;

    
    // Current parent: NOT NULL 
    protected Color parentBackground;
    protected Color parentForeground;
    protected Font parentFont;
	
    // Current: NOT NULL
    protected Color background;
    protected Color foreground;
    protected Font font;
    
    @Override
    public void exportDocument(Document document) throws RTException {
	if (document == null) {
	    return;
	}
	GC gc = getGC();
	if (gc == null) {
	    throw new RTException("Can't export document to format 'UWTCanvas'. GC is null.");
	}
	init();
	paintDocument(gc, document);
    }

    @Override
    public void exportPage(Page page) throws RTException {
	if (page == null) {
	    return;
	}
	GC gc = getGC();
	if (gc == null) {
	    throw new RTException("Can't export page to format 'UWTCanvas'. GC is null.");
	}
	init();
	paintPage(gc, page);
    }

    protected GC getGC() {
	return (GC) getData("gc");
    }
    
    protected void init() {
	Integer offset = getIntegerValue("offsetX");
	if (offset != null) {
	    offsetX = offset;
	}
	offset = getIntegerValue("offsetY");
	if (offset != null) {
	    offsetY = offset;
	}
    }
    
    protected Integer getIntegerValue(String name) {
	Object value = getData(name);
	return (value instanceof Integer) ? (Integer) value : null;  
    }
    
    protected void paintDocument(GC gc, Document document) {
	if (document == null || !document.hasPages()) {
	    return;
	}
	paintPage(gc, document.getPages().get(0));
    }

    protected void paintPage(GC gc, Page page) {
	if (page == null) {
	    return;
	}

	if (!page.hasChildren()) {
	    return;
	}

	// shift offsets by margin
	offsetX = offsetX + page.getMargin().getLeft();
	offsetY = offsetY + page.getMargin().getTop();
	
	List<Element> children = page.getChildren();

	for (Element element : children) {
	    if (element instanceof Grid) {
		paintGrid(gc, (Grid) element);
	    }
	}
    }

    protected Color getColor(Color color, Color parentColor) {
	return color == null ? parentColor : color;
    }

    protected Font getFont(Font font, Font parentFont) {
	return font == null ? parentFont : font;
    }
    
    protected void normalizeCurrentStyle() {
	background = getColor(background, parentBackground);
	foreground = getColor(foreground, parentForeground);
	font = getFont(font, parentFont);
    }
    
    protected void setCurrentStyle(GC gc) {
	gc.setBackground(background);
	gc.setForeground(foreground);
	gc.setFont(font);
    }

    protected void paintGrid(GC gc, Grid grid) {
	if (grid.getRowCount() == 0) {
	    return;
	}

	int columnCount = grid.getColumnCount();
	int rowCount = grid.getRowCount();
	
	List<Column> columns = grid.getColumns();
	int width = ExportHelper.calculateWidth(columns);

	List<Row> rows = grid.getRows();
	int height = ExportHelper.calculateHeight(rows);

	Color contextBackground = gc.getBackground();
	Color contextForeground = gc.getForeground();
	Font contextFont = gc.getFont();

	// Current parent: NOT NULL
	parentBackground = null;
	parentForeground = null;
	parentFont = null;
	
	// Current: NOT NULL
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
	fillBackground(gc, offsetX, offsetY, width, height, background);

	// grid: normalize current gc
	normalizeCurrentStyle();

	// grid: init gc
	setCurrentStyle(gc);
	
	// grid: border
	gc.drawRectangle(offsetX, offsetY, width, height);


	for (Row row : rows) {
	    
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
		
	    // row: background
	    fillBackground(gc, offsetX, offsetY, width, row.getHeight(), background);
	    
	    // row: normalize current gc
	    normalizeCurrentStyle();
	    
	    // row: init gc
	    setCurrentStyle(gc);
	    
	    List<Cell> cells = row.getCells();

	    int columnIndex = 0;
	    int rowIndex = 0;
	    
	    int cellX = offsetX;
	    int cellY = offsetY;
	    int cellWidth = 0;
	    int cellHeight = 0;
	    int colspan = 0;
	    int rowspan = 0;
	    
	    int paddingLeft = 0;
	    int paddingTop = 0;
	    
	    
	    for (Cell cell : cells) {
		
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
		
		// cell: background
		fillBackground(gc, cellX, cellY, cellWidth, cellHeight, background);
		
		// cell: normalize current gc
		normalizeCurrentStyle();
		
		// cell: init gc
		setCurrentStyle(gc);
		
		Object value = cell.getValue();
		if (value != null) {
		    paddingLeft = 0;
		    paddingTop = 0;
		    if (cell.hasPadding()) {
			paddingLeft = cell.getPadding().getLeft();
			paddingTop = cell.getPadding().getTop();
		    }
		    
		    String text = value.toString();
		    drawText(gc, text, cellX + paddingLeft, cellY + paddingTop, -1, -1, font, foreground);
		}
		
		columnIndex = nextColumnIndex;
		cellX += cellWidth;
		
	    }
	    

	    rowIndex++;
	    offsetY += row.getHeight();
	    
	    
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
	    
	    // row: fix current gc
	    normalizeCurrentStyle();
	    
	    // row: init gc
	    setCurrentStyle(gc);
	    
	    //TODO
	    // row: bottom border
	    gc.drawLine(offsetX, offsetY - 1, offsetX + width, offsetY - 1);

	}
	
	//gc.drawRectangle(offsetX, offsetY, width, height);
    }

    protected void fillBackground(GC gc, int x, int y, int width, int height, Color background) {
	if (background == null) {
	    return;
	}
	gc.setBackground(background);
	gc.fillRectangle(x, y, width, height);
    }
    
    
    protected void drawText(GC gc, String text, int x, int y, int width, int height, Font font, Color foreground) {
	if (text == null) {
	    return;
	}
	if (font != null) {
	    gc.setFont(font);
	}
	if (foreground != null) {
	    gc.setForeground(foreground);
	}
	gc.drawText(text, x, y); // TODO
    }
}
