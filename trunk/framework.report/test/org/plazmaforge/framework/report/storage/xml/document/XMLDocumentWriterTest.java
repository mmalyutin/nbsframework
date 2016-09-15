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

import java.io.StringReader;
import java.io.StringWriter;

import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;

import junit.framework.TestCase;

public class XMLDocumentWriterTest extends TestCase {

    private static final String PAGE_FORMAT = "CUSTOM";
    
    private static final int PAGE_WIDTH = 600;
    
    private static final int PAGE_HEIGHT = 800;
    
    private static final int PAGE_MARGIN_LEFT = 21;
    
    private static final int PAGE_MARGIN_TOP = 22;
    
    private static final int PAGE_MARGIN_RIGHT = 23;
    
    private static final int PAGE_MARGIN_BOTTOM = 24;
    
    
    
    public void testWrite() throws Exception {
	XMLDocumentWriter writer = new XMLDocumentWriter();
	
	// Create test document
	Document document1 = createTestDocument();
	
	// Write the document
	StringWriter sw = new StringWriter();
	writer.writeDocument(document1, sw);
	
	String documentString1 = sw.toString(); 
	System.out.println(documentString1);

	// Read the document
	StringReader sr = new StringReader(documentString1);
	XMLDocumentReader reader = new XMLDocumentReader();
	Document document2 = reader.readDocument(sr);
	
	// Equals document1 and document2
	assertEquals(document1, document2);
	
    }
    
    private Document createTestDocument() {
	Document document = new Document();
	
	document.setName("Document1");
	document.setCaption("Document 1");
	document.setDescription("Document Description 1");
	
	// Page Setup
	PageSetup pageSetup = createTestPageSetup();
	document.setPageSetup(pageSetup);
	
	// Pages
	Page page = null;
	
	// Page 1
	page = createTestPage();
	document.addPage(page);
	
	// Page 2
	page = new Page();
	document.addPage(page);
	
	return document;
    }
    
    private PageSetup createTestPageSetup() {
	PageSetup pageSetup = new PageSetup();
	pageSetup.setFormat(PAGE_FORMAT);

	pageSetup.getSize().setWidth(PAGE_WIDTH);
	pageSetup.getSize().setHeight(PAGE_HEIGHT);
	
	pageSetup.getMargin().setLeft(PAGE_MARGIN_LEFT);
	pageSetup.getMargin().setTop(PAGE_MARGIN_TOP);
	pageSetup.getMargin().setRight(PAGE_MARGIN_RIGHT);
	pageSetup.getMargin().setBottom(PAGE_MARGIN_BOTTOM);
	
	return pageSetup;
    }
    
    private Page createTestPage() {
	Page page = new Page();
	Grid grid = createTestGrid();
	page.addChild(grid);
	return page;
    }

    private Grid createTestGrid() {
	Grid grid = new Grid();
	Column column = null;
	for (int i = 1; i <= 2; i++ ) {
	    column = createTestColumn(i);
	    grid.addColumn(column);
	}

	
	Row row = null;
	for (int i = 1; i <= 10; i++ ) {
	    row = createTestRow(i);
	    grid.addRow(row);
	}
	return grid;
    }
    
    private Column createTestColumn(int number) {
	Column column = new Column();
	column.setWidth(100);
	return column;
    }
    
    private Row createTestRow(int number) {
	Row row = new Row();
	row.setHeight(27);
	
	Cell cell = new Cell();
	cell.setValue("" + number);
	row.addCell(cell);

	cell = new Cell();
	cell.setValue("Product " + number);
	row.addCell(cell);
	
	return row;
    }
}
