package org.plazmaforge.framework.erm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity Attribute (property) accessor
 * 
 * @author ohapon
 *
 */
public class Accessor {

    private static final String GET_PREFIX = "get";
    
    private static final String SET_PREFIX = "set";
    
    private static final String IS_PREFIX = "is";

    private Class entityClass;
    
    private Method getter;

    private Method setter;


    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
    
    public Class getAttributeClass() {
        return getter == null ? null : getter.getReturnType();
    }


    public Method getGetter() {
	return getter;
    }

    public void setGetter(Method getter) {
	this.getter = getter;
    }

    public Method getSetter() {
	return setter;
    }

    public void setSetter(Method setter) {
	this.setter = setter;
    }

    public String toString() {
	return "Class=" + entityClass + ", Getter=" + getter + ", Setter=" + setter;
    }
    
    //////
    
    public static Accessor getAccessor(Class klass, String property) {
	Method[] methods = klass.getMethods();
	return getAccessor(methods, property);
    }
    
    public static Accessor getAccessor(Method[] methods, String property) {
	
	Method getter = null;
	Method setter = null;
	
	String capitalizeName = capitalize(property);
	String isMethodName = IS_PREFIX + capitalizeName;
	String getMethodName = GET_PREFIX + capitalizeName;
	String setMethodName = SET_PREFIX + capitalizeName;
	
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
	Accessor accessor = new Accessor();
	accessor.setGetter(getter);
	if (setMethods.isEmpty()) {
	    return accessor;
	}
	Class type = getter.getReturnType();
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

	accessor.setSetter(setter);
	return accessor;
    }

    public static String capitalize(String name) { 
	if (name == null || name.length() == 0) { 
	    return name; 
        }
	return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
