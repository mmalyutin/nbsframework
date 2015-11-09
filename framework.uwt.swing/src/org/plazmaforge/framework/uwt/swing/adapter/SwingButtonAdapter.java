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
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.swing.widget.XButton;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;

public class SwingButtonAdapter extends SwingControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = (java.awt.Container) getContent(parent.getDelegate());
	javax.swing.JButton xButton = new XButton();
	
	Button button = (Button) element;
	
	// Get text
	String text = button.getText();
	if (text != null) {
	    xButton.setText(text);
	}

	// Get icon
	javax.swing.Icon xIcon = createImageIcon(element, button.getIcon());
	if (xIcon != null) {
	    xButton.setIcon(xIcon);
	}
		
	addToParent(xParent, xButton, element);
	return xButton;
    }

    protected javax.swing.AbstractButton getAbstractButton(Object delegate) {
	return (javax.swing.AbstractButton) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	javax.swing.AbstractButton xButton = getAbstractButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}
	if (eq(name, Button.PROPERTY_TEXT)) {
	    xButton.setText(getSafeString(value));
	    return;
	} else if (eq(name, Button.PROPERTY_ICON)) {
	    javax.swing.Icon xIcon = createImageIcon(element, (Image) value);
	    if (xIcon != null) {
		xButton.setIcon(xIcon);
	    }
	    return;
	} else if (eq(name, Button.PROPERTY_ICON_PATH)) {
	    javax.swing.Icon xIcon = createImageIcon(element, (String) value);
	    if (xIcon != null) {
		xButton.setIcon(xIcon);
	    }
	    return;
	} 
	
	super.setProperty(element, name, value);
	
    }

    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	javax.swing.AbstractButton xButton = getAbstractButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xButton.addActionListener(createActionListener(control, listener));
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	Control control = (Control) element;
	javax.swing.AbstractButton xButton = getAbstractButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xButton.removeActionListener(getActionListener(control, listener));
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }
}
