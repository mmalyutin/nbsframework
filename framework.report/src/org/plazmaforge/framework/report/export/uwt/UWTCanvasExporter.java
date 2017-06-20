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
import org.plazmaforge.framework.report.export.AbstractBaseExporter;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.BorderRegion;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.GridUtils;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.GridLayout;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;



/**
 * @author ohapon
 *
 */
public class UWTCanvasExporter extends AbstractBaseExporter {


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
//	offsetX = offsetX + page.getMargin().getLeft();
//	offsetY = offsetY + page.getMargin().getTop();
	
  	int marginLeft = 0;
  	int marginTop = 0;
  	
  	//PageSetup pageSetup = page.getPageSetup(); 
  	//if (pageSetup != null) {
  	//    marginLeft = pageSetup.getMargin().getLeft();
  	//    marginTop = pageSetup.getMargin().getTop();
  	//}
  	
  	if (page.hasMargin()) {
  	    marginLeft = page.getMargin().getLeft();
  	    marginTop = page.getMargin().getTop();
  	}
  	
  	// shift offsets by margin
	offsetX = offsetX + marginLeft;
	offsetY = offsetY + marginTop;
  	
	
	List<Element> children = page.getChildren();

	for (Element element : children) {
	    if (element instanceof Grid) {
		paintGrid(gc, (Grid) element);
	    }
	}
    }

    protected void setCurrentStyle(GC gc) {
	gc.setBackground(background);
	gc.setForeground(foreground);
	gc.setFont(font);
    }

    protected void paintGrid(GC gc, Grid grid) {
	if (!grid.hasRows()) {
	    return;
	}

	boolean isOuterBorder = true; 
	
	int columnCount = grid.getColumnCount();
	int rowCount = grid.getRowCount();
	
	List<Column> columns = grid.getColumns();
	List<Row> rows = grid.getRows();
	
	GridLayout layout = GridUtils.getGridLayout(grid);
	
	// Get grid size without grid border
	int gridWidth = layout.getAreaWidth();
	int gridHeight = layout.getAreaHeight();

	int gridOffsetX = offsetX;
	int gridOffsetY = offsetY;
	
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
	fillBackground(gc, gridOffsetX, gridOffsetY, gridWidth, gridHeight, background);

	// grid: normalize current gc
	normalizeCurrentStyle();

	// grid: init gc
	setCurrentStyle(gc);
	
	// grid: border
	//gc.drawRectangle(gridOffsetX, gridOffsetY, gridWidth, gridHeight);


	int columnIndex = 0;
	int rowIndex = 0;
	int cellIndex = 0;
	
	Row row = null;
	Cell cell = null;
	
	int rowOffsetX = 0;
	int rowOffsetY = 0;
	int rowWidth = 0;
	int rowHeight = 0;
		
	for (int i = 0; i < rowCount; i++) {
	    
	    row = rows.get(i);
	    
	    rowOffsetX = gridOffsetX;
	    rowOffsetY = offsetY;
	    rowWidth = gridWidth;
	    rowHeight = row.getHeight();
	    
	    float rowBorderTop = 0f;
	    float rowBorderBottom = 0f;
	    BorderRegion rowBorder = layout.getRowBorder(i);
	    if (rowBorder != null) {
		rowBorderTop = rowBorder.getPrevWidth();
		rowBorderBottom = rowBorder.getNextWidth();
	    }
	    
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
	    fillBackground(gc, rowOffsetX, rowOffsetY, rowWidth, rowHeight, background);
	    
	    // row: normalize current gc
	    normalizeCurrentStyle();
	    
	    // row: init gc
	    setCurrentStyle(gc);
	    
	    columnIndex = 0;
	    //rowIndex = 0;
	    
	    int cellX = rowOffsetX;
	    int cellY = rowOffsetY;
	    int cellWidth = 0;
	    int cellHeight = 0;
	    int colspan = 0;
	    int rowspan = 0;
	    
	    int paddingLeft = 0;
	    int paddingTop = 0;
	    int paddingRight = 0;
	    int paddingBottom = 0;
	    
	    int cellCount = row.getCellCount();
 	    List<Cell> cells = row.getCells();
	    
 	    // shift cellY (row-border-top)
 	    cellY += rowBorderTop;
 	   
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
		
		cellWidth = GridUtils.calculateCellWidth(layout, cell, columns, columnIndex);
		cellHeight = GridUtils.calculateCellHeight(layout, cell, rows, rowIndex);
		
		float columnBorderLeft = 0f;
		float columnBorderRight = 0f;
		
		BorderRegion columnBorder1 = layout.getColumnBorder(columnIndex);
		if (columnBorder1 != null) {
		    columnBorderLeft = columnBorder1.getPrevWidth();
		}
		BorderRegion columnBorder2 = layout.getColumnBorder(nextColumnIndex - 1);
		if (columnBorder2 != null) {
		    columnBorderRight = columnBorder2.getNextWidth();
		}

		// shift cellX (column-border-left)
		cellX += columnBorderLeft;		  
		
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
		
		int fillCellX = 0;
		int fillCellY = 0;
		int fillCellWidth = 0;
		int fillCellHeight = 0;

		
		if (isOuterBorder) {
		    // Inner cell = Outer border
		    fillCellX = cellX;
		    fillCellY = cellY;
		    fillCellWidth = cellWidth;
		    fillCellHeight = cellHeight;
		    
		    
		} else {
		    // Outer cell = Inner border
		    fillCellX = cellX - toIntF(columnBorderLeft);
		    fillCellY = cellY - toIntF(rowBorderTop);
		    fillCellWidth = cellWidth  + toIntF(columnBorderLeft + columnBorderRight);
		    fillCellHeight = cellHeight + toIntF(rowBorderTop + rowBorderBottom);
		}

		// cell: background
		fillBackground(gc, fillCellX, fillCellY, fillCellWidth, fillCellHeight, background);
		
		// cell: normalize current gc
		normalizeCurrentStyle();
		
		Border border = layout.getCellBorder(columnIndex, rowIndex);
		
		drawBorder(gc, border, fillCellX, fillCellY, fillCellWidth, fillCellHeight, isOuterBorder);
		
		// cell: init gc
		setCurrentStyle(gc);
		
		// cell: padding
		paddingLeft = 0;
		paddingTop = 0;
		paddingRight = 0;
		paddingBottom = 0;

		if (cell.hasPadding()) {
		    paddingLeft = cell.getPadding().getLeft();
		    paddingTop = cell.getPadding().getTop();
		    paddingRight = cell.getPadding().getRight();
		    paddingBottom = cell.getPadding().getBottom();
		}

		
		int clientCellX = cellX;
		int clientCellY = cellY;
		int clientCellWidth = cellWidth;
		int clientCellHeight = cellHeight;
		
		if (!isOuterBorder && border != null && !border.isEmpty()) {
		    // Outer cell = Inner border
		    
		    int borderLeft = toIntF(getLineWidth(border.hasLeft() ? border.getLeft() : null));
		    int borderRight = toIntF(getLineWidth(border.hasRight() ? border.getRight() : null));
		    int borderTop = toIntF(getLineWidth(border.hasTop() ? border.getTop() : null));
		    int borderBottom = toIntF(getLineWidth(border.hasBottom() ? border.getBottom() : null));
		    
		    clientCellX += borderLeft;
		    clientCellY += borderTop;
		    clientCellWidth -= (borderLeft + borderRight);
		    clientCellHeight -= (borderTop + borderBottom);
		}
		
		// cell: area
		int areaX = clientCellX + paddingLeft;
		int areaY = clientCellY + paddingTop;
		int areaWidth = clientCellWidth - paddingLeft - paddingRight;
		int areaHeight = clientCellHeight - paddingTop - paddingBottom;
		
		// cell: paint
		if (areaWidth > 0 && areaHeight > 0) {
		    Object value = cell.getValue();
		    if (value != null) {
			String text = formatCellValue(cell);
			drawText(gc, text, areaX, areaY, areaWidth, areaHeight, font, foreground, cell.getHorizontalAlign(), cell.getVerticalAlign());
		    }
		}
		
		columnIndex = nextColumnIndex;
		cellX += cellWidth;
		//cellX += columnBorderLeft;
		cellX += columnBorderRight;
		
	    }
	    

	    rowIndex++;
	    offsetY += row.getHeight();
	    offsetY += rowBorderTop;
	    offsetY += rowBorderBottom;
	    
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
	    //gc.drawLine(rowOffsetX, rowOffsetY - 1, rowOffsetX + gridWidth, rowOffsetY - 1);

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
    
    protected void drawText(GC gc, String text, int x, int y, int width, int height, Font font, Color foreground, HorizontalAlign horizontalAlign, VerticalAlign verticalAlign) {
	if (text == null) {
	    return;
	}
	if (font != null) {
	    gc.setFont(font);
	}
	if (foreground != null) {
	    gc.setForeground(foreground);
	}
	//gc.drawText(text, x, y);
	gc.drawTextBox(text, x, y, width, height, horizontalAlign, verticalAlign);
    }
    
    protected void drawBorder(GC gc, Border border, int x, int y, int width, int height, boolean outerBorder) {
	if (border == null || border.isEmpty()) {
	    return;
	}

	int w = 0;
	int rx = 0;
	int ry = 0;
	
	int rwidth = 0;
	int rheight = 0;

	Pen pen = null;
	Color color = null;

	Pen leftPen = null;
	Pen rightPen = null;
	Pen topPen = null;
	Pen bottomPen = null;
	
	int leftW = 0;
	int rightW = 0;
	int topW = 0;
	int bottomW = 0;
	
	// =====
	// |   |
	// |   |
	// =====
	
	
	////////////////////////////////////////////////////////////////////////////////
	//
	// Preprocessing
	//
	////////////////////////////////////////////////////////////////////////////////
	
	// Left
	if (border.hasLeft() && !border.getLeft().isEmpty()) {
	    leftPen = border.getLeft();
	    leftW = toIntF(getLineWidth(leftPen));
	}
	
	// Right
	if (border.hasRight() && !border.getRight().isEmpty()) {
	    rightPen = border.getRight();
	    rightW = toIntF(getLineWidth(rightPen));
	}

	// Top
	if (border.hasTop() && !border.getTop().isEmpty()) {
	    topPen = border.getTop();
	    topW = toIntF(getLineWidth(topPen));
	}

	// Bottom
	if (border.hasBottom() && !border.getBottom().isEmpty()) {
	    bottomPen = border.getBottom();
	    bottomW = toIntF(getLineWidth(bottomPen));
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	//
	// Drawing
	//
	////////////////////////////////////////////////////////////////////////////////
	
	
	// Left
	if (leftPen != null) {
	    
	    pen = leftPen;
	    w = leftW;
	    
	    if (outerBorder) {
		rx = x - w;
		ry = y;
		rheight = height;
	    } else {
		rx = x;
		ry = y + topW;
		rheight = height - topW - bottomW;
	    }
	    
	    rwidth = w;
	    
	    color = getLineColor(pen);
	    gc.setBackground(color);
	    gc.fillRectangle(rx, ry, rwidth, rheight);
 
	}

	// Right
	if (rightPen != null) {
	    
	    pen = rightPen;
	    w = rightW;
	    
	    if (outerBorder) {
		rx = x + width;
		ry = y;
		rheight = height;
	    } else {
		rx = x + width - w;
		ry = y + topW;
		rheight = height - topW - bottomW;
	    }
	    
	    rwidth = w;

	    color = getLineColor(pen);
	    gc.setBackground(color);
	    gc.fillRectangle(rx, ry, rwidth, rheight);
	}
	
	// Top
	if (topPen != null) {
	    
	    pen = topPen;
	    w = topW;
	    
	    if (outerBorder) {
		rx = x - leftW;
		ry = y - w;
		rwidth = width + leftW + rightW;
	    } else {
		rx = x;
		ry = y;
		rwidth = width;
	    }
	    
	    rheight = w;

	    color = getLineColor(pen);
	    gc.setBackground(color);
	    gc.fillRectangle(rx, ry, rwidth, rheight);
	}

	// Bottom
	if (bottomPen != null) {
	    
	    pen = bottomPen;
	    w = bottomW;
	    
	    if (outerBorder) {
		rx = x - leftW;
		ry = y + height;
		rwidth = width + leftW + rightW;
	    } else {
		rx = x;
		ry = y + height - w;
		rwidth = width;
	    }
	    
	    rheight = w;

	    color = getLineColor(pen);
	    gc.setBackground(color);
	    gc.fillRectangle(rx, ry, rwidth, rheight);
	}
	
	
    }

 
}
