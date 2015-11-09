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
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.plazmaforge.framework.util.SystemInfo;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Composite;

public class SWTCoolBarAdapter extends SWTCompositeAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
   	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
   	boolean isWindows = SystemInfo.isWindows;
   	org.eclipse.swt.widgets.CoolBar xCoolBar = new org.eclipse.swt.widgets.CoolBar(xParent, isWindows ? SWT.NONE : SWT.FLAT);
   	//initCoolBar(xCoolBar);
   	return xCoolBar;
   }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.CoolBar xCoolBar = (org.eclipse.swt.widgets.CoolBar) element.getDelegate();
	if (xCoolBar == null) {
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

    
    protected void initCoolBar(final CoolBar coolBar) {
        coolBar.addListener(SWT.Resize, new Listener() {
	    public void handleEvent (Event event) {
		// Hard code
		coolBar.getParent().getParent().layout();
	    }
	});
    }
}
