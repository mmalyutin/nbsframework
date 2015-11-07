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

package org.plazmaforge.framework.uwt.gwt;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

public class GWTUtils {
    
    
    public static NumberFormat createNumberFormat(String pattern) {
	if (pattern == null) {
	    return null;
	}
	pattern = pattern.trim();
	if (pattern.isEmpty()) {
	    return null;
	}
	NumberFormat format = NumberFormat.getFormat(pattern);
	if (format == null) {
	    return null;
	}
	String str = format.format(0);
	// Check format. If format return pattern string + '0' then the format is invalid.
	if ((pattern + "0").equals(str)) {
	    return null;
	}
	return format;
    }
    
    public static DateTimeFormat createDateTimeFormat(String pattern) {
	if (pattern == null){
	    return null;
	}
	pattern = pattern.trim();
	if (pattern.isEmpty()) {
	    return null;
	}
	DateTimeFormat format = DateTimeFormat.getFormat(pattern);
	if (format == null) {
	    return null;
	}
	
	// Check format. If format return pattern string then the format is invalid.
	String str = format.format(new Date());
	if (pattern.equals(str)) {
	    return null;
	}
	return format;
    }

}
