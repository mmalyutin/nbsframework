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
package org.plazmaforge.framework.util;

import java.io.Serializable;
import java.util.Locale;

/**
 * The class describes an property values
 * 
 * @author ohapon
 *
 */
public class PropertyValueStore implements Serializable {

    private static final long serialVersionUID = -3283372104679208474L;
    
    private PropertyElement propertyElement;

    public PropertyValueStore(PropertyElement propertyElement) {
	super();
	this.propertyElement = propertyElement;
    }

    public PropertyValue getPropertyValue(int index) {
	String key = getPropertyKey();
	

	if (key == null) {
	    return null;
	}
	IPropertiesStore store = getStore();
	if (store == null) {
	    return null;
	}
	Locale locale = store.getLocales().get(index);
	return store.getPropertyValue(locale, key);

    }

    public PropertyValue getPropertyValue(Locale locale) {
	String key = getPropertyKey();
	if (key == null) {
	    return null;
	}
	IPropertiesStore store = getStore();
	if (store == null) {
	    return null;
	}
	return store.getPropertyValue(locale, key);

    }

    protected IPropertiesStore getStore() {
	return propertyElement == null ? null : propertyElement.getStore();
    }

    protected String getPropertyKey() {
	return propertyElement == null ? null : propertyElement.getKey();
    }

	
}
