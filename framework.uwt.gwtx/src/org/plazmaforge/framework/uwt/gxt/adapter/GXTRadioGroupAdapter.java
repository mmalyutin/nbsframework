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

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.Container;

import com.sencha.gxt.core.client.util.ToggleGroup;

/**
 * 
 * @author ohapon
 *
 */
public class GXTRadioGroupAdapter extends GXTContainerAdapter {

    public static final String SYS_PROPERTY_GROUP = "$group"; 
    
    public Object createDelegate(UIElement parent, UIElement element) {
	com.google.gwt.user.client.ui.HorizontalPanel xRadioGroup = new com.google.gwt.user.client.ui.HorizontalPanel();
	//HorizontalLayoutContainer xRadioGroup = new HorizontalLayoutContainer();
	
	// Create special ToggleGroup to grouping radio buttons
	ToggleGroup group = new ToggleGroup();
	element.setData(SYS_PROPERTY_GROUP, group);
	
	addChild(getContent(parent.getDelegate()), xRadioGroup, element);
	
	return xRadioGroup;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	Object delegate = element.getDelegate();
	if (delegate == null) {
	    return;
	}

	if (Container.PROPERTY_LAYOUT.equals(name)) {
	    // ignore
	    return;
	}	
	
	super.setProperty(element, name, value);
    }
}
