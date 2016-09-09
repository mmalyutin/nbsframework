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

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;


/**
 * 
 * @author ohapon
 *
 */
public class XMLDocumentWriter extends XMLAbstractDocumentWriter {

    public void writeDocument(Document document, String fileName) throws RTException {
	org.jdom.Document doc = buildXMLDocument(document);
        writeXMLDocument(doc, fileName);
    }

    protected void writeDocument(Document document, File file) throws RTException {
	org.jdom.Document doc = buildXMLDocument(document);
        writeXMLDocument(doc, file);
    }

    protected void writeDocument(Document document, OutputStream os) throws RTException {
	org.jdom.Document doc = buildXMLDocument(document);
        writeXMLDocument(doc, os);
    }

    protected void writeDocument(Document document, Writer writer) throws RTException {
	org.jdom.Document doc = buildXMLDocument(document);
	writeXMLDocument(doc, writer);
    }
    
    
    protected org.jdom.Document buildXMLDocument(Document document) {
	//TODO
	Element root = createElement(XML_DOCUMENT);
	org.jdom.Document doc = new org.jdom.Document(root);
	
	// PAGES
	Element pagesNode = buildPagesNode(document);
	if (pagesNode != null) {
	    addChild(root, pagesNode);
	}
	
	return doc;
    }
    
    //PAGES
    protected Element buildPagesNode(Document document) {
	if (!document.hasPages()) {
	    return null;
	}
	List<Page> pages = document.getPages();
	Element parentNode = createElement(XML_PAGES);
	Element pageNode = null;
	XMLPageWriter writer = new XMLPageWriter();
	for (Page page : pages) {
	    pageNode = 	createElement(XML_PAGE);
	    writer.writePage(page, pageNode);
	    addChild(parentNode, pageNode);
	}
	return parentNode;
    }
    
    
    

}
