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

package org.plazmaforge.framework.uwt.builder;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.core.resource.Resource;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Style.Direction;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.LayoutRegion;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.widget.tree.TreeItem;

public abstract class AbstractBuilder implements IUIBuilder {

    protected void populate(IData data, UIObject element) {
	if (data == null || element == null) {
	    return;
	}
	
	assign(data, element);
	
	// Populate UIObject properties
	
	String value = getString(data, UIObject.PROPERTY_ID);
	if (value != null) {
	    element.setId(value);
	}
	value = getString(data, UIObject.PROPERTY_NAME);
	if (value != null) {
	    element.setName(value);
	}
	value = getString(data, UIObject.PROPERTY_TYPE);
	if (value != null) {
	    element.setType(value);
	}
	value = getString(data, UIObject.PROPERTY_LOCALE);
	if (value != null) {
	    element.setLocaleName(value);
	}
	value = getString(data, UIObject.PROPERTY_RESOURCE);
	if (value != null) {
	    element.setResourceName(value);
	}	
	
	
    }

    protected void assign(IData data, UIObject element) {
	if (data == null){
	    return;
	}
	data.set(UIBuilder.SYS_PROPERTY_ELEMENT, element);
    }
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // UTIL METHODS
    //
    /////////////////////////////////////////////////////////////////////////////////
    
    protected IData getDataValue(IData data, String property) {
	return (IData) getValue(data, property);
    }
    
    protected Object getValue(IData data, String property) {
	return UIBuilderHelper.getValue(data, property);
    }

    protected String getString(IData data, String property) {
	//return (String) getValue(data, property);
	return UIBuilderHelper.getString(data, property);
    }
    
    protected Object getValue(String type, IData data, String property) {
	return UIBuilderHelper.getValue(type, data, property);
    }
    
    
    /**
     * Return resource string
     * @param data
     * @param property
     * @return
     */
    protected String getRSString(IData data, String property) {
	return UIBuilderHelper.getRSString(data, property);
    }

    /**
     * Return image path
     * @param data
     * @param property
     * @return
     */
    protected String getImagePath(IData data, String property) {
	return getRSString(data, property);
    }


    protected Boolean getBoolean(IData data, String property) {
	return UIBuilderHelper.getBoolean(data, property);
    }

    protected Integer getInteger(IData data, String property) {
	return UIBuilderHelper.getInteger(data, property);
    }

    protected Byte getByte(IData data, String property) {
	return UIBuilderHelper.getByte(data, property);
    }

    protected Short getShort(IData data, String property) {
	return UIBuilderHelper.getShort(data, property);
    }

    protected Float getFloat(IData data, String property) {
	return UIBuilderHelper.getFloat(data, property);
    }

    protected Double getDouble(IData data, String property) {
	return UIBuilderHelper.getDouble(data, property);
    }
  
    ////
    
    protected Date getDate(IData data, String property) {
	return UIBuilderHelper.getDate(data, property);
    }

    protected Date getTime(IData data, String property) {
	return UIBuilderHelper.getTime(data, property);
    }

    protected Date getDateTime(IData data, String property) {
	return UIBuilderHelper.getDateTime(data, property);
    }

    ///

    protected Color getColor(IData data, String property) {
	return UIBuilderHelper.getColor(data, property);
    }
    
    protected Font getFont(IData data, String property) {
	return UIBuilderHelper.getFont(data, property);
    }

    ////
    
    protected HorizontalAlign getHorizontalAlign(IData data, String property) {
	return UIBuilderHelper.getHorizontalAlign(data, property);
    }
    
    protected VerticalAlign getVerticalAlign(IData data, String property) {
	return UIBuilderHelper.getVerticalAlign(data, property);
    }

    protected Direction getDirection(IData data, String property) {
	return UIBuilderHelper.getDirection(data, property);
    }
    
    protected Orientation getOrientation(IData data, String property) {
	return UIBuilderHelper.getOrientation(data, property);
    }

    protected LayoutRegion getLayoutRegion(IData data, String property) {
	return UIBuilderHelper.getLayoutRegion(data, property);
    }

    ////
    
    protected IData getNode(IData data, String name) {
	return UIBuilderHelper.getNode(data, name);
    }

