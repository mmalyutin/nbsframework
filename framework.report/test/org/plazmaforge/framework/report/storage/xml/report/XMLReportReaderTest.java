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
package org.plazmaforge.framework.report.storage.xml.report;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSFieldFilter;
import org.plazmaforge.framework.core.datastorage.DSFieldOrder;
import org.plazmaforge.framework.core.datastorage.DSFilter;
import org.plazmaforge.framework.core.datastorage.DSOrder;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSQuery;
import org.plazmaforge.framework.core.datastorage.DSVariable;
import org.plazmaforge.framework.report.ReportEngine;
import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.model.base.PageSetup;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.ReportGroup;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.storage.xml.report.XMLReportReader;

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
	} catch (Exception ex) {
	    assertTrue(ex instanceof RTException);
	}
	
	try {
	    XMLReportReader reader = new XMLReportReader();
	    reader.readReport((File) null);
	} catch (Exception ex) {
	    assertTrue(ex instanceof RTException);
	}

	try {
	    XMLReportReader reader = new XMLReportReader();
	    reader.readReport((InputStream) null);
	} catch (Exception ex) {
	    assertTrue(ex instanceof RTException);
	}

	
    }
    
    public void testReadFromInputStream() throws Exception {
	
	XMLReportReader reader = new XMLReportReader();
	InputStream is = ReportEngine.class.getResourceAsStream("resources/reports/Report2.report.xml");
	Report report = reader.readReport(is);
	
	assertNotNull(report);
	
	// Get report attributes
	assertEquals("Report1", report.getName());
	assertEquals("Report 1", report.getCaption());
	
	// Report: Parameters
	checkParameters(report);
	
	// Report: Variables
	checkVariables(report);

	// Report: DataSource
	checkDataSource(report);
	
	// Report: Templates
	checkTemplates(report);

    }
    
    
    private void checkParameters(Report report) {
	// Get report parameters
	assertEquals(1, report.getParameterCount());
	DSParameter parameter = report.getParameters().get(0);
	assertNotNull(parameter);
	
	assertEquals("PRICE_LIMIT", parameter.getName());
	assertEquals(new Float(150.0), parameter.getDefaultValue());
    }
    
    
    private void checkVariables(Report report) {
	
	// Get report variables
	assertEquals(3, report.getVariableCount());
	
	// Variable 1
	DSVariable variable = report.getVariables().get(0);
	assertNotNull(variable);
	
	assertEquals("FULL_PRODUCT_NAME", variable.getName());
	assertEquals("String", variable.getDataType());
	assertEquals("$F{GROUP_NAME} + \"/\" + $F{PRODUCT_NAME}", variable.getExpressionText());
	
	// Variable 2
	variable = report.getVariables().get(1);
	assertNotNull(variable);
	
	assertEquals("GROUP_NAME_HEADER", variable.getName());
	assertEquals("String", variable.getDataType());
	assertEquals("\"Group: \"  + $F{GROUP_NAME}", variable.getExpressionText());
	assertEquals("Group", variable.getResetType());
	assertEquals("PRODUCT_GROUP", variable.getResetName());

	// Variable 3
	variable = report.getVariables().get(2);
	assertNotNull(variable);
	
	assertEquals("PRICE_AVG", variable.getName());
	assertEquals("Float", variable.getDataType());
	assertEquals("$F{PRICE}", variable.getExpressionText());
	assertEquals("AVG", variable.getAggregation());
	
    }
    
    private void checkDataSource(Report report) {
	
	// Get report data
	DSDataSource dataSource = report.getDataSource();
	assertNotNull(dataSource);
	
	// Get query
	DSQuery query = dataSource.getQuery();
	assertNotNull(query);
	
	String queryText = query.getText();
	assertNotNull(queryText);
	assertEquals(queryText, dataSource.getQueryText());
	assertEquals(queryText, "SELECT PRODUCT_ID, PRODUCT_NAME, GROUP_NAME, PRICE, CREATED_DATE FROM PRODUCT WHERE PRICE > :PRICE_LIMIT");
	
	// Get fields
	List<DSField> fields = dataSource.getFields();
	assertNotNull(fields);
	assertEquals(5, fields.size());
	
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
	
	// Get filters
	List<DSFilter> filters = dataSource.getFilters();
	assertNotNull(filters);
	assertEquals(1, filters.size());
	
	// Get filters[0]
	DSFilter filter = filters.get(0);
	assertNotNull(filter);
	assertTrue(filter instanceof DSFieldFilter);
	DSFieldFilter fieldFilter = (DSFieldFilter) filter;
	
	assertNotNull(fieldFilter.getField());
	assertNotNull(fieldFilter.getField().getName());
	assertNotNull(fieldFilter.getOperator());
	assertNotNull(fieldFilter.getValue());
	
	assertEquals("PRODUCT_ID", fieldFilter.getField().getName());
	assertEquals("le", fieldFilter.getOperator());
	assertEquals(194, fieldFilter.getValue());

	// Get orders
	List<DSOrder> orders = dataSource.getOrders();
	assertNotNull(orders);
	assertEquals(2, orders.size());

	// Get orders[0]
	DSOrder order = orders.get(0);
	assertNotNull(order);
	assertTrue(order instanceof DSFieldOrder);
	DSFieldOrder fieldOrder = (DSFieldOrder) order;
	
	assertNotNull(fieldOrder.getField());
	assertNotNull(fieldOrder.getField().getName());
	
	assertEquals("PRICE", fieldOrder.getField().getName());
	assertEquals(false, fieldOrder.isAsc());

	// Get orders[1]
	order = orders.get(1);
	assertNotNull(order);
	assertTrue(order instanceof DSFieldOrder);
	fieldOrder = (DSFieldOrder) order;
	
	assertNotNull(fieldOrder.getField());
	assertNotNull(fieldOrder.getField().getName());
	
	assertEquals("PRODUCT_ID", fieldOrder.getField().getName());
	assertEquals(false, fieldOrder.isAsc());
	
    }
    
    private void checkTemplates(Report report) {
	
	// Get report template
	assertEquals(1, report.getTemplateCount());
	Template template = report.getTemplates().get(0);
	
	assertNotNull(template);
	
	
	
	// Get page setup
	PageSetup pageSetup = template.getPageSetup();
	
	assertNotNull(pageSetup);
	
	assertEquals(15, pageSetup.getMargin().getLeft());
	assertEquals(22, pageSetup.getMargin().getTop());
	assertEquals(23, pageSetup.getMargin().getRight());
	assertEquals(24, pageSetup.getMargin().getBottom());
	
	
	
	
	// Get template columns
	assertEquals(4, template.getColumnCount());
	
	Column column = template.getColumn(0);
	assertNotNull(column);
	assertEquals(100, column.getWidth());

	column = template.getColumn(1);
	assertNotNull(column);
	assertEquals(150, column.getWidth());

	column = template.getColumn(2);
	assertNotNull(column);
	assertEquals(150, column.getWidth());
	
	
	

	// Get groups
	assertEquals(1, template.getGroupCount());
	
	ReportGroup group = template.getGroup(0);
	assertNotNull(group);
	
	assertEquals("PRODUCT_GROUP", group.getName());
	assertEquals("$F{GROUP_NAME}", group.getExpressionText());
	
	// Get group bands
	assertEquals(2, group.getBandCount());
	
	// Get GroupHeader
	Band band = group.getBand(0);
	assertNotNull(band);

	assertEquals(2, band.getRowCount());
	Row row = band.getRow(0);

	assertNotNull(row);
	
	assertEquals(20, row.getHeight());
	assertEquals(1, row.getCellCount());
	
	Cell cell = row.getCell(0);
	assertNotNull(cell);
	
	assertEquals("\"GroupHeader: \"  + $F{GROUP_NAME}", cell.getExpressionText());
	
	
	// Get GroupFooter
	band = group.getBand(1);
	assertNotNull(band);
	
	assertEquals(1, band.getRowCount());
	row = band.getRow(0);

	assertNotNull(row);
	
	assertEquals(20, row.getHeight());
	assertEquals(1, row.getCellCount());
	
	cell = row.getCell(0);
	assertNotNull(cell);
	
	assertEquals("GroupFooter", cell.getValue());
	
	
	// Get template bands
	assertEquals(6, template.getBandCount());
	
	// Get ReportHeader
	band = template.getBand(0);
	assertNotNull(band);
	
	assertEquals(3, band.getRowCount());
	row = band.getRow(0);
	
	assertNotNull(row);
	
	assertEquals(30, row.getHeight());
	assertEquals(1, row.getCellCount());
	
	cell = row.getCell(0);
	assertNotNull(cell);
	
	assertEquals(4, cell.getColspan());
	assertEquals(1, cell.getRowspan());
	
	assertEquals("My Report", cell.getValue());
	
	// Get ColumnHeader
	band = template.getBand(1);
	assertNotNull(band);
	
	assertEquals(1, band.getRowCount());
	row = band.getRow(0);
	
	assertNotNull(row);
	
	assertEquals(30, row.getHeight());
	assertEquals(4, row.getCellCount());

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
	assertEquals(4, row.getCellCount());

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
