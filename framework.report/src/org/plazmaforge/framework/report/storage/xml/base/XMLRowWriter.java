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

import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractWriter;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * 
 * @author ohapon
 *
 */
public class XMLRowWriter extends XMLAbstractWriter {

    public void writeRow(Row row, Element node) {
	
	if (row.getHeight() > 0) {
	    setIntegerValue(node, XML_ATTR_HEIGHT, row.getHeight());
	}

	// background
	Color background = row.getBackground();
	if (background != null) {
	    setColor(node, XML_ATTR_BACKGROUND, background);
	}

	// foreground
	Color foreground = row.getForeground();
	if (foreground != null) {
	    setColor(node, XML_ATTR_FOREGROUND, foreground);
	}

	// font
	Font font = row.getFont();
	if (font != null) {
	    setFont(node, XML_ATTR_FONT, font);
	}	
	
	// cell-line
	Pen cellLine = row.getCellLine();
	if (cellLine != null) {
	    setBorderPenByAttributes(cellLine, node, XML_ATTR_CELL_LINE);
	}

	// column-line
	Pen columnLine = row.getColumnLine();
	if (columnLine != null) {
	    setBorderPenByAttributes(columnLine, node, XML_ATTR_COLUMN_LINE);
	}

	// row-line
	Pen rowLine = row.getRowLine();
	if (rowLine != null) {
	    setBorderPenByAttributes(rowLine, node, XML_ATTR_ROW_LINE);
	}
	
	// border
	Border border = row.getCellBorder();
	if (border != null) {
	    setBorder(border, node, "cell");
	}
	
	// cells
	Element cellsNode = buildCellsNode(row);
	if (cellsNode != null) {
	    addChild(node, cellsNode);
	}
    }
    
    
   
    
    //CELLS
    protected Element buildCellsNode(Row row) {
	if (!row.hasCells()) {
	    return null;
	}
	List<Cell> cells = row.getCells();
	Element parentNode = createElement(XML_CELLS);
	Element cellNode = null;
	XMLCellWriter writer = new XMLCellWriter();
	for (Cell cell : cells) {
	    cellNode = 	createElement(XML_CELL);
	    writer.writeCell(cell, cellNode);
	    addChild(parentNode, cellNode);
	}
	return parentNode;
    }
    
    
    
}
