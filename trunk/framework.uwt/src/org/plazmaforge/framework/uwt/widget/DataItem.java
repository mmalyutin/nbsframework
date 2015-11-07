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
package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.graphics.Image;

/**
 * @author ohapon
 *
 */
public class DataItem implements HasTextImage {

    public static final int DEFAULT_COLUMN_COUNT = 10;
    
    private String text;

    private Image image;
    
    
    public DataItem() {
    }
    
    public DataItem(String text) {
	super();
	this.text = text;
    }

    public DataItem(Image image) {
	super();
	this.image = image;
    }

    public DataItem(String text, Image image) {
	super();
	this.text = text;
	this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImage(String path) {
   	doGetImage().setPath(path);
    }
    
    protected Image doGetImage() {
	if (image == null) {
	    image = new Image();
	}
	return image;
    }

}
