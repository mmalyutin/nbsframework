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
import org.plazmaforge.framework.uwt.widget.Listener;

public class SWTMenuBarAdapter extends SWTWidgetAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	
	org.eclipse.swt.widgets.Decorations xParent = (org.eclipse.swt.widgets.Decorations) getContent(parent.getDelegate());
	org.eclipse.swt.widgets.Menu xMenu = new org.eclipse.swt.widgets.Menu(xParent, SWT.BAR);
	
	addToParent(xParent, xMenu, element);
	return xMenu;
    }

    protected org.eclipse.swt.widgets.Menu getMenu(Object delegate) {
	return (org.eclipse.swt.widgets.Menu) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.Menu xMenu = getMenu(element.getDelegate());
	if (xMenu == null) {
	    return;
	}
	
	// do nothing
	
	super.setProperty(element, name, value);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	org.eclipse.swt.widgets.Menu xMenu = getMenu(element.getDelegate());
	if (xMenu == null) {
	    return;
	}

	//if (equals(UWT.Menu, eventType)) {
	//    xMenu.addMenuListener(createMenuListener(listener));
	//    return;
	//} 
	
	super.addListener(element, eventType, listener);
    }

}
