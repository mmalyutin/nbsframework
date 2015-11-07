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
 * Represents an area in a coordinate system.
 * 
 * @author ohapon
 *
 */
public class Rectangle {

    /**
     * The x coordinate.
     */
    private int x;

    /**
     * The y coordinate.
     */
    private int y;

    /**
     * The width of the rectangle.
     */
    private int width;

    /**
     * The height of the rectangle.
     */
    private int height;
    
    
    /**
     * Create a new rectangle instance.
     */
    public Rectangle() {

    }

    /**
     * Creates a new rectangle instance.
     * 
     * @param x the x value
     * @param y the y value
     * @param width the rectangle's width
     * @param height the rectangle's height
     */
    public Rectangle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    /**
     * Returns true if the point is within the rectangle's region.
     * 
     * @param x the x coordinate value
     * @param y the y coordinate value
     * @return true if xy is contained within the rectangle
     */
    public boolean contains(int x, int y) {
      return (x >= this.x) && (y >= this.y) && ((x - this.x) < width) && ((y - this.y) < height);
    }

    /**
     * Returns true if the point is within the rectangle.
     * 
     * @param p the point
     * @return true if the point is contained within the rectangle
     */
    public boolean contains(Point p) {
	return contains(p.getX(), p.getY());
    }

    public String toString() {
	return "Rectangle[x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    
    
}
