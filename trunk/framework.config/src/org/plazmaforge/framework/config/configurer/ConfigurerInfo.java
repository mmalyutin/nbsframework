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

package org.plazmaforge.framework.config.configurer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.config.object.IObjectConfig;

/**
 * 
 * @author ohapon
 *
 */
public class ConfigurerInfo<T extends IObjectConfig> implements Serializable {

    private static final long serialVersionUID = 6902099394572220599L;

    private String name;
    
    private String type;
    
    private String className;
    
    private String fileName;

    private List<T> objects;
    
    
    public ConfigurerInfo() {
	super();
    }

    public ConfigurerInfo(String name, String type, String className, String fileName) {
	super();
	this.name = name;
	this.type = type;
	this.className = className;
	this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getClassName() {
        return className;
    }

    public String getFileName() {
        return fileName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public List<T> getObjects() {
	return objects;
    }

    public void setObjects(List<T> objects) {
	this.objects = objects;
    }

    public void addObject(T c) {
	if (objects == null) {
	    objects = new ArrayList<T>();
	}
	objects.add(c);

    }
    
    /**
     * Return object by ID
     * @param id
     * @return
     */
    public T getObjectById(String id) {
	if (id == null || objects == null) {
	    return null;
	}
	for (T o : objects) {
	    if (id.equals(o.getId())) {
		return o;
	    }
	}
	return null;
    }

    /**
     * Return object by Code
     * @param code
     * @return
     */
    public T getObjectByCode(String code) {
	if (code == null || objects == null) {
	    return null;
	}
	for (T o : objects) {
	    if (code.equals(o.getCode())) {
		return o;
	    }
	}
	return null;
    }
    
    /**
     * Return object by Name
     * @param name
     * @return
     */
    public T getObjectByName(String name) {
	if (name == null || objects == null) {
	    return null;
	}
	for (T o : objects) {
	    if (name.equals(o.getName())) {
		return o;
	    }
	}
	return null;
    }
    
    
    /**
     * Return objects by category
     * @param category
     * @return
     */
    public List<T> getObjects(String category) {
	if (objects == null) {
	    return null;
	}
	
	// If category is null them return all objects
	if (category == null) {
	    return objects;
	}
	List<T> resultObjects = new ArrayList<T>();
	for (T o: objects) {
	    if (category.equals(o.getCategory())) {
		resultObjects.add(o);
	    }
	}
	return resultObjects;
    }    
    
    public String toString() {
	return "" + name + " " + className;
    }
    
    public ConfigurerInfo<T> clone(String category) {
	ConfigurerInfo<T> clone = new ConfigurerInfo<T>(this.name, this.type, this.className, this.fileName);
	List<T> cloneObjects = getObjects(category);
	clone.setObjects(cloneObjects);
	return clone;
    }
    
    public static ConfigurerInfo<?> getConfigurer(List<ConfigurerInfo<?>> configurers, String name) {
	if (configurers == null || configurers.isEmpty()) {
	    return null;
	}
	for (ConfigurerInfo<?> configurer : configurers) {
	    if (name.equals(configurer.getName())) {
		return configurer;
	    }
	}
	return null;
    }
    
    public static List<ConfigurerInfo<?>> cloneConfigurers(List<ConfigurerInfo<?>> configurers, String[] names, String ui) {
	boolean emptyNames = names == null || names.length == 0;
	List<ConfigurerInfo<?>> result = new ArrayList<ConfigurerInfo<?>>(); 
	String category = null;
	for (ConfigurerInfo<?> configurer:  configurers) {
	    if (emptyNames || in(configurer.getName(), names)) {
		category = isUIDependencyConfigurer(configurer.getName()) ? ui: null;
		ConfigurerInfo<?> clone = configurer.clone(category);
		result.add(clone);
	    }
	}
	return result;
    }
    
    private static boolean isUIDependencyConfigurer(String configurerName) {
	return "MenuConfigurer".equals(configurerName) || "MenuConfigurer".equals(configurerName);
    }
    
    private static boolean in(String str, String[] array) {
	if (str == null || array == null || array.length == 0) {
	    return false;
	}
	for (String a: array) {
	    if (str.equals(a)) {
		return true;
	    }
	}
	return false;
    }

}
