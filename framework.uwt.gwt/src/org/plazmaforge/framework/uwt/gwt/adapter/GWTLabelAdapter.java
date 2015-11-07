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
package org.plazmaforge.framework.uwt.gwt.adapter;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.Label;

public class GWTLabelAdapter extends GWTControlAdapter {
    
    public Object createDelegate(UIObject parent, UIObject element) {
	com.google.gwt.user.client.ui.Panel parentDelegate = (com.google.gwt.user.client.ui.Panel) getParentContent(parent.getDelegate());
	com.google.gwt.user.client.ui.Label delegate = new com.google.gwt.user.client.ui.Label();
	parentDelegate.add(delegate); // Add to parent
	return delegate;
    }

    protected com.google.gwt.user.client.ui.Label getLabel(Object delegate) {
	return (com.google.gwt.user.client.ui.Label) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.google.gwt.user.client.ui.Label label = getLabel(element.getDelegate());
	if (label == null) {
	    return;
	}
	if (Label.PROPERTY_TEXT.equals(name)) {
	    label.setText(value == null ? "" : (String) value);
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }


}
