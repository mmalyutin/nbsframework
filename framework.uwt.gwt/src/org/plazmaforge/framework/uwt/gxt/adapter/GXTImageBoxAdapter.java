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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.gxt.widget.XImageBox;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.ImageBox;

import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class GXTImageBoxAdapter extends GXTControlAdapter {
    
    public Object createDelegate(UIObject parent, UIObject element) {
	XImageBox xImageBox = new XImageBox();
	ImageBox imageBox = (ImageBox) element;
	
	// Get image
	AbstractImagePrototype xImage = createImage(element, imageBox.getImage());
	if (xImage != null) {
	    xImageBox.setIcon(xImage);
	}
	
	addToParent(getContent(parent.getDelegate()), xImageBox, element);
	return xImageBox;
    }

    protected XImageBox getImageBox(Object delegate) {
	return (XImageBox) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	XImageBox xImageBox = getImageBox(element.getDelegate());
	if (xImageBox == null) {
	    return;
	}
	if (Button.PROPERTY_IMAGE.equals(name)) {
	    AbstractImagePrototype xImage = createImage(element, (Image) value);
	    if (xImage != null) {
		xImageBox.setIcon(xImage);
	    }
	    return;
	} else if (Button.PROPERTY_IMAGE_PATH.equals(name)) {
	    AbstractImagePrototype xImage = createImage(element, (String) value);
	    if (xImage != null) {
		xImageBox.setIcon(xImage);
	    }
	    return;
	}
	super.setProperty(element, name, value);
	
    }

}
