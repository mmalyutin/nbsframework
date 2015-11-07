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

import java.util.Date;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.DateField;

import com.google.gwt.i18n.client.DateTimeFormat;

public class GXTDateFieldAdapter extends GXTControlAdapter {
    
    public Object createDelegate(UIObject parent, UIObject element) {
	com.extjs.gxt.ui.client.widget.form.DateField xDateField = new com.extjs.gxt.ui.client.widget.form.DateField();
	addToParent(getContent(parent.getDelegate()), xDateField, element); // Add to parent
	return xDateField;
    }

    protected com.extjs.gxt.ui.client.widget.form.DateField getDateField(Object delegate) {
	return (com.extjs.gxt.ui.client.widget.form.DateField) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	com.extjs.gxt.ui.client.widget.form.DateField xDateField = getDateField(element.getDelegate());
	if (xDateField == null) {
	    return;
	}
	if (DateField.PROPERTY_VALUE.equals(name)) {
	    xDateField.setValue((Date)value);
	    return;
	} else if (DateField.PROPERTY_FORMAT.equals(name)) {
	    xDateField.getPropertyEditor().setFormat(DateTimeFormat.getFormat(getString(value)));
	    return;
	} 
	
	super.setProperty(element, name, value);
    }
    
    
    @Override
    public Object getProperty(UIObject element, String name) {
	com.extjs.gxt.ui.client.widget.form.DateField xDateField = getDateField(element.getDelegate());
	if (xDateField == null) {
	    return null;
	}
	if (DateField.PROPERTY_VALUE.equals(name)) {
	    return xDateField.getValue();
	} 
	return super.getProperty(element, name);
    }


}
