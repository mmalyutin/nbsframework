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

package org.plazmaforge.framework.uwt.jfx.adapter;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.jfx.widget.XAbstractNumberField;
import org.plazmaforge.framework.uwt.jfx.widget.XNumberField;
import org.plazmaforge.framework.uwt.widget.NumberField;


/**
 * 
 * @author ohapon
 *
 */
public class JFXNumberFieldAdapter extends JFXControlAdapter {
    
    @Override
    public Object createDelegate(UIElement parent, UIElement element) {
	NumberField numberField = (NumberField) element;
	String dataType = numberField.getDataType();
	String format = numberField.getFormat();
	
	XAbstractNumberField<?> xNumberField = createNumberField(dataType, format);
	//delegate.setStyleAttribute("align", "right");
	addChild(getContent(parent.getDelegate()), xNumberField, element); // Add to parent
	return xNumberField;
    }

    protected XAbstractNumberField<?> createNumberField(String dataType, String format) {
	XNumberField xNumberField = new XNumberField();
	return xNumberField;
    }
    
    protected XAbstractNumberField<?> asNumberField(Object delegate) {
	return (XAbstractNumberField<?>) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	XAbstractNumberField xNumberField = asNumberField(element.getDelegate());
	if (xNumberField == null) {
	    return;
	}
	if (NumberField.PROPERTY_VALUE.equals(name)) {
	    xNumberField.setValue(asNumber(value));
	    return;
	} if (NumberField.PROPERTY_FORMAT.equals(name)) {
	    String format = asString(value);
	    xNumberField.setFormat(format);
	    return;
	} if (NumberField.PROPERTY_DATA_TYPE.equals(name)) {
	    NumberField numberField = (NumberField) element;
	    String dataType = numberField.getDataType();
	    String format = numberField.getFormat();
		    
	    //TODO
	    //NumberPropertyEditor<?> propertyEditor = createNumberPropertyEditor(dataType, format);
	    //xNumberField.setPropertyEditor(propertyEditor);
	    return;
	} 
	super.setProperty(element, name, value);
    }

    @Override
    public Object getProperty(UIElement element, String name) {
	XAbstractNumberField<?> xNumberField = asNumberField(element.getDelegate());
	if (xNumberField == null) {
	    return null;
	}
	if (NumberField.PROPERTY_VALUE.equals(name)) {
	    return xNumberField.getValue();
	} 
	return super.getProperty(element, name);
    }

}
