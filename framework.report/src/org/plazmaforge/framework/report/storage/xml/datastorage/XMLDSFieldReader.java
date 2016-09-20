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
import org.plazmaforge.framework.core.datastorage.DSComputedField;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractReader;

/**
 * 
 * @author ohapon
 *
 */
public class XMLDSFieldReader extends XMLAbstractReader {

    public DSField readField(Element element) {
	
  	DSExpression expression = getExpression(getChild(element, XML_EXPRESSION));
  	DSField field = expression == null ? new DSField() : new DSComputedField(expression); 
  	
  	readIdentifier(element, field);
  	
  	String sValue = null;
  	
  	// dataType
  	sValue = getStringValue(element, XML_ATTR_DATA_TYPE);
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
