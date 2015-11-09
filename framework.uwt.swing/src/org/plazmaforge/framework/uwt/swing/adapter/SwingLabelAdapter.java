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
import org.plazmaforge.framework.uwt.widget.Label;

public class SwingLabelAdapter extends SwingControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = getContent(parent.getDelegate());
	javax.swing.JLabel xLabel = new javax.swing.JLabel();
	
	Label label = (Label) element;
	
	// Get text
	String text = label.getText();
	if (text != null) {
	    xLabel.setText(text);
	}

	// Get icon
	javax.swing.Icon xIcon = createImageIcon(element, label.getIcon());
	if (xIcon != null) {
	    xLabel.setIcon(xIcon);
	}

	addToParent(xParent, xLabel, element);	
	return xLabel;
    }

    
    protected javax.swing.JLabel getJLabel(Object delegate) {
	return (javax.swing.JLabel) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	javax.swing.JLabel xLabel = getJLabel(element.getDelegate());
	if (xLabel == null) {
	    return;
	}
	if (Label.PROPERTY_TEXT.equals(name)) {
	    // TODO: Experimental solution
	    // Convert text to HTML ('\n' -> '<br>')
	    String text = getSafeString(value);
	    String html = toHtml(text);
	    if (!text.equals(html)) {
		text = "<html>" + text + "</html>";
	    }
	    xLabel.setText(getSafeString(value));
	    return;
	} else if (Label.PROPERTY_ICON.equals(name)) {
	    javax.swing.Icon xIcon = createImageIcon(element, (Image) value);
	    if (xIcon != null) {
		xLabel.setIcon(xIcon);
	    }
	    return;
	} else if (Label.PROPERTY_ICON_PATH.equals(name)) {
	    javax.swing.Icon xIcon = createImageIcon(element, (String) value);
	    if (xIcon != null) {
		xLabel.setIcon(xIcon);
	    }
	    return;
	}  
	
	super.setProperty(element, name, value);
	
    }

}
