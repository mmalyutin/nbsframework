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
import org.plazmaforge.framework.config.configurer.AcceptorConfigurer;
import org.plazmaforge.framework.config.object.IAcceptorConfig;
import org.plazmaforge.framework.config.object.AcceptorConfig;

/**
 * 
 * @author ohapon
 *
 */
public class XMLAcceptorConfigurer extends XMLObjectConfigurer<IAcceptorConfig> implements AcceptorConfigurer {

    public static String NAME = "XMLAcceptorConfigurer";

    public static final String XML_ROOT = "acceptor-config";
    
    public static final String DEFAULT_CONFIG_FILE = XML_ROOT + ".xml";
    
    public static final String DEFAULT_CONFIG_PROPERTIES = XML_ROOT;

    public static final String XML_ACCEPTOR = "acceptor";

    public XMLAcceptorConfigurer(AppConfiguration configuration) {
	super(configuration);
	setConfigFileName(createUIConfigFileName(DEFAULT_CONFIG_FILE));
	setPropertiesBaseName(createUIConfigFileName(DEFAULT_CONFIG_PROPERTIES));
    }


    /**
     * Load report acceptors from file
     */
    protected void doLoadObjects() throws Exception {

	Element rootElement = getRootElement(getConfigInputStream());
	List list = getChildren(rootElement, XML_ACCEPTOR);
	if (list == null) {
	    return;
	}
	Iterator i = list.iterator();
	IAcceptorConfig acceptor = null;
	Element acceptorElement = null;
	while (i.hasNext()) {
	    acceptorElement = ((Element) i.next());

	    String id = getAttributeId(acceptorElement);
	    String name = acceptorElement.getAttributeValue(XML_NAME);
	    String klass = acceptorElement.getAttributeValue(XML_CLASS);

	    acceptor = new AcceptorConfig();
	    acceptor.setId(id);
	    acceptor.setName(name);
	    acceptor.setClassName(klass);

	    if (store != null) {
		loadNLSCaption(store, acceptor);
		loadNLSDescription(store, acceptor);
	    }
	    
	    addObject(acceptor);
	}
    }
    
    /**
     * Store report acceptors to file
     */
    protected void doStoreObjects() throws Exception {
	Element root = createRootElement(XML_ROOT);
	Document doc = new Document(root);
	List<IAcceptorConfig> forms = getObjects();
	if (forms == null) {
	    storeWithNLS(doc, store);
	    return;
	}
	Element formElement = null;
	List<Element> children = new ArrayList<Element>();
	for (IAcceptorConfig form : forms) {
	    formElement = new Element(XML_ACCEPTOR);

	    children.add(formElement);

	    setAttributeId(formElement, form.getId());
	    setAttribute(formElement, XML_NAME, form.getName());
	    setAttribute(formElement, XML_CLASS, form.getClassName());

	    if (store != null) {
		storeNLSCaption(store, form);
		storeNLSDescription(store, form);
	    }
	}
	root.setContent(children);
	storeWithNLS(doc, store);
    }


}
