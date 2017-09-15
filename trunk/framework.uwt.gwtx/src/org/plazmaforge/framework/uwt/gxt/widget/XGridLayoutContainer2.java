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

import org.plazmaforge.framework.uwt.gxt.layout.XCellData;
import org.plazmaforge.framework.uwt.gxt.layout.XGridData;
import org.plazmaforge.framework.uwt.gxt.layout.XGridLayout;
import org.plazmaforge.framework.uwt.gxt.layout.XGridData.HorizontalAlignment;
import org.plazmaforge.framework.uwt.gxt.layout.XGridData.VerticalAlignment;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.GXTLogConfiguration;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.core.client.util.Util;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.InsertResizeContainer;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * 
 * @author ohapon
 *
 */
public class XGridLayoutContainer2 extends InsertResizeContainer {
    
    private static Logger logger = Logger.getLogger(XGridLayoutContainer2.class.getName());

    private XGridLayout gridLayout;
    
    public XGridLayoutContainer2() {
	this(new XGridLayout());
    }
    public XGridLayoutContainer2(XGridLayout gridLayout) {
	super();
	this.gridLayout = gridLayout;
	setElement(Document.get().createDivElement());
	//getContainerTarget().makePositionable(false);
    }
    
    public XGridLayout getGridLayout() {
	if (gridLayout == null) {
	    gridLayout = new XGridLayout();
	}
        return gridLayout;
    }
    
    public int getColumnCount() {
        return getGridLayout().getColumnCount();
    }

    protected int getHorizontalSpacing() {
        return getGridLayout().getHorizontalSpacing();
    }

    protected int getVerticalSpacing() {
        return getGridLayout().getVerticalSpacing();
    }    
    
    protected int getMarginLeft() {
        return getGridLayout().getMarginLeft();
    }

    protected int getMarginTop() {
        return getGridLayout().getMarginTop();
    }

    protected int getMarginRight() {
        return getGridLayout().getMarginRight();
    }

    protected int getMarginBottom() {
        return getGridLayout().getMarginBottom();
    }

    protected XElement getLayoutContainer() {
	return getElement();
    }

