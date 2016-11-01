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
import org.plazmaforge.framework.report.model.base.grid.CellBorderType;
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

    public Grid readGrid(Element element) {
	Grid grid = new Grid();
	readGridAttributes(element, grid);
	readGridContent(element, grid);
	return grid;
    }

    protected void readGridAttributes(Element element, Grid grid) {
	readElementAttributes(element, grid);
	
	// cell-border-type
	CellBorderType cellBorderType = getCellBorderType(element, XML_ATTR_CELL_BORDER_TYPE);
	if (cellBorderType != null) {
	    grid.setCellBorderType(cellBorderType);
	}
	
	// cell-border
	Border cellBorder = getBorder(element, "cell");
	if (cellBorder != null) {
	    grid.setCellBorder(cellBorder);
	}
    }
    
    protected void readGridContent(Element element, Grid grid) {
	readColumns(element, grid);
	readRows(element, grid);
    }
    
    
    // COLUMNS
    protected void readColumns(Element element, Grid grid) {
	Element node = getChild(element, XML_COLUMNS);
	if (node == null){
	    return;
	}
	List children = node.getChildren();
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
    protected void readRows(Element element, Grid grid) {
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
	    grid.addRow(row);
	}
    }
  
  

   
    
}
