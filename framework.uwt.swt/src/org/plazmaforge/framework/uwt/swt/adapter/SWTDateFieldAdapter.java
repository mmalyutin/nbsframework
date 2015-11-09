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

import java.util.Date;

import org.eclipse.swt.SWT;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.swt.widget.XDateField;
import org.plazmaforge.framework.uwt.widget.DateField;

public class SWTDateFieldAdapter extends SWTControlAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	XDateField xDateField = new XDateField(xParent, SWT.BORDER);
	addToParent(xParent, xDateField, element);
	return xDateField;
    }

    protected XDateField getDateField(Object delegate) {
	return (XDateField) delegate;
    }

    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	XDateField xDateField = getDateField(element.getDelegate());
	if (xDateField == null) {
	    return;
	}
	if (DateField.PROPERTY_VALUE.equals(name)) {
	    xDateField.setDate((Date) value);
	    return;
	} else if (DateField.PROPERTY_FORMAT.equals(name)) {
	    xDateField.setPattern(getString(value));
	    return;
	}
	super.setProperty(element, name, value);
    }

    @Override
    public Object getProperty(UIObject element, String name) {
	XDateField xDateField = getDateField(element.getDelegate());
	if (xDateField == null) {
	    return null;
	}
	if (DateField.PROPERTY_VALUE.equals(name)) {
	    return xDateField.getValue();
	}
	return super.getProperty(element, name);
    }
}
