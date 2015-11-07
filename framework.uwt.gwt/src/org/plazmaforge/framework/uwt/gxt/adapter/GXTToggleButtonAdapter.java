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

public class GXTToggleButtonAdapter extends GXTButtonAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	com.extjs.gxt.ui.client.widget.button.ToggleButton xToggleButton = new com.extjs.gxt.ui.client.widget.button.ToggleButton();
	addToParent(getContent(parent.getDelegate()), xToggleButton, element); // Add to parent
	return xToggleButton;
    }

    protected com.extjs.gxt.ui.client.widget.button.ToggleButton getToggleButton(Object delegate) {
	return (com.extjs.gxt.ui.client.widget.button.ToggleButton) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.extjs.gxt.ui.client.widget.button.ToggleButton xToggleButton = getToggleButton(element.getDelegate());
	if (xToggleButton == null) {
	    return;
	}
	if (Button.PROPERTY_SELECTED.equals(name)) {
	    xToggleButton.toggle(getBoolean(value));
	    return;
	} else if (Button.PROPERTY_GROUP.equals(name)) {
	    xToggleButton.setToggleGroup(getString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }

}
