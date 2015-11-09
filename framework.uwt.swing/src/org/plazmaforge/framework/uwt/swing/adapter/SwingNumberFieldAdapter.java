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

package org.plazmaforge.framework.uwt.swing.adapter;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.swing.widget.XNumberField;
import org.plazmaforge.framework.uwt.widget.NumberField;

public class SwingNumberFieldAdapter extends SwingControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = getContent(parent.getDelegate());
	XNumberField xNumberField = new XNumberField();
	addToParent(xParent, xNumberField, element);	
	return xNumberField;
    }

    protected XNumberField getNumberField(Object delegate) {
	return (XNumberField) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	XNumberField xNumberField = getNumberField(element.getDelegate());
	if (xNumberField == null) {
	    return;
	}
	if (NumberField.PROPERTY_VALUE.equals(name)) {
	    xNumberField.setValue((Number) value);
	    return;
	} else if (NumberField.PROPERTY_FORMAT.equals(name)) {
	    xNumberField.setPattern(getString(value));
	    return;
	}
	super.setProperty(element, name, value);
    }

    @Override
    public Object getProperty(UIObject element, String name) {
	XNumberField xNumberField = getNumberField(element.getDelegate());
	if (xNumberField == null) {
	    return null;
	}
	if (NumberField.PROPERTY_VALUE.equals(name)) {
	    return xNumberField.getValue();
	}
	return super.getProperty(element, name);
    }
}
