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

package org.plazmaforge.framework.uwt.view;


import org.plazmaforge.framework.uwt.UWTException;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;

/**
 * Container view with TabPanel implementation 
 * 
 * @author ohapon
 *
 */
public class TabView<T> extends ContainerView<T> {


    protected void checkControl(Control control) {
	if (!(control instanceof TabPanel)) {
	    throw new UWTException("Control must be TabPanel");
	}
    }

    
    protected Control createControl() {
	// Create empty tab panel
	TabPanel tabPanel = new TabPanel();
	return tabPanel;
    }
    
    

    protected void addView(TabPanel tabPanel, IView view) {
	view.create();
	Composite body = view.getContainer();
	if (body == null) {
	    return;
	}
	TabItem tabItem = new TabItem();
	String title = view.getTitle();
	tabItem.setTitle(title);
	tabItem.add(body);
	tabPanel.addItem(tabItem);
    }
    
    @Override
    protected void addView(Composite parent, IView view) {
	addView((TabPanel) parent, view);
    }

}
