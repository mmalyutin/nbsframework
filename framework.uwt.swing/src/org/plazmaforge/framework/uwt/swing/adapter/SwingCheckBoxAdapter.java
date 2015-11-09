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
import org.plazmaforge.framework.uwt.widget.CheckBox;

public class SwingCheckBoxAdapter extends SwingButtonAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = (java.awt.Container) getContent(parent.getDelegate());
	javax.swing.JCheckBox xCheckBox = new javax.swing.JCheckBox();
	addToParent(xParent, xCheckBox, element);
	return xCheckBox;
    }

    protected javax.swing.JCheckBox getCheckBox(Object delegate) {
	return (javax.swing.JCheckBox) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	javax.swing.JCheckBox xCheckBox = getCheckBox(element.getDelegate());
	if (xCheckBox == null) {
	    return;
	}
	if (CheckBox.PROPERTY_VALUE.equals(name) || CheckBox.PROPERTY_SELECTED.equals(name)) {
	    xCheckBox.setSelected(booleanValue(value));
	    return;
	} 
	super.setProperty(element, name, value);
    }
    
   
    @Override
    public Object getProperty(UIObject element, String name) {
	javax.swing.JCheckBox xCheckBox = getCheckBox(element.getDelegate());
	if (xCheckBox == null) {
	    return null;
	}
	if (CheckBox.PROPERTY_VALUE.equals(name) || CheckBox.PROPERTY_SELECTED.equals(name)) {
	    return xCheckBox.isSelected();
	} 
	return super.getProperty(element, name);
    }

    
}
