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
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.ToggleButton;

import com.sencha.gxt.core.client.util.ToggleGroup;

/**
 * 
 * @author ohapon
 *
 */
public class GXTToggleButtonAdapter extends GXTButtonAdapter {

    public static final String SYS_GROUP_KEY = "$group"; 
    
    public Object createDelegate(UIObject parent, UIObject element) {
	com.sencha.gxt.widget.core.client.button.ToggleButton xToggleButton = new com.sencha.gxt.widget.core.client.button.ToggleButton();
	ToggleButton toggleButton = (ToggleButton) element;
	
	initButton(xToggleButton, toggleButton);
	addToParent(getContent(parent.getDelegate()), xToggleButton, element); // Add to parent
	
	String groupName = toggleButton.getGroup();
	if (groupName == null) {
	    return xToggleButton;
	}

	// Get GXT ToggleGroup from UWT parent
	ToggleGroup group = getToggleGroup(parent, groupName, true);
	
	group.add(xToggleButton);
	
	return xToggleButton;
    }

    protected com.sencha.gxt.widget.core.client.button.ToggleButton getToggleButton(Object delegate) {
	return (com.sencha.gxt.widget.core.client.button.ToggleButton) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.sencha.gxt.widget.core.client.button.ToggleButton xToggleButton = getToggleButton(element.getDelegate());
	if (xToggleButton == null) {
	    return;
	}
	ToggleButton toggleButton = (ToggleButton) element;
	UIObject parent = element.getUIParent();
	if (Button.PROPERTY_SELECTED.equals(name)) {
	    xToggleButton.setValue(getBoolean(value));
	    return;
	} else if (Button.PROPERTY_GROUP.equals(name)) {
	    
	    // Remove element form OLD ToggleGroup
	    String oldGroupName = toggleButton.getGroup();
	    ToggleGroup oldGroup = getToggleGroup(parent, oldGroupName, false);
	    if (oldGroup != null) {
		oldGroup.remove(xToggleButton);
	    }
	    
	    // Add element form NEW ToggleGroup
	    String newGroupName = getString(value);
	    if (newGroupName != null) {
		ToggleGroup newGroup = getToggleGroup(parent, newGroupName, true);
		newGroup.add(xToggleButton);
	    }
	    
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }

    private ToggleGroup getToggleGroup(UIObject parent, String groupName, boolean force) {
	if (parent == null || groupName == null) {
	    return null;
	}
	String groupKey = getGroupKey(groupName);
	
	// Get GXT ToggleGroup from UWT parent
	ToggleGroup group = (ToggleGroup) parent.getData(groupKey);
	if (group != null || !force) {
	    return group;
	}
	
	if (group == null) {
	    group = new ToggleGroup();
	    parent.setData(groupKey, group);
	}
	
	return group;
    }
    
    private String getGroupKey(String groupName) {
  	return SYS_GROUP_KEY + ":" + groupName;
    }
}
