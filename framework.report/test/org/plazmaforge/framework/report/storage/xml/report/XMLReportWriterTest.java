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



import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.datastorage.DataStorage;
import org.plazmaforge.framework.datastorage.support.csv.CSVDataConnector;
import org.plazmaforge.framework.datastorage.support.json.JSONDataConnector;
import org.plazmaforge.framework.datastorage.support.xml.XMLDataConnector;
import org.plazmaforge.framework.report.ReportEngine;
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
public class XMLReportWriterTest extends TestCase {

    protected void setUp() throws Exception {
	// Initialize DataStorage: Register base DataProducer factories
	 DataStorage.init();
    }
    
    public void testSimpleWrite() throws Exception {
	
	Report report1 = createTestReport();
	
	XMLReportWriter writer = new XMLReportWriter();
	
	// Write the report
	StringWriter sw = new StringWriter();
	writer.writeReport(report1, sw);
	
	String reportString1 = sw.toString(); 
	System.out.println(reportString1);
	
	// Read the report
	StringReader sr = new StringReader(reportString1);
	XMLReportReader reader = new XMLReportReader();
	
	Report report2 = reader.readReport(sr); 
	
	// Compare report1 and report2
	assertEquals(report1, report2);
    }

    
    public void testFileReadWrite() throws Exception {
	
	// Read the report (from file)    
	XMLReportReader reader = new XMLReportReader();
	InputStream is = ReportEngine.class.getResourceAsStream("resources/reports/ReportNormalize.report.xml");
	Report report1 = reader.readReport(is);
	
	assertNotNull(report1);

	XMLReportWriter writer = new XMLReportWriter();
	
	// Write the report (to string writer)
	StringWriter sw = new StringWriter();
	writer.writeReport(report1, sw);
	
	String reportString1 = sw.toString(); 
	System.out.println(reportString1);

	// Read the report (from string reader)
	StringReader sr = new StringReader(reportString1);
	reader = new XMLReportReader();
	
	Report report2 = reader.readReport(sr); 
	
	assertNotNull(report2);

	// Compare report1 and report2
	assertEquals(report1, report2);
	
	
	/*
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
	*/

    }
    
    
    public void testDataConnectorWrite() throws Exception {
	Report report1 = new Report();
	
	DSDataConnector dataConnector1 = createTestCSVDataConnector();
	DSDataConnector dataConnector2 = createTestXMLDataConnector();
	DSDataConnector dataConnector3 = createTestJSONDataConnector();
	
	report1.addDataConnector(dataConnector1);
	report1.addDataConnector(dataConnector2);
	report1.addDataConnector(dataConnector3);
	
	XMLReportWriter writer = new XMLReportWriter();
	
	// Write the report
	StringWriter sw = new StringWriter();
	writer.writeReport(report1, sw);
	
	String reportString1 = sw.toString(); 
	System.out.println(reportString1);
	
	// Read the report
	StringReader sr = new StringReader(reportString1);
	XMLReportReader reader = new XMLReportReader();
	
	Report report2 = reader.readReport(sr); 
	
	// Compare report1 and report2
	assertEquals(report1, report2);
	
    }

    private Report createTestReport() {
	
	Report report = new Report();
	
	report.setName("Report1");
	report.setCaption("Report 1");
	report.setDescription("Report Description 1");

	
	Template template = new Template();
	template.setType("Table");
	
	template.getPageSetup().getMargin().setLeft(20);
	template.getPageSetup().getMargin().setTop(20);
	template.getPageSetup().getMargin().setBottom(20);
	
	template.addColumn(new Column(150));
	template.addColumn(new Column(250));
	template.addColumn(new Column(100));
	
	report.addTemplate(template);
	
	
	// REPORT HEADER
	Band band = new Band();
	band.setType("ReportHeader");
	template.addBand(band);
	
	Row row = new Row();
	row.setHeight(15);
	band.addRow(row);
		
	Cell cell = new Cell();
	cell.setColspan(4);
	cell.setValue("My Report");
	row.addCell(cell);
	
	return report;
    }
    
    private CSVDataConnector createTestCSVDataConnector() {
	
	CSVDataConnector dataConnector = new CSVDataConnector();
	
	
	dataConnector.setId("csv_id_1234567890");
	dataConnector.setName("csv_name_1234567890");
	dataConnector.setCaption("csv_caption_1234567890");
	dataConnector.setDescription("csv_description_1234567890");
	
	dataConnector.setFile("csv_file_1234567890");
	dataConnector.setEncoding("cp1234567890");
	dataConnector.setColumnDelimiter(";");
	dataConnector.setRowDelimiter("#NIX");
	dataConnector.setFirstRowHeader(true);
	dataConnector.setDateFormat("dd-MM-yyyy");
	dataConnector.setNumberFormat("#.00");
	
	
	return dataConnector;
    }

    private XMLDataConnector createTestXMLDataConnector() {
	
	XMLDataConnector dataConnector = new XMLDataConnector();
	
	
	dataConnector.setId("xml_id_1234567890");
	dataConnector.setName("xml_name_1234567890");
	dataConnector.setCaption("xml_caption_1234567890");
	dataConnector.setDescription("xml_description_1234567890");
	
	dataConnector.setFile("xml_file_1234567890");
	dataConnector.setEncoding("cp1234567890");
	dataConnector.setDateFormat("dd.MM.yyyy");
	dataConnector.setNumberFormat("#.000");
	
	dataConnector.setQuery("root/nodes");
	
	return dataConnector;
    }

    private JSONDataConnector createTestJSONDataConnector() {
	
	JSONDataConnector dataConnector = new JSONDataConnector();
	
	
	dataConnector.setId("json_id_1234567890");
	dataConnector.setName("json_name_1234567890");
	dataConnector.setCaption("json_caption_1234567890");
	dataConnector.setDescription("json_description_1234567890");
	
	dataConnector.setFile("json_file_1234567890");
	dataConnector.setEncoding("cp1234567890");
	dataConnector.setDateFormat("yyyy.MM.dd");
	dataConnector.setNumberFormat("#0.000");
	
	dataConnector.setQuery("root.nodes");
	
	return dataConnector;
    }
    
}
