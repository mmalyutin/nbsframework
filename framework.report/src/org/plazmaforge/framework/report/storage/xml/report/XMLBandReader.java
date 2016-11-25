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

import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.CellBorderRule;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.storage.xml.base.XMLRowReader;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * @author ohapon
 *
 */
public class XMLBandReader extends XMLAbstractReportReader {

    public Band readBand(Element element) {
	Band band = new Band();
	readBandAttributes(element, band);
	readBandContent(element, band);
	return band;
    }

    ////
    
    protected void readBandAttributes(Element element, Band band) {
	String sValue = null;
	Integer iValue = null;
	
	// type
	sValue = getStringValue(element, XML_ATTR_TYPE);
	if (sValue != null) {
	    band.setType(sValue);
	}
	
	// height
	iValue = getIntegerValue(element, XML_ATTR_HEIGHT);
	if (iValue != null) {
	    band.setHeight(iValue);
	}

	// background
	Color background = getColor(element, XML_ATTR_BACKGROUND);
	if  (background != null) {
	    band.setBackground(background);
	}
	    
	// foreground
	Color foreground = getColor(element, XML_ATTR_FOREGROUND);
	if (foreground != null) {
	    band.setForeground(foreground);
	}
	
	// font
	Font font = getFont(element, XML_ATTR_FONT);
	if (font != null) {
	    band.setFont(font);
	}   		

	
	// cell-border-rule
	CellBorderRule cellBorderRule = getCellBorderRule(element, XML_ATTR_CELL_BORDER_RULE);
	if (cellBorderRule != null) {
	    band.setCellBorderRule(cellBorderRule);
	}
	
	// cell-border	
	Pen cellBorder = getBorderPenByAttributes(element, XML_ATTR_CELL_BORDER);
	if (cellBorder != null) {
	    band.setCellLine(cellBorder);
	}

	// column-border	
	Pen columnBorder = getBorderPenByAttributes(element, XML_ATTR_COLUMN_BORDER);
	if (columnBorder != null) {
	    band.setColumnLine(columnBorder);
	}

	// row-border	
	Pen rowBorder = getBorderPenByAttributes(element, XML_ATTR_ROW_BORDER);
	if (rowBorder != null) {
	    band.setRowLine(rowBorder);
	}
	
    }
    
    protected void readBandContent(Element element, Band band) {
	readBandRows(element, band); // ONLY FOR Table report
    }
    
    
    protected void readBandRows(Element element, Band band) {
	Element node = getChild(element, XML_ROWS);
	if (node == null) {
	    return;
	}
	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	Element rowNode = null;
	XMLRowReader reader = new XMLRowReader();
	for (int i = 0; i < count; i++) {
	    rowNode = (Element) children.get(i);
	    Row row = reader.readRow(rowNode);
	    band.addRow(row);
	}
    }
    
    
}
