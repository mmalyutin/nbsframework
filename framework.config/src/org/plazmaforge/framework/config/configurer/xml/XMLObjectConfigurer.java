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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.plazmaforge.framework.config.AppConfiguration;
import org.plazmaforge.framework.config.configurer.AbstractObjectConfigurer;
import org.plazmaforge.framework.config.configurer.ObjectConfigurer;
import org.plazmaforge.framework.config.object.IObjectConfig;
import org.plazmaforge.framework.core.exception.ApplicationException;
import org.plazmaforge.framework.util.IPropertiesStore;


/**
 * The XML Object Configurer loads the config objects from a XML file and configures they.
 * 
 * @author ohapon
 *
 */
public abstract class XMLObjectConfigurer<T extends IObjectConfig> extends AbstractObjectConfigurer<T> implements ObjectConfigurer<T> {

    
    public static final String PROVIDER = "Plazma Forge";
    
    public static final String VERSION = "1.0";
    
    
    public static final String XML_PROVIDER = "provider";
    
    public static final String XML_VERSION = "version";
    
    
    public static final String XML_ID = "id";

    public static final String XML_CODE = "code";

    public static final String XML_NAME = "name";
    
    public static final String XML_CAPTION = "caption";
    
    public static final String XML_DESCRIPTION = "description";
    
    public static final String XML_REF = "ref";

    public static final String XML_TYPE = "type";
    
    public static final String XML_CLASS = "class";

    public static final String XML_UI_TYPE = "ui-type";

    public static final String XML_FILE = "file";

    public static final String XML_VALUE = "value";

    public static final String XML_PROPERTIES = "properties";
    
    public static final String XML_PROPERTY = "property";

    public static final String XML_ICON = "icon";
    
    public static final String XML_INTERFACE = "interface";
    
    public static final String XML_IS_SYSTEM = "system";
    
    public static final String XML_IS_CUSTOM = "custom";
    
    public static final String XML_IS_ENABLED = "enabled";

    

    protected IPropertiesStore store;
    
    protected boolean autoGenerateId = false; // TODO
    
    protected boolean needStoreId = false;
        

    
    public XMLObjectConfigurer(AppConfiguration configuration) {
	super(configuration);
    }

    protected boolean isSupportNLSStore() {
	return true;
    }

    public void loadObjects() throws ApplicationException {
	try {
	    setProcessing(true);
	    doLoadObjectsProcess();
	} catch (Exception ex) {
	    throw new ApplicationException(ex);
	} finally {
	    setProcessing(false);
	}
    }

    
    public void storeObjects() throws ApplicationException {
	try {
	    setProcessing(true);
	    doStoreObjectsProcess();
	} catch (Exception ex) {
	    throw new ApplicationException(ex);
	} finally {
	    setProcessing(false);
	}
    }

    protected void doLoadObjectsProcess() throws Exception {
	initStore(); // Initialize NLS Store
	if (!existsConfigFile()) {
	    doLoadVirtualObjects();
	    return;
	}
	doLoadObjects();
    }

    protected void doStoreObjectsProcess() throws Exception {
	doStoreObjects();
    }

    protected void doLoadVirtualObjects() throws Exception {}
    
    protected abstract void doLoadObjects() throws Exception;

    protected abstract void doStoreObjects() throws Exception;


    protected void initStore() throws IOException {
	if (!isSupportNLSStore()){
	    return;
	}
	store = getConfigPropertiesStore();
    }
    
    protected Element getRootElement(InputStream is) throws IOException, JDOMException {
	SAXBuilder builder = new SAXBuilder();
	org.jdom.Document doc = builder.build(is);
	return doc.getRootElement();

    }
    
    ////
    
    /*
    protected void store(Document doc, OutputStream out) throws IOException {
        store(doc, new OutputStreamWriter(out));
    }
    */

    protected void store(Document doc) throws IOException {
        store(doc, getAbsoluteConfigFileName());
    }

    protected void store(Document doc, String fileName) throws IOException {
	if (fileName == null) {
	    throw new IllegalArgumentException("Configurer file name is empty. Configurer=[" + getClass().getName() + "]");
	}
        store(doc, new File(fileName));
    }

    protected void store(Document doc, File xmlDocument) throws IOException {
        store(doc, new OutputStreamWriter(new FileOutputStream(xmlDocument), "UTF-8") /*new FileWriter(xmlDocument)*/);
    }
   
