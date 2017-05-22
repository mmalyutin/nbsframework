/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

/**
 * 
 */
package org.plazmaforge.framework.report.storage.xml.datastorage;


import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.PropertyProviderFactory;
import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.report.storage.xml.report.XMLAbstractReportReader;


/**
 * @author ohapon
 *
 */
public class XMLDSDataConnectorReader extends XMLAbstractReportReader {

    private PropertyProviderFactory propertyProviderFactory;
    
    public XMLDSDataConnectorReader(PropertyProviderFactory propertyProviderFactory) {
	super();
	this.propertyProviderFactory = propertyProviderFactory;
    }

    public DSDataConnector readDataConnector(Element element) {
	String type = getStringValue(element, XML_ATTR_TYPE);
	DSDataConnector dataConnector = null;
	
	try {
	    dataConnector = createDataConnector(type);
	    PropertyProvider propertyProvider = propertyProviderFactory.getPropertyProvider(dataConnector.getClass());
	    if (propertyProvider == null) {
		throw new DSException("PropertyProvider is not initialized. PropertyProviderFactory: " + propertyProviderFactory.getClass());
	    }
	    readProperties(element, dataConnector, propertyProvider);
	} catch (DSException e) {
	    //TODO
	    System.err.println(e);
	}
	return dataConnector;
    }
    
    ////
    
    protected DSDataConnector createDataConnector(String type) throws DSException {
	if (type == null) {
	    return null;
	}
	
	if (!DataManager.supportsDataProducer(type)) {
	    throw new DSException("Unsupports DataConnector type: " + type);
	}
	
	DataProducer dataProducer = DataManager.getDataProducer(type);
	if (dataProducer == null) {
	    throw new DSException("DataProducer is not initialized by type " + type);
	}
	
	DSDataConnector dataConnector = dataProducer.createDataConnector();
	if (dataConnector == null) {
	    throw new DSException("DataConnector is not initialized by type " + type);
	}
	
	
	return dataConnector;
    }

    
    // PROPERTIES
    protected void readProperties(Element element, DSDataConnector dataConnector, PropertyProvider propertyProvider) {
   	List children = getNodeChildren(element, XML_PROPERTIES, XML_PROPERTY);
   	if (children == null || children.isEmpty()) {
   	    return;
   	}
   	int count = children.size();
   	Element child = null;
   	String name = null;
   	String value = null;
   	for (int i = 0; i < count; i++) {
   	    child = (Element) children.get(i);
   	    name = getStringValue(child, XML_ATTR_NAME);
   	    value = getContentValue(child);
   	    propertyProvider.setValue(dataConnector, name, value);
   	}
    }
    
  
    
}
