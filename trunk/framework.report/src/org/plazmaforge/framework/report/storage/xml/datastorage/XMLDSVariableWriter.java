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
import org.plazmaforge.framework.core.datastorage.DSVariable;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractWriter;


/**
 * @author ohapon
 * 
 */
public class XMLDSVariableWriter extends XMLAbstractWriter {

    
    
    public void writeVariable(DSVariable variable, Element node) {

	writeIdentifier(variable, node);
	
   	// dataType
   	if (variable.getDataType() != null) {
   	    setStringValue(node, XML_ATTR_DATA_TYPE, variable.getDataType());
   	}

   	// resetType
   	if (variable.getResetType() != null) {
   	    setStringValue(node, XML_ATTR_RESET_TYPE, variable.getResetType());
   	}
   	
   	// resetName
   	if (variable.getResetName() != null) {
   	    setStringValue(node, XML_ATTR_RESET_NAME, variable.getResetName());
   	}
	
   	// aggregation
   	if (variable.getAggregation() != null) {
   	    setStringValue(node, XML_ATTR_AGGREGATION, variable.getAggregation());
   	}

	// expression
   	Element expressionNode = null;   	
	if (variable.hasExpressionText()) {
	    expressionNode = createElement(XML_EXPRESSION);
	    addChild(node, expressionNode);
	    setExpression(variable.getExpression(), expressionNode, USE_DATA_TYPE_IN_EXPRESSION);
	}

	// initExpression
	if (variable.hasInitExpressionText()) {
	    expressionNode = createElement(XML_INIT_EXPRESSION);
	    addChild(node, expressionNode);
	    setExpression(variable.getInitExpression(), expressionNode, USE_DATA_TYPE_IN_EXPRESSION);
	}
	
    }

    
}
