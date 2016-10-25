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

import java.util.LinkedHashMap;
import java.util.Map;



/**
 * 
 * General FormatterManager
 * 
 * - Register/Unregister FormatterFactory
 * 
 * - Get FormatterFactory by name 
 * 
 * - Get Formatter by:  (name) | (name, format)
 * 
 * - Use FormatterRegistry for cache mode
 * 
 * 
 * @author ohapon
 *
 */
public class FormatterManager  {

    private Map<String, FormatterFactory<?>> formatterFactories = new LinkedHashMap<String, FormatterFactory<?>>();

    private FormatterRegistry formatterRegistry = new FormatterRegistry();

    private final boolean cacheMode;
    
    
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
    
    public Formatter<?> getFormatter(String name) {
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
	formatter = formatterFactory.getFormatter(); // NO FORMAT
	doAddFormatter(path, formatter);
	return formatter;
    }
    
    public Formatter<?> getFormatter(String name, String format) {
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
	formatter = formatterFactory.getFormatter(format); // FORMAT
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

    public void init() {
	// do nothing by default
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String formatValue(Object value, String type, String format) {
	if (value == null) {
	    return null;
	}
	Formatter formatter = getFormatter(type, format);
	if (formatter == null) {
	    return value.toString();
	}
	return formatter.format(value);
    }
    
    public String formatValue(Object value, String type) {
	return formatValue(value, type, null);
    }

    public Object parseValue(String value, String type, String format) {
	if (value == null) {
	    return null;
	}
	Formatter formatter = getFormatter(type, format);
	if (formatter == null) {
	    return value;
	}
	return formatter.parse(value);
    }

    public Object parseValue(String value, String type) {
	return parseValue(value, type, null);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

}
