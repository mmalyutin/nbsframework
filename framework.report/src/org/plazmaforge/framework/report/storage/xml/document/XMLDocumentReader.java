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
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.report.storage.DocumentReader;
import org.plazmaforge.framework.report.storage.xml.base.XMLPageSetupReader;

/**
 * 
 * @author ohapon
 *
 */
public class XMLDocumentReader extends XMLAbstractDocumentReader implements DocumentReader {

    @Override
    public Document readDocument(String fileName) throws RTException {
	org.jdom.Document doc = readXMLDocument(fileName);
	return readDocument(fileName, doc);
    }

    @Override
    public Document readDocument(File file) throws RTException {
	org.jdom.Document doc = readXMLDocument(file);
	return readDocument(file.getName(), doc);
    }

    @Override
    public Document readDocument(InputStream is) throws RTException {
	org.jdom.Document doc = readXMLDocument(is);
	return readDocument(null, doc);
    }
    
    @Override
    public Document readDocument(Reader reader) throws RTException {
	org.jdom.Document doc = readXMLDocument(reader);
	return readDocument(null, doc);
    }
    
    ////

    protected Document readDocument(String fileName, org.jdom.Document doc) {
	Element root = doc.getRootElement();
	return readDocument(fileName, root);
    }
    
    
    protected Document readDocument(String fileName, Element node) {
	String name = node.getName();

	if (!XML_DOCUMENT.equals(name)) {
	    return null;
	}
	Document document = new Document();
	
	readDocumentAttributes(node, document);
	readDocumentContent(node, document);
	

	return document;
    }
    
    protected void readDocumentAttributes(Element node, Document document) {
   	String value = null;
   	
   	// name
   	value = getStringValue(node, XML_ATTR_NAME);
   	if (value != null) {
   	    document.setName(value);
   	}
   	
   	// caption
   	value = getStringValue(node, XML_ATTR_CAPTION);
   	if (value != null) {
   	    document.setCaption(value);
   	}

   	// description
   	value = getStringValue(node, XML_ATTR_DESCRIPTION);
   	if (value != null) {
   	    document.setDescription(value);
   	}
   	
    }

    protected void readDocumentContent(Element node, Document document) {
	readPageSetup(node, document);
   	readPages(node, document);
    }
    
    // PAGE-SETUP
    protected void readPageSetup(Element node, Document document) {
	Element child = getChild(node, XML_PAGE_SETUP);
	if (child == null) {
	    return;
	}
	XMLPageSetupReader reader = new XMLPageSetupReader();
	PageSetup pageSetup = reader.readPageSetup(child);
	document.setPageSetup(pageSetup);
    }
    
    // PAGES
    protected void readPages(Element node, Document document) {
	List children = getNodeChildren(node, XML_PAGES, XML_PAGE);
	if (children == null || children.isEmpty()) {
	    return;
	}
	int count = children.size();
	XMLPageReader pageReader = new XMLPageReader();
	for (int i = 0; i < count; i++) {
	    Page page = pageReader.readPage((Element) children.get(i));
	    document.addPage(page);
	}
    }    
    
    
}
