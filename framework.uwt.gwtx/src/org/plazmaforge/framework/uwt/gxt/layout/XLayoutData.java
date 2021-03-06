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

package org.plazmaforge.framework.uwt.gxt.layout;


/**
 * 
 * @author ohapon
 *
 */
public abstract class XLayoutData {

    /**
     * Horizontal alignment enumeration.
     */
    public enum HorizontalAlignment {
      LEFT, CENTER, RIGHT, FILL
    }

    /**
     * Vertical alignment enumerations.
     */
    public enum VerticalAlignment {
      TOP, MIDDLE, BOTTOM, FILL;
    }

    
    public static HorizontalAlignment DEFAULT_HORIZONTAL_ALIGN = HorizontalAlignment.LEFT;
    
    public static VerticalAlignment DEFAULT_VERTICAL_ALIGN = VerticalAlignment.MIDDLE;  
    
    
    private int width = -1;
    
    private int height = -1;

    private int preferredWidth = -1;
    
    private int preferredHeight = -1;


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
   
    public int getPreferredWidth() {
        return preferredWidth;
    }

    public void setPreferredWidth(int preferredWidth) {
        this.preferredWidth = preferredWidth;
    }

    public int getPreferredHeight() {
        return preferredHeight;
    }

    public void setPreferredHeight(int preferredHeight) {
        this.preferredHeight = preferredHeight;
    }

    /**
     * Return true if width of component is free (can change)
     * @return
     */
    public abstract boolean isFreeWidth();

    /**
     * Return true if height of component is free (can change)
     * @return
     */
    public abstract boolean isFreeHeight();


    /**
     * Return true if size of component is free (can change) 
     * @return
     */
    public boolean isFreeSize() {
	return isFreeWidth() && isFreeHeight(); 
    }
    
    
}
