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


/**
 * 
 * @author ohapon
 *
 */
public class GenericConverterManager extends ConverterManager {

    public static final String GENERAL_CONVERTER_PACKAGE = ConverterManager.class.getPackage().getName() + ".type";
    
    
    public GenericConverterManager() {
	super();
    }

    public GenericConverterManager(boolean cacheMode) {
	super(cacheMode);
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
	return getConverterSimpleName(getSimpleName(sourceType), getSimpleName(targetType));
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
	ConverterFactory<S, T> converterFactory = createGenericConverterFactory(pkg, sourceType, targetType);
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
    
    
}
