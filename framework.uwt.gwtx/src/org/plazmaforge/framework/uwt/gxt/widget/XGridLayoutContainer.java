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
package org.plazmaforge.framework.uwt.gxt.widget;

import java.util.logging.Logger;

import org.plazmaforge.framework.uwt.gxt.layout.XGridData;
import org.plazmaforge.framework.uwt.gxt.layout.XGridLayout;
import org.plazmaforge.framework.uwt.gxt.layout.XLayoutData;
import org.plazmaforge.framework.uwt.gxt.layout.XLayoutData.HorizontalAlignment;
import org.plazmaforge.framework.uwt.gxt.layout.XLayoutData.VerticalAlignment;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Size;

/**
 * 
 * @author ohapon
 *
 */
public class XGridLayoutContainer extends XAbstractCellLayoutContainer<XGridLayout> {
    
    private static Logger logger = Logger.getLogger(XGridLayoutContainer.class.getName());

    public XGridLayoutContainer() {
	this(new XGridLayout());
    }
    
    public XGridLayoutContainer(XGridLayout gridLayout) {
	super(gridLayout);
	setElement(Document.get().createDivElement());
    }

    //private void setAbsolutePosition() {
    //	XElement container = getLayoutContainer();
    // 	container.getStyle().setPosition(Position.ABSOLUTE);
    //} 
    
    @Override
    protected XGridLayout createLayout() {
	return new XGridLayout();
    }
    
    public int getColumnCount() {
        return getLayout().getColumnCount();
    }

    protected int getHorizontalSpacing() {
        return getLayout().getHorizontalSpacing();
    }

    protected int getVerticalSpacing() {
        return getLayout().getVerticalSpacing();
    }    
    
    protected boolean acceptLayoutData(Object ld) {
	return ld instanceof XGridData;
    }

    protected XLayoutData createLayoutData() {
	return new XGridData();
    }
    
