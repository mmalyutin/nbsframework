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
	readDataSourceAttributes(dataSource, element);
	readDataSourceContent(dataSource, element);
	return dataSource;
    }
    
    ////

    protected void readDataSourceAttributes(DSDataSource dataSource, Element element) {
	
	readIdentifier(element, dataSource);
	
	String sValue = null;
	
	// type
	sValue = getStringValue(element, XML_ATTR_TYPE);
	if (sValue != null) {
	    dataSource.setType(sValue);
	}
	
    }
    
    protected void readDataSourceContent(DSDataSource dataSource, Element element) {
	
	// Read query
	XMLDSQueryReader reader = new XMLDSQueryReader(); 
	DSQuery query = reader.readQuery(getChild(element, XML_QUERY));
	if (query != null) {
	    dataSource.setQuery(query);
	}
	
	readParameters(dataSource, element);
	readFields(dataSource, element);
    }
    
 
    
    protected void readParameters(DSDataSource dataSource, Element element) {
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
    
    protected void readFields(DSDataSource dataSource, Element element) {
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
    
}
