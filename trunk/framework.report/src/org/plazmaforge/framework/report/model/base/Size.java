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
public class Size implements Serializable {

    private static final long serialVersionUID = -4496469006819991184L;
    

    private Integer width;
    
    private Integer height;

    public Size() {
    }

    public Size(Integer width, Integer height) {
	super();
	this.width = width;
	this.height = height;
    }

    public int getWidth() {
        return intValue(width);
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setWidth(Integer width) {
        this.width = width;
    }
    
    public boolean hasWidth() {
	return width != null;
    }

    public int getHeight() {
        return intValue(height);
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public boolean hasHeight() {
	return height != null;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    public boolean isEmpty() {
	return width == null && height == null;
    }
    
    public Size clone() {
	return new Size(hasWidth() ? getWidth(): null, hasHeight() ? getHeight() : null);
    }
    
    protected int intValue(Integer value) {
	return value == null ? 0 : value;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((height == null) ? 0 : height.hashCode());
	result = prime * result + ((width == null) ? 0 : width.hashCode());
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
	Size other = (Size) obj;
	if (height == null) {
	    if (other.height != null)
		return false;
	} else if (!height.equals(other.height))
	    return false;
	if (width == null) {
	    if (other.width != null)
		return false;
	} else if (!width.equals(other.width))
	    return false;
	return true;
    }
    
    @Override
    public String toString() {
	return "Size[" + toValuesString() + "]";
    }
    
    protected String toValuesString() {
 	return "width=" + width + ", height=" + height;
    }    
}
