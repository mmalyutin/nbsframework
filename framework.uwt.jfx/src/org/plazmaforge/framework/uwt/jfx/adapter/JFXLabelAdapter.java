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
import org.plazmaforge.framework.uwt.widget.Label;

/**
 * 
 * @author ohapon
 *
 */
public class JFXLabelAdapter extends JFXControlAdapter {


    public Object createDelegate(UIElement parent, UIElement element) {
	javafx.scene.Parent xParent = getContent(parent.getDelegate());
	javafx.scene.control.Label xLabel = new javafx.scene.control.Label();
	
	Label label = (Label) element; 
	// Get text
	String text = label.getText();
	if (text != null) {
	    xLabel.setText(text);
	}

	// Get icon
	//javafx.scene.image.ImageView xIcon = createImageView(element, label.getIcon());
	//if (xIcon != null) {
	//    xLabel.setGraphic(xIcon);
	//}
		
	addChild(xParent, xLabel, element);
	return xLabel;
    }
    
    

    protected javafx.scene.control.Label asLabel(Object delegate) {
	return (javafx.scene.control.Label) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	javafx.scene.control.Label xLabel = asLabel(element.getDelegate());
	if (xLabel == null) {
	    return;
	}
	if (Label.PROPERTY_TEXT.equals(name)) {
	    xLabel.setText(asSafeString(value));
	    return;
	} else if (Label.PROPERTY_ICON.equals(name)) {
	    //javafx.scene.image.ImageView xIcon = createImageView(element, asImage(value));
	    //if (xIcon != null) {
	    //    xLabel.setGraphic(xIcon);
	    //}
	    return;
	} else if (Label.PROPERTY_ICON_PATH.equals(name)) {
	    //javafx.scene.image.ImageView xIcon = createImageView(element, asString(value));
	    //if (xIcon != null) {
	    //    xLabel.setGraphic(xIcon);
	    //}
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }
}
