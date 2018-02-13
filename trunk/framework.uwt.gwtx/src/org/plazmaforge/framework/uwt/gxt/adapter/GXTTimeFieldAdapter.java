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

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.TimeField;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;

/**
 * 
 * @author ohapon
 *
 */
public class GXTTimeFieldAdapter extends GXTControlAdapter {
    
    @Override
    public Object createDelegate(UIElement parent, UIElement element) {
	com.sencha.gxt.widget.core.client.form.TimeField xTimeField = new com.sencha.gxt.widget.core.client.form.TimeField();
	
	xTimeField.setTriggerAction(TriggerAction.ALL); // Important to correct selection a item by click DOWN button
	addChild(getContent(parent.getDelegate()), xTimeField, element); // Add to parent
	return xTimeField;
    }

    protected com.sencha.gxt.widget.core.client.form.TimeField getTimeField(Object delegate) {
	return (com.sencha.gxt.widget.core.client.form.TimeField) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	com.sencha.gxt.widget.core.client.form.TimeField xTimeField = getTimeField(element.getDelegate());
	if (xTimeField == null) {
	    return;
	}
	if (TimeField.PROPERTY_VALUE.equals(name)) {
	    xTimeField.setValue(asDate(value));
	    return;
	} else if (TimeField.PROPERTY_FORMAT.equals(name)) {
	    xTimeField.setFormat(DateTimeFormat.getFormat(asString(value)));
	    return;
	} 
	
	super.setProperty(element, name, value);
    }
    
    
    @Override
    public Object getProperty(UIElement element, String name) {
	com.sencha.gxt.widget.core.client.form.TimeField xTimeField = getTimeField(element.getDelegate());
	if (xTimeField == null) {
	    return null;
	}
	if (TimeField.PROPERTY_VALUE.equals(name)) {
	   return xTimeField.getValue();
	} 
	return super.getProperty(element, name);
    }



}