    //@Override
    protected void doLayout1() {
	
	XElement container = getLayoutContainer();
	
	// Get size of container (style or computed)
	Size size = container.getStyleSize();

	if (GXTLogConfiguration.loggingIsEnabled()) {
	    logger.finest(getId() + " doLayout  size: " + size);
	}
	
	int w = size.getWidth(); // - getScrollOffset();
	int h = size.getHeight();

	int styleHeight = Util.parseInt(container.getStyle().getProperty("height"), Style.DEFAULT);
	int styleWidth = Util.parseInt(container.getStyle().getProperty("width"), Style.DEFAULT);

	boolean findWidth = styleWidth == -1;
	boolean findHeight = styleHeight == -1;

	if (GXTLogConfiguration.loggingIsEnabled()) {
	    logger.finest(getId() + " findWidth: " + findWidth + " findHeight: " + findHeight);
	}
	
	int count = getWidgetCount();
	int offsetY = 0;
	for (int i = 0; i < count; i++) {

	    Widget widget = getWidget(i);
	    if (!widget.isVisible()) {
		continue;
	    }
	    widget.addStyleName(CommonStyles.get().positionable());
	    widget.getElement().getStyle().setMargin(0, Unit.PX);

	    boolean component = widget instanceof Component;
	    Component c = null;
	    if (component) {
		c = (Component) widget;
	    }

	    int x = 5;
	    int y = offsetY;
	    if (component) {
		c.setPosition(x, y);
	    } else {
		XElement.as(widget.getElement()).setLeftTop(x, y);
	    }
	    
	    offsetY += (widget.getOffsetHeight() + 5);

	}
	
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void doLayout() {
	
	boolean debug = true;
	
	XElement container = getLayoutContainer();
	
	// Get size of container (style or computed)
	Size size = container.getStyleSize();
	
	if (debug) {
	    dumpContainerSize(size.getWidth(), size.getHeight());
	}

	if (GXTLogConfiguration.loggingIsEnabled()) {
	    logger.finest(getId() + " doLayout  size: " + size);
	}
	
	

	int styleHeight = Util.parseInt(container.getStyle().getProperty("height"), Style.DEFAULT);
	int styleWidth = Util.parseInt(container.getStyle().getProperty("width"), Style.DEFAULT);

	boolean findWidth = styleWidth == -1;
	boolean findHeight = styleHeight == -1;
	
	
	/////////
	
	//int hWidth = 0;
	//int hHeight = 0;
	
	int hWidth = size.getWidth(); // - getScrollOffset();
	int hHeight = size.getHeight();

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

 	
 	// GWT/GXT: DISABLE because fix margin of <body> 
 	//if (fix) {
 	    //areaWidth -= 20;
 	    //areaHeight -= 10;
 	//}
 	
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
 	XCellData[] cells = new XCellData[count];
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
 	    Widget c = getWidget(i);
 	    
 	    // Set absolute position mode
 	    c.addStyleName(CommonStyles.get().positionable());
	    c.getElement().getStyle().setMargin(0, Unit.PX);
 	    
 	    Object ld = c.getLayoutData();
 	    XGridData layoutData = null;
 	    if (ld != null && ld instanceof XGridData) {
 		layoutData = (XGridData) ld;
 	    }
 	    if (layoutData == null) {
 		layoutData = new XGridData();
 		
 		// Set or override Layout Data
 		c.setLayoutData(layoutData);
 	    }
 	
 	    /*
 	    boolean needCalculateWidth = hWidth == -1 || layoutData.getPreferredWidth() == -1 || layoutData.isHorizontalFlex();
 	    boolean needCalculateHeight = hHeight == -1 || layoutData.getPreferredHeight() == -1 || layoutData.isVerticalFlex();
 		    
 	    if (needCalculateWidth || needCalculateHeight) {
 		
 		// First compute size
 		Size preferredSize = computePreferredSize(c);
 		
 		if (needCalculateWidth) {
 		    layoutData.setPreferredWidth(layoutData.getWidth() == -1 ? preferredSize.getWidth(): layoutData.getWidth());
 		}
 		
 		if (needCalculateHeight) {
 		    layoutData.setPreferredHeight(layoutData.getHeight() == -1 ? preferredSize.getHeight() : layoutData.getHeight());
 		}

 	    }*/
 	    
 	    // Initialize preferred size
  	    // 1. by layout data size 
	    if (layoutData.getPreferredWidth() == -1) {
		layoutData.setPreferredWidth(layoutData.getWidth());
	    }
	    if (layoutData.getPreferredHeight() == -1) {
		layoutData.setPreferredHeight(layoutData.getHeight());
	    }

	    // 2. by compute size
	    if (layoutData.getPreferredWidth() == -1 || layoutData.getPreferredHeight() == -1) {

		// First compute size
		Size preferredSize = computePreferredSize(c);
		
		if (layoutData.getPreferredWidth() == -1) {
		    layoutData.setPreferredWidth(preferredSize.getWidth());
		}
		
		if (layoutData.getPreferredHeight() == -1) {
		    layoutData.setPreferredHeight(preferredSize.getHeight());
		}
	    }
 	    
 	    int columnSpan = layoutData.getColSpan();
 	    if (columnSpan <= 0 ) {
 		columnSpan = 1;
 	    }
 	    int rowSpan = layoutData.getRowSpan();
 	    if (rowSpan <= 0 ) {
 		rowSpan = 1;
 	    }
 	    
 	    if (layoutData.isHorizontalFlex()){
 		useFlexColumns = true;
 	    }
 	    if (layoutData.isVerticalFlex()){
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
 	    if (column + columnSpan > columnCount) {
 		columnSpan = columnCount - column;
 	    }
 	    if (rowSpan > 1) {
 		rowCount = rowCount + (rowSpan - 1);
 	    }
 	    
 	    prevColumnSpan = columnSpan;
 	    
 	    
 	    ////
 	    
 	    // Find free space for cell if need
 	    // Previous components can fill current cell
 	    
 	    if  (!isFree(cells, column, row, columnSpan, rowSpan)) {
 		boolean isFindSpace = false;
 		while (!isFindSpace) {
 		    // last column
 		    if  (column == columnCount - 1) {
 			column = 0; // reset column
 			row++; // start new row
 		    } else {
 			column++;
 		    }
 		    isFindSpace = isFree(cells, column, row, columnSpan, rowSpan);
 		}
 		
 		// increment rowCount if need 
 		if (rowCount < (row + 1)) {
 		    rowCount = row + 1;
 		}
 	    }

 	    ////
 	    
 	    XCellData cell = new XCellData();
 	    cell.setColumn(column);
 	    cell.setRow(row);
 	    cell.setColumnSpan(columnSpan);
 	    cell.setRowSpan(rowSpan);
 	    cell.setHorizontalAlign(layoutData.getHorizontalAlign());
 	    cell.setVerticalAlign(layoutData.getVerticalAlign());
 	    cell.setHorizontalFlex(layoutData.isHorizontalFlex());
 	    cell.setVerticalFlex(layoutData.isVerticalFlex());
 	    cell.setSize(new Size(layoutData.getPreferredWidth(), layoutData.getPreferredHeight()));
 	    
 	    cells[i] = cell; 
 	    
 	}
 	
 	// DEBUG
 	if (debug) {
 	    dumpSizes(cells);
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
 	    
 	    // Components
 	    for (int i = 0; i < count; i++) {
 		XCellData cell = cells[i];
 		int ch = 0;
 		if (cell.getRow() == k) {
 		    
 		    int rowSpan = cell.getRowSpan();
 		    ch = cells[i].getSize().getHeight();

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
 		    
 		    if (useFlexRows && cell.isVerticalFlex()) {
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
 	    
 	    // Components
 	    for (int i = 0; i < count; i++) {
 		XCellData cell = cells[i];
 		int cw = 0;
 		if (cell.getColumn() == k) {
 		    
 		    int columnSpan = cell.getColumnSpan();
 		    
 		    cw = cells[i].getSize().getWidth();
 		    

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
 		    
 		    if (useFlexColumns && cell.isHorizontalFlex()) {
 			
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
 	    
 	    XCellData cell = cells[i];
 	    Widget c = getWidget(i);
 	   
 	    int offsetColumn = 0;
 	    for (int k = 0; k < cell.getColumn(); k++) {
 		offsetColumn += columnWidth[k];
 	    }
 	    int offsetRow = 0;
 	    for (int k = 0; k < cell.getRow(); k++) {
 		offsetRow += rowHeight[k];
 	    }
 	    int offsetSpacingColumns = 0; // Previous column spacing
 	    if (horizontalSpacing > 0 && cell.getColumn() > 0) {
 		offsetSpacingColumns = cell.getColumn() * horizontalSpacing; 
 	    }

 	    int offsetSpacingRows = 0; // Previous row spacing
 	    if (verticalSpacing > 0 && cell.getRow() > 0) {
 		offsetSpacingRows = cell.getRow() * verticalSpacing; 
 	    }

 	    int cellX = offsetX + offsetColumn + offsetSpacingColumns;
 	    int cellY = offsetY + offsetRow + offsetSpacingRows;

 	    int x = cellX;
 	    int y = cellY;
 	    
 	    HorizontalAlignment hAlign = cell.getHorizontalAlign();
 	    if (hAlign == null) {
 		hAlign = XGridData.DEFAULT_HORIZONTAL_ALIGN;
 	    }
 	    VerticalAlignment vAlign = cell.getVerticalAlign();
 	    if (vAlign == null) {
 		vAlign = XGridData.DEFAULT_VERTICAL_ALIGN;
 	    }
 	    
 	    int cellWidth = 0;
 	    // Calculate width of cell with column span and horizontal spacing 
 	    for (int k = cell.getColumn(); k < cell.getColumn() + cell.getColumnSpan(); k++) {
 		cellWidth += columnWidth[k];
 		if (k > cell.getColumn()) { 
 		    cellWidth += horizontalSpacing;
 		}
 	    }
 	    
 	    int cellHeight = 0;
 	    // Calculate height of cell with row span and vertical spacing
 	    for (int k = cell.getRow(); k < cell.getRow() + cell.getRowSpan(); k++) {
 		cellHeight += rowHeight[k];
 		if (k > cell.getRow()) { 
 		    cellHeight += verticalSpacing;
 		}
 	    }
 	   

 	    int childWidth = cells[i].getSize().getWidth();
 	    
 	    //LF
 	    if (childWidth == -1) {
 		childWidth = c.getOffsetWidth();
 	    }
 	    
 	    //DEBUG-FIX
 	    if (childWidth == 0) {
 		//componentWidth = 10;
 	    }
 	    
 	    if (childWidth > cellWidth) {
 		childWidth = cellWidth;
 	    }
 	    
 	    
 	    int childHeight = cells[i].getSize().getHeight();
 	    
 	    //LF
 	    if (childHeight == -1) {
 		childHeight = c.getOffsetHeight();
 	    }

 	    //DEBUG-FIX
 	    if (childHeight == 0) {
 		//componentHeight = 10;
 	    }

 	    if (childHeight > cellHeight) {
 		childHeight = cellHeight;
 	    }
 	    
 	    
 	    // HORIZONTAL
 	    if (HorizontalAlignment.FILL.equals(hAlign)) {
 		// Fill align
 		childWidth = cellWidth;

 		// Special fix for last component in row. We have problem with ToolBar in CoolBar
 		if (cell.getColumn() == columnCount - 1 && childWidth > 0 /*&& (c instanceof ToolBar)*/) {
 		    childWidth -= 1;
 		}

 	    } else if (HorizontalAlignment.RIGHT.equals(hAlign)) {
 		// Right align
 		int d = cellWidth - childWidth;
 		if (d < 0) {
 		    d = 0;
 		}
 		x = cellX  + d;
 	    } else if (HorizontalAlignment.CENTER.equals(hAlign)) {
 		// Center align
 		int d = cellWidth - childWidth;
 		if (d < 0) {
 		    d = 0;
 		}
 		x = cellX  + d / 2;
 	    }
 	    
 	    // VERTICAL
 	    if (VerticalAlignment.FILL.equals(vAlign)) {
 		// Fill align
 		childHeight = cellHeight;
 	    } else if (VerticalAlignment.BOTTOM.equals(vAlign)) {
 		// Bottom align
 		int d = cellHeight - childHeight;
 		if (d < 0) {
 		    d = 0;
 		}
 		y = cellY  + d;
 	    } else if (VerticalAlignment.MIDDLE.equals(vAlign)) {
 		// Middle align
 		int d = cellHeight - childHeight;
 		if (d < 0) {
 		    d = 0;
 		}
 		y = cellY  + d / 2;
 	    }

 	    //if (layout) {
 		setPosition(c, x, y);
 		setSize(c, childWidth, childHeight);
 	    //}
 	    
 	    maxWidth = Math.max(maxWidth, childWidth);
 	    maxHeight = Math.max(maxHeight, childHeight);

 	    int w = x - marginLeft + childWidth;
 	    int h = y - marginTop + childHeight;
 	    
 	    // Calculate children area
 	    childrenWidth = Math.max(childrenWidth, w);
 	    childrenHeight = Math.max(childrenHeight, h);
 	}

 	int computeWidth = 0;
 	int computeHeight = 0;

 	if (hasChildren) {
 	    computeWidth = childrenWidth + marginLeft + marginRight;
 	    computeHeight = childrenHeight + marginTop + marginBottom;
 	}
 	
// 	Size computeSize = computeTrim(container, computeWidth, computeHeight);
// 	return computeSize;
 	
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
    private boolean isFree(XCellData[] cells, int column, int row, int columnSpan, int rowSpan) {
	if (cells == null || cells.length == 0) {
	    return true;
	}
	for (XCellData c: cells) {
	    if (c == null) {
		return true;
	    }
	    if (column >= c.getColumn() && row >= c.getRow() 
		    && column + columnSpan <= c.getColumn() + c.getColumnSpan()
		    && row + rowSpan <= c.getRow() + c.getRowSpan()) {
		return false;
	    }
	}
	return true;
    }
    
    protected void setPosition(Widget widget, int x, int y) {
	if (widget == null) {
	    return;
	}
	if (widget instanceof Component) {
	    Component c = (Component) widget;
	    c.setPosition(x, y);
	    return;
	}
	XElement.as(widget.getElement()).setLeftTop(x, y);
    }

    protected void setSize(Widget widget, int width, int height) {
	applyLayout(widget, width, height);
    }
    
    protected Size computePreferredSize(Widget widget) {
	if (widget == null) {
	    return new Size(0, 0);
	}
	
	int styleWidth = Util.parseInt(widget.getElement().getStyle().getProperty("width"), Style.DEFAULT);	
	int styleHeight = Util.parseInt(widget.getElement().getStyle().getProperty("height"), Style.DEFAULT);
	
	int offsetWidth = widget.getOffsetWidth();
	int offsetHeight = widget.getOffsetHeight();	
	
	int width = styleWidth;
	int height = styleHeight;
	
//	if (widget instanceof Grid) {
//	    Grid grid = (Grid) widget;
//	    int columnCount = grid.getColumnModel().getColumnCount();
//	    int columnWidth = 0;
//	    
//	    if (columnCount > 0) {
//		for (int i = 0; i < columnCount; i++) {
//		    columnWidth += grid.getColumnModel().getColumnWidth(i);
//		}
//		
//	    }
//	    
//	    log("Grid: StyleSize: width=" + width + ", height=" + height);
//	    log("Grid: OffsetSize: width=" + widget.getOffsetWidth() + ", height=" + widget.getOffsetHeight());
//	    //log("Grid: StyleSize: width=" + width);
//	}
	
	if (width == -1) {
	    width = computeMinWidth(widget);
	    if (width == -1) {
		width = offsetWidth;
	    }
	}
	if (height == -1) {
	    height = offsetHeight;
	}
	
//	if (width < 20) {
//	    width = 20;
//	}
//	if (height < 20) {
//	    height = 20;
//	}

//	if (widget instanceof Label) {
//	    log("Label: StyleSize: width=" + styleWidth + ", height=" + styleHeight);
//	    log("Label: OffsetSize: width=" + offsetWidth + ", height=" + offsetHeight);
//	}
	
	return new Size(width, height);
    }

    protected int computeMinWidth(Widget widget) {
	if (widget == null) {
	    return -1;
	}
	if (widget instanceof Label) {
	    return widget.getOffsetWidth() + 1;
	}
	if (widget instanceof Grid) {
	    Grid<?> grid = (Grid<?>) widget;
	    return grid.getColumnModel().getTotalWidth();
	}
	if (widget instanceof ToolBar) {
	    return widget.getOffsetWidth() + 10;
	}
	return -1;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // DEBUG
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
//    protected boolean isDebug(BoxComponent component) {
//	if (component == null) {
//	    return false;
//	}
//	Object data = component.getData("debugLayout");
//	if (data == null || !(data instanceof Boolean)) {
//	    return false;
//	}
//	return ((Boolean) data);
//    }
//    
    protected void dumpContainerSize(int width, int height) {
	log("container: width=" + width + ", height=" + height);	
    }
    
    protected void dumpSizes(XCellData[] cells) {
	if (cells == null || cells.length == 0) {
	    log("sizes: empty");
	    return;
	}
	log("sizes:");
	for (int k = 0; k < cells.length; k++) {
	    log("cell[" + k + "]: width=" + cells[k].getSize().getWidth() + ", height=" + cells[k].getSize().getHeight());
	}
    }
    
    protected void dumpColumns(String title, int[] columns) {
	String t = title == null ? "columns" : title;
	if (columns == null || columns.length == 0) {
	    log(t + ": empty");
	    return;
	}
	log(t + ":");
	for (int k = 0; k < columns.length; k++) {
	    log("column[" + k + "]=" + columns[k]);
	}
    }
    
    protected void dumpColumns(int[] columns) {
	dumpColumns(null, columns);
    }
    
    protected void dumpFlexColumns(int[] columns) {
	dumpColumns("flex-columns", columns);
    }    
    
    protected void log(String message){
	GWT.log(message);
    }
}
