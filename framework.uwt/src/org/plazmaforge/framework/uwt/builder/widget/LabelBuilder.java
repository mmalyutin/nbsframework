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

package org.plazmaforge.framework.uwt.builder.widget;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.Label;

public class LabelBuilder extends ControlBuilder {

    public UIElement buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	Label label = new Label();
	populate(data, label);
	return label;
    }
    
    protected void populate(IData data, UIElement element) {
	if (data == null) {
	    return;
	}
	
	super.populate(data, element);
	
	Label label = (Label) element;
	String text = getRSString(data, Label.PROPERTY_TEXT);
	if (text != null) {
	    label.setText(text);
	}
	
	String iconPath = getRSString(data, Label.PROPERTY_ICON); // image attribute as image path
	if (iconPath != null) {
	    label.setIcon(iconPath);
	}

    }
    
    
}
