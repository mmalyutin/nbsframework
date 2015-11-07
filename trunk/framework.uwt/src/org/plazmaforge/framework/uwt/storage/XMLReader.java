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

package org.plazmaforge.framework.uwt.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.builder.UIBuilderHelper;



public class XMLReader implements XMLInfo {
    
    
    private static final String DELIM = "-";
    
    // Root Tags
    private static Set<String> rootTags = new HashSet<String>();

    // XML tag -> UI widget type (name with short package)
    private static Map<String, String> nodeTypes = new HashMap<String, String>();
    
    // Alternative XML Tags
    private static Map<String, String> alternativeTags = new HashMap<String, String>();

    // Alternative XML Attributes
    private static Map<String, String> alternativeAttributes = new HashMap<String, String>();

    // Attribute mapping: XML -> Java
    private static Map<String, String> xmlToJavaMap = new HashMap<String, String>();
    
    
    
    
    static {

	///////////////////////////////////////////////////////////////////////
	// Root Tags
	///////////////////////////////////////////////////////////////////////
	
	rootTags.add(XML_COMPOSITE);
	rootTags.add(XML_PANEL);
	rootTags.add(XML_WINDOW);
	rootTags.add(XML_FRAME);
	rootTags.add(XML_DIALOG);
	rootTags.add(XML_WIZARD);
	rootTags.add(XML_VIEW);
	rootTags.add(XML_FORM);
	
	
	///////////////////////////////////////////////////////////////////////
	// Node Types
	///////////////////////////////////////////////////////////////////////
	
	nodeTypes.put("composite", UIBuilder.COMPOSITE_TYPE);
	
	// Panels
	nodeTypes.put("panel", UIBuilder.PANEL_TYPE);
	nodeTypes.put("horizontal-panel", UIBuilder.HORIZONTAL_PANEL_TYPE);
	nodeTypes.put("vertical-panel", UIBuilder.VERTICAL_PANEL_TYPE);
	nodeTypes.put("group-panel", UIBuilder.GROUP_PANEL_TYPE);
	nodeTypes.put("title-panel", UIBuilder.TITLE_PANEL_TYPE);
	nodeTypes.put("collapse-panel", UIBuilder.COLLAPSE_PANEL_TYPE);
	nodeTypes.put("split-panel", UIBuilder.SPLIT_PANEL_TYPE);
	nodeTypes.put("tab-panel", UIBuilder.TAB_PANEL_TYPE);
	nodeTypes.put("tab-item", UIBuilder.TAB_ITEM_TYPE);
	
	nodeTypes.put("label", UIBuilder.LABEL_TYPE);
	nodeTypes.put("separator", UIBuilder.SEPARATOR_TYPE);
	nodeTypes.put("button", UIBuilder.BUTTON_TYPE);
	nodeTypes.put("toggle-button", UIBuilder.TOGGLE_BUTTON_TYPE);
	nodeTypes.put("link", UIBuilder.LINK_TYPE);
	nodeTypes.put("image-box", UIBuilder.IMAGE_BOX_TYPE);
	
	// Fields
	nodeTypes.put("text-box", UIBuilder.TEXT_BOX_TYPE);
	nodeTypes.put("text-area", UIBuilder.TEXT_AREA_TYPE);
	nodeTypes.put("text-field", UIBuilder.TEXT_FIELD_TYPE);
	nodeTypes.put("password-field", UIBuilder.PASSWORD_FIELD_TYPE);
	
	nodeTypes.put("number-field", UIBuilder.NUMBER_FIELD_TYPE);
	nodeTypes.put("integer-field", UIBuilder.INTEGER_FIELD_TYPE);
	nodeTypes.put("currency-field", UIBuilder.CURRENCY_FIELD_TYPE);
	nodeTypes.put("percent-field", UIBuilder.PERCENT_FIELD_TYPE);
	nodeTypes.put("quantity-field", UIBuilder.QUANTITY_FIELD_TYPE);
	nodeTypes.put("spinner-field", UIBuilder.SPINNER_FIELD_TYPE);
	
	nodeTypes.put("slider", UIBuilder.SLIDER_TYPE);
	
	nodeTypes.put("date-field", UIBuilder.DATE_FIELD_TYPE);
	nodeTypes.put("time-field", UIBuilder.TIME_FIELD_TYPE);
	nodeTypes.put("date-time-field", UIBuilder.DATE_TIME_FIELD_TYPE);
	
	nodeTypes.put("check-box", UIBuilder.CHECK_BOX_TYPE);
	nodeTypes.put("radio-button", UIBuilder.RADIO_BUTTON_TYPE);
	nodeTypes.put("radio-group", UIBuilder.RADIO_GROUP_TYPE);
	nodeTypes.put("combo-box", UIBuilder.COMBO_BOX_TYPE);
	nodeTypes.put("lookup-field", UIBuilder.LOOKUP_FIELD_TYPE);
	nodeTypes.put("list-box", UIBuilder.LIST_BOX_TYPE);
	
	nodeTypes.put("table", UIBuilder.TABLE_TYPE);
	nodeTypes.put("tree", UIBuilder.TREE_TYPE);

	nodeTypes.put("window", UIBuilder.WINDOW_TYPE);
	nodeTypes.put("frame", UIBuilder.FRAME_TYPE);
	nodeTypes.put("dialog", UIBuilder.DIALOG_TYPE);
	
	nodeTypes.put("form", UIBuilder.FORM_TYPE);
	nodeTypes.put("list-form", UIBuilder.LIST_FORM_TYPE);
	nodeTypes.put("edit-form", UIBuilder.EDIT_FORM_TYPE);
	
	nodeTypes.put("menu-bar", UIBuilder.MENU_BAR_TYPE);
	nodeTypes.put("menu", UIBuilder.MENU_TYPE);
	nodeTypes.put("menu-item", UIBuilder.MENU_ITEM_TYPE);
	nodeTypes.put("menu-separator", UIBuilder.MENU_SEPARATOR_TYPE);

	nodeTypes.put("tool-bar", UIBuilder.TOOL_BAR_TYPE);
	nodeTypes.put("tool-item", UIBuilder.TOOL_ITEM_TYPE);
	nodeTypes.put("tool-separator", UIBuilder.TOOL_SEPARATOR_TYPE);

	
	///////////////////////////////////////////////////////////////////////
	// Alternative Tags
	///////////////////////////////////////////////////////////////////////

	alternativeTags.put("h-panel", "horizontal-panel");
	alternativeTags.put("v-panel", "vertical-panel");

	alternativeTags.put("menubar", "menu-bar");
	alternativeTags.put("menuitem", "menu-item");
	alternativeTags.put("menuitems", "menu-items");
	alternativeTags.put("toolbars", "tool-bars");
	alternativeTags.put("toolbar", "tool-bar");
	alternativeTags.put("toolitem", "tool-item");
	alternativeTags.put("toolitems", "tool-items");
	
	alternativeTags.put("checkbox", "check-box");
	alternativeTags.put("textbox", "text-box");
	alternativeTags.put("combobox", "combo-box");
	alternativeTags.put("comboedit", "combo-edit");
	alternativeTags.put("listbox", "list-box");

	alternativeTags.put("datetime-field", "date-time-field");
	
	///////////////////////////////////////////////////////////////////////
	// Alternative Attributes
	///////////////////////////////////////////////////////////////////////

	alternativeAttributes.put("h-align", "horizontal-align");
	alternativeAttributes.put("v-align", "vertical-align");
	alternativeAttributes.put("h-flex", "horizontal-flex");
	alternativeAttributes.put("v-flex", "vertical-flex");
	alternativeAttributes.put("align", "horizontal-align");
	alternativeAttributes.put("flex", "horizontal-flex");

	
	
	///////////////////////////////////////////////////////////////////////
	// XML Attributes to Java Properties
	///////////////////////////////////////////////////////////////////////

	xmlToJavaMap.put("tooltip", "toolTip");
	
	xmlToJavaMap.put("halign", "horizontalAlign");
	xmlToJavaMap.put("valign", "verticalAlign");
	xmlToJavaMap.put("hflex", "horizontalFlex");
	xmlToJavaMap.put("vflex", "verticalFlex");
	
	xmlToJavaMap.put("align", "horizontalAlign");
	xmlToJavaMap.put("flex", "horizontalFlex");

	// Attributes or Tags like Attributes: '-' to camel case
	//xmlToJavaMap.put("layout-data", "layoutData");

	
    }
    
  
    ////

