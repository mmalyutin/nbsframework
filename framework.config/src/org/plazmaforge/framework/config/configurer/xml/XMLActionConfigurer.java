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
import org.plazmaforge.framework.config.configurer.ActionConfigurer;
import org.plazmaforge.framework.config.object.ActionConfig;
import org.plazmaforge.framework.config.object.IActionConfig;


/**
 * The XML Action Configurer loads the actions from a XML file and configures they.
 * 
 * @author ohapon
 *
 */
public class XMLActionConfigurer extends XMLObjectConfigurer<IActionConfig> implements ActionConfigurer {

    public static String NAME = "XMLActionConfigurer";

    public static final String XML_ROOT = "action-config";
    
    public static final String DEFAULT_CONFIG_FILE = XML_ROOT + ".xml";

    public static final String DEFAULT_CONFIG_PROPERTIES = XML_ROOT;

    public static final String XML_ACTION = "action";

    public static final String XML_COMMAND = "command";
    
    public static final String XML_CONTROL = "control";
    

    public XMLActionConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(createUIConfigFileName(DEFAULT_CONFIG_FILE));
	setPropertiesBaseName(createUIConfigFileName(DEFAULT_CONFIG_PROPERTIES));
    }

    
    
    /**
     * Load actions from file
     * @throws Exception
     */
    protected void doLoadObjects() throws Exception {
	Element rootElement = getRootElement(getConfigInputStream());
	List list = getChildren(rootElement, XML_ACTION);
	if (list == null) {
	    return;
	}
	Iterator i = list.iterator();
	ActionConfig action = null;
	Element actionElement = null;
	while (i.hasNext()) {
	    actionElement = ((Element) i.next());

	    String id = getAttributeId(actionElement);
	    String name = actionElement.getAttributeValue(XML_NAME);
	    String icon = actionElement.getAttributeValue(XML_ICON);
	    String klass = actionElement.getAttributeValue(XML_CLASS);
	    String command = actionElement.getAttributeValue(XML_COMMAND);

	    String type = actionElement.getAttributeValue(XML_TYPE);
	    String uiType = actionElement.getAttributeValue(XML_UI_TYPE);

	    action = new ActionConfig();
	    action.setId(id);

	    action.setName(name);
	    action.setIcon(icon);
	    action.setClassName(klass);
	    action.setCommand(command);

	    action.setType(type);
	    action.setUiType(uiType);

	    // Load command from <command> element
	    loadCommand(action, actionElement.getChild(XML_COMMAND));

	    if (store != null) {
		loadNLSCaption(store, action);
		loadNLSDescription(store, action);
	    }
	    
	    // Init empty action name
	    initConfigObjectName(action);

	    addObject(action);
	}

    }
    
    protected void doStoreObjects() throws Exception {
	
	Element root = createRootElement(XML_ROOT);
	Document doc = new Document(root);
	List<IActionConfig> actions = getObjects();
	if (actions == null) {
	    storeWithNLS(doc, store);
	    return;
	}
	Element actionElement = null;
	List<Element> children = new ArrayList<Element>();
	for (IActionConfig action : actions) {
	    actionElement = new Element(XML_ACTION);

	    children.add(actionElement);

	    setAttributeId(actionElement, action.getId());
	    setAttribute(actionElement, XML_NAME, action.getName());
	    setAttribute(actionElement, XML_ICON, action.getIcon());
	    setAttribute(actionElement, XML_CLASS, action.getClassName());
	    setAttribute(actionElement, XML_COMMAND, action.getCommand());
	    
	    setAttribute(actionElement, XML_TYPE, action.getType());
	    setAttribute(actionElement, XML_UI_TYPE, action.getUiType());
	    
	    if (store != null) {
		storeNLSCaption(store, action);
		storeNLSDescription(store, action);
	    }

	}
	root.setContent(children);
	storeWithNLS(doc, store);
    }


    /**
     * Get action by id
     */
    public IActionConfig getActionById(String id) {
	return getObjectById(id);
    }


    /**
     * Load command of the action
     * @param actionConfig
     * @param commandElement
     */
    protected void loadCommand(IActionConfig actionConfig, Element commandElement) {
	if (commandElement == null) {
	    return;
	}
	String command = commandElement.getText();
	if (!isEmpty(command)) {
	    actionConfig.setCommand(command);
	}
    }

    
}
