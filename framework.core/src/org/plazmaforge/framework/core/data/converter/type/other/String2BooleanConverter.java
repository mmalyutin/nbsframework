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

public class String2BooleanConverter implements Converter<String, Boolean> {

    public static String[] Y_N = {"y", "n"};
    
    public static String[] YES_NO = {"yes", "no"};
    
    public static String[] TRUE_FALSE = {"true", "false"};
    
    public static String[] T_F = {"t", "f"};
    
    public static String[] N_1_0 = {"1", "0"};
    
    public static String[][] VALUES = {Y_N, YES_NO, TRUE_FALSE, T_F, N_1_0}; 

    
    private String[] values;
    
    public String2BooleanConverter() {
	super();
    }

    public String2BooleanConverter(String[] values) {
	super();
	this.values = values;
	if (values != null) {
	    if (values.length < 2 ){
		this.values = null;
	    } else {
		if (values[0] == null || values[1] == null) {
		    this.values = null;
		}
	    }
	}
    }

    @Override
    public Boolean convert(String source) {
	if (source == null) {
	    return null;
	}
	if (values != null) {
	    if (source.equalsIgnoreCase(values[0])) {
		return Boolean.TRUE;
	    }
	    if (source.equalsIgnoreCase(values[1])) {
		return Boolean.FALSE;
	    }
	    
	    return null;

	}
	for (String[] value: VALUES) {
	    if (source.equalsIgnoreCase(value[0])) {
		return Boolean.TRUE;
	    }
	    if (source.equalsIgnoreCase(value[1])) {
		return Boolean.FALSE;
	    }
	}
	
	return null;
    }
    
    

}