    protected List<IData> getChildrenOfNode(IData data, String name) {
	return UIBuilderHelper.getChildrenOfNode(data, name);
    }

    protected IData createData() {
  	return UIBuilderHelper.createData();
    }
    
    protected IData createData(Map<String, Object> values) {
  	return UIBuilderHelper.createData(values);
    }

    
    /**
     * Parse string data and create <code>IData</code>
     * @param data
     * @return <code>IData</code>
     */
    protected IData createData(String data) {
	return UIBuilderHelper.createData(data);
    }
    
    /**
     * Create <code>IData</code> by <code>DataContainer</code>
     * @param dataContainer
     * @return <code>IData</code>
     */
    protected IData createData(DataContainer dataContainer) {
	return UIBuilderHelper.createData(dataContainer);
    }
    
    
    protected IUIBuilder getBuilder(IData data) {
	return UIBuilder.getBuilder(data, true);
    }
    
    protected UIObject buildDataObject(IData data) {
	IUIBuilder builder = getBuilder(data);
	if (builder == null) {
	    // ERROR
	    return null;
	}
	return builder.buildObject(data);
    }

    protected UIObject buildNodeObject(IData data, String property) {
	IData nodeData = getDataValue(data, property);
	if (nodeData == null) {
	    return null;
	}
	return buildDataObject(nodeData);
    }

    ////
    
    protected List<IData> getDataItems(IData data) {
	return UIBuilderHelper.getDataItems(data);
    }

    protected <T> T getDataValue(Class<T> tClass, IData item) {
	return UIBuilderHelper.getDataValue(tClass, item);
    }

    /**
     * Return list of item value (one value of item)
     * 
     * @param tClass
     * @param data
     * @return
     */
    protected <T> List<T> getItemValues(Class<T> tClass, IData data) {
	return UIBuilderHelper.getItemValues(tClass, data);
    }

    /**
     * Return item value (one value of item)
     * 
     * @param tClass
     * @param item
     * @return
     */
    protected <T> T getItemValue(Class<T> tClass, IData item) {
	return UIBuilderHelper.getItemValue(tClass, item);
    }

    /**
     * Return list of item records (more values of item)
     * 
     * @param tClass
     * @param data
     * @return
     */
    protected List<Object[]> getItemRecords(Class[] tClass, IData data) {
	return UIBuilderHelper.getItemRecords(tClass, data);
    }

    /**
     * Return record of item (more values of item)
     * 
     * @param tClass
     * @param item
     * @return
     */
    protected Object[] getItemRecord(Class[] tClass, IData item) {
	return UIBuilderHelper.getItemRecord(tClass, item);
    }

    protected List<TreeItem> getTreeItems(Class[] tClass, IData data) {
	return UIBuilderHelper.getTreeItems(tClass, data);
    }
    
    ////

    protected void setResource(IData data, Resource resource) {
	UIBuilderHelper.setResource(data, resource);
    }

    protected Resource getResource(IData data) {
	return UIBuilderHelper.getResource(data);
    }
    
    protected Resource findParentResource(IData data) {
	return UIBuilderHelper.findParentResource(data);
    }
    
    protected void assignResource(IData data, UIObject element) {
   	if (data == null || element == null) {
   	    return;
   	}
   	Resource resource = getResource(data);
   	if (resource == null) {
   	    resource = findParentResource(data);
   	    if (resource == null) {
   		 resource = element.getResource();
   	    }
   	    setResource(data, resource);
   	}
    }
    
    ////
    
    protected boolean in(String str, String[] array) {
	if (str == null || array == null || array.length == 0) {
	    return false;
	}
	for (String e: array) {
	    if (str.equals(e)) {
		return true;
	    }
	}
	return false;
    }
    
    protected boolean in(String str, List<String> array) {
	if (str == null || array == null || array.isEmpty()) {
	    return false;
	}
	for (String e: array) {
	    if (str.equals(e)) {
		return true;
	    }
	}
	return false;
    }

    protected boolean eq(Object obj1, Object obj2) {
	if (obj1 == null || obj2 == null) {
	    return false;
	}
	return obj1.equals(obj2); 
    }


}
