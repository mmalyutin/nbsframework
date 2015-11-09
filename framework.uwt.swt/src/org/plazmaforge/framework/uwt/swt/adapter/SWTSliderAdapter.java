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
import org.plazmaforge.framework.uwt.widget.Slider;

public class SWTSliderAdapter extends SWTControlAdapter {

    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	org.eclipse.swt.widgets.Scale xSlider = new org.eclipse.swt.widgets.Scale(xParent, SWT.NONE);
	addToParent(xParent, xSlider, element);
	return xSlider;
    }

    protected org.eclipse.swt.widgets.Scale getSlider(Object delegate) {
	return (org.eclipse.swt.widgets.Scale) delegate;
    }

    
    @Override
    public void setProperty(UIObject element, String name, Object value) {
	org.eclipse.swt.widgets.Scale xSlider = getSlider(element.getDelegate());
	if (xSlider == null) {
	    return;
	}
	if (Slider.PROPERTY_VALUE.equals(name)) {
	    xSlider.setSelection(intValue(value));
	    return;
	}
	super.setProperty(element, name, value);
    }


    @Override
    public Object getProperty(UIObject element, String name) {
	org.eclipse.swt.widgets.Scale xSlider = getSlider(element.getDelegate());
	if (xSlider == null) {
	    return null;
	}
	if (Slider.PROPERTY_VALUE.equals(name)) {
	    return xSlider.getSelection();
	}
	return super.getProperty(element, name);
    }

}
