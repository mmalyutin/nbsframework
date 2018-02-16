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

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.util.StorageUtils;
import org.plazmaforge.framework.util.StringUtils;
import org.plazmaforge.framework.uwt.widget.Listener;


/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractUIAdapter implements UIAdapter {

    public static final String MESSAGE_CANT_ADD_WIDGET  = "Can not add widget to parent";
    
    public static final String MESSAGE_CANT_REMOVE_WIDGET  = "Can not remove widget from parent";
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Default implementations
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
    }
    
    @Override
    public Object getProperty(UIElement element, String name) {
	return null;
    }
    
    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	return null;
    }

    @Override
    public void checkDelegate(UIElement element) {
    }
    
    @Override
    public void addListener(UIElement element, String eventType, Listener listener) {
    }
    
    @Override
    public void removeListener(UIElement element, String eventType, Listener listener) {
    }
    

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Utilities
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////    
    
    protected UIAdapter getAdapter(Class<?> uiObjectClass) {
	return UIAdapterFactory.getAdapter(uiObjectClass);
    }

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
 
    
    protected String getHtmlString(Object value, boolean wrapTag) {
	return toHtml(asString(value), wrapTag);
    }

    protected String getHtmlString(Object value) {
	return getHtmlString(value, false);
    }

    protected String getLinkString(Object value) {
	return toLink(asSafeString(value));
    }

    protected String getSafeHtmlString(Object value, boolean wrapTag) {
	return toHtml(asSafeString(value), wrapTag);
    }

    protected String getSafeHtmlString(Object value) {
	return getSafeHtmlString(value, false);
    }

    protected String toSafeString(Object value) {
 	return value == null ? "" : value.toString();
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
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Cast methods
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////        

    protected String asString(Object value) {
  	return (String) value;
    }

    protected String asSafeString(Object value) {
	return value == null ? "" : (String) value;
    }
    
    protected Boolean asBoolean(Object value) {
	return (Boolean) value;
    }

    protected Number asNumber(Object value) {
	return (Number) value;
    }
    
    protected Integer asInteger(Object value) {
	return (Integer) value;
    }
    
    protected Float asFloat(Object value) {
	return (Float) value;
    }
    
    protected Double asDouble(Object value) {
	return (Double) value;
    }
    
    protected Date asDate(Object value) {
	return (Date) value;
    }    
    
    protected Color asColor(Object value) {
 	return (Color) value;
    } 
    
    protected Font asFont(Object value) {
	return (Font) value;
    }
    
    protected Image asImage(Object value) {
	return (Image) value;
    }
    
    protected boolean booleanValue(Object value) {
 	return value == null ? false : asBoolean(value);
    }
    
    protected int intValue(Object value) {
	return value == null ? 0 : asInteger(value);
    }

    protected float floatValue(Object value) {
	return value == null ? 0f : asFloat(value);
    }

    protected double doubleValue(Object value) {
	return value == null ? 0d : asDouble(value);
    }  
   
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Log
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void log(String message) {
	// Default implementation
	System.out.println(message);
    }
    
    protected void logUI(String message) {
	log("UWT: " + message);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Log methods
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    // Log
    protected void logUnsupportSetProperty(Object widget, String property) {
	logUnsupportProperty("setProperty", widget, property);
    }

    // Log
    protected void logUnsupportGetProperty(Object widget, String property) {
	logUnsupportProperty("getProperty", widget, property);
    }
          
    // Log
    protected void logUnsupportProperty(String title, Object widget, String property) {
  	logUI("" + title + ": Property '"+  property + "' is not supported. Class=" + widget.getClass().getName());
    } 
    
    ////
 
    protected ApplicationContext getApplicationContext(UIElement element) {
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
    protected String getStorage(UIElement element, String type, String path) {
	return StorageUtils.getStorage(element, type, path);
    }

    protected String getImageStorage(UIElement element, String path) {
	return StorageUtils.getImageStorage(element, path);
    }

    protected String getFontStorage(UIElement element, String path) {
	return StorageUtils.getFontStorage(element, path);
    }

    protected String getFileStorage(UIElement element, String path) {
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
