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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.ValuePresenter;
import org.plazmaforge.framework.core.data.object.Data;
import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.core.resource.Resource;
import org.plazmaforge.framework.core.resource.ResourceUtils;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.Style.Direction;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.LayoutRegion;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.widget.tree.TreeItem;

public class UIBuilderHelper {
    
    // Alternative Properties
    private static Map<String, String> alternativeProperties = new HashMap<String, String>();

    public static String RS_PREFIX = "@{";
    
    public static String RS_SUFFIX = "}";
    
    static {
	alternativeProperties.put("hAlign", "horizontalAlign");
	alternativeProperties.put("vAlign", "verticalAlign");
	alternativeProperties.put("hFlex", "horizontalFlex");
	alternativeProperties.put("vFlex", "verticalFlex");
	
	alternativeProperties.put("align", "horizontalAlign");
	alternativeProperties.put("flex", "horizontalFlex");
    }

    
    public static Object getValue(IData data, String property) {
	if (data == null || property == null) {
	    return null;
	}

	// Get value by property
	Object value = data.get(property);
	if (value != null) {
	    return value;
	}

	// Get child by property (name)
	value = getNode(data, property);

	return value;
    }

    /**
     * Return node of data by property name
     * @param data
     * @param name
     * @return
     */
    public static IData getNode(IData data, String name) {
	if (name == null) {
	    return null;
	}
	List<IData> children = getChildren(data);
	if (children == null || children.isEmpty()) {
	    return null;
	}
	for (IData child : children) {
	    String childName = (String) child.get(UIBuilder.SYS_PROPERTY_NAME);
	    if (name.equals(childName)) {
		return child;
	    }
	}
	return null;
    }

    /**
     * Return children of node of data by property name
     * @param data
     * @param name
     * @return
     */
    public static List<IData> getChildrenOfNode(IData data, String name) {
	IData node = getNode(data, name);
	if (node == null) {
	    return null;
	}
	return (List<IData>) getValue(node, UIBuilder.SYS_PROPERTY_CHILDREN);
    }
    
    
    /**
     * Return all children (nodes) of data
     * 
     * @param data
     * @return
     */
    public static List<IData> getChildren(IData data) {
	if (data == null) {
	    return null;
	}
	return (List<IData>) data.get(UIBuilder.SYS_PROPERTY_CHILDREN);
    }

    /**
     * Return children (nodes) by name of data
     * @param data
     * @param name
     * @return
     */
    public static List<IData> getChildren(IData data, String name) {
	if (data == null || name == null) {
	    return null;
	}
	List<IData> children = getChildren(data);
	if (children == null) {
	    return null;
	}
	List<IData> result = new ArrayList<IData>();
	for (IData child : children) {
	    String childName = (String) child.get(UIBuilder.SYS_PROPERTY_NAME);
	    if (name.equals(childName)) {
		result.add(child);
	    }
	}
	return result;
    }

    /**
     * Return data items
     * Tag <items>
     * @param data
     * @return
     */
    public static List<IData> getDataItems(IData data) {
	return getChildrenOfNode(data, Widget.PROPERTY_ITEMS);
    }

    public static <T> T getDataValue(Class<T> tClass, IData item) {
	// TODO: Must use tClass to convert value from string to tClass value
	String s = (String) item.get(UIBuilder.SYS_PROPERTY_VALUE);
	return (T) s;
    }
    
    /**
     * Return list of item value (one value of item)
     * @param tClass
     * @param data
     * @return
     */
    public static <T> List<T> getItemValues(Class<T> tClass, IData data) {
	List<IData> items = getDataItems(data);
	if (items == null) {
	    return null;
	}
	List<T> list = new ArrayList<T>();
	for (IData item : items) {
	    T object = getItemValue(tClass, item);
	    list.add(object);
	}
	return list;
    }

    /**
     * Return item value (one value of item)
     * @param tClass
     * @param item
     * @return
     */
    public static <T> T getItemValue(Class<T> tClass, IData item) {
	return getDataValue(tClass, item);
    }
    
    /**
     * Return list of item records (more values of item)
     * @param tClass
     * @param data
     * @return
     */
    public static List<Object[]> getItemRecords(Class[] tClass, IData data) {
	List<IData> items = getDataItems(data);
	if (items == null) {
	    return null;
	}
	List<Object[]> list = new ArrayList<Object[]>();
	for (IData item : items) {
	    Object[] record = getItemRecord(tClass, item);
	    list.add(record);
	}
	return list;
    }

    /**
     * Return record of item (more values of item)
     * @param tClass
     * @param item
     * @return
     */
    public static Object[] getItemRecord(Class[] tClass, IData item) {
	int length = tClass.length;
	Object[] record = new Object[length];
	if (length == 0) {
	    return record;
	}
	record[0] = (String) item.get(UIBuilder.SYS_PROPERTY_VALUE);
	List<IData> valuesNode = UIBuilderHelper.getChildren(item, Widget.PROPERTY_VALUE);
	if (valuesNode == null || valuesNode.isEmpty()) {
	    return record;
	}
	int i = -1;
	for (IData valueNode: valuesNode) {
	    i++;
	    if (i == length) {
		break;
	    }
	    record[i] = getDataValue(tClass[i], valueNode);
	}
	return record;
    }
    
