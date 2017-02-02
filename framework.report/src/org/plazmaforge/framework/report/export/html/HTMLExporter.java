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

package org.plazmaforge.framework.report.export.html;

import java.io.IOException;
import java.util.List;

import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.BorderRegion;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.Insets;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.GridLayout;
import org.plazmaforge.framework.report.model.base.grid.GridUtils;
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
public class HTMLExporter extends AbstractHTMLExporter {

    
    private static String TAG_DIV = "div";
    
    private static String TAG_TABLE = "table";
    
    private static String TAG_TR = "tr";
    
    private static String TAG_TH = "th";
    
    private static String TAG_TD = "td";


    
    protected int oldOffsetX;
    
    protected int oldOffsetY;
    
    protected int pageOffsetX;
    
    protected int pageOffsetY;
    
    private boolean tableAsDiv = false;  
    
    protected boolean isTableAsDiv() {
        return tableAsDiv;
    }

    protected void setTableAsDiv(boolean tableAsDiv) {
        this.tableAsDiv = tableAsDiv;
    }
    
    
    @Override
    public void exportDocument(Document document) throws RTException {
	try {
	    ensureOutput();
	    checkOutput();

	    writeHeader();
	    writeDocument(document);
	    writeFooter();

	    flushOutput();
	    closeOutput();
	    
	} catch (IOException ex) {
	    throw new RTException(ex);
	}
    }

    @Override
    public void exportPage(Page page) throws RTException {
	// TODO Auto-generated method stub
    }
    
    protected void ensureOutput() throws RTException {

	String outputType = getOutputType();
	if (!isValidOutputType(outputType)) {
	    throw new RTException("Invalid output type: '" + outputType + "'");
	}
	
	String encoding = getEncoding();
	if (isFileNameOutputType(outputType)) {
	    String fileName = (String) getData(PROPERTY_OUTPUT_FILE_NAME);
	    ensureOutput(fileName, encoding);
	} else {
	    //TODO
	    throw new RTException("Invalid output type: '" + outputType + "'");
	}
    }
   
    protected void ensureOutput(String fileName, String encoding) throws RTException {
	if (fileName == null) {
	    throw new RTException("Output file name is empty");
	}
	try {
	    writer = createWriter(fileName, encoding);	    
	} catch (IOException ex){
	    throw new RTException(ex);
	}
    }
    
    protected void checkOutput() throws RTException {
	if (writer == null) {
	    throw new RTException("Output is not ready");
	}
    }

    protected void flushOutput() throws RTException {
	try {
	    writer.flush();
	} catch (IOException ex) {
	    throw new RTException(ex);
	}
    }

    protected void closeOutput() throws RTException {
	try {
	    writer.close();
	} catch (IOException ex) {
	    throw new RTException(ex);
	}
    }

