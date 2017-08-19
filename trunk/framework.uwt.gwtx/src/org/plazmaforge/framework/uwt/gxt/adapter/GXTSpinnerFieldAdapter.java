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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.SpinnerField;

import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;

/**
 * 
 * @author ohapon
 *
 */
public class GXTSpinnerFieldAdapter extends GXTControlAdapter {
   
    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	
	SpinnerField spinnerField = (SpinnerField) element;
	
	// Get values
	int decimals = spinnerField.getDecimals();
	String pattern = getNumberPattern(decimals);
	
	Double value = spinnerField.doubleValue();
	Double minValue = spinnerField.getMinValue();
	Double maxValue = spinnerField.getMaxValue();
	Double incrementValue = spinnerField.getIncrementValue();
		
	String dataType = null;
	Number nValue = null;
	Number nMinValue = null;
	Number nMaxValue = null;
	Number nIncrementValue = null;
	
	if (decimals > 0) {
	    dataType =  "Double";
	    nValue = value;
	    nMinValue = minValue;
	    nMaxValue = maxValue;
	    nIncrementValue = incrementValue;
	} else {
	    dataType = "Integer";
	    nValue = value.intValue();
	    nMinValue = minValue.intValue();
	    nMaxValue = maxValue.intValue();
	    nIncrementValue = incrementValue.intValue();
	}
	
	NumberPropertyEditor<?> propertyEditor = createNumberPropertyEditor(dataType, pattern);
	
	com.sencha.gxt.widget.core.client.form.SpinnerField xSpinner = new com.sencha.gxt.widget.core.client.form.SpinnerField(propertyEditor);
	
	//xSpinner.setFormat(NumberFormat.getFormat(pattern));
	
	xSpinner.setValue(nValue);
	xSpinner.setMinValue(nMinValue);
	xSpinner.setMaxValue(nMaxValue);
	xSpinner.setIncrement(nIncrementValue);
	
	addToParent(getContent(parent.getDelegate()), xSpinner, element); // Add to parent
	return xSpinner;
    }
    
   

    protected com.sencha.gxt.widget.core.client.form.SpinnerField getSpinnerField(Object delegate) {
	return (com.sencha.gxt.widget.core.client.form.SpinnerField) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
	com.sencha.gxt.widget.core.client.form.SpinnerField xSpinner = getSpinnerField(element.getDelegate());
	if (xSpinner == null) {
	    return;
	}
	if (SpinnerField.PROPERTY_VALUE.equals(name)) {
	    xSpinner.setValue((Number) value);
	    return;
	} 
	
	super.setProperty(element, name, value);
	
    }

    @Override
    public Object getProperty(UIObject element, String name) {
	com.sencha.gxt.widget.core.client.form.SpinnerField xSpinner = getSpinnerField(element.getDelegate());
	if (xSpinner == null) {
	    return null;
	}
	if (SpinnerField.PROPERTY_VALUE.equals(name)) {
	    return xSpinner.getValue();
	} 
	return super.getProperty(element, name);
	
    }

}
