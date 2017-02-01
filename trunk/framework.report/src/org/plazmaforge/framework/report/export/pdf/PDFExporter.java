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

/**
 * 
 */
package org.plazmaforge.framework.report.export.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.export.AbstractBaseExporter;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.BorderRegion;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.Margin;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.Size;
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
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author ohapon
 *
 */
public class PDFExporter extends AbstractBaseExporter {
    
    
    public static final short ALIGN_LEFT = com.lowagie.text.Element.ALIGN_LEFT;
    public static final short ALIGN_CENTER = com.lowagie.text.Element.ALIGN_CENTER;
    public static final short ALIGN_RIGHT = com.lowagie.text.Element.ALIGN_RIGHT;
    public static final short ALIGN_FILL = com.lowagie.text.Element.ALIGN_JUSTIFIED;
    //public static final short ALIGN_FILL = com.lowagie.text.Element.ALIGN_JUSTIFIED
    
    public static final short ALIGN_TOP = com.lowagie.text.Element.ALIGN_TOP;
    public static final short ALIGN_MIDDLE = com.lowagie.text.Element.ALIGN_MIDDLE;
    public static final short ALIGN_BOTTOM = com.lowagie.text.Element.ALIGN_BOTTOM;
    
    
    protected int offsetX;
    protected int offsetY;


    protected PdfWriter writer;
    protected com.lowagie.text.Document pdfDocument;
    
    @Override
    public void exportDocument(Document document) throws RTException {
	try {
	    ensureOutput();
	    checkOutput();

	    writeDocument(document);

	    flushOutput();
	    closeOutput();
	    
	} catch (Exception ex) {
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
	
	String encoding = null; //getEncoding();
	if (isFileNameOutputType(outputType)) {
	    String fileName = (String) getData(PROPERTY_OUTPUT_FILE_NAME);
	    ensureOutput(fileName, encoding);
	} else {
	    //TODO
	    throw new RTException("Invalid output type: '" + outputType + "'");
	}
    }
   
    protected void ensureOutput(String fileName, String encoding)
	    throws RTException {
	if (fileName == null) {
	    throw new RTException("Output file name is empty");
	}
	try {

	    FileOutputStream os = new FileOutputStream(fileName);
	    pdfDocument = new com.lowagie.text.Document();

	    writer = PdfWriter.getInstance(pdfDocument, os);
	    writer.setCloseStream(false);

	    pdfDocument.open();
	} catch (Exception ex) {
	    throw new RTException(ex);
	}
    }
    
    protected void checkOutput() throws RTException {
	if (writer == null) {
	    throw new RTException("Output is not ready");
	}
    }

    protected void flushOutput() throws RTException {
	//try {
	//    writer.flush();
	//} catch (IOException ex) {
	//    throw new RTException(ex);
	//}
    }

    protected void closeOutput() throws RTException {
	//try {
	pdfDocument.close();
	writer.close();
	//} catch (IOException ex) {
	//    throw new RTException(ex);
	//}
    }
    
    protected void writeDocument(Document document) throws RTException, IOException, DocumentException {
	if (document == null || !document.hasPages()) {
	    return;
	}

	int pageCount = document.getPageCount();
	List<Page> pages = document.getPages();
	
	PageSetup pageSetup = document.getPage(0).getPageSetup();
	Size pageSize = pageSetup.getSize();
	Margin pageMargin = pageSetup.getMargin();
	
	pdfDocument.setPageSize(new Rectangle(pageSize.getWidth(), pageSize.getHeight()));
	pdfDocument.setMargins(pageMargin.getLeft(), pageMargin.getTop(), pageMargin.getRight(), pageMargin.getBottom());

	for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
	    Page page = pages.get(pageIndex);
	    writePage(page, pageIndex);
	}

    }

