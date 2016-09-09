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

import java.io.StringWriter;

import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;

import junit.framework.TestCase;

public class XMLDocumentWriterTest extends TestCase {

    public void testWrite() throws Exception {
	XMLDocumentWriter writer = new XMLDocumentWriter();
	Document document = createTestDocument();
	
	StringWriter sw = new StringWriter();
	writer.writeDocument(document, sw);
	
	System.out.println(sw.toString());
	
	
    }
    
    private Document createTestDocument() {
	Document document = new Document();
	Page page = null;

	//1
	page = new Page();
	document.addPage(page);
	
	//2
	page = new Page();
	document.addPage(page);
	
	return document;
    }
}
