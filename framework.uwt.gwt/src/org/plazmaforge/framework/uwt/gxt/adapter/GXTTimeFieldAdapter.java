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
import org.plazmaforge.framework.uwt.widget.TimeField;
import com.google.gwt.i18n.client.DateTimeFormat;

public class GXTTimeFieldAdapter extends GXTControlAdapter {
    
    public Object createDelegate(UIObject parent, UIObject element) {
	com.extjs.gxt.ui.client.widget.form.TimeField xTimeField = new com.extjs.gxt.ui.client.widget.form.TimeField();
	addToParent(getContent(parent.getDelegate()), xTimeField, element); // Add to parent
	return xTimeField;
    }

    protected com.extjs.gxt.ui.client.widget.form.TimeField getTimeField(Object delegate) {
	return (com.extjs.gxt.ui.client.widget.form.TimeField) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	com.extjs.gxt.ui.client.widget.form.TimeField xTimeField = getTimeField(element.getDelegate());
	if (xTimeField == null) {
	    return;
	}
	if (TimeField.PROPERTY_VALUE.equals(name)) {
	    xTimeField.setDateValue((Date)value);
	    return;
	} else if (TimeField.PROPERTY_FORMAT.equals(name)) {
	    xTimeField.setFormat(DateTimeFormat.getFormat(getString(value)));
	    return;
	} 
	
	super.setProperty(element, name, value);
    }
    
    
    @Override
    public Object getProperty(UIObject element, String name) {
	com.extjs.gxt.ui.client.widget.form.TimeField xTimeField = getTimeField(element.getDelegate());
	if (xTimeField == null) {
	    return null;
	}
	if (TimeField.PROPERTY_VALUE.equals(name)) {
	    com.extjs.gxt.ui.client.widget.form.Time time = xTimeField.getValue();
	    if (time == null) {
		return null;
	    }
	    return time.getDate();
	} 
	return super.getProperty(element, name);
    }



}
