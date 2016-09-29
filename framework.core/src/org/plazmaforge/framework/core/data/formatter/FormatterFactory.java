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

package org.plazmaforge.framework.core.data.formatter;

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.type.Types;
import org.plazmaforge.framework.core.data.formatter.type.RWByteFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDateFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDateTimeFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDoubleFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWFloatFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWIntegerFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWShortFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWStringFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWTimeFormatter;

/**
 * Formatter Factory
 * 
 * @author ohapon
 *
 */
public class FormatterFactory {


    private Map<String, Formatter<?>> formatters = new HashMap<String, Formatter<?>>();
    
    public FormatterFactory() {
	super();
    }

    public void registerFormatter(String type, Formatter<?> formatter) {
	formatters.put(type, formatter);
    }

    public void unregisterFormatter(String type) {
	formatters.remove(type);
    }
    
    public void registerDefaultFormatters() {
	registerFormatter(Types.StringType, new RWStringFormatter());
	registerFormatter(Types.TextType, new RWStringFormatter());
	registerFormatter(Types.BooleanType, new RWByteFormatter());
	registerFormatter(Types.ShortType, new RWShortFormatter());
	registerFormatter(Types.IntegerType, new RWIntegerFormatter());
	registerFormatter(Types.FloatType, new RWFloatFormatter());
	registerFormatter(Types.DoubleType, new RWDoubleFormatter());
	registerFormatter(Types.DateType, new RWDateFormatter());
	registerFormatter(Types.TimeType, new RWTimeFormatter());
	registerFormatter(Types.DateTimeType, new RWDateTimeFormatter());
    }
    
    
    public Formatter<?> getFormatter(String type) {
	return getFormatter(type, null);
    }

    public Formatter<?> getFormatter(String type, String format) {
	if (type == null) {
	   return null; 
	}
	if (format == null) {
	    return doGetFormatter(type); 
	}

	// TODO: 
	// by path: <type>::<format>
	String path = getFormatterPath(type, format);
	Formatter<?> formatter = doGetFormatter(path);
	if (formatter != null) {
	    return formatter;
	}
	// by type
	formatter = doGetFormatter(type);
	if (formatter == null) {
	    return null;
	}
	registerFormatter(path, formatter);
	return formatter;
    }
    
    protected Formatter<?> doGetFormatter(String type) {
	return formatters.get(type);
    }
    
    
    protected String getFormatterPath(String name, String format) {
 	if (name == null) {
 	    return null;
 	}
 	if (format  == null) {
 	    return name;
 	}
 	return name + "::" + format;
     }
    

}
