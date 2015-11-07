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

package org.plazmaforge.framework.uwt.graphics;

public class Insets {
    
  
    /**
     * The top value.
     */
    private int top;

    /**
     * The right value.
     */
    private int right;

    /**
     * The bottom value.
     */
    private int bottom;

    /**
     * The left value.
     */
    private int left;
    
    

    public Insets() {
    }

    public Insets(int top, int right, int bottom, int left) {
      this.top = top;
      this.right = right;
      this.bottom = bottom;
      this.left = left;
    }


    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public String toString() {
	return "Insets[" + toValuesString() + "]";
    }

    protected String toValuesString() {
 	return "top=" + top + ", right=" + right +  ", bottom=" + bottom + ", left=" + left;
    }
    
    public boolean equals(Object obj) {
	if (obj instanceof Insets) {
	    Insets insets = (Insets) obj;
	    return equals(insets.top, insets.right, insets.bottom, insets.left);
	}
	return super.equals(obj);
    }
    
    protected boolean equals(int top, int right, int bottom, int left) {
	return this.top == top && this.right == right && this.bottom == bottom && this.left == left;
    }
    
    public void inc(Insets insets) {
	if (insets == null) {
	    return;
	}
	top += insets.top;
	left += insets.left;
	right += insets.right;
	bottom += insets.bottom;
    }
    
    public void dec(Insets insets) {
	if (insets == null) {
	    return;
	}
	top -= insets.top;
	left -= insets.left;
	right -= insets.right;
	bottom -= insets.bottom;
    }
    
}
