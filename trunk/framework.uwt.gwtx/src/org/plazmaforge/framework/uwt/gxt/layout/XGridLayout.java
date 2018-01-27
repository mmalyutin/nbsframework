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

/**
 * 
 * @author ohapon
 *
 */
public class XGridLayout extends XLayout {

    private int columnCount = 1;
    
    private int horizontalSpacing;
    
    private int verticalSpacing;
     
    private boolean fix;
    
    public XGridLayout() {
	super();
    }

    public XGridLayout(int columnCount) {
	super();
	this.columnCount = columnCount;
    }
    
    public int getColumnCount() {
        return columnCount < 1 ? 1: columnCount;
    }
    
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getHorizontalSpacing() {
        return horizontalSpacing;
    }

    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    public int getVerticalSpacing() {
        return verticalSpacing;
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


    
}
