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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.plazmaforge.framework.core.data.formatter.type.RWByteFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDateFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDateTimeFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDoubleFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWFloatFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWIntegerFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWShortFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWStringFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWTimeFormatter;
import org.plazmaforge.framework.core.type.Types;


/**
 * 
 * @author ohapon
 *
 */
public class FormatterManager  {

    private Map<String, FormatterFactory<?>> formatterFactories = new LinkedHashMap<String, FormatterFactory<?>>();

    private FormatterRegistry formatterRegistry = new FormatterRegistry();

    private final boolean cacheMode;
    
    
    private Map<String, Formatter<?>> formatters = new HashMap<String, Formatter<?>>();
    
    
    public FormatterManager() {
	this(false);
    }

    public FormatterManager(boolean cacheMode) {
	this.cacheMode = cacheMode;
    }
    
    public boolean isCacheMode() {
        return cacheMode;
    }
    

    public void registerFormatterFactory(String name, FormatterFactory<?> formatterFactory) {
	formatterFactories.put(name, formatterFactory);
    }
    
    public void unregisterFormatterFactory(String name) {
	formatterFactories.remove(name);
    }
    
    public FormatterFactory<?> getFormatterFactory(String name) {
	return formatterFactories.get(name);
    }
    
    public Map<String, FormatterFactory<?>> getFormatterFactories() {
	return new LinkedHashMap<String, FormatterFactory<?>>(formatterFactories);    
    }
    
    ////
    
    public Formatter<?> getFormatter1(String name) {
	if (name == null) {
	    return null;
	}
	String path = getFormatterPath(name, null);
	Formatter<?> formatter = doGetFormatter(path);
	if (formatter != null) {
	    return formatter;
	}
	FormatterFactory<?> formatterFactory = getFormatterFactory(name);
	if (formatterFactory == null) {
	    return null;
	}
	formatter = formatterFactory.getFormatter();
	doAddFormatter(path, formatter);
	return formatter;
    }
    
    public Formatter<?> getFormatter1(String name, String format) {
	if (name == null) {
	    return null;
	}
	String path = getFormatterPath(name, format);
	Formatter<?> formatter = doGetFormatter(path);
	if (formatter != null) {
	    return formatter;
	}
	FormatterFactory<?> formatterFactory = getFormatterFactory(name);
	if (formatterFactory == null) {
	    return null;
	}
	formatter = formatterFactory.getFormatter(format);
	doAddFormatter(path, formatter);
	return formatter;
    }
    
    protected String getFormatterPath(String name, String format) {
	if (name == null) {
	    return null;
	}
	if (format == null) {
	    return name;
	}
	return name + "::" + format;
    }
    
    protected Formatter<?> doGetFormatter(String path) {
	if (!cacheMode) {
	    return null;
	}
	return formatterRegistry.getFormatter(path);
    }

    protected void doAddFormatter(String path, Formatter<?> formatter) {
	if (!cacheMode) {
	    return;
	}
	formatterRegistry.addFormatter(path, formatter);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
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
	    return doGetFormatter2(type); 
	}

	// TODO: 
	// by path: <type>::<format>
	String path = getFormatterPath(type, format);
	Formatter<?> formatter = doGetFormatter2(path);
	if (formatter != null) {
	    return formatter;
	}
	// by type
	formatter = doGetFormatter2(type);
	if (formatter == null) {
	    return null;
	}
	registerFormatter(path, formatter);
	return formatter;
    }
    
    protected Formatter<?> doGetFormatter2(String type) {
	return formatters.get(type);
    }
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public String toString(Object value, String type) {
	if (value == null) {
	    return null;
	}
	Formatter formatter = getFormatter(type);
	if (formatter == null) {
	    return value.toString();
	}
	return formatter.format(value);
    }
    
    public Object toValue(String value, String type) {
	if (value == null) {
	    return null;
	}
	Formatter formatter = getFormatter(type);
	if (formatter == null) {
	    return value;
	}
	return formatter.parse(value);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
}
