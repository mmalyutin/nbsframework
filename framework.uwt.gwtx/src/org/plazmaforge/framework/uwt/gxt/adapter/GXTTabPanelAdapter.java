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

package org.plazmaforge.framework.uwt.gxt.adapter;

import org.plazmaforge.framework.uwt.UIObject;
//import org.plazmaforge.framework.uwt.widget.panel.TabPanel;


/**
 * 
 * @author ohapon
 *
 */
public class GXTTabPanelAdapter extends GXTCompositeAdapter {

    public static final int DEFAULT_TAB_PANEL_WIDTH = 450;
    
    public Object createDelegate(UIObject parent, UIObject element) {
	com.sencha.gxt.widget.core.client.TabPanel xTabPanel = new com.sencha.gxt.widget.core.client.TabPanel();
	xTabPanel.setTabScroll(true);
	xTabPanel.setAnimScroll(true);
	xTabPanel.setWidth(DEFAULT_TAB_PANEL_WIDTH);
	
	addToParent(getContent(parent.getDelegate()), xTabPanel, element); // Add to parent. Use super method
	return xTabPanel;
    }
   
    
//    @Override
//    public Object invoke(UIObject element, String methodName, Object[] args) {
//	com.sencha.gxt.widget.core.client.TabPanel xTabPanel = (com.sencha.gxt.widget.core.client.TabPanel) element.getDelegate();
// 	if (xTabPanel == null) {
// 	    return null;
// 	}
// 	if (TabPanel.METHOD_SET_ACTIVE_ITEM.equals(methodName)) {
// 	    if (args != null && args.length > 0) {
// 		Integer index = (Integer) args[0];
// 		if (index == null) {
// 		    return null;
// 		}
// 		//TODO:MIGRATE: Need get a tab item by index
// 		com.sencha.gxt.widget.core.client.TabItemConfig item = null; // xTabPanel.getItem(index);
// 		if (item == null) {
// 		    return null;
// 		}
// 		//TODO:MIGRATE
// 		// Need select the tab item
// 		//xTabPanel.setSelection(item);
// 	    }
// 	    return null;
// 	}
// 	
// 	return super.invoke(element, methodName, args);
//    }
}
