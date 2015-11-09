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
import org.plazmaforge.framework.uwt.widget.SpinnerField;

public class SWTSpinnerFieldAdapter extends SWTControlAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	
	SpinnerField spinnerField = (SpinnerField) element;
	
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	
	// Get values
	int decimals = spinnerField.getDecimals();	
	
	int value = toSWTValue(spinnerField.doubleValue(), decimals);
	int minValue = toSWTValue(spinnerField.getMinValue(), decimals);
	int maxValue = toSWTValue(spinnerField.getMaxValue(), decimals);
	int incrementValue = toSWTValue(spinnerField.getIncrementValue(), decimals);
	
	int pageIncrement = 10; // SWT default value
	
	org.eclipse.swt.widgets.Spinner xSpinner = new org.eclipse.swt.widgets.Spinner(xParent, SWT.BORDER);
	xSpinner.setValues(value, minValue, maxValue, decimals, incrementValue, pageIncrement);
	addToParent(xParent, xSpinner, element);
	return xSpinner;
    }

    
    private int toSWTValue(Object value, int decimals) {
	Number number = (Number) value;
	return toSWTValue(number == null ? 0d : number.doubleValue(), decimals);
    }
    
    private int toSWTValue(double value, int decimals) {
	if (decimals == 0) {
	    return round(value);
	}
	return round(value * n(decimals)); 
    }

    private double toUWTValue(int value, int decimals) {
	if (value == 0 || decimals == 0) {
	    return value;
	}
	return (double) value / n(decimals); 
    }

    private int n(int count) {
	if (count == 0) {
	    return 0;
	}
	int value = 1;
	for (int i = 0; i < count; i++) {
	    value *= 10;
	}
	return value;
    }
    
    private int round(double value) {
	// TODO
	return (int) value;
    }
    
    protected org.eclipse.swt.widgets.Spinner getSpinner(Object delegate) {
	return (org.eclipse.swt.widgets.Spinner) delegate;
    }

    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	SpinnerField spinnerField = (SpinnerField) element;
	int decimals = spinnerField.getDecimals();
	org.eclipse.swt.widgets.Spinner xSpinner = getSpinner(element.getDelegate());
	if (xSpinner == null) {
	    return;
	}
	if (SpinnerField.PROPERTY_VALUE.equals(name)) {
	    xSpinner.setSelection(toSWTValue(value, decimals));
	    return;
	}
	
	super.setProperty(element, name, value);
    }


    @Override
    public Object getProperty(UIObject element, String name) {
	SpinnerField spinnerField = (SpinnerField) element;
	int decimals = spinnerField.getDecimals();
	org.eclipse.swt.widgets.Spinner xSpinner = getSpinner(element.getDelegate());
	if (xSpinner == null) {
	    return null;
	}
	if (SpinnerField.PROPERTY_VALUE.equals(name)) {
	    return toUWTValue(xSpinner.getSelection(), decimals);
	}
	return super.getProperty(element, name);
    }

}
