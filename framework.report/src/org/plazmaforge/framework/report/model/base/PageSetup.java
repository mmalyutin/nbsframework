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

//    public int getWidth() {
//        return width;
//    }
//
//    public void setWidth(int width) {
//        this.width = width;
//    }
//
//    public int getHeight() {
//        return height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }

    public Size getSize() {
	if (size == null) {
	    size = new Size();
	    size.setWidth(595);  //210
	    size.setHeight(842); //297
	}
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public boolean hasSize() {
	return size != null;
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
    
    public boolean hasMargin() {
	return margin != null && !margin.isEmpty();
    }
    
    
}
