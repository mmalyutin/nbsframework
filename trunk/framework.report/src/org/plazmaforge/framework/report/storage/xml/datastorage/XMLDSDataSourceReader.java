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
import org.plazmaforge.framework.core.datastorage.DSBaseDataSource;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSFilter;
import org.plazmaforge.framework.core.datastorage.DSOrder;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSQuery;
import org.plazmaforge.framework.report.storage.xml.report.XMLAbstractReportReader;


/**
 * @author ohapon
 *
 */
public class XMLDSDataSourceReader extends XMLAbstractReportReader {

    
    public DSDataSource readDataSource(Element node) {
	DSDataSource dataSource = new DSBaseDataSource();
	readDataSourceAttributes(node, dataSource);
	readDataSourceContent(node, dataSource);
	return dataSource;
    }
    
    ////

    protected void readDataSourceAttributes(Element node, DSDataSource dataSource) {
	
	readIdentifier(node, dataSource);
	
	String sValue = null;
	
	// type
	sValue = getStringValue(node, XML_ATTR_TYPE);
	if (sValue != null) {
	    dataSource.setType(sValue);
	}
	
    }
    
    protected void readDataSourceContent(Element node, DSDataSource dataSource) {
	
	// query
	XMLDSQueryReader reader = new XMLDSQueryReader(); 
	DSQuery query = reader.readQuery(getChild(node, XML_QUERY));
	if (query != null) {
	    dataSource.setQuery(query);
	}
	
	readParameters(node, dataSource);
	readFields(node, dataSource);
	readFilters(node, dataSource);
	readOrders(node, dataSource);
    }
    
 
    // PARAMETERS
    protected void readParameters(Element node, DSDataSource dataSource) {
   	List children = getNodeChildren(node, XML_PARAMETERS, XML_PARAMETER);
   	if (children == null || children.isEmpty()) {
   	    return;
   	}
   	int count = children.size();
   	XMLDSParameterReader reader = new XMLDSParameterReader(); 
   	for (int i = 0; i < count; i++) {
   	    DSParameter parameter = reader.readParameter((Element) children.get(i));
   	    if (parameter == null) {
   		continue;
   	    }
   	    dataSource.addParameter(parameter);   	    
   	}
    }
    
    // FIELDS
    protected void readFields(Element node, DSDataSource dataSource) {
   	List children = getNodeChildren(node, XML_FIELDS, XML_FIELD);
   	if (children == null || children.isEmpty()) {
   	    return;
   	}
   	int count = children.size();
   	XMLDSFieldReader reader = new XMLDSFieldReader();
   	for (int i = 0; i < count; i++) {
   	    DSField field = reader.readField((Element) children.get(i));
   	    if (field == null) {
   		continue;
   	    }
   	    dataSource.addField(field);   	    
   	}
    }
    
    // FILTERS
    protected void readFilters(Element node, DSDataSource dataSource) {
   	List children = getNodeChildren(node, XML_FILTERS, XML_FILTER);
   	if (children == null || children.isEmpty()) {
   	    return;
   	}
   	int count = children.size();
   	XMLDSFilterReader reader = new XMLDSFilterReader();
   	for (int i = 0; i < count; i++) {
   	    DSFilter filter = reader.readFilter((Element) children.get(i), dataSource);
   	    if (filter == null) {
   		continue;
   	    }
   	    dataSource.addFilter(filter);   	    
   	}
    }

    // ORDERS
    protected void readOrders(Element node, DSDataSource dataSource) {
   	List children = getNodeChildren(node, XML_ORDERS, XML_ORDER);
   	if (children == null || children.isEmpty()) {
   	    return;
   	}
   	int count = children.size();
   	XMLDSOrderReader reader = new XMLDSOrderReader();
   	for (int i = 0; i < count; i++) {
   	    DSOrder order = reader.readOrder((Element) children.get(i), dataSource);
   	    if (order == null) {
   		continue;
   	    }
   	    dataSource.addOrder(order);   	    
   	}
    }
    
}
