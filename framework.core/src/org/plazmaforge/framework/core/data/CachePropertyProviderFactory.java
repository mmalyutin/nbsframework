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

package org.plazmaforge.framework.core.data;

import java.util.HashMap;
import java.util.Map;


public class CachePropertyProviderFactory implements PropertyProviderFactory {

    
    private Map<String, PropertyProvider<?>> providers = new HashMap<String, PropertyProvider<?>>(); 
    
    @Override
    public PropertyProvider<?> getPropertyProvider(String type) {
	if (type == null) {
	    return null;
	}
	// Get provider form register
	PropertyProvider<?> propertyProvider = doGetPropertyProvider(type);
	if (propertyProvider != null) {
	    return check(propertyProvider);
	}
	// Create new provider
	propertyProvider = createPropertyProvider(type);
	if (propertyProvider == null ) {
	    return null;
	}
	// Add provider to register
	addPropertyProvider(type, propertyProvider);
	return check(propertyProvider);
    }

    @Override
    public <T> PropertyProvider<T> getPropertyProvider(Class<T> klass) {
	return (PropertyProvider<T>) getPropertyProvider(klass == null ? null : klass.getName());
    }
    
    public void addPropertyProvider(String type, PropertyProvider<?> propertyProvider) {
	providers.put(type, propertyProvider);
    }

    public void removePropertyProvider(String type) {
	providers.remove(type);
    }

    
    
    protected PropertyProvider<?> doGetPropertyProvider(String type) {
	return providers.get(type);
    }

    /**
     * If provider is not valid return null
     * @param propertyProvider
     * @return
     */
    protected PropertyProvider<?> check(PropertyProvider<?> propertyProvider) {
  	if  (propertyProvider == null) {
  	    return null;
  	}
  	if (propertyProvider instanceof ValidatePropertyProvider) {
  	    return ((ValidatePropertyProvider<?>) propertyProvider).isValid() ? propertyProvider : null;
  	}
  	return propertyProvider;
    }


    /**
     * Create PropertyProvider
     * By default return null
     * @param type
     * @return
     */
    protected PropertyProvider<?> createPropertyProvider(String type) {
	return null;
    }
       
    protected String getErrorMessage(Throwable t) {
	return t == null ? null : t.getMessage();
    }
       
    /**
     * Invalid PropertyProvider
     * Use to create error provider
     *
     * @param <T>
     */
    public static class InvalidPropertyProvider<T> implements ValidatePropertyProvider<T> {

	private String errorMessage;
	    
	
	public InvalidPropertyProvider() {
	}
	
	public InvalidPropertyProvider(String errorMessage) {
	    super();
	    this.errorMessage = errorMessage;
	}

	@Override
	public Object getValue(T element, String property) {
	    return null;
	}

	@Override
	public void setValue(T element, String property, Object value) {
	}

	@Override
	public boolean isValid() {
	    return false; // NOT VALID
	}

	public String getErrorMessage() {
	    return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
	    this.errorMessage = errorMessage;
	}

	
	
    }
}
