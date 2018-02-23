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

package org.plazmaforge.framework.uwt.widget;

/**
 * 
 * @author ohapon
 *
 */
public abstract class TextControl extends AbstractField<String> implements HasText {
    
    
    public TextControl() {
	super();
    }

    public TextControl(String text) {
	setText(text);
    }
    
    public TextControl(int width) {
	setWidth(width);
    }

    public TextControl(String text, int width) {
	setText(text);
	setWidth(width);
    }
    

    public String getText() {
        return getValue();
    }

    public void setText(String text) {
        setValue(text);
        //fireChangeProperty(PROPERTY_TEXT, text);
    }   
    
    public void append(Object obj) {
	append(obj == null ? (String) null : obj.toString());
    }
    
    public void append(String str) {
	if  (str == null || str.isEmpty()) {
	    return;
	}
	String text = getText();
	if (text == null || text.isEmpty()) {
	    setText(str);
	    return;
	}
	setText(text + str);
    }

}
