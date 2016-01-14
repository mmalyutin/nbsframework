/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.util.CoreUtils;

public class DynamicObject implements Accessor {

    private Map<String, Object> propertyMap;
    
    //@Override
    public Object get(String property) {
	return getPropertyMap().get(property);
    }

    //@Override
    public void set(String property, Object value) {
	getPropertyMap().put(property, value);
    }

    //@Override
    public List<String> getPropertyNames() {
	return CoreUtils.toKeyList(getPropertyMap());
    }

    private Map<String, Object> getPropertyMap() {
	if (propertyMap == null) {
	    propertyMap = new HashMap<String, Object>();
	}
	return propertyMap;
    }

}
