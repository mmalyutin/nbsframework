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
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.configurer.InterfaceConfigurer;
import org.plazmaforge.framework.config.object.IInterfaceConfig;
import org.plazmaforge.framework.config.object.InterfaceConfig;

/**
 *  
 * @author ohapon
 * 
 */

public class XMLInterfaceConfigurer extends XMLObjectConfigurer<IInterfaceConfig> implements InterfaceConfigurer {

    public static String NAME = "XMLInterfaceConfigurer";

    public static final String XML_ROOT = "interface-config";
    
    public static final String DEFAULT_CONFIG_FILE = XML_ROOT + ".xml";

    public static final String DEFAULT_CONFIG_PROPERTIES = XML_ROOT;
    
    public XMLInterfaceConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(createUIConfigFileName(DEFAULT_CONFIG_FILE));
	setPropertiesBaseName(createUIConfigFileName(DEFAULT_CONFIG_PROPERTIES));
    }
    
    /**
     * Load UIs from file
     */
    protected void doLoadObjects() throws Exception {

	Element rootElement = getRootElement(getConfigInputStream());
	List list = getChildren(rootElement, XML_INTERFACE);
	if (list == null) {
	    return;
	}
	Iterator i = list.iterator();
	InterfaceConfig ui = null;
	Element uiElement = null;
	while (i.hasNext()) {
	    uiElement = ((Element) i.next());

	    String id = getAttributeId(uiElement);
	    String name = uiElement.getAttributeValue(XML_NAME);
	    ui = new InterfaceConfig();
	    ui.setId(id);
	    ui.setName(name);

	    String enabled = uiElement.getAttributeValue(XML_IS_ENABLED);
	    if (enabled != null) {
		ui.setEnabled(getBoolean(enabled));
	    }

	    if (store != null) {
		loadNLSCaption(store, ui);
		loadNLSDescription(store, ui);
	    }

	    initConfigObjectName(ui);
	    addObject(ui);
	}
    }

    /**
     * Store UIs to file
     */
    protected void doStoreObjects() throws Exception {
	Element root = createRootElement(XML_ROOT);
	Document doc = new Document(root);
	List<IInterfaceConfig> uiList = getObjects();
	if (uiList == null) {
	    storeWithNLS(doc, store);
	    return;
	}
	Element uiElement = null;
	List<Element> children = new ArrayList<Element>();
	for (IInterfaceConfig ui : uiList) {
	    uiElement = new Element(XML_INTERFACE);
	    children.add(uiElement);

	    setAttributeId(uiElement, ui.getId());
	    setAttribute(uiElement, XML_NAME, ui.getName());
	    setEnabledAttribute(uiElement, XML_IS_ENABLED, ui.isEnabled());
	    
	    if (store != null) {
		storeNLSCaption(store, ui);
		storeNLSDescription(store, ui);
	    }
	    
	}
	root.setContent(children);
	storeWithNLS(doc, store);
    }
   
}