    public UIObject readObject(String fileName) throws IOException {
	Document doc = readDocument(fileName);
	return readObject(fileName, doc);
    }

    public UIObject readObject(File file) throws IOException {
	Document doc = readDocument(file);
	return readObject(file.getName(), doc);
    }

    public UIObject readObject(String fileName, InputStream is) throws IOException {
	Document doc = readDocument(is);
	return readObject(fileName, doc);
    }
    
    public UIObject readObject(InputStream is) throws IOException {
	return readObject(null, is);
    }
    
    ////
    
    
    public IData readData(String fileName) throws IOException {
	Document doc = readDocument(fileName);
	return readData(fileName, doc);
    }

    public IData readData(File file) throws IOException {
	Document doc = readDocument(file);
	return readData(file.getName(), doc);
    }

    public IData readData(String fileName, InputStream is) throws IOException {
	Document doc = readDocument(is);
	return readData(fileName, doc);
    }
    
    public IData readData(InputStream is) throws IOException {
	return readData(null, is);
    }

    ////
    
    
    protected Document readDocument(String fileName) throws IOException {
	return readDocument(new File(fileName));
    }
    
    protected Document readDocument(File file) throws IOException {
	return readDocument(new FileInputStream(file));
    }
    
    protected Document readDocument(InputStream is) throws IOException {
	SAXBuilder builder = new SAXBuilder();
	//builder.setValidation(false);
	try {
	    return builder.build(is);
	} catch (JDOMException ex) {
	    throw new IOException(ex);
	}
    }
        
    
    /**
     * General read method
     * @param fileName
     * @param doc
     * @return
     */
    protected UIObject readObject(String fileName, Document doc) {
	IData data = readData(fileName, doc);
	return new UIBuilder().buildObject(data);
    }
    
