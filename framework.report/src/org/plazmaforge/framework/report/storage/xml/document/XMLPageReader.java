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
package org.plazmaforge.framework.report.storage.xml.document;

import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.uwt.graphics.Color;

/**
 * @author ohapon
 *
 */
public class XMLPageReader extends XMLAbstractDocumentReader {

    public void readPage(Page page, Element element) {
	readPageAttributes(page, element);
	readPageContent(page, element);
    }

    ////
    
    protected void readPageAttributes(Page page, Element element) {
	
	// background
	Color background = getColor(element, XML_ATTR_BACKGROUND);
	if  (background != null) {
	    page.setBackground(background);
	}
	    
	// foreground
	Color foreground = getColor(element, XML_ATTR_FOREGROUND);
	if (foreground != null) {
	    page.setForeground(foreground);
	}

    }
    
    protected void readPageContent(Page page, Element element) {
	readPageElements(page, element);
    }
    
    
    protected void readPageElements(Page page, Element element) {
	Element node = getChild(element, XML_ELEMENTS);
	if (node == null) {
	    return;
	}
	List children = node.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	
	/*
	Element rowNode = null;
	Integer iValue = null;
	for (int i = 0; i < count; i++) {
	    rowNode = (Element) children.get(i);
	    Row row = new Row();
	    band.addRow(row);
	    
	    // Get attributes
	    iValue = getIntegerValue(rowNode, XML_ATTR_HEIGHT);
	    if (iValue != null) {
		row.setHeight(iValue);
	    }
	    
	    // Get cells
	    readRowCells(row, rowNode);
	}
	*/
    }
    
 
    
}
