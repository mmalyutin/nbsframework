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

/**
 * 
 */
package org.plazmaforge.framework.uwt.builder.widget;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.widget.IField;
import org.plazmaforge.framework.uwt.widget.Widget;

/**
 * @author ohapon
 *
 */
public abstract class AbstractFieldBuilder extends ControlBuilder {

    protected abstract IField createField(IData data);
    
    protected abstract void populateValue(IData data, UIObject element);

  
    protected void populateFieldValue(IData data, IField field) {
  	String dataType = field.getDataType();
  	Object value = getValue(dataType, data, Widget.PROPERTY_VALUE);
  	if (value != null) {
  	    field.setValue(value);
  	}
    }
    
    public UIObject buildObject(IData data) {
	if (data == null) {
	    return null;
	}
	IField field = createField(data);
	if (field == null) {
	    return null;
	}
	UIObject element = (UIObject) field;
	populate(data, element);
	return element;
    }    

    @Override
    protected void populate(IData data, UIObject element) {
	super.populate(data, element);
	
	IField field = (IField) element;
	
	String property = getString(data, Widget.PROPERTY_PROPERTY);
	if (property != null) {
	    field.setProperty(property);
	}
	String dataType = getString(data, Widget.PROPERTY_DATA_TYPE);
	if (dataType != null) {
	    field.setDataType(dataType);
	}
	
	populateValue(data, element);
    }
    
}
