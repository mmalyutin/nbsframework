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

package org.plazmaforge.framework.report.storage.xml.document;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.document.Page;

/**
 * 
 * @author ohapon
 *
 */
public class XMLPageWriter extends XMLAbstractDocumentWriter {

    //PAGE
    public Element writePage(Page page) {
	Element pageNode = new Element(XML_PAGE);
	
	//List<Element> children = new ArrayList<Element>();
	//Element elementsNode = buildPageElements(page);
	//if (elementsNode != null) {
	//    children.add(elementsNode);
	//}
	//pageNode.setContent(children);
	
	return pageNode;
    }
    
    //ELEMENTS
    protected Element buildPageElements(Page page) {
	if (!page.hasChildren()) {
	    return null;
	}
	List<org.plazmaforge.framework.report.model.base.Element> elements = page.getChildren();
	Element parentNode = new Element(XML_ELEMENTS);
	Element elementNode = null;
	for (org.plazmaforge.framework.report.model.base.Element element: elements) {
	    elementNode = buildElementNodeByType(element);
	    if (elementNode == null) {
		continue;
	    }
	    addChild(parentNode, elementNode);
	}
	return parentNode;
    }
    
    //ELEMENT-BY-TYPE
    protected Element buildElementNodeByType(org.plazmaforge.framework.report.model.base.Element element) {
	if (element instanceof Grid) {
	    return buildGridNode((Grid) element);
	}
	return buildElement(element);
    }

    //ELEMENT
    protected Element buildElement(org.plazmaforge.framework.report.model.base.Element element) {
	return null;
    }
	
    //GRID
    protected Element buildGridNode(Grid grid) {
	return null;
    }

}
