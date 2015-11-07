package org.plazmaforge.framework.uwt.gxt.core;

import com.google.gwt.core.client.GWT;

public abstract class XBeanModelLookup {
    

    private static XBeanModelLookup instance;

    /**
     * Returns the singleton bean model lookup.
     * 
     * @return the singleton instance
     */
    public static XBeanModelLookup get() {
      if (instance == null) {
        if (GWT.isClient()) {
          instance = GWT.create(XBeanModelLookup.class);
        }
      }
      return instance;
    }

    /**
     * Returns the factory for the given class.
     * 
     * @param the bean class
     * @return the factory
     */
    public XBeanModelFactory getFactory(Class<?> klass) {
	String className = klass.getName();
	return getFactory(className);
    }
    
    /**
     * Returns the factory for the given class name.
     * @param className
     * @return
     */
    public abstract XBeanModelFactory getFactory(String className);

}
