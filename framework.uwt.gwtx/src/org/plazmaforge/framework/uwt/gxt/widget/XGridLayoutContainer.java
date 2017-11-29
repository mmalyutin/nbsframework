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
import org.plazmaforge.framework.uwt.gxt.util.GXTUtils;
import org.plazmaforge.framework.uwt.gxt.layout.XGridData.HorizontalAlignment;
import org.plazmaforge.framework.uwt.gxt.layout.XGridData.VerticalAlignment;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.GXTLogConfiguration;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.InsertResizeContainer;


/**
 * 
 * @author ohapon
 *
 */
public class XGridLayoutContainer extends InsertResizeContainer {
    
    private static Logger logger = Logger.getLogger(XGridLayoutContainer.class.getName());

    private XGridLayout gridLayout;
    
    protected boolean debugMode = true;
    
    public XGridLayoutContainer() {
	this(new XGridLayout());
    }
    public XGridLayoutContainer(XGridLayout gridLayout) {
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
      
    @Override
    protected void doLayout() {
	
	boolean debug = isDebug();
	
	XElement container = getLayoutContainer();
	
	// Get size of container (style or computed)
	Size size = container.getStyleSize();
	
	if (debug) {
	    dumpContainerSize(size.getWidth(), size.getHeight());
	}

	if (GXTLogConfiguration.loggingIsEnabled()) {
	    logger.finest(getId() + " doLayout  size: " + size);
	}
	
	int styleHeight = GXTUtils.getStyleHeight(container);
	int styleWidth = GXTUtils.getStyleWidth(container);

	boolean findWidth = styleWidth == -1;
	boolean findHeight = styleHeight == -1;
	
	/////////
	
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
 	    
 	    // Set absolute position mode
 	    widget.addStyleName(CommonStyles.get().positionable());
	    widget.getElement().getStyle().setMargin(0, Unit.PX);
 	    
 	    Object ld = widget.getLayoutData();
 	    XGridData layoutData = null;
 	    if (ld != null && ld instanceof XGridData) {
 		layoutData = (XGridData) ld;
 	    }
 	    if (layoutData == null) {
 		layoutData = new XGridData();
 		
 		// Set or override Layout Data
 		widget.setLayoutData(layoutData);
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
		Size preferredSize = computePreferredSize(widget);
		
		if (layoutData.getPreferredWidth() == -1) {
		    layoutData.setPreferredWidth(preferredSize.getWidth());
		}
		
		if (layoutData.getPreferredHeight() == -1) {
		    layoutData.setPreferredHeight(preferredSize.getHeight());
		}
	    }
 	    
 	    int colSpan = layoutData.getColSpan();
 	    if (colSpan <= 0 ) {
 		colSpan = 1;
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
 	    cell.preferredSize = new Size(layoutData.getPreferredWidth(), layoutData.getPreferredHeight());
 	    
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

 	    //if (layout) {
 		setPosition(widget, x, y);
 		setSize(widget, widgetWidth, widgetHeight);
 	    //}
 	    
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
 	    computeHeight = childrenHeight + marginTop + marginBottom;
 	}
 	
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
    
    protected void setPosition(Widget widget, int x, int y) {
	if (widget == null) {
	    return;
	}
	//if (widget instanceof TabPanel ){
	//    return;
	//}
	if (widget instanceof Component) {
	    Component c = (Component) widget;
	    c.setPosition(x, y);
	    return;
	}
	XElement.as(widget.getElement()).setLeftTop(x, y);
    }

    protected void setSize(Widget widget, int width, int height) {
	//if (widget instanceof TabPanel ){
	//    return;
	//}
	applyLayout(widget, width, height);
    }
    
    protected Size computePreferredSize(Widget widget) {
	if (widget == null) {
	    return new Size(0, 0);
	}
	
	boolean debug = isDebug();
	
//	if (widget instanceof XSplitPanel) {
//	    debug = true;
//	    
//	    XElement e = ((XSplitPanel) widget).getElement();
//	    Size s = e.getFrameSize();
//	    log("DEBUG: FrameSize: width=" + s.getWidth() + ", height=" + s.getHeight());
//	    
//	}
	
	int styleWidth = GXTUtils.getStyleWidth(widget); 	
	int styleHeight = GXTUtils.getStyleHeight(widget);
	
	int offsetWidth = widget.getOffsetWidth();
	int offsetHeight = widget.getOffsetHeight();	
	
	int width = styleWidth;
	int height = styleHeight;
	
	if (width == -1) {
	    width = GXTUtils.computeMinWidth(widget);
	    if (width == -1) {
		width = offsetWidth;
	    }
	}
	if (height == -1) {
	    height = GXTUtils.computeMinHeight(widget);
	    if (height == -1) {
		height = offsetHeight;
	    }
	}
	
//	if (width < 20) {
//	    width = 20;
//	}
//	if (height < 20) {
//	    height = 20;
//	}

	if (debug) {
	    boolean warningMarker = styleWidth == -1 && styleHeight == -1 && offsetWidth == 0 && offsetHeight == 0;
	    String widgetName = widget.getClass().getSimpleName() + (warningMarker ? " (!)" : "");
	    logDebug("StyleSize : [" + styleWidth + ", " + styleHeight + "], " + widgetName);
	    logDebug("OffsetSize: [" + offsetWidth + ", " + offsetHeight + "], " + widgetName);
	}
	
	return new Size(width, height);
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // DEBUG
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected boolean isDebug() {
	return debugMode;
    }
    
    protected void dumpContainerSize(int width, int height) {
	logDebug("container: width=" + width + ", height=" + height);	
    }
    
    protected void dumpCells(Cell[] cells) {
	if (cells == null || cells.length == 0) {
	    logDebug("\nDump cells: empty");
	    return;
	}
	logDebug("\nDump cells:");
	Cell cell = null;
	for (int k = 0; k < cells.length; k++) {
	    cell = cells[k];
	    logDebug("cell[" + k + "]: [" + cell.column + ", " + cell.row + "], [" + cell.colSpan + ", " + cell.rowSpan + "]"
	    + ", size=[" + cell.preferredSize.getWidth() + ", " + cell.preferredSize.getHeight() + "]");
	}
    }
    
    protected void dumpColumns(String title, int[] columns) {
	String name = title == null ? "columns" : title;
	if (columns == null || columns.length == 0) {
	    logDebug("\nDump " + name + ": empty");
	    return;
	}
	logDebug("\nDump " + name + ":");
	for (int k = 0; k < columns.length; k++) {
	    logDebug("column[" + k + "]: width=" + columns[k]);
	}
    }
    
    protected void dumpColumns(int[] columns) {
	dumpColumns(null, columns);
    }
    
    protected void dumpFlexColumns(int[] columns) {
	dumpColumns("flex-columns", columns);
    }    
    
    protected void logDebug(String message) {
	if (!debugMode || message == null) {
	    return;
	}
	GWT.log(message);
    }
    
    public static class Cell {

	public int column;

	public int row;

	public int colSpan = 1;

	public int rowSpan = 1;

	public HorizontalAlignment horizontalAlign = XGridData.DEFAULT_HORIZONTAL_ALIGN;

	public VerticalAlignment verticalAlign = XGridData.DEFAULT_VERTICAL_ALIGN;

	public boolean horizontalFlex;

	public boolean verticalFlex;

	public Size preferredSize;

    }

}
