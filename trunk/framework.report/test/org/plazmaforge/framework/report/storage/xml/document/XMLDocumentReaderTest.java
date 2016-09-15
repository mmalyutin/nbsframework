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
import java.util.List;

import org.plazmaforge.framework.report.ReportEngine;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;
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
	
	// Document
	assertNotNull(document);

	assertEquals("Document1", document.getName());
	assertEquals("Document 1", document.getCaption());
	assertEquals("Document Description1", document.getDescription());
	
	// Page Setup
	PageSetup pageSetup = document.getPageSetup();
	assertNotNull(pageSetup);
	
	// Page Setup: margin
	assertEquals(21, pageSetup.getMargin().getLeft());
	assertEquals(22, pageSetup.getMargin().getTop());
	assertEquals(23, pageSetup.getMargin().getRight());
	assertEquals(24, pageSetup.getMargin().getBottom());

	// Page Setup: format
	assertEquals("CUSTOM", pageSetup.getFormat());
	assertEquals(600, pageSetup.getSize().getWidth());
	assertEquals(800, pageSetup.getSize().getHeight());
	
	// Pages
	assertEquals(2, document.getPageCount());
	
	Page page = document.getPage(0);
	assertNotNull(page);
	
	checkPage(page);
	
	page = document.getPage(1);
	assertNotNull(page);

    }
    
    private void checkPage(Page page) {
	List<Element> elements = page.getChildren();
	assertNotNull(elements);
	assertEquals(1, elements.size());
	
	Element element = elements.get(0);
	assertTrue(element instanceof Grid);
	
	Grid grid = (Grid) element;
	
	assertEquals(2, grid.getColumnCount());
	assertEquals(3, grid.getRowCount());
	
	Column column = grid.getColumns().get(0);
	assertEquals(150, column.getWidth());
	
	column = grid.getColumns().get(1);
	assertEquals(250, column.getWidth());
	
	// Row - 1
	Row row = grid.getRows().get(0);
	assertEquals(20, row.getHeight());
	assertEquals(2, row.getCellCount());
	Cell cell = row.getCell(0);
	Object value = cell.getValue();
	assertNotNull(value);
	assertEquals("1", value.toString());

	cell = row.getCell(1);
	value = cell.getValue();
	assertNotNull(value);
	assertEquals("Product 1", value.toString());
	
	
	// Row - 2
	row = grid.getRows().get(1);
	assertEquals(25, row.getHeight());
	assertEquals(2, row.getCellCount());
	cell = row.getCell(0);
	value = cell.getValue();
	assertNotNull(value);
	assertEquals("2", value.toString());

	cell = row.getCell(1);
	value = cell.getValue();
	assertNotNull(value);
	assertEquals("Product 2", value.toString());
	

	// Row - 3
	row = grid.getRows().get(2);
	assertEquals(30, row.getHeight());
	assertEquals(2, row.getCellCount());
	cell = row.getCell(0);
	value = cell.getValue();
	assertNotNull(value);
	assertEquals("3", value.toString());

	cell = row.getCell(1);
	value = cell.getValue();
	assertNotNull(value);
	assertEquals("Product 3", value.toString());
	
	
    }
}
