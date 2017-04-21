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
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSQuery;
import org.plazmaforge.framework.report.storage.xml.report.XMLAbstractReportReader;


/**
 * @author ohapon
 *
 */
public class XMLDSDataSourceReader extends XMLAbstractReportReader {

    
    public DSDataSource readDataSource(Element element) {
	DSDataSource dataSource = new DSBaseDataSource();
	readDataSourceAttributes(element, dataSource);
	readDataSourceContent(element, dataSource);
	return dataSource;
    }
    
    ////

    protected void readDataSourceAttributes(Element element, DSDataSource dataSource) {
	
	readIdentifier(element, dataSource);
	
	String sValue = null;
	
	// type
	sValue = getStringValue(element, XML_ATTR_TYPE);
	if (sValue != null) {
	    dataSource.setType(sValue);
	}
	
    }
    
    protected void readDataSourceContent(Element element, DSDataSource dataSource) {
	
	// Read query
	XMLDSQueryReader reader = new XMLDSQueryReader(); 
	DSQuery query = reader.readQuery(getChild(element, XML_QUERY));
	if (query != null) {
	    dataSource.setQuery(query);
	}
	
	readParameters(element, dataSource);
	readFields(element, dataSource);
	readFilters(element, dataSource);
    }
    
 
    
    protected void readParameters(Element element, DSDataSource dataSource) {
   	Element node = getChild(element, XML_PARAMETERS);
   	if (node == null){
   	    return;
   	}

   	List children = node.getChildren();
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
    
    protected void readFields(Element element, DSDataSource dataSource) {
   	Element node = getChild(element, XML_FIELDS);
   	if (node == null){
   	    return;
   	}

   	List children = node.getChildren();
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

    protected void readFilters(Element element, DSDataSource dataSource) {
   	Element node = getChild(element, XML_FILTERS);
   	if (node == null){
   	    return;
   	}

   	List children = node.getChildren();
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
    
}
