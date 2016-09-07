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
package org.plazmaforge.framework.report.storage.xml.report;

import org.jdom.Element;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractReader;

/**
 * @author ohapon
 *
 */
public class XMLAbstractReportReader extends XMLAbstractReader implements XMLReportInfo {
    
    protected DSExpression getExpression(Element element) {
	return getExpression(element, true);
    }

    protected DSExpression getExpression(Element element, boolean isLoadDataType) {
	if (element == null) {
	    return null;
	}
	DSExpression expression = new DSExpression();
	String sValue = getContentValue(element);
	if (sValue != null) {
	    expression.setText(sValue);
	}
	if (!isLoadDataType) {
	    return expression;
	}
	sValue = getValue(element, XML_ATTR_DATA_TYPE);
	if (sValue != null) {
	    expression.setDataType(sValue);
	}
	return expression;
    }

    
}
