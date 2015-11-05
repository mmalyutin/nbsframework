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
package org.plazmaforge.framework.util;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author ohapon
 *
 */
public class SystemUtils {

   

    public static String changeClassNameToFileName(String name) {
	if (name == null) {
	    throw new IllegalArgumentException("Class Name is null");
	}
	return name.replace('.', '/').concat(".class");
    }

    public static String changeFileNameToClassName(String name) {
	if (name == null) {
	    throw new IllegalArgumentException("File Name is null");
	}
	String className = null;
	if (name.toLowerCase().endsWith(".class")) {
	    className = name.replace('/', '.');
	    className = className.replace('\\', '.');
	    className = className.substring(0, className.length() - 6);
	}
	return className;
    }

    public static String getFileNameSuffix(String fileName) {
	if (fileName == null) {
	    throw new IllegalArgumentException("File name is null");
	}
	int pos = fileName.lastIndexOf('.');
	if (pos > 0 && pos < fileName.length() - 1) {
	    return fileName.substring(pos + 1);
	}
	return "";
    }

    public static String removeFileNameSuffix(String fileName) {
	if (fileName == null) {
	    throw new IllegalArgumentException("File name is null");
	}
	int pos = fileName.lastIndexOf('.');
	if (pos > 0 && pos < fileName.length() - 1) {
	    return fileName.substring(0, pos);
	}
	return fileName;
    }

    public static String formatSize(long longSize) {
	return formatSize(longSize, -1);
    }

    public static String formatSize(long longSize, int decimalPos) {
	NumberFormat fmt = NumberFormat.getNumberInstance();
	if (decimalPos >= 0) {
	    fmt.setMaximumFractionDigits(decimalPos);
	}
	final double size = longSize;
	double val = size / (1024 * 1024);
	if (val > 1) {
	    return fmt.format(val).concat(" MB");
	}
	val = size / 1024;
	if (val > 10) {
	    return fmt.format(val).concat(" KB");
	}
	return fmt.format(val).concat(" bytes");
    }

    public static boolean equals(Object obj1, Object obj2) {
	if (obj1 == null && obj2 == null) {
	    return true;
	}
	if (obj1 != null) {
	    return obj1.equals(obj2);
	}
	return obj2.equals(obj1);
    }

    ///////////////////////////////////////////////////////////////////////
    
    public static boolean isEmpty(String str) {
	return str == null || str.isEmpty();
    }

    public static boolean isEmpty(Object str) {
	return str == null;
    }

    // TODO: OLD: BEGIN
    public static boolean isEmpty(String[] array) {
	return array == null || array.length == 0;
    }

    public static boolean isEmptyAll(String[] array) {
	if (isEmpty(array)) {
	    return true;
	}
	for (int i = 0; i < array.length; i++) {
	    if (!isEmpty(array[i]))
		return false;
	}
	return true;
    }
    // TODO:OLD: END

    public static <T> boolean  isEmpty(T[] array) {
	return array == null || array.length == 0;
    }

    public static <T> boolean isEmptyAll(T[] array) {
	if (isEmpty(array)) {
	    return true;
	}
	for (int i = 0; i < array.length; i++) {
	    if (!isEmpty(array[i]))
		return false;
	}
	return true;
    }
    
    
    public static boolean isEmpty(Collection<?> collection) {
	return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?,?> map) {
	return map == null || map.isEmpty();
    }
    
    public static int getListSize(List<?> list) {
	return list == null ? 0 : list.size();
    }

    
}
