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

/**
 * 
 */
package org.plazmaforge.framework.util;

/**
 * @author ohapon
 *
 */
public class ClassUtils {

    private final static String LANG_PACKAGE = "java.lang";
    private final static String UTIL_PACKAGE = "java.util";
    private final static String SQL_PACKAGE = "java.sql";
    
    private static String[] BASE_PACKAGES = new String[] {LANG_PACKAGE, UTIL_PACKAGE, SQL_PACKAGE};
    
    
    /**
     * Return ClassLoader of current thread  
     * @return
     */
    public static ClassLoader getContextClassLoader() {
	return Thread.currentThread().getContextClassLoader();
    }

    /**
     * Return class by name
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> getClass(String className) throws ClassNotFoundException {
	ClassLoader classLoader = getContextClassLoader();
	if (classLoader == null) {
	    return Class.forName(className);
	}
	return Class.forName(className, true, classLoader);
    }

    /**
     * Return class by name
     * @param className
     * @return null if catch exception
     */
    public static Class<?> getSafeClass(String className) {
	try {
	    return getClass(className);
	} catch (Exception ex) {
	    return null;
	}
    }
    
    /**
     * Return instance of class
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Object newInstance(String className)  throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	Class<?> klass = getClass(className);
	return klass.newInstance();
    }
    
    /**
     * Return instance of class
     * @param className
     * @return null if catch exception
     */
    public static Object newSafeInstance(String className) {
  	try {
  	    return newInstance(className);
  	} catch (Exception ex) {
  	    return null;
  	}
      }

      
      public static Object getDefaultPrimitiveValue(Class<?> klass) {
  	if (klass == null || !klass.isPrimitive()) {
  	    return null;
  	}
  	String className = klass.getName();
  	if (boolean.class.getName().equals(className)) {
  	    return false;
  	} else if (byte.class.getName().equals(className)) {
  	    return (byte) 0;
  	} else if (short.class.getName().equals(className)) {
  	    return (short) 0;
  	} else if (int.class.getName().equals(className)) {
  	    return 0;
  	} else if (long.class.getName().equals(className)) {
  	    return 0l;
  	} else if (float.class.getName().equals(className)) {
  	    return 0.0f;
  	} else if (double.class.getName().equals(className)) {
  	    return 0.0d;
  	}
  	return null;
      }

      /**
       * If the class located in base packages ('java.lang', 'java.util', 'java.sql') then return simple name of class
       * Example: 
       * java.lang.String -> String
       * java.util.Date -> Date
       * java.sql.Date -> Date
       *  
       * @param klass
       * @return
       */
    public static String getBaseClassName(Class<?> klass) {
	if (klass == null) {
	    return null;
	}
	String className = klass.getName();
	String simpleName = null;
	for (String pkg : BASE_PACKAGES) {
	    if (className.startsWith(pkg + ".")) {
		simpleName = className.substring(pkg.length() + 1);
		return simpleName.indexOf(".") > 0 ? className : simpleName;
	    }
	}
	return className;
    }

    /**
     * Return simple class name by full class name
     * @param className
     * @return
     */
    public static String getSimpleClassName(String className) {
	if (className == null) {
	    return null;
	}
	int index = className.lastIndexOf(".");
	if (index < 0) {
	    return className;
	}
	if (index == className.length() - 1) {
	    return null;
	}
	return className.substring(index + 1);
    }

    /**
     * Return package name by full class name
     * @param className
     * @return
     */
    public static String getPackageName(String className) {
	if (className == null) {
	    return null;
	}
	int index = className.lastIndexOf(".");
	if (index <= 0) {
	    return null;
	}
	return className.substring(0, index);
    }
    
    public static boolean isCollection(Class<?> klass/*, isLikeCollection*/) {
	// TODO: Use flag isLikeCollection for Map, because Map is not collection
	return isExtends(klass, java.util.Collection.class) || isExtends(klass, java.util.Map.class);
    }

    public static boolean isExtends(Class<?> klass, Class<?> subclass) {
	if (klass == null || subclass == null) {
	    return false;
	}
	return subclass.isAssignableFrom(klass);
    }
       
}
