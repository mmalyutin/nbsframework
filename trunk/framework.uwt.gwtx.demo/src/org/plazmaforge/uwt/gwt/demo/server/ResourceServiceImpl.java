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

package org.plazmaforge.uwt.gwt.demo.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.resource.BundleResourceProvider;
import org.plazmaforge.framework.core.resource.CacheResource;
import org.plazmaforge.framework.core.resource.Resource;
import org.plazmaforge.uwt.gwt.demo.client.ResourceService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ResourceServiceImpl extends RemoteServiceServlet implements ResourceService {

    public Resource getResource(String name, String locale) {
	BundleResourceProvider resourceProvider = BundleResourceProvider.load(name, locale);
	Resource resource = resourceProvider.getResource(name, locale);
	return resource;
    }
    
    
    public List<Resource> getResources(String[] names, String locale) {
	BundleResourceProvider resourceProvider = BundleResourceProvider.load(names, locale);
	List<Resource> input = resourceProvider.getResources();
	List<Resource> output = new ArrayList<Resource>();
	for (Resource resource: input) {
	    Map<String, String> entries = resource.getEntries();
	    CacheResource cacheResource = new CacheResource(resource.getName(),locale, entries);
	    output.add(cacheResource);
	}
	return output;
    }
    
    
}
