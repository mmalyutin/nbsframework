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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ohapon
 *
 */
public class AccessUtils {

    public static String capitalize(String name) { 
	if (name == null || name.length() == 0) { 
	    return name; 
        }
	return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static PropertyAccessor getPropertyAccessor(Class<?> klass, String property) {
	if (klass == null || property == null) {
	    return null;
	}
	Method[] methods = klass.getMethods();
	return getPropertyAccessor(methods, property);
    }
    
    public static PropertyAccessor getPropertyAccessor(Method[] methods, String property) {
	
	Method getter = null;
	Method setter = null;
	
	String capitalizeName = capitalize(property);
	String isMethodName = PropertyAccessor.IS_PREFIX + capitalizeName;
	String getMethodName = PropertyAccessor.GET_PREFIX + capitalizeName;
	String setMethodName = PropertyAccessor.SET_PREFIX + capitalizeName;
	
	Method isMethod = null;
	Method getMethod = null;
	Method setMethod = null;

	String methodName = null;
	Class[] parameterTypes = null;
	int parameterCount = 0;
	List<Method> setMethods = new ArrayList<Method>(1);
	for (Method method : methods) {
	    methodName = method.getName();
	    parameterTypes = method.getParameterTypes();
	    parameterCount = parameterTypes.length;
	    Class returnType = method.getReturnType();
	    if (isMethodName.equals(methodName) && parameterCount == 0 && returnType != void.class) {
		isMethod = method;
		continue;
	    }
	    if (getMethodName.equals(methodName) && parameterCount == 0 && returnType != void.class) {
		getMethod = method;
		continue;
	    }
	    if (setMethodName.equals(method.getName()) && parameterCount == 1 && returnType == void.class) {
		setMethods.add(method);
	    }
	}
	getter = getMethod == null ? isMethod : getMethod;
	if (getter == null) {
	    return null;
	}
	BasePropertyAccessor propertyAccessor = new BasePropertyAccessor();
	propertyAccessor.setGetter(getter);
	if (setMethods.isEmpty()) {
	    return propertyAccessor;
	}
	Class type = getter.getReturnType();
	propertyAccessor.setType(type);
	
	Class parameterType = null;
	for (Method method : setMethods) {
	    parameterType = method.getParameterTypes()[0];
	    if (type.equals(parameterType)) {
		setter = method;
		break;
	    }
	}

	if (setter == null) {
	    for (Method method : setMethods) {
		parameterType = method.getParameterTypes()[0];
		if (type.isAssignableFrom(parameterType)) {
		    setter = method;
		    break;
		}
	    }
	}

	propertyAccessor.setSetter(setter);
	return propertyAccessor;
    }
    
    
    public static Object getValue(PropertyAccessor propertyAccessor, Object obj) {
 	if (obj == null || propertyAccessor == null) {
 	    return null;
 	}
 	Method getter = propertyAccessor.getGetter();
 	if (getter == null) {
 	    return null;
 	}
 	try {
 	    return invoke(getter, obj);
 	} catch (Exception ex) {
 	    //todo
 	    return null;
 	}
     }

     public static void setValue(PropertyAccessor propertyAccessor, Object obj, Object value) {
 	if (obj == null || propertyAccessor == null) {
 	    return;
 	}
 	Method setter = propertyAccessor.getSetter();
 	if (setter == null) {
 	    return;
 	}
 	try {
 	     invoke(setter, obj, value);
 	} catch (Exception ex) {
 	    //todo
 	}
     }

     public static Object invoke(Method method, Object obj, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
 	return method.invoke(obj, args);
     } 
         
}
