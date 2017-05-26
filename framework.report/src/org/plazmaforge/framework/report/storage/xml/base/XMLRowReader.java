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
import org.plazmaforge.framework.report.storage.xml.XMLAbstractReader;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * 
 * @author ohapon
 *
 */
public class XMLRowReader extends XMLAbstractReader {

    
    public Row readRow(Element node) {
	Row row = new Row();

	// height
	Integer iValue = getIntegerValue(node, XML_ATTR_HEIGHT);
	if (iValue != null) {
	    row.setHeight(iValue);
	}
	
	// background
	Color background = getColor(node, XML_ATTR_BACKGROUND);
	if (background != null) {
	    row.setBackground(background);
	}

	// foreground
	Color foreground = getColor(node, XML_ATTR_FOREGROUND);
	if (foreground != null) {
	    row.setForeground(foreground);
	}

	// font
	Font font = getFont(node, XML_ATTR_FONT);
	if (font != null) {
	    row.setFont(font);
	}
	
	
	// cell-line	
	Pen cellLine = getBorderPenByAttributes(node, XML_ATTR_CELL_LINE);
	if (cellLine != null) {
	    row.setCellLine(cellLine);
	}

	// column-line	
	Pen columnLine = getBorderPenByAttributes(node, XML_ATTR_COLUMN_LINE);
	if (columnLine != null) {
	    row.setColumnLine(columnLine);
	}

	// row-line	
	Pen rowLine = getBorderPenByAttributes(node, XML_ATTR_ROW_LINE);
	if (rowLine != null) {
	    row.setRowLine(rowLine);
	}
	
	
	// border
   	Border border = getBorder(node, "cell");
   	if (border != null) {
   	    row.setCellBorder(border);
   	}
	
	// Get cells
	readCells(node, row);
	
	return row;
    }
    
    
    // CELLS
    protected void readCells(Element node, Row row) {
	List children = getNodeChildren(node, XML_CELLS, XML_CELL);
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	Element cellNode = null;
	XMLCellReader reader = new XMLCellReader();
	for (int i = 0; i < count; i++) {
	    cellNode = (Element) children.get(i);
	    Cell cell = reader.readCell(cellNode);
	    row.addCell(cell);
	}
    }
        
}
