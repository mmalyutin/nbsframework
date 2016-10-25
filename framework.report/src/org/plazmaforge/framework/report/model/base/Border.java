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
    
    private Pen topPen;
    
    private Pen rightPen;
    
    private Pen bottomPen;

    private Pen leftPen;
    
    public Border() {
	super();
    }


    public Border(Pen topPen, Pen rightPen, Pen bottomPen, Pen leftPen) {
	super();
	this.topPen = topPen;
	this.rightPen = rightPen;
	this.bottomPen = bottomPen;
	this.leftPen = leftPen;
    }


    public Pen getTopPen() {
	if (topPen == null) {
	    topPen = new Pen();
	}
        return topPen;
    }

    public void setTopPen(Pen topPen) {
        this.topPen = topPen;
    }
    
    public Pen getRightPen() {
	if (rightPen == null) {
	    rightPen = new Pen();
	}
        return rightPen;
    }

    public void setRightPen(Pen rightPen) {
        this.rightPen = rightPen;
    }

    public Pen getBottomPen() {
	if (bottomPen == null) {
	    bottomPen = new Pen();
	}
        return bottomPen;
    }

    public void setBottomPen(Pen bottomPen) {
        this.bottomPen = bottomPen;
    }

    public Pen getLeftPen() {
	if (leftPen == null) {
	    leftPen = new Pen();
	}
        return leftPen;
    }

    public void setLeftPen(Pen leftPen) {
        this.leftPen = leftPen;
    }
    
    
    ////
    
    protected boolean hasPen(Pen pen) {
	return !isEmpty(pen);
    }
    
    protected boolean isEmpty(Pen pen) {
	return pen == null || pen.isEmpty();
    }
    
    ////

    public boolean hasTopPen() {
	return hasPen(topPen);
    }
    
    public boolean hasRightPen() {
	return hasPen(rightPen);
    }
    
    public boolean hasBottomPen() {
	return hasPen(bottomPen);
    }
    
    public boolean hasLeftPen() {
	return hasPen(leftPen);
    }
    
    public boolean isEmpty() {
	return isEmpty(topPen) && isEmpty(rightPen) && isEmpty(bottomPen) && isEmpty(leftPen);
    }

    @Override
    public String toString() {
	return "Border[topPen=" + topPen + ", rightPen="  + rightPen + ", bottomPen="  + bottomPen + ", leftPen=" + leftPen +  "]";
    }
    
    @Override
    public Border clone() {
	return new Border(topPen, rightPen, bottomPen, leftPen);
    }


    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((bottomPen == null) ? 0 : bottomPen.hashCode());
	result = prime * result + ((leftPen == null) ? 0 : leftPen.hashCode());
	result = prime * result
		+ ((rightPen == null) ? 0 : rightPen.hashCode());
	result = prime * result + ((topPen == null) ? 0 : topPen.hashCode());
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
	if (bottomPen == null) {
	    if (other.bottomPen != null)
		return false;
	} else if (!bottomPen.equals(other.bottomPen))
	    return false;
	if (leftPen == null) {
	    if (other.leftPen != null)
		return false;
	} else if (!leftPen.equals(other.leftPen))
	    return false;
	if (rightPen == null) {
	    if (other.rightPen != null)
		return false;
	} else if (!rightPen.equals(other.rightPen))
	    return false;
	if (topPen == null) {
	    if (other.topPen != null)
		return false;
	} else if (!topPen.equals(other.topPen))
	    return false;
	return true;
    }
    
    
}
