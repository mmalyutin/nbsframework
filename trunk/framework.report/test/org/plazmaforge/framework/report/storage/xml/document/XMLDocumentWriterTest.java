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

import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;
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
	page = createTestPage();
	document.addPage(page);
	
	//2
	page = new Page();
	document.addPage(page);
	
	return document;
    }
    
    private Page createTestPage() {
	Page page = new Page();
	Grid grid = createTestGrid();
	page.addChild(grid);
	return page;
    }

    private Grid createTestGrid() {
	Grid grid = new Grid();
	Row row = null;
	for (int i = 1; i <= 10; i++ ) {
	    row = createTestRow(i);
	    grid.addRow(row);
	}
	return grid;
    }
    
    private Row createTestRow(int number) {
	Row row = new Row();
	row.setHeight(27);
	
	Cell cell = new Cell();
	cell.setValue("" + number);
	row.addCell(cell);

	cell = new Cell();
	cell.setValue("Product" + number);
	row.addCell(cell);
	
	return row;
    }
}
