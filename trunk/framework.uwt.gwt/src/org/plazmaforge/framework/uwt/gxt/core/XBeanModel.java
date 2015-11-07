package org.plazmaforge.framework.uwt.gxt.core;

import java.util.Collection;

import com.extjs.gxt.ui.client.data.BeanModel;

public class XBeanModel extends BeanModel {

    private static final long serialVersionUID = 4912260085153730500L;
    

    public void setBean(Object bean) {
	super.setBean(bean);
    }
    
    @Override
    public <X> X get(String property) {
	if ("toString".equals(property)) {
	    toString();
	}
	return super.get(property);
    }

    @Override
    public <X> X set(String property, X value) {
	if ("toString".equals(property)) {
	    return value;
	}
	return super.set(property, value);
    }
    
    @Override
    public Collection<String> getPropertyNames() {
      Collection<String> c = super.getPropertyNames();
      c.add("toString");
      return c;
    }
}
