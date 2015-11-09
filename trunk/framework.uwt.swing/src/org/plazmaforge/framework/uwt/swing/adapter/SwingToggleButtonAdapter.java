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
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.ToggleButton;

public class SwingToggleButtonAdapter extends SwingButtonAdapter {

    public static final String SYS_GROUP_KEY = "$group"; 
    
    public Object createDelegate(UIObject parent, UIObject element) {
	
	ToggleButton toggleButton = (ToggleButton) element;
		
	java.awt.Container xParent = getContent(parent.getDelegate());
	javax.swing.JToggleButton xToogleButton = new javax.swing.JToggleButton();
	addToParent(xParent, xToogleButton, element);
	
	if (toggleButton.getGroup() == null) {
	    return xToogleButton;
	}
	
	String groupName = toggleButton.getGroup();
	String groupKey = getGroupKey(groupName);
	
	// Get Swing ButtonGroup from UWT parent
	javax.swing.ButtonGroup group = (javax.swing.ButtonGroup) parent.getData(groupKey);
	if (group == null) {
	    group = new javax.swing.ButtonGroup();
	    // Create ButtonGroup if null
	    parent.setData(groupKey, group);
	}
	javax.swing.JToggleButton.ToggleButtonModel model = (javax.swing.JToggleButton.ToggleButtonModel) xToogleButton.getModel();
	
	// Set common ButtonGroup
	model.setGroup(group);
	
	return xToogleButton;
    }

    protected javax.swing.JToggleButton getJToggleButton(Object delegate) {
	return (javax.swing.JToggleButton) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	javax.swing.JToggleButton xToogleButton = getJToggleButton(element.getDelegate());
	if (xToogleButton == null) {
	    return;
	}
	if (Button.PROPERTY_SELECTED.equals(name)) {
	    xToogleButton.setSelected(getBoolean(value));
	    return;
	} else if (Button.PROPERTY_GROUP.equals(name)) {
	    UIObject parent = element.getUIParent();
	    String groupName = getString(value);
	    String groupKey = getGroupKey(groupName);

	    // Get Swing ButtonGroup from UWT parent
	    javax.swing.ButtonGroup group = (javax.swing.ButtonGroup) parent.getData(groupKey);
	    if (group == null) {
		// Create ButtonGroup if null
		group = new javax.swing.ButtonGroup();
		parent.setData(groupKey, group);
	    }
	    javax.swing.JToggleButton.ToggleButtonModel model = (javax.swing.JToggleButton.ToggleButtonModel) xToogleButton.getModel();
		
	    // Set common ButtonGroup
	    model.setGroup(group);
	}
	
	super.setProperty(element, name, value);
	
    }
    
    private String getGroupKey(String groupName) {
	return SYS_GROUP_KEY + ":" + groupName;
    }
}
