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

package org.plazmaforge.framework.report.storage.xml.base;


import org.jdom.Element;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractWriter;

/**
 * 
 * @author ohapon
 *
 */
public class XMLCellWriter extends XMLAbstractWriter {

    public void writeCell(Cell cell, Element node) {
	
	// column span
	if (cell.getColspan() > 1) {
	    setIntegerValue(node, XML_ATTR_COLSPAN, cell.getColspan());
	}

	// row span
	if (cell.getRowspan() > 1) {
	    setIntegerValue(node, XML_ATTR_ROWSPAN, cell.getRowspan());
	}
	
	// dataType
	String sValue = normalizeString(cell.getDataType());
	if (sValue != null) {
	    setStringValue(node, XML_ATTR_DATA_TYPE, sValue);
	}

	// format
	sValue = normalizeString(cell.getFormat());
	if (sValue != null) {
	    setStringValue(node, XML_ATTR_FORMAT, sValue);
	}
	
	// value
	Object value = cell.getValue();
	if (value != null) {
	    sValue = toString(value, cell.getDataType());
	    if (sValue != null) {
		Element valueNode = createElement(XML_VALUE);
		addChild(node, valueNode);
		setContentValue(valueNode, sValue);
	    }
	}
	
	// expression
	// TODO
	
    }
    
    protected String convertCellValue(Object value, String dataType) {
	return value == null ? null : value.toString();
    }
    
    
}
