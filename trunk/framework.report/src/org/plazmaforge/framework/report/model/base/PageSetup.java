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

/**
 * 
 */
package org.plazmaforge.framework.report.model.base;


/**
 * @author ohapon
 *
 */
public class PageSetup {
    
    
    public static final PageFormat DEFAULT_PAGE_FORMAT = PageFormat.A4;
    
    

    private String format;
    
    private Size size;

    private Margin margin;
    
    public PageSetup() {
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Size getSize() {
	if (size == null) {
	    size = new Size();
	    
	    //size.setWidth(595);  //210
	    //size.setHeight(842); //297
	    
	    size.setWidth((int) PageFormat.toPT(DEFAULT_PAGE_FORMAT.getWidth()));   //210
	    size.setHeight((int) PageFormat.toPT(DEFAULT_PAGE_FORMAT.getHeight())); //297
	}
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setSize(Integer width, Integer height) {
        this.size = new Size(width, height);
    }
    
    public boolean hasSize() {
	return size != null;
    }

    public boolean isEmptySize() {
	return size == null || size.isEmpty();
    }
    
    public Margin getMargin() {
	if (margin == null) {
	    margin = new Margin();
	}
        return margin;
    }

    public void setMargin(Margin margin) {
        this.margin = margin;
    }

    public void setMargin(Integer margin) {
        this.margin = new Margin(margin);
    }

    public void setMargin(Integer top, Integer right, Integer bottom, Integer left) {
        this.margin = new Margin(top, right, bottom, left);
    }
    
    public boolean hasMargin() {
	return margin != null && !margin.isEmpty();
    }

    public boolean isEmptyMargin() {
	return margin == null || margin.isEmpty();
    }
    
    public boolean isEmpty() {
	return  getFormat() == null && isEmptySize() && isEmptyMargin();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((format == null) ? 0 : format.hashCode());
	result = prime * result + ((margin == null) ? 0 : margin.hashCode());
	result = prime * result + ((size == null) ? 0 : size.hashCode());
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
	PageSetup other = (PageSetup) obj;
	if (format == null) {
	    if (other.format != null)
		return false;
	} else if (!format.equals(other.format))
	    return false;
	if (margin == null) {
	    if (other.margin != null)
		return false;
	} else if (!margin.equals(other.margin))
	    return false;
	if (size == null) {
	    if (other.size != null)
		return false;
	} else if (!size.equals(other.size))
	    return false;
	return true;
    }
    
    @Override
    public String toString() {
  	StringBuilder b = new StringBuilder();
  	b.append("PageSetup[");
  	b.append("format=" + format);
  	b.append(", size=" + size);
  	b.append(", margin=" + margin);
  	b.append("]");
  	return b.toString();
    }
}
