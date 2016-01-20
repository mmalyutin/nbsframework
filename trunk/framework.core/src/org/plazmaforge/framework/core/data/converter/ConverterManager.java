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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.data.converter.type.date.String2DateTimeConverter;
import org.plazmaforge.framework.core.data.converter.type.date.String2DateTimeConverterFactory;
import org.plazmaforge.framework.core.data.converter.type.number.String2TimeConverter;
import org.plazmaforge.framework.core.data.converter.type.number.String2TimeConverterFactory;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public class ConverterManager {

    public static final String GENERAL_CONVERTER_PACKAGE = ConverterManager.class.getPackage().getName() + ".type";
    
    private Map<String, ConverterFactory<?, ?>> converterFactories = new HashMap<String, ConverterFactory<?, ?>>();
    
    private ConverterRegistry converterRegistry = new ConverterRegistry(); 

    
    private final boolean cacheMode;
    
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

    public static String getConverterClassName(Class<?> sourceType, Class<?> targetType) {
	return getConverterClassName(null, sourceType, targetType);
    }
    
    public static String getConverterClassName(String pkg, Class<?> sourceType, Class<?> targetType) {
	return getConverterClassName(pkg, sourceType == null ? null : sourceType.getSimpleName(), targetType == null ? null : targetType.getSimpleName());
    }
    
    public static String getConverterClassName(String pkg, String sourceType, String targetType) {
	String simpleName = getConverterSimpleName(sourceType, targetType);
	if (simpleName == null) {
	    return null;
	}
	if (pkg == null) {
	    // By default general package
	    pkg = GENERAL_CONVERTER_PACKAGE; 
	} else if (pkg.startsWith(".")) {
	    // Relative package
	    pkg = GENERAL_CONVERTER_PACKAGE + pkg;
	}
	return pkg + "." + simpleName;
    }
    
    public static String getConverterSimpleName(Class<?> sourceType, Class<?> targetType) {
	return getConverterSimpleName(sourceType == null ? null : sourceType.getSimpleName(), targetType == null ? null : targetType.getSimpleName());
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
    
    public static <S, T> ConverterFactory<S, T> createGenericConverterFactory(String pkg, Class<S> sourceType, Class<T> targetType) {
	return new GenericConverterFactory(pkg, sourceType, targetType);
    }
    
    public <S, T> void registerGenericConveretrFactory(Class<S> sourceType, Class<T> targetType) {
	registerGenericConveretrFactory(null, sourceType, targetType);
    }
    
    public <S, T> void registerGenericConveretrFactory(String pkg, Class<S> sourceType, Class<T> targetType) {
	String simpleName = getConverterSimpleName(sourceType, targetType);
	if (simpleName == null) {
	    //TODO
	    return;
	}
	ConverterFactory<S, T> converterFactory = ConverterManager.createGenericConverterFactory(pkg, sourceType, targetType);
	if (converterFactory == null) {
	    //TODO
	    return;
	}
	registerConveretrFactory(simpleName, converterFactory);
    }
    
    public <R> void registerSelfConveretrFactory(Class<R> type) {
	String simpleName = getConverterSimpleName(type, type);
	if (simpleName == null) {
	    //TODO
	    return;
	}
	ConverterFactory<R, R> converterFactory = new SelfConverterFactory<R>();
	registerConveretrFactory(simpleName, converterFactory);
    }
    
    public void registerBaseConveretrFactories() {
	
	//////////////////////////////////////////////////////////////////////////////////
	// Package: number
	//////////////////////////////////////////////////////////////////////////////////
	
	// String -> <Number>
	registerGenericConveretrFactory(".number", String.class, Integer.class);
	registerGenericConveretrFactory(".number", String.class, Long.class);
	registerGenericConveretrFactory(".number", String.class, Float.class);
	registerGenericConveretrFactory(".number", String.class, Double.class);

	// <Number> -> <Number>
	registerGenericConveretrFactory(".number", Double.class, Integer.class);

	
	
	//////////////////////////////////////////////////////////////////////////////////
	// Package: date
	//////////////////////////////////////////////////////////////////////////////////
	
	// String -> <Date representation> - real type class
	registerGenericConveretrFactory(".date", String.class, Date.class);

	// String -> <Date representation> - virtual type class
	registerConveretrFactory(String2TimeConverter.class.getSimpleName(), new String2TimeConverterFactory());
	registerConveretrFactory(String2DateTimeConverter.class.getSimpleName(), new String2DateTimeConverterFactory());
	
	
	// Self
	registerSelfConveretrFactory(String.class);
	registerSelfConveretrFactory(Integer.class);
	registerSelfConveretrFactory(Float.class);
	registerSelfConveretrFactory(Double.class);
	
	registerSelfConveretrFactory(Date.class);
	
    }
}
