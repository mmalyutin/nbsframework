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
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.util.CoreUtils;

public class BasePropertyHolder implements PropertyHolder {

    private Map<String, String> propertyMap;
    
    @Override
    public String getProperty(String property) {
	return getPropertyMap().get(property);
    }

    @Override
    public void setProperty(String property, String value) {
	getPropertyMap().put(property, value);
    }

    @Override
    public List<String> getPropertyNames() {
	return CoreUtils.toKeyList(getPropertyMap());
    }

    private Map<String, String> getPropertyMap() {
	if (propertyMap == null) {
	    propertyMap = new HashMap<String, String>();
	}
	return propertyMap;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((propertyMap == null) ? 0 : propertyMap.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	BasePropertyHolder other = (BasePropertyHolder) obj;
	if (propertyMap == null) {
	    if (other.propertyMap != null)
		return false;
	} else if (!propertyMap.equals(other.propertyMap))
	    return false;
	return true;
    }
    
    
}
