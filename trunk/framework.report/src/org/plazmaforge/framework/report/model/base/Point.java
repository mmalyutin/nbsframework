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
public class Point implements Serializable {

    private static final long serialVersionUID = -2491054266272038099L;
    
    private Integer x;
    
    private Integer y;

    public Point() {
    }

    public Point(Integer x, Integer y) {
	this.x = x;
	this.y = y;
    }

    public int getX() {
        return intValue(x);
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public void setX(Integer x) {
        this.x = x;
    }
    
    public boolean hasX() {
        return x != null;
    }
    
    public int getY() {
        return intValue(y);
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setY(Integer y) {
        this.y = y;
    }
    
    public boolean hasY() {
        return y != null;
    }
    
    public boolean isEmpty() {
  	return x == null && y == null;
    }
    
    public Point clone() {
	return new Point(hasX() ? getX(): null, hasY() ? getY() : null);
    }
    

    protected int intValue(Integer value) {
	return value == null ? 0 : value;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((x == null) ? 0 : x.hashCode());
	result = prime * result + ((y == null) ? 0 : y.hashCode());
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
	Point other = (Point) obj;
	if (x == null) {
	    if (other.x != null)
		return false;
	} else if (!x.equals(other.x))
	    return false;
	if (y == null) {
	    if (other.y != null)
		return false;
	} else if (!y.equals(other.y))
	    return false;
	return true;
    }
    
    
    @Override
    public String toString() {
	return "Point[" + toValuesString() + "]";
    }
    
    protected String toValuesString() {
 	return "x=" + x + ", y=" + y;
    }       
}
