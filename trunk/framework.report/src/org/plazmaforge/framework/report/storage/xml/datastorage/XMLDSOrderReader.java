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

import org.jdom.Element;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.DSExpressionOrder;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSFieldOrder;
import org.plazmaforge.framework.core.datastorage.DSOrder;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractReader;

/**
 * 
 * @author ohapon
 *
 */
public class XMLDSOrderReader extends XMLAbstractReader {

    public DSOrder readOrder(Element element, DSDataSource dataSource) {
  	
  	DSOrder order = null;
  	DSExpression expression = null;
  	
  	// field
  	String fieldName = getStringValue(element, XML_ATTR_FIELD);
  	if (fieldName != null) {
  	    
  	    // Field filter
  	    DSField field = dataSource.getField(fieldName);
  	    DSFieldOrder fieldOrder = new DSFieldOrder(field);
  	    
  	    // asc
  	    Boolean asc = getBooleanValue(element, XML_ATTR_ASC);
  	    if (asc != null) {
  		fieldOrder.setAsc(asc);
  	    }
  	    
  	    order = fieldOrder;
  	} else {
  	    
  	    // Expression filter
  	    
  	    // expression
  	    expression = getExpression(getChild(element, XML_EXPRESSION));
  	    
  	    DSExpressionOrder expressionOrder = new DSExpressionOrder(expression);
  	    order = expressionOrder;
  	}

  	// name, caption, description
  	//readIdentifier(element, filter);

  	return order;

    }
    
}