    protected IData readData(String fileName, Document doc) {
	try {
	    IData data = doReadData(doc);
	    //if (fileLog) {
		//System.out.println("Loaded UIObject" + (fileName != null ? (" from file '" + fileName + "'") : ""));
	    //}
	    return data;
	} catch (Exception ex) {
	    throw new RuntimeException("Error load UIObject" + (fileName != null ? (" from file '" + fileName + "'") : "") + ": " + ex.getMessage());
	}
    }
    
    protected IData doReadData(Document doc) {
	Element root = doc.getRootElement();
	String name = root.getName();
	
	if (!XML_UI.equals(name)) {
	    return null;
	}
	
	List children = root.getChildren();
	int count = children.size();
	if (count == 0) {
	    return null;
	}

	// Get first (single) element
	Element element = (Element) children.get(0);
	String elementName = element.getName();
	if (!isRootElement(elementName)) {
	    throw new RuntimeException("'" + elementName + "' is not root");
	}
	
	IData data = readData(element);  
	return data;
    }
    
    protected IData readData(Element element) {
	IData data = createData();
	if (element == null) {
	    return data;
	}
	
	readElement(data, element);
	
	return data;
	
    }
    
    protected void readElement(IData data, Element element) {
	String tagName = element.getName(); // Get element tag name
	tagName = getTagName(tagName); // Convert from alternative to standard tag name
	String type = toTypeName(tagName); // Convert tag name to type
	data.set(UIBuilder.SYS_PROPERTY_TYPE, type);
	
	// Read attributes
	readElementAttributes(data, element);
	
	// Read children
	readElementChildren(data, element);
	
    }
    
