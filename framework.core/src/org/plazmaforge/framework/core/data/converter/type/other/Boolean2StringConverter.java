/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.core.data.converter.type.other;

import org.plazmaforge.framework.core.data.converter.Converter;

public class Boolean2StringConverter implements Converter<Boolean, String> {

    
    private String[] values;
    
    
    public Boolean2StringConverter() {
	super();
    }


    public Boolean2StringConverter(String[] values) {
	super();
	this.values = values;
	this.values = values;
	if (values != null) {
	    if (values.length < 2) {
		this.values = null;
	    } else {
		if (values[0] == null || values[1] == null) {
		    this.values = null;
		}
	    }
	}	
    }


    @Override
    public String convert(Boolean source) {
	if (source == null) {
	    return null;
	}
	String trueValue = values == null ? String2BooleanConverter.TRUE_FALSE[0] : values[0];
	String falseValue = values == null ? String2BooleanConverter.TRUE_FALSE[1] : values[1];
	return source ? trueValue : falseValue;
    }

}
