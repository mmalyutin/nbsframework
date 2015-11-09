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
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;

public class SWTTabPanelAdapter extends SWTCompositeAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
   	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
   	org.eclipse.swt.widgets.TabFolder xTabPanel = new org.eclipse.swt.widgets.TabFolder(xParent, SWT.NONE);
   	addToParent(xParent, xTabPanel, element);
   	return xTabPanel;
   }
   
   @Override
   public void checkDelegate(UIObject element) {
	// clear super method
   }
   
   @Override
   public Object invoke(UIObject element, String methodName, Object[] args) {
       org.eclipse.swt.widgets.TabFolder xTabPanel = (org.eclipse.swt.widgets.TabFolder) element.getDelegate();
	if (xTabPanel == null) {
	    return null;
	}
	if (TabPanel.METHOD_SET_ACTIVE_ITEM.equals(methodName)) {
	    if (args != null && args.length > 0) {
		Integer index = (Integer) args[0];
		if (index == null) {
		    return null;
		}
		xTabPanel.setSelection(index);
	    }
	    return null;
	}
	
	return super.invoke(element, methodName, args);
   }
}
