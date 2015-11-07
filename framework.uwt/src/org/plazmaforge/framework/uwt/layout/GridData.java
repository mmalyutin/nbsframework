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

package org.plazmaforge.framework.uwt.layout;

import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.widget.LayoutData;

public class GridData extends LayoutData {

    public static final HorizontalAlign DEFAULT_HORIZONTAL_ALIGN = HorizontalAlign.LEFT;
    
    public static final VerticalAlign DEFAULT_VERTICAL_ALIGN = VerticalAlign.MIDDLE;

    /**
     * Column span 
     */
    private int columnSpan;
    
    /**
     * Row span
     */
    private int rowSpan;
    
    /**
     * Horizontal alignment
     */
    private HorizontalAlign horizontalAlign;
    
    /**
     * Vertical alignment
     */
    private VerticalAlign verticalAlign;
    

    /**
     * Flag to force/grab horizontal space
     */
    private boolean horizontalFlex;
    
    /**
     * Flag to force/grab vertical space
     */
    private boolean verticalFlex;
    
    
    
    public GridData() {
	this.columnSpan = 1;
	this.rowSpan = 1;
	this.horizontalAlign = DEFAULT_HORIZONTAL_ALIGN;
	this.verticalAlign = DEFAULT_VERTICAL_ALIGN;
    }

    public GridData(int columnSpan, int rowSpan) {
	this.columnSpan = columnSpan;
	this.rowSpan = rowSpan;
	this.horizontalAlign = DEFAULT_HORIZONTAL_ALIGN;
	this.verticalAlign = DEFAULT_VERTICAL_ALIGN;
    }

    public GridData(int columnSpan, int rowSpan, HorizontalAlign horizontalAlign, VerticalAlign verticalAlign) {
	this.columnSpan = columnSpan;
	this.rowSpan = rowSpan;
	this.horizontalAlign = horizontalAlign;
	this.verticalAlign = verticalAlign;
    }


    public GridData(int columnSpan, int rowSpan, HorizontalAlign horizontalAlign, VerticalAlign verticalAlign, boolean horizontalFlex, boolean verticalFlex) {
	super();
	this.columnSpan = columnSpan;
	this.rowSpan = rowSpan;
	this.horizontalAlign = horizontalAlign;
	this.verticalAlign = verticalAlign;
	this.horizontalFlex = horizontalFlex;
	this.verticalFlex = verticalFlex;
    }

    public GridData(HorizontalAlign horizontalAlign, VerticalAlign verticalAlign) {
	this.columnSpan = 1;
	this.rowSpan = 1;
	this.horizontalAlign = horizontalAlign;
	this.verticalAlign = verticalAlign;
    }

    public GridData(HorizontalAlign horizontalAlign) {
	this.columnSpan = 1;
	this.rowSpan = 1;
	this.horizontalAlign = horizontalAlign;
	this.verticalAlign = DEFAULT_VERTICAL_ALIGN;
    }

    public GridData(VerticalAlign verticalAlign) {
	this.columnSpan = 1;
	this.rowSpan = 1;
	this.horizontalAlign = DEFAULT_HORIZONTAL_ALIGN;
	this.verticalAlign = verticalAlign;
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

    public HorizontalAlign getHorizontalAlign() {
        return horizontalAlign;
    }

    public void setHorizontalAlign(HorizontalAlign horizontalAlign) {
        this.horizontalAlign = horizontalAlign;
    }

    public VerticalAlign getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(VerticalAlign verticalAlign) {
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

    

}
