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
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.Padding;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractReader;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

/**
 * 
 * @author ohapon
 *
 */
public class XMLCellReader extends XMLAbstractReader {

    
    public Cell readCell(Element node) {
	
   	Cell cell = new Cell();
   	
   	String sValue = null;
   	Integer iValue = null;

   	// column span
   	iValue = getIntegerValue(node, XML_ATTR_COLSPAN);
   	if (iValue != null) {
   	    cell.setColspan(iValue);
   	}

   	// row span
   	iValue = getIntegerValue(node, XML_ATTR_ROWSPAN);
   	if (iValue != null) {
   	    cell.setRowspan(iValue);
   	}

   	// horizontal alignment
   	HorizontalAlign horizontalAlign = getHorizontalAlign(node, XML_ATTR_HORIZONTAL_ALIGN);
   	if (horizontalAlign != null) {
   	    cell.setHorizontalAlign(horizontalAlign);
   	}

   	// vertical alignment
   	VerticalAlign verticalAlign = getVerticalAlign(node, XML_ATTR_VERTICAL_ALIGN);
   	if (verticalAlign != null) {
   	    cell.setVerticalAlign(verticalAlign);
   	}
   	
   	// border
   	Border border = getBorder(node);
   	if (border != null) {
   	    cell.setBorder(border);
   	}
   	
   	// dataType
   	sValue = getStringValue(node, XML_ATTR_DATA_TYPE);
   	if (sValue != null) {
   	    cell.setDataType(sValue);
   	}

   	// format
   	sValue = getStringValue(node, XML_ATTR_FORMAT);
   	if (sValue != null) {
   	    cell.setFormat(sValue);
   	}
   	
   	// value: attribute
   	//sValue = getStringValue(element, XML_ATTR_VALUE);
   	//if (sValue != null) {
   	//    cell.setValue(sValue);
   	//}

   	// value: node
   	sValue = getContentValue(getChild(node, XML_VALUE));
   	if (sValue == null) {
   	    
   	    // value: attribute
   	    sValue = getStringValue(node, XML_ATTR_VALUE);
   	}
   	if (sValue != null) {
   	    Object value = getTValue(cell.getDataType(), sValue);
   	    cell.setValue(value);
   	}

   	// expression
   	DSExpression expression = getExpression(getChild(node, XML_EXPRESSION), USE_DATA_TYPE_IN_EXPRESSION);
   	if (expression != null) {
   	    cell.setExpression(expression);
   	}
   	
	// background
	Color background = getColor(node, XML_ATTR_BACKGROUND);
	if (background != null) {
	    cell.setBackground(background);
	}
	
   	// foreground
	Color foreground = getColor(node, XML_ATTR_FOREGROUND);
	if (foreground != null) {
	    cell.setForeground(foreground);
	}

	// font
	Font font = getFont(node, XML_ATTR_FONT);
	if (font != null) {
	    cell.setFont(font);
	}   		
   		
	
   	// padding
   	Padding padding = getPaddingByAttributes(node);
   	if (padding != null) {
   	    cell.setPadding(padding);
   	}

   	return cell;

    }
    
    
}