    protected void writePage(Page page, int pageIndex) throws RTException, IOException, DocumentException {
	
	offsetX = 0;
	offsetY = 0;
	
	pdfDocument.newPage();
	
	if (!page.hasChildren()) {
	    return;
	}

	List<Element> children = page.getChildren();

	for (Element element : children) {
	    if (element instanceof Grid) {
		writeGrid((Grid) element);
	    }
	}
    }
       
    
    protected void writeGrid(Grid grid) throws RTException, IOException, DocumentException {
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

   	//int gridOffsetX = offsetX;
   	//int gridOffsetY = offsetY;
   	
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
   	//rowOffsetX = gridOffsetX;
   	//rowOffsetY = gridOffsetY;

   	int columnWidth = 0;
   	
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
   	

   	PdfPTable table = new PdfPTable(columnCount); 
   	float[] columnWidths = new float[columnCount];
   	
	for (int i = 0; i < columnCount; i++) {
	    columnIndex = i;
	    columnWidths[columnIndex] = columns.get(columnIndex).getWidth();
	}
	
	table.setWidths(columnWidths);
	
	
	for (int i = 0; i < rowCount; i++) {

	    row = rows.get(i);

	    // !NEW!
	    //rowOffsetX = gridOffsetX;
	    // rowOffsetY = offsetY;

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
   	    //table.add
   	    /*
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
   	    
   	    writeRowStart(style);
   	    */
   	    
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
   		
   		
   		/*
   		styleAttributes = new Attributes();
   		
   		String attr = null;
   		
   		    
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
   		
   		
   		setBorder(styleAttributes, border);
   		setBackground(styleAttributes, background);
   		setForeground(styleAttributes, foreground);
   		setFont(styleAttributes, font);
   		
   		style = styleAttributes.toStyleAttribute("style");
   		if (attr != null) {
   		    style += (" " + attr);
   		}
   		
   		writeCellStart(style);
   		*/
   		
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
   		String text = null;
   		if (areaWidth > 0 && areaHeight > 0) {
   		    Object value = cell.getValue();
   		    if (value != null) {
   			text = formatCellValue(cell);
   			
   			//writeText(text, (isTextPosition ? areaX : null), (isTextPosition ? areaY : null), areaWidth, areaHeight, textMargin, font, foreground, cell.getHorizontalAlign(), cell.getVerticalAlign());
   		    }
   		}
   		if (text == null) {
   		    text = "";
   		}
   		
   		com.lowagie.text.Font pdfFont = getPdfFont(font, foreground);
   		
   		Phrase phrase = pdfFont == null ? new Phrase(text) : new Phrase(text, pdfFont);
   		
		PdfPCell pdfCell = new PdfPCell(phrase);
		
		pdfCell.setFixedHeight(cellHeight);
		
		if (colspan > 1) {
		    pdfCell.setColspan(colspan);
		}
		if (rowspan > 1) {
		    pdfCell.setRowspan(rowspan);
		}
		
		// cell: horizontal align
		setHorizontalAlign(pdfCell, cell.getHorizontalAlign());

		// cell: vertical align
		setVerticalAlign(pdfCell, cell.getVerticalAlign());
		
		// cell: padding
		if (cell.hasPadding()) {
		    pdfCell.setPaddingLeft(paddingLeft);
		    pdfCell.setPaddingTop(paddingTop);
		    pdfCell.setPaddingRight(paddingRight);
		    pdfCell.setPaddingBottom(paddingBottom);
		}
		
		// cell: border
		setBorder(pdfCell, border);
		
		// cell: background
		setBackground(pdfCell, background);
		
		
   		table.addCell(pdfCell);
   		
   		// cell: end
   	   	    
   		columnIndex = nextColumnIndex;
   		cellX += cellWidth;
   		//cellX += columnBorderLeft;
   		cellX += columnBorderRight;
   		
   	    }
   	    
   	    // row: end

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
	
	
	pdfDocument.add(table);
	
    }
    
    
    ////
    
    
    
    // horizontal align
    protected void setHorizontalAlign(PdfPCell pdfCell, HorizontalAlign horizontalAlign) {
	if (horizontalAlign == null) {
	    return;
	}
	if (horizontalAlign == HorizontalAlign.LEFT) {
	    pdfCell.setHorizontalAlignment(ALIGN_LEFT);
	} else if (horizontalAlign == HorizontalAlign.CENTER) {
	    pdfCell.setHorizontalAlignment(ALIGN_CENTER);
	} else if (horizontalAlign == HorizontalAlign.RIGHT) {
	    pdfCell.setHorizontalAlignment(ALIGN_RIGHT);
	}
    }

    // vertical align
    protected void setVerticalAlign(PdfPCell pdfCell, VerticalAlign verticalAlign) {
	if (verticalAlign == null) {
	    return;
	}
	if (verticalAlign == VerticalAlign.TOP) {
	    pdfCell.setVerticalAlignment(ALIGN_TOP);
	} else if (verticalAlign == VerticalAlign.MIDDLE) {
	    pdfCell.setVerticalAlignment(ALIGN_MIDDLE);
	} else if (verticalAlign == VerticalAlign.BOTTOM) {
	    pdfCell.setVerticalAlignment(ALIGN_BOTTOM);
	}
    }
    
