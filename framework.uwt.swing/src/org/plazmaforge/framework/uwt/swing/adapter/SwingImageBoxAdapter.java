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

package org.plazmaforge.framework.uwt.swing.adapter;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.ImageBox;
import org.plazmaforge.framework.uwt.widget.Label;

public class SwingImageBoxAdapter extends SwingControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = getContent(parent.getDelegate());
	javax.swing.JLabel xImageBox = new javax.swing.JLabel();
	
	ImageBox label = (ImageBox) element;
	
	// Get image
	javax.swing.Icon xImage = createImageIcon(element, label.getImage());
	if (xImage != null) {
	    xImageBox.setIcon(xImage);
	}

	addToParent(xParent, xImageBox, element);	
	return xImageBox;
    }

    
    protected javax.swing.JLabel getImageBox(Object delegate) {
	return (javax.swing.JLabel) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	javax.swing.JLabel xImageBox = getImageBox(element.getDelegate());
	if (xImageBox == null) {
	    return;
	}
	if (Label.PROPERTY_IMAGE.equals(name)) {
	    javax.swing.Icon xImage = createImageIcon(element, (Image) value);
	    if (xImage != null) {
		xImageBox.setIcon(xImage);
	    }
	    return;
	} else if (Label.PROPERTY_IMAGE_PATH.equals(name)) {
	    javax.swing.Icon xImage = createImageIcon(element, (String) value);
	    if (xImage != null) {
		xImageBox.setIcon(xImage);
	    }
	    return;
	}  
	
	super.setProperty(element, name, value);
	
    }

}
