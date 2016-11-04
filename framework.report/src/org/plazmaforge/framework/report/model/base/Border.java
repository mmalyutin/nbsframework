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

package org.plazmaforge.framework.report.model.base;

import java.io.Serializable;

/**
 * 
 * @author ohapon
 *
 */
public class Border implements Serializable  {

    private static final long serialVersionUID = -9119801104835934766L;
    
    public static final Border NONE = new NoneBorder();
    
    private Pen top;
    
    private Pen right;
    
    private Pen bottom;

    private Pen left;
    
    public Border() {
	super();
    }

    public Border(Pen pen) {
	this(pen, pen, pen, pen);
    }

    public Border(Pen top, Pen right, Pen bottom, Pen left) {
	super();
	this.top = clonePen(top);
	this.right = clonePen(right);
	this.bottom = clonePen(bottom);
	this.left = clonePen(left);
    }


    public Pen getTop() {
	if (top == null) {
	    top = new Pen();
	}
        return top;
    }

    public void setTop(Pen top) {
        this.top = top;
    }
    
    public Pen getRight() {
	if (right == null) {
	    right = new Pen();
	}
        return right;
    }

    public void setRight(Pen right) {
        this.right = right;
    }

    public Pen getBottom() {
	if (bottom == null) {
	    bottom = new Pen();
	}
        return bottom;
    }

    public void setBottom(Pen bottom) {
        this.bottom = bottom;
    }

    public Pen getLeft() {
	if (left == null) {
	    left = new Pen();
	}
        return left;
    }

    public void setLeft(Pen left) {
        this.left = left;
    }
    
    
    ////
    
    protected boolean hasPen(Pen pen) {
	return pen != null;
    }
    
    protected boolean isEmpty(Pen pen) {
	return pen == null || pen.isEmpty();
    }
    
    ////

    public boolean hasTop() {
	return hasPen(top);
    }
    
    public boolean hasRight() {
	return hasPen(right);
    }
    
    public boolean hasBottom() {
	return hasPen(bottom);
    }
    
    public boolean hasLeft() {
	return hasPen(left);
    }
    
    public boolean isEmpty() {
	return isEmpty(top) && isEmpty(right) && isEmpty(bottom) && isEmpty(left);
    }

    @Override
    public String toString() {
	if (isNormalize()) {
	    return "Border[" + left +  "]";
	}
	return "Border[top=" + top + ", right="  + right + ", bottom="  + bottom + ", left=" + left +  "]";
    }
    
    @Override
    public Border clone() {
	return new Border(clonePen(top), clonePen(right), clonePen(bottom), clonePen(left));
    }


    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((bottom == null) ? 0 : bottom.hashCode());
	result = prime * result + ((left == null) ? 0 : left.hashCode());
	result = prime * result
		+ ((right == null) ? 0 : right.hashCode());
	result = prime * result + ((top == null) ? 0 : top.hashCode());
	return result;
    }


    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Border other = (Border) obj;
	if (bottom == null) {
	    if (other.bottom != null)
		return false;
	} else if (!bottom.equals(other.bottom))
	    return false;
	if (left == null) {
	    if (other.left != null)
		return false;
	} else if (!left.equals(other.left))
	    return false;
	if (right == null) {
	    if (other.right != null)
		return false;
	} else if (!right.equals(other.right))
	    return false;
	if (top == null) {
	    if (other.top != null)
		return false;
	} else if (!top.equals(other.top))
	    return false;
	return true;
    }

    // Temporarily method
    public static Pen clonePen(Pen pen) {
	return (pen == null || pen == Pen.NONE) ? pen : pen.clone();
    }

    // Temporarily method
    public static Border cloneBorder(Border border) {
	return (border == null || border == Border.NONE) ? border : border.clone();
    }
    
    public boolean isNormalize() {
	if (top == null && right == null && bottom == null && left == null) {
	    return true;
	}
	if (top == null || right == null || bottom == null || left == null) {
	    return false;
	}
	return top.equals(right) && right.equals(bottom);
    }    
    
    private static final class NoneBorder extends Border {

	private static final long serialVersionUID = 5955160718224198038L;

	private final Pen pen;
	
	private NoneBorder() {
	    super();
	    pen = Pen.NONE;
	}

	@Override
	public Pen getTop() {
	    return pen;
	}

	@Override
	public void setTop(Pen top) {
	}

	@Override
	public Pen getRight() {
	    return pen;
	}

	@Override
	public void setRight(Pen right) {
	}

	@Override
	public Pen getBottom() {
	    return pen;
	}

	@Override
	public void setBottom(Pen bottom) {}

	@Override
	public Pen getLeft() {
	    return pen;
	}

	@Override
	public void setLeft(Pen left) {}	    
	    
	@Override
	public boolean hasTop() {
	    return true;
	}

	@Override
	public boolean hasRight() {
	    return true;
	}
	    
	@Override
	public boolean hasBottom() {
	    return true;
	}

	@Override
	public boolean hasLeft() {
	    return true;
	}
	
	@Override
	public boolean isEmpty() {
	    return true;
	}

	@Override
	public String toString() {
	    return "Border[none]";
	}
	    
	@Override
	public Border clone() {
	    return this;
	}
	
	@Override
	public boolean isNormalize() {
	    return true;
	}
	
    }
}
