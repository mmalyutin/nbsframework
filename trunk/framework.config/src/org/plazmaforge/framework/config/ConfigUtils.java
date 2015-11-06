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


package org.plazmaforge.framework.config;

import java.util.List;

import org.plazmaforge.framework.config.object.ConfigIdentifier;
import org.plazmaforge.framework.config.object.IConfigIdentifier;
import org.plazmaforge.framework.config.object.IElement;
import org.plazmaforge.framework.config.object.IUIObjectConfig;
import org.plazmaforge.framework.util.CoreUtils;
import org.plazmaforge.framework.util.StringUtils;


public class ConfigUtils {

    
    
    
    private ConfigUtils() {
    }

    /**
     * Generate id by code Example: Code = 'SaleOrder', Id = 'SALE_ORDER'
     * 
     * @param code
     * @return
     */
    public static String generateIdByCode(String code) {
	if (code == null) {
	    return null;
	}
	int len = code.length();
	String str = code.trim();
	StringBuffer buf = new StringBuffer();
	for (int i = 0; i < len; i++) {
	    char ch = str.charAt(i);
	    if (Character.isUpperCase(ch) && i != 0) {
		buf.append("_");
	    }
	    buf.append(Character.toUpperCase(ch));
	}
	return buf.toString();
    }

    public static String generateCodeById(String id) {
	return generateCodeById(id, true);
    }

    public static String generateCodeById(String id, boolean isCheck) {
	if (isCheck && StringUtils.isEmpty(id)) {
	    throw new IllegalArgumentException("String is empty");
	}
	if (id == null) {
	    return null;
	}
	String s = id.trim();

	char[] ca = s.toCharArray();
	StringBuffer buf = new StringBuffer();
	boolean isStart = true;
	for (int i = 0; i < ca.length; i++) {

	    if (ca[i] == '_') {
		isStart = true;
		continue;
	    }

	    if (isStart) {
		// TODO: Bad code
		buf.append((new Character(ca[i])).toString().toUpperCase());
	    } else {
		// TODO: Bad code
		buf.append((new Character(ca[i])).toString().toLowerCase());
	    }

	    isStart = false;
	}

	return buf.toString();
    }
    
    public static boolean isUpperString(String str) {
	if (str == null) {
	    return false;
	}
	char[] ca = str.toCharArray();
	for (int i = 0; i < ca.length; i++) {
	    if (Character.isLowerCase(ca[i])) {
		return false;
	    }
	}

	return true;
    }
    
    
    public static String createIdByClass(Class klass) {
	return createIdByClassName(klass.getName());
    }

    public static String createIdByClassName(String className) {
	int pos;
	pos = className.lastIndexOf('.');
	String code = className;
	if (pos > 0) {
	    code = className.substring(pos + 1);
	}
	return generateIdByCode(code);
    }

    public static IConfigIdentifier createDefaultConfigIdentifier(Class klass) {
	if (klass == null) {
	    return null;
	}
	IConfigIdentifier idn = new ConfigIdentifier();
	idn.setConfigName(CoreUtils.getSimpleClassName(klass.getName()));
	return idn;
    }
    
    
    private static boolean isEmpty(String str) {
	return StringUtils.isEmpty(str);
    }
    
    
    public static int getNextIndex(List list, Object obj) {
	if (list == null || obj == null) {
	    return -1;
	}
	int size = list.size();
	if (size == 0) {
	    return -1;
	}
	int index = list.indexOf(obj);
	if (index < 0) {
	    return -1;
	}
	int lastIndex = size - 1;
	index++;
	if (index >= lastIndex ) {
	    return -1;
	}
	return index;
    }
    
    public static int getPrevIndex(List list, Object obj) {
	if (list == null || obj == null) {
	    return -1;
	}
	int size = list.size();
	if (size == 0) {
	    return -1;
	}
	int index = list.indexOf(obj);
	if (index < 1) {
	    return -1;
	}
	index--;
	return index;
    }
    

    public static int canUpElement(List list, Object obj) {
	if (list == null || obj == null) {
	    return -1;
	}
	int index = list.indexOf(obj);
	if (index > 0) {
	    return index;
	}
	return -1;
    }

    public static int canDownElement(List list, Object obj) {
	if (list == null || obj == null) {
	    return -1;
	}
	int index = list.indexOf(obj);
	int size = list.size();
	if (index > -1 && index < size - 1) {
	    return index;
	}
	return -1;
    }

    public static boolean isUpElement(List list, Object obj) {
	return canUpElement(list, obj) > -1;
    }

    
    public static boolean isDownElement(List list, Object obj) {
	return canDownElement(list, obj) > -1;
    }

    
    public static boolean upElement(List list, Object obj) {
	int index = canUpElement(list, obj);
	if (index < 0) {
	    return false;
	}
	int prevIndex = index - 1;
	Object prevObj = list.get(prevIndex);
	list.set(prevIndex, obj);
	list.set(index, prevObj);
	return true;
    }
    
    public static boolean downElement(List list, Object obj) {
	int index = canDownElement(list, obj);
	if (index < 0) {
	    return false;
	}
	int nextIndex = index + 1;
	Object nextObj = list.get(nextIndex);
	list.set(nextIndex, obj);
	list.set(index, nextObj);
	return true;
    }
    
    public static boolean isSupportUI(IUIObjectConfig object, String ui) {
	if (object == null) {
	    return false;
	}
	String uiList = object.getUiType();
	if (isEmpty(uiList) || isEmpty(ui)) {
	    return true;
	}
	String[] uiArray = uiList.split(",");
	if (uiArray.length == 0) {
	    return true;
	}
	ui = ui.toLowerCase().trim();
	for (String uiToken: uiArray) {
	    uiToken = uiToken.toLowerCase().trim();
	    if (uiToken.length() == 0) {
		continue;
	    }
	    if (uiToken.equals(ui) || uiToken.equals("*")) {
		return true;
	    }
	    if (uiToken.equals("not " + ui)) {
		return false;
	    }
	}
	return false;
    }
    
    public String getNLSIdentifier(IElement element) {
	return getIdentifier(element);
    }
    
    public String getIdentifier(IElement element) {
	return (element == null) ? null : element.getId();
    }
    
}
