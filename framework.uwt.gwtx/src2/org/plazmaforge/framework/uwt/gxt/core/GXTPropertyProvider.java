package org.plazmaforge.framework.uwt.gxt.core;

import org.plazmaforge.framework.core.data.ValidatePropertyProvider;

public class GXTPropertyProvider<T> implements ValidatePropertyProvider<T> {
    
    private String className;

    private XBeanModelFactory factory; 
    
    private XBeanModel model;
    
    public GXTPropertyProvider(String className) {
	this.className = className;
	factory = XBeanModelLookup.get().getFactory(className);
	// Use cast 'null' to 'Object' because need call createModel for one bean (not collection)
	model = (XBeanModel) factory.createModel((Object) null);
    }
    
    public boolean isValid() {
	return model != null;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public Object getValue(T element, String property) {
	if (!isValid()) {
	   return null; 
	}
	model.setBean(element);
	return model.get(property);
    }

    @Override
    public void setValue(T element, String property, Object value) {
	if (!isValid()) {
	   return; 
	}
	model.setBean(element);
	model.set(property, value);
    }

}
