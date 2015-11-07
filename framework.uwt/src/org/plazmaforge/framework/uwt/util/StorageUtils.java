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

package org.plazmaforge.framework.uwt.util;

import org.plazmaforge.framework.uwt.ApplicationContext;
import org.plazmaforge.framework.uwt.UIObject;



/**
 * 
 * UWT Storage Utilities
 * 
 * @author ohapon
 *
 */
//[UWT ONLY: ApplicationContext]
public class StorageUtils {

    /**
     * Storage types
     */
    public static final String IMAGE = "IMAGE";
    
    public static final String FONT = "FONT";
    
    public static final String FILE = "FILE";
   
    public static String STORAGE_SEPARATOR_IN_PATH = "::";
    
    public static String STORAGE_SEPARATOR_IN_PROPERTY = "/";
   
    
    /**
     * Return full path
     * @param storage
     * @param path
     * @return
     */
    public static String getPath(String storage, String path) {
	if (path == null) {
	    return null;
	}
	
	// If path is empty then return null
	path = path.trim();
	if (path.isEmpty()) {
	    return null;
	}
	
	// Find storage separator and remove prefix from path
	int index = path.indexOf(STORAGE_SEPARATOR_IN_PATH);
	if (index > 0) {
	    index = index + STORAGE_SEPARATOR_IN_PATH.length(); // convert to start index 
	    if (index == path.length() - 1) {
		return null;
	    }
	    path = path.substring(index).trim();
	}
	
	if (storage != null) {
	    return storage.trim() + "/" + path;
	}
	return path;
    }
    
    /**
     * Return path of application storage by type and name  (optional)
     * 
     * For example:
     * type=IMAGE, property=imageStorage, path=C:\Images
     * type=FONT, property=fontStorage, path=C:\Fonts
     * type=FILE, property=fileStorage, path=C:\Files
     * 
     * @param context
     * @param type
     * @param path
     *  
     * @return path
     */
    public static String getStorage(ApplicationContext context, String type, String path) {
	if (type == null || context == null) {
	    return null;
	}
	String name = null;
	if (path != null) {
	    int index = path.indexOf(STORAGE_SEPARATOR_IN_PATH);
	    if (index > 0) {
		name = path.substring(0, index);
	    }
	}
	String propertyName = type.toLowerCase() + "Storage" + (name == null ? "" : (STORAGE_SEPARATOR_IN_PROPERTY + name));
	return context.getProperty(propertyName);
    }

    public static String getStorage(UIObject element, String type, String path) {
	return getStorage(getApplicationContext(element), type, path);
    }

    /**
     * Return image storage
     * @param context
     * @return
     */
    public static String getImageStorage(ApplicationContext context, String path) {
	return getStorage(context, IMAGE, path);
    }
    
    public static String getImageStorage(UIObject element, String path) {
	return getImageStorage(getApplicationContext(element), path);
    }

    /**
     * Return font storage
     * @param context
     * @return
     */
    public static String getFontStorage(ApplicationContext context, String path) {
	return getStorage(context, FONT, path);
    }

    public static String getFontStorage(UIObject element, String path) {
	return getFontStorage(getApplicationContext(element), path);
    }

    /**
     * Return file storage
     * @param context
     * @return
     */
    public static String getFileStorage(ApplicationContext context, String path) {
	return getStorage(context, FILE, path);
    }

    public static String getFileStorage(UIObject element, String path) {
	return StorageUtils.getFileStorage(getApplicationContext(element), path);
    }
    
    
    public static ApplicationContext getApplicationContext(UIObject element) {
  	return element == null ? null : element.getApplicationContext();
    }

}
