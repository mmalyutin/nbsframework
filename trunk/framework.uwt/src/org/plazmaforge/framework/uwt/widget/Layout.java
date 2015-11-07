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

package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.UIObject;

public abstract class Layout extends UIObject {

    
    //////////////////////////////////////////////////////////////////////
    // PROPERTIES
    //////////////////////////////////////////////////////////////////////

    public static final String PROPERTY_MARGIN_LEFT = "marginLeft";
    public static final String PROPERTY_MARGIN_TOP = "marginTop";
    public static final String PROPERTY_MARGIN_RIGHT = "marginRight";
    public static final String PROPERTY_MARGIN_BOTTOM = "marginBottom";
    public static final String PROPERTY_SPACING = "spacing";

    
    
    public static final int DEFAULT_SPACING = 5;
    
    public static final int DEFAULT_MARGIN = 5;
    
    /**
     * Owner of Layout
     */
    private Composite owner;
    
    
    private int marginLeft;
    
    private int marginTop;
    
    private int marginRight;
    
    private int marginBottom;

    private int spacing;
    
    
    
    public Layout() {
	super();
    }

    public Composite getOwner() {
        return owner;
    }

    public void setOwner(Composite owner) {
        this.owner = owner;
    }

    public int getMarginLeft() {
        return marginLeft < 0 ? 0 : marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        fireChangeProperty(PROPERTY_MARGIN_LEFT, marginLeft);
    }

    public int getMarginTop() {
        return marginTop < 0 ? 0 : marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
        fireChangeProperty(PROPERTY_MARGIN_TOP, marginTop);
    }

    public int getMarginRight() {
        return marginRight < 0 ? 0 : marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        fireChangeProperty(PROPERTY_MARGIN_RIGHT, marginRight);
    }

    public int getMarginBottom() {
        return marginBottom < 0 ? 0 : marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        fireChangeProperty(PROPERTY_MARGIN_BOTTOM, marginBottom);
    }

    public int getSpacing() {
        return spacing < 0 ? 0 : spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
        fireChangeProperty(PROPERTY_SPACING, spacing);
    }
    
    /**
     * Set all margin values (left, top, right, bottom)
     * @param margin
     */
    public void setMargin(int margin) {
	setMarginLeft(margin);
	setMarginTop(margin);
	setMarginRight(margin);
	setMarginBottom(margin);
    }
    
    /**
     * Reset all margin values (set 0) 
     */
    public void resetMargin() {
	setMargin(0);
    }

    /**
     * Set margin width (left, right)
     * @param margin
     */
    public void setMarginWidth(int margin) {
	setMarginLeft(margin);
	setMarginRight(margin);
    }
    

    /**
     * Set margin height (top, bottom)
     * @param margin
     */
    public void setMarginHeight(int margin) {
	setMarginTop(margin);
	setMarginBottom(margin);
    }

    public abstract boolean isCompatible(Object layoutData);
}
