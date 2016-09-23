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

public class Font extends PathResource {

    
    /**
     * The normal style constant.
     */
    public static final int NORMAL       = 0;

    /**
     * The bold style constant.  This can be combined with the other style
     * constants (except NORMAL) for mixed styles.
     */
    public static final int BOLD        = 1;

    /**
     * The italicized style constant.  This can be combined with the other
     * style constants (except NORMAL) for mixed styles.
     */
    public static final int ITALIC      = 2;

    
    
    private String name;
    
    private int size;
    
    private int style;
    
    
    
    public Font(String name, int size) {
	this(name, size, NORMAL); // By default NORMAL style
    }

    public Font(String name, int size, int style) {
	super();
	this.name = name;
	this.size = size;
	this.style = style;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getStyle() {
        return style;
    }

    /**
     * Indicates whether or not this <code>Font</code> object's style is NORMAL.
     * @return 
     */
    public boolean isNormal() {
        return style == 0;
    }

    /**
     * Indicates whether or not this <code>Font</code> object's style is BOLD.
     * @return 
     */
    public boolean isBold() {
        return (style & BOLD) != 0;
    }

    /**
     * Indicates whether or not this <code>Font</code> object's style is ITALIC.
     */
    public boolean isItalic() {
        return (style & ITALIC) != 0;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + size;
	result = prime * result + style;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Font other = (Font) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (size != other.size)
	    return false;
	if (style != other.style)
	    return false;
	return true;
    }

    public String getKey() {
	return "" + name + ", " + size + ", " + style; 
    }
}
