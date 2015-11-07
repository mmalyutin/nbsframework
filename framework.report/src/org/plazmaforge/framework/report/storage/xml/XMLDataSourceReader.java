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
package org.plazmaforge.framework.report.storage.xml;


import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.core.datastorage.DSComputedField;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.DSExpressionParameter;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSQuery;


/**
 * @author ohapon
 *
 */
public class XMLDataSourceReader extends XMLAbstractReportReader {

    
    public void readDataSource(DSDataSource dataSource, Element element) {
	readDataSourceAttributes(dataSource, element);
	readDataSourceContent(dataSource, element);
    }
    
    ////

    protected void readDataSourceAttributes(DSDataSource dataSource, Element element) {
	String sValue = null;
	
	// name
	sValue = getValue(element, XML_ATTR_NAME);
	if (sValue != null) {
	    dataSource.setName(sValue);
	}
	
	// caption
	sValue = getValue(element, XML_ATTR_CAPTION);
	if (sValue != null) {
	    dataSource.setCaption(sValue);
	}
	
	// type
	sValue = getValue(element, XML_ATTR_TYPE);
	if (sValue != null) {
	    dataSource.setType(sValue);
	}
	
    }
    
    protected void readDataSourceContent(DSDataSource dataSource, Element element) {
	
	// Read query
	DSQuery query = readQuery(getChild(element, XML_QUERY));
	if (query != null) {
	    dataSource.setQuery(query);
	}
	
	readParameters(dataSource, element);
	readFields(dataSource, element);
    }
    
    protected DSQuery readQuery(Element element) {
	if (element == null) {
	    return null;
	}
	DSQuery query = new DSQuery();
	String sValue = getValue(element, XML_ATTR_LANGUAGE);
	if (sValue != null) {
	    query.setLanguage(sValue);
	}
	sValue = getContentValue(element);
	if (sValue != null) {
	    query.setText(sValue);
	}
	return query;
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
   	for (int i = 0; i < count; i++) {
   	    DSParameter parameter = readParameter((Element) children.get(i));
   	    if (parameter == null) {
   		continue;
   	    }
   	    dataSource.addParameter(parameter);   	    
   	}
    }
    
    protected DSParameter readParameter(Element element) {
	
	DSExpression expression = getExpression(element);
	DSParameter parameter = expression == null ? new DSParameter() : new DSExpressionParameter(expression); 
	
	String sValue = null;
	
	// name
	sValue = getValue(element, XML_ATTR_NAME);
	if (sValue != null) {
	    parameter.setName(sValue);
	}
	
	// caption
	sValue = getValue(element, XML_ATTR_CAPTION);
	if (sValue != null) {
	    parameter.setCaption(sValue);
	}

	// dataType
	sValue = getValue(element, XML_ATTR_DATA_TYPE);
	if (sValue != null) {
	    parameter.setDataType(sValue);
	}

	// format
	//sValue = getValue(element, XML_ATTR_FORMAT);
	//if (sValue != null) {
	//    field.setFormat(sValue);
	//}

	sValue = getValue(element, XML_ATTR_DEFAULT_VALUE);
	if (sValue != null) {
	    Object defaultValue = getValuePresenterWorker().toValue(sValue, parameter.getDataType());
	    parameter.setDefaultValue(defaultValue);
	}
	
	return parameter;

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
   	for (int i = 0; i < count; i++) {
   	    DSField field = readField((Element) children.get(i));
   	    if (field == null) {
   		continue;
   	    }
   	    dataSource.addField(field);   	    
   	}
    }
    
    protected DSField readField(Element element) {
	
	DSExpression expression = getExpression(element);
	DSField field = expression == null ? new DSField() : new DSComputedField(expression); 
	
	String sValue = null;
	
	// name
	sValue = getValue(element, XML_ATTR_NAME);
	if (sValue != null) {
	    field.setName(sValue);
	}
	
	// caption
	sValue = getValue(element, XML_ATTR_CAPTION);
	if (sValue != null) {
	    field.setCaption(sValue);
	}

	// dataType
	sValue = getValue(element, XML_ATTR_DATA_TYPE);
	if (sValue != null) {
	    field.setDataType(sValue);
	}

	// format
	//sValue = getValue(element, XML_ATTR_FORMAT);
	//if (sValue != null) {
	//    field.setFormat(sValue);
	//}
	
	return field;

    }

    
}
