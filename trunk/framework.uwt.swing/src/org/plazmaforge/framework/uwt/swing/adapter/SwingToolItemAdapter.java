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
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;

public class SwingToolItemAdapter extends SwingControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	
	javax.swing.JToolBar xToolBar = (javax.swing.JToolBar) getContent(parent.getDelegate());
	
	//TODO: ToolItem can be not only Button
	javax.swing.JButton xButton = new javax.swing.JButton();
	xButton.setFocusable(false);
	
	ToolItem toolItem = (ToolItem) element;
	
	// Get text
	String text = toolItem.getText();
	if (text != null) {
	    xButton.setText(text);
	}

	// Get icon
	javax.swing.Icon xIcon = createImageIcon(element, toolItem.getIcon());
	if (xIcon != null) {
	    xButton.setIcon(xIcon);
	}

	xToolBar.add(xButton);
	return xButton;
    }

    protected javax.swing.AbstractButton getAbstractButton(Object delegate) {
	return (javax.swing.AbstractButton) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	//TODO: ToolItem can be not only Button
	
	javax.swing.AbstractButton xButton = getAbstractButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}
	if (eq(ToolItem.PROPERTY_TEXT, name)) {
	    xButton.setText(getSafeString(value));
	    return;
	} else if (eq(ToolItem.PROPERTY_ICON, name)) {
	    javax.swing.Icon xIcon = createImageIcon(element, (Image) value);
	    if (xIcon != null) {
		xButton.setIcon(xIcon);
	    }
	    return;
	} else if (eq(ToolItem.PROPERTY_ICON_PATH, name)) {
	    javax.swing.Icon xIcon = createImageIcon(element, (String) value);
	    if (xIcon != null) {
		xButton.setIcon(xIcon);
	    }
	    return;
	} 
	
	super.setProperty(element, name, value);
	
    }

    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	//TODO: ToolItem can be not only Button
	Widget widget = (Widget) element;
	javax.swing.AbstractButton xButton = getAbstractButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xButton.addActionListener(createActionListener(widget, listener));
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }

    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	
	//TODO: ToolItem can be not only Button
	Widget widget = (Widget) element;
	javax.swing.AbstractButton xButton = getAbstractButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xButton.removeActionListener(getActionListener(widget, listener));
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }
}
