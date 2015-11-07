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

/**
 * Represents 4-side margin.
 */
public class Margin extends Insets {

    /**
     * Creates a new margins instance with 0 values for all sides.
     */
    public Margin() {
	this(0);
    }

    /**
     * Creates a new margins instance.
     * 
     * @param margin the margin value for all 4 sides.
     */
    public Margin(int margin) {
	super(margin, margin, margin, margin);
    }

    public Margin(int top, int right, int bottom, int left) {
	super(top, right, bottom, left);
    }

    public String toString() {
	return "Margin[" + toValuesString() + "]";
    }

    public boolean equals(Object obj) {
	if (obj instanceof Margin) {
	    Margin margin = (Margin) obj;
	    return equals(margin.getTop(), margin.getRight(), margin.getBottom(), margin.getLeft());
	}
	return super.equals(obj);
    }
}
