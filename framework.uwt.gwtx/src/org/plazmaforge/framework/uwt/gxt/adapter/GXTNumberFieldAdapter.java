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
import org.plazmaforge.framework.uwt.widget.NumberField;

import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;

/**
 * 
 * @author ohapon
 *
 */
public class GXTNumberFieldAdapter extends GXTControlAdapter {
    
    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	NumberField numberField = (NumberField) element;
	String dataType = numberField.getDataType();
	String format = numberField.getFormat();
	
	com.sencha.gxt.widget.core.client.form.NumberField<?> xNumberField = createNumberField(dataType, format);
	//delegate.setStyleAttribute("align", "right");
	addToParent(getContent(parent.getDelegate()), xNumberField, element); // Add to parent
	return xNumberField;
    }

    protected com.sencha.gxt.widget.core.client.form.NumberField<?> createNumberField(String dataType, String format) {
	NumberPropertyEditor<?> propertyEditor = createNumberPropertyEditor(dataType, format);
	com.sencha.gxt.widget.core.client.form.NumberField<?> xNumberField = new com.sencha.gxt.widget.core.client.form.NumberField(propertyEditor);
	return xNumberField;
    }
    
    protected com.sencha.gxt.widget.core.client.form.NumberField<?> getNumberField(Object delegate) {
	return (com.sencha.gxt.widget.core.client.form.NumberField<?>) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	com.sencha.gxt.widget.core.client.form.NumberField xNumberField = getNumberField(element.getDelegate());
	if (xNumberField == null) {
	    return;
	}
	if (NumberField.PROPERTY_VALUE.equals(name)) {
	    xNumberField.setValue(getNumber(value));
	    return;
	} if (NumberField.PROPERTY_FORMAT.equals(name)) {
	    String format = getString(value);
	    xNumberField.setFormat(getNumberFormat(format));
	    return;
	} if (NumberField.PROPERTY_DATA_TYPE.equals(name)) {
	    NumberField numberField = (NumberField) element;
	    String dataType = numberField.getDataType();
	    String format = numberField.getFormat();
		    
	    NumberPropertyEditor<?> propertyEditor = createNumberPropertyEditor(dataType, format);
	    xNumberField.setPropertyEditor(propertyEditor);
	    return;
	} 
	super.setProperty(element, name, value);
    }

    @Override
    public Object getProperty(UIObject element, String name) {
	com.sencha.gxt.widget.core.client.form.NumberField<?> xNumberField = getNumberField(element.getDelegate());
	if (xNumberField == null) {
	    return null;
	}
	if (NumberField.PROPERTY_VALUE.equals(name)) {
	    return xNumberField.getValue();
	} 
	return super.getProperty(element, name);
    }

}
