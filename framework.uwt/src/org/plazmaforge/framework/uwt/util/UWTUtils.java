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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.core.data.access.AccessUtils;
import org.plazmaforge.framework.core.data.access.PropertyAccessor;
import org.plazmaforge.framework.uwt.widget.Column;
import org.plazmaforge.framework.uwt.widget.IViewer;
import org.plazmaforge.framework.uwt.widget.tree.Tree;

/**
 * General UWT Utils 
 * 
 * @author ohapon
 *
 */
public class UWTUtils {


    public static final String PROPERTY_FORMAT = "format";
    
    public static final String PROPERTY_ACCESSOR = "accessor";
    
    
    /**
     * Create <code>java.text.Format</code> by pattern
     * 
     * @param pattern
     * @return
     */
    public static Format createFormat(String pattern) {
  	try {
  	    
  	    // TODO: May be use special FormatFactory to create Format by pattern 
  	    
  	    if (pattern == null) {
  		return null;
  	    }
  	    Format format = createNumberFormat(pattern);
  	    if (format != null) {
  		return format;
  	    }
  	    
  	    format = createDateFormat(pattern);
  	    if (format != null) {
  		return format;
  	    }
  	    return null;
  	} catch (Throwable e) {
  	    return null;
  	}
    }
      
    
    /**
     * Create <code>java.text.NumberFormat</code> by pattern
     * If format not valid return null value;
     * @param pattern
     * @return
     */
    public static NumberFormat createNumberFormat(String pattern) {
  	try {
  	    if (pattern == null) {
  		return null;
  	    }
  	    pattern = pattern.trim();
  	    if (pattern.isEmpty()) {
  		return null;
  	    }
  	    DecimalFormat format = new DecimalFormat(pattern);
  	    String str = format.format(0);
  	    
  	    // Check format. If format return pattern string + 0 then the format is invalid.
  	    if ((pattern + "0").equals(str)) { 
  		return null;
  	    }
  	    return format;
  	} catch (Throwable e) {
  	    return null;
  	}
    }
    
    
    /**
     * Create <code>java.text.DateFormat</code> by pattern
     * If format not valid return null value;
     * @param pattern
     * @return
     */
    public static DateFormat createDateFormat(String pattern) {
  	try {
  	    if (pattern == null) {
  		return null;
  	    }
  	    pattern = pattern.trim();
  	    if (pattern.isEmpty()) {
  		return null;
  	    }
  	    SimpleDateFormat format = new SimpleDateFormat(pattern);
  	    String str = format.format(new Date());
  	    
  	    // Check format. If format return pattern string then the format is invalid.
  	    if (pattern.equals(str)) { 
  		return null;
  	    }
  	    return format;
  	} catch (Throwable e) {
  	    return null;
  	}
    }
      
      
    /**
     * Format value by <code>java.text.Format</code>
     * @param value
     * @param format
     * @return
     */
    public static String format(Object value, Format format) {
	if (value == null) {
	    return null;
	}
	if (format == null) {
	    return value.toString();
	}
	try {
	    return format.format(value);
	} catch (Throwable ex) {
	    return value.toString();
	}
    }    
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    /**
     * Format value by Column
     * @param value
     * @param column
     * @return
     */
    public static String format(Object value, Column column) {
	Format format = null;
	if (column == null) {
	    return format(value, format);
	}
	format = (Format) column.getData(PROPERTY_FORMAT);
	if (format != null) {
	    return format(value, format);
	}
	String pattern = column.getFormat(); // pattern of format
	if (pattern == null) {
	    return format(value, format);
	}
	format = createFormat(pattern);
	column.setData(PROPERTY_FORMAT, format);
	return format(value, format);
    }

    
    public static String format(Object value, IViewer<?> viewer) {
	Format format = null;
	if (viewer == null) {
	    return format(value, format);
	}
	format = (Format) viewer.getData(PROPERTY_FORMAT);
	if (format != null) {
	    return format(value, format);
	}
	
	if (!(viewer instanceof Tree)) {
	    return format(value, format);
	}
	
	String pattern = ((Tree) viewer).getDisplayFormat(); // pattern of format
	if (pattern == null) {
	    return format(value, format);
	}
	format = createFormat(pattern);
	viewer.setData(PROPERTY_FORMAT, format);
	return format(value, format);
    }
    
    

    
    public static Object getValue(Object obj, PropertyAccessor propertyAccessor) {
	if (obj == null || propertyAccessor == null) {
	    return null;
	}
	return propertyAccessor.getValue(obj);
    }


