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
    public static final int NORMAL      = 0;

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
    
    /**
     * The underline style constant.  This can be combined with the other
     * style constants (except NORMAL) for mixed styles.
     */
    public static final int UNDERLINE   = 4;
    
    /**
     * The strikeout style constant.  This can be combined with the other
     * style constants (except NORMAL) for mixed styles.
     */
    public static final int STRIKEOUT   = 8;

    
    
    private String name;
    
    private int size;
    
    private int style;
    
    private String key;
    
    public Font(String name, int size) {
	this(name, size, NORMAL); // By default NORMAL style
    }

    public Font(String name, int size, int style) {
	super();
	this.name = normalizeName(name);
	this.size = normalizeSize(size);
	this.style = normalizeStyle(style);
	this.key = getKey(this);
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

    /**
     * Indicates whether or not this <code>Font</code> object's style is UNDERLINE.
     */
    public boolean isUnderline() {
        return (style & UNDERLINE) != 0;
    }

    /**
     * Indicates whether or not this <code>Font</code> object's style is STRIKEOUT.
     */
    public boolean isStrikeout() {
        return (style & STRIKEOUT) != 0;
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

    private String getKey(Font font) {
	return (font.isEmptyName() ? "" : (font.getName() + ", " )) + font.getSize() + ", " + font.getStyle(); 
    }
    
    public String getKey() {
	return key;
    }
    
    public String toString() {
	StringBuffer buf = new StringBuffer();
	boolean flag = false;
	buf.append("Font[");
	
	if (name != null && !name.isEmpty() ) {
	    buf.append(name);
	    flag = true;
	}
	if (size > 0) {
	    if (flag) {
		buf.append(", ");
	    }
	    buf.append(size);
	    flag = true;
	}
	if (style != NORMAL) {
	    if (flag) {
		buf.append(", ");
	    }
	    buf.append(getStyleString());
	}
	
	buf.append("]");

	return buf.toString();
    }
    
    private String getStyleString() {
	if (style == NORMAL) {
	    return "";
	}
	StringBuffer buf = new StringBuffer();
	boolean flag = false;
	if (isBold()) {
	    buf.append("bold");
	    flag = true;
	}
	if (isItalic()) {
	    if (flag) {
		buf.append("|");
	    }
	    buf.append("italic");
	    flag = true;
	}
	if (isUnderline()) {
	    if (flag) {
		buf.append("|");
	    }
	    buf.append("underline");
	    flag = true;
	}
	if (isStrikeout()) {
	    if (flag) {
		buf.append("|");
	    }
	    buf.append("strikeout");
	    flag = true;
	}
	return buf.toString();
    }
    
    private String normalizeName(String name) {
	name = normalizeString(name);
	return name == null ? "" : name;
    }

    private int normalizeStyle(int style) {
	return style < 0 ? 0 : style;
    }

    private int normalizeSize(int size) {
	return size < 0 ? 0 : size;
    }
    
    private String normalizeString(String str) {
	if (str == null) {
	    return null;
	}
	str = str.trim();
	return str.isEmpty() ? null : str;
    }
    
    public boolean isIncomplete() {
	// name or size only (style == 0 is complete)
	return isEmptyName() || isEmptySize();
    }

    public boolean isComplete() {
	return !isIncomplete();
    }
    
    public boolean isEmptyName() {
	return name.isEmpty();
    }
    
    public boolean isEmptySize() {
	return size == 0;
    }

    public boolean isEmptyStyle() {
	return style == 0;
    }
    
    public boolean isEmpty() {
	return isEmptyName() && isEmptySize() && isEmptyStyle();
    }
    
}
