package org.plazmaforge.framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoreUtils {

    private CoreUtils() {
    }

    
    public static <T> boolean contains(T[] array, T element) {
	if (array == null || array.length == 0 || element == null) {
	    return false;
	}
	for (T e: array) {
	    if (element.equals(e)) {
		return true;
	    }
	}
	return false;
    }

    public static <T> boolean contains(Collection<T> collection, T element) {
	if (collection == null || collection.isEmpty() || element == null) {
	    return false;
	}
	return collection.contains(element);
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
    
    
    /**
     * Converts command line arguments to Map<String, String>
     * @param args
     * @return
     */
    public static Map<String, String> toInputMap(String[] args) {
	Map<String, String> map  = new HashMap<String, String>();
	transferArguments(args, map);
	return map;
    }
    
    
    /**
     * Transfers command line arguments to Map
     * @param args
     * @param map
     */
    public static void transferArguments(String[] args, Map map) {
   	if (args == null || args.length == 0) {
   	    return;
   	}
   	String p = null;
   	String v = null;
      	for (int i = 0; i < args.length; i++) {
      	    p = normalizeArg(args[i]);
      	    if (p == null) {
      		continue;
      	    }
      	    if (p.startsWith("-") && p.length() > 1) {
      		p = p.substring(1);
      		if ((i + 1) < args.length) {
      		    v = normalizeArg(args[i + 1]);
      		    if (v != null && v.startsWith("-")) {
      			v = "true";
      		    } else {
      			i++;
      		    }
      		} else {
      		    v = "true";
      		}
      		if (v == null) {
      		    continue;
      		}
      		map.put(p, v);
      		
      	    }
      	}
    }
    
    private static String normalizeArg(String str) {
	return str; // ?
    }
        
}
