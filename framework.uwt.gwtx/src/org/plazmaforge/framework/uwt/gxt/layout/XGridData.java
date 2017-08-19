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



public class XGridData extends XLayoutData {

    /**
     * Horizontal alignment enumeration.
     */
    public enum HorizontalAlignment {
      LEFT, CENTER, RIGHT, FILL
    }

    /**
     * Vertical alignment enumerations.
     */
    public enum VerticalAlignment {
      TOP, MIDDLE, BOTTOM, FILL;
    }

    
    public static HorizontalAlignment DEFAULT_HORIZONTAL_ALIGN = HorizontalAlignment.LEFT;
    
    public static VerticalAlignment DEFAULT_VERTICAL_ALIGN = VerticalAlignment.MIDDLE;  
    

    
    
    private int horizontalSpan;
    
    private int verticalSpan;

    
    private HorizontalAlignment horizontalAlign;
    
    private VerticalAlignment verticalAlign;

    private boolean horizontalFlex;
    
    private boolean verticalFlex;
    
    private boolean forceComputeSize;
    
    
    public XGridData() {
	super();
	horizontalSpan = 1;
	verticalSpan = 1;
	horizontalAlign = DEFAULT_HORIZONTAL_ALIGN;
	verticalAlign = DEFAULT_VERTICAL_ALIGN;
    }

    public int getHorizontalSpan() {
        return horizontalSpan;
    }

    public void setHorizontalSpan(int horizontalSpan) {
        this.horizontalSpan = horizontalSpan;
    }

    public int getVerticalSpan() {
        return verticalSpan;
    }

    public void setVerticalSpan(int verticalSpan) {
        this.verticalSpan = verticalSpan;
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

    
    public boolean isFreeWidth() {
	return (!isHorizontalFlex() && !HorizontalAlignment.FILL.equals(horizontalAlign));
    }

    public boolean isFreeHeight() {
	return (!isVerticalFlex() && !VerticalAlignment.FILL.equals(verticalAlign));
    }

    ////
    
    public boolean isForceComputeSize() {
        return forceComputeSize;
    }

    public void setForceComputeSize(boolean forceComputeSize) {
        this.forceComputeSize = forceComputeSize;
    }
    
    
}
