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

package org.plazmaforge.framework.core.data.access;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ohapon
 *
 */
public class ReflectUtils {

    private ReflectUtils() {
	super();
    }
    
    /**
     * Find methods by name
     * @param klass
     * @param name
     * @return
     */
    public static List<Method> findMethods(Class<?> klass, String name) {
	if (name == null) {
	    return null;
	}
	return findMethods(klass.getMethods(), name);
    }
    
    /**
     * Find declared methods by name
     * @param klass
     * @param name
     * @return
     */
    public static List<Method> findDeclaredMethods(Class<?> klass, String name) {
	if (name == null) {
	    return null;
	}
	return findMethods(klass.getDeclaredMethods(), name);
    }
    
    /**
     * Find methods by name
     * @param methods
     * @param name
     * @return
     */
    public static List<Method> findMethods(Method[] methods, String name) {
	if (name == null) {
	    return null;
	}
	List<Method> result = new ArrayList<Method>();
	for (Method method : methods) {
	    if (name.equals(method.getName())) {
		result.add(method);
	    }
	}
	return result.isEmpty() ? null : result;
    }
    
    public static Method findMethod(Method[] methods, Class<?>[] parameterTypes) {
	// Find methods by parameter types
	for (Method method : methods) {
	    if (acceptMethod(method, parameterTypes)) {
		return method;
	    }
	}
	return null;
    }

    public static Method findMethod(List<Method> methods, Class<?>[] parameterTypes) {
	// Find methods by parameter types
	for (Method method : methods) {
	    if (acceptMethod(method, parameterTypes)) {
		return method;
	    }
	}
	return null;
    }
    
    public static boolean acceptMethod(Method method,  Class<?>[] parameterTypes) {
	Class<?>[] methodParameterTypes = method.getParameterTypes();
	if (parameterTypes == null && methodParameterTypes == null) {
	    return true;
	}
	if (parameterTypes.length == 0 && methodParameterTypes.length == 0) {
	    return true;
	}

	if (parameterTypes.length != methodParameterTypes.length) {
	    return false;
	}
	Class<?> methodParameterType = null;
	Class<?> parameterType = null;
	int acceptLevel = -1;
	for (int i = 0; i < methodParameterTypes.length; i++) {
	    methodParameterType = methodParameterTypes[i];
	    parameterType = parameterTypes[i];
	    acceptLevel = getAcceptLevel(methodParameterType, parameterType);
	    if (acceptLevel < 0) {
		return false;
	    }
	}
	return true;
    }
       
    public static int getAcceptLevel(Class<?> methodParameterType,  Class<?> parameterType) {
	if (parameterType == null || methodParameterType.getName().equals(parameterType.getName())) {
	    return 0;
	}
	if (methodParameterType.isAssignableFrom(parameterType)) {
	    return 1; // TODO: Must find level
	}
	return -1;
    }           
    

}
