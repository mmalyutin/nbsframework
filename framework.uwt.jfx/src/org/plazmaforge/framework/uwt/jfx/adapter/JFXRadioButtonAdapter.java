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
import org.plazmaforge.framework.uwt.widget.Button;
import org.plazmaforge.framework.uwt.widget.CheckBox;
import org.plazmaforge.framework.uwt.widget.RadioButton;


/**
 * 
 * @author ohapon
 *
 */
public class JFXRadioButtonAdapter extends JFXButtonAdapter /*JFXCheckBoxAdapter*/ {

    public Object createDelegate(UIElement parent, UIElement element) {
	javafx.scene.Parent xParent = getContent(parent.getDelegate());
	javafx.scene.control.RadioButton xRadioButton = new javafx.scene.control.RadioButton();
	
	// Initialize
	RadioButton radioButton = (RadioButton) element;
	String text = asSafeString(radioButton.getText());
	xRadioButton.setText(text);
	
	 // Add to parent
	addChild(xParent, xRadioButton, element);
	
	// Add to group
	javafx.scene.control.ToggleGroup group = (javafx.scene.control.ToggleGroup) parent.getData(JFXRadioGroupAdapter.SYS_PROPERTY_GROUP);
	xRadioButton.setToggleGroup(group);
	
	return xRadioButton;
    }

    protected javafx.scene.control.RadioButton getRadioButton(Object delegate) {
	return (javafx.scene.control.RadioButton) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	javafx.scene.control.RadioButton xRadioButton = getRadioButton(element.getDelegate());
	if (xRadioButton == null) {
	    return;
	}
	if (Button.PROPERTY_SELECTED.equals(name)) {
	    xRadioButton.setSelected(asBoolean(value));
	    return;
	}
	
	super.setProperty(element, name, value);
	
    }

    @Override
    public Object getProperty(UIElement element, String name) {
	
	javafx.scene.control.RadioButton xRadioButton = getRadioButton(element.getDelegate());
	if (xRadioButton == null) {
	    return null;
	}
	if (CheckBox.PROPERTY_VALUE.equals(name)) {
	    return xRadioButton.isSelected();
	}
	return super.getProperty(element, name);
     }
    
    
}
