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


package org.plazmaforge.framework.config.configurer;

import java.util.Locale;

import org.plazmaforge.framework.config.object.IElement;
import org.plazmaforge.framework.util.IPropertiesStore;


public class ConfigurerHelper {

    
    
    
    private ConfigurerHelper() {
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // NLS Property methods
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public static String createPropertyKey(String objectName, String propertyName) {
	return objectName + "." + propertyName;
    }
    
    public static String getNLSString(IPropertiesStore store, String propertyKey) {
	return getNLSString(store, store.getLocale(), propertyKey);
    }
    
    public static String getNLSString(IPropertiesStore store, Locale locale, String propertyKey) {
	if (store == null) {
	    return null;
	}
	String str = null;
	try {
	    str = store.getDisplayValue(locale, propertyKey);
	} catch (Exception ex) {
	    // Not found
	}
	return str;
    }
    
    
    ////////
    
    
    public static void setNLSString(IPropertiesStore store, Locale locale, IElement objectConfig, String propertyName, String propertyValue) {
	String propertyKey = createPropertyKey(objectConfig.getId(), propertyName);
	setNLSString(store, locale, propertyKey, propertyValue);
    }
    
    public static void setNLSString(IPropertiesStore store, Locale locale, String propertyKey, String propertyValue) {
	store.setDisplayValue(locale, propertyKey, propertyValue, true);
    }
    
}
