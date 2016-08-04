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

package org.plazmaforge.framework.script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScriptUtils {
    
    public static boolean isGlobalVariable(String identifier) {
	if (identifier != null && identifier.startsWith("$")) {
	    return true; 
	}
	return false;
    }

    public static Scope getSafeScope(Scope scope) {
	return scope == null ? new Scope() : scope;
    }
    
    public static <T> List<T> getSafeList(List<T> list) {
	return list == null ? new ArrayList<T>() : list; 
    }
    
    public static <T> Set<T> getSafeSet(Set<T> list) {
	return list == null ? new LinkedHashSet<T>() : list; 
    }
    
    
    public static Map<String, Function> getSafeFunctions(Map<String, Function> functions) {
	return functions == null ? new HashMap<String, Function>() : functions; 
    }

    public static <V> List<V> newList() {
	return new ArrayList<V>(); // by default ArrayList
    }

    public static <V> Set<V> newSet() {
	return new LinkedHashSet<V>(); // by default LinkedHashSet (physic order)
    }

    public static <K, V> Map<K, V> newMap() {
	return new LinkedHashMap<K, V>(); // by default LinkedHashMap (physic order)
    }

    public static <V> List<V> cloneList(List<V> original) {
	List<V> list = newList();
	list.addAll(original);
	return list;
    }
    
    public static <V> Set<V> cloneSet(Set<V> original) {
	Set<V> set = newSet();
	set.addAll(original);
	return set;
    }
    
    public static <K, V> Map<K, V> cloneMap(Map<K, V> original) {
	Map<K, V> map = newMap();
	map.putAll(original);
	return map;
    }

}