    public Size computeSize(int hWidth, int hHeight, boolean layout) {
	
	boolean debug = isDebug();
	if (debug) {
	    logDebug("LAYOUT: " + (layout ? "doLayout" : "computeSize"));
	    dumpContainerSize(hWidth, hHeight);
	}
	
 	// Width of container
 	int containerWidth = hWidth;
 	
 	// Height of container
 	int containerHeight = hHeight;
 	
 	// Fix size of container
 	if (containerWidth < 0) {
 	    containerWidth = 0;
 	}
 	if (containerHeight < 0) {
 	    containerHeight = 0;
 	}

 	//////////////////////////////////////////////////////
 	// Get grid layout info
 	//////////////////////////////////////////////////////
 	
 	int columnCount = getColumnCount();
 	int horizontalSpacing = getHorizontalSpacing();
 	int verticalSpacing = getVerticalSpacing();
 	
 	// Get margin
 	int marginLeft = getMarginLeft();
 	int marginTop = getMarginTop();
 	int marginRight = getMarginRight();
 	int marginBottom = getMarginBottom();
 	
 	// Fix margin
 	if (marginLeft < 0) {
 	    marginLeft = 0;
 	}
 	if (marginTop < 0) {
 	    marginTop = 0;
 	}
 	if (marginRight < 0) {
 	    marginRight = 0;
 	}
 	if (marginBottom < 0) {
 	    marginBottom = 0;
 	}
 	
 	boolean hasMargin = marginLeft > 0 || marginTop > 0 || marginRight > 0 || marginBottom > 0;

 	// Get size without margin (Client/Output Size)
 	int clientWidth =  containerWidth  - marginLeft - marginRight;
 	int clientHeight = containerHeight - marginTop - marginBottom;


 	// Shift start position by margin
 	int offsetX = getMarginLeft();
 	int offsetY = getMarginTop();

 	if (columnCount <= 0) {
 	    columnCount = 1;
 	}
 	
 	// Fix spacing
 	if (horizontalSpacing < 0) {
 	    horizontalSpacing = 0;
 	}
 	if (verticalSpacing < 0) {
 	    verticalSpacing = 0;
 	}
 	
 	// Real space width:  width of area without columns spacing (areaWidth - horizontalSpacing of all columns [columnCount - 1])
 	int spaceWidth = clientWidth;
 	
 	// Real space height: height of area without rows spacing (areaHeight - verticalSpacing of all columns [rowCount - 1])
 	int spaceHeight = clientHeight;
 	    
 	int count = getWidgetCount();
 	
 	boolean hasChildren = count > 0;
 	
 	int column = 0;
 	int row = 0;
 	int rowCount = 1;
 	Cell[] cells = new Cell[count];
 	int prevColumnSpan = 0;
 	boolean useFlexColumns = false;
 	boolean useFlexRows = false;

 	// Max width of child component
 	int maxWidth = 0;

 	// Max height of child component
 	int maxHeight = 0;
 	
 	// Width of children area
 	int childrenWidth = 0;
 	
 	// Height of children area
 	int childrenHeight = 0;
 	
 	// POINT-1: Process components
 	// Calculate sizes and create cells
 	// One cell - one component
 	for (int i = 0; i < count; i++) {
 	    Widget widget = getWidget(i);
 	    
 	    // S-1
 	    preparePosition(widget);
 	
 	    // S-2
 	    boolean foundLayoutData = isValidLayoutData(widget);
 	    XGridData layoutData = (XGridData) prepareLayoutData(widget);
 	   
 	    // S-3
	    Size preferredSize = preparePreferredSize(widget, layoutData); 	    
     

 	    
 	    int colSpan = layoutData.getColSpan();
 	    if (colSpan <= 0 ) {
 		colSpan = 1;
 	    }
 	    int rowSpan = layoutData.getRowSpan();
 	    if (rowSpan <= 0 ) {
 		rowSpan = 1;
 	    }
 	    
 	    if (layoutData.isHorizontalFlex()) {
 		useFlexColumns = true;
 	    }
 	    if (layoutData.isVerticalFlex()) {
 		useFlexRows = true;
 	    }

 	    
 	    // If last column
 	    if (column + prevColumnSpan >= columnCount) {
 		prevColumnSpan = 0;
 		column = 0; // reset column
 		row++; // start new row
 		if (rowCount < (row + 1)) {
 		    rowCount = row + 1;
 		}
 	    } else {
 		column += prevColumnSpan;
 	    }
 	    
 	    
 	    // Fix column span
 	    if (column + colSpan > columnCount) {
 		colSpan = columnCount - column;
 	    }
 	    if (rowSpan > 1) {
 		rowCount = rowCount + (rowSpan - 1);
 	    }
 	    
 	    prevColumnSpan = colSpan;
 	    
 	    
 	    ////
 	    
 	    // Find free space for cell if need
 	    // Previous components can fill current cell
 	    
 	    if  (!isFree(cells, column, row, colSpan, rowSpan)) {
 		boolean isFindSpace = false;
 		while (!isFindSpace) {
 		    // last column
 		    if  (column == columnCount - 1) {
 			column = 0; // reset column
 			row++; // start new row
 		    } else {
 			column++;
 		    }
 		    isFindSpace = isFree(cells, column, row, colSpan, rowSpan);
 		}
 		
 		// increment rowCount if need 
 		if (rowCount < (row + 1)) {
 		    rowCount = row + 1;
 		}
 	    }

 	    ////
 	    
 	    Cell cell = new Cell();
 	    cell.column = column;
 	    cell.row = row;
 	    cell.colSpan = colSpan;
 	    cell.rowSpan = rowSpan;
 	    cell.horizontalAlign = layoutData.getHorizontalAlign();
 	    cell.verticalAlign = layoutData.getVerticalAlign();
 	    cell.horizontalFlex = layoutData.isHorizontalFlex();
 	    cell.verticalFlex = layoutData.isVerticalFlex();
 	    //cell.preferredSize = new Size(layoutData.getPreferredWidth(), layoutData.getPreferredHeight());
 	    cell.preferredSize = preferredSize;
 	    
 	   if (layout) {
 		layoutData.setPreferredWidth(preferredSize.getWidth());
 		layoutData.setPreferredHeight(preferredSize.getHeight());
 		if (!foundLayoutData) {
 		    widget.setLayoutData(layoutData);
 		}
 	   }
 	   
	   
 	    cells[i] = cell; 
 	    
 	}
 	
 	// DEBUG
 	if (debug) {
 	    dumpCells(cells);
 	}
 	
 	int[] columnWidth = new int[columnCount];  
 	int[] rowHeight = new int[rowCount];

 	boolean[] flexColumns = null;
 	boolean[] flexRows = null;

 	if (useFlexColumns) {
 	    flexColumns = new boolean[columnCount];
 	}
 	if (useFlexRows) {
 	    flexRows = new boolean[rowCount];
 	}

 	
 	// Calculate Space Width
 	if (columnCount > 1 && horizontalSpacing > 0) {
 	    spaceWidth = clientWidth - (horizontalSpacing * (columnCount - 1));
 	}

 	// Calculate Space Height
 	if (rowCount > 1 && verticalSpacing > 0) {
 	    spaceHeight = clientHeight - (verticalSpacing * (rowCount - 1));
 	}

 	
 	
 	// POINT-2: Calculate row height
 	for (int k = 0; k < rowCount; k++) {
 	    int rowH = 0;
 	    
 	    // Widgets
 	    for (int i = 0; i < count; i++) {
 		Cell cell = cells[i];
 		int ch = 0;
 		if (cell.row == k) {
 		    
 		    int rowSpan = cell.rowSpan;
 		    ch = cells[i].preferredSize.getHeight();

 		    if (rowSpan > 1) {
 			
 			// Calculate avg height
 			ch = ch / rowSpan;
 			
 			// Transfer avg height to next rows
 			for (int z = k + 1; z < k + rowSpan; z++) {
 			    rowHeight[k] = Math.max(rowHeight[k], ch);
 			}
 		    }

 		    if (ch < 0){
 			ch = 0;
 		    }
 		    
 		    if (useFlexRows && cell.verticalFlex) {
 			// Transfer flex marker to current and next rows
 			for (int z = k; z < k + rowSpan; z++) {
 			    flexRows[z] = true;
 			}
 		    }
 		}
 		rowH = Math.max(rowH, ch);
 	    }
 	    rowHeight[k] = rowH;
 	}

 	// DISABLE: ONLY FOR COLUMNS
 	//boolean fixColumnMaxSize = true; 
 	
 	// POINT-3: Calculate column width
 	for (int k = 0; k < columnCount; k++) {
 	    int columnW = columnWidth[k];
 	    
 	    // Widgets
 	    for (int i = 0; i < count; i++) {
 		Cell cell = cells[i];
 		int cw = 0;
 		if (cell.column == k) {
 		    
 		    int columnSpan = cell.colSpan;
 		    
 		    cw = cells[i].preferredSize.getWidth();
 		    

 		    // DISABLE
 		    //if (fixColumnMaxSize && cw > spaceWidth) {
 			//cw = spaceWidth / columnCount;
 			// TODO: May be need transfer avg width to next columns (see next lines) 
 		    //}
 		    
 		    if (columnSpan > 1) {
 			
 			// Calculate avg width
 			cw = cw / columnSpan;
 			
 			// Transfer avg width to next columns
 			for (int z = k + 1; z < k + columnSpan; z++) {
 			    columnWidth[k] = Math.max(columnWidth[k], cw);
 			}
 		    }
 		    
 		    
 		    if (cw < 0) {
 			cw = 0;
 		    }
 		    
 		    if (useFlexColumns && cell.horizontalFlex) {
 			
 			// Transfer flex marker to current and next columns
 			for (int z = k; z < k + columnSpan; z++) {
 			    flexColumns[z] = true;
 			}
 		    }
 		}
 		columnW = Math.max(columnW, cw);
 	    }
 	    columnWidth[k] = columnW;
 	}

 	// DEBUG
 	if (debug) {
 	    dumpColumns(columnWidth);
 	    dumpRows(rowHeight);
 	}
 	
 	// POINT-2.2
 	// Recalculate flex rows
 	if (useFlexRows && hHeight != -1) {
 	    int fixHeight = 0;
 	    int flexHeight = 0;
 	    int flexCount = 0;
 	    int totalHeight = 0;
 	    for (int k = 0; k < rowCount; k++) {
 		int ch = rowHeight[k];
 		totalHeight += ch;
 		if (flexRows[k]) {
 		    flexCount++;
 		    flexHeight += ch;
 		} else {
 		    fixHeight += ch;
 		}
 	    }
 	    int f = spaceHeight - fixHeight;
 	    if (f < 0) {
 		f = 0;
 	    }
 	    int avgHeight = f == 0 ? 0 : f / flexCount;
 	    for (int k = 0; k < rowCount; k++) {
 		if (flexRows[k]) {
 		    rowHeight[k] = avgHeight;
 		}
 	    }
 	}
 		
 	// POINT-3.2
 	// Recalculate flex columns
 	if (useFlexColumns && hWidth != -1) {
 	    int fixWidth = 0;
 	    int flexWidth = 0;
 	    int flexCount = 0;
 	    int totalWidth = 0;
 	    for (int k = 0; k < columnCount; k++) {
 		int cw = columnWidth[k];
 		totalWidth += cw;
 		if (flexColumns[k]) {
 		    flexCount++;
 		    flexWidth += cw;
 		} else {
 		    fixWidth += cw;
 		}
 	    }
 	    int f = spaceWidth - fixWidth;
 	    if (f < 0) {
 		f = 0;
 	    }
 	    //int avgWidth = flexWidth / flexCount;
 	    int avgWidth = f == 0 ? 0 : f / flexCount;
 	    for (int k = 0; k < columnCount; k++) {
 		if (flexColumns[k]) {
 		    columnWidth[k] = avgWidth;
 		}
 	    }
 	}
 	
 	// DEBUG
 	if (debug) {
 	    dumpFlexColumns(columnWidth);
 	}

 	
 	// POINT-4: Render components
 	for (int i = 0; i < count; i++) {
 	    
 	    Cell cell = cells[i];
 	    Widget widget = getWidget(i);
 	   
 	    int offsetColumn = 0;
 	    for (int k = 0; k < cell.column; k++) {
 		offsetColumn += columnWidth[k];
 	    }
 	    int offsetRow = 0;
 	    for (int k = 0; k < cell.row; k++) {
 		offsetRow += rowHeight[k];
 	    }
 	    int offsetSpacingColumns = 0; // Previous column spacing
 	    if (horizontalSpacing > 0 && cell.column > 0) {
 		offsetSpacingColumns = cell.column * horizontalSpacing; 
 	    }

 	    int offsetSpacingRows = 0; // Previous row spacing
 	    if (verticalSpacing > 0 && cell.row > 0) {
 		offsetSpacingRows = cell.row * verticalSpacing; 
 	    }

 	    int cellX = offsetX + offsetColumn + offsetSpacingColumns;
 	    int cellY = offsetY + offsetRow + offsetSpacingRows;

 	    int x = cellX;
 	    int y = cellY;
 	    
 	    HorizontalAlignment hAlign = cell.horizontalAlign;
 	    if (hAlign == null) {
 		hAlign = XGridData.DEFAULT_HORIZONTAL_ALIGN;
 	    }
 	    VerticalAlignment vAlign = cell.verticalAlign;
 	    if (vAlign == null) {
 		vAlign = XGridData.DEFAULT_VERTICAL_ALIGN;
 	    }
 	    
 	    int cellWidth = 0;
 	    // Calculate width of cell with column span and horizontal spacing 
 	    for (int k = cell.column; k < cell.column + cell.colSpan; k++) {
 		cellWidth += columnWidth[k];
 		if (k > cell.column) { 
 		    cellWidth += horizontalSpacing;
 		}
 	    }
 	    
 	    int cellHeight = 0;
 	    // Calculate height of cell with row span and vertical spacing
 	    for (int k = cell.row; k < cell.row+ cell.rowSpan; k++) {
 		cellHeight += rowHeight[k];
 		if (k > cell.row) { 
 		    cellHeight += verticalSpacing;
 		}
 	    }
 	   

 	    int widgetWidth = cells[i].preferredSize.getWidth();
 	    
 	    //LF
 	    if (widgetWidth == -1) {
 		widgetWidth = widget.getOffsetWidth();
 	    }
 	    
 	    //DEBUG-FIX
 	    if (widgetWidth == 0) {
 		//componentWidth = 10;
 	    }
 	    
 	    if (widgetWidth > cellWidth) {
 		widgetWidth = cellWidth;
 	    }
 	    
 	    
 	    int widgetHeight = cells[i].preferredSize.getHeight();
 	    
 	    //LF
 	    if (widgetHeight == -1) {
 		widgetHeight = widget.getOffsetHeight();
 	    }

 	    //DEBUG-FIX
 	    if (widgetHeight == 0) {
 		//componentHeight = 10;
 	    }

 	    if (widgetHeight > cellHeight) {
 		widgetHeight = cellHeight;
 	    }
 	    
 	    
 	    // HORIZONTAL
 	    if (HorizontalAlignment.FILL.equals(hAlign)) {
 		// Fill align
 		widgetWidth = cellWidth;

 		// Special fix for last component in row. We have problem with ToolBar in CoolBar
 		if (cell.column == columnCount - 1 && widgetWidth > 0 /*&& (c instanceof ToolBar)*/) {
 		    widgetWidth -= 1;
 		}

 	    } else if (HorizontalAlignment.RIGHT.equals(hAlign)) {
 		// Right align
 		int d = cellWidth - widgetWidth;
 		if (d < 0) {
 		    d = 0;
 		}
 		x = cellX  + d;
 	    } else if (HorizontalAlignment.CENTER.equals(hAlign)) {
 		// Center align
 		int d = cellWidth - widgetWidth;
 		if (d < 0) {
 		    d = 0;
 		}
 		x = cellX  + d / 2;
 	    }
 	    
 	    // VERTICAL
 	    if (VerticalAlignment.FILL.equals(vAlign)) {
 		// Fill align
 		widgetHeight = cellHeight;
 	    } else if (VerticalAlignment.BOTTOM.equals(vAlign)) {
 		// Bottom align
 		int d = cellHeight - widgetHeight;
 		if (d < 0) {
 		    d = 0;
 		}
 		y = cellY  + d;
 	    } else if (VerticalAlignment.MIDDLE.equals(vAlign)) {
 		// Middle align
 		int d = cellHeight - widgetHeight;
 		if (d < 0) {
 		    d = 0;
 		}
 		y = cellY  + d / 2;
 	    }

 	    if (layout) {
 		setPosition(widget, x /*+ shiftX*/, y /*+ shiftY*/);
 		setSize(widget, widgetWidth, widgetHeight);
 	    }
 	    
 	    maxWidth = Math.max(maxWidth, widgetWidth);
 	    maxHeight = Math.max(maxHeight, widgetHeight);

 	    int w = x - marginLeft + widgetWidth;
 	    int h = y - marginTop + widgetHeight;
 	    
 	    // Calculate children area
 	    childrenWidth = Math.max(childrenWidth, w);
 	    childrenHeight = Math.max(childrenHeight, h);
 	}

 	int computeWidth = 0;
 	int computeHeight = 0;

 	if (hasChildren) {
 	    computeWidth = childrenWidth + marginLeft + marginRight;
 	    //computeHeight = childrenHeight + marginTop + marginBottom;
 	    
 	    for (int h = 0; h < rowHeight.length; h++) {
 		if (h > 0) {
 		   computeHeight += verticalSpacing;
 		}
 		computeHeight += rowHeight[h];
 	    }
 	    computeHeight += (marginTop + marginBottom);
  	}
 	
 	//GWT.log("PACK-(: hWidth=" + hWidth + ", hHeight=" + hHeight);
 	//GWT.log("PACK-): computeWidth=" + computeWidth + ", computeHeight=" + computeHeight);
 	
 	
 	//TODO: Analyze it
 	if (hWidth > computeWidth) {
 	   computeWidth = hWidth;
 	}
 	if (hHeight > computeHeight) {
  	   computeHeight = hHeight;
  	}
 	
 	
 	
 	return new Size(computeWidth, computeHeight);
     }    
    
    /**
     * Return true if cell is free
     * @param cells
     * @param column
     * @param row
     * @param columnSpan
     * @param rowSpan
     * @return
     */
    private boolean isFree(Cell[] cells, int column, int row, int columnSpan, int rowSpan) {
	if (cells == null || cells.length == 0) {
	    return true;
	}
	for (Cell c: cells) {
	    if (c == null) {
		return true;
	    }
	    if (column >= c.column && row >= c.row 
		    && column + columnSpan <= c.column + c.colSpan
		    && row + rowSpan <= c.row + c.rowSpan) {
		return false;
	    }
	}
	return true;
    }
    
     

}
