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
package org.plazmaforge.framework.report.model.base.grid;

import java.util.Collection;
import java.util.Map;

import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.BorderRegion;

/**
 * @author ohapon
 *
 */
public class GridLayout {

    private Map<String, Border> cellBorders;

    private Map<Integer, BorderRegion> columnBorders;

    private Map<Integer, BorderRegion> rowBorders;

    private int columnsWidth;

    private int rowsHeight;

    private int areaWidth;

    private int areaHeight;

    public GridLayout() {
	super();
    }

    public Map<String, Border> getCellBorders() {
	return cellBorders;
    }

    public void setCellBorders(Map<String, Border> cellBorders) {
	this.cellBorders = cellBorders;
    }

    public Map<Integer, BorderRegion> getColumnBorders() {
	return columnBorders;
    }

    public void setColumnBorders(Map<Integer, BorderRegion> columnBorders) {
	this.columnBorders = columnBorders;
    }

    public Map<Integer, BorderRegion> getRowBorders() {
	return rowBorders;
    }

    public void setRowBorders(Map<Integer, BorderRegion> rowBorders) {
	this.rowBorders = rowBorders;
    }

    public int getColumnsWidth() {
	return columnsWidth;
    }

    public void setColumnsWidth(int columnsWidth) {
	this.columnsWidth = columnsWidth < 0 ? 0 : columnsWidth;
    }

    public int getRowsHeight() {
	return rowsHeight;
    }

    public void setRowsHeight(int rowsHeight) {
	this.rowsHeight = rowsHeight < 0 ? 0 : rowsHeight;
    }

    public int getAreaWidth() {
	return areaWidth;
    }

    public void setAreaWidth(int areaWidth) {
	this.areaWidth = areaWidth;
    }

    public int getAreaHeight() {
	return areaHeight;
    }

    public void setAreaHeight(int areaHeight) {
	this.areaHeight = areaHeight;
    }

    public String getCellKey(int columnIndex, int rowIndex) {
	return "" + columnIndex + ":" + rowIndex;
    }

    ////

    public Border getCellBorder(String cellKey) {
	return cellKey == null ? null : cellBorders.get(cellKey);
    }

    public Border getCellBorder(int columnIndex, int rowIndex) {
	String cellKey = getCellKey(columnIndex, rowIndex);
	return getCellBorder(cellKey);
    }

    public BorderRegion getColumnBorder(int columnIndex) {
	return columnBorders.get(columnIndex);
    }

    public BorderRegion getRowBorder(int rowIndex) {
	return rowBorders.get(rowIndex);
    }

    ////

    public int calculateValue(Map<Integer, BorderRegion> borders) {
	if (borders == null) {
	    return 0;
	}
	int width = 0;
	Collection<BorderRegion> values = borders.values();
	for (BorderRegion value : values) {
	    width += (value.getPrevWidth() + value.getNextWidth());
	}
	return width;
    }

}
