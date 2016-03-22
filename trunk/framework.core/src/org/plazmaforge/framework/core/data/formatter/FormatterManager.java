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

package org.plazmaforge.framework.core.data.formatter;


/**
 * 
 * @author ohapon
 *
 */
public class FormatterManager extends FormatterFactory {

    public String toString(Object value, String type) {
	if (value == null) {
	    return null;
	}
	Formatter formatter = getFormatter(type);
	if (formatter == null) {
	    return null;
	}
	return formatter.format(value);
    }
    
    public Object toValue(String value, String type) {
	if (value == null) {
	    return null;
	}
	Formatter formatter = getFormatter(type);
	if (formatter == null) {
	    return null;
	}
	return formatter.parse(value);
    }
    
    
}
