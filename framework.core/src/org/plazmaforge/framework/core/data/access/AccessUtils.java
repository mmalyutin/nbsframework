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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public class AccessUtils {

    private AccessUtils() {
    }

    public static String capitalize(String name) { 
	if (name == null || name.isEmpty()) { 
	    return name; 
        }
	return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String decapitalize(String name) { 
	if (name == null || name.isEmpty()) { 
	    return name; 
        }
	return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
    
    
    public static String convertGetterToProperty(String methodName) {
	if (methodName == null) {
	    return null;
	}
	if (methodName.startsWith(PropertyAccessor.GET_PREFIX) && methodName.length() > 3) {
	    return decapitalize(methodName.substring(3));
	}
	if (methodName.startsWith(PropertyAccessor.IS_PREFIX) && methodName.length() > 2) {
	    return decapitalize(methodName.substring(2));
	}
	return null;
    }

    public static String convertSetterToProperty(String methodName) {
	if (methodName == null) {
	    return null;
	}
	if (methodName.startsWith(PropertyAccessor.SET_PREFIX) && methodName.length() > 3) {
	    return decapitalize(methodName.substring(3));
	}
	return null;
    }
    
    /**
     * Return true if the method is getter (start with 'get' or 'is')
     * @param methodName
     * @return
     */
    public static boolean isGetter(String methodName) {
	if (methodName == null) {
	    return false;
	}
	return methodName.startsWith(PropertyAccessor.GET_PREFIX) || methodName.startsWith(PropertyAccessor.IS_PREFIX); 
    }

    /**
     * Return true if the method is setter (start with 'set')
     * @param methodName
     * @return
     */
    public static boolean isSetter(String methodName) {
	if (methodName == null) {
	    return false;
	}
	return methodName.startsWith(PropertyAccessor.SET_PREFIX); 
    }
    
    /**
     * Return true if the method is getter (start with 'get' or 'is', parameter count = 0, return type != void)
     * @param method
     * @return
     */
    public static boolean isGetter(Method method) {
	if (method == null) {
	    return false;
	}
	
	// Check by name
	if (!isGetter(method.getName())) {
	    return false;
	}
	
	// Check parameter types: parameters = 0
	if (method.getParameterTypes().length != 0) {
	    return false;
	}
	
	// Check by return type: return type != void
	if (void.class.equals(method.getReturnType()))  {
	    return false;
	}
	
	return true;
    }

    /**
     * Return true if the method is setter (start with 'set', parameter count = 1, return type = void)
     * @param method
     * @return
     */
    public static boolean isSetter(Method method) {
	if (method == null) {
	    return false;
	}
	
	// Check by name
	if (!isSetter(method.getName())) {
	    return false;
	}
	
	// Check parameter types: parameters = 1
	if (method.getParameterTypes().length != 1) {
	    return false;
	}

	// Check by return type: return type = void
	if (!void.class.equals(method.getReturnType()))  {
	    return false;
	}
	
	return true;
    }
    

    public static PropertyAccessor[] getPropertyAccessors(Method[] methods) {
	if (methods == null || methods.length == 0) {
	    return new PropertyAccessor[0];
	}

	Map<String, PropertyInfo> map = new LinkedHashMap<String, PropertyInfo>();
	String methodName = null;
	String property = null;
	
	Method isMethod = null;
	Method getMethod = null;
	Method setMethod = null;
	
	for (Method method : methods) {
	    
	    isMethod = null;
	    getMethod = null;
	    setMethod = null;
	    
	    methodName = method.getName();
	    
	    // Find Getter/Setter
	    if (isGetter(method)) {
		property = convertGetterToProperty(methodName);
		
		if (methodName.startsWith(PropertyAccessor.GET_PREFIX)) {
		    getMethod = method;
		} else if (methodName.startsWith(PropertyAccessor.IS_PREFIX)) {
		    isMethod = method;
		}
		
	    } else if (isSetter(method)) {
		property = convertSetterToProperty(methodName);
		setMethod = method;
	    }
	    
	    if (property == null) {
		continue;
	    }
	    
	    PropertyInfo info = map.get(property);
	    if (info == null) {
		info = new PropertyInfo();
		info.name = property;
		map.put(property, info);
	    }
	    
	    if (getMethod != null) {
		info.getMethod = getMethod;
	    }
	    
	    if (isMethod != null) {
		info.isMethod = isMethod;
	    }
	    
	    if (setMethod != null) {
		if (info.setMethods == null) {
		    info.setMethods = new ArrayList<Method>();
		}
		info.setMethods.add(setMethod);
	    }
	    
	}
	
	List<PropertyAccessor> result = new ArrayList<PropertyAccessor>();
	
	for (PropertyInfo propertyInfo: map.values()) {
	
	    property = propertyInfo.name;
	    if ("class".equals(property)) {
		continue;
	    }
	    
	    PropertyAccessor propertyAccessor = createPropertyAccessor(property, propertyInfo.getMethod, propertyInfo.isMethod, propertyInfo.setMethods);
	    if (propertyAccessor == null) {
		continue;
	    }
	    result.add(propertyAccessor);
	}
	
	return result.toArray(new PropertyAccessor[0]);
    }
    
    public static PropertyAccessor[] getPropertyAccessors(Class<?> klass) {
	if (klass == null) {
	    return null;
	}
	Method[] methods = klass.getMethods();
	return getPropertyAccessors(methods);
    }
    
    
    public static PropertyAccessor getPropertyAccessor(Class<?> klass, String property) {
	if (klass == null || property == null) {
	    return null;
	}
	Method[] methods = klass.getMethods();
	return getPropertyAccessor(methods, property);
    }
    
    public static PropertyAccessor getPropertyAccessor(Method[] methods, String property) {
	
	if (methods == null || methods.length == 0 || property == null) {
	    return null;
	}
	
	property = StringUtils.normalizeString(property);
	if (property == null) {
	    return null;
	}
	
	//Method getter = null;
	//Method setter = null;
	
	String capitalizeName = capitalize(property);
	String isMethodName = PropertyAccessor.IS_PREFIX + capitalizeName;
	String getMethodName = PropertyAccessor.GET_PREFIX + capitalizeName;
	String setMethodName = PropertyAccessor.SET_PREFIX + capitalizeName;
	
	Method isMethod = null;
	Method getMethod = null;
	//Method setMethod = null;

	String methodName = null;
	
	//Class[] parameterTypes = null;
	//int parameterCount = 0;
	
	List<Method> setMethods = new ArrayList<Method>(1);
	for (Method method : methods) {
	    methodName = method.getName();
	    
	    //parameterTypes = method.getParameterTypes();
	    //parameterCount = parameterTypes.length;
	    //Class returnType = method.getReturnType();
	    
	    if (isMethodName.equals(methodName) && isGetter(method) /*parameterCount == 0 && returnType != void.class*/) {
		isMethod = method;
		continue;
	    }
	    if (getMethodName.equals(methodName) && isGetter(method) /*parameterCount == 0 && returnType != void.class*/) {
		getMethod = method;
		continue;
	    }
	    if (setMethodName.equals(method.getName()) && isGetter(method) /*parameterCount == 1 && returnType == void.class*/) {
		setMethods.add(method);
	    }
	}
	
	PropertyAccessor propertyAccessor = createPropertyAccessor(property, getMethod, isMethod, setMethods);
	
//	getter = getMethod == null ? isMethod : getMethod;
//	if (getter == null) {
//	    return null;
//	}
//	BasePropertyAccessor propertyAccessor = new BasePropertyAccessor();
//	propertyAccessor.setGetter(getter);
//	if (setMethods.isEmpty()) {
//	    return propertyAccessor;
//	}
//	Class type = getter.getReturnType();
//	propertyAccessor.setType(type);
//	
//	Class parameterType = null;
//	for (Method method : setMethods) {
//	    parameterType = method.getParameterTypes()[0];
//	    if (type.equals(parameterType)) {
//		setter = method;
//		break;
//	    }
//	}
//
//	if (setter == null) {
//	    for (Method method : setMethods) {
//		parameterType = method.getParameterTypes()[0];
//		if (type.isAssignableFrom(parameterType)) {
//		    setter = method;
//		    break;
//		}
//	    }
//	}
//
//	propertyAccessor.setSetter(setter);
	
	
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
       
     
     private static class PropertyInfo {
	 
	 String name;
	 
	 // Getter
	 Method isMethod;
	 Method getMethod;
	 
	 // Setter
	 List<Method> setMethods;
	 
     }
     
    private static PropertyAccessor createPropertyAccessor(String name, Method getMethod, Method isMethod, List<Method> setMethods) {
	
	Method getter = null;
	Method setter = null;

	getter = getMethod == null ? isMethod : getMethod;
	if (getter == null) {
	    //TODO: Analyze
	    return null;
	}
	BasePropertyAccessor propertyAccessor = new BasePropertyAccessor();
	propertyAccessor.setPropertyName(name);
	
	// Getter
	propertyAccessor.setGetter(getter);
	
	// Type
	Class type = getter.getReturnType();
	propertyAccessor.setType(type);
	
	if (setMethods == null || setMethods.isEmpty()) {
	    return propertyAccessor;
	}

	// Find Setter (original type)
	Class parameterType = null;
	for (Method method : setMethods) {
	    parameterType = method.getParameterTypes()[0];
	    if (type.equals(parameterType)) {
		setter = method;
		break;
	    }
	}

	// Find Setter (extend type - optional)
	if (setter == null) {
	    for (Method method : setMethods) {
		parameterType = method.getParameterTypes()[0];
		if (type.isAssignableFrom(parameterType)) {
		    setter = method;
		    break;
		}
	    }
	}

	// Setter
	propertyAccessor.setSetter(setter);
	return propertyAccessor;

    }
}
