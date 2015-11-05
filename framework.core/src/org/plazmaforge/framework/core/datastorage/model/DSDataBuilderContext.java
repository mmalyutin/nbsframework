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

/**
 * 
 */
package org.plazmaforge.framework.core.datastorage.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.util.StringUtils;

/**
 * @author ohapon
 *
 */
public class DSDataBuilderContext {

    private List<DSDataConnector> dataConnectors;
    
    private List<DSDataSource> dataSources;
    
    private List<DSDataSet> dataSets;
 
    
    private Map<DSDataSource, Boolean> findHierachyMap;

    public DSDataBuilderContext() {
	findHierachyMap = new HashMap<DSDataSource, Boolean>();
    }

    public DSDataBuilderContext(List<DSDataConnector> dataConnectors, List<DSDataSource> dataSources, List<DSDataSet> dataSets) {
	this();
	this.dataConnectors = dataConnectors;
	this.dataSources = dataSources;
	this.dataSets = dataSets;
    }
    
    public List<DSDataConnector> getDataConnectors() {
        return dataConnectors;
    }

    public List<DSDataSource> getDataSources() {
        return dataSources;
    }

    public List<DSDataSet> getDataSets() {
        return dataSets;
    }

    public DSDataConnector findDataConnector(String name) {
	if (name == null || dataConnectors == null) {
	    return null;
	}
	name = normalize(name);
	if (name == null) {
	    return null;
	}
	for (DSDataConnector dataConnector: dataConnectors) {
	    if (name.equals(normalize(dataConnector.getName()))) {
		return dataConnector; 
	    }
	}
	return null;
    }
    
    public DSDataSource findDataSource(String name) {
	if (name == null || dataSources == null) {
	    return null;
	}
	name = normalize(name);
	if (name == null) {
	    return null;
	}
	for (DSDataSource dataSource: dataSources) {
	    if (name.equals(normalize(dataSource.getName()))) {
		return dataSource; 
	    }
	}
	return null;
    }
    
    public DSDataSet findDataSet(String name) {
	if (name == null || dataSets == null) {
	    return null;
	}
	name = normalize(name);
	if (name == null) {
	    return null;
	}
	for (DSDataSet dataSet: dataSets) {
	    if (name.equals(normalize(dataSet.getName()))) {
		return dataSet; 
	    }
	}
	return null;
    }
    
    public DSDataSet findDataSetByDataSource(String name) {
	if (name == null || dataSets == null) {
	    return null;
	}
	name = normalize(name);
	if (name == null) {
	    return null;
	}
	for (DSDataSet dataSet: dataSets) {
	    if (name.equals(normalize(dataSet.getDataSourceName()))) {
		return dataSet; 
	    }
	}
	return null;
    }
    
    public List<DSDataSource> findDataSourceChildren(String parentName, boolean isRoot) {
	if (isEmpty(dataSources)) {
	    return null;
	}
	parentName = normalize(parentName); 
	List<DSDataSource> result = new ArrayList<DSDataSource>();
	boolean flag = false;
	for (DSDataSource dataSource: dataSources) {
	    if (isRoot) {
		flag = isEmpty(dataSource.getParentName());
	    } else {
		flag = equals(parentName, normalize(dataSource.getParentName()));
	    }
	    if (flag) {
		
		Boolean f = findHierachyMap.get(dataSource);
		if (f == Boolean.TRUE) {
		    //TODO: throw error
		}
		
		findHierachyMap.put(dataSource, true);
		
		result.add(dataSource);
		
	    }
	}
	return result.isEmpty() ? null : result;
    }
    
    public boolean isEmptyData() {
	return (isEmpty(dataConnectors) && isEmpty(dataSources) && isEmpty(dataSets));
    }
	
    protected String normalize(String str) {
	return StringUtils.normalizeString(str);
    }
    
    protected boolean isEmpty(String str) {
	return normalize(str) == null;
    }

    protected boolean isEmpty(Collection<?> collection) {
	return collection == null || collection.isEmpty();
    }

    protected boolean equals(String s1, String s2) {
	if (s1 == null || s2 == null) {
	    return false;
	}
	return s1.equals(s2);
    }
	
}
