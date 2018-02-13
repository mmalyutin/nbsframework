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

package org.plazmaforge.framework.uwt.builder.widget;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.widget.IField;
import org.plazmaforge.framework.uwt.widget.Slider;

public class SliderBuilder extends NumberFieldBuilder {

    @Override
    public boolean accept(String type) {
	return eq(type, UIBuilder.SLIDER_TYPE);
    } 
    
    @Override
    protected IField createField(IData data) {
	return new Slider();
    }

    protected void populateValue(IData data, UIElement element) {
	Slider slider = (Slider) element;
	Integer value = getInteger(data, Slider.PROPERTY_VALUE);
	if (value != null) {
	    slider.setValue(value);
	}
    }

    protected void populateFormat(IData data, UIElement element) {
	// do nothing
    }

}
