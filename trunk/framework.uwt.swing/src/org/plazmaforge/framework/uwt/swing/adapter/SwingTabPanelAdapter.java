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
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;

public class SwingTabPanelAdapter extends SwingCompositeAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
   	java.awt.Container xParent = getContent(parent.getDelegate());
   	
   	javax.swing.JTabbedPane xTabPanel = new javax.swing.JTabbedPane();

   	addToParent(xParent, xTabPanel, element);	
   	return xTabPanel; 
   }
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	javax.swing.JTabbedPane xTabPanel = (javax.swing.JTabbedPane) element.getDelegate();
 	if (xTabPanel == null) {
 	    return null;
 	}
 	if (TabPanel.METHOD_SET_ACTIVE_ITEM.equals(methodName)) {
 	    if (args != null && args.length > 0) {
 		Integer index = (Integer) args[0];
 		if (index == null) {
 		    return null;
 		}
 		xTabPanel.setSelectedIndex(index);
 	    }
 	    return null;
 	}
 	
 	return super.invoke(element, methodName, args);
    }    
    
}
