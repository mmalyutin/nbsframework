/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.report;

import java.io.InputStream;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSBaseDataSource;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.report.ReportManager;
import org.plazmaforge.framework.report.ReportSamples;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.design.Report;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.report.storage.xml.document.XMLDocumentWriter;


/**
 * @author ohapon
 *
 */
public class ReportManagerTest extends AbstractTestCase {


    protected void setUp() throws Exception {
	super.setUp();
	openTestConnection();
    }

    public void testFillNullReport() throws Exception {
	System.out.println("Test fill NullReport");
	ReportManager manager = ReportEngine.getReportManager();
	Document document = manager.fillReport(null, (DSResultSet) null);
	assertNotNull(document);
	printDocument(document);
    }

    public void testFillEmptyReport() throws Exception {
	System.out.println("Test fill EmptyReport");
	ReportManager manager = ReportEngine.getReportManager();
	Report report = new Report();
	Document document = manager.fillReport(report, (DSResultSet) null);
	assertNotNull(document);
	printDocument(document);
    }

    public void testFillSimpleReport() throws Exception {
	System.out.println("Test fill SimpleReport");
	Report report = new Report();
	Template template = new Template();
	
	report.addTemplate(template);
	
	ReportManager manager = ReportEngine.getReportManager();
	Document document = manager.fillReport(report, (DSResultSet) null);
	
	printDocument(document);
    }
    
    
    public void testFillTableReportWithCustomData() throws Exception {
	System.out.println("Test fill TableReport (CustomData)");
	Report report = ReportSamples.createTableReport();
	DSResultSet reportData = ReportSamples.createProductResultSet();
	
	ReportManager manager = ReportEngine.getReportManager();
	Document document = manager.fillReport(report, reportData);
	printDocument(document);

    }

    public void testFillTableReportWithSQLConnection() throws Exception {
	System.out.println("Test fill TableReport (SQLConnection)");
	Report report = ReportSamples.createTableReport();
	DSSession session = DataManager.openSession(getConnection());
	
	String query = "SELECT GROUP_NAME, PRODUCT_NAME, PRICE FROM PRODUCT";
	DSResultSet reportData = DataManager.openResultSet(session, query);
	
	ReportManager manager = ReportEngine.getReportManager();
	Document document = manager.fillReport(report, reportData);
	printDocument(document);
    }

    public void testFillTableReportWithSQLConnection2() throws Exception {
	System.out.println("Test fill TableReport (SQLConnection-2)");
	Report report = ReportSamples.createTableReport();
	
	String query = "SELECT GROUP_NAME, PRODUCT_NAME, PRICE FROM PRODUCT";
	DSDataSource dataSource = new DSBaseDataSource();
	dataSource.setQueryText(query);
	
	report.setDataSource(dataSource);
	
	
	ReportManager manager = ReportEngine.getReportManager();
	Document document = manager.fillReport(report, getConnection());
	printDocument(document);
    }


    public void testFillReportFromFile() throws Exception {
	
	String testStorage = getTestDir();
	
	String reportFile = "resources/reports/Report1.report.xml";
	String documentFile = testStorage + "/Document1.document.xml";
	String documentFileW = testStorage + "/Document1w.document.xml";
	
   	//System.out.println("Test fill Report form file: '" + reportFile + "'");

   	ReportManager manager = ReportEngine.getReportManager();
   	
   	// 1. Read report from file
   	long start = System.currentTimeMillis();
	InputStream is = ReportEngine.class.getResourceAsStream(reportFile);
	Report report = manager.readReport(is);
	logDeltaTime("Read report time     ", start);
	assertNotNull(report);
	
	// 2. Fill report (create document)
	start = System.currentTimeMillis();
   	Document document = manager.fillReport(report, getConnection());
   	logDeltaTime("Fill report time     ", start);
   	assertNotNull(document);
   	//printDocument(document);
   	
   	// 3. Export document to XML file
   	start = System.currentTimeMillis();
   	manager.exportDocumentToFile(document, "xml", documentFile, null);
   	logDeltaTime("Export document time ", start);
   	
   	// 4. Read document from XML file
   	start = System.currentTimeMillis();
   	document = manager.readDocument(documentFile);
   	logDeltaTime("Read document time   ", start);
   	assertNotNull(document);
   	
   	// 5. Write document to XML file (see export to XML)
   	XMLDocumentWriter writer = new XMLDocumentWriter();
   	writer.writeDocument(document, documentFileW);
    }

    
    public void testExportReportFromFile() throws Exception {
	
	String testStorage = getTestDir();
	
	String reportFile = "resources/reports/Report1.report.xml";
	String documentName = testStorage + "/Document1";
	String[] exportTypes = new String[] {"xml", "html", "xls", "xlsx", "pdf"};
	
   	//System.out.println("Test fill Report form file: '" + reportFile + "'");

   	ReportManager manager = ReportEngine.getReportManager();
   	
   	long start = System.currentTimeMillis();
	InputStream is = ReportEngine.class.getResourceAsStream(reportFile);
	Report report = manager.readReport(is);
	logDeltaTime("Read report time     ", start);
	assertNotNull(report);
	
	start = System.currentTimeMillis();
   	Document document = manager.fillReport(report, getConnection());
   	logDeltaTime("Fill report time     ", start);
   	assertNotNull(document);
   	//printDocument(document);
   	
   	for (String exportType: exportTypes) {
   	    start = System.currentTimeMillis();
   	    manager.exportDocumentToFile(document, exportType, documentName + "." + exportType, null);
   	    logDeltaTime("Export document ("  + exportType + ") time ", start);
   	}
    }
    
    private float timeMs(long time ) {
	return (float) time / 1000;
    }
    
    private float timeDeltaMs(long start) {
	return timeMs(System.currentTimeMillis() - start);
    }
 
    private void logDeltaTime(String text, long start) {
	System.out.println(text + ": " + timeDeltaMs(start)  + " s");
    }

    
    private void printDocument(Document document) {
	if (document == null) {
	    System.out.println("Document is null");
	    System.out.println("=======================");
	    System.out.println();
	    return;
	}
	
	if (document.isEmpty()) {
	    System.out.println("Document is empty");
	    System.out.println("=======================");
	    System.out.println();
	    return;
	}
	
	System.out.println("Start print document...");
	System.out.println("=======================");

	System.out.println(document);
	
	List<Page> pages = document.getPages();
	int pageNo = 0;
	for (Page page: pages) {
	    pageNo++;
	    System.out.println("Page #" + pageNo);
	    List<Element> elements = page.getChildren();
	    for  (Element element: elements) {
		printElement(element);
	    }
	}
	System.out.println();
    }

    
    private void printElement(Element element) {
	System.out.println(element.toStringModel());
    }
    
    
}
