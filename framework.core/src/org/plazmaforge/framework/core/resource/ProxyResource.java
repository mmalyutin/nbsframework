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

import java.util.Map;

public class ProxyResource extends AbstractResource implements Resource {

    private static final long serialVersionUID = 1894506357766544319L;
    
    private Resource resource;

    
    
    public ProxyResource(Resource resource) {
	if (resource == null) {
	    throw new RuntimeException("Resource must be not null");
	}
	this.resource = resource;
    }

    @Override
    public String getName() {
	return resource.getName();
    }

    @Override
    public String getLocaleName() {
	return resource.getLocaleName();
    }

    @Override
    public String getString(String key) {
	return getSafeString(key, resource.getString(key));
    }

    @Override
    public String[] getStringArray(String key) {
	return getSafeStringArray(key, resource.getStringArray(key));
    }

    @Override
    public Map<String, String> getEntries() {
	return resource.getEntries();
    }

}
