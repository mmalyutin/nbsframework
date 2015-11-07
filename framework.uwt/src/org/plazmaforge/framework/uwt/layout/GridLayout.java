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

import org.plazmaforge.framework.uwt.widget.Layout;

/**
 * Grid layout
 * 
 * @author ohapon
 *
 */
public class GridLayout extends Layout {

    
    //////////////////////////////////////////////////////////////////////
    // PROPERTIES
    //////////////////////////////////////////////////////////////////////

    public static final String PROPERTY_COLUMN_COUNT = "columnCount";
    
    
    public static final int DEFAULT_COLUMN_COUNT = 1;
    
    
    
    /**
     * Count of columns
     */
    private int columnCount;


    /**
     * Horizontal element spacing
     */
    private int horizontalSpacing;
    
    /**
     * Vertical element spacing
     */
    private int verticalSpacing;
    
    private boolean fix;
    
    public GridLayout() {
	initialize(DEFAULT_COLUMN_COUNT);
    }

    public GridLayout(int columnCount) {
	initialize(columnCount);
    }
    
    private void initialize(int columnCount) {
	this.columnCount = columnCount;
	this.horizontalSpacing = DEFAULT_SPACING;
	this.verticalSpacing = DEFAULT_SPACING;
	setMargin(DEFAULT_MARGIN);
    }

    public int getColumnCount() {
        return columnCount < 1 ? 1: columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getHorizontalSpacing() {
        return horizontalSpacing < 0 ? 0 : horizontalSpacing;
    }

    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    public int getVerticalSpacing() {
        return verticalSpacing < 0 ? 0 : verticalSpacing;
    }

    public void setVerticalSpacing(int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    public boolean isFix() {
        return fix;
    }

    public void setFix(boolean fix) {
        this.fix = fix;
    }
	
    public void setSpacing(int spacing) {
	setVerticalSpacing(spacing);
	setHorizontalSpacing(spacing);
    }
    
    public void resetSpacing() {
	setSpacing(0);
    }
	
    public boolean isCompatible(Object layoutData) {
	return (layoutData instanceof GridData);
    }

}
