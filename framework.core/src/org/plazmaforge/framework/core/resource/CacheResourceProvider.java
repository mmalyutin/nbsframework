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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheResourceProvider implements ResourceProvider {

    private Map<String, Resource> resources = new HashMap<String, Resource>(); 
    
    private Resource resource;
    
    public CacheResourceProvider() {
	super();
    }
    
    public CacheResourceProvider(Resource resource) {
	if (resource != null) {
	    addResource(resource);
	    this.resource = resource;
	}
    }

    public CacheResourceProvider(Resource[] resources) {
	if (resources != null) {
	    for (Resource resource : resources) {
		addResource(resource);
	    }
	    if (resources.length > 0){
		this.resource = resources[0];
	    }
	}
    }


    public void addResource(Resource resource) {
	if (resource == null) {
	    throw new IllegalArgumentException("Resource must be not null");
	}
	if (resource.getName() == null) {
	    throw new IllegalArgumentException("Name of resource must be not null");
	}

	String key = createResourceKey(resource.getName(), resource.getLocaleName());
	resources.put(key, resource);
    }

    private String createResourceKey(String name, String locale) {
	if (locale == null) {
	    return name;
	}
	return name + "|" + locale;
    }
    
    @Override
    public Resource getResource() {
	return resource;
    }

    @Override
    public Resource getResource(String name) {
	String key = createResourceKey(name, null); // Locale is null (without locale)
	Resource resource = resources.get(key);
	if (resource == null) {
	    // If not found use DEFAULT_LOCALE
	    key = createResourceKey(name, DEFAULT_LOCALE);
	    resource = resources.get(key); 
	}
	return resource;
    }

    @Override
    public Resource getResource(String name, String locale) {
	String key = createResourceKey(name, locale);
	return resources.get(key);
    }

    public List<Resource> getResources() {
	Collection<Resource> entries = resources.values();
	return new ArrayList<Resource>(entries);
    }
}
