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

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Implementation of Null Resource
 * 
 * @author ohapon
 *
 */
public class NullResource extends AbstractResource implements Resource {

    
    private static final long serialVersionUID = -9054386914056106476L;
    

    @Override
    public String getString(String key) {
	return getSafeString(key, null);
    }

    @Override
    public String[] getStringArray(String key) {
	return getSafeStringArray(key, null);
    }

    @Override
    public String getName() {
	return "empty";
    }
    
    @Override
    public String getLocaleName() {
	return "";
    }
    
    public Map<String, String> getEntries() {
	return new HashMap<String, String>(); 
    }

}
