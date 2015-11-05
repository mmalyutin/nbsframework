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

package org.plazmaforge.framework.core.data.object;

import java.util.List;

/**
 * 
 * @author ohapon
 *
 */
public class Model extends Data implements IModel {

    private static final long serialVersionUID = -5399239220209541782L;
    
    private Meta meta;
    
    public Model() {
	super();
    }

    public Meta getMeta() {
        return meta;
    }
    
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    public Object get(String property) {
	if (isCheckProperty() && !hasProperty(property)) {
	    throw new RuntimeException("Property [" + property + "] not found");
	}
	return super.get(property);
    }

    @Override
    public void set(String property, Object value) {
	if (isCheckProperty() && !hasProperty(property)) {
	    throw new RuntimeException("Property [" + property + "] not found");
	}
	super.set(property, value);
    }


    @Override
    public List<Property> getProperties() {
	return meta == null ? null : meta.getProperties();
    }

    @Override
    public List<String> getPropertyNames() {
	return meta == null ? null : meta.getPropertyNames();
    }
    
    @Override
    public Property getProperty(String name) {
	return meta == null ? null : meta.getProperty(name);
    }

    @Override
    public boolean hasProperty(String name) {
	return meta == null ? false: meta.hasProperty(name);
    }

    @Override
    public List<Method> getMethods() {
	return meta == null ? null: meta.getMethods();
    }

    @Override
    public List<String> getMethodNames() {
	return meta == null ? null : meta.getMethodNames();
    }

    @Override
    public Method getMethod(String name) {
	return meta == null ? null : meta.getMethod(name);
    }

    @Override
    public boolean hasMethod(String name) {
	return meta == null ? false : meta.hasMethod(name);
    }

    @Override
    public Object invoke(String method, Object[] parameters) {
	if (isCheckMethod() && !hasMethod(method)) {
	    throw new RuntimeException("Method [" + method + "] not found");
	}

	if (!hasInvoker()) {
	    return null;
	}
	return null;
    }

    @Override
    public Invoker getInvoker() {
	return meta == null ? null : meta.getInvoker();
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    protected boolean hasInvoker() {
	return getInvoker() != null;
    }

    protected boolean isCheckProperty() {
        return meta == null ? false: meta.isCheckProperty();
    }

    protected boolean isCheckMethod() {
        return meta == null ? false: meta.isCheckMethod();
    }
}
