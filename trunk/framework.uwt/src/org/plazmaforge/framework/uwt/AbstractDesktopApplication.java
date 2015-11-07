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
package org.plazmaforge.framework.uwt;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.core.data.Destroyer;
import org.plazmaforge.framework.core.data.Initializer;

/**
 * @author ohapon
 *
 */
public class AbstractDesktopApplication extends Application {

    
    //[CORE]
    /**
     * Return map of properties by array of arguments 
     * @param args
     * @return
     */
    public static Map<String, String> getProperties(String[] args) {

	Map<String, String> properties = new LinkedHashMap<String, String>();
	int i = 0;
	while (i < args.length) {
	    String arg = args[i];
	    i++;
	    if (arg.startsWith("-") && arg.length() > 1) {
		if (i >= args.length) {
		    break;
		}
		String property = arg.substring(1, arg.length());
		String value = args[i]; // next element
		properties.put(property, value);
	    }
	}
	return properties;
    }
    
    //[CORE]
    /**
     * Initialize special UWT class 'org.plazmaforge.framework.uwt.UWT_<UI>'
     * @param ui
     * @return
     */
    public static boolean initUWT(String ui) {
	try {
	    ui = getUIName(ui);
	    String basePackage = "org.plazmaforge.framework.uwt";
	    String uiPackage = basePackage + "." + ui.toLowerCase();
	    String initializerClassName = uiPackage + ".UWT_" + ui;
	    Class initializerClass = getClass(initializerClassName);
	    Object initializer = initializerClass.newInstance();
	    Method initMethod = initializerClass.getMethod("init", null);
	    initMethod.invoke(initializer, null);
	    System.out.println("UWT_" + ui + " was initialized");
	    return true;
	} catch (ClassNotFoundException e) {
	    System.err.println("UWT Initialize error: Class not found: " + e.getMessage());
	} catch (Throwable e) {
	    if (e instanceof InvocationTargetException) {
		e = ((InvocationTargetException) e).getTargetException();
	    }
	    System.err.println("UWT Initialize error: " + e.getClass() + ": " +  e.getMessage());
	}
	return false;	
    }

    public static Object getClassInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	if (className == null) {
	    return null;
	}
	Class klass = getClass(className);
	return klass.newInstance();
    }
    
    public static Class getClass(String className) throws ClassNotFoundException {
	if (className == null) {
	    return null;
	}
	return Class.forName(className);
    }    
    
    public static Initializer getInitializer(String initializerClass) {
	try {
	    if (initializerClass == null) {
		return null;
	    }
	    Initializer initializer = (Initializer) getClassInstance(initializerClass);
	    return initializer;
	} catch (ClassNotFoundException e) {
	    System.err.println("Load application initializer error: Class not found: " + e.getMessage());
	} catch (Throwable e) {
	    System.err.println("Load application initializer error: " + e.getMessage());
	}
	return null;
    }

    public static Destroyer getDestroyer(String destroyerClass) {
	try {
	    if (destroyerClass == null) {
		return null;
	    }
	    Destroyer destroyer = (Destroyer) getClassInstance(destroyerClass);
	    return destroyer;
	} catch (ClassNotFoundException e) {
	    System.err.println("Load application destroyer error: Class not found: " + e.getMessage());
	} catch (Throwable e) {
	    System.err.println("Load application destroyer error: " + e.getMessage());
	}
	return null;
    }

    //[CORE]
    /**
     * Return name of UI by code
     * For example: 
     *  swt, SWT, Swt -> SWT
     *  swing, Swing, SWING -> Swing 
     * @param ui
     * @return
     */
    public static String getUIName(String ui) {
	if (ui == null) {
	    return null;
	}
	ui = ui.trim();
	String upper = ui.toUpperCase();
	if ("SWT".equals(upper)) {
	    return "SWT";
	} else if ("SWING".equals(upper)) {
	    return "Swing";
	} else if ("GWT".equals(upper)) {
	    return "GWT";
	} else if ("GXT".equals(upper)) {
	    return "GXT";
	}
	return ui;
    }
    
    public void start(Map<String, String> properties) {
	
	// Transfer start properties to application
	if (properties != null) {
	    ApplicationContext applicationContext = getApplicationContext();
	    Set<String> keys = properties.keySet();
	    for (String key: keys) {
		applicationContext.setProperty(key, properties.get(key));
	    }
	}
	start();
    }
    


}
