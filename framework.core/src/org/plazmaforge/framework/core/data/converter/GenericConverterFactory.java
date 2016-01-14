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

import java.lang.reflect.Constructor;

import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.util.ClassUtils;

/**
 * 
 * @author ohapon
 *
 * @param <S>
 * @param <T>
 */
public class GenericConverterFactory<S, T> implements ConverterFactory<S, T> {

    
    private static final Logger logger = Logger.getLogger(GenericConverterFactory.class.getName());
    
    
    private Class<S> sourceType;
    
    private Class<T> targetType;
    
    
    private Class<?> converterClass;
    
    private Constructor<?> converterConstructor1;
    
    private Constructor<?> converterConstructor2;    
    
    
    private boolean invalidInstance;
    
    private boolean invalidInstance1;
    
    private boolean invalidInstance2;
    

    public GenericConverterFactory(Class<S> sourceType, Class<T> targetType) {
	this.sourceType = sourceType;
	this.targetType = targetType;
    }

    @Override
    public Converter<S, T> getConverter() {
	return invalidInstance ? null : newConverter();
    }

    @Override
    public Converter<S, T> getConverter(String format) {
	return invalidInstance1 ? null : newConverter(format);
    }

    @Override
    public Converter<S, T> getConverter(String sourceFormat, String targetFormat) {
	return invalidInstance2 ? null : newConverter(sourceFormat, targetFormat);
    }
    
    protected Converter<S, T> newConverter() {
	Class<?> klass = getConverterClass();
	if (klass == null) {
	    invalidInstance = true;
	    return null;
	}
	try {
	    return (Converter<S, T>) klass.newInstance();
	} catch (Exception e) {
	    invalidInstance = true;
	    logger.error("Error converter instance", e);
	    return null;
	}
    }

    protected Converter<S, T> newConverter(String format) {
	Constructor<?> klass = getConverterConstructor1();
	if (klass == null) {
	    invalidInstance1 = true;
	    return null;
	}
	try {
	    return (Converter<S, T>) klass.newInstance(format);
	} catch (Exception e) {
	    invalidInstance1 = true;
	    logger.error("Error converter instance (String)", e);
	    return null;
	}
    }
    
    protected Converter<S, T> newConverter(String sourceFormat, String targetFormat) {
	Constructor<?> klass = getConverterConstructor2();
	if (klass == null) {
	    invalidInstance2 = true;
	    return null;
	}
	try {
	    return (Converter<S, T>) klass.newInstance(sourceFormat, targetFormat);
	} catch (Exception e) {
	    invalidInstance2 = true;
	    logger.error("Error converter instance (String, String)", e);
	    return null;
	}
    }
    
    protected Class<?> getConverterClass() {
	if (converterClass == null) {
	    converterClass = loadConverterClass();
	}
	return converterClass;
    }
    
    protected Constructor<?> getConverterConstructor1() {
	if (converterConstructor1 == null) {
	    converterConstructor1 = loadConverterConstructor1();
	}
	return converterConstructor1;
    }

    protected Constructor<?> getConverterConstructor2() {
	if (converterConstructor2 == null) {
	    converterConstructor2 = loadConverterConstructor2();
	}
	return converterConstructor2;
    }
    
    protected Class<?> loadConverterClass() {
	String className = getConverterClassName();
	if (className == null) {
	    invalidInstance = true;
	    return null;
	}
	try {
	    return ClassUtils.getClass(className);
	} catch (Exception e) {
	    invalidInstance = true;
	    logger.error("Error converter class", e);
	    return null;
	}
    }
    
    protected Constructor<?> loadConverterConstructor1() {
	Class<?> klass = getConverterClass();
	if (klass == null) {
	    invalidInstance1 = true;
	    return null;
	}
	try {
	    return klass.getConstructor(String.class);
	} catch (Exception e) {
	    invalidInstance1 = true;
	    logger.error("Error converter constructor (String)", e);
	    return null;
	}	
    }

    protected Constructor<?> loadConverterConstructor2() {
	Class<?> klass = getConverterClass();
	if (klass == null) {
	    invalidInstance2 = true;
	    return null;
	}
	try {
	    return klass.getConstructor(String.class, String.class);
	} catch (Exception e) {
	    invalidInstance2 = true;
	    logger.error("Error converter constructor (String, String)", e);
	    return null;
	}	
    }
    
    protected String getConverterClassName() {
	return ConverterManager.getConverterClassName(sourceType, targetType);
    }
    
}
