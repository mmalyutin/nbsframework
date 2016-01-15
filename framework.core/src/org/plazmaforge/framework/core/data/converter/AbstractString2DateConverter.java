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

package org.plazmaforge.framework.core.data.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.plazmaforge.framework.util.StringUtils;


/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractString2DateConverter<T> extends AbstractStringConverter<String, T> {

    public static final String DEFAULT_DATE_FROMAT = "yyyy-MM-dd";
    
    public static final String DEFAULT_TIME_FROMAT = "HH:mm:ss";
    
    public static final String DEFAULT_DATE_TIME_FROMAT = "yyyy-MM-dd HH:mm:ss";
    
	    
    private DateFormat formatter;
    
    
    public AbstractString2DateConverter() {
	super();
    }


    public AbstractString2DateConverter(String format) {
	super(format);
	formatter = createFormatter(format);
    }

    protected DateFormat createFormatter(String format) {
	return new SimpleDateFormat(StringUtils.isEmpty(format, true) ? DEFAULT_DATE_FROMAT : format);
    }


    public DateFormat getFormatter() {
        return formatter;
    }
    
    protected Date parseDate(String source, DateFormat formatter) {
	//TODO
	//return DateUtils.parseDate(source, type, formatter, false);
	if (source == null) {
	    return null;
	}
	try {
	    return formatter.parse(source);
	} catch (ParseException ex) {
	    throw new IllegalArgumentException("Could not parse date: " + ex.getMessage());
	}	
    }
    
}
