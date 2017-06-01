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


import org.jdom.Element;
import org.plazmaforge.framework.core.datastorage.DSExpressionFilter;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSFieldFilter;
import org.plazmaforge.framework.core.datastorage.DSFilter;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractWriter;


/**
 * @author ohapon
 * 
 */
public class XMLDSFilterWriter extends XMLAbstractWriter {
    
    
    public void writeFilter(DSFilter filter, Element node) {

	
	if (filter instanceof DSFieldFilter) {

	    // => 1. FieldFilter
	    DSFieldFilter fieldFilter = (DSFieldFilter) filter;
	    
	    // field
	    DSField field = fieldFilter.getField();
		
	    // field name
	    String fieldName = field == null ? null : field.getName();
	    
	    if (fieldName != null) {
		setStringValue(node, XML_ATTR_FIELD, fieldName);
	    }

	    // operator
	    if (fieldFilter.getOperator() != null) {
		setStringValue(node, XML_ATTR_OPERATOR, fieldFilter.getOperator());
	    }

	    // value
	    if (fieldFilter.getValue() != null) {
		String dataType = field == null ? null : field.getDataType();
		String sValue = getTString(dataType, fieldFilter.getValue());
		setStringValue(node, XML_VALUE, sValue);
	    }
	    
	} else if (filter instanceof DSExpressionFilter) {
	    
	    // => 2. ExpressionFilter
	    DSExpressionFilter expressionField = (DSExpressionFilter) filter;
	    if (expressionField.hasExpressionText()) {
		Element expressionNode = createElement(XML_EXPRESSION);
		setExpression(expressionField.getExpression(), expressionNode,	USE_DATA_TYPE_IN_EXPRESSION);
	    }
	}
	
    }


    
}
