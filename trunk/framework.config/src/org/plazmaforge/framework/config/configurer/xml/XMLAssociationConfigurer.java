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
import org.plazmaforge.framework.config.configurer.AssociationConfigurer;
import org.plazmaforge.framework.config.object.AssociationConfig;
import org.plazmaforge.framework.config.object.IAssociationConfig;

/**
 * 
 * @author ohapon
 *
 */
public class XMLAssociationConfigurer extends XMLObjectConfigurer<IAssociationConfig> implements AssociationConfigurer {

    
    public static String NAME = "XMLAssociationConfigurer";

    public static final String XML_ROOT = "association-config";
    
    public static final String DEFAULT_CONFIG_FILE = XML_ROOT + ".xml";
    
    public static final String DEFAULT_CONFIG_PROPERTIES = XML_ROOT;

    public static final String XML_ASSOCIATION = "association";

    
    
    public XMLAssociationConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(DEFAULT_CONFIG_FILE);
	setPropertiesBaseName(DEFAULT_CONFIG_PROPERTIES);
    }



    /**
     * Load associations from file
     */
    protected void doLoadObjects() throws Exception {

	Element rootElement = getRootElement(getConfigInputStream());
	List list = getChildren(rootElement, XML_ASSOCIATION);
	if (list == null) {
	    return;
	}
	Iterator i = list.iterator();
	IAssociationConfig association = null;
	Element associationElement = null;
	while (i.hasNext()) {
	    associationElement = ((Element) i.next());

	    String id = getAttributeId(associationElement);
	    String name = associationElement.getAttributeValue(XML_NAME);
	    String klass = associationElement.getAttributeValue(XML_CLASS);

	    association = new AssociationConfig();
	    association.setId(id);
	    association.setName(name);
	    association.setClassName(klass);

	    if (store != null) {
		loadNLSCaption(store, association);
		loadNLSDescription(store, association);
	    }
	    
	    addObject(association);
	}
    }
    
    protected void doStoreObjects() throws Exception {
	Element root = createRootElement(XML_ROOT);
	Document doc = new Document(root);
	List<IAssociationConfig> associations = getObjects();
	if (associations == null) {
	    storeWithNLS(doc, store);
	    return;
	}
	Element associationElement = null;
	List<Element> children = new ArrayList<Element>();
	for (IAssociationConfig association : associations) {
	    associationElement = new Element(XML_ASSOCIATION);

	    children.add(associationElement);

	    setAttributeId(associationElement, association.getId());
	    setAttribute(associationElement, XML_NAME, association.getName());
	    setAttribute(associationElement, XML_CLASS, association.getClassName());
	    
	    if (store != null) {
		storeNLSCaption(store, association);
		storeNLSDescription(store, association);
	    }

	}
	root.setContent(children);
	storeWithNLS(doc, store);
    }


}
