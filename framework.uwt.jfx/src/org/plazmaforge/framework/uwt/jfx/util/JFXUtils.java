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

package org.plazmaforge.framework.uwt.jfx.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.uwt.jfx.widget.XLayoutContainer;
import org.plazmaforge.framework.uwt.jfx.widget.cell.XCellFactory;
import org.plazmaforge.framework.uwt.jfx.widget.cell.XDateCellFactory;
import org.plazmaforge.framework.uwt.jfx.widget.cell.XNumberCellFactory;
import org.plazmaforge.framework.uwt.util.UWTUtils;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


/**
 * 
 * @author ohapon
 *
 */
public class JFXUtils {
    
    
    public static Image getImage(InputStream is) {
	if (is == null) {
	    return null;
	}
        return new Image(is);
    }
    
    public static Image getClassImage(String path) {
	path = UWTUtils.normalyzeClassImagePath(path);
	return getImage(JFXUtils.class, path);
    }
    
    public static Image getFileImage(String path) {
	Image image = null;
	try {
	    InputStream is = UWTUtils.getInputStream(path);
	    image = normalyzeImage(getImage(is));
	    close(is);
	} catch (Exception e) {
	    image = getMissingImage();
	}
	return image;
    }
    
    public static Image getImage(Class<?> clazz, String path) {
	Image image = null;
	try {
	    InputStream is = UWTUtils.getInputStream(clazz, path);
	    image = normalyzeImage(getImage(is));
	    close(is);
	} catch (Exception e) {
	    image = getMissingImage();
	}
	return image;
    }
    
    private static void close(InputStream is) {
	if (is == null) {
	    return;
	}
	try {
	    is.close();
	} catch (IOException ex) {
	    
	}
    }
    
    public static Image normalyzeImage(Image image) {
	return image == null ? getMissingImage() : image;
    }
    
    public static Image getMissingImage() {
	//TODO
	return null;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
    public static Scene createScene() {
	
	// Create default container
	Parent container = new XLayoutContainer(); //new FlowPane();

	// Create Scene
	Scene scene = new Scene(container);
	
	return scene;
    }
    
    public static XCellFactory createCell(String type) {
	return createCell(type, null);
    }
    
    public static XCellFactory createCell(String type, String pattern) {
	if (type == null && pattern == null) {
	    return null;
	}
	Class klass = TypeUtils.getClass(type);
	if (klass == null  && pattern == null) {
	    return null;
	}
	
	if (klass == null) {
	    
	    NumberFormat formatter1 = createNumberFormat(pattern);
	    if (formatter1 != null) {
		XNumberCellFactory cell = createNumberCell(Number.class, formatter1);
		return cell;
	    }
	    
	    DateFormat formatter2 = createDateTimeFormat(pattern);
	    if (formatter2 != null) {
		XDateCellFactory cell = createDateCell(formatter2);
		return cell;
	    }
	    
	    return null;
	}
	
	if (TypeUtils.isLikeNumberType(type)) {
	    NumberFormat formatter = createNumberFormat(pattern);
	    XNumberCellFactory cell = createNumberCell(klass, formatter);
	    return cell;
	} else if (TypeUtils.isLikeDateType(type) || TypeUtils.isLikeDateType(type)) {
	    DateFormat formatter = createDateTimeFormat(pattern);
	    XDateCellFactory cell = createDateCell(formatter);
	    return cell;
	}
	
	return null;

    }
    
 
    public static XNumberCellFactory createNumberCell(Class<?> type, NumberFormat format) {
	return format == null ? new XNumberCellFactory() : new XNumberCellFactory(format);
    }
    
    public static XDateCellFactory createDateCell(DateFormat format) {
	return format == null ? new XDateCellFactory() : new XDateCellFactory(format);
    }
    
    public static NumberFormat createNumberFormat(String pattern) {
	if (pattern == null) {
	    return null;
	}
	pattern = pattern.trim();
	if (pattern.isEmpty()) {
	    return null;
	}

	try {
	    NumberFormat format = new DecimalFormat(pattern);
	    // if (format == null) {
	    // return null;
	    // }
	    String str = format.format(0);
	    // Check format. If format return pattern string + '0' then the
	    // format is invalid.
	    if ((pattern + "0").equals(str)) {
		return null;
	    }
	    return format;
	} catch (IllegalArgumentException e) {
	    return null;
	}

    }
    
    public static DateFormat createDateTimeFormat(String pattern) {
	if (pattern == null) {
	    return null;
	}
	pattern = pattern.trim();
	if (pattern.isEmpty()) {
	    return null;
	}
	try {
	    DateFormat format = new SimpleDateFormat(pattern);
	    // if (format == null) {
	    // return null;
	    // }

	    // Check format. If format return pattern string then the format is
	    // invalid.
	    String str = format.format(new Date());
	    if (pattern.equals(str)) {
		return null;
	    }
	    return format;
	} catch (IllegalArgumentException e) {
	    return null;
	}

    }
    
    
    /**
     * Sets user data to Node
     * @param node
     * @param key
     * @param value
     */
    public static void setData(Node node, String key, Object value) {
	Map<String, Object> map = (Map<String, Object>) node.getUserData();
	if (map == null) {
	    map = new HashMap<>();
	    node.setUserData(map);
	}
	map.put(key, value);
    }
    
    /**
     * Gets user data from Node
     * @param node
     * @param key
     * @return
     */
    public static Object getData(Node node, String key) {
	Map<String, Object> map = (Map<String, Object>) node.getUserData();
	if (map == null) {
	    return null;
	}
	return map.get(key);
    }    

    public static <T> T getDataOrNull(Class<T> type, Node node, String key) {
	Object value = getData(node, key);
	return asTypeOrNull(type, value);
    }
    
    

    // [CORE]
    public static boolean isType(Class<?> type, Object value) {
	if (value == null) {
	    return false;
	}
	return type.isAssignableFrom(value.getClass());
    }
 
    // [CORE]
    public static final <T> T asType(Class<T> type, Object value) {
	return (T) value;
    } 
    
    // [CORE]
    public static <T> T asTypeOrNull(Class<T> type, Object value) {
	if (!isType(type, value)) {
	    return null;
	}
	return asType(type, value);
    }
}
