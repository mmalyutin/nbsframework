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
import org.plazmaforge.framework.uwt.widget.Listener;

public class SwingMenuBarAdapter extends SwingWidgetAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	// Only Frame can have MenuBar
	javax.swing.JFrame xFrame = (javax.swing.JFrame) parent.getDelegate();
	
	//java.awt.MenuBar xMenuBar = new java.awt.MenuBar();
	//xFrame.setMenuBar(xMenuBar);
	
	javax.swing.JMenuBar xMenuBar = new javax.swing.JMenuBar();
	xFrame.setJMenuBar(xMenuBar);
	
	return xMenuBar;
    }
    
    
    protected javax.swing.JMenuBar getMenuBar(Object delegate) {
	return (javax.swing.JMenuBar) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	javax.swing.JMenuBar xMenuBar = getMenuBar(element.getDelegate());
	if (xMenuBar == null) {
	    return;
	}
	
	// do nothing
	
	super.setProperty(element, name, value);
    }

    
    @Override
    public void addListener(UIObject element, String eventType, final Listener listener) {
	
	javax.swing.JMenuBar xMenuBar = getMenuBar(element.getDelegate());
	if (xMenuBar == null) {
	    return;
	}

	//if (equals(UWT.Menu, eventType)) {
	//    xMenuBar.addMenuListener(createMenuListener(listener));
	//    return;
	//} 
	
	super.addListener(element, eventType, listener);
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, final Listener listener) {
	
	javax.swing.JMenuBar xMenuBar = getMenuBar(element.getDelegate());
	if (xMenuBar == null) {
	    return;
	}

	//if (equals(UWT.Menu, eventType)) {
	//    xMenuBar.removeMenuListener(getMenuListener(listener));
	//    return;
	//} 
	
	super.removeListener(element, eventType, listener);
    }


}
