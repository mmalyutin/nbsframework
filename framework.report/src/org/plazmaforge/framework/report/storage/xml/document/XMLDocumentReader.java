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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;
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

    public Document readDocument(String fileName) throws RTException {
	org.jdom.Document doc = readXMLDocument(fileName);
	return readDocument(fileName, doc);
    }

    public Document readDocument(File file) throws RTException {
	org.jdom.Document doc = readXMLDocument(file);
	return readDocument(file.getName(), doc);
    }

    public Document readDocument(InputStream is) throws RTException {
	org.jdom.Document doc = readXMLDocument(is);
	return readDocument(null, doc);
    }
    
    public Document readDocument(Reader reader) throws RTException {
	org.jdom.Document doc = readXMLDocument(reader);
	return readDocument(null, doc);
    }
    

    ////

    protected org.jdom.Document readXMLDocument(String fileName) throws RTException {
	if (fileName == null) {
	    throw new RTException("Can't read report. File name is null.");
	}
	fileName = normalizeString(fileName);
	if (fileName == null) {
	    throw new RTException("Can't read report. File name is empty.");
	}
	return readXMLDocument(new File(fileName));
    }

    protected org.jdom.Document readXMLDocument(File file) throws RTException {
	if (file == null) {
	    throw new RTException("Can't read report. File is null.");
	}
	try {
	    return readXMLDocument(new FileInputStream(file));
	} catch (FileNotFoundException ex) {
	    throw new RTException(ex);
	}
    }

    protected org.jdom.Document readXMLDocument(Reader reader) throws RTException {
	if (reader == null) {
	    throw new RTException("Can't read report. Reader is null.");
	}
	try {
	    SAXBuilder builder = new SAXBuilder();
	    // builder.setValidation(false);
	    return builder.build(reader);
	} catch (Exception ex) {
	    throw new RTException(ex);
	}
    }
    
    protected org.jdom.Document readXMLDocument(InputStream is) throws RTException {
	if (is == null) {
	    throw new RTException("Can't read report. InputStream is null.");
	}
	try {
	    SAXBuilder builder = new SAXBuilder();
	    // builder.setValidation(false);
	    return builder.build(is);
	} catch (Exception ex) {
	    throw new RTException(ex);
	}
    }

    protected Document readDocument(String fileName, org.jdom.Document doc) {
	Element root = doc.getRootElement();
	return readDocument(fileName, root);
    }
    
    
    protected Document readDocument(String fileName, Element element) {
	String name = element.getName();

	if (!XML_DOCUMENT.equals(name)) {
	    return null;
	}
	Document document = new Document();
	
	readDocumentAttributes(element, document);
	readDocumentContent(element, document);
	

	return document;
    }
    
    protected void readDocumentAttributes(Element element, Document document) {
   	String value = null;
   	
   	// name
   	value = getStringValue(element, XML_ATTR_NAME);
   	if (value != null) {
   	    document.setName(value);
   	}
   	
   	// caption
   	value = getStringValue(element, XML_ATTR_CAPTION);
   	if (value != null) {
   	    document.setCaption(value);
   	}

   	// description
   	value = getStringValue(element, XML_ATTR_DESCRIPTION);
   	if (value != null) {
   	    document.setDescription(value);
   	}
   	
    }

    protected void readDocumentContent(Element element, Document document) {
	readPageSetup(element, document);
   	readPages(element, document);
    }
    
    // PAGE-SETUP
    protected void readPageSetup(Element element, Document document) {
	Element node = element.getChild(XML_PAGE_SETUP);
	if (node == null) {
	    return;
	}
	XMLPageSetupReader reader = new XMLPageSetupReader();
	PageSetup pageSetup = reader.readPageSetup(node);
	document.setPageSetup(pageSetup);
    }
    
    // PAGES
    protected void readPages(Element element, Document document) {
	Element node = getChild(element, XML_PAGES);
	if (node == null) {
	    return;
	}
	List children = node.getChildren();
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
