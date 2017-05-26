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

import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.report.storage.xml.base.XMLGridReader;
import org.plazmaforge.framework.uwt.graphics.Color;

/**
 * @author ohapon
 *
 */
public class XMLPageReader extends XMLAbstractDocumentReader {

    public Page readPage(org.jdom.Element node) {
	Page page = new Page();
	readPageAttributes(node, page);
	readPageContent(node, page);
	return page;
    }

    ////
    
    protected void readPageAttributes(org.jdom.Element node, Page page) {
	
	// background
	Color background = getColor(node, XML_ATTR_BACKGROUND);
	if  (background != null) {
	    page.setBackground(background);
	}
	    
	// foreground
	Color foreground = getColor(node, XML_ATTR_FOREGROUND);
	if (foreground != null) {
	    page.setForeground(foreground);
	}

    }
    
    protected void readPageContent(org.jdom.Element node, Page page) {
	readPageElements(node, page);
    }
    
    
    protected void readPageElements(org.jdom.Element node, Page page) {
	org.jdom.Element elementsNode = getChild(node, XML_ELEMENTS);
	if (elementsNode == null) {
	    return;
	}
	// Get all children (grid, text, image...) 
	List children = elementsNode.getChildren();
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	
	org.jdom.Element childNode = null;
	for (int i = 0; i < count; i++) {
	    childNode = (org.jdom.Element) children.get(i);
	    Element child = readElementByType(childNode);
	    if (child != null) {
		page.addChild(child);
	    }
	}

    }
    
    
    protected Element readElementByType(org.jdom.Element node) {
	String elementName = node.getName();
	if (XML_ELEMENT.equals(elementName)) {
	    return readElement(node);
	} else if (XML_GRID.equals(elementName)) {
	    return readGrid(node);
	}
	// handleError("Document is not valid");
	return null;
    }
 
    protected Element readElement(org.jdom.Element node) {
	// TODO
	return null;
    }    
    
    protected Grid readGrid(org.jdom.Element node) {
	XMLGridReader reader = new XMLGridReader();
	return reader.readGrid(node);
    }    
    
}
