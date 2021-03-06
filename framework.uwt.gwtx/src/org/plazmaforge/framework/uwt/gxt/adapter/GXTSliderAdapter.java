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
import org.plazmaforge.framework.uwt.widget.Container;
import org.plazmaforge.framework.uwt.widget.Slider;

/**
 * 
 * @author ohapon
 *
 */
public class GXTSliderAdapter extends GXTControlAdapter {
   
    @Override
    public Object createDelegate(UIElement parent, UIElement element) {
	com.sencha.gxt.widget.core.client.Slider xSlider = new com.sencha.gxt.widget.core.client.Slider();
	addChild(getContent(parent.getDelegate()), xSlider, element); // Add to parent
	return xSlider;
    }

    protected com.sencha.gxt.widget.core.client.Slider getSlider(Object delegate) {
	return (com.sencha.gxt.widget.core.client.Slider) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	com.sencha.gxt.widget.core.client.Slider xSlider = getSlider(element.getDelegate());
	if (xSlider == null) {
	    return;
	}
	if (Slider.PROPERTY_VALUE.equals(name)) {
	    xSlider.setValue(intValue(value));
	    return;
	} else if (Container.PROPERTY_TOOL_TIP.equals(name)) {
	    //TODO:MIGRATION
	    // Use SliderCell.setToolTipConfig with anchor Side.RIGTH
	    return;
	}

	super.setProperty(element, name, value);
    }

    
    @Override
    public Object getProperty(UIElement element, String name) {
	com.sencha.gxt.widget.core.client.Slider xSlider = getSlider(element.getDelegate());
	if (xSlider == null) {
	    return null;
	}
	if (Slider.PROPERTY_VALUE.equals(name)) {
	    return xSlider.getValue();
	} 
	return super.getProperty(element, name);
    }

}