    public static Object getValue(Object element, IViewer<?> viewer, Column column) {
	
	if  (element == null || viewer == null || column == null) {
	    return null;
	}

	// Get ValueProvider
	ValueProvider valuePovider = column.getValueProvider();
	if (valuePovider != null) {
	    // By ValueProvider
	    return getValue(element, valuePovider);
	}
	    
	// Get property
	String property = column.getProperty();
	if (property == null) {
	    return null;
	}

	// Get PropertyProvider
	PropertyProvider propertyProvider = viewer.getPropertyProvider();
	if (propertyProvider != null) {
	    // By PropertyProvider
	    return getValue(element, property, propertyProvider);
	}

	// By PropertyAccessor
	PropertyAccessor propertyAccessor = getPropertyAccessor(element, column);
	return getValue(element, propertyAccessor);	    
	
    }

 
    
    public static Object getValue(Object element, ValueProvider valuePovider,  String property, PropertyProvider propertyProvider) {

	if (element == null) {
	    return null;
	}

	// By ValueProvider
	if (valuePovider != null) {
	    return getValue(element, valuePovider);
	}

	if (propertyProvider != null) {
	    // By PropertyProvider
	    return getValue(element, property, propertyProvider);
	}
	
	// By PropertyProvider
	return getValue(element, property, propertyProvider);
    }

    /**
     * Get value by ValueProvider 
     * @param element
     * @param valuePovider
     * @return
     */
    public static Object getValue(Object element, ValueProvider valuePovider) {

	if (element == null || valuePovider == null) {
	    return null;
	}
	return valuePovider.getValue(element);
    }

    /**
     * Get value by PropertyProvider 
     * @param element
     * @param property
     * @param propertyProvider
     * @return
     */
    public static Object getValue(Object element, String property,  PropertyProvider propertyProvider) {

	if (element == null || property == null || propertyProvider == null) {
	    return null;
	}
	return propertyProvider.getValue(element, property);
    }
    

    /**
     * Return formatted text value
     * @param value
     * @param column
     * @return
     */
    public static String getTextValue(Object value, Column column) {
	return format(value, column);
    }       

    /**
     * Return formatted text value
     * @param value
     * @param viewer
     * @return
     */
    public static String getTextValue(Object value, IViewer<?> viewer) {
	return format(value, viewer);
    }       

    /**
     * Return value
     * @param element
     * @param property
     * @param propertyProvider
     * @return
     */
    public static Object getSimpleValue(Object element, String property,  PropertyProvider propertyProvider, IViewer<?> viewer) {

   	if (element == null) {
   	    return null;
   	}
   	
   	if (property == null) {
   	    return element.toString(); // TO_STRING
   	}
   	
   	// By PropertyProvider
   	if (propertyProvider != null) {
   	    return propertyProvider.getValue(element, property);
   	}
   	

   	// By PropertyAccessor
   	PropertyAccessor propertyAccessor = getPropertyAccessor(element, property, viewer);
	if (propertyAccessor == null) {
	    // TODO !!! WHY
	    return element.toString(); // TO_STRING
	}
	return getValue(element, propertyAccessor);
   }
    
    
    
    public static PropertyAccessor getPropertyAccessor(Object obj, String property) {
	if (obj == null || property == null) {
	    return null;
	}
	PropertyAccessor propertyAccessor = createPropertyAccessor(obj.getClass(), property);
	return propertyAccessor;

    }

    
    
    /**
     * Get PropertyAccessor by Column
     * @param obj
     * @param column
     * @return
     */
    public static PropertyAccessor getPropertyAccessor(Object obj, Column column) {
	if (obj == null || column == null) {
	    return null;
	}
	PropertyAccessor propertyAccessor = (PropertyAccessor) column.getData(PROPERTY_ACCESSOR);
	if (propertyAccessor == null) {
	    propertyAccessor = createPropertyAccessor(obj.getClass(), column.getProperty());
	    column.setData(PROPERTY_ACCESSOR, propertyAccessor);
	}
	return propertyAccessor;

    }
    

    
    /**
     * Get PropertyAccessor by Viewer
     * Use only for Tree without columns
     * @param obj
     * @param tree
     * @return
     */
    public static PropertyAccessor getPropertyAccessor(Object obj, String property, IViewer<?> viewer) {
	if (obj == null || viewer == null) {
	    return null;
	}
	PropertyAccessor propertyAccessor = (PropertyAccessor) viewer.getData(PROPERTY_ACCESSOR);
	if (propertyAccessor == null) {
	    propertyAccessor = createPropertyAccessor(obj.getClass(), property);
	    viewer.setData(PROPERTY_ACCESSOR, propertyAccessor);
	}
	return propertyAccessor;

    }
    
    
    public static PropertyAccessor createPropertyAccessor(Class<?> entityClass, String property) {
	return (entityClass == null || property == null) ? null : AccessUtils.getPropertyAccessor(entityClass, property);
    }



}
