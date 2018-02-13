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
import org.plazmaforge.framework.uwt.gxt.widget.IXField;
import org.plazmaforge.framework.uwt.widget.IField;
import org.plazmaforge.framework.uwt.widget.TextArea;

/**
 * 
 * @author ohapon
 *
 */
public class GXTTextAreaAdapter extends GXTControlAdapter {
    
    public Object createDelegate(UIElement parent, UIElement element) {
	com.sencha.gxt.widget.core.client.form.TextArea xTextArea = new com.sencha.gxt.widget.core.client.form.TextArea();
	setSize(xTextArea, IField.DEFAULT_TEXT_WIDTH, IXField.DEFAULT_FIELD_HEIGHT * 3);
	addChild(getContent(parent.getDelegate()), xTextArea, element); // Add to parent
	return xTextArea;
    }

    protected com.sencha.gxt.widget.core.client.form.TextArea getTextArea(Object delegate) {
	return (com.sencha.gxt.widget.core.client.form.TextArea) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	com.sencha.gxt.widget.core.client.form.TextArea xTextArea = getTextArea(element.getDelegate());
	if (xTextArea == null) {
	    return;
	}
	if (TextArea.PROPERTY_VALUE.equals(name)) {
	    xTextArea.setValue(asSafeString(value));
	    return;
	} 
	super.setProperty(element, name, value);
	
    }

    @Override
    public Object getProperty(UIElement element, String name) {
	
	com.sencha.gxt.widget.core.client.form.TextArea xTextArea = getTextArea(element.getDelegate());
	if (xTextArea == null) {
	    return null;
	}
	if (TextArea.PROPERTY_VALUE.equals(name)) {
	    return xTextArea.getValue();
	} 
	
	return super.getProperty(element, name);
    }
}
