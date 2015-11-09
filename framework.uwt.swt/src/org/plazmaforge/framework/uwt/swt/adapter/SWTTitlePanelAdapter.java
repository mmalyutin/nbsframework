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
import org.plazmaforge.framework.uwt.widget.panel.GroupPanel;
import org.plazmaforge.framework.uwt.widget.panel.TitlePanel;

public class SWTTitlePanelAdapter extends SWTPanelAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	TitlePanel titlePanel = (TitlePanel) element;
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	
	// Create PGroup without rounded edges of the PGroup
	org.eclipse.swt.nebula.widgets.pgroup.PGroup xTitlePanel = new org.eclipse.swt.nebula.widgets.pgroup.PGroup(xParent, SWT.NONE);
	xTitlePanel.setToggleRenderer(null);
	//xTitlePanel.setBackgroundMode(SWT.INHERIT_DEFAULT);
	
	
	//xTitlePanel.setToggleRenderer(new ChevronsToggleRenderer()); //default
	//xTitlePanel.setToggleRenderer(new MinMaxToggleRenderer());
	//xTitlePanel.setToggleRenderer(new TreeNodeToggleRenderer());
	//xTitlePanel.setToggleRenderer(new TwisteToggleRenderer());
	 
	String title = titlePanel.getTitle();
	if (title != null) {
	    xTitlePanel.setText(title);
	}
	addToParent(xParent, xTitlePanel, element);
	return xTitlePanel;
    }
    
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	org.eclipse.swt.nebula.widgets.pgroup.PGroup xTitlePanel = (org.eclipse.swt.nebula.widgets.pgroup.PGroup) element.getDelegate();
	if (xTitlePanel == null) {
	    return;
	}
	
	if (GroupPanel.PROPERTY_TEXT.equals(name)) {
	    xTitlePanel.setText(getSafeString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
    }

    
}
