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

package org.plazmaforge.framework.uwt.data.store;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.PropertyProvider;

/**
 * The Sorter for properties of the element 
 * 
 * @author ohapon
 *
 */
//[CORE]
public class PropertySorter<T> extends DataSorter<T> {

    private List<PropertyInfo> properties;
    
    private PropertyProvider<T> propertyProvider;

    public List<PropertyInfo> getProperties() {
	if (properties == null) {
	    properties = new ArrayList<PropertyInfo>();
	}
        return properties;
    }

    public void setProperties(List<PropertyInfo> properties) {
        this.properties = properties;
    }

    public void addProperty(String property, boolean asc) {
        getProperties().add(new PropertyInfo(property, asc));
    }
    
    public void addProperty(String property) {
	addProperty(property, true);
    }

    public boolean hasProperties() {
	return properties != null && !properties.isEmpty();
    }
    
    /**
     * Return true if the sorter has only one (single) property
     * @return
     */
    public boolean isSingle() {
	return properties != null && properties.size() == 1;
    }
    
    /**
     * Return first property
     * @return
     */
    public PropertyInfo getProperty() {
	return (properties != null && properties.size() > 0) ? properties.get(0) : null;
		
    }

 
    public PropertyProvider<T> getPropertyProvider() {
        return propertyProvider;
    }

    public void setPropertyProvider(PropertyProvider<T> propertyProvider) {
        this.propertyProvider = propertyProvider;
    }
    
    public int compare(T e1, T e2) {
	
	int cat1 = category(e1);
	int cat2 = category(e2);

	if (cat1 != cat2) {
	    return cat1 - cat2;
	}
	
	if (e1 == null & e2 == null) {
	    return 0;
	}
	if (e1 == null) {
	    return -1;
	}
	if (e2 == null) {
	    return 1;
	}
	
	int result = 0;
	
	for (PropertyInfo property : properties) {
	    Object v1 = getPropertyProvider().getValue(e1, property.getName());
	    Object v2 = getPropertyProvider().getValue(e2, property.getName());
	    boolean asc = property.isAsc();
	    result = compareValue(v1, v2, asc);
	    if (result != 0) {
		return result;
	    }
	}
	return result;
    }
    
    
    protected int compareValue(Object v1, Object v2, boolean asc) {
	if (v1 == null && v2 == null) {
	    return 0;
	}
	if (v1 == null) {
	    return asc ? -1 : 1;
	}
	if (v2 == null) {
	    return asc ? 1 : -1;
	}
	int result = 0;
	if (v1 instanceof Comparable) {
	    result = ((Comparable) v1).compareTo(v2);
	    if (!asc) {
		result = result * -1;
	    }
	    return result;
	}
	if (v2 instanceof Comparable) {
	    result = ((Comparable) v2).compareTo(v1);
	    if (!asc) {
		result = result * -1;
	    }
	    return result;

	}
	return 0;
    }
    
    public void sort(List<T> elements) {
	if (!isValid()) {
	    return;
	}
	super.sort(elements);
    }
    
    protected boolean isValid() {
	return propertyProvider != null && properties != null && !properties.isEmpty();
    }
    
    public static class PropertyInfo {
	
	private String name;
	
	private boolean asc;

	public PropertyInfo(String name, boolean asc) {
	    super();
	    this.name = name;
	    this.asc = asc;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public boolean isAsc() {
	    return asc;
	}

	public void setAsc(boolean asc) {
	    this.asc = asc;
	}
	
    }
}
