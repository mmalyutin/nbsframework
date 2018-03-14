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
import org.plazmaforge.framework.uwt.widget.SpinnerField;

import javafx.scene.control.SpinnerValueFactory;


/**
 * 
 * @author ohapon
 *
 */
public class JFXSpinnerFieldAdapter extends JFXControlAdapter {
   
    @Override
    public Object createDelegate(UIElement parent, UIElement element) {
	
	SpinnerField spinnerField = (SpinnerField) element;
	
	// Get values
	int decimals = spinnerField.getDecimals();
	
	String dataType = null;
	Number value = spinnerField.doubleValue();
	Number minValue = spinnerField.getMinValue();
	Number maxValue = spinnerField.getMaxValue();
	Number incrementValue = spinnerField.getIncrementValue();
		
	if (decimals > 0) {
	    dataType =  "Double";
	} else {
	    dataType = "Integer";
	}
	
	SpinnerValueFactory valueFactory = createSpinnerValueFactory(dataType, value, minValue, maxValue, incrementValue);
	javafx.scene.control.Spinner xSpinner = new javafx.scene.control.Spinner();
	xSpinner.setValueFactory(valueFactory);
	
	addChild(getContent(parent.getDelegate()), xSpinner, element); // Add to parent
	return xSpinner;
    }
    
   

    protected javafx.scene.control.Spinner asSpinner(Object delegate) {
	return (javafx.scene.control.Spinner) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	
	javafx.scene.control.Spinner xSpinner = asSpinner(element.getDelegate());
	if (xSpinner == null) {
	    return;
	}
	if (SpinnerField.PROPERTY_VALUE.equals(name)) {
	    SpinnerValueFactory valueFactory = xSpinner.getValueFactory();
	    Class<Number> type = (Class<Number>) JFXHelper.getDataType(valueFactory);
	    Number numberValue = toNumber((Number) value, type);
	    xSpinner.getValueFactory().setValue(numberValue);
	    return;
	} 
	
	super.setProperty(element, name, value);
	
    }

    @Override
    public Object getProperty(UIElement element, String name) {
	javafx.scene.control.Spinner xSpinner = asSpinner(element.getDelegate());
	if (xSpinner == null) {
	    return null;
	}
	if (SpinnerField.PROPERTY_VALUE.equals(name)) {
	    return xSpinner.getValue();
	} 
	return super.getProperty(element, name);
	
    }

}
