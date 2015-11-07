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

package org.plazmaforge.framework.uwt;

import java.util.Date;

import org.plazmaforge.framework.uwt.util.StorageUtils;
import org.plazmaforge.framework.util.StringUtils;
import org.plazmaforge.framework.uwt.widget.Listener;



public abstract class AbstractUIAdapter implements UIAdapter {

    @Override
    public void setProperty(UIObject element, String name, Object value) {
	
    }
    
    @Override
    public Object getProperty(UIObject element, String name) {
	return null;
    }
    
    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	return null;
    }

    @Override
    public void checkDelegate(UIObject element) {
	
    }
    
    @Override
    public void addListener(UIObject element, String eventType, Listener listener) {
	
    }
    
    @Override
    public void removeListener(UIObject element, String eventType, Listener listener) {
	
    }
    

    // Utilities

    /**
     * Tests if this string starts with the specified prefix.
     * @param str
     * @param prefix
     * @return
     */
    protected boolean startsWith(String str, String prefix) {
	return StringUtils.startsWith(str, prefix);
    }

    /**
     * Tests if this string ends with the specified suffix.
     * @param str
     * @param suffix
     * @return
     */
    protected boolean endsWith(String str, String suffix) {
	return StringUtils.endsWith(str, suffix);
    }

    protected String replicate(String s, int n) {
	return StringUtils.replicate(s, n);
    }
    
    /**
     * Return number pattern by decimal count
     * @param decimals
     * @return
     */
    protected String getNumberPattern(int decimals) {
	String pattern = "##0";
	if (decimals > 0) {
	    pattern += "." + replicate("0", decimals);
	}
	return pattern;
    }
    
    protected String getString(Object value) {
	return (String) value;
    }

    protected String getSafeString(Object value) {
	return value == null ? "" : (String) value;
    }

    
    protected String getHtmlString(Object value, boolean wrapTag) {
	return toHtml(getString(value), wrapTag);
    }

    protected String getHtmlString(Object value) {
	return getHtmlString(value, false);
    }

    protected String getLinkString(Object value) {
	return toLink(getSafeString(value));
    }

    protected String getSafeHtmlString(Object value, boolean wrapTag) {
	return toHtml(getSafeString(value), wrapTag);
    }

    protected String getSafeHtmlString(Object value) {
	return getSafeHtmlString(value, false);
    }

    /**
     * Convert string to HTML
     * @param str
     * @param wrapTag
     * @return
     */
    protected String toHtml(String str, boolean wrapTag) {
	if (str == null) {
	    return null;
	}
	String html = StringUtils.toHtml(str);
	if (wrapTag) {
		html = "<html>" + html + "</html>";
	    }

	return html;
    }

    /**
     * Convert string to HTML Link text
     * @param str
     * @return
     */
    protected String toLink(String str) {
	if (str == null) {
	    return "";
	}
	return "<a>" + str.trim() + "</a>";
    }

    protected String toHtml(String str) {
	return toHtml(str, false);
    }


    protected Date getDate(Object value) {
	return (Date) value;
    }
    

    protected Boolean getBoolean(Object value) {
	return (Boolean) value;
    }

    protected boolean booleanValue(Object value) {
	return value == null ? false : getBoolean(value);
    }

    protected Integer getInteger(Object value) {
	return (Integer) value;
    }

    protected Number getNumber(Object value) {
	return (Number) value;
    }

    protected int intValue(Object value) {
	return value == null ? 0 : getInteger(value);
    }
   
    ////
 
    protected ApplicationContext getApplicationContext(UIObject element) {
	return StorageUtils.getApplicationContext(element);
    }

    protected String getPath(String storage, String path) {
	return StorageUtils.getPath(storage, path);
    }
    
    /**
     * Return path of application storage by type
     * 
     * @param type
     * @return path
     */
    protected String getStorage(UIObject element, String type, String path) {
	return StorageUtils.getStorage(element, type, path);
    }

    protected String getImageStorage(UIObject element, String path) {
	return StorageUtils.getImageStorage(element, path);
    }

    protected String getFontStorage(UIObject element, String path) {
	return StorageUtils.getFontStorage(element, path);
    }

    protected String getFileStorage(UIObject element, String path) {
	return StorageUtils.getFileStorage(element, path);
    }

    protected boolean eq(Object obj1, Object obj2) {
	if (obj1 == null || obj2 == null) {
	    return false;
	}
	return obj1.equals(obj2); 
    }
    
    /**
     * Return true if object contains in array
     * @param obj
     * @param array
     * @return
     */
    protected boolean in(Object obj, Object[] array) {
	if (obj == null || array == null || array.length == 0) {
	    return false;
	}
	for (Object o: array ) {
	    if (eq(obj, o) ) {
		return true;
	    }
	}
	return false; 
    }
    
    
    protected boolean checkArgs(Object[] args, int length) {
	if (args == null || args.length != length) {
	    return false;
	}
	return true;
    }


}