    protected void writeDocument(Document document) throws RTException, IOException {
	if (document == null || !document.hasPages()) {
	    return;
	}
	
	int pageCount = document.getPageCount();
	List<Page> pages = document.getPages();

	pageOffsetX = offsetX;
	pageOffsetY = offsetY;
	
	level = -1;
	
	levelInc();
	for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
	    Page page = pages.get(pageIndex);
	    writePage(page, pageIndex);
	}
	levelDec();
	
    }

    protected void writePage(Page page, int pageIndex) throws RTException, IOException {
	offsetX = 0;
	offsetY = 0;
	
	levelInc();
	writePageStart(page);
	writePageBody(page);
	writePageEnd(page);
	levelDec();
	
    }
    
    protected void writePageStart(Page page) throws RTException, IOException {
	
	int pageWidth = page.getDisplayWidth();
	int pageHeight = page.getDisplayHeight();
	
	
	
	Attributes styleAttributes = new Attributes();
	setPosition(styleAttributes, 0, pageOffsetY);
	setSize(styleAttributes, pageWidth, pageHeight);
	
	styleAttributes.addAttribute("font-family", DEFAULT_FONT_FAMILY);
	styleAttributes.addAttribute("font-size", toFontSizeString(DEFAULT_FONT_SIZE));
	
	String style = styleAttributes.toStyleAttribute("style");
	write("<div " + style + ">\n");
	
	pageOffsetY += pageHeight;
    }
    
    protected void writePageBody(Page page) throws RTException, IOException {
	if (!page.hasChildren()) {
	    return;
	}

	List<Element> children = page.getChildren();

	for (Element element : children) {
	    levelInc();
	    if (element instanceof Grid) {
		writeGrid((Grid) element);
	    }
	    levelDec();
	}
    }
    
    protected void writePageEnd(Page page) throws RTException, IOException {
	write("</div>\n");
    }

    protected void writeGrid(Grid grid) throws RTException, IOException {
   	if (!grid.hasRows()) {
   	    return;
   	}

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
   	
   	// TODO:GC
   	Color contextBackground = null ;// gc.getBackground();
   	Color contextForeground = null; //gc.getForeground();
   	Font contextFont = null; //gc.getFont();
   	// TODO:GC

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
   	
   	// grid: start (position, size, background, border?)
	Attributes styleAttributes = new Attributes();
	setPosition(styleAttributes, gridOffsetX, gridOffsetY);
	setSize(styleAttributes, gridWidth, gridHeight);
	setBackground(styleAttributes, background);
	setForeground(styleAttributes, foreground);
	setFont(styleAttributes, font);
	
	String style = styleAttributes.toStyleAttribute("style");
	
	// TODO: HTML-TYPE
	//write("<div " + style + ">\n");
	writeGridStart(style);

	
   	// grid: normalize current gc
   	normalizeCurrentStyle();

   	// grid: init gc
   	// TODO:GC
   	//setCurrentStyle(gc);
   	
   	int columnIndex = 0;
   	int rowIndex = 0;
   	int cellIndex = 0;
   	
   	Row row = null;
   	Cell cell = null;
   	
   	int rowOffsetX = 0;
   	int rowOffsetY = 0;
   	int rowWidth = 0;
   	int rowHeight = 0;

   	// !NEW!
   	rowOffsetX = gridOffsetX;
   	rowOffsetY = gridOffsetY;


   	int columnWidth = 0;
   	
   	
   	// table tag only
	if (!isTableAsDiv()) {

	    styleAttributes = new Attributes();
	    setHeight(styleAttributes, 0);
	    style = styleAttributes.toStyleAttribute("style");
	   
	    levelInc();
	    writeRowStart(style);
	    
	    
	    // table: empty first row
	    columnIndex = 0;
	    for (int i = 0; i < columnCount; i++) {
		columnIndex = i;
		columnWidth = columns.get(columnIndex).getWidth();

		styleAttributes = new Attributes();
		setWidth(styleAttributes, columnWidth);
		style = styleAttributes.toStyleAttribute("style");

		levelInc();
		writeCellStart(style);
		writeCellEnd();
		levelDec();
	    }

	    writeRowEnd();
	    levelDec();
	}	

	columnIndex = 0;
	columnWidth = 0;
	
   	for (int i = 0; i < rowCount; i++) {
   	    
   	    row = rows.get(i);
   	    
   	    // !NEW!
   	    rowOffsetX = gridOffsetX;
   	    //rowOffsetY = offsetY;
   	    
   	    rowWidth = gridWidth;
   	    rowHeight = row.getHeight();
   	    
   	    int rowBorderTop = 0;
   	    int rowBorderBottom = 0;
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
   		
   	    // row: start (position, size, background)
   	    styleAttributes = new Attributes();
   	    
   	    if (isTableAsDiv()) {
   		setPosition(styleAttributes, rowOffsetX, rowOffsetY);
   		setSize(styleAttributes, rowWidth, rowHeight);
   	    } else {
   		setHeight(styleAttributes, rowHeight);
   	    }
   	    
   	    setBackground(styleAttributes, background);
   	    setForeground(styleAttributes, foreground);
   	    setFont(styleAttributes, font);
   	 
   	    style = styleAttributes.toStyleAttribute("style");
   	    
   	    levelInc();
   	    writeRowStart(style);
   	    
   	    // row: normalize current gc
   	    normalizeCurrentStyle();
   	    
   	    // row: init gc
   	    // TODO:GC
   	    //setCurrentStyle(gc);
   	    
   	    columnIndex = 0;
   	    //rowIndex = 0;
   	    
   	    int cellX = rowOffsetX;
   	    int cellY = 0; // !NEW!
   	    //int cellY = rowOffsetY;
   	    
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
   		
   		columnWidth = columns.get(columnIndex).getWidth();
   		//int rowHeight
   		
   		cellWidth = GridUtils.calculateCellWidth(layout, cell, columns, columnIndex);
   		cellHeight = GridUtils.calculateCellHeight(layout, cell, rows, rowIndex);
   		
   		int columnBorderLeft = 0;
   		int columnBorderRight = 0;
   		
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

   		// fill outer cell
   		int fillCellX = cellX - columnBorderLeft;
   		int fillCellY = cellY - rowBorderTop;
   		int fillCellWidth = cellWidth  + columnBorderLeft + columnBorderRight;
   		int fillCellHeight = cellHeight + rowBorderTop + rowBorderBottom;
   		
   		Border border = layout.getCellBorder(columnIndex, rowIndex);

   		int borderLeft = 0;
   		int borderRight = 0;
   		int borderTop = 0;
   		int borderBottom = 0;

   		if (border != null && !border.isEmpty()) {
    		   borderLeft = getLineWidth(border.hasLeft() ? border.getLeft() : null);
 		   borderRight = getLineWidth(border.hasRight() ? border.getRight() : null);
 		   borderTop = getLineWidth(border.hasTop() ? border.getTop() : null);
 		   borderBottom = getLineWidth(border.hasBottom() ? border.getBottom() : null);
 		   
 		   // fixed size
 		   fillCellWidth -= (borderLeft + borderRight);
 		   fillCellHeight -= (borderTop + borderBottom);   		
   		}
   		
   		
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
   		
   		
   		// cell: start (position, size, background, foreground, border)
   		styleAttributes = new Attributes();
   		
   		String attr = null;
   		if (isTableAsDiv()) {
   		    
   		    // cell: [DIV] position, size
   		    setPosition(styleAttributes, fillCellX, fillCellY);
   		    setSize(styleAttributes, fillCellWidth, fillCellHeight);
   		    
   		    // cell: [DIV] parent {display: table}, child {display: table-cell}
   		    if (cell.getVerticalAlign() != null) {
   			styleAttributes.addAttribute("display", "table");
   		    }
   		    
   		    // cell: [DIV] padding = none, use textMargin late (emulate padding like margin)
   		    //if (cell.hasPadding()) {
   			//setPadding(styleAttributes, cell.getPadding());
   		    //}
   		    
   		} else {
   		    
   		    // cell: [TABLE:TD] position, size = none, use first empty row in table (invisible: height=0)
   		    //if (rowIndex == 0) {
   			//setWidth(styleAttributes, columnWidth);
   		    //}
   		    
   		    // cell: [TABLE:TD] padding
   		    if (cell.hasPadding()) {
   			setPadding(styleAttributes, cell.getPadding());
   		    }
   		    
   		    if (colspan > 1) {
   			attr = "colspan=\"" + colspan + "\"";
   		    }
   		    if (rowspan > 1) {
   			if (attr == null) {
   			    attr = "";
   			} else {
   			    attr = attr + " ";
   			}
   			attr += "rowspan=\"" + rowspan + "\"";
   		    }
   		}
   		
   		setBorder(styleAttributes, border);
   		setBackground(styleAttributes, background);
   		setForeground(styleAttributes, foreground);
   		setFont(styleAttributes, font);
   		
   		style = styleAttributes.toStyleAttribute("style");
   		if (attr != null) {
   		    style += (" " + attr);
   		}
   		
   		levelInc();
   		writeCellStart(style);
   		
   		// cell: normalize current gc
   		normalizeCurrentStyle();
   		
   		// cell: init gc
   		//setCurrentStyle(gc);
   		
   		
   		// cell: area
   		int areaX = /*cellX + */ paddingLeft; // #REL
   		int areaY = /*cellY + */ paddingTop;  // #REL
   		int areaWidth = cellWidth - paddingLeft - paddingRight;
   		int areaHeight = cellHeight - paddingTop - paddingBottom;
   		
   		// cell: paint
   		boolean isTextPosition = false; //isTableAsDiv();
   		Insets textMargin = null;
   		
   		// cell: [TABLE:TD] textMargin
   		if (isTableAsDiv()) {
   		    textMargin =  cell.hasPadding() ? cell.getPadding() : null;
   		}
   		if (areaWidth > 0 && areaHeight > 0) {
   		    Object value = cell.getValue();
   		    if (value != null) {
   			String text = formatCellValue(cell);
   			writeText(text, (isTextPosition ? areaX : null), (isTextPosition ? areaY : null), areaWidth, areaHeight, textMargin, font, foreground, cell.getHorizontalAlign(), cell.getVerticalAlign());
   		    }
   		}
   		
   		// cell: end
   		writeCellEnd();
   		
   		levelDec();
   	   	    
   		columnIndex = nextColumnIndex;
   		cellX += cellWidth;
   		//cellX += columnBorderLeft;
   		cellX += columnBorderRight;
   		
   	    }
   	    
   	    // row: end
   	    writeRowEnd();
   	    levelDec();

   	    rowIndex++;
   	    int shiftY = row.getHeight() + rowBorderTop + rowBorderBottom;
   	    rowOffsetY += shiftY;
   	    offsetY += shiftY;
   	 
   	    //offsetY += row.getHeight();
   	    //offsetY += rowBorderTop;
   	    //offsetY += rowBorderBottom;
   	    
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
   	    // TODO:GC
   	    //setCurrentStyle(gc);
   	    
   	}
   	
   	// grid: end
   	
   	//TODO: HTML-TYPE
	//write("</div>\n");
	writeGridEnd();
    }
    
    
	

    protected void writeGridStart(String attr) throws IOException {
	writeStartTag(isTableAsDiv() ? TAG_DIV : (TAG_TABLE + " cellpadding=\"0\" cellspacing=\"0\""), attr);
    }

    protected void writeGridEnd() throws IOException {
	writeEndTag(isTableAsDiv() ? TAG_DIV : TAG_TABLE);
    }

    protected void writeRowStart(String attr) throws IOException {
	writeStartTag(isTableAsDiv() ? TAG_DIV : TAG_TR, attr);
    }

    protected void writeRowEnd() throws IOException {
	writeEndTag(isTableAsDiv() ? TAG_DIV : TAG_TR);
    }

    protected void writeCellStart(String attr) throws IOException {
	writeStartTag(isTableAsDiv() ? TAG_DIV : TAG_TD, attr);
    }

    protected void writeCellEnd() throws IOException {
	writeEndTag(isTableAsDiv() ? TAG_DIV : TAG_TD);
    }
    
}
