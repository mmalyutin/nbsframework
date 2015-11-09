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
import org.plazmaforge.framework.uwt.swt.util.SWTUtils;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;

public class SWTToolItemAdapter extends SWTWidgetAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	
	
	org.eclipse.swt.widgets.ToolBar xToolBar = (org.eclipse.swt.widgets.ToolBar) getWidget(parent.getDelegate());
	org.eclipse.swt.widgets.ToolItem xToolItem = new org.eclipse.swt.widgets.ToolItem(xToolBar, SWT.FLAT);
	
	// Get text
	ToolItem toolItem = (ToolItem) element;
	String text = toolItem.getText();
	if (text != null) {
	    xToolItem.setText(text);
	}
	
	// Get icon
	org.eclipse.swt.graphics.Image xIcon = createImage(element, toolItem.getIcon());
	if (xIcon != null) {
	    xToolItem.setImage(xIcon);
	}
	
	
	org.eclipse.swt.widgets.CoolItem xCoolItem = (org.eclipse.swt.widgets.CoolItem) xToolBar.getData(SWTToolBarAdapter.SYS_PROPETY_COOL_ITEM);
	
	// Fix size 
	SWTUtils.updateSize(xCoolItem, xToolBar);
	
	return xToolItem;
    }

    protected org.eclipse.swt.widgets.ToolItem getToolItem(Object delegate) {
	return (org.eclipse.swt.widgets.ToolItem) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.ToolItem xToolItem = getToolItem(element.getDelegate());
	if (xToolItem == null) {
	    return;
	}
	
	if (ToolItem.PROPERTY_TEXT.equals(name)) {
	    xToolItem.setText(getSafeString(value));
	    return;
	} else if (eq(name, ToolItem.PROPERTY_ICON)) {
	    org.eclipse.swt.graphics.Image xIcon = createImage(element, (Image) value);
	    if (xIcon != null) {
		xToolItem.setImage(xIcon);
	    }
	    return;
	} else if (eq(name, ToolItem.PROPERTY_ICON_PATH)) {
	    org.eclipse.swt.graphics.Image xIcon = createImage(element, (String) value);
	    if (xIcon != null) {
		xToolItem.setImage(xIcon);
	    }
	    return;
	} else if (ToolItem.PROPERTY_ENABLED.equals(name)) {
	    xToolItem.setEnabled(booleanValue(value));
	    return;
	} else if (ToolItem.PROPERTY_TOOL_TIP.equals(name)) {
	    xToolItem.setToolTipText(getSafeString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	Widget widget = (Widget) element;
	org.eclipse.swt.widgets.ToolItem xToolItem = getToolItem(element.getDelegate());
	if (xToolItem == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xToolItem.addSelectionListener(createSelectionListener(widget, listener));
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
    
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	
	Widget widget = (Widget) element;
	org.eclipse.swt.widgets.ToolItem xToolItem = getToolItem(element.getDelegate());
	
	if (eq(Events.Selection, eventType)) {
	    xToolItem.removeSelectionListener(getSelectionListener(widget, listener));
	    return;
	} 
	
	super.removeListener(element, eventType, listener);
    }


}
