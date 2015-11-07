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
 * Instances of this class represent places on the (x, y) coordinate plane.
 * 
 * @author ohapon
 *
 */
public class Point {

    
    /**
     * The x coordinate of the point
     */
    private int x;

    /**
     * The y coordinate of the point
     */
    private int y;

    
    
    public Point() {
    }
    
    
    public Point(int x, int y) {
	super();
	this.x = x;
	this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public String toString() {
	return "Point[x=" + x + ", y=" + y + "]";
    }

    public boolean equals(Object obj) {
	if (obj instanceof Point) {
	    Point p = (Point) obj;
	    if (x == p.x && y == p.y) {
		return true;
	    }
	    return false;
	}
	return super.equals(obj);
    }
    
}
