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
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.model.base.grid.Cell;
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
	readGridAttributes(grid, element);
	readGridContent(grid, element);
	return grid;
    }

    protected void readGridAttributes(Grid grid, Element xmlElement) {
	readElementAttributes(grid, xmlElement);
    }
    
    protected void readGridContent(Grid grid, Element element) {
	readColumns(grid, element);
	readRows(grid, element);
    }
    
    
    // COLUMNS
    protected void readColumns(Grid grid, Element element) {
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
	for (int i = 0; i < count; i++) {
	    columnNode = (Element) children.get(i);
	    Column column = readColumn(columnNode);
	    grid.addColumn(column);
	}
    }
    
    protected Column readColumn(Element element) {
	Column column = new Column();
	Integer iValue = getIntegerValue(element, XML_ATTR_WIDTH);
	if (iValue != null) {
	    column.setWidth(iValue);
	}
	return column;
    }

    // ROWS
    protected void readRows(Grid grid, Element element) {
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
	for (int i = 0; i < count; i++) {
	    rowNode = (Element) children.get(i);
	    Row row = readRow(rowNode);
	    grid.addRow(row);
	}
    }
    
    protected Row readRow(Element element) {
	Row row = new Row();

	// Get attributes
	Integer iValue = getIntegerValue(element, XML_ATTR_HEIGHT);
	if (iValue != null) {
	    row.setHeight(iValue);
	}

	// Get cells
	readCells(row, element);
	
	return row;
    }
    
    // CELLS
    protected void readCells(Row row, Element element) {
	Element node = getChild(element, XML_CELLS);
	if (node == null) {
	    return;
	}
	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	Element cellNode = null;
	for (int i = 0; i < count; i++) {
	    cellNode = (Element) children.get(i);
	    Cell cell = readCell(cellNode);
	    row.addCell(cell);
	}
    }
    

    protected Cell readCell(Element element) {
	
	Cell cell = new Cell();
	
	String sValue = null;
	Integer iValue = null;

	// column span
	iValue = getIntegerValue(element, XML_ATTR_COLSPAN);
	if (iValue != null) {
	    cell.setColspan(iValue);
	}

	// row span
	iValue = getIntegerValue(element, XML_ATTR_ROWSPAN);
	if (iValue != null) {
	    cell.setRowspan(iValue);
	}

	// dataType
	sValue = getValue(element, XML_ATTR_DATA_TYPE);
	if (sValue != null) {
	    cell.setDataType(sValue);
	}

	// format
	sValue = getValue(element, XML_ATTR_FORMAT);
	if (sValue != null) {
	    cell.setFormat(sValue);
	}

	// value: attribute
	sValue = getValue(element, XML_ATTR_VALUE);
	if (sValue != null) {
	    cell.setValue(sValue);
	}

	// value: node
	sValue = getContentValue(getChild(element, XML_VALUE));
	if (sValue != null) {
	    cell.setValue(sValue);
	}

	// expression
	DSExpression expression = getExpression(getChild(element, XML_EXPRESSION), false);
	if (expression != null) {
	    cell.setExpression(expression);
	}
	
	return cell;

    }
    
}
