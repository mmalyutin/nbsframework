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

package org.plazmaforge.framework.report.storage.xml.datastorage;

import java.io.Serializable;

import org.jdom.Element;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.DSExpressionFilter;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSFieldFilter;
import org.plazmaforge.framework.core.datastorage.DSFilter;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractReader;

/**
 * 
 * @author ohapon
 *
 */
public class XMLDSFilterReader extends XMLAbstractReader {

    public DSFilter readFilter(Element node, DSDataSource dataSource) {
  	
  	String sValue = null;
  	DSFilter filter = null;
  	DSExpression expression = null;
  	
  	// field
  	String fieldName = getStringValue(node, XML_ATTR_FIELD);
  	if (fieldName != null) {
  	    
  	    // => 1. FieldFilter
  	    DSField field = dataSource.getField(fieldName);
  	    DSFieldFilter fieldFilter = new DSFieldFilter(field);
  	    
  	    // operation
  	    sValue = getStringValue(node, XML_ATTR_OPERATOR);
  	    fieldFilter.setOperator(sValue);
  	    
  	    // value
  	    sValue = getContentValue(getChild(node, XML_VALUE));
  	    if (sValue != null && field != null) {
  		String dataType = field == null ? null : field.getDataType(); 
  		Serializable filterValue = (Serializable) getTValue(dataType, sValue) ; //getFormatterManager().parseValue(sValue, field.getDataType());
  		fieldFilter.setValue(filterValue);
  	    }
  	    filter = fieldFilter;
  	} else {
  	    
  	    // => 2. ExpressionFilter
  	    
  	    // expression
  	    expression = getExpression(getChild(node, XML_EXPRESSION));
  	    
  	    DSExpressionFilter expressionFilter = new DSExpressionFilter(expression);
  	    filter = expressionFilter;
  	}

  	// name, caption, description
  	//readIdentifier(node, filter);

  	return filter;

    }
    
}
