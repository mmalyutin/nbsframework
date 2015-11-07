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

package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.graphics.Image;

public class ImageBox extends Control implements HasImage {

    private Image image;
    
    public ImageBox() {
    }

    public ImageBox(Image image) {
	super();
	this.image = image;
    }

    public ImageBox(String path) {
	super();
	doGetImage().setPath(path);
    }
    
    
    @Override
    public void setImage(Image image) {
	this.image = image;
	fireChangeProperty(PROPERTY_IMAGE, image);
	
    }
    
    public void setImage(String path) {
	setImagePath(path);
    }
    
    public void setImagePath(String path) {
	doGetImage().setPath(path);
	fireChangeProperty(PROPERTY_IMAGE_PATH, path);
    }
    
    protected Image doGetImage() {
	if (image == null) {
	    image = new Image();
	}
	return image;
    }

    @Override
    public Image getImage() {
	return image;
    }

}