    // border (left, top, right, bottom)
    protected void setBorder(PdfPCell pdfCell, Border border) {
	if (border == null || border.isEmpty()) {
	    pdfCell.setBorder(Rectangle.NO_BORDER);
	    return;
	}
	
	Pen pen = null;
	int w = 0;
	Color color = null;
	
	int borderType = Rectangle.NO_BORDER;
	
	// Left
	pen = getBorderPen(border.hasLeft() ? border.getLeft() : null);
	if (pen != null) {
	    borderType |= Rectangle.LEFT;
	    w = getLineWidth(pen);
	    color = getLineColor(pen);
	    pdfCell.setBorderColorLeft(getAWTColor(color));
	    pdfCell.setBorderWidthLeft(w);
	} else {
	    pdfCell.setBorderWidthLeft(0);
	}

	// Right
	pen = getBorderPen(border.hasRight() ? border.getRight() : null);
	if (pen != null) {
	    borderType |= Rectangle.RIGHT;
	    w = getLineWidth(pen);
	    color = getLineColor(pen);
	    pdfCell.setBorderColorRight(getAWTColor(color));
	    pdfCell.setBorderWidthRight(w);
	} else {
	    pdfCell.setBorderWidthRight(0);
	}

	// Top
	pen = getBorderPen(border.hasTop() ? border.getTop() : null);
	if (pen != null) {
	    borderType |= Rectangle.TOP;
	    w = getLineWidth(pen);
	    color = getLineColor(pen);
	    pdfCell.setBorderColorTop(getAWTColor(color));
	    pdfCell.setBorderWidthTop(w);
	} else {
	    pdfCell.setBorderWidthTop(0);
	}

	// Bottom
	pen = getBorderPen(border.hasBottom() ? border.getBottom() : null);
	if (pen != null) {
	    borderType |= Rectangle.BOTTOM;
	    w = getLineWidth(pen);
	    color = getLineColor(pen);
	    pdfCell.setBorderColorBottom(getAWTColor(color));
	    pdfCell.setBorderWidthBottom(w);
	} else {
	    pdfCell.setBorderWidthBottom(0);
	}

	pdfCell.setBorder(borderType);
    }
    
    // border (one line)
//    protected void setBorder(PdfPCell pdfCell, String name, Pen pen) {
//	if (pen == null || pen.isEmpty()) {
//	    return;
//	}
//	int w = getLineWidth(pen);
//	Color color = getLineColor(pen);
//	styleAttributes.addAttribute(name, "" + toDimensionString(w) + " solid " + toColorString(color));
//    }
    
    protected Pen getBorderPen(Pen pen) {
	if (pen == null || pen.isEmpty()) {
	    return null;
	}
	return pen;
    }

    protected void setBackground(PdfPCell pdfCell, Color color) {
	if (color == null) {
	    return;
	}
	java.awt.Color awtColor = getAWTColor(color);
	pdfCell.setBackgroundColor(awtColor);
	
    }
    
    protected java.awt.Color getAWTColor(Color color) {
	if (color == null) {
	    return null;
	}
	return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue(), get255ColorAlpha(color.getAlpha())); 
    }
    
    protected int get255ColorAlpha(float alpha) {
	return (int) (alpha * 255);
    }
 
    protected com.lowagie.text.Font getPdfFont(Font font, Color color) {
	if (font == null) {
	    return null;
	}
	java.awt.Color fontColor = getAWTColor(color);
	//new com.lowagie.text.Font(BaseFont.HELVETICA, font.getSize(), Font.NORMAL, fontColor);
	if (fontColor == null) {
	    fontColor = java.awt.Color.BLACK;
	}
	String fontName = font.getName(); //BaseFont.HELVETICA
	int fontSize = font.getSize();
	int fontStyle = com.lowagie.text.Font.NORMAL;
	if (font.isBold()) {
	    fontStyle |= com.lowagie.text.Font.BOLD;
	}
	if (font.isItalic()) {
	    fontStyle |= com.lowagie.text.Font.ITALIC;
	}
	if (font.isUnderline()) {
	    fontStyle |= com.lowagie.text.Font.UNDERLINE;
	}
	if (font.isStrikeout()) {
	    fontStyle |= com.lowagie.text.Font.STRIKETHRU;
	}
	
	return FontFactory.getFont(fontName, /*"cp1251",*/ fontSize, fontStyle, fontColor);
    }
}
