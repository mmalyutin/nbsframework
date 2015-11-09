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

package org.plazmaforge.framework.uwt.swt.adapter.viewer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class SWTTableLayout extends Layout implements ControlListener {

    public enum AutoResize {
	OFF, ALL, LAST
    }
    
    private Table table;

    private Map<TableColumn, ColumnLayoutData> columnDataMap;
    
    private AutoResize autoResize;

    private boolean firstTime = true;
    
    private boolean adjustForScrollBar;
    
    private boolean processing;
    
    private static int COLUMN_TRIM;

    /*
    static {
	if (Util.isWindows()) {
	    COLUMN_TRIM = 4;
	} else if (Util.isMac()) {
	    COLUMN_TRIM = 24;
	} else {
	    COLUMN_TRIM = 3;
	}
    }
    */
     	
    public SWTTableLayout(Table table) {
	this.table = table;
	table.addControlListener(this);
    }

    public AutoResize getAutoResize() {
        return autoResize;
    }

    public void setAutoResize(AutoResize autoResize) {
        this.autoResize = autoResize;
    }
    
    public void controlMoved(ControlEvent e) {
    }

    public void controlResized(ControlEvent e) {
	doLayout();
    }
    
    public void doLayout() {
	if (processing) {
	    return;
	}
	processing = true;
	try {
	    doLayout(table, false);
	} finally {
	    processing = false;
	}
    }
    
    public void layout(Composite c, boolean flush) {
	if (!firstTime) {
	    return;
	}
	doLayout();
	firstTime = false;
    }
    
    public boolean isAutoResizing() {
	return autoResize != null && autoResize != AutoResize.OFF; 
    }
    
    
    public Point computeSize(Composite c, int wHint, int hHint, boolean flush) {
	if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
	    return new Point(wHint, hHint);
	}
	Table table = (Table) c;
	table.setLayout(null);

	// Use native layout algorithm
	Point result = table.computeSize(wHint, hHint, flush);
	table.setLayout(this);
	int width = 0;
	ColumnLayoutData[] columns = getColumnDataArray(table);
	int size = columns.length;
	for (int i = 0; i < size; ++i) {
	    ColumnLayoutData layoutData = (ColumnLayoutData) columns[i];
	    if (layoutData instanceof ColumnPixelData) {
		ColumnPixelData col = (ColumnPixelData) layoutData;
		width += col.width;
		if (col.addTrim) {
		    // width += COLUMN_TRIM;
		}
	    } else if (layoutData instanceof ColumnWeightData) {
		ColumnWeightData col = (ColumnWeightData) layoutData;
		width += col.minimumWidth;
	    } else {
		throw new IllegalStateException("Unknown column layout data");
	    }
	}
	if (width > result.x) {
	    result.x = width;

	}
	return result;

    }
    
    protected void doLayout(Composite c, boolean flush) {

	AutoResize autoResize = getAutoResize();
	if (AutoResize.ALL != autoResize) {
	    return;
	}

	int width = c.getClientArea().width;

	// XXX: Layout is being called with an invalid value
	// the first time it is being called on Linux.
	// This method resets the layout to null,
	// so we run it only when the value is OK.

	if (width <= 1) {
	    return;
	}

	Table table = (Table) c;
	TableColumn[] tableColumns = table.getColumns();
	ColumnLayoutData[] columns = getColumnDataArray(tableColumns);
	int size = columns.length;

	// int size = Math.min(columns.size(), tableColumns.length);

	int[] widths = new int[size];
	int fixedWidth = 0;
	int numberOfWeightColumns = 0;
	int totalWeight = 0;

	// First calculate space occupied by fixed columns.
	for (int i = 0; i < size; i++) {
	    ColumnLayoutData col = columns[i];
	    if (col instanceof ColumnPixelData) {
		int pixels = ((ColumnPixelData) col).width;
		widths[i] = pixels;
		fixedWidth += pixels;
	    } else if (col instanceof ColumnWeightData) {
		ColumnWeightData cw = (ColumnWeightData) col;
		numberOfWeightColumns++;
		int weight = cw.weight;
		totalWeight += weight;
	    } else {
		throw new IllegalStateException("Unknown column layout data");
	    }
	}
	// Do we have columns that have a weight?
	if (numberOfWeightColumns > 0) {

	    // if (adjustForScrollBar && c.getVerticalBar() != null)
	    // width -= c.getVerticalBar().getThumbTrackBounds().width;

	    // Now, distribute the rest
	    // to the columns with weight.
	    int rest = width - fixedWidth;
	    int totalDistributed = 0;
	    for (int i = 0; i < size; i++) {
		ColumnLayoutData col = columns[i];
		if (col instanceof ColumnWeightData) {
		    ColumnWeightData cw = (ColumnWeightData) col;
		    int weight = cw.weight;
		    int pixels = totalWeight == 0 ? 0 : weight * rest
			    / totalWeight;
		    if (pixels < cw.minimumWidth)
			pixels = cw.minimumWidth;
		    totalDistributed += pixels;
		    widths[i] = pixels;
		}
	    }

	    // Distribute any remaining pixels
	    // to columns with weight.
	    int diff = rest - totalDistributed;
	    for (int i = 0; diff > 0; i++) {
		if (i == size)
		    i = 0;
		ColumnLayoutData col = columns[i];
		if (col instanceof ColumnWeightData) {
		    ++widths[i];
		    --diff;
		}
	    }
	}

	for (int i = 0; i < size; i++) {
	    if (tableColumns[i].getWidth() != widths[i])
		tableColumns[i].setWidth(widths[i]);
	}
    }    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected Map<TableColumn, ColumnLayoutData> getColumnDataMap() {
	if (columnDataMap == null) {
	    columnDataMap = new HashMap<TableColumn, ColumnLayoutData>();
	}
	return columnDataMap;
    }
    
    protected void addColumnData(TableColumn column, ColumnLayoutData data) {
	getColumnDataMap().put(column, data);
    }
    
    protected void addColumnData(TableColumn column) {
	ColumnLayoutData data = createColumnData(column);
	getColumnDataMap().put(column, data);
    }
    
    public ColumnLayoutData getColumnData(TableColumn column) {
	ColumnLayoutData data = getColumnDataMap().get(column);
	if (data == null) {
	    data = createColumnData(column);
	    addColumnData(column, data);
	}
	return data;
    }
    
    
    protected ColumnLayoutData createColumnData(TableColumn column) {
	return new ColumnWeightData(column.getWidth(), column.getResizable());
    }

    protected ColumnLayoutData[] getColumnDataArray(Table table) {
	TableColumn[] tableColumns = table.getColumns();
	return getColumnDataArray(tableColumns);
    }
    
    protected ColumnLayoutData[] getColumnDataArray(TableColumn[] tableColumns) {
	int size = tableColumns.length;
	ColumnLayoutData[] columns = new ColumnLayoutData[size];
	for (int i = 0; i < size; i++) {
	    columns[i] = getColumnData(tableColumns[i]);
	}
	return columns;
    }

    public void dispose() {
	table = null;
	if (columnDataMap != null) {
	    columnDataMap.clear();
	}
	columnDataMap = null;
    }
}
