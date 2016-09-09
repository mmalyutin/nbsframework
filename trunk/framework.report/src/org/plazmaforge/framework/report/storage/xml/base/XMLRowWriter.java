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
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.storage.xml.document.XMLAbstractDocumentWriter;

/**
 * 
 * @author ohapon
 *
 */
public class XMLRowWriter extends XMLAbstractDocumentWriter {

    public void writeRow(Row row, Element node) {
	
	if (row.getHeight() > 0) {
	    setValue(node, XML_ATTR_HEIGHT, row.getHeight());
	}
	
	
	//CELLS
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
