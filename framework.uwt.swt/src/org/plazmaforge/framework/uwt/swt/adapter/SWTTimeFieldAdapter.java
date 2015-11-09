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
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.swt.widget.XTimeField;
import org.plazmaforge.framework.uwt.widget.TimeField;

public class SWTTimeFieldAdapter extends SWTControlAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	XTimeField xDateField = new XTimeField(xParent, SWT.BORDER);
	addToParent(xParent, xDateField, element);
	return xDateField;
    }

    protected XTimeField getTimeField(Object delegate) {
	return (XTimeField) delegate;
    }

    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	XTimeField xTimeField = getTimeField(element.getDelegate());
	if (xTimeField == null) {
	    return;
	}
	if (TimeField.PROPERTY_VALUE.equals(name)) {
	    xTimeField.setValue(value);
	    return;
	} else if (TimeField.PROPERTY_FORMAT.equals(name)) {
	    xTimeField.setPattern(getString(value));
	    return;
	}
	super.setProperty(element, name, value);
    }

    @Override
    public Object getProperty(UIObject element, String name) {
	XTimeField xTimeField = getTimeField(element.getDelegate());
	if (xTimeField == null) {
	    return null;
	}
	if (TimeField.PROPERTY_VALUE.equals(name)) {
	    return xTimeField.getValue();
	}
	return super.getProperty(element, name);
    }

}
