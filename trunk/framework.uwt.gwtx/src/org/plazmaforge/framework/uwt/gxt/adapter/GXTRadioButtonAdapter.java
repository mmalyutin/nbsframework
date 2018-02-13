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
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.RadioButton;

import com.google.gwt.user.client.ui.HasWidgets;
import com.sencha.gxt.core.client.util.ToggleGroup;


/**
 * 
 * @author ohapon
 *
 */
public class GXTRadioButtonAdapter extends GXTCheckBoxAdapter {

    public Object createDelegate(UIElement parent, UIElement element) {
	HasWidgets xParent = (HasWidgets) parent.getDelegate();
	com.sencha.gxt.widget.core.client.form.Radio xRadioButton = new com.sencha.gxt.widget.core.client.form.Radio();
	
	// Initialize
	RadioButton radioButton = (RadioButton) element;
	String text = asSafeString(radioButton.getText());
	xRadioButton.setBoxLabel(text);
	xRadioButton.setHeight(20);
	
	 // Special add to parent
	xParent.add(xRadioButton);
	
	// Add to group
	ToggleGroup group = (ToggleGroup) parent.getData(GXTRadioGroupAdapter.SYS_PROPERTY_GROUP);
	group.add(xRadioButton);
	
	return xRadioButton;
    }

    protected com.sencha.gxt.widget.core.client.form.Radio getRadioButton(Object delegate) {
	return (com.sencha.gxt.widget.core.client.form.Radio) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	com.sencha.gxt.widget.core.client.form.Radio xRadioButton = getRadioButton(element.getDelegate());
	if (xRadioButton == null) {
	    return;
	}
	if (Button.PROPERTY_SELECTED.equals(name)) {
	    xRadioButton.setValue(asBoolean(value));
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }

}
