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
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.storage.xml.XMLAbstractWriter;

/**
 * 
 * @author ohapon
 *
 */
public class XMLGridWriter extends XMLAbstractWriter {

    public void writeGrid(Grid grid, Element node) {
	writeGridAttributes(grid, node);
	writeColumns(grid, node);
	writeRows(grid, node);
    }

    protected void writeGridAttributes(Grid grid, Element node) {
	writeElementAttributes(grid, node);
	
	// cell-border-rule
	if (grid.getCellBorderRule() != null) {
	    setCellBorderRule(node, XML_ATTR_CELL_BORDER_RULE, grid.getCellBorderRule());
	}
	
	// cell-line
	Pen cellLine = grid.getCellLine();
	if (cellLine != null) {
	    setBorderPenByAttributes(cellLine, node, XML_ATTR_CELL_LINE);
	}

	// column-line
	Pen columnLine = grid.getColumnLine();
	if (columnLine != null) {
	    setBorderPenByAttributes(columnLine, node, XML_ATTR_COLUMN_LINE);
	}

	// row-line
	Pen rowLine = grid.getRowLine();
	if (rowLine != null) {
	    setBorderPenByAttributes(rowLine, node, XML_ATTR_ROW_LINE);
	}
	
    }
    
    //COLUMNS
    protected void writeColumns(Grid grid, Element node) {
	Element columnsNode = buildColumnsNode(grid);
	if (columnsNode == null) {
	    return;
	}
	addChild(node, columnsNode);	
    }
    

    protected Element buildColumnsNode(Grid grid) {
	if (!grid.hasColumns()) {
	    return null;
	}
	List<Column> columns = grid.getColumns();
	Element parentNode = createElement(XML_COLUMNS);
	Element columnNode = null;
	XMLColumnWriter writer = new XMLColumnWriter();
	for (Column column : columns) {
	    columnNode = createElement(XML_COLUMN);
	    writer.writeColumn(column, columnNode);
	    addChild(parentNode, columnNode);
	}
	return parentNode;
    }
    
    //ROWS
    protected void writeRows(Grid grid, Element node) {
	Element rowsNode = buildRowsNode(grid);
	if (rowsNode == null) {
	    return;
	}
	addChild(node, rowsNode);	
    }
    
    protected Element buildRowsNode(Grid grid) {
	if (!grid.hasRows()) {
	    return null;
	}
	List<Row> rows = grid.getRows();
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
