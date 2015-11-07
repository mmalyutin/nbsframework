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
 * Instances of this class represent a rectangle's size.
 * 
 * @author ohapon
 *
 */
public class Size {

    
    /**
     * The width.
     */
    private int width;

    /**
     * The height.
     */
    private int height;
    
    /**
     * Creates a new size instance.
     * 
     * @param width the width
     * @param height the height
     */
    public Size(int width, int height) {
      this.width = width;
      this.height = height;
    }

    public String toString() {
      return "Size[width=" + width + ", height=" + height  + "]";
    }

    public boolean equals(Object obj) {
      if (obj instanceof Size) {
        Size s = (Size) obj;
        if (width == s.width && height == s.height) {
          return true;
        }
        return false;
      }
      return super.equals(obj);
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