    protected void readElementAttributes(IData data, Element element) {
	if (element == null) {
	    return;
	}
	List attributes = element.getAttributes();
	if (attributes == null || attributes.isEmpty()) {
	    return;
	}
	for (int i = 0; i < attributes.size(); i++) {
	    Attribute attribute = (Attribute) attributes.get(i);
	    String attributeName = getAttributeName(attribute.getName());
	    String property = getPropertyNameByAttribute(attributeName);
	    String value = attribute.getValue();
	    data.set(property, value);
	}
    }

    protected void readElementChildren(IData data, Element element) {
	if (element == null) {
	    return;
	}
	List children = element.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	
	List<IData> childList = (List<IData>) data.get(UIBuilder.SYS_PROPERTY_CHILDREN);
	
	for (int i = 0; i < children.size(); i++) {
	    Element childElement = (Element) children.get(i);

	    String tagName = getTagName(childElement.getName());
	    
	    String property = getPropertyNameByTag(tagName);
	    String type = toTypeName(tagName);
	    String value = isNode(tagName) ? null : childElement.getText(); //childElement.getValue(); // FIX 2013-12-27
	    
	    if (childList == null) {
		childList = new ArrayList<IData>();
		data.set(UIBuilder.SYS_PROPERTY_CHILDREN, childList);
	    }
	    
	    IData nodeData = createData();
	    
	    // Duplicate $name and $type property
	    nodeData.set(UIBuilder.SYS_PROPERTY_NAME, property);
	    nodeData.set(UIBuilder.SYS_PROPERTY_TYPE, type);
	    nodeData.set(UIBuilder.SYS_PROPERTY_PARENT, data);
	    
	    
	    childList.add(nodeData);
	    
	    if (value != null) {
		value = value.trim();
	    }
	    if (value != null && !value.isEmpty()) {
		nodeData.set(UIBuilder.SYS_PROPERTY_VALUE, value);
	    }
	    
	    readElement(nodeData, childElement);
	    
	}
    }

    /**
     * Convert name of XML attribute to name of Java property
     * @param attributeName
     * @return
     */
    protected String toPropertyName(String attributeName) {
	String propertyName = xmlToJavaMap.get(attributeName);
	if (propertyName!= null) {
	    return propertyName;
	}
	return UIStorageUtils.toCamelCase(attributeName, DELIM);
    }
    
    protected String getPropertyNameByTag(String tagName) {
	
	// Tag Node is not Java property
	//if (isNode(tagName)) {
	//    return tagName;
	//}
	
	return toPropertyName(tagName);
    }
    
    protected String getPropertyNameByAttribute(String attributeName) {
	return toPropertyName(attributeName);
    }


    /**
     * Convert name of tag to type of widget
     * @param tagName
     * @return
     */
    protected String toTypeName(String tagName) {
	return nodeTypes.get(tagName);
    }
    
    protected boolean isNode(String tagName) {
	if (tagName == null || nodeTypes == null) {
	    return false;
	}
	return nodeTypes.containsKey(tagName);
    }

    protected IData createData() {
	return UIBuilderHelper.createData();
    }
    
    protected String getTagName(String tagName) {
	if (tagName == null) {
	    return null;
	}
	String alternativeTagName = alternativeTags.get(tagName);
	return alternativeTagName == null ? tagName : alternativeTagName;
    }
    
    protected String getAttributeName(String attributeName) {
	if (attributeName == null) {
	    return null;
	}
	String alternativeTagName = alternativeAttributes.get(attributeName);
	return alternativeTagName == null ? attributeName : alternativeTagName;
    }
    
    protected boolean isRootElement(String name) {
	if (name == null) {
	    return false;
	}
	if (rootTags.contains(name)) {
	    return true;
	}
	// Check <name>-<root-tag>
	// For example title-panel, collapse-panel, vertical-panel, table-view, list-form, edit-form 
	for (String t: rootTags ) {
	    if (name.endsWith("-" + t) ) {
		return true;
	    }
	}
	return false;
    }
}
