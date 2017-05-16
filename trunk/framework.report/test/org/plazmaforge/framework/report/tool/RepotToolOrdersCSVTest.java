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

package org.plazmaforge.framework.report.tool;

import org.plazmaforge.framework.report.AbstractTestCase;


/**
 * Test DataConnector and Connection string external (command line arguments)
 * 
 * @author ohapon
 *
 */
public class RepotToolOrdersCSVTest extends AbstractTestCase {

    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // PDF
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    // PDF: DataConnector
    public void testToPDF_DC() {
	
	String reportFile = getResourcesFileName("reports/Orders.report.xml");
	String outputFile = getTestFileName("OrdersCSV_DC.pdf");
	String dataFile = getResourcesFileName("data/orders.csv");	
	
	String[] args = new String[] {
		"-report-file", reportFile, 
		"-output-file", outputFile,
		"-output-format", "PDF", 
		"-data-connector.type", "CSV", 
		"-data-connector.file", dataFile, 
		"-data-connector.firstRowHeader", "true",
		"-data-connector.rowDelimiter", "#LF",
		"-data-connector.encoding", "UTF-8",
		"-log", "true",		
		};
	
	ReportTool.main(args);
    }

    // PDF: Connection string
    public void testToPDF_CS() {
	
	String reportFile = getResourcesFileName("reports/Orders.report.xml");
	String outputFile = getTestFileName("OrdersCSV_CS.pdf");
	String dataFile = getResourcesFileName("data/orders.csv");	
	String connection = format("csv::{0}->(firstRowHeader=true; rowDelimiter=#LF; encoding=UTF-8)", dataFile);
	
	String[] args = new String[] {
		"-report-file", reportFile, 
		"-output-file", outputFile,
		"-output-format", "PDF", 
		"-connection", connection,
		"-log", "true",		
		};
	
	ReportTool.main(args);
    }
    
    

    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // XLS
    //////////////////////////////////////////////////////////////////////////////////////////////////
    
    // XLS: DataConnector
    public void testToXLS_DC() {
	
	String reportFile = getResourcesFileName("reports/Orders.report.xml");
	String outputFile = getTestFileName("OrdersCSV_DC.xls");
	String dataFile = getResourcesFileName("data/orders.csv");	
	
	String[] args = new String[] {
		"-report-file", reportFile, 
		"-output-file", outputFile,
		"-output-format", "XLS", 
		"-data-connector.type", "CSV", 
		"-data-connector.file", dataFile, 
		"-data-connector.firstRowHeader", "true",
		"-data-connector.rowDelimiter", "#LF",
		"-data-connector.encoding", "UTF-8",
		"-log", "true",		
		};
	
	ReportTool.main(args);
    }

    // XLS: Connection string    
    public void testToXLS_CS() {
	
	String reportFile = getResourcesFileName("reports/Orders.report.xml");
	String outputFile = getTestFileName("OrdersCSV_CS.xls");
	String dataFile = getResourcesFileName("data/orders.csv");	
	String connection = format("csv::{0}->(firstRowHeader=true; rowDelimiter=#LF; encoding=UTF-8)", dataFile);
	
	String[] args = new String[] {
		"-report-file", reportFile, 
		"-output-file", outputFile,
		"-output-format", "XLS", 
		"-connection", connection,
		"-log", "true",		
		};
	
	ReportTool.main(args);
    }

    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // HTML
    //////////////////////////////////////////////////////////////////////////////////////////////////

    // HTML: DataConnector
    public void testToHTML_DC() {
	
	String reportFile = getResourcesFileName("reports/Orders.report.xml");
	String outputFile = getTestFileName("OrdersCSV_DC.html");
	String dataFile = getResourcesFileName("data/orders.csv");	
	
	String[] args = new String[] {
		"-report-file", reportFile, 
		"-output-file", outputFile,
		"-output-format", "HTML", 
		"-data-connector.type", "CSV", 
		"-data-connector.file", dataFile, 
		"-data-connector.firstRowHeader", "true",
		"-data-connector.rowDelimiter", "#LF",
		"-data-connector.encoding", "UTF-8",
		"-log", "true",		
		};
	
	ReportTool.main(args);
    }

    // HTML: Connection string       
    public void testToHTML_CS() {
	
	String reportFile = getResourcesFileName("reports/Orders.report.xml");
	String outputFile = getTestFileName("OrdersCSV_CS.html");
	String dataFile = getResourcesFileName("data/orders.csv");	
	String connection = format("csv::{0}->(firstRowHeader=true; rowDelimiter=#LF; encoding=UTF-8)", dataFile);
	
	String[] args = new String[] {
		"-report-file", reportFile, 
		"-output-file", outputFile,
		"-output-format", "HTML", 
		"-connection", connection,
		"-log", "true",		
		};
	
	ReportTool.main(args);
    }
    
    
}
