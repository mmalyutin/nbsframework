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

import java.util.Date;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.swing.widget.XDateTimeField;
import org.plazmaforge.framework.uwt.widget.DateTimeField;

public class SwingDateTimeFieldAdapter extends SwingControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = (java.awt.Container) getContent(parent.getDelegate());
	XDateTimeField xDateTimeField = new XDateTimeField();
	addToParent(xParent, xDateTimeField, element);
	return xDateTimeField;
    }

    protected XDateTimeField getDateTimeField(Object delegate) {
	return (XDateTimeField) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	XDateTimeField xDateTimeField = getDateTimeField(element.getDelegate());
	if (xDateTimeField == null) {
	    return;
	}
	if (DateTimeField.PROPERTY_VALUE.equals(name)) {
	    xDateTimeField.setDate((Date) value);
	    return;
	} else if (DateTimeField.PROPERTY_FORMAT.equals(name)) {
	    xDateTimeField.setPattern(getString(value));
	    return;
	} 
	super.setProperty(element, name, value);
    }
    
    @Override
    public Object getProperty(UIObject element, String name) {
	XDateTimeField xDateTimeField = getDateTimeField(element.getDelegate());
	if (xDateTimeField == null) {
	    return null;
	}
	if (DateTimeField.PROPERTY_VALUE.equals(name)) {
	    return xDateTimeField.getDate();
	}
	return super.getProperty(element, name);
    }


}
