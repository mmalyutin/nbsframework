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

package org.plazmaforge.framework.uwt.swt.adapter;

import org.eclipse.swt.SWT;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Label;

public class SWTLabelAdapter extends SWTControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	org.eclipse.swt.widgets.Label xLabel = new org.eclipse.swt.widgets.Label(xParent, SWT.NONE);
	
	Label label = (Label) element; 
	// Get text
	String text = label.getText();
	if (text != null) {
	    xLabel.setText(text);
	}

	// Get icon
	org.eclipse.swt.graphics.Image xIcon = createImage(element, label.getIcon());
	if (xIcon != null) {
	    xLabel.setImage(xIcon);
	}		
		
	addToParent(xParent, xLabel, element);
	return xLabel;
    }
    
    

    protected org.eclipse.swt.widgets.Label getLabel(Object delegate) {
	return (org.eclipse.swt.widgets.Label) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.Label xLabel = getLabel(element.getDelegate());
	if (xLabel == null) {
	    return;
	}
	if (Label.PROPERTY_TEXT.equals(name)) {
	    xLabel.setText(getSafeString(value));
	    layout(xLabel);
	    return;
	} else if (Label.PROPERTY_ICON.equals(name)) {
	    org.eclipse.swt.graphics.Image xIcon = createImage(element, (Image) value);
	    if (xIcon != null) {
		xLabel.setImage(xIcon);
	    }
	    return;
	} else if (Label.PROPERTY_ICON_PATH.equals(name)) {
	    org.eclipse.swt.graphics.Image xIcon = createImage(element, (String) value);
	    if (xIcon != null) {
		xLabel.setImage(xIcon);
	    }
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }
}
