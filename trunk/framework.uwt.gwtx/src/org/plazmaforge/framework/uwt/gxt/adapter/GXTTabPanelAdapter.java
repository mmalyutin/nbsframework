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
import org.plazmaforge.framework.uwt.gxt.widget.XTabPanel;
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Widget;


/**
 * 
 * @author ohapon
 *
 */
public class GXTTabPanelAdapter extends GXTContainerAdapter {

    public static final int DEFAULT_TAB_PANEL_WIDTH = 450;
    
    public Object createDelegate(UIObject parent, UIObject element) {
	XTabPanel xTabPanel = new XTabPanel();
	xTabPanel.setTabScroll(true);
	xTabPanel.setAnimScroll(true);
	//xTabPanel.setWidth(DEFAULT_TAB_PANEL_WIDTH);
	
	addChild(getContent(parent.getDelegate()), xTabPanel, element); // Add to parent. Use super method
	return xTabPanel;
    }
   
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	XTabPanel xTabPanel = (XTabPanel) element.getDelegate();
 	if (xTabPanel == null) {
 	    //TODO
 	   GWT.log("Call invoke method: Delegate is null");
 	    return null;
 	}
 	if (TabPanel.METHOD_SET_ACTIVE_ITEM.equals(methodName)) {
 	    // TODO: For init delegate only. Maybe need convert to property. See Desktop too.
 	    if (args != null && args.length > 0) {
 		
 		Integer index = (Integer) args[0];
 		if (index == null) {
 		    return null;
 		}
 		Widget widget = xTabPanel.getWidget(index);
  		xTabPanel.setActiveWidget(widget);
 	    }
 	    return null;
 	}
 	
 	return super.invoke(element, methodName, args);
    }
}
