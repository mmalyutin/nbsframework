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


import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.core.data.MetaPropertyProvider;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.PropertyProviderFactory;
import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.report.storage.xml.report.XMLAbstractReportWriter;
import org.plazmaforge.framework.util.CoreUtils;


/**
 * @author ohapon
 *
 */
public class XMLDSDataConnectorWriter extends XMLAbstractReportWriter {

    private static List<String> generalPropertyNames;
    
    private PropertyProviderFactory propertyProviderFactory;
    
    public XMLDSDataConnectorWriter(PropertyProviderFactory propertyProviderFactory) {
	super();
	this.propertyProviderFactory = propertyProviderFactory;
    }

    public void writeDataConnector(DSDataConnector dataConnector, Element node) {
	
	String type = dataConnector.getType();
	if (type != null) {
	    setStringValue(node, XML_ATTR_TYPE, type);
	}
	try {
	    
	    writeIdentifier(dataConnector, node);
	    
	    PropertyProvider propertyProvider = propertyProviderFactory.getPropertyProvider(dataConnector.getClass());
	    if (propertyProvider == null) {
		throw new DSException("PropertyProvider is not initialized. PropertyProviderFactory: " + propertyProviderFactory.getClass());
	    }
	    writeProperties(dataConnector, node, propertyProvider);
	} catch (DSException e) {
	    //TODO
	    System.err.println(e);
	}
    }
    
    ////
    
    // PROPERTIES
    protected void writeProperties(DSDataConnector dataConnector, Element node, PropertyProvider propertyProvider) {
	
	List<String> propertyNames = ((MetaPropertyProvider) propertyProvider).getPropertyNames();
	if (propertyNames == null || propertyNames.isEmpty()) {
	    return;
	}
	
	propertyNames = filterPropertyNames(propertyNames, propertyProviderFactory);

	if (propertyNames == null || propertyNames.isEmpty()) {
	    return;
	}
	
	Element propertiesNode = createElement(XML_PROPERTIES);
   	Element child = null;
   	Object value = null;
   	String sValue = null;
	for (String name : propertyNames) {
	    value = propertyProvider.getValue(dataConnector, name);
	    if (value == null) {
		continue;
	    }
	    sValue = getTString(value.getClass().getSimpleName(), value);
	    if (sValue == null) {
		// E: Formatter not found
		continue;
	    }
	    
	    child = createElement(XML_PROPERTY);
	    setStringValue(child, XML_ATTR_NAME, name);
	    setContentValue(child, sValue, null);
	    
	    addChild(propertiesNode, child);
	}
	
	addChild(node, propertiesNode);
	
    }
    
    private static List<String> filterPropertyNames(List<String> propertyNames, PropertyProviderFactory propertyProviderFactory) {
	if (propertyNames == null || propertyNames.isEmpty()) {
	    return propertyNames;
	}

	List<String> excludes = getGeneralPropertyNames(propertyProviderFactory);
	if (excludes == null || excludes.isEmpty()) {
	    return propertyNames;
	}
	
	List<String> result = new ArrayList<String>();
	
	for (String name : propertyNames) {
	    if (!CoreUtils.contains(excludes, name)) {
		result.add(name);
	    }
	}
	
	return result;
    }
  
    private static List<String> getGeneralPropertyNames(PropertyProviderFactory propertyProviderFactory) {
	if (generalPropertyNames != null) {
	    return generalPropertyNames;
	}
	try {
	    PropertyProvider propertyProvider = propertyProviderFactory.getPropertyProvider(DSDataConnector.class);
	    if (propertyProvider == null) {
		throw new DSException("PropertyProvider is not initialized. PropertyProviderFactory: " + propertyProviderFactory.getClass());
	    }
	    List<String> propertyNames = ((MetaPropertyProvider) propertyProvider).getPropertyNames();
	    generalPropertyNames = propertyNames;
	} catch (DSException e) {
	    
	    //TODO
	    System.err.println(e);
	    
	    generalPropertyNames = new ArrayList<String>();
	}
	
	return generalPropertyNames;
	
    }
    
    
    
}
