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
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractWriter;


/**
 * @author ohapon
 * 
 */
public class XMLDSDataSourceWriter extends XMLAbstractWriter {
    
    public void writeDataSource(DSDataSource dataSource, Element node) {
	writeDataSourceAttributes(dataSource, node);
	writeDataSourceContent(dataSource, node);
    }
    
    protected void writeDataSourceAttributes(DSDataSource dataSource, Element node) {
	
	writeIdentifier(dataSource, node);

   	// type
   	if (dataSource.getType() != null) {
   	    setStringValue(node, XML_ATTR_DATA_TYPE, dataSource.getType());
   	}
	
    }

    protected void writeDataSourceContent(DSDataSource dataSource, Element node) {
	
	// TODO
	writeParameters(dataSource, node);
	writeFields(dataSource, node);
	//writeFilters(dataSource, node);
	//writeOrders(dataSource, node);
    }
    
    
    // PARAMETERS    
    protected void writeParameters(DSDataSource dataSource, Element node) {
	Element childNode = buildParametersNode(dataSource);
	if (childNode != null) {
	    addChild(node, childNode);
	}
    }

    protected Element buildParametersNode(DSDataSource dataSource) {
	if (!dataSource.hasParameters()) {
	    return null;
	}
	List<DSParameter> parameters = dataSource.getParameters();
	Element parentNode = createElement(XML_PARAMETERS);
	Element childNode = null;
	XMLDSParameterWriter writer = new XMLDSParameterWriter();
	for (DSParameter parameter : parameters) {
	    childNode =	createElement(XML_PARAMETER);
	    writer.writeParameter(parameter, childNode);
	    addChild(parentNode, childNode);
	}
	return parentNode;
    }
    
    // FIELDS    
    protected void writeFields(DSDataSource dataSource, Element node) {
	Element childNode = buildFieldsNode(dataSource);
	if (childNode != null) {
	    addChild(node, childNode);
	}
    }

    protected Element buildFieldsNode(DSDataSource dataSource) {
	if (!dataSource.hasFields()) {
	    return null;
	}
	List<DSField> fields = dataSource.getFields();
	Element parentNode = createElement(XML_FILTERS);
	Element childNode = null;
	XMLDSFieldWriter writer = new XMLDSFieldWriter();
	for (DSField field : fields) {
	    childNode =	createElement(XML_FIELD);
	    writer.writeField(field, childNode);
	    addChild(parentNode, childNode);
	}
	return parentNode;
    }    
    
}
