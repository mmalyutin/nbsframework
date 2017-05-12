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

    public DSFilter readFilter(Element element, DSDataSource dataSource) {
  	
  	String sValue = null;
  	DSFilter filter = null;
  	DSExpression expression = null;
  	
  	// field
  	String fieldName = getStringValue(element, XML_ATTR_FIELD);
  	if (fieldName != null) {
  	    
  	    // Field filter
  	    DSField field = dataSource.getField(fieldName);
  	    DSFieldFilter fieldFilter = new DSFieldFilter(field);
  	    
  	    // operation
  	    sValue = getStringValue(element, XML_ATTR_OPERATOR);
  	    fieldFilter.setOperator(sValue);
  	    
  	    // value
  	    sValue = getContentValue(getChild(element, XML_VALUE));
  	    if (sValue != null && field != null) {
  		Serializable filterValue = (Serializable) getFormatterManager().parseValue(sValue, field.getDataType());
  		fieldFilter.setValue(filterValue);
  	    }
  	    filter = fieldFilter;
  	} else {
  	    
  	    // Expression filter
  	    
  	    // expression
  	    expression = getExpression(getChild(element, XML_EXPRESSION));
  	    
  	    DSExpressionFilter expressionFilter = new DSExpressionFilter(expression);
  	    filter = expressionFilter;
  	}

  	// name, caption, description
  	//readIdentifier(element, filter);

  	return filter;

    }
    
}
