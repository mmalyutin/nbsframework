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
import org.eclipse.swt.events.ArmEvent;
import org.eclipse.swt.events.ArmListener;
import org.eclipse.swt.widgets.ToolTip;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.event.Events;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.menu.MenuItem;

public class SWTMenuItemAdapter extends SWTWidgetAdapter {

    public static final String SYS_PROPETY_TOOL_TIP = "$toolTip";
    

    public Object createDelegate(UIObject parent, UIObject element) {
	
	
	org.eclipse.swt.widgets.Widget xParent = (org.eclipse.swt.widgets.Widget) getWidget(parent.getDelegate());
	org.eclipse.swt.widgets.MenuItem xMenuItem = null;
	if (xParent instanceof org.eclipse.swt.widgets.Menu) {
	    org.eclipse.swt.widgets.Menu xParentMenu = (org.eclipse.swt.widgets.Menu) xParent;
	    xMenuItem = new org.eclipse.swt.widgets.MenuItem(xParentMenu, SWT.PUSH); // PUSH;
	} else {
	    org.eclipse.swt.widgets.MenuItem xParentMenuItem = (org.eclipse.swt.widgets.MenuItem) xParent;
	    xMenuItem =  new org.eclipse.swt.widgets.MenuItem(xParentMenuItem.getMenu(), SWT.PUSH); // PUSH;
	}
	
	// Get text
	MenuItem menuItem = (MenuItem) element;
	String text = menuItem.getText();
	if (text != null) {
	    xMenuItem.setText(text);
	}
	
	// Get image
	org.eclipse.swt.graphics.Image xIcon = createImage(element, menuItem.getIcon());
	if (xIcon != null) {
	    xMenuItem.setImage(xIcon);
	}
	
	//TODO: Crazy solution !!!
	//VARIANT 2
	//ToolTipHelper.getHandler().register(xMenuItem);
	
	return xMenuItem;
    }

    protected org.eclipse.swt.widgets.MenuItem getMenuItem(Object delegate) {
	return (org.eclipse.swt.widgets.MenuItem) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.MenuItem xMenuItem = getMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}
	
	if (eq(name, MenuItem.PROPERTY_TEXT)) {
	    xMenuItem.setText(getSafeString(value));
	    return;
	} else if (eq(name, MenuItem.PROPERTY_ICON)) {
	    org.eclipse.swt.graphics.Image xIcon = createImage(element, (Image) value);
	    if (xIcon != null) {
		xMenuItem.setImage(xIcon);
	    }
	    return;
	} else if (eq(name, MenuItem.PROPERTY_ICON_PATH)) {
	    org.eclipse.swt.graphics.Image xIcon = createImage(element, (String) value);
	    if (xIcon != null) {
		xMenuItem.setImage(xIcon);
	    }
	    return;
	} else if (eq(name, MenuItem.PROPERTY_TOOL_TIP)) {

	    
	    //VARIANT 1
	    /*
	    xMenuItem.setData("TIP_TEXT", getSafeString(value));

	    if (xMenuItem.getData("tooltip.register") == null) {
		xMenuItem.setData("tooltip.register", true);
		//ToolTipHelper.getHandler().register(xMenuItem);
	    }
	    */
	    
	    
	    
	    //VARIANT 2
	    ToolTip toolTip = (ToolTip) xMenuItem.getData(SYS_PROPETY_TOOL_TIP);
	    if (toolTip == null){
		toolTip = new ToolTip(xMenuItem.getParent().getShell(), SWT.NONE);
		final ToolTip fToolTip = toolTip;
		xMenuItem.setData(SYS_PROPETY_TOOL_TIP, toolTip);
		xMenuItem.addArmListener(new ArmListener() {
		    
		    @Override
		    public void widgetArmed(ArmEvent event) {
			fToolTip.setVisible(true);
			//System.out.println("Arm");
		    }
		});
	    }
	    toolTip.setText(getSafeString(value));
	    return;
	} else if (eq(name, MenuItem.PROPERTY_ENABLED)) {
	    xMenuItem.setEnabled(booleanValue(value));
	    return;
	}
	
	super.setProperty(element, name, value);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	Widget widget = (Widget) element;
	org.eclipse.swt.widgets.MenuItem xMenuItem = getMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xMenuItem.addSelectionListener(createSelectionListener(widget, listener));
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }


    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	
	Widget widget = (Widget) element;
	org.eclipse.swt.widgets.MenuItem xMenuItem = getMenuItem(element.getDelegate());
	if (xMenuItem == null) {
	    return;
	}

	if (eq(Events.Selection, eventType)) {
	    xMenuItem.removeSelectionListener(getSelectionListener(widget, listener));
	    return;
	} 
	
	super.addListener(element, eventType, listener);
    }
}
