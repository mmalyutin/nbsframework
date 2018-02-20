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
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.ToggleButton;


/**
 * 
 * @author ohapon
 *
 */
public class JFXToggleButtonAdapter extends JFXButtonAdapter {

    public static final String SYS_GROUP_KEY = "$group"; 
    
    public Object createDelegate(UIElement parent, UIElement element) {
	
	javafx.scene.Parent xParent = (javafx.scene.Parent) getContent(parent.getDelegate());
	javafx.scene.control.ToggleButton xToggleButton = new javafx.scene.control.ToggleButton();
	ToggleButton toggleButton = (ToggleButton) element;
	
	initButton(xToggleButton, toggleButton);
	addChild(getContent(parent.getDelegate()), xToggleButton, element); // Add to parent
	
	String groupName = toggleButton.getGroup();
	if (groupName == null) {
	    return xToggleButton;
	}

	// Get GXT ToggleGroup from UWT parent
	javafx.scene.control.ToggleGroup group = getToggleGroup(parent, groupName, true);
	xToggleButton.setToggleGroup(group);
	
	return xToggleButton;
    }

    
    protected javafx.scene.control.ToggleButton asToggleButton(Object delegate) {
	return (javafx.scene.control.ToggleButton) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	javafx.scene.control.ToggleButton xToggleButton = asToggleButton(element.getDelegate());
	if (xToggleButton == null) {
	    return;
	}
	ToggleButton toggleButton = (ToggleButton) element;
	UIElement parent = element.getUIParent();
	if (Button.PROPERTY_SELECTED.equals(name)) {
	    xToggleButton.setSelected(asBoolean(value));
	    return;
	} else if (Button.PROPERTY_GROUP.equals(name)) {
	    
	    // Remove element form OLD ToggleGroup
	    String oldGroupName = toggleButton.getGroup();
	    javafx.scene.control.ToggleGroup oldGroup = getToggleGroup(parent, oldGroupName, false);
	    if (oldGroup != null) {
		xToggleButton.setToggleGroup(null);
	    }
	    
	    // Add element form NEW ToggleGroup
	    String newGroupName = asString(value);
	    if (newGroupName != null) {
		javafx.scene.control.ToggleGroup newGroup = getToggleGroup(parent, newGroupName, true);
		xToggleButton.setToggleGroup(newGroup);
	    }
	    
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }

    private javafx.scene.control.ToggleGroup getToggleGroup(UIElement parent, String groupName, boolean force) {
	if (parent == null || groupName == null) {
	    return null;
	}
	String groupKey = getGroupKey(groupName);
	
	// Get JFX ToggleGroup from UWT parent
	javafx.scene.control.ToggleGroup group = (javafx.scene.control.ToggleGroup) parent.getData(groupKey);
	if (group != null || !force) {
	    return group;
	}
	
	if (group == null) {
	    group = new javafx.scene.control.ToggleGroup();
	    parent.setData(groupKey, group);
	}
	
	return group;
    }
    
    private String getGroupKey(String groupName) {
  	return SYS_GROUP_KEY + ":" + groupName;
    }
    
}
