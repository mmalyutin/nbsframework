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

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ohapon
 *
 */
public class Parameters implements Serializable {

    private static final long serialVersionUID = 6721447302525476332L;

    
    public static final String BY_INDEX = "byIndex";
    
    public static final String BY_NAME = "byName";
    
    
    private LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
    
    private int generator;
    
    public Parameters() {
    }

    public void add(String name, Object value) {
	//TODO: Check '$1234567890'
	map.put(name, value);
    }

    public void add(Object value) {
	String name = "$" + getGenerator();
	map.put(name, value);
    }
    
    public void remove(String name) {
	map.remove(name);
    }

    public void remove(int index) {
	map.remove(findKey(index));
    }
    
    public Object get(String name) {
	return map.get(name);
    }
    
    public Object get(int index) {
	return map.get(findKey(index));
    }
    
    //TODO: Must deprecated
    public void put(String name, Object value) {
	add(name, value);
    }
    
    protected String findKey(int index) {
	if (index < 0 || index >= size()) {
	    throw new IndexOutOfBoundsException();
	}
	Set<Map.Entry<String, Object>> entries = map.entrySet();
	int i = 0;
	String key = null;
	for (Map.Entry<String, Object> entry: entries) {
	    if (i == index) {
		key = entry.getKey(); 
		break;
	    }
	}
	return key;
    }
    
    public boolean isEmpty() {
	return map.isEmpty();
    }

    public int size() {
	return map.size();
    }

    public void clear() {
	map.clear();
    }
    
    public Object[] toArray() {
	return map.values().toArray();
    }

    public Map<String, Object> toMap() {
	return new LinkedHashMap<String, Object>(map);
    }

    private int getGenerator() {
	return generator++;
    }
    
    public static Parameters byIndex(Object[] values) {
	Parameters parameters = new Parameters();
	if (values == null ) {
	    return parameters;
	}
	for (Object value: values) {
	    parameters.add(value);
	}
	return parameters;
    }
    
    public static Parameters byName(Map<String, Object> values) {
	Parameters parameters = new Parameters();
	if (values == null ) {
	    return parameters;
	}
	Set<Map.Entry<String, Object>> entries = values.entrySet();
	for (Map.Entry<String, Object> entry: entries) {
	    parameters.add(entry.getKey(), entry.getValue());
	}
	return parameters;
    }

}
