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

import java.awt.FlowLayout;


import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Composite;

public class SwingRadioGroupAdapter extends SwingCompositeAdapter {

    public static final String SYS_PROPERTY_GROUP = "$group"; 
    
    public Object createDelegate(UIObject parent, UIObject element) {
	
	// Create Swing ButtonGroup
	javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();
	element.setData(SYS_PROPERTY_GROUP, group);
	
	java.awt.Container xParent = getContent(parent.getDelegate());
	
	// To emulate RadioGroup we use JPanel
	javax.swing.JPanel xRadioGroup = new javax.swing.JPanel();
	xRadioGroup.setLayout(new FlowLayout(FlowLayout.LEFT));
	addToParent(xParent, xRadioGroup, element);
	return xRadioGroup;
    }

       
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	java.awt.Container xRadioGroup = (java.awt.Container) element.getDelegate();
	if (xRadioGroup == null) {
	    return;
	}
	if (eq(Composite.PROPERTY_LAYOUT, name)) {
	    // ignore
	    // TODO: Use horizontal/vertical orientation
	    return;
	}
	super.setProperty(element, name, value);
    }
    
}
