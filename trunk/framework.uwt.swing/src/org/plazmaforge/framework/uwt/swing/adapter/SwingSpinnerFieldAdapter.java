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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.SpinnerField;

public class SwingSpinnerFieldAdapter extends SwingControlAdapter {


    public Object createDelegate(UIObject parent, UIObject element) {
	SpinnerField spinnerField = (SpinnerField) element;
	
	// Get values
	int decimals = spinnerField.getDecimals();
	String pattern = getNumberPattern(decimals);
	
	double value = spinnerField.doubleValue();
	double minValue = spinnerField.getMinValue();
	double maxValue = spinnerField.getMaxValue();
	double incrementValue = spinnerField.getIncrementValue();
	
	java.awt.Container xParent = getContent(parent.getDelegate());
	javax.swing.SpinnerNumberModel model = new javax.swing.SpinnerNumberModel(value, minValue, maxValue, incrementValue);
	javax.swing.JSpinner xSpinner = new  javax.swing.JSpinner(model);
	javax.swing.JSpinner.NumberEditor xEditor = new javax.swing.JSpinner.NumberEditor(xSpinner, pattern);
	xSpinner.setEditor(xEditor);
	addToParent(xParent, xSpinner, element);	
	return xSpinner;
    }

    protected javax.swing.JSpinner getSpinner(Object delegate) {
	return (javax.swing.JSpinner) delegate;
    }
    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	javax.swing.JSpinner xSpinner = getSpinner(element.getDelegate());
	if (xSpinner == null) {
	    return;
	}
	if (eq(SpinnerField.PROPERTY_VALUE, name)) {
	    xSpinner.setValue(value);
	    return;
	}
	super.setProperty(element, name, value);
    }


    @Override
    public Object getProperty(UIObject element, String name) {
	javax.swing.JSpinner xSpinner = getSpinner(element.getDelegate());
	if (xSpinner == null) {
	    return null;
	}
	if (eq(SpinnerField.PROPERTY_VALUE, name)) {
	    return xSpinner.getValue();
	}
	return super.getProperty(element, name);
    }

}
