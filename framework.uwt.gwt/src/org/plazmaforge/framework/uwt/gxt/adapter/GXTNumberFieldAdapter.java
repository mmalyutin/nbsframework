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

import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.NumberField;

import com.google.gwt.i18n.client.NumberFormat;

public class GXTNumberFieldAdapter extends GXTControlAdapter {
    
    public Object createDelegate(UIObject parent, UIObject element) {
	com.extjs.gxt.ui.client.widget.form.NumberField xNumberField = createNumberField();
	//delegate.setStyleAttribute("align", "right");
	addToParent(getContent(parent.getDelegate()), xNumberField, element); // Add to parent
	return xNumberField;
    }

    protected com.extjs.gxt.ui.client.widget.form.NumberField createNumberField() {
	com.extjs.gxt.ui.client.widget.form.NumberField xNumberField = new com.extjs.gxt.ui.client.widget.form.NumberField();
	return xNumberField;
    }
    
    protected com.extjs.gxt.ui.client.widget.form.NumberField getNumberField(Object delegate) {
	return (com.extjs.gxt.ui.client.widget.form.NumberField) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	com.extjs.gxt.ui.client.widget.form.NumberField xNumberField = getNumberField(element.getDelegate());
	if (xNumberField == null) {
	    return;
	}
	if (NumberField.PROPERTY_VALUE.equals(name)) {
	    xNumberField.setValue(getNumber(value));
	    return;
	} if (NumberField.PROPERTY_FORMAT.equals(name)) {
	    xNumberField.setFormat(NumberFormat.getFormat(getString(value)));
	    return;
	} if (NumberField.PROPERTY_DATA_TYPE.equals(name)) {
	    NumberField numberField = (NumberField) element;
	    Class<?> dataClass = TypeUtils.getClass(numberField.getDataType());
	    xNumberField.setPropertyEditorType(dataClass);
	    return;
	} 
	super.setProperty(element, name, value);
    }

    @Override
    public Object getProperty(UIObject element, String name) {
	com.extjs.gxt.ui.client.widget.form.NumberField xNumberField = getNumberField(element.getDelegate());
	if (xNumberField == null) {
	    return null;
	}
	if (NumberField.PROPERTY_VALUE.equals(name)) {
	    return xNumberField.getValue();
	} 
	return super.getProperty(element, name);
    }

}
