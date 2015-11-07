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
package org.plazmaforge.framework.report.model.base;

import java.io.Serializable;


/**
 * @author ohapon
 *
 */
public class Insets implements Serializable {

    
    private static final long serialVersionUID = -402418488413449031L;
    

    /**
     * The top value.
     */
    private Integer top;

    /**
     * The right value.
     */
    private Integer right;

    /**
     * The bottom value.
     */
    private Integer bottom;

    /**
     * The left value.
     */
    private Integer left;
    

    public Insets() {
    }

    public Insets(Integer top, Integer right, Integer bottom, Integer left) {
	this.top = top;
	this.right = right;
	this.bottom = bottom;
	this.left = left;
    }
    
    public int getTop() {
        return intValue(top);
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public boolean hasTop() {
        return top != null;
    }

    public int getRight() {
        return intValue(right);
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }
    
    public boolean hasRight() {
        return right != null;
    }

    public int getBottom() {
        return intValue(bottom);
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public void setBottom(Integer bottom) {
        this.bottom = bottom;
    }
    
    public boolean hasBottom() {
        return bottom != null;
    }

    public int getLeft() {
        return intValue(left);
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean isEmpty() {
	return top == null && right == null && bottom == null && left == null;
    }
    
    public void setValue(Integer value) {
	setTop(value);
	setRight(value);
	setBottom(value);
	setLeft(value);
    }
    
    public void merge(Insets insets) {
	if (insets == null || insets.isEmpty()) {
	    return;
	}
	if (insets.hasTop()) {
	    setTop(insets.getTop());
	}
	if (insets.hasRight()) {
	    setRight(insets.getRight());
	}
	if (insets.hasBottom()) {
	    setBottom(insets.getBottom());
	}
	if (insets.hasLeft()) {
	    setLeft(insets.getLeft());
	}
    }
    
    public String toString() {
	return "Insets[" + toValuesString() + "]";
    }

    protected String toValuesString() {
 	return "top=" + top + ", right=" + right +  ", bottom=" + bottom + ", left=" + left;
    }
    
    protected int intValue(Integer value) {
	return value == null ? 0 : value;
    }
    
    public Insets clone() {
   	return new Insets(
   		hasTop() ? getTop() : null,
   		hasRight() ? getRight() : null,
   		hasBottom() ? getBottom() : null,
   		hasLeft() ? getLeft() : null);
       }

    
}
