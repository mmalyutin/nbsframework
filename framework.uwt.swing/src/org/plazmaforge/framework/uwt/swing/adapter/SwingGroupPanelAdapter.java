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
import org.plazmaforge.framework.uwt.swing.widget.XGroupPanel;
import org.plazmaforge.framework.uwt.widget.panel.GroupPanel;

public class SwingGroupPanelAdapter extends SwingPanelAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	GroupPanel groupPanel = (GroupPanel) element;
	java.awt.Container xParent = getContent(parent.getDelegate());
	XGroupPanel xGroupPanel = new XGroupPanel();
	xGroupPanel.setTitle(getSafeString(groupPanel.getTitle()));
	addToParent(xParent, xGroupPanel, element);
	return xGroupPanel;
    }
    
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	XGroupPanel xGroupPanel = (XGroupPanel) element.getDelegate();
	if (xGroupPanel == null) {
	    return;
	}
	
	if (GroupPanel.PROPERTY_TITLE.equals(name)) {
	    xGroupPanel.setTitle(getSafeString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
    }


}
