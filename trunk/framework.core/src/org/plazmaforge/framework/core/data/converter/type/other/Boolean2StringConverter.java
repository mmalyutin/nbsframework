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
import org.plazmaforge.framework.core.data.formatter.type.BooleanFormatter;

public class Boolean2StringConverter implements Converter<Boolean, String> {

    
    private String format;
    
    private String[] values;
    
    
    public Boolean2StringConverter() {
	super();
    }

    public Boolean2StringConverter(String format) {
	this.format = format;
	this.values = format == null ? null : BooleanFormatter.parseFormat(format, true);
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String convert(Boolean source) {
	if (source == null) {
	    return null;
	}
	String trueValue = values == null ? BooleanFormatter.DEFAULT_VALUES[0] : values[0];
	String falseValue = values == null ? BooleanFormatter.DEFAULT_VALUES[1] : values[1];
	return source ? trueValue : falseValue;
    }

}
