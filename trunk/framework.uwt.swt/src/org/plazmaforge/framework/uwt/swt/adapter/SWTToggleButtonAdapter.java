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
import org.plazmaforge.framework.uwt.widget.ToggleButton;

public class SWTToggleButtonAdapter extends SWTButtonAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	
	ToggleButton toggleButton = (ToggleButton) element;
	
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	org.eclipse.swt.widgets.Button xToggleButton = new org.eclipse.swt.widgets.Button(xParent, SWT.TOGGLE);
	String group = toggleButton.getGroup();
	if (group != null) {
	    xToggleButton.setData(ToggleButton.PROPERTY_GROUP, group);
	}
	xToggleButton.addSelectionListener(createToggleListener());
	
	addToParent(xParent, xToggleButton, element);
	return xToggleButton;
    }

      
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	org.eclipse.swt.widgets.Button xButton = getButton(element.getDelegate());
	if (xButton == null) {
	    return;
	}
	if (ToggleButton.PROPERTY_SELECTED.equals(name)) {
	    String group = (String) xButton.getData(ToggleButton.PROPERTY_GROUP);
	    boolean selected = booleanValue(value);
	    if (group == null) {
		xButton.setSelection(selected);
		return;
	    }
	    onToggleEvent(xButton, selected);
	    return;
	} else if (ToggleButton.PROPERTY_GROUP.equals(name)) {
	    xButton.setData(ToggleButton.PROPERTY_GROUP, getString(value));
	    return;
	}
	super.setProperty(element, name, value);
    }

    protected org.eclipse.swt.events.SelectionListener createToggleListener() {
	return new org.eclipse.swt.events.SelectionAdapter() {

	    @Override
	    public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
		org.eclipse.swt.widgets.Button xButton = (org.eclipse.swt.widgets.Button) e.widget;
		onToggleEvent(xButton, true);
	    }

	};
    }
    
    protected void onToggleEvent(org.eclipse.swt.widgets.Button xButton, boolean selected) {
	if (xButton == null) {
	    return;
	}
	// Get ToggleGroup from SWT Button
	String group = (String) xButton.getData(ToggleButton.PROPERTY_GROUP);
	if (group == null) {
	    // Toggle button has not group
	    return;
	}
	org.eclipse.swt.widgets.Composite xParent = xButton.getParent();
	org.eclipse.swt.widgets.Control[] children = xParent.getChildren();
	
        for (int i = 0; i < children.length; i++) {
            org.eclipse.swt.widgets.Control child = children[i];
            // Get ToggleGroup from SWT Button
            String childGroup = (String) child.getData(ToggleButton.PROPERTY_GROUP);
            if (!group.equals(childGroup)) {
        	// Other group
        	continue;
            }
            
            if (child != xButton 
        	    && child instanceof org.eclipse.swt.widgets.Button 
        	    && (child.getStyle() & SWT.TOGGLE) != 0) {
        	// Reset non selected button 
                ((org.eclipse.swt.widgets.Button) child).setSelection(false);
            }
        }
        xButton.setSelection(selected);
        // TODO: action perform

    }
    
}
