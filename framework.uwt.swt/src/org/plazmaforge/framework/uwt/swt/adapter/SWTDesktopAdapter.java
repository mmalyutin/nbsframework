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
import org.plazmaforge.framework.uwt.desktop.Desktop;
import org.plazmaforge.framework.uwt.desktop.DesktopItem;
import org.plazmaforge.framework.uwt.swt.widget.XDesktop;
import org.plazmaforge.framework.uwt.swt.widget.XDesktopItem;

public class SWTDesktopAdapter extends SWTCompositeAdapter {

    
    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	XDesktop xDesktop = new XDesktop(xParent, SWT.NONE);
	//xDesktop.setBackgroundMode(SWT.INHERIT_DEFAULT);
	addToParent(xParent, xDesktop, element);
	return xDesktop;
    }
    
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	XDesktop xDesktop = (XDesktop) element.getDelegate();
	if (xDesktop == null) {
	    return null;
	}
	if (Desktop.METHOD_SET_ACTIVE_ITEM.equals(methodName)) {
	    if (args != null && args.length > 0) {
		DesktopItem desktopItem = (DesktopItem) args[0];
		XDesktopItem xDesktopItem = (XDesktopItem) desktopItem.getDelegate();
		xDesktop.setSelectionItem(xDesktopItem);
	    }
	    return null;
	}
	
	return super.invoke(element, methodName, args);
    }


}
