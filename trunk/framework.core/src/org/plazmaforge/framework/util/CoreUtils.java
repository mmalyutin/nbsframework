package org.plazmaforge.framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CoreUtils {

    private CoreUtils() {
    }

    public static <K, V> List<K> toKeyList(Map<K, V> map) {
	if (map == null) {
	    return null;
	}
	List<K> keys = new ArrayList<K>();
	Set<K> keySet = map.keySet();
	for (K k : keySet) {
	    keys.add(k);
	}
	return keys;
    }
    
//    public static <K, V> K[] toKeyArray(Map<K, V> map) {
//	List<K> keys = toKeyList(map);
//	return keys == null ? null : (K[]) keys.toArray(); 
//    }
    
    public static <K, V> List<V> toValueList(Map<K, V> map) {
	if (map == null) {
	    return null;
	}
	List<V> values = new ArrayList<V>();
	Collection<V> valueCollection = map.values();
	for (V k : valueCollection) {
	    values.add(k);
	}
	return values;
    }

    public static List<Object> toObjectList(List<?> list) {
	if (list == null) {
	    return null;
	}
	List<Object> result = new ArrayList<Object>();
	for (Object e : list) {
	    result.add(e);
	}
	return result;
    }

    public static Object[] toObjectArray(List<?> list) {
	if (list == null) {
	    return null;
	}
	return list.toArray(new Object[0]);
    }

    public static <T> List<T> cloneList(List<T> list) {
	if (list == null || list.isEmpty()) {
	    return new ArrayList<T>();
	}
	return new ArrayList<T>(list);
    }

    public static <F, T> void transferList(List<F> from, List<T> to) {
	if (from == null || from.isEmpty()) {
	    return;
	}
	if (to == null) {
	    throw new IllegalArgumentException("Output list must be not null");
	}
	for (F e : from) {
	    to.add((T) e);
	}
    }     
     
    public static void transferProperties(Properties properties, Map<String, String> map) {
	if (properties == null || map == null) {
	    return;
	}
	Set<Object> keySet = properties.keySet();
	for (Object key: keySet) {
	    if (key == null) {
		continue;
	    }
	    map.put(key.toString(), properties.getProperty(key.toString()));
	}
    }
    
    public static Map<String, String> toMap(Properties properties) {
	if (properties == null) {
	    return null;
	}
	Map<String, String> map = new HashMap<String, String>();
	transferProperties(properties, map);
	return map;
    }
    
    public static String getSimpleClassName(String className) {
	if (className == null) {
	    return null;
	}
	int index = className.lastIndexOf(".");
	return (index > 0 && index != className.length() - 1) ? className.substring(index + 1) : className;
    }

    public static String getPackageName(String className) {
	if (className == null) {
	    return null;
	}
	int index = className.lastIndexOf(".");
	return index > 0 ? className.substring(0, index) : null;
    }
    

}
