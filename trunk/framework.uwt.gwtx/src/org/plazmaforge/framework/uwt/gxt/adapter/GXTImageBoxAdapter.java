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

package org.plazmaforge.framework.uwt.gxt.adapter;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.ImageBox;

import com.google.gwt.resources.client.ImageResource;

/**
 * 
 * @author ohapon
 *
 */
public class GXTImageBoxAdapter extends GXTControlAdapter {
    
    public Object createDelegate(UIElement parent, UIElement element) {
	//com.sencha.gxt.widget.core.client.Status - old implementation
	com.google.gwt.user.client.ui.Image xImageBox = new com.google.gwt.user.client.ui.Image();
	ImageBox imageBox = (ImageBox) element;
	
	// Get image
	ImageResource xImage = createImage(element, imageBox.getImage());
	if (xImage != null) {
	    xImageBox.setResource(xImage);
	}
	
	addChild(getContent(parent.getDelegate()), xImageBox, element);
	return xImageBox;
    }

    protected com.google.gwt.user.client.ui.Image getImageBox(Object delegate) {
	return (com.google.gwt.user.client.ui.Image) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	com.google.gwt.user.client.ui.Image xImageBox = getImageBox(element.getDelegate());
	if (xImageBox == null) {
	    return;
	}
	if (Button.PROPERTY_IMAGE.equals(name)) {
	    ImageResource xImage = createImage(element, asImage(value));
	    if (xImage != null) {
		xImageBox.setResource(xImage);
	    }
	    return;
	} else if (Button.PROPERTY_IMAGE_PATH.equals(name)) {
	    ImageResource xImage = createImage(element, asString(value));
	    if (xImage != null) {
		xImageBox.setResource(xImage);
	    }
	    return;
	}
	super.setProperty(element, name, value);
	
    }

}
