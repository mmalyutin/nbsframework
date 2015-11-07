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
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.widget.IField;
import org.plazmaforge.framework.uwt.widget.TextBox;
import org.plazmaforge.framework.uwt.widget.TextControl;
import org.plazmaforge.framework.uwt.widget.TextField;

/**
 * @author ohapon
 *
 */
public class TextFieldBuilder extends AbstractFieldBuilder {

    @Override
    public boolean accept(String type) {
	return eq(type, UIBuilder.TEXT_FIELD_TYPE);
    }    


    @Override
    protected IField createField(IData data) {
	TextField field = new TextField();
	return field;
    }
    
    protected void populateValue(IData data, UIObject element) {
	
	TextControl textField = (TextControl) element;

	String value = getString(data, TextBox.PROPERTY_VALUE);
	if (value != null) {
	    textField.setValue(value);
	}
	
	// Property TEXT is priority than property VALUE
	String text = getString(data, TextBox.PROPERTY_TEXT);
	if (text != null) {
	    textField.setText(text);
	}
    }
}
