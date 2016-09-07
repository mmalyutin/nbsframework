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

import java.io.File;
import java.io.InputStream;

import org.plazmaforge.framework.report.ReportEngine;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.report.storage.xml.document.XMLDocumentReader;


import junit.framework.TestCase;

/**
 * @author ohapon
 *
 */
public class XMLDocumentReaderTest extends TestCase {

    public void testReadEmptyFile() throws Exception {
	
	try {
	    XMLDocumentReader reader = new XMLDocumentReader();
	    reader.readDocument((String) null);
	} catch (Exception ex){
	    assertTrue(ex instanceof RTException);
	}
	
	try {
	    XMLDocumentReader reader = new XMLDocumentReader();
	    reader.readDocument((File) null);
	} catch (Exception ex){
	    assertTrue(ex instanceof RTException);
	}

	try {
	    XMLDocumentReader reader = new XMLDocumentReader();
	    reader.readDocument((InputStream) null);
	} catch (Exception ex){
	    assertTrue(ex instanceof RTException);
	}

	
    }
    
    public void testReadFromInputStream() throws Exception {
	
	XMLDocumentReader reader = new XMLDocumentReader();
	InputStream is = ReportEngine.class.getResourceAsStream("resources/documents/Document1.document.xml");
	Document document = reader.readDocument(is);
	
	assertNotNull(document);

	assertEquals("Document1", document.getName());
	assertEquals("Document 1", document.getCaption());
	assertEquals("Document Description1", document.getDescription());
	
	assertEquals(2, document.getPageCount());
	
	Page page = document.getPage(0);
	assertNotNull(page);
	
	page = document.getPage(1);
	assertNotNull(page);

    }
}