    protected void store(Document doc, Writer writer) throws IOException {

        // Output document to supplied filename
        XMLOutputter outputter = createXMLOutputter("UTF-8"); // new XMLOutputter("  ", true);
        outputter.output(doc, writer);
        writer.flush();
    }
    

    protected void storeWithNLS(Document doc, IPropertiesStore store) throws IOException {
	store(doc);
	storeNLS(store);
    }
    
    protected String getAttributeId(Element element) {
	String id = element.getAttributeValue(XML_ID);
	if (autoGenerateId && isEmpty(id)) {
	    return generateId();
	}
	return id;
    }
    
    protected void setAttributeId(Element element, String attributeValue) {
	if (element == null || isEmpty(attributeValue)) {
	    return;
	}
	if (!needStoreId) {
	    return;
	}
	element.setAttribute(XML_ID, attributeValue);
    }
    

    protected void setAttribute(Element element, String attributeName, String attributeValue) {
	if (element == null || isEmpty(attributeName) || isEmpty(attributeValue)) {
	    return;
	}
	element.setAttribute(attributeName, attributeValue);
    }

    protected void setBooleanAttributeNullIfFalse(Element element, String attributeName, boolean attributeValue) {
	setAttribute(element, attributeName, getBooleanStringNullIfFalse(attributeValue));
    }

    protected void setBooleanAttributeNullIfTrue(Element element, String attributeName, boolean attributeValue) {
	setAttribute(element, attributeName, getBooleanStringNullIfTrue(attributeValue));
    }

    protected void setBooleanAttribute(Element element, String attributeName, boolean attributeValue) {
	setBooleanAttributeNullIfFalse(element, attributeName, attributeValue);
    }

    protected void setEnabledAttribute(Element element, String attributeName, boolean attributeValue) {
	setBooleanAttributeNullIfTrue(element, attributeName, attributeValue);
    }
  
    protected void loadNLSCaption(IPropertiesStore store, IObjectConfig objectConfig) {
	loadNLSCaption(store, objectConfig, XML_CAPTION);
    }
    
    protected void loadNLSDescription(IPropertiesStore store, IObjectConfig objectConfig) {
	loadNLSDescription(store, objectConfig, XML_DESCRIPTION);
    }

    protected void storeNLSCaption(IPropertiesStore store, IObjectConfig objectConfig) {
	storeNLSName(store, objectConfig, XML_CAPTION);
    }
    
    protected void storeNLSDescription(IPropertiesStore store, IObjectConfig objectConfig) {
	storeNLSDescription(store, objectConfig, XML_DESCRIPTION);
    }
    
    protected void storeNLS(IPropertiesStore store) throws IOException {
	if (store == null) {
	    return;
	}
	store.store();
    }
    
    protected void populateRootAttributes(Element root) {
	if (root == null) {
	    return;
	}
	root.setAttribute(XML_PROVIDER, PROVIDER);
	root.setAttribute(XML_VERSION, VERSION);
    }
    
    protected Element createRootElement(String name) {
	Element root = new Element(name);
	populateRootAttributes(root);
	return root;
    }


    public IPropertiesStore getStore() {
        return store;
    }
    

    //[Utils]
    protected static XMLOutputter createXMLOutputter() {
	// Format format = Format.getPrettyFormat();
	// format.setEncoding("UTF-8");
	XMLOutputter xmloutputter = new XMLOutputter();
	return xmloutputter;
    }

    //[Utils]
    protected XMLOutputter createXMLOutputter(String encoding) {
	Format format = Format.getPrettyFormat();
	if (encoding == null) {
	    encoding = "UTF-8";
	}
	format.setEncoding(encoding);
	XMLOutputter xmloutputter = new XMLOutputter(format);
	return xmloutputter;
    }
    
    protected List getChildren(Element rootElement, String parentNode, String node) {
	if (rootElement == null || parentNode == null || node == null) {
	    return new ArrayList();
	}
	Element parentElement = getChild(rootElement, parentNode);
	if (parentElement == null) {
	    return new ArrayList();
	}
	return getChildren(parentElement, node);
    }
    
    
    protected Element getChild(Element parent, String name) {
	return parent.getChild(name, parent.getNamespace()); // XSD Namespace
    }

    protected List getChildren(Element parent, String name) {
	return parent.getChildren(name, parent.getNamespace()); // XSD Namespace
    }
    
}
