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
package org.plazmaforge.framework.config.configurer.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Document;
import org.jdom.Element;
import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.configurer.ToolBarConfigurer;
import org.plazmaforge.framework.config.object.ToolItemConfig;
import org.plazmaforge.framework.config.object.IToolBarConfig;
import org.plazmaforge.framework.config.object.IToolItemConfig;
import org.plazmaforge.framework.config.object.ToolBarConfig;
import org.plazmaforge.framework.util.IPropertiesStore;

/**
 * 
 * @author ohapon
 *
 */
public class XMLToolBarConfigurer extends XMLObjectConfigurer<IToolBarConfig> implements ToolBarConfigurer {

    public static String NAME = "XMLToolBarConfigurer";

    public static final String XML_ROOT = "tool-bar-config";
    
    public static final String DEFAULT_CONFIG_FILE = XML_ROOT + ".xml";

    public static final String XML_TOOL_BAR_CONFIG = XML_ROOT;
    
    public static final String DEFAULT_CONFIG_PROPERTIES = XML_ROOT;

    public static final String XML_TOOLBAR = "tool-bar";

    public static final String XML_TOOLITEM = "tool-item";

    public static final String XML_ACTION = "action";

    public XMLToolBarConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(createUIConfigFileName(DEFAULT_CONFIG_FILE));
	setPropertiesBaseName(createUIConfigFileName(DEFAULT_CONFIG_PROPERTIES));
    }

    /**
     * Load tool bars from file
     */
    protected void doLoadObjects() throws Exception {
	
	Element rootElement = getRootElement(getConfigInputStream());
	loadUserInterfaces(rootElement, store);
    }

    /**
     * Store tool bars to file
     */
    protected void doStoreObjects() throws Exception {
	Element root = createRootElement(XML_ROOT);
	Document doc = new Document(root);
	List<IToolBarConfig> toolBars = getObjects();
	if (toolBars == null) {
	    storeWithNLS(doc, store);
	    return;
	}
	Map<String, List<IToolBarConfig>> uis = createUserInterfaces(toolBars);
	if (uis.isEmpty()) {
	    storeToolBars(root, toolBars, store);
	    storeWithNLS(doc, store);
	    return;
	}
	Element uiElement = null;
	List<Element> children = new ArrayList<Element>();
	Set<String> keys = uis.keySet();
	for (String u : keys) {
	    uiElement = new Element(XML_INTERFACE);
	    children.add(uiElement);
	    setAttribute(uiElement, XML_NAME, u);
	    toolBars = uis.get(u);
	    storeToolBars(uiElement, toolBars, store);
	}
	root.setContent(children);
	storeWithNLS(doc, store);
    }

    protected Map<String, List<IToolBarConfig>> createUserInterfaces(List<IToolBarConfig> toolBars) {
	HashMap<String, List<IToolBarConfig>> uis = new LinkedHashMap<String, List<IToolBarConfig>>();
	if (toolBars.isEmpty()) {
	    return uis;
	}
	String category = null;
	for (IToolBarConfig t: toolBars) {
	    category = t.getCategory();
	    if (isEmpty(category)) {
		// We lost ToolBar without category !!!
		continue;
	    }
	    List<IToolBarConfig> list = uis.get(category);
	    if (list == null) {
		list = new ArrayList<IToolBarConfig>();
		uis.put(category, list);
	    }
	    list.add(t);
	}
	return uis;
    }
    
    protected void storeToolBars(Element parentElement, List<IToolBarConfig> toolBars, IPropertiesStore store) {
	if (toolBars == null || toolBars.isEmpty()) {
	    return;
	}
	Element toolBarElement = null;
	List<Element> children = new ArrayList<Element>();
	for (IToolBarConfig toolBar : toolBars) {
	    
	    toolBarElement = new Element(XML_TOOLBAR);

	    children.add(toolBarElement);

	    setAttributeId(toolBarElement, toolBar.getId());
	    setAttribute(toolBarElement, XML_NAME, toolBar.getName());
	    setAttribute(toolBarElement, XML_TYPE, toolBar.getType());
	    setAttribute(toolBarElement, XML_UI_TYPE, toolBar.getUiType());
	    
	    storeButtons(toolBar, toolBarElement);
	    
	    // NLS
	    if (store != null) {
		storeNLSCaption(store, toolBar);
		storeNLSDescription(store, toolBar);
	    }
	}
	parentElement.setContent(children);
	
    }
    
    protected void storeButtons(IToolBarConfig toolBar, Element toolBarElement)  {
	List<IToolItemConfig> buttons = toolBar.getChildren();
	if (buttons == null || buttons.size() == 0) {
	    return;
	}
	for (IToolItemConfig button : buttons) {
	    Element buttonElement = new Element(XML_TOOLITEM);
	    setAttribute(buttonElement, XML_ACTION, button.getAction());
	    toolBarElement.getChildren().add(buttonElement);
	}
    } 
    
    ////

    protected void loadUserInterfaces(Element rootElement, IPropertiesStore store) {
	List uiList = getChildren(rootElement, XML_INTERFACE);
	if (isEmpty(uiList)) {
	    loadToolBars(rootElement, null, store);
	    return;
	}
	Iterator i = uiList.iterator();
	while (i.hasNext()) {
	    Element uiElement = (Element) i.next();
	    String name = uiElement.getAttributeValue(XML_NAME);
	    loadToolBars(uiElement, name, store);
	}
    }
    
    /**
     * Load ToolBars by UI (category)
     * @param rootElement
     * @param category
     */
    protected void loadToolBars(Element rootElement, String category, IPropertiesStore store) {
	List barList = getChildren(rootElement, XML_TOOLBAR);
	if (barList == null) {
	    return;
	}
	    
	Iterator i = barList.iterator();
	while (i.hasNext()) {
	    IToolBarConfig toolBar = new ToolBarConfig();
	    Element toolBarElement = (Element) i.next();

	    String id = getAttributeId(toolBarElement);
	    String name = toolBarElement.getAttributeValue(XML_NAME);
	    String type = toolBarElement.getAttributeValue(XML_TYPE);
	    String uiType = toolBarElement.getAttributeValue(XML_UI_TYPE);

	    toolBar.setId(id);
	    toolBar.setName(name);
	    toolBar.setType(type);
	    toolBar.setUiType(uiType);
	    toolBar.setCategory(category);

	    // NLS
	    if (store != null) {
		loadNLSCaption(store, toolBar);
		loadNLSDescription(store, toolBar);
	    }
	    
	    loadButtons(toolBar, toolBarElement);
	    addObject(toolBar);
	}
    }

    /**
     * Load buttons of ToolBar
     * @param toolBar
     * @param toolBarElement
     */
    protected void loadButtons(IToolBarConfig toolBar, Element toolBarElement) {
	List list = getChildren(toolBarElement, XML_TOOLITEM);
	if (list == null) {
	    return;
	}
	    
	Iterator i = list.iterator();
	Element buttonElement = null;
	IToolItemConfig button = null;
	while (i.hasNext()) {
	    buttonElement = ((Element) i.next());
	    button = new ToolItemConfig();
	    button.setAction(buttonElement.getAttributeValue(XML_ACTION));
	    toolBar.addChild(button);
	}
    }

}
