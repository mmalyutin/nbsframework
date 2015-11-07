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
package org.plazmaforge.framework.report.storage.xml;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSQuery;
import org.plazmaforge.framework.report.ReportEngine;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.Template;

import junit.framework.TestCase;

/**
 * @author ohapon
 *
 */
public class XMLReportReaderTest extends TestCase {

    public void testReadEmptyFile() throws Exception {
	
	try {
	    XMLReportReader reader = new XMLReportReader();
	    reader.readReport((String) null);
	} catch (Exception ex){
	    assertTrue(ex instanceof RTException);
	}
	
	try {
	    XMLReportReader reader = new XMLReportReader();
	    reader.readReport((File) null);
	} catch (Exception ex){
	    assertTrue(ex instanceof RTException);
	}

	try {
	    XMLReportReader reader = new XMLReportReader();
	    reader.readReport((InputStream) null);
	} catch (Exception ex){
	    assertTrue(ex instanceof RTException);
	}

	
    }
    
    public void testReadFromInputStream() throws Exception {
	
	XMLReportReader reader = new XMLReportReader();
	InputStream is = ReportEngine.class.getResourceAsStream("resources/reports/Report1.report.xml");
	Report report = reader.readReport(is);
	
	assertNotNull(report);
	
	// Get report attributes
	assertEquals("Report1", report.getName());
	assertEquals("Report 1", report.getCaption());
	
	// Get report data
	DSDataSource dataSource = report.getDataSource();
	assertNotNull(dataSource);
	
	// Get query
	DSQuery query = dataSource.getQuery();
	assertNotNull(query);
	
	String queryText = query.getText();
	assertNotNull(queryText);
	assertEquals(queryText, dataSource.getQueryText());
	assertEquals(queryText, "SELECT PRODUCT_ID, PRODUCT_NAME, GROUP_NAME, PRICE FROM PRODUCT");
	
	// Get fields
	List<DSField> fields = dataSource.getFields();
	assertNotNull(fields);
	assertEquals(4, fields.size());
	
	DSField field = fields.get(0);
	assertNotNull(field);
	assertEquals("PRODUCT_ID", field.getName());
	assertEquals("Integer", field.getDataType());

	field = fields.get(1);
	assertNotNull(field);
	assertEquals("PRODUCT_NAME", field.getName());
	assertEquals("String", field.getDataType());

	field = fields.get(2);
	assertNotNull(field);
	assertEquals("GROUP_NAME", field.getName());
	assertEquals("String", field.getDataType());

	field = fields.get(3);
	assertNotNull(field);
	assertEquals("PRICE", field.getName());
	assertEquals("Float", field.getDataType());

	
	
	// Get report template
	assertEquals(1, report.getTemplateCount());
	Template template = report.getTemplates().get(0);
	
	assertNotNull(template);
	
	// Get page setup
	PageSetup pageSetup = template.getPageSetup();
	
	assertNotNull(pageSetup);
	
	assertEquals(21, pageSetup.getMargin().getLeft());
	assertEquals(22, pageSetup.getMargin().getTop());
	assertEquals(23, pageSetup.getMargin().getRight());
	assertEquals(24, pageSetup.getMargin().getBottom());
	
	// Get template columns
	assertEquals(3, template.getColumnCount());
	
	Column column = template.getColumn(0);
	assertNotNull(column);
	assertEquals(150, column.getWidth());

	column = template.getColumn(1);
	assertNotNull(column);
	assertEquals(250, column.getWidth());

	column = template.getColumn(2);
	assertNotNull(column);
	assertEquals(100, column.getWidth());


	// Get bands
	assertEquals(3, template.getBandCount());
	
	// Get ReportHeader
	Band band = template.getBand(0);
	assertNotNull(band);
	
	assertEquals(1, band.getRowCount());
	Row row = band.getRow(0);
	
	assertNotNull(row);
	
	assertEquals(15, row.getHeight());
	assertEquals(1, row.getCellCount());
	
	Cell cell = row.getCell(0);
	assertNotNull(cell);
	
	assertEquals(3, cell.getColspan());
	assertEquals(1, cell.getRowspan());
	
	assertEquals("My Report", cell.getValue());
	
	// Get ColumnHeader
	band = template.getBand(1);
	assertNotNull(band);
	
	assertEquals(1, band.getRowCount());
	row = band.getRow(0);
	
	assertNotNull(row);
	
	assertEquals(30, row.getHeight());
	assertEquals(3, row.getCellCount());

	// Get cell-1
	cell = row.getCell(0);
	assertNotNull(cell);
	
	assertEquals(1, cell.getColspan());
	assertEquals(1, cell.getRowspan());
	
	assertEquals("Product ID", cell.getValue());
	
	// Get cell-2
	cell = row.getCell(1);
	assertNotNull(cell);
	
	assertEquals(1, cell.getColspan());
	assertEquals(1, cell.getRowspan());
	
	assertEquals("Product Name", cell.getValue());

	// Get cell-3
	cell = row.getCell(2);
	assertNotNull(cell);
	
	assertEquals(1, cell.getColspan());
	assertEquals(1, cell.getRowspan());
	
	assertEquals("Price", cell.getValue());


	
	// Get Detail
	band = template.getBand(2);
	assertNotNull(band);

	assertEquals(1, band.getRowCount());
	row = band.getRow(0);

	assertNotNull(row);

	assertEquals(20, row.getHeight());
	assertEquals(3, row.getCellCount());

	// Get cell-1
	cell = row.getCell(0);
	assertNotNull(cell);

	assertEquals(1, cell.getColspan());
	assertEquals(1, cell.getRowspan());

	assertEquals("$F{PRODUCT_ID}", cell.getExpressionText());

	// Get cell-2
	cell = row.getCell(1);
	assertNotNull(cell);

	assertEquals(1, cell.getColspan());
	assertEquals(1, cell.getRowspan());

	assertEquals("$F{PRODUCT_NAME}", cell.getExpressionText());

	// Get cell-3
	cell = row.getCell(2);
	assertNotNull(cell);

	assertEquals(1, cell.getColspan());
	assertEquals(1, cell.getRowspan());

	assertEquals("$F{PRICE}", cell.getExpressionText());
	assertEquals("#.00", cell.getFormat());

    }
}
