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
import org.plazmaforge.framework.uwt.swt.util.SWTUtils;
import org.plazmaforge.framework.uwt.widget.Composite;

public class SWTToolBarAdapter extends SWTCompositeAdapter {

    
    public static final String SYS_PROPETY_COOL_ITEM = "$coolItem";
    
    
    public Object createDelegate(UIObject parent, UIObject element) {
   	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
   	org.eclipse.swt.widgets.ToolBar xToolBar = null;
   	if (xParent instanceof org.eclipse.swt.widgets.CoolBar) {
   	    
   	    // ONLY FOR CoolBar
   	    org.eclipse.swt.widgets.CoolBar xCoolBar = (org.eclipse.swt.widgets.CoolBar) xParent;
   	    org.eclipse.swt.widgets.CoolItem xCoolItem = new org.eclipse.swt.widgets.CoolItem(xCoolBar, SWT.NONE);
   	    xToolBar = new org.eclipse.swt.widgets.ToolBar(xCoolBar, SWT.FLAT);
   	    
   	    // Set control
	    xCoolItem.setControl(xToolBar);
	    
	    // Assign CoolItem to fix size
	    xToolBar.setData(SYS_PROPETY_COOL_ITEM, xCoolItem);

	    // Fix size
	    SWTUtils.updateSize(xCoolItem, xToolBar);

   	} else {
   	    xToolBar = new org.eclipse.swt.widgets.ToolBar(xParent, SWT.FLAT);
   	}
   	
   	return xToolBar;
   }
    
   
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.ToolBar xToolBar = (org.eclipse.swt.widgets.ToolBar) element.getDelegate();
	if (xToolBar == null) {
	    return;
	}
	if (Composite.PROPERTY_LAYOUT.equals(name) || Composite.PROPERTY_BACKGROUND.equals(name)) {
	    // ignore
	    return;
	}
	
	super.setProperty(element, name, value);
    }
    
    
    @Override
    public void checkDelegate(UIObject element) {
 	// clear super method
    }

}
