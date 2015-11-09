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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.menu.MenuBar;

public class SWTFrameAdapter extends SWTWindowAdapter {

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	org.eclipse.swt.widgets.Shell xFrame = (org.eclipse.swt.widgets.Shell) element.getDelegate();
	
	if (xFrame == null) {
	    return;
	}
	
	if (Frame.PROPERTY_MENU_BAR.equals(name)) {
	    MenuBar menuBar = (MenuBar) value;
	    if (menuBar != null) {
		menuBar.activateUI();
		xFrame.setMenuBar((org.eclipse.swt.widgets.Menu) menuBar.getDelegate()); 
	    }
	    return;
	}

	super.setProperty(element, name, value);
    }
    


}
