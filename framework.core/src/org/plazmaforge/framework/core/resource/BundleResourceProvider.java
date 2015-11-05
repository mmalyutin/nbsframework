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


public class BundleResourceProvider extends CacheResourceProvider {

    public BundleResourceProvider() {
	super();
    }

    public BundleResourceProvider(Resource resource) {
	super(resource);
    }

    public BundleResourceProvider(Resource[] resources) {
	super(resources);
    }

    public static BundleResourceProvider load(String[] names, String locale) {
	BundleResourceProvider provider = new BundleResourceProvider();
	for (String name: names) {
	    Resource resource = provider.createResource(name, locale);
	    provider.addResource(resource);
	}
	return provider;
    }
    
    public static BundleResourceProvider load(String name, String locale) {
	BundleResourceProvider provider = new BundleResourceProvider();
	Resource resource = provider.createResource(name, locale);
	provider.addResource(resource);
	return provider;
    }
    
    @Override
    public Resource getResource() {
	return super.getResource();
    }


    @Override
    public Resource getResource(String name) {
	Resource resource = super.getResource(name);
	if (resource == null) {
	    resource = createResource(name, null);
	}
	return resource;
    }

    @Override
    public Resource getResource(String name, String locale) {
	Resource resource = super.getResource(name, locale);
	if (resource == null) {
	    resource = createResource(name, locale);
	}
	return resource;
    }
    
    
    
    protected Resource createResource(String name, String locale) {
	return new BundleResource(name, locale);
    }

}
