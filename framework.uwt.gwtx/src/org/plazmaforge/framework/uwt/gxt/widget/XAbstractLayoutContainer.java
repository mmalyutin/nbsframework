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

import org.plazmaforge.framework.uwt.gxt.layout.XLayout;
import org.plazmaforge.framework.uwt.gxt.layout.XLayoutData;
import org.plazmaforge.framework.uwt.gxt.layout.XLayoutData.HorizontalAlignment;
import org.plazmaforge.framework.uwt.gxt.layout.XLayoutData.VerticalAlignment;
import org.plazmaforge.framework.uwt.gxt.util.GXTUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.Size;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.InsertResizeContainer;

/**
 * 
 * @author ohapon
 *
 */
public abstract class XAbstractLayoutContainer<L extends XLayout> extends InsertResizeContainer implements HasComputeSize {

    protected boolean debugMode;// = true;
    
    private L layout;

    public XAbstractLayoutContainer(L layout) {
	super();
	this.layout = layout;
    }
    
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }    
    
    public L getLayout() {
	if (layout == null) {
	    layout = createLayout();
	}
	return layout;
    }

    protected abstract L createLayout();
    
    protected int getMarginLeft() {
        return getLayout().getMarginLeft();
    }

    protected int getMarginTop() {
        return getLayout().getMarginTop();
    }

    protected int getMarginRight() {
        return getLayout().getMarginRight();
    }

    protected int getMarginBottom() {
	return getLayout().getMarginBottom();
    }

    protected XElement getLayoutContainer() {
	return getElement();
    }

    protected Size getLayoutSize() {
	XElement container = getLayoutContainer();

	// Get size of container (style or computed)
	Size size = container.getStyleSize();
	return size;
    }

    @Override
    protected void doLayout() {
	Size size = getLayoutSize();
	int hWidth = size.getWidth(); // - getScrollOffset();
	int hHeight = size.getHeight();
	computeSize(hWidth, hHeight, true);
    }

    public Size computeSize() {
	Size size = getLayoutSize();
	int hWidth = size.getWidth(); // - getScrollOffset();
	int hHeight = size.getHeight();
	return computeSize(hWidth, hHeight, false);
    }
         
    /**
     * Sets widget position
     * @param widget
     * @param x
     * @param y
     */
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

    /**
     * Sets widget size
     * @param widget
     * @param width
     * @param height
     */
    protected void setSize(Widget widget, int width, int height) {
	applyLayout(widget, width, height);
    }

    protected Size computePreferredSize(Widget widget) {
	if (widget == null) {
	    return new Size(0, 0);
	}

	boolean debug = isDebug();

	// Style size
	int styleWidth = GXTUtils.getStyleWidth(widget);
	int styleHeight = GXTUtils.getStyleHeight(widget);

	// Offset size
	int offsetWidth = GXTUtils.getOffsetWidth(widget);
	int offsetHeight = GXTUtils.getOffsetHeight(widget);

	// Compute size
	int computeWidth = -1;
	int computeHeight = -1;

	// Set size. Style size is priority
	int width = styleWidth;
	int height = styleHeight;

	// if (width == -1) {
	// width = GXTUtils.computeMinWidth(widget);
	// }

	// if (height == -1) {
	// height = GXTUtils.computeMinHeight(widget);
	// }

	// If style width/height is not setting then compute size
	Size computeSize = null;
	if (width == -1 || height == -1) {

	    // Compute size of widget
	    computeSize = GXTUtils.computeSize(styleWidth, styleHeight, widget);
	    if (computeSize != null) {
		computeWidth = computeSize.getWidth();
		computeHeight = computeSize.getHeight();
		if (width == -1) {
		    width = computeWidth;
		}
		if (height == -1) {
		    height = computeHeight;
		}
	    }

	}

	if (width == -1) {
	    width = GXTUtils.computeMinWidth(widget);
	}

	if (height == -1) {
	    height = GXTUtils.computeMinHeight(widget);
	}

	if (debug) {
	    boolean warningMarker = styleWidth == -1 && styleHeight == -1 && offsetWidth == 0 && offsetHeight == 0;
	    String widgetName = widget.getClass().getSimpleName() + (warningMarker ? " (!)" : "");
	    logDebug("StyleSize :  [" + styleWidth + ", " + styleHeight + "], " + widgetName);
	    logDebug("OffsetSize:  [" + offsetWidth + ", " + offsetHeight + "], " + widgetName);
	    logDebug("ComputeSize: [" + (computeSize == null ? "none" : ("" + computeWidth + ", " + computeHeight)) + "], " + widgetName);
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
    
    protected void dumpRows(int[] rows) {
	dumpRows(null, rows);
    }
    
    protected void dumpRows(String title, int[] rows) {
	String name = title == null ? "rows" : title;
	if (rows == null || rows.length == 0) {
	    logDebug("\nDump " + name + ": empty");
	    return;
	}
	logDebug("\nDump " + name + ":");
	for (int k = 0; k < rows.length; k++) {
	    logDebug("row[" + k + "]: height=" + rows[k]);
	}
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

	public HorizontalAlignment horizontalAlign = XLayoutData.DEFAULT_HORIZONTAL_ALIGN;

	public VerticalAlignment verticalAlign = XLayoutData.DEFAULT_VERTICAL_ALIGN;

	public boolean horizontalFlex;

	public boolean verticalFlex;

	public Size preferredSize;

    }
    
}
