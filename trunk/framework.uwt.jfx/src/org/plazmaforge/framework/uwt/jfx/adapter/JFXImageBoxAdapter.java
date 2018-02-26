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

package org.plazmaforge.framework.uwt.jfx.adapter;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.ImageBox;
import org.plazmaforge.framework.uwt.widget.Label;

/**
 * 
 * @author ohapon
 *
 */
public class JFXImageBoxAdapter extends JFXControlAdapter {


    public Object createDelegate(UIElement parent, UIElement element) {
	javafx.scene.Parent xParent = (javafx.scene.Parent) getContent(parent.getDelegate());
	
	ImageBox imageBox = (ImageBox) element;
	
	javafx.scene.image.ImageView xImageBox = new javafx.scene.image.ImageView();
	
	// Get image
	javafx.scene.image.Image xImage = createImage(imageBox, imageBox.getImage());
	if (xImage != null) {
	    xImageBox.setImage(xImage);
	}		
		
	imageBox.resetInitProperty(ImageBox.PROPERTY_ICON);
	imageBox.resetInitProperty(ImageBox.PROPERTY_ICON_PATH);
	
	addChild(xParent, xImageBox, element);
	return xImageBox;
    }
    
    

    protected javafx.scene.image.ImageView asImageBox(Object delegate) {
	return (javafx.scene.image.ImageView) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	javafx.scene.image.ImageView xImageBox = asImageBox(element.getDelegate());
	if (xImageBox == null) {
	    return;
	}
	if (Label.PROPERTY_IMAGE.equals(name)) {
	    javafx.scene.image.Image xImage = createImage(element, (Image) value);
	    if (xImage != null) {
		xImageBox.setImage(xImage);
	    }
	    return;
	} else if (Label.PROPERTY_IMAGE_PATH.equals(name)) {
	    javafx.scene.image.Image xImage = createImage(element, (String) value);
	    if (xImage != null) {
		xImageBox.setImage(xImage);
	    }
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }

}
