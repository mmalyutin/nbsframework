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

package org.plazmaforge.framework.uwt.swt.adapter;

import org.eclipse.swt.SWT;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.swt.widget.XNumberField;
import org.plazmaforge.framework.uwt.widget.NumberField;

public class SWTNumberFieldAdapter extends SWTControlAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	XNumberField xNumberField = new XNumberField(xParent, SWT.BORDER);
	addChild(xParent, xNumberField, element);
	return xNumberField;
    }

    protected XNumberField getNumberField(Object delegate) {
	return (XNumberField) delegate;
    }

    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	XNumberField xNumberField = getNumberField(element.getDelegate());
	if (xNumberField == null) {
	    return;
	}
	if (NumberField.PROPERTY_VALUE.equals(name)) {
	    xNumberField.setValue(value);
	    return;
	} else if (NumberField.PROPERTY_FORMAT.equals(name)) {
	    xNumberField.setPattern(asString(value));
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }
    
    
    @Override
    public Object getProperty(UIElement element, String name) {
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
