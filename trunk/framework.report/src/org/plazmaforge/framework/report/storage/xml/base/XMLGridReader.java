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
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.CellBorderRule;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractReader;

/**
 * 
 * @author ohapon
 *
 */
public class XMLGridReader extends XMLAbstractReader {

    public Grid readGrid(Element node) {
	Grid grid = new Grid();
	readGridAttributes(node, grid);
	readGridContent(node, grid);
	return grid;
    }

    protected void readGridAttributes(Element node, Grid grid) {
	readElementAttributes(node, grid);
	
	// cell-border-rule
	CellBorderRule cellBorderRule = getCellBorderRule(node, XML_ATTR_CELL_BORDER_RULE);
	if (cellBorderRule != null) {
	    grid.setCellBorderRule(cellBorderRule);
	}
	
	// cell-line	
	Pen cellLine = getBorderPenByAttributes(node, XML_ATTR_CELL_LINE);
	if (cellLine != null) {
	    grid.setCellLine(cellLine);
	}

	// column-line	
	Pen columnLine = getBorderPenByAttributes(node, XML_ATTR_COLUMN_LINE);
	if (columnLine != null) {
	    grid.setColumnLine(columnLine);
	}

	// row-line	
	Pen rowLine = getBorderPenByAttributes(node, XML_ATTR_ROW_LINE);
	if (rowLine != null) {
	    grid.setRowLine(rowLine);
	}
	
    }
    
    protected void readGridContent(Element node, Grid grid) {
	readColumns(node, grid);
	readRows(node, grid);
    }
    
    
    // COLUMNS
    protected void readColumns(Element node, Grid grid) {
	List children = getNodeChildren(node, XML_COLUMNS, XML_COLUMN);
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	Element columnNode = null;
	XMLColumnReader reader = new XMLColumnReader();
	for (int i = 0; i < count; i++) {
	    columnNode = (Element) children.get(i);
	    Column column = reader.readColumn(columnNode);
	    grid.addColumn(column);
	}
    }
 

    // ROWS
    protected void readRows(Element node, Grid grid) {
	List children = getNodeChildren(node, XML_ROWS, XML_ROW);
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	Element rowNode = null;
	XMLRowReader reader = new XMLRowReader();
	for (int i = 0; i < count; i++) {
	    rowNode = (Element) children.get(i);
	    Row row = reader.readRow(rowNode);
	    grid.addRow(row);
	}
    }
  
  

   
    
}
