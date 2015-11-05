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

package org.plazmaforge.framework.core.resource;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Implementation of <code>ResourceBundle</code> Resource
 * 
 * @author ohapon
 *
 */
public class BundleResource extends AbstractResource implements Resource {

    private String bundleName;
    
    private String localeName;
    
    
    
    private ResourceBundle resourceBundle;
    
    private boolean init;
    
    private boolean error;
    
    private String errorMessage;
    
    /**
     * Special safe mode
     */
    private boolean safeMode;
    
    
    
    public BundleResource(String bundleName) {
	this(bundleName, null);
    }
    
    public BundleResource(String bundleName, String localeName) {
	this(bundleName, localeName, true);
    }
    
    public BundleResource(String bundleName, String localeName, boolean safeMode) {
	super();
	this.bundleName = bundleName;
	this.localeName = localeName;
	this.safeMode = safeMode;
    }


    public String getBundleName() {
        return bundleName;
    }

    public String getName() {
        return bundleName;
    }

    public String getLocaleName() {
        return localeName;
    }


    public boolean isInit() {
        return init;
    }

    public boolean isError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private ResourceBundle getResourceBundle() {
	if (resourceBundle == null) {
	    resourceBundle = init ? null: createResourceBundle();
	}
        return resourceBundle;
    }

    private ResourceBundle createResourceBundle() {
	try {
	    init = true;
	    Locale locale = createLocale(localeName);
	    return ResourceBundle.getBundle(bundleName, locale);
	} catch (Throwable e) {
	    error = true;
	    errorMessage = e.getMessage();
	    // TODO: ERROR
	}
	return null;
    }
    
    private Locale createLocale(String localeName) {
	if (localeName != null) {
	    localeName = localeName.trim();
	}
	if (localeName == null || localeName.isEmpty()){
	    return Locale.getDefault();
	}
	String language = "";
	String country = "";
	String variant = "";
	String[] values = localeName.split("_");
	
	if (values.length > 0) {
	    language = values[0];
	}
	if (values.length > 1) {
	    country = values[1];
	}
	if (values.length > 2) {
	    variant = values[2];
	}
	Locale locale = new Locale(language, country, variant);
	return locale;
    }
    
    
    @Override
    public String getString(String key) {
	return getSafeString(key, getResourceString(key));
    }

    @Override
    public String[] getStringArray(String key) {
	return getSafeStringArray(key, getResourceStringArray(key));
    }

    protected String getResourceString(String key) {
	if (getResourceBundle() == null) {
	    return null;
	}
	try {
	    return getResourceBundle().getString(key);
	} catch (MissingResourceException e) {
	    if (safeMode) {
		return null;
	    }
	    throw e;
	}
    }
    
    protected String[] getResourceStringArray(String key) {
   	if (getResourceBundle() == null) {
   	    return null;
   	}
   	try {
   	    return getResourceBundle().getStringArray(key);
   	} catch (MissingResourceException e) {
   	    if (safeMode) {
   		return null;
   	    }
   	    throw e;
   	}
    }
    
    public Set<String> getKeys() {
	ResourceBundle resourceBundle = getResourceBundle();
	if (resourceBundle == null) {
	    return new HashSet<String>(); 
	}
	Enumeration<String> enumKeys = resourceBundle.getKeys();
	LinkedHashSet<String> keys = new LinkedHashSet<String>();
	while (enumKeys.hasMoreElements()) {
	    keys.add(enumKeys.nextElement());
	}
	return keys;
    }

    @Override
    public Map<String, String> getEntries() {
	ResourceBundle resourceBundle = getResourceBundle();
	if (resourceBundle == null) {
	    return new HashMap<String, String>(); 
	}
	Enumeration<String> enumKeys = resourceBundle.getKeys();
	LinkedHashMap<String, String> entries = new LinkedHashMap<String, String>();
	while (enumKeys.hasMoreElements()) {
	    String key = enumKeys.nextElement();
	    String value = resourceBundle.getString(key);
	    entries.put(key, value);
	}
	return entries;
    }
}
