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
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

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
	
	// horizontal alignment
	HorizontalAlign horizontalAlign = cell.getHorizontalAlign();
	if (horizontalAlign != null) {
	    setHorizontalAlign(node, XML_ATTR_HORIZONTAL_ALIGN, horizontalAlign);
	}

	// vertical alignment
	VerticalAlign verticalAlign = cell.getVerticalAlign();
	if (verticalAlign != null) {
	    setVerticalAlign(node, XML_ATTR_VERTICAL_ALIGN, verticalAlign);
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
	
	Element valueNode = null;
	
	// value
	Object value = cell.getValue();
	if (value != null) {
	    sValue = getTString(cell.getDataType(), value);
	    if (sValue != null) {
		valueNode = createElement(XML_VALUE);
		addChild(node, valueNode);
		setContentValue(valueNode, sValue);
	    }
	}

	// expression
	if (cell.getExpression() != null) {
	    valueNode = createElement(XML_EXPRESSION);
	    setExpression(cell.getExpression(), valueNode, USE_DATA_TYPE_IN_EXPRESSION);
	}
	
	// background
	Color background = cell.getBackground();
	if (background != null) {
	    setColor(node, XML_ATTR_BACKGROUND, background);
	}

	// foreground
	Color foreground = cell.getForeground();
	if (foreground != null) {
	    setColor(node, XML_ATTR_FOREGROUND, foreground);
	}

	// font
	Font font = cell.getFont();
	if (font != null) {
	    setFont(node, XML_ATTR_FONT, font);
	}		
    }
    
    
}