    public static List<TreeItem> getTreeItems(Class[] tClass, IData data) {
	List<IData> items = getDataItems(data);
	if (items == null) {
	    return null;
	}
	List<TreeItem> list = new ArrayList<TreeItem>();
	for (IData item : items) {
	    Object[] record = getItemRecord(tClass, item);
	    TreeItem treeItem = new TreeItem();
	    list.add(treeItem);
	    if (record != null && record.length >= 0) {
		for (int i = 0; i < record.length; i++) {
		    treeItem.setValue(i, record[i]);
		}
	    }
	    List<TreeItem> children = getTreeItems(tClass, item);
	    if (children == null) {
		continue;
	    }
	    for (TreeItem child: children) {
		treeItem.addItem(child);
	    }
	}
	return list;
    }
    
    public static ValuePresenter getPresenter(String type) {
	return UIPresenter.getPresenter(type);
    }
    
    public static Object getValue(String type, IData data, String property) {
	String value = getString(data, property);
	if (value == null) {
	    return null;
	}
	if (type == null || "String".equals(type)) {
	    return value;
	}
	if (type == null || "Boolean".equals(type)) {
	    return UIPresenter.toBoolean(value);
	}
	if (type == null || "Integer".equals(type)) {
	    return UIPresenter.toInteger(value);
	}
	if (type == null || "Float".equals(type)) {
	    return UIPresenter.toFloat(value);
	}
	
	//TODO
	return null;
	
	
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
    public static String getString(IData data, String property) {
	return (String) getValue(data, property);
    }

    public static Boolean getBoolean(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toBoolean(value);
    }

    public static Byte getByte(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toByte(value);
    }
    
    public static Short getShort(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toShort(value);
    }

    public static Integer getInteger(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toInteger(value);
    }
    
    public static Float getFloat(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toFloat(value);
    }
    
    public static Double getDouble(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toDouble(value);
    }

    public static Date getDate(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toDate(value);
    }

    public static Date getTime(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toTime(value);
    }

    public static Date getDateTime(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toDateTime(value);
    }

    public static Color getColor(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toColor(value);
    }

    public static Font getFont(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toFont(value);
    }

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static String getRSString(IData data, String property) {
	String value = getString(data, property);
	if (value == null) {
	    return null;
	}
	String rsKey = getRSKey(value);
	if (rsKey == null) {
	    return value;
	}
	if (rsKey.isEmpty()) {
	    return ""; // (?)
	}
	UIObject element = (UIObject) data.get(UIBuilder.SYS_PROPERTY_ELEMENT);
	if (element == null) {
	    return ResourceUtils.getEmptyString(rsKey);
	}
	
	//TODO
	String rsValue = null;
	Resource resource = findParentResource(data);
	if (resource != null) {
	    rsValue = resource.getString(rsKey);
	} else {
	    rsValue = element.getString(rsKey); // getResourceString()
	}
	
	if (rsValue == null) {
	    return  ResourceUtils.getEmptyString(rsKey);
	}
	return rsValue;
    }

    public static void setResource(IData data, Resource resource) {
	if (data == null) {
	    return;
	}
	data.set(UIBuilder.SYS_PROPERTY_RESOURCE, resource);
    }

    public static Resource getResource(IData data) {
	return data == null ? null : (Resource) data.get(UIBuilder.SYS_PROPERTY_RESOURCE);
    }
    
    public static Resource findParentResource(IData data) {
	if (data == null) {
	    return null;
	}
	Resource resource = getResource(data);
	if (resource != null) {
	    return resource;
	}
	IData parent = (IData) data.get(UIBuilder.SYS_PROPERTY_PARENT);
	if (parent == null) {
	    return null;
	}
	return findParentResource(parent);
    }

    /**
     * Parse string and return resource key
     * @param str
     * @return
     */
    public static String getRSKey(String str) {
	if (str == null) {
	    return null;
	}
	str = str.trim();
	if (str.isEmpty()) {
	    return null;
	}
	if (str.startsWith(RS_PREFIX) && str.endsWith(RS_SUFFIX)) {
	    if (str.length() == (RS_PREFIX.length() + RS_SUFFIX.length())) { // "@{}" 
		return "";
	    }
	    return str.substring(RS_PREFIX.length(), str.length() - RS_SUFFIX.length()).trim();
	}
	return null;
    }
    
    public static HorizontalAlign getHorizontalAlign(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toHorizontalAlign(value);
    }

    public static VerticalAlign getVerticalAlign(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toVerticalAlign(value);
    }

    public static Direction getDirection(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toDirection(value);
    }

    public static Orientation getOrientation(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toOrientation(value);
    }

    public static LayoutRegion getLayoutRegion(IData data, String property) {
	String value = getString(data, property);
	return UIPresenter.toLayoutRegion(value);
    }

    

  
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static DataContainer parseDataContainer(String data) {
	return parseDataContainer(data, "()", ";", "=");
    }
    
    public static DataContainer parseDataContainer(String data, String quotes, String separator, String operation) {
	if (data == null) {
	    //Data empty
	    return null;
	}
	data = data.trim();
	if (data.isEmpty()) {
	    //Data empty
	    return null;
	}
	if (quotes == null || quotes.length() != 2 
		|| separator == null || separator.isEmpty()
		|| operation == null || operation.isEmpty()) {
	    // IAE
	    return null;
	}
	
	String header = null;
	String body = null;
	int start = data.indexOf(quotes.substring(0, 1));	// '('
	int end = data.indexOf(quotes.substring(1, 2));		// ')'

	if (start >= 0 && end == data.length() - 1) {

	    if (start == 0) {
		// Empty data: "(...)"
		return null;
	    }

	    // "Header(...)"
	    header = data.substring(0, start);

	    if (end - start > 1) {
		body = data.substring(start + 1, end);
	    }
	} else {
	    header = data;
	}
	
	DataContainer dataContainer = new DataContainer();
	dataContainer.setName(header);
	
	if (body == null) {
	    return dataContainer;
	}
	
	String[] properties = body.split(separator);
	if (properties != null && properties.length > 0) {
	    Map<String, Object> propertyMap = new LinkedHashMap<String, Object>();
	    for (String line : properties) {
		String[] array = line.split(operation);
		if (array != null && array.length == 2) {
		    String p = array[0];
		    if (p != null) {
			p = p.trim();
		    }
		    String v = array[1];
		    if (v != null) {
			v = v.trim();
		    }
		    if (p != null && !p.isEmpty() && v != null && !v.isEmpty()) {
			p = getPropertyName(p);
			propertyMap.put(p, v);
		    }
		}
	    }
	    dataContainer.setProperties(propertyMap);
	}

	return dataContainer;
    }
    
    /**
     * Create empty <code>IData</code>
     * @return
     */
    public static IData createData() {
  	return new Data();
    }

    /**
     * Create <code>IData</code> by values
     * @param values
     * @return
     */
    public static IData createData(Map<String, Object> values) {
  	return new Data(values);
    }


    /**
     * Create <code>IData</code> by string presentation
     * @param data
     * @return
     */
    public static IData createData(String data) {
   	DataContainer dataContainer = parseDataContainer(data);
   	return createData(dataContainer);
   }
       
    /**
     * Create <code>IData</code> by <code>DataContainer</code>
     * 
     * @param dataContainer
     * @return <code>IData</code>
     */
    public static IData createData(DataContainer dataContainer) {
	if (dataContainer == null) {
	    return null;
	}
	IData data = createData(dataContainer.getProperties());
	data.set(UIObject.PROPERTY_TYPE, dataContainer.getName());
	return data;
    }

    
    private static String getPropertyName(String propertyName) {
	if (propertyName == null) {
	    return null;
	}
	String alternativePropertyName = alternativeProperties.get(propertyName);
	return alternativePropertyName == null ? propertyName : alternativePropertyName;
    }

    
    public static String toDumpString(IData data) {
	if (data == null) {
	    return "Data[null]";
	}

	StringBuffer buf = new StringBuffer("Data[");
	buf.append("$type=" + data.get(UIBuilder.SYS_PROPERTY_TYPE));
	buf.append(", $name=" + data.get(UIBuilder.SYS_PROPERTY_NAME));
	buf.append(", $parent=" + data.get(UIBuilder.SYS_PROPERTY_PARENT));
	
	
	List<IData> childList = (List<IData>) data.get(UIBuilder.SYS_PROPERTY_CHILDREN);
	if (childList == null) {
	    buf.append("]");
	    return buf.toString();
	}
	
	buf.append(", $children=[");
	int size = childList.size();
	IData child = null;
	for (int i = 0; i < size; i++) {
	    child =  childList.get(0);
	    if (i > 0) {
		buf.append(", ");
	    }
	    buf.append(toDumpString(child));
	}
	buf.append("]");
	return buf.toString();
	
    }
    
    /**
     * Set parent of children
     * @param data
     */
    public static void assingParent(IData data) {
	if (data == null) {
	    return;
	}
	List<IData> childList = (List<IData>) data.get(UIBuilder.SYS_PROPERTY_CHILDREN);
	if (childList == null) {
	    return;
	}
	
	for (IData child: childList) {
	    IData parent = (IData) child.get(UIBuilder.SYS_PROPERTY_PARENT);
	    if (parent != null) {
		continue;
	    }
	    parent = data;
	    child.set(UIBuilder.SYS_PROPERTY_PARENT, parent);
	    
	    assingParent(child);
	}


    }
}
