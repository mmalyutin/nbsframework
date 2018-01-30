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

import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.uwt.gxt.widget.cell.XDateCell;
import org.plazmaforge.framework.uwt.gxt.widget.cell.XNumberCell;

import com.google.gwt.cell.client.Cell;
//import com.google.gwt.cell.client.DateCell;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
//import com.sencha.gxt.cell.core.client.NumberCell;

/**
 * 
 * @author ohapon
 *
 */
public class GWTUtils {
    
    public static Cell<?> createCell(String type) {
	return createCell(type, null);
    }
    
    public static Cell<?> createCell(String type, String pattern) {
	if (type == null && pattern == null) {
	    return null;
	}
	Class klass = TypeUtils.getClass(type);
	if (klass == null  && pattern == null) {
	    return null;
	}
	
	if (klass == null) {
	    
	    NumberFormat formatter1 = createNumberFormat(pattern);
	    if (formatter1 != null) {
		XNumberCell<?> cell = createNumberCell(Number.class, formatter1);
		return cell;
	    }
	    
	    DateTimeFormat formatter2 = createDateTimeFormat(pattern);
	    if (formatter2 != null) {
		XDateCell cell = createDateCell(formatter2);
		return cell;
	    }
	    
	    return null;
	}
	
	if (TypeUtils.isLikeNumberType(type)) {
	    NumberFormat formatter = createNumberFormat(pattern);
	    XNumberCell<?> cell = createNumberCell(klass, formatter);
	    return cell;
	} else if (TypeUtils.isLikeDateType(type) || TypeUtils.isLikeDateType(type)) {
	    DateTimeFormat formatter = createDateTimeFormat(pattern);
	    XDateCell cell = createDateCell(formatter);
	    return cell;
	}
	
	return null;

    }
    
 
    public static <T extends Number> XNumberCell<T> createNumberCell(Class<T> type, NumberFormat format) {
	return format == null ? new XNumberCell<T>() : new XNumberCell<T>(format);
    }
    
    public static XDateCell createDateCell(DateTimeFormat format) {
	return format == null ? new XDateCell() : new XDateCell(format);
    }
    
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
