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

import java.util.List;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.IField;
import org.plazmaforge.framework.uwt.widget.RadioGroup;
import org.plazmaforge.framework.uwt.widget.Widget;

/**
 * 
 * @author ohapon
 *
 */
public class RadioGroupBuilder extends CompositeBuilder {
    
    @Override
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	RadioGroup radioGroup = new RadioGroup();
	populate(data, radioGroup);
	return radioGroup;
    }

    @Override
    protected void populateCommon(IData data, UIObject element) {
	super.populateCommon(data, element);
	
	RadioGroup radioGroup = (RadioGroup) element;
	
	String property = getString(data, Widget.PROPERTY_PROPERTY);
	if (property != null) {
	    radioGroup.setProperty(property);
	}
	String dataType = getString(data, Widget.PROPERTY_DATA_TYPE);
	if (dataType != null) {
	    radioGroup.setDataType(dataType);
	}
	
	////
	
	String displayProperty = getString(data, Widget.PROPERTY_DISPLAY_PROPERTY);
	if (displayProperty != null) {
	    radioGroup.setDisplayProperty(displayProperty);
	}

	populateFieldValue(data, radioGroup);
    }
    
    // See AbstractFieldBuilder
    protected void populateFieldValue(IData data, IField field) {
  	String dataType = field.getDataType();
  	Object value = getValue(dataType, data, Widget.PROPERTY_VALUE);
  	if (value != null) {
  	    field.setValue(value);
  	}
    }
    
    @Override
    protected void populateBody(IData data, UIObject element) {
   	RadioGroup radioGroup = (RadioGroup) element;
   	List<IData> children = getChildrenOfNode(data, RadioGroup.PROPERTY_RADIO_BUTTONS);
   	populateContentChildren(children, radioGroup);
    }
    

}
