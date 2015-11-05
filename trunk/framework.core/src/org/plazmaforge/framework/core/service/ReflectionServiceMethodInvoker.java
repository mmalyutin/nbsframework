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

package org.plazmaforge.framework.core.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.Parameters;


/**
 * 
 * @author ohapon
 *
 */
//[NONJS]
public class ReflectionServiceMethodInvoker implements ServiceMethodInvoker {

    @Override
    public Object invoke(Object service, String methodName, Parameters parameters) {
	if (service == null) {
	    throw new ServiceCallerException("Service must be not null"); 
	}
	Class<?> serviceClass = service.getClass();
	if (methodName == null) {
	    throw new ServiceCallerException("Method must be not null"); 
	}
	
	// Find methods by name
	List<Method> methods = getMethods(serviceClass, methodName);
	if (methods == null) {
	    throw new ServiceCallerException("Method '" + methodName + "' not found");
	}
	
	// Create parameter types
	Class<?>[] parameterTypes = null;
	Object[] parameterValues = null;
	if (parameters == null || parameters.isEmpty()) {
	    parameterTypes = new Class<?>[0];
	    parameterValues = new Object[0];
	} else {
	    parameterValues = parameters.toArray();
	    int parameterCount = parameterValues.length;
	    parameterTypes = new Class<?>[parameterCount];
	    Object parameterValue = null;
	    for (int i = 0; i < parameterCount; i++) {
		parameterValue = parameterValues[i];
		parameterTypes[i] = parameterValue == null ? null : parameterValue.getClass();
	    }
	}
	
	// Find methods by parameter types
	Method serviceMethod = null;
	for (Method method : methods) {
	    if (acceptByParameters(method, parameterTypes)) {
		serviceMethod = method;
		break;
	    }
	}
	
	if (serviceMethod == null) {
	    //TODO: Must create parameters string
	    throw new ServiceCallerException("Method '" + methodName + "(" + "" + ")' not found");
	}
	try {
	    Object result = serviceMethod.invoke(service, parameterValues);
	    return result;
	} catch  (Throwable e) {
	    throw new ServiceCallerException("Call service error", e);
	}
    }
    
    protected List<Method> getMethods(Class<?> serviceClass, String methodName) {
	if (methodName == null) {
	    return null;
	}
	//Method[] declaredMethods = serviceClass.getDeclaredMethods();
	Method[] methods = serviceClass.getMethods();
	List<Method> result = new ArrayList<Method>();
	for (Method method: methods ) {
	    if (methodName.equals(method.getName())) {
		result.add(method);
	    }
	}
	return result.isEmpty() ?  null : result;
    }

    
    protected boolean acceptByParameters(Method method, Class[] parameterTypes) {
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
	    if (acceptLevel < 0)  {
		return false;
	    }
	}
	return true;
    }
    
    protected int getAcceptLevel(Class<?> methodParameterType, Class<?> parameterType) {
	if (parameterType == null || methodParameterType.getName().equals(parameterType.getName())) {
	    return 0;
	}
	if (methodParameterType.isAssignableFrom(parameterType) ) {
	    return 1; // TODO: Must find level 
	}
	return -1;
    }
    
    
    
    public static Throwable getErrorCause(Throwable e) {
	if (e == null) {
	    return null;
	}
	if (e instanceof ServiceCallerException) {
	    return getServiceCallerCause((ServiceCallerException) e);
	} else if (e instanceof InvocationTargetException) {
	    return getInvocationTargetCause((InvocationTargetException) e);
	}
	return e;
    }
    
    public static Throwable getServiceCallerCause(ServiceCallerException e) {
	if (e == null) {
	    return null;
	}
	Throwable cause = e.getCause();
	if (cause == null) {
	    return e;
	}
	if (cause instanceof InvocationTargetException) {
	    cause = getInvocationTargetCause((InvocationTargetException) cause);
	}
	return cause == null ? e : cause;
    }
    
    public static Throwable getInvocationTargetCause(InvocationTargetException e) {
	if (e == null) {
	    return null;
	}
	Throwable cause = e.getTargetException();
	if (cause == null) {
	    cause = e.getCause();
	}
	return cause == null ? e : cause;
    }
   
}
