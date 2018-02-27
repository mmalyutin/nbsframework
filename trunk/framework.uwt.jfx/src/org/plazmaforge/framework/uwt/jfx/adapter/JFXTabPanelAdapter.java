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

package org.plazmaforge.framework.uwt.jfx.adapter;

import org.plazmaforge.framework.uwt.UIElement;
//import org.plazmaforge.framework.uwt.widget.panel.TabPanel;
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;



/**
 * 
 * @author ohapon
 *
 */
public class JFXTabPanelAdapter extends JFXContainerAdapter {

    public static final int DEFAULT_TAB_PANEL_WIDTH = 450;
    
    public Object createDelegate(UIElement parent, UIElement element) {
	javafx.scene.control.TabPane xTabPanel = new javafx.scene.control.TabPane();
	//xTabPanel.setWidth(DEFAULT_TAB_PANEL_WIDTH);
	
	addChild(getContent(parent.getDelegate()), xTabPanel, element); // Add to parent. Use super method
	return xTabPanel;
    }
   
    protected javafx.scene.control.TabPane asTabPanel(Object delegate) {
	return (javafx.scene.control.TabPane) delegate;
    }
    
    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	javafx.scene.control.TabPane xTabPanel = (javafx.scene.control.TabPane) element.getDelegate();
 	if (xTabPanel == null) {
 	    logUI("Call invoke method: Delegate is null");
 	    return null;
 	}
 	if (TabPanel.METHOD_SET_ACTIVE_ITEM.equals(methodName)) {
 	    // TODO: For init delegate only. Maybe need convert to property. See Desktop too.
 	    if (args != null && args.length > 0) {
 		
 		Integer index = (Integer) args[0];
 		if (index == null) {
 		    return null;
 		}
 		
 		//TODO
 		//Widget widget = xTabPanel.getWidget(index);
  		//xTabPanel.setActiveWidget(widget);
 	    }
 	    return null;
 	}
 	
 	return super.invoke(element, methodName, args);
    }
}
