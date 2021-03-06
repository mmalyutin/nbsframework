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

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.swing.widget.XTimeField;
import org.plazmaforge.framework.uwt.widget.TimeField;

public class SwingTimeFieldAdapter extends SwingControlAdapter {


    public Object createDelegate(UIElement parent, UIElement element) {
	java.awt.Container xParent = (java.awt.Container) getContent(parent.getDelegate());
	XTimeField xTimeField = new XTimeField();
	addChild(xParent, xTimeField, element);
	return xTimeField;
    }

    protected XTimeField getTimeField(Object delegate) {
	return (XTimeField) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	XTimeField xTimeField = getTimeField(element.getDelegate());
	if (xTimeField == null) {
	    return;
	}
	if (eq(TimeField.PROPERTY_VALUE, name)) {
	    xTimeField.setDate((Date) value);
	    return;
	} else if (eq(TimeField.PROPERTY_FORMAT, name)) {
	    xTimeField.setPattern(asString(value));
	    return;
	} 
	super.setProperty(element, name, value);
    }
    
    @Override
    public Object getProperty(UIElement element, String name) {
	XTimeField xTimeField = getTimeField(element.getDelegate());
	if (xTimeField == null) {
	    return null;
	}
	if (eq(TimeField.PROPERTY_VALUE, name)) {
	    return xTimeField.getDate();
	}
	return super.getProperty(element, name);
    }

}
