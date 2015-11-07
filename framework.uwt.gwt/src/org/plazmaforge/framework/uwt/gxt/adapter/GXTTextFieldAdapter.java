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
import org.plazmaforge.framework.uwt.widget.IField;
import org.plazmaforge.framework.uwt.widget.TextField;

public class GXTTextFieldAdapter extends GXTControlAdapter {
    
    public Object createDelegate(UIObject parent, UIObject element) {
	com.extjs.gxt.ui.client.widget.form.TextField<String> xTextField = new com.extjs.gxt.ui.client.widget.form.TextField<String>();
	xTextField.setWidth(IField.DEFAULT_TEXT_WIDTH);
	addToParent(getContent(parent.getDelegate()), xTextField, element); // Add to parent
	return xTextField;
    }

    protected com.extjs.gxt.ui.client.widget.form.TextField<String> getTextField(Object delegate) {
	return (com.extjs.gxt.ui.client.widget.form.TextField<String>) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.extjs.gxt.ui.client.widget.form.TextField<String> xTextField = getTextField(element.getDelegate());
	if (xTextField == null) {
	    return;
	}
	if (TextField.PROPERTY_VALUE.equals(name)) {
	    xTextField.setValue(getSafeString(value));
	    return;
	} 
	
	super.setProperty(element, name, value);
	
    }

    
    @Override
    public Object getProperty(UIObject element, String name) {
	
	com.extjs.gxt.ui.client.widget.form.TextField<String> xTextField = getTextField(element.getDelegate());
	if (xTextField == null) {
	    return null;
	}
	if (TextField.PROPERTY_VALUE.equals(name)) {
	    return xTextField.getValue();
	} 
	
	return super.getProperty(element, name);
    }

    
}
