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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.plazmaforge.framework.util.StringUtils;

/**
 * General ConverterManager
 * 
 * - Register/Unregister ConverterFactory
 * 
 * - Get ConverterFactory by name 
 * 
 * - Get Converter by:  (name) | (name, format) | (name, sourceFormat, targetFormat)
 * 
 * - Use ConverterRegistry for cache mode
 * 
 * @author ohapon
 *
 */
public class ConverterManager {


    private static Map<String, String> classSimpleNameMap = new HashMap<String, String>();
    
    private Map<String, ConverterFactory<?, ?>> converterFactories = new LinkedHashMap<String, ConverterFactory<?, ?>>();
    
    private ConverterRegistry converterRegistry = new ConverterRegistry(); 

    
    private final boolean cacheMode;

    static {
	classSimpleNameMap = new HashMap<String, String>();
	
	classSimpleNameMap.put("java.sql.Date", "SQLDate");
	classSimpleNameMap.put("java.sql.Time", "SQLTime");
	classSimpleNameMap.put("java.sql.Timestamp", "SQLTimestamp");
    }
    
    public ConverterManager() {
	this(false);
    }

    public ConverterManager(boolean cacheMode) {
	this.cacheMode = cacheMode;
    }
    
    public boolean isCacheMode() {
        return cacheMode;
    }

    public void registerConveretrFactory(String name, ConverterFactory<?,?> converterFactory) {
	converterFactories.put(name, converterFactory);
    }
    
    public void unregisterConveretrFactory(String name) {
	converterFactories.remove(name);
    }
    
    public ConverterFactory<?, ?> getConverterFactory(String name) {
	return converterFactories.get(name);
    }
    
    public Map<String, ConverterFactory<?, ?>> getConverterFactories() {
	return new LinkedHashMap<String, ConverterFactory<?,?>>(converterFactories);
    }
    
    public Converter<?, ?> getConverter(String name) {
	if (name == null) {
	    return null;
	}
	String path = getConverterPath(name, null, null);
	Converter<?, ?> converter = doGetConverter(path);
	if (converter != null) {
	    return converter;
	}
	ConverterFactory<?, ?> converterFactory = getConverterFactory(name);
	if (converterFactory == null) {
	    return null;
	}
	converter = converterFactory.getConverter();
	doAddConverter(path, converter);
	return converter;
    }
    
    public Converter<?, ?> getConverter(String name, String format) {
	if (name == null) {
	    return null;
	}
	String path = getConverterPath(name, format, null);
	Converter<?, ?> converter = doGetConverter(path);
	if (converter != null) {
	    return converter;
	}
	ConverterFactory<?, ?> converterFactory = getConverterFactory(name);
	if (converterFactory == null) {
	    return null;
	}
	converter = converterFactory.getConverter(format);
	doAddConverter(path, converter);
	return converter;
    }

    public Converter<?, ?> getConverter(String name, String sourceFormat, String targetFormat) {
	if (name == null) {
	    return null;
	}
	String path = getConverterPath(name, sourceFormat, targetFormat);
	Converter<?, ?> converter = doGetConverter(path);
	if (converter != null) {
	    return converter;
	}
	ConverterFactory<?, ?> converterFactory = getConverterFactory(name);
	if (converterFactory == null) {
	    return null;
	}
	converter = converterFactory.getConverter(sourceFormat, targetFormat);
	doAddConverter(path, converter);
	return converter;
    }
    
    protected String getConverterPath(String name, String sourceFormat, String targetFormat) {
	if (name == null) {
	    return null;
	}
	if (sourceFormat  == null && targetFormat == null) {
	    return name;
	}
	if (targetFormat == null) {
	    return name + "::" + sourceFormat;
	}
	return name + "::" + sourceFormat + "::" + targetFormat;
    }
    
    protected Converter<?, ?> doGetConverter(String path) {
	if (!cacheMode) {
	    return null;
	}
	return converterRegistry.getConverter(path);
    }

    protected void doAddConverter(String path, Converter<?, ?> converter) {
	if (!cacheMode) {
	    return;
	}
	converterRegistry.addConverter(path, converter);
    }

    public void init() {
	// do nothing by default
    }
    
    public static String getConverterSimpleName(String sourceType, String targetType) {
	// Name = <SourceType>2<TargetType>Converter
	sourceType = StringUtils.normalizeString(sourceType);
	targetType = StringUtils.normalizeString(targetType);
	if (sourceType == null || targetType == null) {
	    return null;
	}
	return sourceType + "2" + targetType + "Converter";
    }
    
    public static String getSimpleName(Class<?> type) {
	if (type == null) {
	    return null;
	}
	String simpleName = classSimpleNameMap.get(type.getName());
	if (simpleName != null) {
	    return simpleName;
	}
	return type.getSimpleName();
    }

    
}
