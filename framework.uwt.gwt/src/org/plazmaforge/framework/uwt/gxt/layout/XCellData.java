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

package org.plazmaforge.framework.uwt.gxt.layout;

import org.plazmaforge.framework.uwt.gxt.layout.XGridData.HorizontalAlignment;
import org.plazmaforge.framework.uwt.gxt.layout.XGridData.VerticalAlignment;

import com.extjs.gxt.ui.client.util.Size;


public class XCellData {

    private int column;
    
    private int row;
    
    private int columnSpan;
    
    private int rowSpan;

    private HorizontalAlignment horizontalAlign;
    
    private VerticalAlignment verticalAlign;
    
    private boolean horizontalFlex;
    
    private boolean verticalFlex;
    
    private Size size;
    
    
    public XCellData() {
	super();
	columnSpan = 1;
	rowSpan = 1;
	horizontalAlign = XGridData.DEFAULT_HORIZONTAL_ALIGN;
	verticalAlign = XGridData.DEFAULT_VERTICAL_ALIGN;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumnSpan() {
        return columnSpan;
    }

    public void setColumnSpan(int columnSpan) {
        this.columnSpan = columnSpan;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public HorizontalAlignment getHorizontalAlign() {
        return horizontalAlign;
    }

    public void setHorizontalAlign(HorizontalAlignment horizontalAlign) {
        this.horizontalAlign = horizontalAlign;
    }

    public VerticalAlignment getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(VerticalAlignment verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    public boolean isHorizontalFlex() {
        return horizontalFlex;
    }

    public void setHorizontalFlex(boolean horizontalFlex) {
        this.horizontalFlex = horizontalFlex;
    }

    public boolean isVerticalFlex() {
        return verticalFlex;
    }

    public void setVerticalFlex(boolean verticalFlex) {
        this.verticalFlex = verticalFlex;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    

    
    
}
