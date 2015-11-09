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
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Listener;

public class SWTButtonAdapter extends SWTControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	org.eclipse.swt.widgets.Button xButton = new org.eclipse.swt.widgets.Button(xParent, SWT.NONE);
	
	Button button = (Button) element;
	
	// Get text
	String text = button.getText();
	if (text != null) {
	    xButton.setText(text);
	}
	
	// Get icon
	org.eclipse.swt.graphics.Image xIcon = createImage(element, button.getIcon());
	if (xIcon != null) {
	    xButton.setImage(xIcon);
	}
	
	addToParent(xParent, xButton, element);
	return xButton;
    }

    protected org.eclipse.swt.widgets.Button getButton(Object delegate) {
	return (org.eclipse.swt.widgets.Button) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.Button xButton = getButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}
	if (eq(name, Button.PROPERTY_TEXT)) {
	    xButton.setText(getSafeString(value));
	    return;
	} else if (eq(name, Button.PROPERTY_ICON)) {
	    org.eclipse.swt.graphics.Image xIcon = createImage(element, (Image) value);
	    if (xIcon != null) {
		xButton.setImage(xIcon);
	    }
	    return;
	} else if (eq(name, Button.PROPERTY_ICON_PATH)) {
	    org.eclipse.swt.graphics.Image xIcon = createImage(element, (String) value);
	    if (xIcon != null) {
		xButton.setImage(xIcon);
	    }
	    return;
	}

	
	super.setProperty(element, name, value);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	Control control = (Control) element;
	org.eclipse.swt.widgets.Button xButton = getButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xButton.addSelectionListener(createSelectionListener(control, listener));
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	
	Control control = (Control) element;
	org.eclipse.swt.widgets.Button xButton = getButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xButton.removeSelectionListener(getSelectionListener(control, listener));
	    return;
	}
 	
	super.removeListener(element, eventType, listener);
    }
}
