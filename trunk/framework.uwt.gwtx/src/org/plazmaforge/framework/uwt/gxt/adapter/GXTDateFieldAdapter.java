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
import org.plazmaforge.framework.uwt.widget.HasFormat;

import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;

/**
 * 
 * @author ohapon
 *
 */
public class GXTDateFieldAdapter extends GXTControlAdapter {
    
    public Object createDelegate(UIObject parent, UIObject element) {
	String format = ((HasFormat) element).getFormat();
	com.sencha.gxt.widget.core.client.form.DateField xDateField = createDateField(format);
	addChild(getContent(parent.getDelegate()), xDateField, element); // Add to parent
	return xDateField;
    }

    protected com.sencha.gxt.widget.core.client.form.DateField createDateField(String format) {
	if (format == null) {
	    return new com.sencha.gxt.widget.core.client.form.DateField();
	}
 	return new com.sencha.gxt.widget.core.client.form.DateField(new DateTimePropertyEditor(format));
    }
    
    protected com.sencha.gxt.widget.core.client.form.DateField getDateField(Object delegate) {
	return (com.sencha.gxt.widget.core.client.form.DateField) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	com.sencha.gxt.widget.core.client.form.DateField xDateField = getDateField(element.getDelegate());
	if (xDateField == null) {
	    return;
	}
	if (DateField.PROPERTY_VALUE.equals(name)) {
	    xDateField.setValue((Date) value);
	    return;
	} else if (DateField.PROPERTY_FORMAT.equals(name)) {
	    String format = (String) value;
	    xDateField.setPropertyEditor(createDateTimePropertyEditor(format));
	    return;
	} 
	
	super.setProperty(element, name, value);
    }
    
    
    @Override
    public Object getProperty(UIObject element, String name) {
	com.sencha.gxt.widget.core.client.form.DateField xDateField = getDateField(element.getDelegate());
	if (xDateField == null) {
	    return null;
	}
	if (DateField.PROPERTY_VALUE.equals(name)) {
	    return xDateField.getValue();
	} 
	return super.getProperty(element, name);
    }


}
