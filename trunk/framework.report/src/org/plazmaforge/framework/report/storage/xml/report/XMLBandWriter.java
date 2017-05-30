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
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.storage.xml.base.XMLRowWriter;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;


/**
 * @author ohapon
 * 
 * Read group elements:
 * 
 * - expression
 * - bands
 *
 */
public class XMLBandWriter extends XMLAbstractReportWriter {

    public void writeBand(Band band, Element node) {
	writeBandAttributes(band, node);
	writeBandContent(band, node);
    }

    protected void writeBandAttributes(Band band, Element node) {
	

   	// type
   	if (band.getType() != null) {
   	    setStringValue(node, XML_ATTR_TYPE, band.getType());
   	}
   	
   	if (band.getHeight() > 0) {
	    setIntegerValue(node, XML_ATTR_HEIGHT, band.getHeight());
	}

	// background
	Color background = band.getBackground();
	if (background != null) {
	    setColor(node, XML_ATTR_BACKGROUND, background);
	}

	// foreground
	Color foreground = band.getForeground();
	if (foreground != null) {
	    setColor(node, XML_ATTR_FOREGROUND, foreground);
	}

	// font
	Font font = band.getFont();
	if (font != null) {
	    setFont(node, XML_ATTR_FONT, font);
	}
	
	
	Pen cellLine = band.getCellLine();
	if (cellLine != null) {
	    setBorderPenByAttributes(cellLine, node, XML_ATTR_CELL_LINE);
	}

	// column-line
	Pen columnLine = band.getColumnLine();
	if (columnLine != null) {
	    setBorderPenByAttributes(columnLine, node, XML_ATTR_COLUMN_LINE);
	}

	// row-line
	Pen rowLine = band.getRowLine();
	if (rowLine != null) {
	    setBorderPenByAttributes(rowLine, node, XML_ATTR_ROW_LINE);
	}
	
	
    }
    
    protected void writeBandContent(Band band, Element node) {
	writeRows(band, node); // ONLY FOR Table report
    }
    
    // ROWS
    protected void writeRows(Band band, Element node) {
	Element rowsNode = buildRowsNode(band);
	if (rowsNode == null) {
	    return;
	}
	addChild(node, rowsNode);	
    }
    
    protected Element buildRowsNode(Band band) {
	if (!band.hasRows()) {
	    return null;
	}
	List<Row> rows = band.getRows();
	Element parentNode = createElement(XML_ROWS);
	Element rowNode = null;
	XMLRowWriter writer = new XMLRowWriter();
	for (Row row : rows) {
	    rowNode = createElement(XML_ROW);
	    writer.writeRow(row, rowNode);
	    addChild(parentNode, rowNode);
	}
	return parentNode;
    }    
}
